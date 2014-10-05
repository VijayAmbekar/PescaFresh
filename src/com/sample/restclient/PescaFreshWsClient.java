package com.sample.restclient;

import in.co.pescafresh.dao.City;

import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.representation.Form;

public class PescaFreshWsClient {

	public static final String BASE_URI = "http://localhost:8080/PescaFresh";
	public static final String PATH_NAME = "UserInfoService/name/";
	public static final String PATH_AGE = "UserInfoService/age/";
	public static final String CITY_XML = "<City><id>5</id></City>";

	public static final String INSERT_CITY = "insertCity";
	public static final String CITY_LIST = "cityList";

	public static void main(String[] args) {
		String name = "Vijay";
		int age = 25;

		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource resource = client.resource(BASE_URI);

		InsertCity(resource);
		GetCitiesList(resource);

		// // create a City XML WS Request
		// Form form = new Form();
		// form.add("id", 4);
		// form.add("name", "Demonstration of the client lib for forms");
		// form.add("state", "Demonstration of the client lib for forms");
		// form.add("description", "Demonstration of the client lib for forms");
		// form.add("imageUrl", "Demonstration of the client lib for forms");
		// ClientResponse response = resource.path("rest").path("restws")
		// .type(MediaType.APPLICATION_XML)
		// .put(ClientResponse.class, CITY_XML);
		// System.out.println("Form response " +
		// response.getEntity(String.class));

	}

	public static void InsertCity(WebResource resource) {
		// Create City JSON WS Request
		JSONObject cityJson = new JSONObject();
		try {
			cityJson.put("id", 4);
			cityJson.put("name", "Goa");
			cityJson.put("state", "Demonstration");
			cityJson.put("description",
					"Demonstration of the client lib for forms");
			cityJson.put("imageUrl", "Goa.jpg");

			ClientResponse response = resource.path("rest").path("restws")
					.path(INSERT_CITY).type(MediaType.APPLICATION_JSON)
					.post(ClientResponse.class, cityJson);

			System.out.println("City JSON response "
					+ response.getEntity(String.class));
		} catch (JSONException e) {
			System.out.println("City JSON Error " + e.toString());
			e.printStackTrace();
		}
	}

	public static void GetCitiesList(WebResource resource) {
		try {
			ClientResponse response = resource.path("rest").path("restws")
					.path(CITY_LIST).accept(MediaType.APPLICATION_JSON)
					.get(ClientResponse.class);

			System.out.println("CityList response "
					+ response.getEntity(String.class));
		} catch (Exception e) {
			System.out.println("CityList Error " + e.toString());
			e.printStackTrace();
		}
	}
}
