package ru.omsu.imit.novikova.rest.request;

import java.util.Date;

public class OrganisationRequest {
    private long tin;
    private String title;
    private String additionalInfo;
    private Date registrationDate;
    private String phone;
    private String email;
    private String password;

    public OrganisationRequest() {
    }

    public OrganisationRequest(long tin, String title, String additionalInfo, Date registrationDate, String phone, String email, String password) {
        this.tin = tin;
        this.title = title;
        this.additionalInfo = additionalInfo;
        this.registrationDate = registrationDate;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    public long getTin() {
        return tin;
    }

    public String getTitle() {
        return title;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
