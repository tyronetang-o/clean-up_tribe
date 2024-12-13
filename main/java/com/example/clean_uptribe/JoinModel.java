package com.example.clean_uptribe;


public class JoinModel {
    private int id;
    private String name;
    private String email;
    private String location;
    private String interests;
    private String contributions;

    public JoinModel(int id, String name, String email, String location, String interests, String contributions) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.location = location;
        this.interests = interests;
        this.contributions = contributions;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getLocation() { return location; }
    public String getInterests() { return interests; }
    public String getContributions() { return contributions; }
}
