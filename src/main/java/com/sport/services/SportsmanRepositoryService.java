package com.sport.services;

import com.sport.infrastructure.SportsmanRepository;
import com.sport.models.Sportsman;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SportsmanRepositoryService {

    SportsmanRepository sportsmanRepository = new SportsmanRepository();

    public void saveSportsman(Sportsman sportsman){
        sportsmanRepository.saveSportsman(sportsman);
    }

    public void saveSportsmen(ArrayList<Sportsman> sportsmen){
        for (var sportsman: sportsmen) {
            saveSportsman(sportsman);
        }
    }

    public List<Sportsman> getAllSportsmen(){
        return  sportsmanRepository.getAllSportsmen();
    }

    public String getTeamWithMaxAvgAgeFromListOfTeams(List<String> teams){
        var condition = "team = \'" + String.join("\' OR team = \'", teams) + "\'";

        return sportsmanRepository.getTeamWithMaxAvgAgeWithCondition(condition);
    }

    public String getTeamWithMaxAvgColumn(String columnName){
        return sportsmanRepository.getTeamWithMaxAvgColumn(columnName);
    }

    public List<Sportsman> getSportsmenWithMaxHeightByTeam(String team, int sportsmenCount){
        return sportsmanRepository.getSportsmenWithMaxHeightByTeam(team, sportsmenCount);
    }

    public HashMap<String, Double> getTeamsAndTheirAvgAge(){
        var teamsWithAvgAgeList = sportsmanRepository.getTeamsAndTheirAvgAge();
        var teamsWithAvgAgeMap = new HashMap<String, Double>();
        for(var team : teamsWithAvgAgeList){
            teamsWithAvgAgeMap.put((String) team.get("0"), (double)team.get("1"));
        }

        return teamsWithAvgAgeMap;
    }

    public List<String> getTeamsByAvgHeightAndAvgWeight(int minHeight, int maxHeight, int minWeight, int maxWeight){
        var teamByHeight = sportsmanRepository.getTeamsByAvgHeightBetween(minHeight, maxHeight);
        var teamByWeight = sportsmanRepository.getTeamsByAvgWeightBetween(minWeight, maxWeight);
        teamByHeight.retainAll(teamByWeight);

        return  teamByHeight;
    }
}
