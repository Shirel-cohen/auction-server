package com.dev.controllers;

import com.dev.models.AuctionModel;
import com.dev.objects.Auction;
import com.dev.objects.Offers;
import com.dev.objects.User;
import com.dev.responses.AllOffersResponse;
import com.dev.responses.AllAuctionsResponse;
import com.dev.responses.BasicResponse;
import com.dev.responses.LoginResponse;
import com.dev.utils.Persist;
import com.dev.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.dev.utils.Errors.*;

@RestController
public class LoginController {


    @Autowired
    private Utils utils;

    @Autowired
    private Persist persist;

    @RequestMapping(value = "sign-up")
    public BasicResponse signUp (String username, String password) {
        BasicResponse basicResponse = new BasicResponse();
        boolean success = false;
        Integer errorCode = null;
        if (username != null) {
            if (password != null) {
                    User fromDb = persist.getUserByUsername(username);
                    if (fromDb == null) {
                        User toAdd = new User(username, utils.createHash(username, password));
                        persist.saveUser(toAdd);
                        success = true;
                    } else {
                        errorCode = ERROR_USERNAME_ALREADY_EXISTS;
                    }

            } else {
                errorCode = ERROR_MISSING_PASSWORD;
            }
        } else {
            errorCode = ERROR_MISSING_USERNAME;
        }
        basicResponse.setSuccess(success);
        basicResponse.setErrorCode(errorCode);
        return basicResponse;
    }

    @RequestMapping (value = "login")
    public BasicResponse login (String username, String password) {
        BasicResponse basicResponse = new BasicResponse();
        boolean success = false;
        Integer errorCode = null;
        if (username != null) {
            if (password != null) {
                String token = utils.createHash(username, password);
                User fromDb = persist.getUserByUsernameAndToken(username, token);
                if (fromDb != null) {
                    success = true;
                    basicResponse = new LoginResponse(token);
                } else {
                    errorCode = ERROR_WRONG_LOGIN_CREDS;
                }
            } else {
                errorCode = ERROR_MISSING_PASSWORD;
            }
        } else {
            errorCode = ERROR_MISSING_USERNAME;
        }
        basicResponse.setSuccess(success);
        basicResponse.setErrorCode(errorCode);
        return basicResponse;
    }

    @RequestMapping (value = "get-all-auctions", method = RequestMethod.GET)
    public BasicResponse getAllAuctions () {
        List<Auction> auctions = persist.getAllAuctions();
        AllAuctionsResponse allAuctionsResponse = new AllAuctionsResponse( auctions);
        return allAuctionsResponse;
    }

    @RequestMapping (value = "get-all-offers", method = RequestMethod.GET)
    public BasicResponse getAllOffers () {
        List<Offers> offers = persist.getAllOffers();
        BasicResponse basicResponse=null;
        if(offers!=null){
             basicResponse = new AllOffersResponse(offers);
        }else
             basicResponse = new BasicResponse(false, ERROR_NO_OFFERS);

        return basicResponse;
    }
}
