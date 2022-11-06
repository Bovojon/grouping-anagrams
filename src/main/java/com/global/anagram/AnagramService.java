package com.global.anagram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnagramService {
    private final Map<String, List<String>> anagramGroups;

    public AnagramService() {
        this.anagramGroups = new HashMap<>();
    }

    public void process(String fileName) {
        int prevLength = 0;
        String word;

        try (BufferedReader bufferedReader = new BufferedReader(
                new FileReader(fileName))) {
            word = bufferedReader.readLine();
            if (word == null) {
                System.out.println("File is empty.");
                return;
            }
            prevLength = word.length();
            while (word != null) {
                word = word.toLowerCase();
                if (word.length() != prevLength) {
                    outputAnagrams();
                    anagramGroups.clear();
                    prevLength = word.length();
                }
                addToGroup(word);
                word = bufferedReader.readLine();
            }
            outputAnagrams();
        } catch (IOException ex) {
            System.out.printf("Failed opening file: %s", fileName);
            throw new RuntimeException(ex);
        }
    }

    private void addToGroup(String word) {
        char[] trackingArray = new char[26];
        for (char ch : word.toCharArray()) {
            int index = ch - 'a';
            if (index<0 || index>25) {
                System.out.println("Words must only consist of letters.");
                return;
            }
            trackingArray[index]++;
        }
        String anagramKey = String.valueOf(trackingArray);
        if (!anagramGroups.containsKey(anagramKey)) {
            anagramGroups.put(anagramKey, new ArrayList<>());
        }
        anagramGroups.get(anagramKey).add(word);
    }

    private void outputAnagrams() {
        String opening = "[";
        String closing = "]";
        String empty = "";
        for (Map.Entry<String, List<String>> group : anagramGroups.entrySet()) {
            String anagramGroup = Arrays.toString(
                    group.getValue().toArray())
                    .replace(opening, empty)
                    .replace(closing, empty);
            System.out.println(anagramGroup);
        }
    }
}
