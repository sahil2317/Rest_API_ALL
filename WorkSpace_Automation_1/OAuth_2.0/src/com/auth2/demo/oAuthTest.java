package com.auth2.demo;

import static io.restassured.RestAssured.*;
import io.restassured.path.json.JsonPath;


public class oAuthTest {

	public static void main(String[] args) throws InterruptedException {

		
		//     Get Authorization code using selnium (banned by google for now)
		
		//		System.setProperty("webdriver.chrome.driver","E:\\Sahil\\Engineering\\Testing_Courses\\chromedriver_win32\\chromedriver.exe");
		//		WebDriver driver = new ChromeDriver();
		//		driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php");
		//		driver.findElement(By.cssSelector("input[type='email']")).sendKeys("sahilmehta845@gmail.com");
		//		driver.findElement(By.cssSelector("input[type='email']")).sendKeys(Keys.ENTER);
		//		Thread.sleep(3000);
		//		driver.findElement(By.cssSelector("input[type='password']")).sendKeys("Sahil@#231796!");
		//		driver.findElement(By.cssSelector("input[type='password']")).sendKeys(Keys.ENTER);
		//		Thread.sleep(4000);
		//      String  url = driver.getCurrentUrl();
		

		//       Get Authorization Code(Manually For Now)
		
		String url = "https://rahulshettyacademy.com/getCourse.php?state=verifybms&code=4%2F0AY0e-g53mxPz-csZ_41A8_S_xUGB4TZhF0C63KCJuzWrHaO2AiJ-hzPoC6FcFgCcTdDi9Q&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none#";
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



		//Get Actual Response After getting Access toke

		String response = given().queryParam("access_token", accessToken)
				.when().log().all()
				.get("https://rahulshettyacademy.com/getCourse.php").asString();

		System.out.println(response);


	}

}
