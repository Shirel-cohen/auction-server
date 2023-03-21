package com.dev.responses;

import com.dev.models.AuctionModel;
import com.dev.models.MyOffersPageModel;
import com.dev.objects.Auction;
import com.dev.objects.Offers;
import com.dev.objects.User;

import java.util.ArrayList;
import java.util.List;

public class MyOffersPageResponse extends BasicResponse {
    private List<MyOffersPageModel> offers;


    public MyOffersPageResponse(boolean success, Integer errorCode, List<MyOffersPageModel> offers) {
        super(success, errorCode);
        this.offers = offers;
    }
    public MyOffersPageResponse (List<Offers> offers) {
        this.setSuccess(true);
        this.offers = new ArrayList<>();
        for (Offers offer : offers) {
            this.offers.add(new MyOffersPageModel(offer));
        }
    }

    public List<MyOffersPageModel> getOffers() {
        return offers;
    }

    public void setOffers(List<MyOffersPageModel> offers) {
        this.offers = offers;
    }
}
