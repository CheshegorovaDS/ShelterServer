package ru.omsu.imit.novikova.rest.request;

public class CardRequest {
    private int idAnimal;
    private int idUser;
    private int idCategory;

    public CardRequest() {
    }

    public CardRequest(int idAnimal, int idUser, int idCategory) {
        this.idAnimal = idAnimal;
        this.idUser = idUser;
        this.idCategory = idCategory;
    }

    public int getIdAnimal() {
        return idAnimal;
    }

    public int getIdUser() {
        return idUser;
    }

    public int getIdCategory() {
        return idCategory;
    }
}
