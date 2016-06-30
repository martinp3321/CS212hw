Last FM Client
==============
For this homework, you will implement a web client that will retrieve artist information from the last.fm API and store the information into a relational database.

To prepare for this assignment, make sure to do the following:

1. Sign up for a [last.fm](http://www.last.fm/) account.
2. Sign up for a [last.fm API](http://www.last.fm/api) account.
3. [Get an API Key](http://www.last.fm/api/authentication).

:warning: **Make note of your API key**. You will need to include it as a URL parameter in all requests you send to the last.fm API, and you cannot retrieve it later.

## Due Friday 4/29 - 11:59pm

You will implement two methods for this assignment. Of course, you may implement additional helper methods and classes as appropriate. My solution used two additional classes and several additional methods.

**`DBHelper.createArtistTable`** - This method takes as input a `DBConfig` object and will create a table `artist` in the specified database. The `artist` table will have four columns as described in the javadoc: `name`, `listeners`, `playcount`, and `bio`. Note that the test cases will remove any previous artist table before running the test case to verify this method.

**`LastFMClient.fetchAndStoreArtists`** - This method takes as input a list of artists, as strings, and a `DBConfig` object. As described in the javadoc, the functionality of this method is as follows:

1. Fetches the information about that artist from the last.fm artist API: [http://www.last.fm/api/show/artist.getInfo](http://www.last.fm/api/show/artist.getInfo)
2. Extracts the artist name, number of listeners, playcount, and bio.
3. Stores all four pieces of information in a relational database table. The table must be called artist and must have columns name, listeners, playcount, and bio.
4. The information stored in dbconfig must be used to connect to the database.
This method assumes the table exists, but should catch any exceptions that occur if the table does not exist.

###Requirements

1. Modify the `dbconfig.json` file to use your [database assignment](https://github.com/CS212-S16/lectures/blob/master/notes/dbassignments.md). When running your program, we may not use your database so make sure your solution does not assume anything about your own database.
2. If the last.fm API does not return information for a given artist, do not store anything in the database.
3. Make *sure* to close your database connection after each request, query or update. Though an optimization would reuse the same connection, to avoid having too many open connections to the database (which will prevent all students from accessing the database) it is safer to close your connections each time.
