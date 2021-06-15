package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.Location;
import pojo.addPlace;

public class TestDataBuild {
	
	public addPlace addPlacePayLoad(String name, String language, String address, String Phone_number) {
		addPlace place = new addPlace();

		place.setAccuracy(50);
		place.setAddress(address);
		place.setLanguage(language);
		place.setPhone_number(Phone_number);
		place.setWebsite("http://google.com");
		place.setName(name);

		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		place.setTypes(myList);

		Location loc = new Location();
		loc.setLat(-38.383494);
		loc.setLng(33.427362);
		place.setLocation(loc);
		
		return place;
		
	}
	
	public String deletePlacePayload(String  placeId) {
		
		return "{\r\n    \"place_id\":\"" + placeId + "\"\r\n}";


	}

}
