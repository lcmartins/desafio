package com.movimentacaobancaria.repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class FileRepository implements IFileRepository {

    private static final String FILE_PATH = "./dados.log";
    private String separatorPattern = "(\\s){3,}";

    public List<String[]> readFile() throws IOException {
        List<String[]> lineList;
        try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(FILE_PATH))) {
            lineList = bufferedReader
                    .lines()
                    .map(line -> line.split(separatorPattern))
                    .filter(lineArray -> lineArray.length > 0)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw e;
        }
        return lineList;
    }
}
