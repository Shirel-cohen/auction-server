package com.dev.objects;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "offers")
public class Offers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @ManyToOne
    @JoinColumn(name = "auctionId")
    private Auction auction;

    @ManyToOne
    @JoinColumn
    private User ownOfOffer;

    @Column
    private double amountOfOffer;

    @Column
    private boolean isChosen;

    @Column
    private LocalDate dateStartOffering;

    @Column
    private LocalTime timeStartOffering;


    public Offers() {

    }

    public Offers(Auction auction,User ownOfOffer, double amountOfOffer) {
        this.auction= auction;
        this.ownOfOffer = ownOfOffer;
        this.amountOfOffer = amountOfOffer;
        this.isChosen = false;
        this.dateStartOffering =LocalDate.now();
        this.timeStartOffering = LocalTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getOwnOfOffer() {
        return ownOfOffer;
    }

    public void setOwnOfOffer(User ownOfOffer) {
        this.ownOfOffer = ownOfOffer;
    }

    public double getAmountOfOffer() {
        return amountOfOffer;
    }

    public void setAmountOfOffer(double amountOfOffer) {
        this.amountOfOffer = amountOfOffer;
    }

    public boolean isChosen() {
        return isChosen;
    }

    public void setChosen(boolean chosen) {
        isChosen = chosen;
    }

    public LocalDate getDateStartOffering() {
        return dateStartOffering;
    }

    public void setDateStartOffering(LocalDate dateStartOffering) {
        this.dateStartOffering = dateStartOffering;
    }

    public LocalTime getTimeStartOffering() {
        return timeStartOffering;
    }

    public void setTimeStartOffering(LocalTime timeStartOffering) {
        this.timeStartOffering = timeStartOffering;
    }

    public Auction getAuction() {
        return auction;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }
}

