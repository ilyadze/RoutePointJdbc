package ru.courceWork.models;

import java.util.List;

public class Route {

    private String name;
    private List<Point> points;
    private double distance;
    private String description;
    private String category;
    private String user;


    public Route() {}
    public Route(String name, List<Point> points, double distance, String description, String category, String user) {
        this.name = name;
        this.points = points;
        this.distance = distance;
        this.description = description;
        this.category = category;
        this.user = user;
    }

    public Route(String name, List<Point> points, double distance) {
        this.name = name;
        this.points = points;
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
