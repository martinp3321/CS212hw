Tweet Parser
============

For this homework you implement a program that uses regular expressions to extract hash tags and URL domains from a twitter data set.

To prepare for this homework, make sure to *carefully* follow the following steps:

1. Download the trainingandtestdata zip file from here: [http://help.sentiment140.com/for-students/](http://help.sentiment140.com/for-students/) 

2. Unzip and move training.1600000.processed.noemoticon.csv into the `TweetProcessor` project.

3. Rename training.1600000.processed.noemoticon.csv to `tweetdata.csv`.

:warning: for the test cases to work correctly it is very important that you download the correct data set and make sure to save it in the correct location.

## Due Friday 4/1 - 11:59pm

The `TweetParser` will process a CSV file and build two `HashMap` data structures.

### Processing the CSV ###

1. The `path` parameter of the constructor specifies the file name of the tweet data set.
	
2. When reading the file, use the ISO-8859-1 character set.

3. The first five fields of each line may be ignored.

4. Anything following the fifth comma is considered part of the tweet.

5. Remove any quotation marks at the beginning or ending of the tweet.

6. Tags and domains **must** be extracted from the tweet using **regular expressions**. 

### Identifying Tags ###

1. A tag begins with a # and ends with a whitespace character.

2. A tag must contain at least one non-whitespace character.

3. A tag may contain any other character (including punctuation).

4. A tag must be preceded by whitespace, or be at the beginning of a tweet.

5. Examples: 
 - This tweet has two hash tags:
`Why can't I install WHS in a Virtual Machine? #vmware #fail`
 
 - This tweet does not contain any hash tags because there is a space between # and the next character:
`Wow! I'm on tweet # 100 - wish I'd thought of something more profound to say now ...`

 - This tweet contains only one hash tag because the second # is preceded by punctuation:
`@zacparker Sorry to miss you! I've got some time now though if you're on. If not #weekendtechtalk (#wttalk for short) continues tomorrow`

 - This tweet has four hash tags, again because one of the # is preceded by punctuation:
`#iphone is working with #vista now. and yes...#apple sux. it seems to be that my #powerbook is outdated. not enough #usb power`

 ### Identifying Domains ###
 
1. A domain begins with either http:// or https://

2. The domain is any series of characters between the second / and either the next / or a whitespace.

3. Examples:
 - This tweet has a domain tinyurl.com:
`Working on my new photography site. Experimenting with template themes. I haven't done web designing in ages!  http://tinyurl.com/r9qf4r`

 - This tweet has a domain www.getdropbox.com:
`Note: https://www.getdropbox.com/referrals/NTI2NTA2ODk link gives you 2G + extra 250MB bonus space`

 - This tweet has no domains:
`@paul https didn't work`

 - This tweet also has no domains:
`Oh and they also know I use my Gmail using https:// too  I'm such a rebel!!`

