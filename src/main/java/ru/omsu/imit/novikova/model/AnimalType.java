package ru.omsu.imit.novikova.model;

import java.util.Objects;

public class AnimalType {
    private int id;
    private String title;

    public AnimalType() {
    }

    public AnimalType(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AnimalType)) return false;
        AnimalType that = (AnimalType) o;
        return id == that.id &&
                Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }

    @Override
    public String toString() {
        return "AnimalType{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
