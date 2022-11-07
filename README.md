# Grouping Anagrams

## How to run the code
```shell
git clone git@github.com:Bovojon/grouping-anagrams.git
cd grouping-anagrams
chmod u+x ./run.sh
./run.sh Data/example1.txt
```

### To run the tests
```shell
./gradlew test
```

## Description of solution
* Given that the words in the input file are ordered by size, at any point we can store in memory, words of the same size. So after we process all the words with the same size, we clear our data structure. 
* We use `try-with-resources` to open the file since it is the most effective way to prevent memory leaks when working with resources that must be closed.
* To read from the file, we use the `BufferedReader`'s `readline()` which has a large buffer size and is one of the most efficient ways to read a file.
* We read the first line from the file, and if it's empty, just print out a message and return.
* Else, we enter a loop and start by lowercasing every letter in the word - O(word).
* Then we check if it's the same size as the previous word.
* If it is not the same size, we output the current anagrams in the hashMap, clear the hashMap, and then set the new size.
* To output, we go through each anagram group in the hashmap, remove the opening and closing brackets, and the print it - let's say the size of hashmap is H and size of a group is G, then this could be O(H*G).
* Then we add the word to its respective group in the hashMap:
    * We use each letter's ASCII code and a tracking array of 26 slots (one for each alphabet) to determine how many letters there are in the word.
    * If we see anything other than a letter, we throw an exception.
    * Using the string value of the tracking array, we then check if there is an anagram key in our map already. If there is not, we create a new list.
    * We add the word to the list.
* We read the next line of the file.

## Big O analysis
* Since we go through each word in the file, the most signifiant cost is the size of the file, which is O(N).
* However, we also go through each letter in a word and if the size of a word is W, then this is O(W), which could be O(N*W).
* There is also the cost of converting the list of anagrams into their string versions, and if the size of the list is L, then this can be O(L) in the worst case.

## Reasons behind data structures chosen
* `HashMap` - to store lists of anagrams with their base array signature as the key. Lookup time is O(1).
* `char` `array` - to count the number of letters we see in a word and compare to other words. Looking up by index is O(1).
* `ArrayList` - to group all the anagrams. Adding an element to the list has amortized time of O(1). 

## With more time...
If I had more time, I would consider the following:
* Use Kafka to process the anagrams in real-time as the file is being read.
* Add check style (`jacoco`) to make sure code is clean.
* Add better logging with Lombok instead of `System.out`.
* Add a better way of handling paths to test files and keep `Data` in `resources`.
* Use Mockito to improve tests so we mock the File IO operations.

## Assumptions
The following assumptions were made:
* Each word in the given file is in lowercase Latin letters, so no numbers or punctuation, for example.
* A word can get repeated as in `example1.txt`.
