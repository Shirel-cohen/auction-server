package com.dev.responses;

import com.dev.objects.Product;
import com.dev.objects.Tender;

import java.util.List;

public class AllTendersResponse extends BasicResponse {
    private List<Tender> tenders;

    public AllTendersResponse(List<Tender> tenders) {
        this.tenders = tenders;
    }

    public AllTendersResponse(boolean success, Integer errorCode, List<Tender> tenders) {
        super(success, errorCode);
        this.tenders = tenders;
    }

    public List<Tender> getTenders() {
        return tenders;
    }

    public void setTenders(List<Tender> tenders) {
        this.tenders = tenders;
    }
}
