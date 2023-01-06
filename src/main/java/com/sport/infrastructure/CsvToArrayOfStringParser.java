package com.sport.infrastructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class CsvToArrayOfStringParser {
    public static ArrayList<String[]> parse(String path, boolean removeHeader) throws IOException {
        ArrayList<String[]> result = new ArrayList<>();
        BufferedReader br = Files.newBufferedReader(Paths.get(path));

        String line;
        while ((line = br.readLine()) != null) {
            String[] columns = Arrays.stream(line.split(","))
                    .map(s -> s.replace("\"", "").trim())
                    .toArray(String[]::new);
            result.add(columns);
        }

        if (removeHeader){
            result.remove(0);
        }

        return result;
    }
}
