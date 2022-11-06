package com.global.anagram;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnagramService {
    private final Map<String, List<String>> anagramGroups;

    public AnagramService() {
        this.anagramGroups = new HashMap<>();
    }

    public void process(String fileName) {
        throw new RuntimeException("Not implemented yet.");
    }
}
