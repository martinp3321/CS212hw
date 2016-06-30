import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Martin Murphy

/**
 * A class that processes a CSV file containing many 
 * tweets and builds two maps:
 * The first map maps hash tags to the number
 * of times the tag appears in the data set.
 * The second map maps domains to the number of
 * times they appear in the data set.
 * 
 * @author srollins
 *
 */
public class TweetParser {
	
	//Path path;
//	Path path = Paths.get("/data/tweet.csv");
	HashMap<String, Integer> tags = new HashMap<String, Integer>();
	HashMap<String, Integer> domains = new HashMap<String, Integer>();
	Path path;
	
	

	String regex = ".+?,.+?,.+?,.+?,.+?,";
	//String regex2 = 
		//	string = string.replaceAll("^\"|\"$", "");
	
	/**
	 * The path parameter specifies the file name of the tweet data set.
	 * The data set is a csv file.
	 * The first five fields may be ignored.
	 * Anything following the fifth comma is considered part of the tweet.
	 * Remove any quotation marks at the beginning or ending of the tweet.
	 * When reading the file, use the ISO-8859-1 character set.
	 * Tags and domains must be extracted from the tweet using
	 * regular expressions. 
	 * @param path
	 * 
	 */

	
	public TweetParser(Path path) throws IOException {	

		try (BufferedReader reader = Files.newBufferedReader(path, Charset.forName("ISO-8859-1"))){
			
			String line = reader.readLine();
			
			while (line != null) {
			//	System.out.println("ORIGINAL: " + line);
				line = line.replaceFirst(regex, "");
				line = line.replaceAll("^\"|\"$", "");
				//line = line.substring(1, line.length()-1);

				
				//TAG
				Pattern pT = Pattern.compile("(^|\\s)#([^\\s]+?)(?=\\s|$)");
				//DOMAIN
				Pattern pD = Pattern.compile("(^|//s|.+?)(https|http)://([^\\s]+?)(/|//s|$)");
				
				
				//(?m)(^|\\p{Space})#([^\\p{Space}]+)
				
				//(?m)(http|https)://([^/|\\p{Space}]+)
				
				
			

				Matcher mT = pT.matcher(line);
				Matcher mD = pD.matcher(line);
				while (mT.find()) {
					
					if(!tags.containsKey(mT.group(2))){
						System.out.println("Tag " +mT.group(2));					
						tags.put(mT.group(2), 1);
						
					}
					else{
						int value = tags.get(mT.group(2));
						value++;
						tags.put(mT.group(2), value);
						System.out.println("Tag " +mT.group(2) + value);
						
					}
					
					}
				
				
			while(mD.find()){	
				if(!domains.containsKey(mD.group(3))){
					
					System.out.println("Domain " +mD.group(3));
					domains.put(mD.group(3),1);
					
				}
				
				else{
					int value2 = domains.get(mD.group(3));
					value2++;
					domains.put(mD.group(3), value2);
					
				}
			}
				line = reader.readLine();
				//getTagCount();
			}				
		}	
		getTagCount();
	}	
	
	/**
	 * Return a HashMap where the key is a tag and the value is the 
	 * number of times the tag appears in the data set.
	 * A tag begins with a # and ends with a whitespace character.
	 * A tag must contain at least one non-whitespace character.
	 * A tag may contain any other character (including punctuation).
	 * A tag must be preceded by a space, or be at the beginning of a tweet.
	 * 
	 * 
	 * This tweet has two hash tags:
	 * Why can't I install WHS in a Virtual Machine? #vmware #fail
	 * 
	 * This tweet does not contain any hash tags because there is a space between # and 
	 * the next character:
	 * Wow! I'm on tweet # 100 - wish I'd thought of something more profound to say now ...
	 * 
	 * This tweet contains only one hash tag because the second # is preceded by punctuation:
	 * @zacparker Sorry to miss you! I've got some time now though if you're on. If not #weekendtechtalk (#wttalk for short) continues tomorrow 
	 * 
	 * This tweet has four hash tags, again because one of the # is preceded by punctuation:
	 * #iphone is working with #vista now. and yes...#apple sux. it seems to be that my #powerbook is outdated. not enough #usb power
	 */
	public void getTagCount() {
		
		
		System.out.println(tags.toString());
		
		
//		for (Entry<String, Integer> entry : tags.entrySet()) {
//			  String key = entry.getKey();
//			  Integer value = ((Entry<String, Integer>) tags).getValue();
//			  
//			  System.out.println("key: " +key + "value: " + value);
//			  
//			  // do stuff
//			}
		
		
					
		
	}
	
	/**
	 * Return a HashMap where the key is a domain and the value is the 
	 * number of times the domain appears in the data set.
	 * A domain begins with either http:// or https://
	 * The domain is any series of characters between the second / and 
	 * either the next / or a whitespace.
	 * 
	 * This tweet has a domain tinyurl.com:
	 * Working on my new photography site. Experimenting with template themes. I haven't done web designing in ages!  http://tinyurl.com/r9qf4r
	 * 
	 * This tweet has a domain www.getdropbox.com:
	 * Note: https://www.getdropbox.com/referrals/NTI2NTA2ODk link gives you 2G + extra 250MB bonus space
	 * 
	 * This tweet has no domains:
	 * @paul https didn't work
	 * 
	 * This tweet also has no domains:
	 * Oh and they also know I use my Gmail using https:// too  I'm such a rebel!!
	 * 
	 * @return
	 */
	public HashMap<String, Integer> getDomainCount() {
		return domains;
	}
	
	
	public static void main(String[] args) throws IOException {
		Path path = Paths.get("tweetdata.csv");
		TweetParser test1 = new TweetParser(path);
		
		
		
	
	
	
	}
	
	//You may use additional methods
}