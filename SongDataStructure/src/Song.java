import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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
/**
 * A class to maintain data about a single song.

 * Java object representation of a JSON object with schema below.
 * @author srollins
 *
 */

//martin murphy
//

/*
 * 
 * 
{  
   "artist":"The Primitives",
   "timestamp":"2011-09-07 12:34:34.851502",
   "similars":[  
      [  
         "TROBUDC128F92F7F0B",
         1
      ],
      [  
         "TRWSCCK128F92F7EDB",
         0.98714400000000002
      ]
   ],
   "tags":[  
      [  
         "1980s",
         "100"
      ],
      [  
         "80s",
         "33"
      ],
      [  
         "pop",
         "33"
      ],
      [  
         "alternative",
         "33"
      ]
   ],
   "track_id":"TRBDCAB128F92F7EE4",
   "title":"Never Tell"
} 
 
*/

public class Song {

	/**
	 * Declare appropriate instance variables.
	 */
	
	private String artist;
	public String trackId;
	private String title;
	private ArrayList<String> similars;
	private ArrayList<String> tags;
	private Object i;
	
	
	
	
	
	/**
	 * Constructor.
	 * @param artist
	 * @param trackId
	 * @param title
	 * @param similars
	 * @param tags
	 */
	public Song(String artist, String trackId, String title, ArrayList<String> similars, ArrayList<String> tags) {
		//TODO: Complete constructor.

		this.artist=artist;
		this.trackId=trackId;
		this.title=title;
		this.similars = similars;
		this.tags = tags;
				
	}

	/**
	 * Constructor that takes as input a JSONObject as illustrated in the example above and
	 * constructs a Song object by extract the relevant data.
	 * @param object
	 */
	public Song(JSONObject object) {
		//TODO: Complete constructor.		
		
		this.setArtist((String)object.get("artist"));
		this.setTitle((String)object.get("title"));
		this.setTrackId((String)object.get("track_id"));
		
		this.setSimilars((JSONArray)object.get("similars"));
		this.setTags((JSONArray)object.get("tags"));
	}

	/**
	 * Return artist.
	 * @return
	 */
	public String getArtist() {
		//TODO: complete method
		return artist;
	}

	/**
	 * Return track ID.
	 * @return
	 */
	public String getTrackId() {
		//TODO: complete method
		return trackId;
	}

	/**
	 * Return title.
	 * @return
	 */
	public String getTitle() {
		//TODO: complete method
		return title;
	}

	/**
	 * Return a list of the track IDs of all similar tracks.
	 * @return
	 */
	public ArrayList<String> getSimilars() {
		//TODO: complete method
		return similars;
	}

	/**
	 * Return a list of all tags for this track.
	 * @return
	 */
	public ArrayList<String> getTags() {
		//TODO: complete method
		return tags;
	}	
	
	
	/**
	 * Set artist.
	 * @param string 
	 * @return
	 */
	public void setArtist(String artist) {
		//TODO: complete method
		this.artist = artist;
		
	}

	/**
	 * Set track ID.
	 *
	 */
	public void setTrackId(String trackId) {
		//TODO: complete method
		this.trackId = trackId;
		
	}

	/**
	 * Set title.
	 * @return
	 */
	public void setTitle(String title) {
		this.title = title;
		
	}

	/**
	 * Set a list of the track IDs of all similar tracks.
	 */
	public void setSimilars(ArrayList<String> similars) {
		//TODO: complete method
		//Iterator i = similars.iterator();
		this.similars = similars;
		
	}
		

	/**
	 * Seta list of all tags for this track.
	 */
	public void setTags(ArrayList<String> tags) {
		
		this.tags = tags;
	}

		
	}
	
	
	

	

