package com.lib.demo;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.ReUsableMethod;
import files.payLoad;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DynamicJson {
	
	@Test(dataProvider="BooksData")
	//Add Book API
	public void addBook(String isbn, String aisle) {
		
		
		 RestAssured.baseURI = "https://rahulshettyacademy.com";
		 String addBookResponse = given().header("Content-Type","application/json")
		.body(payLoad.addBook(isbn,aisle))
		.when().post("/Library/Addbook.php")
		.then().assertThat().statusCode(200)
		.extract().response().asString();
		
		System.out.println("POST_RESPONSE WHEN USER ADD NEW BOOK DATA  " + addBookResponse);
		JsonPath js =  ReUsableMethod.rawToJson(addBookResponse);
		String bookId = js.get("ID");
		System.out.println("Book ID is " +bookId);
	
 
  //Delete Book API 
		 
		 
		
	}
	
	
	@DataProvider(name="BooksData")
	public Object[][] getData() {
		
		   return new Object[][] {{"physics","011"},{"chemistry","012"},{"Maths","013"},{"Biology","014"}};
		
		
	}
}
