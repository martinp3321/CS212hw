import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

/**
 * Search for grades of a single student.
 * @author srollins
 *
 */
public class GradeSearchServlet extends BaseServlet {

	/**
	 * GET /search returns a web page containing a search box where a student's name may be entered.
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String responseHtml = "<html" + 
							"<head><title>Grade Search</title><link href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css' rel='stylesheet' type='text/css'><link href='http://fonts.googleapis.com/css?family=Josefin+Sans' rel='stylesheet' type='text/css'></head>" +
							"<body>" +
							"<style> body{background-color: #3498db} </style>"+
							"<form action=\"search\" method=\"post\">" +
							"Enter name to search for a student's grades:<br/>"+
							"<input type=\"text\" name=\"student\"><br/>" +
							"<input type=\"submit\" value=\"Search\"><br/>" +
							"</form>" +
							"<form action=\"all\" method=\"get\">" +
							"<input type=\"submit\" value=\"Show all students\"><br/>" +
							"</form>" +
							
							"</body>" +
							"</html>";
		
		

		
		PrintWriter writer = prepareResponse(response);
		writer.println(responseHtml);
	}
	
	/**
	 * POST /search assumes a parameter of student=<student_name>.
	 * Returns a web page containing the grades of the student.
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//get the parameter fromt he search box
		String student = request.getParameter("student");
		//retrieve the GradeBook from the context
		GradeBook book = (GradeBook) request.getServletContext().getAttribute("gradebook");
		
		//get the JSON representation of the student's grades.
		JSONObject result = book.getGrades(student);

		//craft the HTML response
		String responseHtmlHead = "<html" + 
				"<head><title>Grade Search</title><link href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css' rel='stylesheet' type='text/css'><link href='http://fonts.googleapis.com/css?family=Josefin+Sans' rel='stylesheet' type='text/css'></head>" +
				"<body>";
		
		//body of response will vary based on whether the student was found in the GradeBook
		String responseHtmlContent;
		if(result != null) {
			responseHtmlContent = "<table border=\"2px\" width=\"100%\">" +	
					"<style> body{background-color: #3498db} </style>" +
					"<tr><td><strong>Student</strong></td><td><strong>Grades</strong></td></tr>" +
					"<tr><td>" + student + "</td><td>" + result.get("grades") + "</td></tr>" +
					"</table>";				

		} else {
			responseHtmlContent = "Student: " + student + " not found!";
		}
		
		String responseHtmlFoot = "<form action=\"search\" method=\"get\"><input type=\"submit\" value=\"Search Again\"></form>" + 
								  "<form action=\"all\" method=\"get\"><input type=\"submit\" value=\"Show all students\"></form>" +"</body>" +
									"</html>"; 
		
		String responseHtml = responseHtmlHead + responseHtmlContent + responseHtmlFoot;		
				
		PrintWriter writer = prepareResponse(response);
		writer.println(responseHtml);
	}
	
}
