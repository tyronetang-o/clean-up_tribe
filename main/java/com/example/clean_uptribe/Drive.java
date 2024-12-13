package com.example.clean_uptribe;

public class Drive {

    public Drive(int id, String name, int date, int time) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Drive{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", time=" + time +
                '}';
    }

    private int id;
    private String name;
    private int date;
    private int time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}

