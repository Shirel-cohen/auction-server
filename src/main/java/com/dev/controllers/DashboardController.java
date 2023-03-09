package com.dev.controllers;


import com.dev.objects.Product;
import com.dev.objects.User;
import com.dev.responses.AllUsersResponse;
import com.dev.responses.BasicResponse;
import com.dev.responses.UsernameResponse;
import com.dev.responses.productResponse;
import com.dev.utils.Persist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static com.dev.utils.Errors.ERROR_NO_SUCH_RECIPIENT;
import static com.dev.utils.Errors.ERROR_NO_SUCH_TOKEN;

@RestController
public class DashboardController {

    @Autowired
    private Persist persist;

    @Autowired
    private LiveUpdatesController liveUpdatesController;

    @RequestMapping (value = "get-username", method = RequestMethod.GET)
    public BasicResponse getUsername (String token) {
        User user = persist.getUserByToken(token);
        BasicResponse basicResponse = null;
        if (user != null) {
            basicResponse = new UsernameResponse(true, null, user.getUsername());
        } else {
            basicResponse = new BasicResponse(false, ERROR_NO_SUCH_TOKEN);
        }
        return basicResponse;
    }


    @RequestMapping(value = "upload-product")
    public BasicResponse uploadProduct(String owner, String productName, String img, String describe, int minimalCost) {
        Product productToAdd = new Product(productName, describe, minimalCost, owner,img);
        productResponse productResponse = new productResponse(true,null,productToAdd);
        persist.uploadProduct(productToAdd);

        return productResponse;

    }












}
