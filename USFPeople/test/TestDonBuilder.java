import static org.junit.Assert.*;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestDonBuilder {

	private static final String undergrad = "{\"name\":\"Jenny\", \"id\":20398776, \"gpa\":3.4, \"year\":\"Junior\" }";
	private static final String grad = "{\"name\":\"Jenny\", \"id\":20398776, \"gpa\":3.4, \"undergrad_uni\":\"Mills College\" }";
	private static final String faculty = "{\"name\":\"Jenny\", \"id\":20398776, \"gpa\":3.4, \"salary\": 108564 }";

	private static  final String invalidNameType = "{\"name\": 1234, \"id\":20398776, \"gpa\":3.4, \"year\":\"Junior\" }";
	private static  final String invalidIdType = "{\"name\":\"Jenny\", \"id\":\"a12b7\", \"gpa\":3.4, \"year\":\"Junior\" }";
	private static final String missingId = "{\"name\":\"Jenny\", \"gpa\":3.4, \"year\":\"Junior\" }";
	private static final String invalidSalary = "{\"name\":\"Jenny\", \"id\":20398776, \"gpa\":3.4, \"salary\": \"$100,987\" }";


	private static JSONObject ugradObj, gradObj, facObj, invalidNameObj, invalidIdObj, missingIdObj, invalidSalaryObj;

	@BeforeClass
	public static void setUp() {

		JSONParser parser = new JSONParser();
				
		try {
			ugradObj = (JSONObject) parser.parse(undergrad);
			gradObj = (JSONObject) parser.parse(grad);
			facObj = (JSONObject) parser.parse(faculty);
			invalidNameObj = (JSONObject) parser.parse(invalidNameType);
			invalidIdObj = (JSONObject) parser.parse(invalidIdType);
			missingIdObj = (JSONObject) parser.parse(missingId);
			invalidSalaryObj = (JSONObject) parser.parse(invalidSalary);			
		} catch(ParseException pe) {
			System.err.println("Unable to execute tests. " + pe.getMessage());
		}

	}

	@Test
	public void testUndergraduate() {
		Don don = DonBuilder.buildDon(ugradObj);
		assertTrue("testUndergraduate: Result not instance of Undergraduate.", don instanceof Undergraduate);		
	}

	@Test
	public void testGraduate() {
		Don don = DonBuilder.buildDon(gradObj);
		assertTrue("testGraduate: Result not instance of Graduate.", don instanceof Graduate);		
	}

	@Test
	public void testFaculty() {
		Don don = DonBuilder.buildDon(facObj);
		assertTrue("testFaculty: Result not instance of Faculty.", don instanceof Faculty);		
	}

	@Test
	public void testUndergradInheritsStudent() {
		Don don = DonBuilder.buildDon(ugradObj);
		assertTrue("testUndergradInheritsStudent: Result not instance of Student.", don instanceof Student);		
	}

	@Test
	public void testGradInheritsStudent() {
		Don don = DonBuilder.buildDon(gradObj);
		assertTrue("testGradInheritsStudent: Result not instance of Student.", don instanceof Student);		
	}

	@Test
	public void testInvalidTypeForName() {
		Don don = DonBuilder.buildDon(invalidNameObj);
		assertEquals("testInvalidTypeForName: Return null if name not of type String.", null, don);		
	}

	@Test
	public void testInvalidTypeForId() {
		Don don = DonBuilder.buildDon(invalidIdObj);
		assertEquals("testInvalidTypeForId: Return null if id not of type Long.", null, don);		
	}

	@Test
	public void testMissingId() {
		Don don = DonBuilder.buildDon(missingIdObj);
		assertEquals("testMissingId: Return null if JSON does not contain key id.", null, don);		
	}

	@Test
	public void testInvalidTypeForSalary() {
		Don don = DonBuilder.buildDon(invalidSalaryObj);
		assertEquals("testInvalidTypeForSalary: Return null if salary not a Long.", null, don);		
	}

}

