package com.agilecrm.types.company;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CompanyTagWithTimeResponseDto {
    private String tag;
    private Object createdTime;
    private int availableCount;
    private String entity_type;

    @JsonProperty("tag")
    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
    @JsonProperty("createdTime")
    public Object getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Object createdTime) {
        this.createdTime = createdTime;
    }
    @JsonProperty("availableCount")
    public int getAvailableCount() {
        return availableCount;
    }

    public void setAvailableCount(int availableCount) {
        this.availableCount = availableCount;
    }
    @JsonProperty("entity_type")
    public String getEntity_type() {
        return entity_type;
    }

    public void setEntity_type(String entity_type) {
        this.entity_type = entity_type;
    }
}
