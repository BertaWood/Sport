package com.sport.services;

import com.sport.infrastructure.CsvToArrayOfStringParser;
import com.sport.models.Sportsman;

import java.io.IOException;
import java.util.ArrayList;

public class SportsmanService {
    public static ArrayList<Sportsman> getSportsmenFromCSV(String path) throws IOException {
        var parsedData = CsvToArrayOfStringParser.parse(path, true);
        var result = new ArrayList<Sportsman>();
        for (var sportsman: parsedData) {
            var name = sportsman[0];
            var team = sportsman[1];
            var position = sportsman[2];
            var height = Integer.parseInt(sportsman[3]);
            var weight = Integer.parseInt(sportsman[4]);
            var age = (int)Double.parseDouble(sportsman[5]);

            result.add(new Sportsman(name, team, position, height, weight, age));
        }

        return result;
    }
}
