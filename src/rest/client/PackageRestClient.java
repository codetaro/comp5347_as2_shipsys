package rest.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.Package;

/**
 * This class is only for test purpose,
 * it is not an essential part of online
 * shopping-shipping systems.
 * 
 * @author gyua0818
 *
 */
public class PackageRestClient {

	public static void main(String[] args) {
		
		Client client = ClientBuilder.newClient();
		String mediaType = MediaType.APPLICATION_XML;
		WebTarget root = client.target("http://localhost:9080/shipping/rest/packages");

		// Create a package
		/*
		 * There should be at least one record in table packages.
		 * Otherwise, ds.connection() cannot be established! 
		 */
		Package pac = new Package("warehouse_add", "delivery_add", "items");
		Entity<Package> packageEntity = Entity.entity(pac, mediaType);
		Response response = root.request().post(packageEntity, Response.class);
		System.out.println(response.getStatus());
	}

}
