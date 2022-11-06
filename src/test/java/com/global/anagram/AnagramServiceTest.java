package com.global.anagram;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AnagramServiceTest {
    AnagramService anagramService;
    final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    final PrintStream originalOut = System.out;
    final PrintStream originalErr = System.err;
    String nonExistentFile = "nonexistent.txt";
    String testFileName = "Data/example1.txt";
    String emptyFile = "Data/empty.txt";
    String numberFile = "Data/number_word.txt";
    String upperCaseFile = "Data/upper.txt";

    @BeforeEach
    void setUp() {
        anagramService = new AnagramService();
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    void afterEach() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    void processAnagrams_happyCase_printsAnagrams() {
        // given
        String expectedResult = String.format("fun, fun, unf%nabc, bac, cba%nhello%n");

        // when
        anagramService.process(testFileName);

        // then
        assertEquals(expectedResult, outContent.toString());
    }

    @Test
    void processAnagrams_upperCaseLetters_printsAnagrams() {
        // given
        String expectedResult = String.format("fun%nabc, bac%n");

        // when
        anagramService.process(upperCaseFile);

        // then
        assertEquals(expectedResult, outContent.toString());
    }

    @Test
    void processAnagrams_emptyFile_printsMessage() {
        // given
        String expectedErrMessage = String.format("File is empty.%n");

        // when
        anagramService.process(emptyFile);

        // then
        assertEquals(expectedErrMessage, outContent.toString());
    }

    @Test
    void processAnagrams_nonExistentFile_throws() {
        // given
        String expectedErrMessage = String.format("Failed opening file: %s", nonExistentFile);

        // then
        assertThrows(RuntimeException.class, () -> anagramService.process(nonExistentFile));
        assertEquals(expectedErrMessage, outContent.toString());
    }

    @Test
    void processAnagrams_wordsWithNumbers_throws() {
        // given
        String expectedErrMessage = "Words must only consist of letters: hi7";

        // then
        assertThrows(RuntimeException.class,
                () -> anagramService.process(numberFile), expectedErrMessage);
    }
}
