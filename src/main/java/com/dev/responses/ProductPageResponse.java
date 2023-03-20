package com.dev.responses;

import com.dev.models.AuctionModel;
import com.dev.models.ProductPageModel;
import com.dev.objects.Auction;

import java.util.ArrayList;
import java.util.List;

public class ProductPageResponse extends BasicResponse {
    private ProductPageModel productPage;

    public ProductPageResponse(){}

    public ProductPageResponse(boolean success, Integer errorCode, ProductPageModel productPageModel){
        super(success, errorCode);
        this.productPage = productPageModel;
    }

    public ProductPageResponse (Auction auction) {
        this.setSuccess(true);
        this.productPage = new ProductPageModel(auction);
    }

    public ProductPageModel getProductPage() {
        return productPage;
    }

    public void setProductPage(ProductPageModel productPage) {
        this.productPage = productPage;
    }
}
