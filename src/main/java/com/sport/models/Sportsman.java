package com.sport.models;

import javax.persistence.*;

@Entity
@Table(name="sportsman")
public class Sportsman {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String name;
    private String team;
    private String position;
    private int height;
    private int weight;
    private int age;

    public Sportsman(){}

    public Sportsman(String name, String team, String position, int height, int weight, int age) {
        this.name = name;
        this.team = team;
        this.position = position;
        this.height = height;
        this.weight = weight;
        this.age = age;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Sportsman{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", team='" + team + '\'' +
                ", position='" + position + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", age=" + age +
                '}';
    }
}
