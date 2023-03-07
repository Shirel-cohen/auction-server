package com.dev.controllers;


import com.dev.objects.Product;
import com.dev.objects.Tender;
import com.dev.objects.User;
import com.dev.responses.AllTendersResponse;
import com.dev.responses.AllUsersResponse;
import com.dev.responses.BasicResponse;
import com.dev.utils.Persist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    @RequestMapping (value = "get-all-tenders", method = RequestMethod.GET)
    public BasicResponse getAllTenders () {
        List<Tender> tenders = persist.getAllTenders();
        AllTendersResponse allTendersResponse = new AllTendersResponse(true, null, tenders);
        return allTendersResponse;
    }

    @RequestMapping (value = "get-all-open-tenders", method = RequestMethod.GET)
    public BasicResponse getAllOpenTenders () {
        List<Tender> tenders = persist.getAllOpenTenders();
        AllTendersResponse allTendersResponse = new AllTendersResponse(true, null, tenders);
        return allTendersResponse;
    }

//     @RequestMapping(value = "update-credits-for-user", method = RequestMethod.POST)
//    public int updateCreditsForUser(String username, int creditsToUpdate) {
//        persist.updateCredits(username, creditsToUpdate);
//        return creditsToUpdate;
//    }
}
