package com.basics;

import files.payLoad;
import io.restassured.path.json.JsonPath;


//To handle Complex JSOM Application in API- So here is just worked on JSON API Parsing
public class ComplexJsonParse {
	
 //1)Print No of courses returned by API
	
	public static void main(String[] args) {
	
		JsonPath js = new JsonPath(payLoad.CoursePrice());
		
		//1)Print No of courses returned JSON in API
		        int count = js.getInt("courses.size()");
		        System.out.println("No of Courses are " +count);
		         
		//2)Print purchase amount from json
                int totalAmount = js.getInt("dashboard.purchaseAmount");
                System.out.println("Total Amount is " +totalAmount);
               
                
        //3)Print title of the first course
             String titleFirstCourse =    js.get("courses[0].title");
             System.out.println("Title of the first course is " + titleFirstCourse);
             
        //4)Print All courses a title and their respective prices
             for(int i=0; i<count;i++) {
            	String titleOfAll = js.get("courses["+i+"].title");
                int priceOfAll =	js.get("courses ["+i+"].price");
            	System.out.println("Course " + i + " is " + titleOfAll + " and  Price of " + titleOfAll + " is " +priceOfAll);
             }
             
             
         //5)Print no of copies sold by RPA Course
             System.out.println("Print no of copies sold by RPA Course");
             for(int i=0;i<count;i++) {
            	 String courseTitles = js.get("courses["+i+"].title");
            	 if(courseTitles.equalsIgnoreCase("RPA")) {
            		 //Copies sold
            		int copyCount = js.get("courses["+i+"].copies");
            		System.out.println("Number of copies in RPA " + copyCount);
            		break;
            	 }
             }
             
        //6)Verify    if sum of all course prices is equal to  purchaseAmount   
	}

}
