package ru.omsu.imit.novikova.model;

import java.util.Date;
import java.util.Objects;

public class Human {
    private User user;
    private String firstName;
    private String lastName;
    private String patronymic;
    private Date birthdate;
    private String country;
    private String city;
    private Date registrationDate;

    public Human() {
    }

    public Human(int id, String phone, String email, String password, String firstName, String lastName, String patronymic, Date birthdate, String country, String city, Date registrationDate) {
        this.user = new User(id, phone, email, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.birthdate = birthdate;
        this.country = country;
        this.city = city;
        this.registrationDate = registrationDate;
    }

    public Human(User user, String firstName, String lastName, String patronymic, Date birthdate, String country, String city, Date registrationDate) {
        this.user = user;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.birthdate = birthdate;
        this.country = country;
        this.city = city;
        this.registrationDate = registrationDate;
    }

    public int getId() {
        return user.getId();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public void updateUser(String phone, String email, String password) {
        user.setPhone(phone);
        user.setEmail(email);
        user.setPassword(password);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Human)) return false;
        Human human = (Human) o;
        return Objects.equals(user, human.user) &&
                Objects.equals(firstName, human.firstName) &&
                Objects.equals(lastName, human.lastName) &&
                Objects.equals(patronymic, human.patronymic) &&
                Objects.equals(birthdate, human.birthdate) &&
                Objects.equals(country, human.country) &&
                Objects.equals(city, human.city) &&
                Objects.equals(registrationDate, human.registrationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, firstName, lastName, patronymic, birthdate, country, city, registrationDate);
    }

    @Override
    public String toString() {
        return "Human{" +
                "user=" + user +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", birthdate=" + birthdate +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", registrationDate=" + registrationDate +
                '}';
    }
}
