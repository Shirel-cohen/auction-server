package com.dev.responses;

import com.dev.objects.Offers;
import com.dev.responses.BasicResponse;

public class OfferResponse extends BasicResponse {

    private Offers offer;
    private double maxOfferForProduct;
    private Integer offersForUser;

    public OfferResponse(Offers offer) {
        this.offer = offer;
    }

    public OfferResponse(boolean success, Integer errorCode, double maxOffer) {
        super(success, errorCode);
        this.maxOfferForProduct=maxOffer;
    }


    public Offers getOffer() {
        return offer;
    }

    public void setOffer(Offers offer) {
        this.offer = offer;
    }

    public double getMaxOfferForProduct() {
        return maxOfferForProduct;
    }

    public void setMaxOfferForProduct(double maxOfferForProduct) {
        this.maxOfferForProduct = maxOfferForProduct;
    }
}