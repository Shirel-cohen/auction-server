package com.dev.responses;

import com.dev.objects.Offers;
import com.dev.objects.User;

import java.util.List;

public class AllOffersResponse extends BasicResponse {
    private List<Offers> offers;

    public AllOffersResponse(List<Offers> offers) {
        this.offers = offers;
    }

    public AllOffersResponse(boolean success, Integer errorCode, List<Offers> offers) {
        super(success, errorCode);
        this.offers = offers;
    }

    public List<Offers> getOffers() {
        return offers;
    }

    public void setUsers(List<Offers> offers) {
        this.offers = offers;
    }
}
