package ru.omsu.imit.novikova.rest.response;

public class CategoryResponse {
    private int id;
    private String title;

    public CategoryResponse() {
    }

    public CategoryResponse(int id, String title) {
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
