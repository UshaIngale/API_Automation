package com.agilecrm.types.contact;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OwnerDto {
    private long id;
    private String domain;
    private String email;
    private String phone;
    private String name;
    private String pic;
    private String schedule_id;
    private String calendar_url;
    private String calendarURL;
    @JsonProperty("id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    @JsonProperty("domain")
    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @JsonProperty("phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @JsonProperty("pic")
    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
    @JsonProperty("schedule_id")
    public String getSchedule_id() {
        return schedule_id;
    }

    public void setSchedule_id(String schedule_id) {
        this.schedule_id = schedule_id;
    }
    @JsonProperty("calendar_url")
    public String getCalendar_url() {
        return calendar_url;
    }

    public void setCalendar_url(String calendar_url) {
        this.calendar_url = calendar_url;
    }
    @JsonProperty("calendarURL")
    public String getCalendarURL() {
        return calendarURL;
    }

    public void setCalendarURL(String calendarURL) {
        this.calendarURL = calendarURL;
    }
}
