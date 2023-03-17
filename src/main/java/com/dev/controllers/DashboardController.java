package com.dev.controllers;


import com.dev.objects.Auction;
import com.dev.objects.Offers;
import com.dev.objects.User;
import com.dev.responses.*;
import com.dev.utils.Constants;
import com.dev.utils.Persist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
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

    @RequestMapping(value = "get-credits-for-user", method = RequestMethod.GET)
    public CreditsResponse getCreditsForUser(String username) {
        Double creditsForUser = persist.getCreditsByUsername(username);
        CreditsResponse creditsResponse = new CreditsResponse(true, null, creditsForUser);
        return creditsResponse;
    }

    @RequestMapping(value = "upload-product")
    public BasicResponse uploadProduct(String owner, String productName, String img, String describe, int minimalCost) {
        Auction productToAdd = new Auction(productName, describe, minimalCost, owner, img);
        AuctionResponse auctionResponse = new AuctionResponse(true, null, productToAdd);
        persist.uploadProduct(productToAdd);
        return auctionResponse;
    }

//    @RequestMapping(value = "close-auction",method = RequestMethod.POST)
//    public BasicResponse closeAuction(int auctionId){
//        BasicResponse basicResponse = null;
//        Auction auction = persist.getAuctionById(auctionId);
//        if(auction.getAmountOfOffering() < 3){
//            basicResponse = new BasicResponse(false,ERROR_LESS_THAN_3_OFFERS);
//        }
//        else {
//            persist.closeAuction(auctionId);
//            // persist.updateOfferWon(auctionId);
//            basicResponse = new BasicResponse(true,null);
//        }
//        return basicResponse;
//    }
@RequestMapping(value = "close-auction",method = RequestMethod.POST)
public BasicResponse closeAuction(int auctionId){
    BasicResponse basicResponse = null;
    List<Offers> offersForProduct = persist.getOffersByAuctionId(auctionId);
    // Auction auction = persist.getAuctionById(auctionId);
    if(offersForProduct.size() < 3){
        basicResponse = new BasicResponse(false,ERROR_LESS_THAN_3_OFFERS);
    }
    else {
        persist.closeAuction(auctionId);
        liveUpdatesController.sendCloseAuction(offersForProduct);
        basicResponse = new BasicResponse(true,null);
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

    @RequestMapping(value = "send-offer")
    public BasicResponse setOfferForAuction(String ownOfOffer, String productName, double amountOfOffer, String ownOfProduct, int amountOfOffering) {
        User user = persist.getUserByUsername(ownOfOffer);
        Auction product = persist.getProductByProductNameAndOwnerOf (productName,ownOfProduct);
        Double maxOfferForSpecificUsername = Constants.STARTING_OFFERING_NUMBER;
        if(persist.getAmountOfOffersForProductByUsername(ownOfOffer,productName) >= Constants.MINIMAL_OFFERING_NUMBER) {
            maxOfferForSpecificUsername = persist.getMaxOfferByUsernameAndProduct(ownOfOffer, productName);
        }
        BasicResponse basicResponse = null;
        if (user.getAmountOfCredits() < amountOfOffer) {
            basicResponse = new BasicResponse(false, ERROR_NOT_ENOUGH_CREDITS);
        }
        else if (maxOfferForSpecificUsername > amountOfOffer) {
            basicResponse = new BasicResponse(false, ERROR_OFFER_NOT_HIGH_ENOUGH);
        }
        else if (product.getMinCost() > amountOfOffer) {
            basicResponse = new BasicResponse(false, ERROR_OFFER_LOWER_THAN_MIN_COST);
        } else {
            Offers newOffer = new Offers(product.getId(),ownOfOffer,productName,ownOfProduct,amountOfOffer);
            persist.updateUserCredits(ownOfOffer,amountOfOffer,productName);
            persist.saveOffer(newOffer);
            persist.updateAmountOfOffersForAuction(product.getId(),amountOfOffering);
            Double maxOfferForProductInGeneral = persist.getMaxOfferForProduct(ownOfProduct, product.getId());
            if(maxOfferForProductInGeneral == null){
                maxOfferForProductInGeneral = 0.0;
            }
            persist.updateMaxOfferForAuction(product.getId(),maxOfferForProductInGeneral);
            String tokenOwnerOfProduct = persist.getUserByUsername(product.getOwnerOfTheProduct()).getToken();
            liveUpdatesController.sendNewOffer(tokenOwnerOfProduct);
            basicResponse = new BasicResponse(true, null);
        }
        return basicResponse;
    }
}
