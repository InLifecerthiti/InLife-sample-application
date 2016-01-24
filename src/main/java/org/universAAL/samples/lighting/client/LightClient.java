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

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.Arrays;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.WindowConstants;

import org.universAAL.ontology.phThing.Device;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class LightClient extends javax.swing.JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField inputUrl, language;
	private JButton callServiceButton;
	private AbstractAction CallWebpagetranslator;

	JFrame frame;

	static {
		// Vadim - turn off the logging
		java.util.logging.Logger.getLogger("sun").setLevel(
				java.util.logging.Level.OFF);
		java.util.logging.Logger.getLogger("java").setLevel(
				java.util.logging.Level.OFF);
		java.util.logging.Logger.getLogger("javax").setLevel(
				java.util.logging.Level.OFF);
	}

	// create the GUI
	public void start() {

		frame = new JFrame();
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setSize(500, 400);
		frame.setVisible(true);
		frame.getContentPane().setLayout(null);
		frame.setTitle("SST Client");
		frame.setEnabled(true);

		{
			language = new JTextField();
			frame.getContentPane().add(language);
			language.setText("language");
			language.setBounds(160, 12, 80, 21);

		}

		{
			inputUrl = new JTextField();
			frame.getContentPane().add(inputUrl);
			inputUrl.setText("inputUrl");
			inputUrl.setBounds(103, 69, 80, 21);
		}
		{
			callServiceButton = new JButton();
			frame.getContentPane().add(callServiceButton);
			callServiceButton.setText("Call Service");
			callServiceButton.setBounds(25, 120, 160, 35);
			callServiceButton.setAction(getGetLampsAction());
		}

	}

	/**
	 * @return
	 */
	private Action getGetLampsAction() {
		if (CallWebpagetranslator == null) {
			CallWebpagetranslator = new AbstractAction("Call Service", null) {
				private static final long serialVersionUID = 1L;

				public void actionPerformed(ActionEvent evt) {
					String lang = language.getText();
					String url = inputUrl.getText();
					String json = "{" + "\"inputUrl\": \"" + url + "\","
							+ "\"targetLanguage\": \"" + lang + "\" }";
					WebServices.callRestfulServicePOST(json,
							WebServices.Translatewebpage);

				}
			};
		}
		return CallWebpagetranslator;
	}

	public LightClient() {
		super();
		initGUI();
		start();
	}

	private void initGUI() {
		try {
			setPreferredSize(new Dimension(400, 300));
			this.setLayout(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Auto-generated method for setting the popup menu for a component
	 */
	private void setComponentPopupMenu(final java.awt.Component parent,
			final javax.swing.JPopupMenu menu) {
		parent.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent e) {
				if (e.isPopupTrigger())
					menu.show(parent, e.getX(), e.getY());
			}

			public void mouseReleased(java.awt.event.MouseEvent e) {
				if (e.isPopupTrigger())
					menu.show(parent, e.getX(), e.getY());
			}
		});
	}

}
