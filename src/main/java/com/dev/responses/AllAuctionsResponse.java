//package com.dev.responses;
//
//import com.dev.models.AuctionModel;
//import com.dev.objects.Auction;
//
//import javax.accessibility.AccessibleIcon;
//import java.util.ArrayList;
//import java.util.List;
//
//public class AllAuctionsResponse extends BasicResponse {
//    private List<AuctionModel> auctions;
//
//    public AllAuctionsResponse (List<Auction> auctions, int userId) {
//        this.setSuccess(true);
//        this.auctions = new ArrayList<>();
//        for (Auction auction : auctions) {
//            this.auctions.add(new AuctionModel(auction, userId));
//        }
//    }
//
//
//    public List<AuctionModel> getMessageList() {
//        return auctions;
//    }
//
//    public void setMessageList(List<AuctionModel> messageList) {
//        this.auctions = messageList;
//    }
//}
package com.dev.responses;

import com.dev.models.AuctionModel;
import com.dev.objects.Auction;
import com.dev.objects.Offers;
import com.dev.objects.User;

import java.util.ArrayList;
import java.util.List;

public class AllAuctionsResponse extends BasicResponse {
    private List<AuctionModel> auctions;


    public AllAuctionsResponse(boolean success, Integer errorCode, List<AuctionModel> auctions) {
        super(success, errorCode);
        this.auctions = auctions;
    }
    public AllAuctionsResponse (List<Auction> auctions) {
        this.setSuccess(true);
        this.auctions = new ArrayList<>();
        for (Auction auction : auctions) {
            this.auctions.add(new AuctionModel(auction));
        }
    }


    public List<AuctionModel> getAuctions() {
        return auctions;
    }

    public void setAuctions(List<AuctionModel> auctions) {
        this.auctions = auctions;
    }
}

