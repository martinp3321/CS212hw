NumberListBuilder
=================

For this homework, you will implement a program that uses a custom exception, catches exceptions, and propagates exceptions.

## Due Friday 2/5 - 11:59pm

Your assignment is to complete the `NumberListBuilder` class such that it passes all test cases specified in `TestNumberListBuilder`.

You will complete the `buildList` method as follows:

- The method takes as input a `path` that specifies a single JSON file.
- The method will open the file specified by the `path` parameter and parse it as a JSON file. 
- The expected format of the file is as follows:

```json
{  
   "list": [
      "1",
      "3",
      "15",
      "6",
      "1",
      "a",
      "9",
      "b",
      "3"
   ]
}
```
- The program will extract the array associted with the key `list` and will parse each item in the list as an integer. *Hint: use `Integer.parseInt` and make sure to handle the `NumberFormatException`.* Each item found will be added to an `ArrayList` that will be returned to the method caller. In the example above, the resulting `ArrayList` would contain values `[1, 3, 15, 6, 1, 9, 3]`. 
- Any **IOException**s should be propagated, for example if the file specified by the `path` parameter cannot be opened the exception will be propagated to the method caller.
- If the file does not contain valid JSON (i.e., a `ParseException` is generated) the method will return an empty `ArrayList`, that is an `ArrayList` of size 0.
- If any items in the array are not integers (i.e., parsing them generates a `NumberFormatException`), ignore those items. Note that "a" and "b" from the example above are ignored.
- If the file can be opened and contains valid JSON but does **not** contain the key `list`, throw a custom exception of the type `InvalidJsonSchemaException`.
 
 ## Hints
 
- You *may* change the header of the `buildList` method.
- The test cases will not compile appropriately until you have correctly implemented the `InvalidJsonSchemaException` class.
