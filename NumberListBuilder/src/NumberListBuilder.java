import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


//Martin Murphy
//2/4/2016
//CS212
//HW2



public class NumberListBuilder {
	
	//Fields
	private Path path;
	static ArrayList<Integer> numeroArray;
	static ArrayList<Integer> result;
	
	
	
	//Constructor
	public NumberListBuilder(Path path) throws Exception {
		this.path=path;
		NumberListBuilder.numeroArray = new ArrayList<>();	
		NumberListBuilder.result = new ArrayList<>();
		buildList(path);

	}
	/**
	 * Parse the JSON file specified by the path parameter. The file is expected to have a key 
	 * "list" that maps to an array of items. Each item will be a string representation of an 
	 * integer. Convert each value in the array to an Integer and store in an ArrayList.
	 * Return the ArrayList.
	 * 
	 * Propagate any IOExceptions, for example if the file specified by path is not found.
	 * If the file is not valid JSON, return an empty ArrayList, that is a list of size 0.
	 * If any items in the array cannot be parsed as an integer, ignore those items.
	 * If the file does not contain the key "list" throw a custom exception of 
	 * type InvalidJsonSchemaException. 
	 * @throws Exception 
	 * 
	 */

	//NOTE: You may change the method header.
	public static ArrayList<Integer> buildList(Path path) throws Exception  {
		ArrayList<Integer> result = new ArrayList<Integer>();
		
		try{

			BufferedReader reader = Files.newBufferedReader(path, Charset.forName("UTF-8"));
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
			//InvalidJsonSchema Conditional
			if(jsonObject.containsKey("list")){
			
			JSONArray numeroArray= (JSONArray) jsonObject.get("list");
			
			// take the elements of the json array
			for(int i=0; i<numeroArray.size(); i++){
				//Cast elements to char value
				int j = (int)numeroArray.get(i).toString().charAt(0);
				//Check ASIIC values 0-9
				if ( j > 47 && j < 58 ) {
					result.add(Integer.parseInt((String) numeroArray.get(i)));					
				}
		
			}
			//Traverse Result Array 
			for (int i = 0; i < result.size(); i++) {
				System.out.println("The " + i + " element of the array: "+result.get(i));				
			}
			Iterator i = numeroArray.iterator();

		}
			else {
				//Exception InvalidJsonSchemaException = null;
				throw new InvalidJsonSchemaException(null);
			}
		
		}
		
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		catch (NumberFormatException e) {
			e.printStackTrace();
		} 
		
		catch (ParseException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		System.out.println("Result: " + result);
		return result;
		}
//}

	//Tests
public static void main(String[] args) throws Exception {
	
	Path thePath = Paths.get("input/list.json");
	NumberListBuilder numberTest1 = new NumberListBuilder(thePath);	
}
}






