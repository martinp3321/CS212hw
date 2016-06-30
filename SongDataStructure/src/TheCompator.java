import java.util.Comparator;

class TheCompator implements Comparator<Song> {
    public int compare(Song paramT1, Song paramT2) {
    	
    	
        if (paramT1.getArtist().compareTo(paramT2.getArtist()) > 0)
            return -1;
        else if (paramT1.getArtist().compareTo(paramT2.getArtist()) < 0)
            return 1;
        else  if (paramT1.getTitle().compareTo(paramT2.getTitle()) > 0)
            return -1;
        else if (paramT1.getTitle().compareTo(paramT2.getTitle()) < 0)
            return 1;
        return 0;
		//return (paramT1.getTrackId().compareTo(paramT2.getTrackId()));
            
    }
}