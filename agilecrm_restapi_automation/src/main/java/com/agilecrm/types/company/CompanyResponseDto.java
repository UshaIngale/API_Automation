package com.agilecrm.types.company;

import com.agilecrm.types.contact.ContactResponseViewedDto;
import com.agilecrm.types.contact.PropertyResponseDto;
import com.agilecrm.types.contact.TagWithTimeContactResponseDto;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public   class CompanyResponseDto {
    private   long id;
    private   String type;
    private   int created_time;
    private   int updated_time;
    private   int last_contacted;
    private   int last_emailed;
    private   int last_campaign_emaild;
    private   int last_called;
    private   int viewed_time;
    private   CompanyResponseViewedDto viewed;
    private   int star_value;
    private   int lead_score;
    private   String klout_score;
    private List<String> tags;
    private   List<CompanyTagWithTimeResponseDto> tagsWithTime;
    private   List<CompanyPropertyResponseDto> properties;
    private   List<Object> campaignStatus;
    private   String entity_type;
    private   String source;
    private   List<Object> unsubscribeStatus;
    private   List<Object> emailBounceStatus;
    private   int formId;
    private   List<Object> browserId;
    private   int lead_source_id;
    private   int lead_status_id;
    private   boolean is_lead_converted;
    private   int lead_converted_time;
    private   boolean is_duplicate_existed;
    private   int trashed_time;
    private   int restored_time;
    private   boolean is_duplicate_verification_failed;
    private   boolean is_client_import;
    private   boolean concurrent_save_allowed;
    private   CompanyOwnerDto owner;
    @JsonProperty("unsubscribeStatus")
    public List<Object> getUnsubscribeStatus() {
        return unsubscribeStatus;
    }

    public void setUnsubscribeStatus(List<Object> unsubscribeStatus) {
        this.unsubscribeStatus = unsubscribeStatus;
    }
    @JsonProperty("emailBounceStatus")
    public List<Object> getEmailBounceStatus() {
        return emailBounceStatus;
    }

    public void setEmailBounceStatus(List<Object> emailBounceStatus) {
        this.emailBounceStatus = emailBounceStatus;
    }
    @JsonProperty("formId")
    public int getFormId() {
        return formId;
    }

    public void setFormId(int formId) {
        this.formId = formId;
    }
    @JsonProperty("browserId")
    public List<Object> getBrowserId() {
        return browserId;
    }

    public void setBrowserId(List<Object> browserId) {
        this.browserId = browserId;
    }
    @JsonProperty("lead_source_id")
    public int getLead_source_id() {
        return lead_source_id;
    }

    public void setLead_source_id(int lead_source_id) {
        this.lead_source_id = lead_source_id;
    }
    @JsonProperty("lead_status_id")
    public int getLead_status_id() {
        return lead_status_id;
    }

    public void setLead_status_id(int lead_status_id) {
        this.lead_status_id = lead_status_id;
    }
    @JsonProperty("is_lead_converted")
    public boolean getIs_lead_converted() {
        return is_lead_converted;
    }

    public void setIs_lead_converted(boolean is_lead_converted) {
        this.is_lead_converted = is_lead_converted;
    }
    @JsonProperty("lead_converted_time")
    public int getLead_converted_time() {
        return lead_converted_time;
    }

    public void setLead_converted_time(int lead_converted_time) {
        this.lead_converted_time = lead_converted_time;
    }
    @JsonProperty("is_duplicate_existed")
    public boolean getIs_duplicate_existed() {
        return is_duplicate_existed;
    }

    public void setIs_duplicate_existed(boolean is_duplicate_existed) {
        this.is_duplicate_existed = is_duplicate_existed;
    }
    @JsonProperty("trashed_time")
    public int getTrashed_time() {
        return trashed_time;
    }

    public void setTrashed_time(int trashed_time) {
        this.trashed_time = trashed_time;
    }
    @JsonProperty("restored_time")
    public int getRestored_time() {
        return restored_time;
    }

    public void setRestored_time(int restored_time) {
        this.restored_time = restored_time;
    }
    @JsonProperty("is_duplicate_verification_failed")

    public boolean getIs_duplicate_verification_failed() {
        return is_duplicate_verification_failed;
    }

    public void setIs_duplicate_verification_failed(boolean is_duplicate_verification_failed) {
        this.is_duplicate_verification_failed = is_duplicate_verification_failed;
    }
    @JsonProperty("is_client_import")
    public boolean getIs_client_import() {
        return is_client_import;
    }

    public void setIs_client_import(boolean is_client_import) {
        this.is_client_import = is_client_import;
    }
    @JsonProperty("concurrent_save_allowed")
    public boolean getConcurrent_save_allowed() {
        return concurrent_save_allowed;
    }

    public void setConcurrent_save_allowed(boolean concurrent_save_allowed) {
        this.concurrent_save_allowed = concurrent_save_allowed;
    }

    public CompanyOwnerDto getOwner() {
        return owner;
    }

    public void setOwner(CompanyOwnerDto owner) {
        this.owner = owner;
    }


    @JsonProperty("id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    @JsonProperty("type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    @JsonProperty("created_time")
    public int getCreated_time() {
        return created_time;
    }

    public void setCreated_time(int created_time) {
        this.created_time = created_time;
    }
    @JsonProperty("updated_time")
    public int getUpdated_time() {
        return updated_time;
    }

    public void setUpdated_time(int updated_time) {
        this.updated_time = updated_time;
    }
    @JsonProperty("last_contacted")
    public int getLast_contacted() {
        return last_contacted;
    }

    public void setLast_contacted(int last_contacted) {
        this.last_contacted = last_contacted;
    }
    @JsonProperty("last_emailed")
    public int getLast_emailed() {
        return last_emailed;
    }

    public void setLast_emailed(int last_emailed) {
        this.last_emailed = last_emailed;
    }
    @JsonProperty("last_campaign_emaild")
    public int getLast_campaign_emaild() {
        return last_campaign_emaild;
    }

    public void setLast_campaign_emaild(int last_campaign_emaild) {
        this.last_campaign_emaild = last_campaign_emaild;
    }
    @JsonProperty("last_called")
    public int getLast_called() {
        return last_called;
    }

    public void setLast_called(int last_called) {
        this.last_called = last_called;
    }
    @JsonProperty("viewed_time")
    public int getViewed_time() {
        return viewed_time;
    }

    public void setViewed_time(int viewed_time) {
        this.viewed_time = viewed_time;
    }
    @JsonProperty("viewed")
    public CompanyResponseViewedDto getViewed() {
        return viewed;
    }

    public void setViewed(CompanyResponseViewedDto viewed) {
        this.viewed = viewed;
    }
    @JsonProperty("star_value")
    public int getStar_value() {
        return star_value;
    }

    public void setStar_value(int star_value) {
        this.star_value = star_value;
    }
    @JsonProperty("lead_score")
    public int getLead_score() {
        return lead_score;
    }

    public void setLead_score(int lead_score) {
        this.lead_score = lead_score;
    }
    @JsonProperty("klout_score")
    public String getKlout_score() {
        return klout_score;
    }

    public void setKlout_score(String klout_score) {
        this.klout_score = klout_score;
    }
    @JsonProperty("tags")
    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
    @JsonProperty("tagsWithTime")
    public List<CompanyTagWithTimeResponseDto> getTagsWithTime() {
        return tagsWithTime;
    }

    public void setTagsWithTime(List<CompanyTagWithTimeResponseDto> tagsWithTime) {
        this.tagsWithTime = tagsWithTime;
    }
    @JsonProperty("properties")
    public List<CompanyPropertyResponseDto> getProperties() {
        return properties;
    }

    public void setProperties(List<CompanyPropertyResponseDto> properties) {
        this.properties = properties;
    }
    @JsonProperty("campaignStatus")
    public List<Object> getCampaignStatus() {
        return campaignStatus;
    }

    public void setCampaignStatus(List<Object> campaignStatus) {
        this.campaignStatus = campaignStatus;
    }
    @JsonProperty("entity_type")

    public String getEntity_type() {
        return entity_type;
    }

    public void setEntity_type(String entity_type) {
        this.entity_type = entity_type;
    }
    @JsonProperty("source")
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

}
