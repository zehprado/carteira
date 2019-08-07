package br.com.credsystem.test.carteira.service;

import java.util.List;

import br.com.credsystem.test.carteira.model.Card;
import br.com.credsystem.test.carteira.request.UpdateCardRequest;
import br.com.credsystem.test.carteira.request.UpdateLimitRequest;

public interface CardService {

	List<Card> listAllCardsById(Integer clientId);

	List<Card> listAllCards();

	Card listCard(String cardNumber);

	String deleteCard(String cardNumber);

	String addCardToClient(Integer clientId, Card card);

	String updateLimit(UpdateLimitRequest updateLimit);

	String paymentCard(UpdateLimitRequest paymentCard);

	String updateCard(UpdateCardRequest card);

}
