package ru.omsu.imit.novikova.rest.request;

public class CategoryRequest {
    private String title;

    public CategoryRequest() {
    }

    public CategoryRequest(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
