package com.dev.responses;

import com.dev.objects.Auction;

public class AuctionResponse extends BasicResponse {

    private Auction auction;
    private int offersForUser;

    public AuctionResponse(Auction auction) {
        this.auction = auction;
    }

    public AuctionResponse(boolean success, Integer errorCode, Auction auction) {
        super(success, errorCode);
        this.auction = auction;
    }


    public Auction getAuction() {
        return auction;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }
}
