package br.com.credsystem.test.carteira.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.credsystem.test.carteira.model.Card;
import br.com.credsystem.test.carteira.repository.CardRepository;
import br.com.credsystem.test.carteira.request.UpdateCardRequest;
import br.com.credsystem.test.carteira.request.UpdateLimitRequest;
import br.com.credsystem.test.carteira.service.CardService;

@Service
public class CardServiceImpl implements CardService {

	@Autowired
	private CardRepository repository;

	@Override
	public List<Card> listAllCardsById(Integer clientId) {
		return repository.listAllCardsById(clientId);
	}

	@Override
	public List<Card> listAllCards() {
		return repository.listAllCards();
	}

	@Override
	public Card listCard(String cardNumber) {
		return repository.listCard(cardNumber);
	}

	@Override
	public String deleteCard(String cardNumber) {
		Boolean result = repository.deleteCard(cardNumber);
		return result ? "Cartão removido com sucesso" : "Erro ao remover cartão";
	}

	@Override
	public String updateCard(UpdateCardRequest card) {
		Boolean result = repository.updateCard(card.getCardNumber(), card.getCard());
		return result ? "Cartão atualizado com sucesso" : "Erro ao atualizar cartão";
	}

	@Override
	public String addCardToClient(Integer clientId, Card card) {
		Integer message = repository.addCard(clientId, card);
		if (message != null && message > 0) {
			return "Cartão ".concat(card.getCardNumber()).concat(" adicionado para o client ")
					.concat(clientId.toString());
		} else {
			return "Erro ao adicionar Cartão";
		}
	}

	@Override
	public String updateLimit(UpdateLimitRequest updateLimit) {
		Card card = repository.listCard(updateLimit.getCardNumber());
		if (card.getLimit() - card.getAvailableLimit() < updateLimit.getValue()
				|| card.getLimit() < updateLimit.getValue()) {
			return "Limite Indisponível";
		}
		Boolean result = repository.updateLimit(updateLimit.getCardNumber(),
				card.getAvailableLimit() + updateLimit.getValue(), updateLimit.getPassword());
		return result ? "Limite consumido com sucesso" : "Erro ao consumir limite";
	}

	@Override
	public String paymentCard(UpdateLimitRequest paymentCard) {
		Card card = repository.listCard(paymentCard.getCardNumber());
		if (card.getAvailableLimit() < paymentCard.getValue()) {
			return "Valor maior do que o devido";
		}
		Boolean result = repository.updateLimit(paymentCard.getCardNumber(),
				card.getAvailableLimit() - paymentCard.getValue(), paymentCard.getPassword());
		card = repository.listCard(paymentCard.getCardNumber());

		String message = result
				? "Pagamento concluido. Limite disponível: " + (card.getLimit() - card.getAvailableLimit())
						+ ". Saldo devedor: " + card.getAvailableLimit()
				: "Erro no pagamento";
		return message;
	}

}
