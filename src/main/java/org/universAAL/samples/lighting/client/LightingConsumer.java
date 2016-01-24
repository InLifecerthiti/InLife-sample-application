/*
    Copyright 2007-2014 Fraunhofer IGD, http://www.igd.fraunhofer.de
    Fraunhofer-Gesellschaft - Institute for Computer Graphics Research

    See the NOTICE file distributed with this work for additional
    information regarding copyright ownership

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
 */
package org.universAAL.samples.lighting.client;

import java.util.List;

import org.universAAL.middleware.container.ModuleContext;
import org.universAAL.middleware.container.utils.LogUtils;
import org.universAAL.middleware.context.ContextEvent;
import org.universAAL.middleware.context.ContextEventPattern;
import org.universAAL.middleware.context.ContextSubscriber;
import org.universAAL.middleware.owl.MergedRestriction;
import org.universAAL.middleware.service.CallStatus;
import org.universAAL.middleware.service.DefaultServiceCaller;
import org.universAAL.middleware.service.ServiceCaller;
import org.universAAL.middleware.service.ServiceRequest;
import org.universAAL.middleware.service.ServiceResponse;
import org.universAAL.ontology.lighting.LightSource;
import org.universAAL.ontology.lighting.Lighting;
import org.universAAL.ontology.phThing.Device;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * @author amarinc
 * 
 */
public class LightingConsumer extends ContextSubscriber {

	private LightClient lightClient;

	public static ServiceCaller caller;

	private static final String LIGHTING_CONSUMER_NAMESPACE = "http://ontology.igd.fhg.de/LightingConsumer.owl#";

	private static final String OUTPUT_LIST_OF_LAMPS = LIGHTING_CONSUMER_NAMESPACE
			+ "controlledLamps";

	private static final String OUTPUT_OF_WEBPAGETRANSLATOR = LIGHTING_CONSUMER_NAMESPACE
			+ "callWebservicetranslator";

	public static ContextEventPattern[] getContextSubscriptionParams() {
		// I am interested in all events with a light source as subject
		ContextEventPattern cep = new ContextEventPattern();
		cep.addRestriction(MergedRestriction.getAllValuesRestriction(
				ContextEvent.PROP_RDF_SUBJECT, LightSource.MY_URI));
		return new ContextEventPattern[] { cep };
	}

	

	public LightingConsumer(ModuleContext context) {
		// the constructor register us to the bus
		super(context, getContextSubscriptionParams());

		caller = new DefaultServiceCaller(context);

		try {
			lightClient = new LightClient();
		} catch (java.awt.HeadlessException ex) {
			LogUtils.logInfo(
					Activator.mc,
					LightingConsumer.class,
					"LightingConsumer",
					new Object[] { "client activates GUI-off mode because of no screen access" },
					null);
		}
	}

	public static ServiceRequest createServiceInput(String targetLanguage,
			String inputUrl) {
		String json = "{" + "\"inputUrl\": \"" + inputUrl + "\","
				+ "\"targetLanguage\": \"" + targetLanguage + "\" }";
		ServiceRequest req = new ServiceRequest(new Lighting(), null);
		// req.setResourceComment(json);
		req.addRequiredOutput(OUTPUT_OF_WEBPAGETRANSLATOR,
				new String[] { Lighting.PROP_CONTROLS });

		return req;
	}

	// Get a list of all available light-source in the system
	public static String callService(String targetLanguage, String inputUrl) {

		// Make a call for the lamps and get the request
		ServiceResponse sr = caller.call(createServiceInput(targetLanguage,
				inputUrl));

		if (sr.getCallStatus() == CallStatus.succeeded) {
			try {
				List result = sr.getOutput(OUTPUT_OF_WEBPAGETRANSLATOR);
			} catch (Exception e) {
				LogUtils.logError(Activator.mc, LightingConsumer.class,
						"callWebservicetranslator", new Object[] {
								"got exception", e.getMessage() }, e);
				return null;
			}
		} else {
			LogUtils.logWarn(Activator.mc, LightingConsumer.class,
					"callWebservicetranslator",
					new Object[] { "callstatus is not succeeded" }, null);

		}
		return sr.getURI();
	}

	public static ServiceRequest getAllLampsRequest() {

		ServiceRequest getAllLamps = new ServiceRequest(new Lighting(), null);

		getAllLamps.addRequiredOutput(

		OUTPUT_LIST_OF_LAMPS,

		new String[] { Lighting.PROP_CONTROLS });

		return getAllLamps;
	}

	// Get a list of all available light-source in the system
	public static Device[] getControlledLamps() {

		// Make a call for the lamps and get the request
		ServiceResponse sr = caller.call(getAllLampsRequest());

		if (sr.getCallStatus() == CallStatus.succeeded) {
			try {
				List lampList = sr.getOutput(OUTPUT_LIST_OF_LAMPS);

				if (lampList == null || lampList.size() == 0) {
					LogUtils.logInfo(Activator.mc, LightingConsumer.class,
							"getControlledLamps",
							new Object[] { "there are no lamps" }, null);
					return null;
				}

				// simple create an array out of the lamp-array and give it back
				// --> finished
				LightSource[] lamps = (LightSource[]) lampList
						.toArray(new LightSource[lampList.size()]);

				return lamps;

			} catch (Exception e) {
				LogUtils.logError(Activator.mc, LightingConsumer.class,
						"getControlledLamps",
						new Object[] { "got exception", e.getMessage() }, e);
				return null;
			}
		} else {
			LogUtils.logWarn(Activator.mc, LightingConsumer.class,
					"getControlledLamps",
					new Object[] { "callstatus is not succeeded" }, null);
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ContextSubscriber#handleContextEvent(ContextEvent)
	 */
	public void handleContextEvent(ContextEvent event) {
		LogUtils.logInfo(Activator.mc, LightingConsumer.class,
				"handleContextEvent", new Object[] {
						"Received context event:\n", "    Subject     = ",
						event.getSubjectURI(), "\n", "    Subject type= ",
						event.getSubjectTypeURI(), "\n", "    Predicate   = ",
						event.getRDFPredicate(), "\n", "    Object      = ",
						event.getRDFObject() }, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ContextSubscriber#communicationChannelBroken()
	 */
	public void communicationChannelBroken() {
	}

	@Override
	public void close() {
		super.close();
		caller.close();
		// Destroy the JFrame object
		lightClient.frame.setVisible(false);
		lightClient.frame.dispose();
	}
}
