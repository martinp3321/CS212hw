import java.util.ArrayList;

public class LastFMClient {

	/**
	 * For each artist in the list artists, does the following: 
	 * 1. Fetches the information about that artist from the last.fm artist 
	 * API: http://www.last.fm/api/show/artist.getInfo
	 * 2. Extracts the artist name, number of listeners, playcount, and bio.
	 * 3. Stores all four pieces of information in a relational database table.
	 * The table must be called artist and must have columns name, listeners, playcount, and bio.
	 * The information stored in dbconfig must be used to connect to the database.
	 * This method assumes the table exists, but should catch any exceptions that occur if
	 * the table does not exist.
	 * 
	 * @param artists
	 * @param dbconfig
	 */
	public static void fetchAndStoreArtists(ArrayList<String> artists, DBConfig dbconfig) {
		

	}

}
