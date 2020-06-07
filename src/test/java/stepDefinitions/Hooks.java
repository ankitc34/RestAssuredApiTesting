package stepDefinitions;

import cucumber.api.java.Before;

public class Hooks {

	@Before("@DeletePlace")
	public void beforeScenario() throws Throwable {

		StepDefinition sd = new StepDefinition();

		if (StepDefinition.placeId == null) {
			sd.add_place_payload("Ank", "English", "2/3 Rajani Khand, Lucknow");
			sd.user_calls_with_Post_HTTP_request("AddPlaceAPI", "Post");
			sd.verify_place_id_created_maps_to_using("Ank", "GetPlaceAPI");
		}
	}

}
