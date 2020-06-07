package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

public class Utils {
	public static RequestSpecification req;
	static Properties prop;

	public RequestSpecification requestSpecifications() throws IOException {

		if (req == null) {
			PrintStream log = new PrintStream(new FileOutputStream("logging.text"));
			RestAssured.baseURI = getProperty("baseUrl");

			req = new RequestSpecBuilder().setBaseUri(RestAssured.baseURI).addQueryParam("key", "qaclick123")
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log)).setContentType(ContentType.JSON).build();

			return req;
		} else
			return req;
	}

	public static String getProperty(String key) throws IOException {
		prop = new Properties();
		File f = new File("src");
		FileInputStream file = new FileInputStream(f.getAbsolutePath() + "\\test\\java\\resources\\config.properties");
		prop.load(file);
		return prop.getProperty(key);

	}
	
	public static String getJsonResp(String response, String key) {
		JsonPath js = new JsonPath(response);
		return js.get(key).toString();
	}
}
