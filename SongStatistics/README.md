SongDataParser
==============

For this homework you implement a program to recursively traverse a directory and read files in JSON format. 

To prepare for this homework, make sure to *carefully* follow the following steps:

1. Clone your homework repository as outlined in the [homework guidelines](https://github.com/CS212-S16/lectures/blob/master/notes/homeworkguidelines.md). For this homework, complete code to pass all test cases in the `SongStatistics` project. 

2. Download the [last.fm "SUBSET" data set](http://labrosa.ee.columbia.edu/millionsong/lastfm) here: [http://labrosa.ee.columbia.edu/millionsong/sites/default/files/lastfm/lastfm_subset.zip](http://labrosa.ee.columbia.edu/millionsong/sites/default/files/lastfm/lastfm_subset.zip)

3. Extract the data into a directory in the top-level SongStatistics project directory. For example, if you have downloaded the SongStatistics project into `/Users/srollins/cs212/homework/SongStatistics` then you should have a directory `/Users/srollins/cs212/homework/SongStatistics/lastfm_subset` that contains all of the data downloaded from the site.

:warning: for the test cases to work correctly it is very important that you download the correct data set and make sure to save it in the correct location.

## Due Friday 1/29 - 11:59pm

For this assignment, you will (begin) work with the last.fm data set found on [Million Song Dataset](http://labrosa.ee.columbia.edu/millionsong/lastfm).

The dataset consists of a set of directories. Each directory may contain one or more directories, or one or more files in JSON format. 

Each JSON file contains data about a single song. The data contains the artist, track ID, title, timestamp, list of tags (for example, "rock"), and list of IDs of tracks that are considered *similar*. 

For this assignment, you will traverse a directory containing files of this format and calculate two basic statistics: (1) the total number of songs found and (2) the average number of similar songs.

As an example, suppose your directory contained the two JSON files below. The first has zero similar songs and the second has three similar songs. In this case, the total number of songs found would be two and the average number of similar songs would be (0+3)/2 = 1.5.

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

```json
{  
   "artist":"BigBang",
   "track_id":"TRHWNNY128F4232443",
   "similars":[  
      [  
         "TRIXSRN128F92E83ED",
         0.897092
      ],
      [  
         "TRYSGPG128F92E83EB",
         0.897092
      ],
      [  
         "TRHDIEP128F42641EF",
         0.593165
      ]
   ],
   "title":"Street Parade (Acoustic Live)",
   "timestamp":"2011-09-08 02:37:28.513842",
   "tags":[  
      [  
         "live music",
         "100"
      ],
      [  
         "Norwegian Music",
         "100"
      ]
   ]
}
```

To receive full credit, you must complete the methods specified in `SongDataParser` and pass all test cases in `TestSongDataParser`.

The methods required are as follows:

- `SongDataParser` - The constructor takes as input a path specifying a directory and a boolean indicating whether or not to recursively traverse the directory. It will initialize any data members  and process the directory specified in the path parameter. If the isRecursive parameter is true it will process all descendant subdirectories of the path variable, else it will only process the directory specified by the path variable. To process a directory, it will find all files ending with `.json`, case insensitive, to determine the total number of songs (i.e., the total number of JSON files found) and the mean number of similar songs (i.e., the average length of the "similars" list.
- `getSongCount` - Returns an integer specifying the number of songs found.
- `getMeanNumberSimilarSongs` - Returns a double specifying the average length of the "similars" list across all songs.

### Hints

- You will need to declare several data members, for example to store the path, the recursive flag, and the variables used to store and calculate the statistics.
- My solution uses four additional private methods. Your solution may use more or fewer, however your final grade will depend on how well you decompose the problem into methods.
- If you do not follow all [style guidelines](https://github.com/CS212-S16/lectures/blob/master/notes/style.md) you will receive a deduction.