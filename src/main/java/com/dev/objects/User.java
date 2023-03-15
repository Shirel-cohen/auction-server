package com.dev.objects;

import javax.persistence.*;

@Entity
@Table (name = "my_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String username;

    @Column
    private String token;

    @Column
    private int amountOfAuctions;

    @Column
    private double amountOfCredits;

    public User() {
        this.amountOfCredits = 1000;
    }

    public User(String username, String token) {
        this.username = username;
        this.token = token;
        this.amountOfCredits = 1000;
    }

    public int getAmountOfAuctions() {
        return amountOfAuctions;
    }

    public void setAmountOfAuctions(int amountOfTenders) {
        this.amountOfAuctions = amountOfTenders;
    }

    public double getAmountOfCredits() {
        return amountOfCredits;
    }

    public void setAmountOfCredits(double amountOfCredits) {
        this.amountOfCredits = amountOfCredits;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
