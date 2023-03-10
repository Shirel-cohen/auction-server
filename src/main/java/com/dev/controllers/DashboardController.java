package com.dev.controllers;


import com.dev.models.AuctionModel;
import com.dev.objects.Auction;
import com.dev.objects.Offers;
import com.dev.objects.User;
import com.dev.responses.*;
import com.dev.utils.Persist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.awt.image.BandCombineOp;
import java.util.List;

import static com.dev.utils.Errors.*;

@RestController
public class DashboardController {

    @Autowired
    private Persist persist;

    @Autowired
    private LiveUpdatesController liveUpdatesController;

    @RequestMapping(value = "get-username", method = RequestMethod.GET)
    public BasicResponse getUsername(String token) {
        User user = persist.getUserByToken(token);
        BasicResponse basicResponse = null;
        if (user != null) {
            basicResponse = new UsernameResponse(true, null, user.getUsername());
        } else {
            basicResponse = new BasicResponse(false, ERROR_NO_SUCH_TOKEN);
        }
        return basicResponse;
    }

    @RequestMapping(value = "get-all-auction-for-user", method = RequestMethod.GET)
    public AllAuctionsResponse getAllAuctionForUser(String username) {
        List<Auction> auctionListForUser = persist.getAuctionsByUsername(username);
        AllAuctionsResponse allAuctionsResponse = new AllAuctionsResponse(auctionListForUser);
        return allAuctionsResponse;
    }

    @RequestMapping(value = "get-all-offers-for-user", method = RequestMethod.GET)
    public AllOffersResponse getAllOffersForUser(String username) {
        List<Offers> offersListForUser = persist.getOffersByUsername(username);
        AllOffersResponse allOffersResponse = new AllOffersResponse(true, null, offersListForUser);
        return allOffersResponse;
    }

    @RequestMapping(value = "get-max-offer-for-product", method = RequestMethod.GET)
    public BasicResponse getMaxOfferForProduct(String username, String productName) {
        double maxOffer = persist.getAllOffersForProduct(username, productName);
        BasicResponse basicResponse = null;
        if (maxOffer != 0) {
            basicResponse = new OfferResponse(true, null, maxOffer);
        } else {
            basicResponse = new BasicResponse(false, ERROR_MISSING_OFFERS);
        }
        return basicResponse;
    }

    @RequestMapping(value = "upload-product")
    public BasicResponse uploadProduct(String owner, String productName, String img, String describe, int minimalCost) {
        Auction productToAdd = new Auction(productName, describe, minimalCost, owner, img);

        AuctionResponse auctionResponse = new AuctionResponse(true, null, productToAdd);
        persist.uploadProduct(productToAdd);
        return auctionResponse;
    }

    @RequestMapping(value = "close-auction")
    public BasicResponse closeAction(Auction auction) {
        int countOfOffering = persist.getAmountOfOffersByName(auction.getProductName());
        BasicResponse basicResponse = null;
        if (countOfOffering >= 3) {
            auction.setOpen(false);
            basicResponse = new BasicResponse(true, null);
        } else {
            basicResponse = new BasicResponse(false, ERROR_LESS_THAN_3_OFFERS);
        }
        return basicResponse;
    }

    @RequestMapping(value = "get-product-by-id", method = RequestMethod.GET)
    public BasicResponse getProductById(int id) {
        Auction auction = persist.getAuctionById(id);
        BasicResponse basicResponse = null;
        if (auction != null) {
            basicResponse = new AuctionResponse(true, null, auction);
        } else {
            basicResponse = new BasicResponse(false, ERROR_NO_SUCH_PRODUCT);
        }
        return basicResponse;
    }

    @RequestMapping(value = "get-my-offers-on-product", method = RequestMethod.GET)
    public BasicResponse getMyOffersOnProduct(String username, String productName) {
        List<Offers> listOfMyOffers = persist.listOfMyOffers(username, productName);
        BasicResponse basicResponse = null;
        if (listOfMyOffers != null) {
            basicResponse = new AllOffersResponse(true, null, listOfMyOffers);
        } else {
            basicResponse = new BasicResponse(false, ERROR_NO_OFFERS);
        }
        return basicResponse;
    }

    @RequestMapping(value = "send-offer", method = RequestMethod.POST)
    public BasicResponse setOfferForAuction(String username, String productName, double offerAmount, String ownerOfProduct) {
        User user = persist.getUserByUsername(username);
        double maxOffer = persist.getMaxOfferByUsernameAndProduct(username, productName);
        Auction product = persist.getProductByProductNameAndOwnerOf (productName,ownerOfProduct);
        BasicResponse basicResponse = null;
        if (user.getAmountOfCredits() < offerAmount) {
            basicResponse = new BasicResponse(false, ERROR_NOT_ENOUGH_CREDITS);
        }
        if (maxOffer < offerAmount) {
            basicResponse = new BasicResponse(false, ERROR_OFFER_NOT_HIGH_ENOUGH);
        }
        if (product.getMinCost() > offerAmount) {
            basicResponse = new BasicResponse(false, ERROR_OFFER_LOWER_THAN_MIN_COST);
        } else {
            Offers newOffer = new Offers(product.getId(),username,productName,ownerOfProduct,offerAmount);
            persist.saveOffer(newOffer);
            basicResponse = new BasicResponse(true, null);
        }
        return basicResponse;
    }
}


