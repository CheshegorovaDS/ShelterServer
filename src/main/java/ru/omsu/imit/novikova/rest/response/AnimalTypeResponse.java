package ru.omsu.imit.novikova.rest.response;

public class AnimalTypeResponse {
    private int id;
    private String title;

    public AnimalTypeResponse() {
    }

    public AnimalTypeResponse(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
