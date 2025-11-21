package ru.yandex.practicum;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

class WordleDictionaryLoaderTest {

    File testWords = new File("test/ru/yandex/practicum/testwords_ru.txt");

    PrintWriter printWriter = new PrintWriter(System.out);
    WordleDictionaryLoader wordleDictionaryLoader = new WordleDictionaryLoader
            (testWords, printWriter);

    @Test
    void readFileTest() throws IOException {
        ArrayList<String> testList = new ArrayList<>(wordleDictionaryLoader.readFile());
        ArrayList<String> trowList = new ArrayList<>();
        String ee = "ё";
        BufferedReader bufferedReader = new BufferedReader(new FileReader(testWords,
                StandardCharsets.UTF_8));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            if (line.length() == 5) {
                line = line.toLowerCase().replace("ё", "е");
                trowList.add(line);
            }
        }
        Assertions.assertArrayEquals(testList.toArray(), trowList.toArray());
        Assertions.assertFalse(testList.contains(ee));
        for (String x : testList){
            Assertions.assertEquals(5, x.length());
        }
    }
}