package com.basics;

import org.testng.Assert;
import org.testng.annotations.Test;

import files.payLoad;
import io.restassured.path.json.JsonPath;

public class sumValidation {
	
	//Verify if sum of all course prices is equal to  purchaseAmount   
	@Test
	public void sumOfCourses() {
		
		int sum = 0;
		JsonPath js = new JsonPath(payLoad.CoursePrice());
		 int count = js.getInt("courses.size()");
		 for(int i=0; i<count;i++) {
			 int prices= js.getInt("courses["+i+"].price");
			 int copies= js.getInt("courses["+i+"].copies");
			 int amount = prices * copies;
			 System.out.println("Amount is " +amount);
			 sum = sum + amount;
			 }
		 System.out.println("Total Sum is " +sum);
		 
		 int purchasedAmount = js.getInt("dashboard.purchaseAmount");
		 Assert.assertEquals(sum, purchasedAmount);
	

}
}