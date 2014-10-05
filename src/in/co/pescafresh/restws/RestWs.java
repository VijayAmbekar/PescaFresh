package in.co.pescafresh.restws;

import in.co.pescafresh.dao.City;
import in.co.pescafresh.dao.DBAccess;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.xml.bind.JAXBElement;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

@Path("restws")
public class RestWs {

	@GET
	@Path("/test")
	@Produces(MediaType.TEXT_XML)
	public String testWs() {
		return "<Status>Success</Status>";
	}

	// @PUT
	// @Consumes(MediaType.APPLICATION_XML)
	// public Response insertCity(JAXBElement<City> city) {
	// City nCity = city.getValue();
	// System.out.print("insertCity WS Called" + nCity.getId());
	// return Response.noContent().build();
	// }

	@POST
	@Path("/insertCity")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insertCity(String cityData) {
		JSONObject tempCity = null;
		City city = null;
		try {
			tempCity = new JSONObject(cityData);
			city = new City();
			city.setName(tempCity.getString("name"));
			city.setState(tempCity.getString("state"));
			city.setDescription(tempCity.getString("description"));
			city.setImageUrl(tempCity.getString("imageUrl"));

			int resId = DBAccess.insertCity(city);

			if (resId <= 0) {
				System.out.println("Thers is some error :" + resId);
				return Response.serverError().build();
			} else {
				return Response.status(Status.CREATED)
						.entity(String.valueOf(resId)).build();
			}
		} catch (Exception e) {
			System.out.println("Unalbe to insert City to the server:"
					+ e.toString());
			return Response.serverError().build();
		}
	}

	@GET
	@Path("/cityList")
	@Produces(MediaType.APPLICATION_JSON)
	public String getCityList() {
		JSONArray tempCityList = null;
		try {
			tempCityList = DBAccess.getCityList();

			System.out.println("Sending to Client: " + tempCityList.toString());
			return tempCityList.toString();
		} catch (Exception e) {
			System.out.println("Unalbe to get City List from the server:"
					+ e.toString());

		}
		return null;
	}

	@GET
	@Path("/city/{i}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getCity(@PathParam("i") int id) {
		try {
			JSONObject tempCity = DBAccess.getCity(id);

			System.out.println("Sending to Client: " + tempCity.toString());
			return tempCity.toString();
		} catch (Exception e) {
			System.out.println("Unalbe to get City Info from the server:"
					+ e.toString());

		}
		return null;
	}

}
