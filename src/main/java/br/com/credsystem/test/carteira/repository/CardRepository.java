package br.com.credsystem.test.carteira.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import br.com.credsystem.test.carteira.mapper.CardMapper;
import br.com.credsystem.test.carteira.model.Card;

@Repository
public class CardRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static final String queryCardsById = "select id_client, number, limit_card, limit_using, password from card  where id_client = ?";

	private static final String queryAllCards = "select id_client, number, limit_card, limit_using, password from card";
	
	private static final String queryByCardNumber = "select id_client, number, limit_card, limit_using, password from card where number = ?";
	
	private static final String deleteCardByNumber = "delete from card where number = ?";
	
	private static final String updateCard = "update card set number = ?, limit_card = ?, password = ? where number = ?";
	
	private static final String addCard = "insert into card (id_client, number, limit_card, limit_using, password) values (?,?,?,?,?)";
	
	private static final String updateLimit = "update card set limit_using = ? where number = ? and password = ?";
	
	public List<Card> listAllCardsById(Integer clientId) {
		Object[] params = new Object[] {clientId};
		return jdbcTemplate.query(queryCardsById, params, new CardMapper());
	}

	public List<Card> listAllCards() {
		return jdbcTemplate.query(queryAllCards, new CardMapper());
	}

	public Card listCard(String cardNumber) {
		Object[] params = new Object[] {cardNumber};
		return jdbcTemplate.queryForObject(queryByCardNumber, params, new CardMapper());
	}

	public Boolean deleteCard(String cardNumber) {
		Object[] params = new Object[] {cardNumber};
		return jdbcTemplate.update(deleteCardByNumber,params) == 1;
	}

	public Boolean updateCard(String cardNumber, Card card) {
		Object[] params = new Object[] {card.getCardNumber(), card.getLimit(), card.getCardPassword(), cardNumber};
		return jdbcTemplate.update(updateCard, params) == 1;
	}

	public Integer addCard(Integer clientId, Card card) {
		KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(addCard, Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, clientId);
				ps.setString(2, card.getCardNumber());
				ps.setDouble(3, card.getLimit());
				ps.setDouble(4, card.getAvailableLimit());
				ps.setString(5, card.getCardPassword());
				return ps;
			}
		}, holder);

		int id = holder.getKey().intValue();
		return id;
	}

	public Boolean updateLimit(String cardNumber, double d, String password) {
		Object[] params = new Object[] { d, cardNumber, password};
		return jdbcTemplate.update(updateLimit,params) == 1;
	}

	
}
