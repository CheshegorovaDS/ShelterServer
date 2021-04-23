package ru.omsu.imit.novikova.model;

import java.util.Date;
import java.util.Objects;

public class Organisation {
    private User user;
    private long TIN;
    private String title;
    private String additionalInfo;
    private Date registrationDate;

    public Organisation() {
    }

    public Organisation(User user, long TIN, String title, String additionalInfo, Date registrationDate) {
        this.user = user;
        this.TIN = TIN;
        this.title = title;
        this.additionalInfo = additionalInfo;
        this.registrationDate = registrationDate;
    }

    public Organisation(int id, String phone, String email, String password,
                        long TIN, String title, String additionalInfo, Date registrationDate) {
        this.user = new User(id, phone, email, password);
        this.TIN = TIN;
        this.title = title;
        this.additionalInfo = additionalInfo;
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

    public long getTIN() {
        return TIN;
    }

    public void setTIN(long TIN) {
        this.TIN = TIN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
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
        if (!(o instanceof Organisation)) return false;
        Organisation that = (Organisation) o;
        return TIN == that.TIN &&
                Objects.equals(user, that.user) &&
                Objects.equals(title, that.title) &&
                Objects.equals(additionalInfo, that.additionalInfo) &&
                Objects.equals(registrationDate, that.registrationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, TIN, title, additionalInfo, registrationDate);
    }

    @Override
    public String toString() {
        return "Organisation{" +
                "user=" + user +
                ", TIN=" + TIN +
                ", title='" + title + '\'' +
                ", additionalInfo='" + additionalInfo + '\'' +
                ", registrationDate=" + registrationDate +
                '}';
    }
}
