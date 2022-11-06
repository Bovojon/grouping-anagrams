package com.global;

import com.global.anagram.AnagramService;

public class Main {
    public static void main(String[] args) {
        String fileName = args[0];
        AnagramService anagramService = new AnagramService();
        System.out.printf("%nOpening file and processing anagrams:%n");
        anagramService.process(fileName);
        System.out.printf("%nFinished processing anagrams.%n");
    }
}
