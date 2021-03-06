package resources;

import java.io.FileInputStream;

//To Use Request Specify Builder Class Globally 

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	
	public static RequestSpecification req;

	//To Obtain Base URL From Global  Value [Properties is java inBuilt class]
	public static String getGlobalValue (String key) throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("E:\\Sahil\\Engineering\\Testing_Courses\\Rest_API\\Rest_API_Framwork\\APIFramework\\src\\test\\java\\resources\\global.properties");
		prop.load(fis);
		return prop.getProperty(key);

	}

	public RequestSpecification requestSpecification() throws IOException {
		
		if(req == null) 
		{
            PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
            req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl"))
				.addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.setContentType(ContentType.JSON)
				.build();
		    return req;
		}
		return req;
	}

	public  String getJsonPath(Response storedResponse,String key) {
		
		String responseString = storedResponse.asString();
		JsonPath js = new JsonPath(responseString);
		return js.get(key).toString();
	}
}
