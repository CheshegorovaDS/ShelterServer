package ru.omsu.imit.novikova.rest.response;

import java.util.Date;

public class HumanResponse extends BaseResponseObject {
    private int id;
    private String phone;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String patronymic;
    private Date birthdate;
    private String country;
    private String city;
    private Date registrationDate;

    public HumanResponse() {
    }

    public HumanResponse(int id, String phone, String email, String password, String firstName, String lastName, String patronymic, Date birthdate, String country, String city, Date registrationDate) {
        this.id = id;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.birthdate = birthdate;
        this.country = country;
        this.city = city;
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
}
