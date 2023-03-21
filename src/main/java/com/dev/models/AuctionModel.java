package com.dev.models;

import com.dev.objects.Auction;
import com.dev.objects.Offers;

import javax.swing.*;
import java.text.SimpleDateFormat;

public class AuctionModel {

    private int id;
    private String productName;
    private String productDescription;
    private String productImage;
    private String dateOpening;
    private int amountOfOffers;
    private boolean isOpen;
    private double maxOfferAmount;
    private String ownerOfTheProduct;

    public AuctionModel () {
    }




    public AuctionModel (Auction auction) {
        this.id = auction.getId();
        this.productName = auction.getProductName();
        this.productDescription = auction.getProductDescription();
        this.productImage = auction.getProductImage();
        this.isOpen = auction.isOpen();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        this.dateOpening = simpleDateFormat.format(auction.getDateOpenTender());
        this.amountOfOffers = auction.getAmountOfOffering();
        this.maxOfferAmount = auction.getMaxOfferAmount();
        this.ownerOfTheProduct=auction.getOwnerOfTheProduct().getUsername();

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

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getDateOpening() {
        return dateOpening;
    }

    public void setDateOpening(String dateOpening) {
        this.dateOpening = dateOpening;
    }

    public int getAmountOfOffers() {
        return amountOfOffers;
    }

    public void setAmountOfOffers(int amountOfOffers) {
        this.amountOfOffers = amountOfOffers;
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

    public String getOwnerOfTheProduct() {
        return ownerOfTheProduct;
    }

    public void setOwnerOfTheProduct(String ownerOfTheProduct) {
        this.ownerOfTheProduct = ownerOfTheProduct;
    }
}

