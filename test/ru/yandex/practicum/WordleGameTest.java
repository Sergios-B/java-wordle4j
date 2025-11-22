package ru.yandex.practicum;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.*;

class WordleGameTest {

    File testWords = new File("test/ru/yandex/practicum/testwords_ru.txt");

    PrintWriter printWriter = new PrintWriter(System.out);
    WordleDictionaryLoader wordleDictionaryLoader = new WordleDictionaryLoader
            (testWords, printWriter);
    WordleDictionary wordleDictionary = new WordleDictionary(wordleDictionaryLoader.readFile(), printWriter);
    WordleGame wordleGame = new WordleGame(wordleDictionary, printWriter);

    WordleGameTest() throws IOException {
    }


    @Test
    void getSteps() throws WordNotFoundInDictionary {
        Assertions.assertEquals(6, wordleGame.getSteps());
        wordleGame.testWord("сукно");
        Assertions.assertEquals(5, wordleGame.getSteps());
        wordleGame.testWord("");
        Assertions.assertEquals(5, wordleGame.getSteps());
    }

    @Test
    void getAnswer() {
        Assertions.assertTrue(wordleDictionary.getWords().contains(wordleGame.getAnswer()));
    }

    @Test
    void testWord() {
        String v = "Вау! Вы победили!";
        assertEquals(v, wordleGame.testWord(wordleGame.getAnswer()));
        String nonExistingWord = "abcde";
        assertThrows(WordNotFoundInDictionary.class, () -> wordleGame.testWord(nonExistingWord));
        String nonFiveChar = "сон";
        assertThrows(IllegalArgumentException.class, () -> wordleGame.testWord(nonFiveChar));
        String attempt = "сукно";
        if (!attempt.equals(wordleGame.getAnswer())) {
            Assertions.assertTrue(wordleGame.testWord(attempt).contains("-") ||
                    wordleGame.testWord(attempt).contains("+") || wordleGame.testWord(attempt).contains("^") &&
                    wordleGame.testWord(attempt).length() == 5);
        } else {
            assertEquals(v, wordleGame.testWord(attempt));
        }
    }


    @Test
    void giveHint() {
        Assertions.assertTrue(wordleDictionary.getWords().contains(wordleGame.testWord("   ")));
        String firstAttempsWord = "сукно";
        String firstAttemps = wordleGame.testWord("сукно");
        boolean realy = true;
        for (int i = 0; i < 5; i++) {
            realy = switch (String.valueOf(firstAttemps.charAt(i))) {
                case "-" -> !(wordleGame.testWord(" ").contains(String.valueOf(firstAttempsWord.charAt(i))));
                case "+" ->
                        String.valueOf(wordleGame.testWord(" ").charAt(i)).equals(String.valueOf(wordleGame.getAnswer().charAt(i)));
                case "^" -> wordleGame.testWord(" ").contains(String.valueOf(firstAttempsWord.charAt(i)));
                default -> realy;
            };
        }
        assertTrue(realy);
    }
}