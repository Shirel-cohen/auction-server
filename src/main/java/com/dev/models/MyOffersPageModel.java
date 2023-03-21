package com.dev.models;

import com.dev.objects.Offers;

public class MyOffersPageModel {

    private String productName;
    private double amountOfOffer;
    private boolean auctionStatus;
    private boolean offerStatus;
    int auctionId;

    public MyOffersPageModel(){

    }
    public MyOffersPageModel(Offers offer){
        this.productName = offer.getAuction().getProductName();
        this.amountOfOffer = offer.getAmountOfOffer();
        this.auctionStatus=offer.getAuction().isOpen();
        this.offerStatus=offer.isChosen();
        this.auctionId = offer.getAuction().getId();
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getAmountOfOffer() {
        return amountOfOffer;
    }

    public void setAmountOfOffer(double amountOfOffer) {
        this.amountOfOffer = amountOfOffer;
    }

    public boolean isAuctionStatus() {
        return auctionStatus;
    }

    public void setAuctionStatus(boolean auctionStatus) {
        this.auctionStatus = auctionStatus;
    }

    public boolean isOfferStatus() {
        return offerStatus;
    }

    public void setOfferStatus(boolean offerStatus) {
        this.offerStatus = offerStatus;
    }

    public int getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(int auctionId) {
        this.auctionId = auctionId;
    }
}
