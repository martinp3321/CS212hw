import java.util.ArrayList;
import java.util.TreeMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * A class to store grades for students. 
 * @author srollins
 *
 */
public class GradeBook {

	//map student name to list of grades for the student
	private TreeMap<String, ArrayList<Double>> grades;
	
	/**
	 * Construct and empty GradeBook
	 */
	public GradeBook() {
		this.grades = new TreeMap<>();
	}

	/**
	 * Add a single grade for a student.
	 * Class uses sychronized methods to ensure thread safety.
	 * @param student
	 * @param grade
	 */
	public synchronized void addGrade(String student, double grade) {
		
		if(!this.grades.containsKey(student)) {
			this.grades.put(student, new ArrayList<Double>());
		}
		this.grades.get(student).add(grade);		
	}
	
	/**
	 * Return a JSONObject containing grades for a given student.
	 * Relevant data will be copied into the result object.
	 * The format of the result will be as follows:
	 * 
	 * {"name":"Bob","grades":[75.0,81.0,82.5]}
	 * 
	 * If a student with name "student" does not exist in the gradebook the 
	 * method returns null.
	 * 
	 * @param student
	 * @return
	 */
	public synchronized JSONObject getGrades(String student) {

		if(this.grades.containsKey(student)) {
			return getStudentJson(student);
		}
		return null;		
	}
	
	/**
	 * Return a JSONObject containing all grades for all
	 * students in the GradeBook.
	 * 
	 * The format of the result will be as follows:
	 * 
	 * {
	 * 		"students":[
	 * 			{"name":"Bob","grades":[75.0,81.0,82.5]},
	 * 			{"name":"Jenny","grades":[23.0,98.5,85.5]},
	 * 			{"name":"Sally","grades":[23.0,48.0,33.0]},
	 * 			{"name":"Veronica","grades":[99.0,98.5,99.5]}
	 * 		]
	 * }
	 * 
	 * If the GradeBook is empty, the method will return: {"students":[]}
	 * 
	 * @return
	 */
	public synchronized JSONObject getGrades() {
		JSONObject result = new JSONObject();
		JSONArray array = new JSONArray();

		for(String student: grades.keySet()) {
			array.add(getStudentJson(student));
		}
		result.put("students", array);
		return result;
	}

	/**
	 * Helper method to convert the grades for the given student
	 * into a JSONObject.
	 * @param student
	 * @return
	 */
	private JSONObject getStudentJson(String student) {
		JSONObject result = new JSONObject();
		result.put("name", student);
		ArrayList<Double> grades = this.grades.get(student);
		JSONArray array = new JSONArray();
		
		for(double grade: grades) {
			array.add(grade);
		}			
		result.put("grades", array);
		return result;
	}
	
	
	
}
