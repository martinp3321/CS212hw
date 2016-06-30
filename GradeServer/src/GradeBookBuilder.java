import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * A class that builds a GradeBook object from a given path.
 * @author srollins
 *
 */
public class GradeBookBuilder {

	/**
	 * Build and return a GradeBook from the json document at the given path.
	 * Return null if the json document does not have a valid format.
	 * @param path
	 * @return
	 */
	public static GradeBook buildBook(Path path) {
		
		GradeBook book = new GradeBook();
		
		JSONParser parser = new JSONParser();
		try(BufferedReader reader = Files.newBufferedReader(path, Charset.forName("UTF-8")))  {			
			JSONObject contents = (JSONObject) parser.parse(reader);
			
			if(!validateStudents(contents)) {
				return null;
			}
			JSONArray array = (JSONArray) contents.get("students");			
						
			for(int i = 0; i < array.size(); i++) {
				JSONObject student = (JSONObject) array.get(i);
				
				if(!validateName(student)) {
					return null;
				}				
				String name = (String) student.get("name");
				
				if(!validateGrades(student)) {
					return null;
				}				
				
				JSONArray grades = (JSONArray) student.get("grades");
				for(int j = 0; j < grades.size(); j++) {
					Object n = grades.get(j);
					double grade = 0.0;
					if(n instanceof Long) {
						grade = ((Long) n).doubleValue();
					} else if(n instanceof Double) {
						grade = (double) n;
					} else {
						return null;
					}
					book.addGrade(name, grade);
				}
			}
			
		} catch(IOException ioe) {
			System.out.println("GradeBookBuilder.buildBook::path - " + path.toString() + " not found.");
			return null;
		} catch (ParseException e) {
			System.out.println("GradeBookBuilder.buildBook::path - " + path.toString() + " is not a valid JSON Object.");
			return null;
		}
		return book;
		
	}
	
	/**
	 * Method to verify that the JSON object has a grades key with a value of type ArrayList<Double>.
	 * @param student
	 * @return
	 */
	private static boolean validateGrades(JSONObject student) {
		if(!student.containsKey("grades") || !(student.get("grades") instanceof JSONArray)) {
			return false;
		}
		return true;
	}

	/**
	 * Method to verify that the JSON object has a name key with a value of type String.
	 * @param student
	 * @return
	 */
	private static boolean validateName(JSONObject student) {
		if(!student.containsKey("name") || !(student.get("name") instanceof String)) {
			return false;
		}
		return true;
	}
	
	/**
	 * Method to verify that the JSON object has a students key with a value of type JSONArray. 
	 * @param object
	 * @return
	 */
	private static boolean validateStudents(JSONObject contents) {
		if(!contents.containsKey("students") || !(contents.get("students") instanceof JSONArray)) {
			return false;
		}		
		return true;
	}
	
}
