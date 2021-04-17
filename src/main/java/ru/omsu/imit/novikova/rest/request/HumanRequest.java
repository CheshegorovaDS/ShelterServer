package ru.omsu.imit.novikova.rest.request;

import java.util.Date;

public class HumanRequest {

    private String firstName;
    private String lastName;
    private String patronymic;
    private Date birthdate;
    private String country;
    private String city;
    private Date registrationDate;
    private String phone;
    private String email;
    private String password;

    public HumanRequest() {
    }

    public HumanRequest(String firstName, String lastName, String patronymic, Date birthdate, String country, String city, Date registrationDate, String phone, String email, String password) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.birthdate = birthdate;
        this.country = country;
        this.city = city;
        this.registrationDate = registrationDate;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
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
