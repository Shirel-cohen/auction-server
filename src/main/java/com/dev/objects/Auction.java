package com.dev.objects;

import com.dev.utils.Constants;

import javax.persistence.*;
import javax.swing.*;
import java.util.Date;


@Entity
@Table(name = "auctions")
public class Auction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String productName;

    @Column
    private String productImage;

    @Column
    private Date dateOpenTender;

    @Column
    private int amountOfOffering;

    @Column
    private boolean isOpen;

    @Column
    private String productDescription;

    @Column
    private int minCost;

    @Column
    private String ownerOfTheProduct;

    @Column
    private double maxOfferAmount;

    public Auction(){

    }

    public Auction(String productName, String productImage, String dateOpenTender, int amountOfOffering, String productDescription, int minCost) {
        this.productName = productName;
        this.productImage = productImage;
        this.dateOpenTender = new Date();
        this.amountOfOffering = Constants.STARTING_OFFERING_NUMBER;
        this.isOpen = true;
        this.productDescription= productDescription;
        this.minCost = minCost;
    }

    public Auction(String productName, String productDescription, int minCost,String ownerOfTheProduct, String productImage) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.minCost = minCost;
        this.ownerOfTheProduct=ownerOfTheProduct;
        this.productImage = productImage;
        this.isOpen=true;
        this.dateOpenTender=new Date();
        this.maxOfferAmount = Constants.STARTING_OFFERING_NUMBER;
        this.amountOfOffering = Constants.STARTING_OFFERING_NUMBER;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public int getMinCost() {
        return minCost;
    }

    public void setMinCost(int minCost) {
        this.minCost = minCost;
    }

    public String getOwnerOfTheProduct() {
        return ownerOfTheProduct;
    }

    public void setOwnerOfTheProduct(String ownerOfTheProduct) {
        this.ownerOfTheProduct = ownerOfTheProduct;
    }

    public Date getDateOpenTender() {
        return dateOpenTender;
    }

    public void setDateOpenTender(Date dateOpenTender) {
        this.dateOpenTender = dateOpenTender;
    }

    public int getAmountOfOffering() {
        return amountOfOffering;
    }

    public void setAmountOfOffering(int amountOfOffering) {
        this.amountOfOffering = amountOfOffering;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public double getMaxOfferAmount() {
        return maxOfferAmount;
    }

    public void setMaxOfferAmount(double maxOfferAmount) {
        this.maxOfferAmount = maxOfferAmount;
    }
}

