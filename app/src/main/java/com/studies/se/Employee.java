package com.studies.se;

public class Employee {
    private String id;
    private String name;
    private String surname;
    private String rate;
    private String birth;
    private String experience;
    private String owner;

    public Employee(String id, String name, String surname, String rate, String birth, String experience, String owner){
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.rate = rate;
        this.birth = birth;
        this.experience = experience;
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

    public String getBirth() {
        return birth;
    }

    public String getExperience() { return experience; }

    public String getOwner() {
        return owner;
    }

    public String getId() {
        return id;
    }
}
