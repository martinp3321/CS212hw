import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


//Martin Murphy
//CS212
//2/25/2016

public class DonBuilder {

	/*

// Undergraduate
{  
   "name":"Jenny",
   "id":20398776,
   "gpa":3.4,
   "year":"Junior"
}	
	
//Graduate
{  
   "name":"Jenny",
   "id":20398776,
   "gpa":3.4,
   "undergrad_uni":"Mills College"
} 
 
//Faculty
{  
   "name":"Jenny",
   "id":20398776,
   "salary":108765
}
	
	 */
	
	/**
	 * Construct an object of type Don.
	 * The method should determine the type of object to instantiate based on the 
	 * schema of the JSONObject.
	 * Above are examples of the schemas for Undergraduate, Graduate, and Faculty.
	 * 
	 * name must be a String
	 * id must be a whole number
	 * gpa must be a double
	 * undergrad_unit must be a String
	 * salary must be a whole number
	 * 
	 * The method will return null if any items are missing or if the value 
	 * associated with a key is not of the correct type.
	 * 
	 * @param donJson
	 * @return
	 */
	public static Don buildDon(JSONObject donJson) {
		

		if(!(donJson.get("name") instanceof String)){
			return null;
		}
		
		
		
		if(!(donJson.get("id") instanceof Long)){
			return null;
		}
		
		
		String name = (String) donJson.get("name");	
		
		
		long id = (long) donJson.get("id");	
		
		
		if(donJson.containsKey("year")){
			
			double gpa = (double) donJson.get("gpa");
			String year = (String) donJson.get("year");
			return new Undergraduate(name,id,gpa,year);
		}
		
		
			
		if(donJson.containsKey("undergrad_uni")){
			double gpa = (double) donJson.get("gpa");
			String undergrad_uni = (String) donJson.get("undergrad_uni");
				
				return new Graduate(name,id,gpa,undergrad_uni);
				
			}
		
		
		if(!(donJson.get("salary") instanceof Long)){
			return null;
		}
		
		else if(donJson.containsKey("salary")){
			
				long salary = (long) donJson.get("salary");	
				
				
				
				
				return new Faculty(name,id,salary);
				
			}
		

		return null ;
				
		

	
}
}
