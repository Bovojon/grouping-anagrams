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
* Given that the words in the input file are ordered by size, at any point we can store in the cache words of the same size list only. This will help us save memory.
* To read from the file, we use the `BufferedReader`'s `readline()` which has a large buffer size.
* We read the first line from the file, and if it's empty, just print out a message and return.
* Else, we enter a loop and start by lowercasing every letter in the word - O(word).
* Then we check if it's the same size as the previous word.
* If it is not the same size, we output the current anagrams in the hashMap, clear the hashMap, and then set the new size.
* To output, we go through each anagram group in the hashmap, remove the opening and closing brackets, and the print it - let's say the size of hashmap is H and size of a group is G, then this would be O(H*G).
* Then we add the word to its respective group in the hashMap.
* ...

## Big O analysis
* ...

## Reasons behind data structures chosen
* `HashMap` - to store lists of anagrams with their base array signature as the key. Lookup time is O(1).
* `char` `array` - to count the number of letters we see in a word and compare to other words. Looking up by index is O(1).
* `ArrayList` - to group all the anagrams. Adding an element to the list has amortized time of O(1). 

## With more time...
If I had more time, I would consider the following:
* Using Kafka to process the anagrams in real-time as the file is being read.
* Add check style (`jacoco`) for to make sure code is clean.
* Add better logging with Lombok instead of `System.out`.

## Assumptions
The following assumptions were made:
* Each word in the given file is in lowercase Latin letters, so no numbers or punctuation, for example.
* A word can get repeated as in `example1.txt`.
