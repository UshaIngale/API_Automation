package com.agilecrm.types.contact;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ContactResponseViewedDto {
    private int viewed_time;
    @JsonProperty("viewed_time")
    public int getViewed_time() {
        return viewed_time;
    }

    public void setViewed_time(int viewed_time) {
        this.viewed_time = viewed_time;
    }


}
