package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pojo.Location;
import pojo.addPlace;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class stepDefination extends Utils {


	RequestSpecification  res;
	ResponseSpecification responseSpec;
	Response storedResponse;
	static String place_id;
	TestDataBuild data = new TestDataBuild();


	@Given("Add Place Payload with {string} {string} {string} {string}")
	public void add_Place_Payload_with(String name, String language, String address, String Phone_number) throws IOException {

		res =given().spec(requestSpecification()).body(data.addPlacePayLoad(name,language,address,Phone_number));
	}

	@When("User calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String httpMethod) {

		//constructor from APIResource.java will be called with valueOf method of resource which you pass
		APIResources resourceAPI = APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource()); 

		responseSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		if(httpMethod.equalsIgnoreCase("POST"))
		{

			storedResponse = res.when().post(resourceAPI.getResource());

		}else if(httpMethod.equalsIgnoreCase("GET")) 
		{
			storedResponse = res.when().get(resourceAPI.getResource());
		}
	}

	@Then("The API call got success with status code {int}")
	public void the_API_call_got_success_with_status_code(Integer int1) {

		assertEquals(storedResponse.getStatusCode(),200);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String Expectedvalue) {

		assertEquals(getJsonPath(storedResponse, keyValue),Expectedvalue);
	}

	//To verify  place is added- Use Get Request for same
	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_Id_created_maps_to_using(String expectedName, String resource) throws IOException {

		// prepare request specification
		place_id =getJsonPath(storedResponse,"place_id");
		res =given().spec(requestSpecification()).queryParam("place_id", place_id);
		user_calls_with_http_request(resource,"GET");
		String actualName = getJsonPath(storedResponse,"name");
		assertEquals(actualName,expectedName);
		//get API call

	}


	@Given("DeletePlace Payload")
	public void deleteplace_Payload() throws IOException {
		res = given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));

	}
}
