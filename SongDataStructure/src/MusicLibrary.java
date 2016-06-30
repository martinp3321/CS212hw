import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Maintains a music library of several songs.
 * @author srollins
 *
 */
public class MusicLibrary {

	/**
	 * Declare appropriate instance variables.
	 * It should be easy to retrieve a song given its unique track ID.
	 * It should also be easy to retrieve a sorted set of songs by a given
	 * artist.
	 */

	private HashMap<String, Song> songMap;
	private HashMap<String, TreeSet<Song>> artistMap;
	/**
	 * Constructor
	 */
	public MusicLibrary() {
		//TODO: complete constructor.
		this.songMap = new HashMap<>();
		this.artistMap = new HashMap<>();
	}
	
	/**
	 * Add a song to the library.
	 * Make sure to add a reference to the song object to all 
	 * appropriate data structures.
	 * @param song
	 */

	public void addSong(Song song) {
		
		
		this.songMap.put(song.getTrackId(), song);
		
		String artist = song.getArtist();
		//if I have never seen a user with this name before
		//  create a new arraylist to put into the map
		if(!this.artistMap.containsKey(artist)) {
			this.artistMap.put(artist, new TreeSet<>(new TheCompator()));
		}
		
		
		this.artistMap.get(artist).add(song);
		
		System.out.println("ID: " + song.getTrackId());
		System.out.println("Size: " + this.songMap.size());
		
		
	}
	
	/**
	 * Return the song associated with a unique track ID.
	 * @param trackId
	 * @return
	 */

	public Song getSongById(String trackId) {
		//TODO: complete method
		return this.songMap.get(trackId);
	}
	
	
	/**
	 * Return a sorted set of all songs by a given artist.
	 * @param artist
	 * @return
	 */
// try to implement this method in constant time
	public TreeSet<Song> getSongsByArtist(String artist) {

		if(!this.artistMap.containsKey(artist)) {
			//return new TreeSet<Song>();
			return null;
			
		}
		return this.artistMap.get(artist);
	}
}
		
		
		
		//return this.artistMap.get(artist);
	
	
		
	
	

	


			

	

