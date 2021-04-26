package ru.omsu.imit.novikova.model;

import java.util.Objects;

public class Animal {
    private int id;
    private String name;
    private String photo;
    private int age;
    private String breed;
    private AnimalType animalType;
    private Sex sex;
    private String passport;
    private String description;

    public Animal() {
    }

    public Animal(int id, String name, String photo, int age,
                  String breed, AnimalType animalType, Sex sex,
                  String passport, String description) {
        this.id = id;
        this.name = name;
        this.photo = photo;
        this.age = age;
        this.breed = breed;
        this.animalType = animalType;
        this.sex = sex;
        this.passport = passport;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public AnimalType getAnimalType() {
        return animalType;
    }

    public void setAnimalType(AnimalType animalType) {
        this.animalType = animalType;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Animal)) return false;
        Animal animal = (Animal) o;
        return id == animal.id &&
                age == animal.age &&
                Objects.equals(name, animal.name) &&
                Objects.equals(photo, animal.photo) &&
                Objects.equals(breed, animal.breed) &&
                Objects.equals(animalType, animal.animalType) &&
                sex == animal.sex &&
                Objects.equals(passport, animal.passport) &&
                Objects.equals(description, animal.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, photo, age, breed, animalType, sex, passport, description);
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", photo='" + photo + '\'' +
                ", age=" + age +
                ", breed='" + breed + '\'' +
                ", animalType=" + animalType +
                ", sex=" + sex +
                ", passport='" + passport + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
