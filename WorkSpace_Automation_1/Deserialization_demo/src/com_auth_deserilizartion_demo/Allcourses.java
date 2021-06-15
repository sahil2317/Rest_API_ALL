package com_auth_deserilizartion_demo;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import com.pojoclass.Api;
import com.pojoclass.GetCourse;
import com.pojoclass.webAutomation;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

public class Allcourses {

	public static void main(String[] args) {
		//Expected Data for Courses
		String[] courseTitles = {"Selenium Webdriver Java","Cypress","Protractor"};


		String url = "https://rahulshettyacademy.com/getCourse.php?state=verifybms&code=4%2F0AY0e-g4xQlWlDRAGlTDzOcrNe4X9MzuTljJR6BcolAp43EZ2GsvxS7r8ylTevGPoA0qLzQ&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none#";
		String partialCode = url.split("code=")[1];
		String actualCode = partialCode.split("&scope")[0];
		System.out.println(actualCode);



		//Get  Access Token From Authorization Code

		String  accessTokenResponse= given().urlEncodingEnabled(false)
				.queryParams("code",actualCode)
				.queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
				.queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
				.queryParams("grant_type","authorization_code")
				.when().log().all()
				.post("https://www.googleapis.com/oauth2/v4/token").asString();

		JsonPath js = new JsonPath(accessTokenResponse);
		String accessToken =  js.getString("access_token");



		//Get Actual Response After getting Access token Using Deserializaton

		GetCourse getCourse = given().queryParam("access_token", accessToken).expect().defaultParser(Parser.JSON)
				.when()
				.get("https://rahulshettyacademy.com/getCourse.php").as(GetCourse.class);

		System.out.println(getCourse.getLinkedIn()); //To get Linkdin Url from Response
		System.out.println(getCourse.getInstructor()); //To get instructor name from response

		//To get prices for Soap UI Course from Response
		List<Api> apiCourses = getCourse.getCourses().getApi();
		for(int i=0; i < apiCourses.size(); i++) {
			if(apiCourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing")) {
				System.out.println("Price for Soap UI Course is " +apiCourses.get(i).getPrice());
			}
		}

		//To get course title for WebAutomation

		ArrayList<String> actualWebAutomationCourseList = new ArrayList<String>();
		List<webAutomation> w = getCourse.getCourses().getWebAutomation();
		for(int j=0; j<w.size();j++) {
			actualWebAutomationCourseList.add(w.get(j).getCourseTitle());
			System.out.println("All WebCourses Are listes as " +w.get(j).getCourseTitle());	
		}

		//Convert array  into arrayList(For expected Result array)
		List<String> expctedWebAutomationCourseList = Arrays.asList(courseTitles);

		//Compare two array WebAutomation List
		Assert.assertTrue(actualWebAutomationCourseList.equals(expctedWebAutomationCourseList));
		System.out.println("WebCourses are same as expected");

	}

}
