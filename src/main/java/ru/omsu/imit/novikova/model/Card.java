package ru.omsu.imit.novikova.model;

import java.util.Objects;

public class Card {
    private User user;
    private Category category;
    private Animal animal;

    public Card() {
    }

    public Card(User user, Category category, Animal animal) {
        this.user = user;
        this.category = category;
        this.animal = animal;
    }

    public Card(User user, Category category,
                int id, String name, String photo, int age,
                String breed, AnimalType animalType, Sex sex,
                String passport, String description
    ) {
        this.user = user;
        this.category = category;
        this.animal = new Animal(id, name, photo, age, breed, animalType, sex, passport, description);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public void updateCategory(Category newCategory) {
        category.setId(newCategory.getId());
        category.setTitle(newCategory.getTitle());
    }

    public void updateAnimal(String name, String photo, int age,
                             String breed, AnimalType animalType, Sex sex,
                             String passport, String description) {
        animal.setName(name);
        animal.setPhoto(photo);
        animal.setAge(age);
        animal.setBreed(breed);
        animal.setAnimalType(animalType);
        animal.setSex(sex);
        animal.setPassport(passport);
        animal.setDescription(description);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;
        Card card = (Card) o;
        boolean isEquals = Objects.equals(user, card.user) &&
                Objects.equals(category, card.category) &&
                Objects.equals(animal, card.animal);
        return isEquals;
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, category, animal);
    }

    @Override
    public String toString() {
        return "Card{" +
                "user=" + user +
                ", category=" + category +
                ", animal=" + animal +
                '}';
    }
}
