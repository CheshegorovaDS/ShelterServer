package ru.omsu.imit.novikova.rest.request;

import ru.omsu.imit.novikova.model.Animal;
import ru.omsu.imit.novikova.model.Sex;

public class CardRequest {
    private int idUser;
    private int idCategory;
    private String nameAnimal;
    private String photoAnimal;
    private int ageAnimal;
    private String breedAnimal;
    private int idAnimalType;
    private Sex sexAnimal;
    private String passportAnimal;
    private String descriptionAnimal;

    public CardRequest() {
    }

    public CardRequest(int idUser, int idCategory,
                       String nameAnimal, String photoAnimal, int ageAnimal,
                       String breedAnimal, int idAnimalType, Sex sexAnimal,
                       String passportAnimal, String descriptionAnimal) {
        this.idUser = idUser;
        this.idCategory = idCategory;
        this.nameAnimal = nameAnimal;
        this.photoAnimal = photoAnimal;
        this.ageAnimal = ageAnimal;
        this.breedAnimal = breedAnimal;
        this.idAnimalType = idAnimalType;
        this.sexAnimal = sexAnimal;
        this.passportAnimal = passportAnimal;
        this.descriptionAnimal = descriptionAnimal;
    }

    public int getIdUser() {
        return idUser;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public String getNameAnimal() {
        return nameAnimal;
    }

    public String getPhotoAnimal() {
        return photoAnimal;
    }

    public int getAgeAnimal() {
        return ageAnimal;
    }

    public String getBreedAnimal() {
        return breedAnimal;
    }

    public int getIdAnimalType() {
        return idAnimalType;
    }

    public Sex getSexAnimal() {
        return sexAnimal;
    }

    public String getPassportAnimal() {
        return passportAnimal;
    }

    public String getDescriptionAnimal() {
        return descriptionAnimal;
    }
}
