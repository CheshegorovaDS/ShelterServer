package ru.omsu.imit.novikova.rest.response;

import java.util.Date;

public class OrganisationResponse {
    private int id;
    private String phone;
    private String email;
    private String password;
    private long TIN;
    private String title;
    private String additionalInfo;
    private Date registrationDate;

    public OrganisationResponse() {
    }

    public OrganisationResponse(int id, String phone, String email, String password, long TIN, String title, String additionalInfo, Date registrationDate) {
        this.id = id;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.TIN = TIN;
        this.title = title;
        this.additionalInfo = additionalInfo;
        this.registrationDate = registrationDate;
    }

    public int getId() {
        return id;
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

    public long getTIN() {
        return TIN;
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
}
