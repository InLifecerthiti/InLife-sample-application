package INLIFE_sample_application.src.main.java.org.universAAL.samples.lighting.client;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class WebServices {

	public static String Translatewebpage = "http://160.40.50.183:8080/CLOUD4All_SST_Restful_WS/SST/call_Translatewebpage";

	public static void main(String args[]) {

		// String json = "{" + "\"inputUrl\": \"http://www.uom.gr\","
		// + "\"targetLanguage\": \"fr\" }";
		//
		// callRestfulServicePOST(json, Translatewebpage);
	}

	public static String callRestfulServicePOST(String inputJsonStr, String link) {

		System.out.println("\nWeb service input:\n");
		System.out.println(inputJsonStr);

		Client client = Client.create();
		WebResource webResource = client.resource(link);
		ClientResponse response = webResource.accept("application/json")
				.type("application/json")
				.post(ClientResponse.class, inputJsonStr);
		String output = response.getEntity(String.class);

		System.out.println("\nWeb service output:\n");
		System.out.println(output);

		return output;
	}

}