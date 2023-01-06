package com.sport;

import com.sport.models.Sportsman;
import com.sport.services.ChartService;
import com.sport.services.SportsmanRepositoryService;
import com.sport.services.SportsmanService;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

    private static final SportsmanRepositoryService sportsmanService = new SportsmanRepositoryService();
    private static final String pathToCsv = "./src/main/resources/Sportsmen.csv";

    public static void main(String[] args){
        //Initialization already done
        //initializeDbFromCsv();
        doFirstExercise();
        doSecondExercise();
        doThirdExercise();
    }

    private static void initializeDbFromCsv(){
        ArrayList<Sportsman> sportsmen = null;

        try {
            sportsmen = SportsmanService.getSportsmenFromCSV(pathToCsv);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert sportsmen != null;
        sportsmanService.saveSportsmen(sportsmen);
    }

    private static void doFirstExercise(){
        var maps = sportsmanService.getTeamsAndTheirAvgAge();

        try {
            ChartService.createChartFromHashMap(maps);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void doSecondExercise(){
        var teamWithMaxAvgHeight = sportsmanService.getTeamWithMaxAvgColumn("height");
        var sportsmenWithMaxHeight = sportsmanService.getSportsmenWithMaxHeightByTeam(teamWithMaxAvgHeight, 5);

        for(var sportsman : sportsmenWithMaxHeight){
            System.out.println(sportsman);
        }
    }

    private static void doThirdExercise(){
        var teams = sportsmanService.getTeamsByAvgHeightAndAvgWeight(74, 78, 190, 210);
        System.out.println(sportsmanService.getTeamWithMaxAvgAgeFromListOfTeams(teams));
    }
}