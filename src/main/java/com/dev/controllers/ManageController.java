package com.dev.controllers;


import com.dev.models.AuctionModel;
import com.dev.objects.Auction;
import com.dev.objects.User;
import com.dev.responses.AllAuctionsResponse;
import com.dev.responses.AllUsersResponse;
import com.dev.responses.BasicResponse;
import com.dev.responses.CreditsResponse;
import com.dev.utils.Persist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.dev.utils.Errors.ERROR_INVALID_CREDIT;

@RestController
public class ManageController {

    @Autowired
    private Persist persist;

    @RequestMapping (value = "get-all-users", method = RequestMethod.GET)
    public BasicResponse getAllUsers () {
        List<User> users = persist.getAllUsers();
        AllUsersResponse allUsersResponse = new AllUsersResponse(true, null, users);
        return allUsersResponse;
    }

    @RequestMapping (value = "get-all-open-auctions", method = RequestMethod.GET)
    public BasicResponse getAllOpenTenders () {
        List<Auction> auctions = persist.getAllOpenAuctions();
        AllAuctionsResponse allAuctionsResponse = new AllAuctionsResponse(auctions);
        return allAuctionsResponse;
    }

    @RequestMapping(value = "update-credit", method = RequestMethod.POST)
    public BasicResponse updateCredit(String token, Double newCredit) {
        CreditsResponse creditsResponse = new CreditsResponse();
        if (newCredit > 0) {
            creditsResponse.setCredits(persist.updateUserCredit(token, newCredit));
            creditsResponse.setSuccess(true);
        } else {
            creditsResponse.setCredits(newCredit);
            creditsResponse.setErrorCode(ERROR_INVALID_CREDIT);
        }
        return creditsResponse;
    }
}
