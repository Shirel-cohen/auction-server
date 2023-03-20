package com.dev.models;

import com.dev.objects.Auction;

import javax.security.sasl.SaslServer;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProductPageModel {
    private String productName;
    private String productDescription;
    private String  auctionOpeningDate;
    private String ownerOfProduct;
    private int amountOfOfferingOnProduct;
    private double minimalCost;
    private boolean isOpen;
    private String image;

    public ProductPageModel(){
    }

    public ProductPageModel(Auction auction){
        this.productName = auction.getProductName();
        this.productDescription = auction.getProductDescription();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        this.auctionOpeningDate = simpleDateFormat.format(auction.getDateOpenTender());
        this.ownerOfProduct = auction.getOwnerOfTheProduct().getUsername();
        this.amountOfOfferingOnProduct = auction.getAmountOfOffering();
        this.minimalCost = auction.getMinCost();
        this.isOpen = auction.isOpen();
        this.image = auction.getProductImage();

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

    public String getAuctionOpeningDate() {
        return auctionOpeningDate;
    }

    public void setAuctionOpeningDate(String auctionOpeningDate) {
        this.auctionOpeningDate = auctionOpeningDate;
    }

    public String getOwnerOfProduct() {
        return ownerOfProduct;
    }

    public void setOwnerOfProduct(String ownerOfProduct) {
        this.ownerOfProduct = ownerOfProduct;
    }

    public int getAmountOfOfferingOnProduct() {
        return amountOfOfferingOnProduct;
    }

    public void setAmountOfOfferingOnProduct(int amountOfOfferingOnProduct) {
        this.amountOfOfferingOnProduct = amountOfOfferingOnProduct;
    }

    public double getMinimalCost() {
        return minimalCost;
    }

    public void setMinimalCost(double minimalCost) {
        this.minimalCost = minimalCost;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
