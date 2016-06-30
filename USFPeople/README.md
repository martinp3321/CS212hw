USFPeople
=========

For this homework you will practice using inheritance to implement objects to store data about USF people. 

:warning: The test cases for this homework will not compile until you have implemented the classes specified below.

## Due Friday 2/26 - 11:59pm

You will implement objects to represent the following types of people associated with USF: `Undergraduate`, `Graduate`, and `Faculty`.

### Undergraduate

The following JSON document represents an undergraduate student. An undergraduate must have all four elements show in the document below.

```json
{  
   "name":"Jenny",
   "id":20398776,
   "gpa":3.4,
   "year":"Junior"
}	
```
	
### Graduate

The following JSON document represents an graduate student. A graduate must have all four elements show in the document below.

```json
{  
   "name":"Jenny",
   "id":20398776,
   "gpa":3.4,
   "undergrad_uni":"Mills College"
} 
```
 
### Faculty
The following JSON document represents a faculty member. A faculty member must have all four elements show in the document below.

```json
{  
   "name":"Jenny",
   "id":20398776,
   "salary":108765
}
```

### Base Classes
All types of people are `Don`s, the base class provided for you. In addition, `Undergraduate` and `Graduate` are also `Student`. 

Make sure that `Don` stores any data common to all people, and that `Student` stores data common to both `Graduate` and `Undergraduate`.

### DonBuilder

The `buildDon` method of `DonBuilder` will take as input a JSONObject formatted as described above and will instantiate and return the appropriate subclass. 

The type of object instantiated will be dependent upon the elements contained in the JSONObject. Make sure to do appropriate error checking as described in the method javadoc.

### Other requirements
Your grade will depend upon whether you pass the test cases as well as on how well you use Java inheritance mechanisms. 

1. Make sure to use abstract classes and/or methods where appropriate.
2. Make sure to use the private/public/protected keywords appropriately.