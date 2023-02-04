package com.agilecrm.types.contact;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ContactDto {
    private String type;
    private int star_value;
    private int lead_score;
    private List<String> tags;
    private List<ContactPropertyDto> properties;

    @JsonProperty("star_value")
    public int getStar_value() {
        return this.star_value;
    }

    public void setStar_value(int star_value) {
        this.star_value = star_value;
    }

    @JsonProperty("lead_score")
    public int getLead_score() {
        return this.lead_score;
    }

    public void setLead_score(int lead_score) {
        this.lead_score = lead_score;
    }

    @JsonProperty("tags")
    public List<String> getTags() {
        return this.tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @JsonProperty("properties")
    public List<ContactPropertyDto> getProperties() {
        return this.properties;
    }

    public void setProperties(List<ContactPropertyDto> properties) {
        this.properties = properties;
    }


    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}
