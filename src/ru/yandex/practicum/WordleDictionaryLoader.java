package ru.yandex.practicum;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class WordleDictionaryLoader {

    private final File wordsFile;
    private final PrintWriter log;

    public WordleDictionaryLoader(File wordsFile, PrintWriter log) {
        this.wordsFile = wordsFile;
        this.log = log;
    }

    public List<String> readFile() throws IOException {
        List<String> wodi = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(wordsFile, StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.length() == 5) {
                    line = line.toLowerCase().replace("ё", "е");
                    wodi.add(line);
                }
            }
        } catch (IOException e) {
            log.println("Ошибка в чтении файла: " + e.getMessage());
            throw e;
        }
        return wodi;
    }
}