package com.example.practice_r3;

public class Passenger {
    private String name;
    private String phone;
    private String preference; // bus / plane / train

    public Passenger() {
    }

    public Passenger(String name, String phone, String preference) {
        this.name = name;
        this.phone = phone;
        this.preference = preference;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPreference() {
        return preference;
    }

    public void setPreference(String preference) {
        this.preference = preference;
    }
}
