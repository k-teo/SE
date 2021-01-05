package com.octolearn.se;

public class Employee {
    private String id;
    private String name;
    private String surname;
    private String rate;
    private String owner;

    public Employee(String id, String name, String surname, String rate, String owner){
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.rate = rate;
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getRate() {
        return rate;
    }

    public String getOwner() {
        return owner;
    }

    public String getId() {
        return id;
    }
}
