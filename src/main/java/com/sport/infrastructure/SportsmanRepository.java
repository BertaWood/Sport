package com.sport.infrastructure;

import com.sport.models.Sportsman;
import org.hibernate.Transaction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SportsmanRepository {

    public void saveSportsman(Sportsman sportsman){
        Transaction transaction = null;

        try(var session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.save(sportsman);
            transaction.commit();
        }
        catch (Exception e){
            if (transaction != null)
                transaction.rollback();
        }
    }

    public List<Sportsman> getAllSportsmen(){
        Transaction transaction = null;
        List<Sportsman> sportsmen = null;

        try(var session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            //var query = session.createQuery("from sportsman");
            sportsmen = session.createQuery("from Sportsman").list();
            transaction.commit();
        }
        catch (Exception e){
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }

        return sportsmen;
    }

    public String getTeamWithMaxAvgColumn(String columnName){
        Transaction transaction = null;
        List<String> teams = null;

        try(var session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            teams = session.createQuery(
                    "SELECT team " +
                    "FROM Sportsman " +
                    "GROUP BY team " +
                    "ORDER By AVG(" + columnName + ") DESC"
            ).list();
            transaction.commit();
        }
        catch (Exception e){
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }

        return teams.get(0);
    }

    public List<Sportsman> getSportsmenWithMaxHeightByTeam(String team, int sportsmenCount){
        Transaction transaction = null;
        List<Sportsman> sportsmen = null;

        try(var session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            var query = session.createQuery(
                    "FROM Sportsman" +
                            " WHERE team = \'" + team + "\'" +
                            " ORDER By height DESC");
            query.setFirstResult(0);
            query.setMaxResults(sportsmenCount);
            sportsmen = query.list();
            transaction.commit();
        }
        catch (Exception e){
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }

        return sportsmen;
    }

    public List<String> getTeamsByAvgHeightBetween(int min, int max){
        Transaction transaction = null;
        List<String> teams = null;

        try(var session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            var query = session.createQuery(
                    "SELECT team " +
                            "FROM Sportsman " +
                            "GROUP by team " +
                            "HAVING (AVG(height) BETWEEN " + min + " AND " + max + ")");
            teams = query.list();
            transaction.commit();
        }
        catch (Exception e){
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }

        return teams;
    }

    public List<String> getTeamsByAvgWeightBetween(int min, int max){
        Transaction transaction = null;
        List<String> teams = null;

        try(var session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            var query = session.createQuery(
                    "SELECT team " +
                            "FROM Sportsman " +
                            "GROUP by team " +
                            "HAVING (AVG(weight) BETWEEN " + min + " AND " + max + ")");
            teams = query.list();
            transaction.commit();
        }
        catch (Exception e){
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }

        return teams;
    }

    public String getTeamWithMaxAvgAgeWithCondition(String condition){
        Transaction transaction = null;
        List<String> teams = null;

        try(var session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            var query = session.createQuery(
                    "SELECT team " +
                            "FROM Sportsman " +
                            "WHERE " + condition +
                            "GROUP by team " +
                            "ORDER BY AVG(age) DESC");
            teams = query.list();
            transaction.commit();
        }
        catch (Exception e){
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }

        return teams.get(0);
    }

    public List<Map> getTeamsAndTheirAvgAge(){
        Transaction transaction = null;
        List<Map> teams = null;

        try(var session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            var query = session.createQuery(
                    "SELECT new map(team,  AVG(age)) " +
                            "FROM Sportsman " +
                            "GROUP by team", Map.class);
            teams = query.getResultList();
            transaction.commit();
        }
        catch (Exception e){
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }

        return teams;
    }
}
