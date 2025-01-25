package org.spring.authenticationservice.DTO.Currency;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReloadDto {
    @JsonProperty("amount")
    private Float amount;

    @JsonProperty("userID")
    private String userID;

    public Float getCurrency() {
        return amount;
    }

    public void setCurrency(Float currency) {
        this.amount = currency;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
