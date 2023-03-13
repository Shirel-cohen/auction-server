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
    private int auctionId;

    @Column
    private String ownOfOffer;

    @Column
    private String productName;
    @Column
    private String ownOfProduct;
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
    public Offers(int auctionId, String ownOfOffer, String productName,String ownOfProduct, double amountOfOffer) {
        this.auctionId= auctionId;
        this.ownOfOffer = ownOfOffer;
        this.productName = productName;
        this.ownOfProduct = ownOfProduct;
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

    public String getOwnOfProduct() {
        return ownOfProduct;
    }

    public void setOwnOfProduct(String ownOfProduct) {
        this.ownOfProduct = ownOfProduct;
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

    public int getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(int auctionId) {
        this.auctionId = auctionId;
    }
}

