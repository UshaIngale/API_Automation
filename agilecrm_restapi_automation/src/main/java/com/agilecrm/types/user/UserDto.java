package com.agilecrm.types.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDto {
    private String name;
    private String gender;
    private String email;
    private String status;
    @JsonProperty("name")
    public String getName() {
        return this.name; }
    public void setName(String name) {
        this.name = name; }

    @JsonProperty("gender")
    public String getGender() {
        return this.gender; }
    public void setGender(String gender) {
        this.gender = gender; }

    @JsonProperty("email")
    public String getEmail() {
        return this.email; }
    public void setEmail(String email) {
        this.email = email; }

    @JsonProperty("status")
    public String getStatus() {
        return this.status; }
    public void setStatus(String status) {
        this.status = status; }
}
