import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Search for grades of a single student.
 * @author srollins 
 * @author Martin murphy
 *
 */
public class GradeAllServlet extends BaseServlet {
	GradeBook book = GradeBookBuilder.buildBook(Paths.get("grades.json"));

	/**
	 * GET /all returns a web page containing all students and grades
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String responseHtml = "<html>\n" + 
							"<head>\n<title>All Student Grades</title>\n<link href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css' rel='stylesheet' type='text/css'><link href='http://fonts.googleapis.com/css?family=Josefin+Sans' rel='stylesheet' type='text/css'></head>\n" +
							"<body>\n" +
							"<style> body{background-color: #3498db} </style>"+ 
							
							"<table border=\"2px\" width=\"100%\">\n" +
							"<tr>\n<td>\n<strong>Student</strong></td><td><strong>Grades</strong></td></tr>\n";
		
		
		
		
		
		
		
		
		JSONObject allGrades = book.getGrades();
//		System.out.println(allGrades.get("students"));
		for (Object student : (JSONArray)(allGrades.get("students"))) {
//			String singleGrade = "";
			JSONObject singleStudentMap = book.getGrades(((Map<String, String>)student).get("name"));
			responseHtml += "<tr><td>" + singleStudentMap.get("name") + "</td><td>" + singleStudentMap.get("grades") + "</td></tr>\n";
		}
		responseHtml += "</table><form action=\"search\" method=\"get\"><button class=\"btn btn-primary\"<input type=\"submit\">Search</button></form>" + 
								  "<form action=\"all\" method=\"get\"><button class=\"btn btn-primary\" type=\"submit\">All Grades</button></body>" +
									"</html>";
		
	
		
		
		PrintWriter writer = prepareResponse(response);
		writer.println(responseHtml);
	}
	
	
}
