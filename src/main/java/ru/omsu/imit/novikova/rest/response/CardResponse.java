package ru.omsu.imit.novikova.rest.response;

public class CardResponse {
    private int idAnimal;
    private String nameAnimal;
    private String photoAnimal;
    private int ageAnimal;
    private String breedAnimal;
    private String animalType;
    private String sexAnimal;
    private String passportAnimal;
    private String descriptionAnimal;
    private int idUser;
    private String phoneUser;
    private String emailUser;
    private String passwordUser;
    private int idCategory;
    private String titleCategory;

    public CardResponse() {
    }

    public CardResponse(int idAnimal, String nameAnimal, String photoAnimal,
                        int ageAnimal, String breedAnimal, String animalType,
                        String sexAnimal, String passportAnimal, String descriptionAnimal,
                        int idUser, String phoneUser, String emailUser, String passwordUser,
                        int idCategory, String titleCategory) {
        this.idAnimal = idAnimal;
        this.nameAnimal = nameAnimal;
        this.photoAnimal = photoAnimal;
        this.ageAnimal = ageAnimal;
        this.breedAnimal = breedAnimal;
        this.animalType = animalType;
        this.sexAnimal = sexAnimal;
        this.passportAnimal = passportAnimal;
        this.descriptionAnimal = descriptionAnimal;
        this.idUser = idUser;
        this.phoneUser = phoneUser;
        this.emailUser = emailUser;
        this.passwordUser = passwordUser;
        this.idCategory = idCategory;
        this.titleCategory = titleCategory;
    }

    public int getIdAnimal() {
        return idAnimal;
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

    public String getAnimalType() {
        return animalType;
    }

    public String getSexAnimal() {
        return sexAnimal;
    }

    public String getPassportAnimal() {
        return passportAnimal;
    }

    public String getDescriptionAnimal() {
        return descriptionAnimal;
    }

    public int getIdUser() {
        return idUser;
    }

    public String getPhoneUser() {
        return phoneUser;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public String getPasswordUser() {
        return passwordUser;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public String getTitleCategory() {
        return titleCategory;
    }
}
