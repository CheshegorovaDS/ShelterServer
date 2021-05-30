package ru.omsu.imit.novikova.rest.request;

public class AnimalTypeRequest {
    private String title;

    public AnimalTypeRequest() {
    }

    public AnimalTypeRequest(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
