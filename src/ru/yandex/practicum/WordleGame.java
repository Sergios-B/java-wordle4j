package ru.yandex.practicum;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WordleGame {

    private final String answer;

    private int steps = 5;

    private final WordleDictionary dictionary;

    private final PrintWriter log;

    public String[] getCharacterTrue() {
        return characterTrue;
    }

    public ArrayList<String> getCharacterContain() {
        return characterContain;
    }

    public HashMap<Integer, ArrayList<String>> getCharacterNoPlace() {
        return characterNoPlace;
    }

    public ArrayList<String> getCharacterNon() {
        return characterNon;
    }

    public WordleDictionary getDictionary() {
        return dictionary;
    }

    String[] characterTrue = new String[5];
    ArrayList<String> characterContain = new ArrayList<>();
    HashMap<Integer, ArrayList<String>> characterNoPlace = new HashMap<>();
    ArrayList<String> characterNon = new ArrayList<>();

    public WordleGame(WordleDictionary dictionary, PrintWriter log) {
        this.dictionary = dictionary;
        answer = dictionary.getRandomWord();
        this.log = log;
    }

    public int getSteps() {
        return steps;
    }

    public String getAnswer() {
        return answer;
    }

    public String testWord(String word) throws WordNotFoundInDictionary, IllegalArgumentException {
        if (word.isBlank()) {
            return giveHint();
        }
        if (word.length() != 5) {
            throw new IllegalArgumentException("Слово должно состоять из 5 букв.");
        }
        if (!dictionary.getWords().contains(word)) {
            throw new WordNotFoundInDictionary("Введённое слово не найдено в словаре.");
        }
        steps = steps - 1;
        StringBuilder result = new StringBuilder("-----");
        if (word.equals(answer)) {
            return "Вау! Вы победили!";
        }
        for (int i = 0; i < 5; i++) {
            char letterWord = word.charAt(i);
            char letterAnswer = answer.charAt(i);
            if (letterWord == letterAnswer) {
                result.replace(i, i + 1, "+");
                characterTrue[i] = String.valueOf(letterWord);
            } else if (answer.contains(String.valueOf(letterWord))) {
                result.replace(i, i + 1, "^");
                characterContain.add(String.valueOf(letterWord));
                if (characterNoPlace.get(i) != null) {
                    ArrayList<String> in = characterNoPlace.get(i);
                    in.add(String.valueOf(letterWord));
                    characterNoPlace.put(i, in);
                } else {
                    ArrayList<String> in = new ArrayList<>();
                    in.add(String.valueOf(letterWord));
                    characterNoPlace.put(i, in);
                }
            } else {
                characterNon.add(String.valueOf(letterWord));
            }
        }
        log.println("Пустой ввод. Попытка: " + word + ", осталось попыток: " + steps);
        return result.toString();
    }

    public String giveHint() {
        String maybe = null;
        List<String> wordsss = new ArrayList<>(dictionary.mixList());

        for (String x : wordsss) {
            boolean matches = true;

            for (String a : characterContain) {
                if (!x.contains(a)) {
                    matches = false;
                    break;
                }
            }

            for (String a : characterNon) {
                if (x.contains(a)) {
                    matches = false;
                    break;
                }
            }

            if (matches) {
                for (int i = 0; i < characterTrue.length; i++) {
                    if (characterTrue[i] != null && !characterTrue[i].equals(String.valueOf(x.charAt(i)))) {
                        matches = false;
                        break;
                    }
                }

                for (int i = 0; i < 5; i++) {
                    if (characterNoPlace.get(i) != null &&
                            characterNoPlace.get(i).contains(String.valueOf(x.charAt(i)))) {
                        matches = false;
                        break;
                    }
                }

                if (matches) {
                    maybe = x;
                    break;
                }
            }
        }
        return maybe;
    }
}
