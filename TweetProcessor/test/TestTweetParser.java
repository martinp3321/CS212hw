import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;

public class TestTweetParser {

	@Test
	public void testTweets() {
		Path path = Paths.get("tweetdata.csv");
		TweetParser parser = new TweetParser(path);
		JSONParser jsonParser = new JSONParser();
		JSONObject obj = null;
		try {
			obj = (JSONObject) jsonParser.parse(Files.newBufferedReader(Paths.get("output/tagCounts.json")));
		} catch (IOException | ParseException e) {
			fail("testTweets::Exception: " + e.getMessage());
		}	
		assertEquals(JSONValue.toJSONString(parser.getTagCount()), obj.toJSONString());
	}
	
	@Test
	public void testDomains() {
		Path path = Paths.get("tweetdata.csv");
		TweetParser parser = new TweetParser(path);
		JSONParser jsonParser = new JSONParser();
		JSONObject obj = null;
		try {
			obj = (JSONObject) jsonParser.parse(Files.newBufferedReader(Paths.get("output/domainCounts.json")));
		} catch (IOException | ParseException e) {
			fail("testTweets::Exception: " + e.getMessage());
		}		
		assertEquals(JSONValue.toJSONString(parser.getDomainCount()), obj.toJSONString());
	}

}
