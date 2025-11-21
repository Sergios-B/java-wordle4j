package ru.yandex.practicum;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class WordleDictionary {

    private final List<String> words;
    private final PrintWriter log;


    public WordleDictionary(List<String> words, PrintWriter log) {
        this.words = words;
        this.log = log;

    }

    public List<String> getWords() {
        return words;
    }

    public String getRandomWord() {
        Random random = new Random();
        int index = random.nextInt(words.size());
        return words.get(index);
    }

    public List<String> mixList() {
        List<String> list = new ArrayList<>(words);
        Collections.shuffle(list);
        return list;
    }
}