package ru.omsu.imit.novikova.rest.request;

public class UserRequest {
    private String login;
    private String password;

    public UserRequest() {
    }

    public UserRequest(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
