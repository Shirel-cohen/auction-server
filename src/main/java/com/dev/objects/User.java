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

//    @Column
//    private int amountOfTenders;
//
//    @Column
//    private int amountOfCredits;

    public User() {
    }

    public User(String username, String token) {
        this.username = username;
        this.token = token;
//        this.amountOfTenders= amountOfTenders;
//        this.amountOfCredits = amountOfCredits;
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

//    public int getAmountOfTenders() {
//        return amountOfTenders;
//    }
//
//    public void setAmountOfTenders(int amountOfTenders) {
//        this.amountOfTenders = amountOfTenders;
//    }
//
//    public int getAmountOfCredits() {
//        return amountOfCredits;
//    }
//
//    public void setAmountOfCredits(int amountOfCredits) {
//        this.amountOfCredits = amountOfCredits;
//    }
}
