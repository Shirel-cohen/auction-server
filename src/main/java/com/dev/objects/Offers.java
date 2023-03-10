package com.dev.objects;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "offers")
public class Offers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String ownOfOffer;

    @Column
    private String productName;

    @Column
    private double offersAmount;

    @Column
    private boolean isChosen;

    @Column
    private LocalDate dateStartOffering;

    @Column
    private LocalTime timeStartOffering;


    public Offers() {

    }
    public Offers(int id, String ownOfOffer, String productName, double offersAmount, boolean isChosen) {
        this.id = id;
        this.ownOfOffer = ownOfOffer;
        this.productName = productName;
        this.offersAmount = offersAmount;
        this.isChosen = isChosen;
        this.dateStartOffering =LocalDate.now();
        this.timeStartOffering = LocalTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOwnOfOffer() {
        return ownOfOffer;
    }

    public void setOwnOfOffer(String ownOfOffer) {
        this.ownOfOffer = ownOfOffer;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getOffersAmount() {
        return offersAmount;
    }

    public void setOffersAmount(double offersAmount) {
        this.offersAmount = offersAmount;
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
}

