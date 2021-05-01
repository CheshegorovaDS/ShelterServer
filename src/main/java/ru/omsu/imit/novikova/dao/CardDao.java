package ru.omsu.imit.novikova.dao;


import ru.omsu.imit.novikova.exception.ShelterException;
import ru.omsu.imit.novikova.model.Card;

import java.util.List;

public interface CardDao {

    public Card insert(Card card);

    public Card getById(int idUser, int idAnimal) throws ShelterException;

    public List<Card> getAll();

    public List<Card> getByCategory();

    public List<Card> getByAnimalType();

    public List<Card> getByUserId();

    public void changeCard(int id, Card newCard) throws ShelterException;

    public void delete(int id);

    public void deleteAll();
}
