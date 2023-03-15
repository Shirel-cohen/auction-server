package com.dev.responses;

public class CreditsResponse extends BasicResponse {
    private Double credits;

    public CreditsResponse(Double credits) {
        this.credits = credits;
    }

    public CreditsResponse(boolean success, Integer errorCode, Double credits) {
        super(success, errorCode);
        this.credits = credits;
    }

    public Double getCredits() {
        return credits;
    }

    public void setCredits(Double credits) {
        this.credits = credits;
    }
}
