package ru.yandex.practicum;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Wordle {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        try (PrintWriter log = new PrintWriter(new FileWriter("log.txt", true))) {
            WordleDictionaryLoader wordleDictionaryLoader = new WordleDictionaryLoader
                    (new File("words_ru.txt"), log);
            WordleDictionary wordleDictionary = new WordleDictionary(wordleDictionaryLoader.readFile(), log);
            WordleGame wordleGame = new WordleGame(wordleDictionary, log);
            game(wordleGame, log);
        }
    }

    public static void game(WordleGame wordleGame, PrintWriter log) {
        System.out.println("Оставшиеся попытки: " + wordleGame.getSteps() + " Введите слово:");
        String word = " ";
        while (!word.equals(wordleGame.getAnswer()) && wordleGame.getSteps() > 0) {
            try {
                word = scanner.nextLine().toLowerCase().trim();
                System.out.println(wordleGame.testWord(word));
                System.out.println("Количество попыток: " + wordleGame.getSteps());
                System.out.println("Для подсказки нажмите enter");
            } catch (WordNotFoundInDictionary | IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Игра закончена. Правильное слово: " + wordleGame.getAnswer());
    }
}


