package ru.yandex.practicum;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

class WordleDictionaryTest {

    File testWords = new File("test/ru/yandex/practicum/testwords_ru.txt");

    PrintWriter printWriter = new PrintWriter(System.out);
    WordleDictionaryLoader wordleDictionaryLoader = new WordleDictionaryLoader
            (testWords, printWriter);
    WordleDictionary wordleDictionary = new WordleDictionary(wordleDictionaryLoader.readFile(), printWriter);


    WordleDictionaryTest() throws IOException {
    }

    @Test
    void getWords() throws IOException {
        ArrayList<String> trowList = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(testWords,
                StandardCharsets.UTF_8));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            if (line.length() == 5) {
                line = line.toLowerCase().replace("ё", "е");
                trowList.add(line);
            }
        }
        Assertions.assertArrayEquals(trowList.toArray(), wordleDictionary.getWords().toArray());
    }

    @Test
    void getRandomWordTest() throws IOException {
        ArrayList<String> trowList = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(testWords,
                StandardCharsets.UTF_8));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            if (line.length() == 5) {
                line = line.toLowerCase().replace("ё", "е");
                trowList.add(line);
            }
        }
        Assertions.assertTrue(trowList.contains(wordleDictionary.getRandomWord()));
    }

    @Test
    void mixListTest() throws IOException {
        ArrayList<String> trowList = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(testWords,
                StandardCharsets.UTF_8));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            if (line.length() == 5) {
                line = line.toLowerCase().replace("ё", "е");
                trowList.add(line);
            }
        }
        Assertions.assertNotEquals(trowList.toArray(), wordleDictionary.mixList().toArray());
    }
}