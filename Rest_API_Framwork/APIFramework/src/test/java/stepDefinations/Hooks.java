package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@DeletePlace")
	public void beforeScenario() throws IOException 
	{ 
		//Execute this code only when placeId is null
		//Write a code that will give you place id
		
		stepDefination sm = new stepDefination();
		if(stepDefination.place_id==null) {
			sm.add_Place_Payload_with("BSM", "English", "Pune", "76754332222");
			sm.user_calls_with_http_request("AddPlaceAPI", "POST");
			sm.verify_place_Id_created_maps_to_using("BSM", "getPlaceAPI");
		}
	}
}
