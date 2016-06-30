SongDataStructure
=================

For this homework you implement an *efficient* data structure to store information about many songs in a music library. 

## Due Friday 2/12 - 11:59pm

For this homework you will implement two classes:

**`Song`** - The `Song` class represents a single song. It should have data members to represent all of the relevant information contained in the JSONObjects given in the last.fm data set. The class will have two constructors. The first will take as input individual variables already extracted from the JSONObject. The second will take as input a JSONObject of the format below and will extract information from the object to be stored in the data members. The class will provide getters for all relevant data.

```json
{  
   "artist":"The Stills",
   "timestamp":"2011-09-07 18:04:51.570742",
   "similars":[  

   ],
   "tags":[  
      [  
         "utterly listenable",
         "100"
      ]
   ],
   "track_id":"TRAWKBW12903CC48FE",
   "title":"Still In Love Song (Extended 12\" Remix)"
}
```

**`MusicLibrary`** - The `MusicLibrary` class will store the `Song` objects for all songs in the collection. It will provide methods to add a song to the library, and retrieve songs in *two* ways. `getSongById` will take as input a *unique* track ID and return the `Song` object that represents the track, or null if the song does not exist. `getSongsByArtist` will take an artist name and will return a *sorted* set of all songs by the given artist. The set will be sorted first by artist. If the artists are the same, sort the songs by title. If both artist and title are the same, sort by track ID, which you may assume is unique.

### Hints

It is strongly recommended that you maintain multiple data structures in your `MusicLibrary`. Each will refer to the underlying `Song` objects in different ways, and ensure that you may efficiently access the data in multiple ways.
