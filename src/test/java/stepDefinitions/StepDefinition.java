package stepDefinitions;

import static io.restassured.RestAssured.given;

import org.junit.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import resources.ApiResources;
import resources.TestData;
import resources.Utils;

public class StepDefinition extends Utils {
	public RequestSpecification res;
	public ResponseSpecification resSpec = new ResponseSpecBuilder().expectStatusCode(200)
			.expectContentType(ContentType.JSON).build();;
	public Response response;
	public ApiResources resourceRequest;
	public static String placeId;
	public TestData data = new TestData();

	@Given("^Add place payload with \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void add_place_payload(String name, String language, String address) throws Throwable {

		res = given().spec(requestSpecifications()).body(data.addPlacePayload(name, language, address));

	}

	@When("^user calls \"([^\"]*)\" with \"([^\"]*)\" HTTP request$")
	public Response user_calls_with_Post_HTTP_request(String resource, String httpRequest) throws Throwable {

		resourceRequest = ApiResources.valueOf(resource);

		if (httpRequest.equalsIgnoreCase("Post"))
			response = res.when().post(resourceRequest.getResource()).then().spec(resSpec).extract().response();
		else if (httpRequest.equalsIgnoreCase("Get"))
			response = res.when().get(resourceRequest.getResource()).then().spec(resSpec).extract().response();

		return response;
	}

	@Then("^the API call got success with status code (\\d+)$")
	public void the_API_call_got_success_with_status_code(int arg1) throws Throwable {
		Assert.assertEquals(200, response.getStatusCode());
	}

	@Then("^\"([^\"]*)\" response body is \"([^\"]*)\"$")
	public void response_body_is(String arg1, String arg2) throws Throwable {

		Assert.assertEquals(arg2, getJsonResp(response.asString(), arg1));
	}

	@Then("^verify place_id created maps to \"([^\"]*)\" using \"([^\"]*)\"$")
	public void verify_place_id_created_maps_to_using(String name, String resource) throws Throwable {

		placeId = getJsonResp(response.asString(), "place_id");
		resourceRequest = ApiResources.valueOf(resource);

		res = given().spec(requestSpecifications()).queryParam("place_id", placeId);

		response = user_calls_with_Post_HTTP_request(resource, "Get");

		String getResponse = response.asString();
		Assert.assertEquals(name, getJsonResp(getResponse, "name"));

	}

	@Given("^DeletePlace payload$")
	public void deleteplace_payload() throws Throwable {

		res = given().spec(requestSpecifications()).body(data.addPlaceId(placeId));

	}
}
