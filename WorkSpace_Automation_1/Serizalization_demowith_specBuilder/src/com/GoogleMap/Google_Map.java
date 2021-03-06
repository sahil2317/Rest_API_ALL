package com.GoogleMap;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import com.pojo.Location;
import com.pojo.addPlace;

public class Google_Map {

	public static void main(String[] args) {

		addPlace place = new addPlace();

		place.setAccuracy(50);
		place.setAddress("29, side layout, cohen 09");
		place.setLanguage("French-IN");
		place.setPhone_number("(+91) 983 893 3937");
		place.setWebsite("http://google.com");
		place.setName("Frontline house");

		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		place.setTypes(myList);

		Location loc = new Location();
		loc.setLat(-38.383494);
		loc.setLng(33.427362);
		place.setLocation(loc);

		
		RestAssured.baseURI="https://rahulshettyacademy.com";

		Response  res =given().log().all().queryParam("key", "qaclick123")
				.body(place)
				.when().post("/maps/api/place/add/json")
				.then().assertThat().statusCode(200).extract().response();

		String responseString = res.asString();
		System.out.println(responseString);

	}

}
