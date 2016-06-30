import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestLastFMClient {
	
	static DBConfig dbconfig;
	static ArrayList<String> tables = new ArrayList<>(Arrays.asList("artist"));
	
	@BeforeClass
	public static void setUpBeforeClass() {
		try {
			BufferedReader reader = Files.newBufferedReader(Paths.get("dbconfig.json"));
			JSONParser parser = new JSONParser();
			JSONObject object = (JSONObject) parser.parse(reader);
			
			String username = (String) object.get("username");
			String password = (String) object.get("password");
			String db = (String) object.get("db");
			String host = (String) object.get("host");
			String port = (String) object.get("port");

			dbconfig = new DBConfig(username, password, db, host, port);
			DBHelper.clearTables(dbconfig, tables);						
			
		} catch(Exception e) {
			fail("TestLastFMClient.setUpBeforeClass:: Test case setup failed: " + e.getMessage());
		}
	}

	@Test
	public void testACreateArtistTable() {
		try {
			DBHelper.createArtistTable(dbconfig);
		} catch (SQLException e) {
			fail("TestLastFMClient.testACreateArtistTable:: Unable to prepare artist table: " + e.getMessage());
		}
	}
	
	@Test
	public void testBVerifyArtistTable() {
		try {
			boolean result = verifyArtistTable(dbconfig);
			if(!result) {
				fail("TestLastFMClient.testBVerifyArtistTable:: Incorrect columns in artist table.");
			}
		} catch (SQLException e) {
			fail("TestLastFMClient.testBVerifyArtistTable:: Incorrect columns in artist table: " + e.getMessage());
		}
	}

	@Test
	public void testCVerifyCorrectArtists() {
		try {
			ArrayList<String> artists = new ArrayList<>(Arrays.asList("Cher", "Air Supply", "Heart"));		
			LastFMClient.fetchAndStoreArtists(artists, dbconfig);
			boolean result = verifyCorrectArtists(dbconfig, artists);
			if(!result) {
				fail("TestLastFMClient.testCVerifyCorrectArtists:: Incorrect artists stored in table.");
			}
		} catch (SQLException e) {
			fail("TestLastFMClient.testCVerifyCorrectArtists:: Incorrect artists stored in table: " + e.getMessage());
		}
		
	}
	
	private static boolean verifyArtistTable(DBConfig dbconfig) throws SQLException {

		Connection con = null;
		try {
			con = DBHelper.getConnection(dbconfig);
			int expectedColCount = 4;		
			ArrayList<String> expectedCols = new ArrayList<>(Arrays.asList("name", "listeners", "playcount", "bio"));

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM artist");
			ResultSetMetaData rsmd = rs.getMetaData();

			if(rsmd.getColumnCount() != expectedColCount) {
				return false;
			}

			for(int i = 1; i <= expectedColCount; i++) {
				String colName = rsmd.getColumnName(i);
				if(!expectedCols.contains(colName)) {
					return false;
				}
			}

			return true;
		} finally {
			con.close();
		}
	}

	private static boolean verifyCorrectArtists(DBConfig dbconfig, ArrayList<String> expectedArtists) throws SQLException {

		Connection con = null;
		try {
			con = DBHelper.getConnection(dbconfig);
			Statement stmt = con.createStatement();
			
			int count = 0;
			ResultSet artists = stmt.executeQuery("SELECT name FROM artist");
			while(artists.next()) {
				String artist = artists.getString("name");
				System.out.println(artist);
				if(!expectedArtists.contains(artist)) {
					return false;
				}
				count++;
			}
			if(count != expectedArtists.size()) {
				return false;
			}
			
			return true;
		} finally {
			con.close();
		}		
	}

	
}
