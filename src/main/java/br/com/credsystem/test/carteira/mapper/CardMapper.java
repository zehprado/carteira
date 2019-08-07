package br.com.credsystem.test.carteira.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.credsystem.test.carteira.model.Card;

public class CardMapper implements RowMapper<Card> {

	@Override
	public Card mapRow(ResultSet rs, int rowNum) throws SQLException {
		Card card = new Card();
		
		card.setLimit(rs.getDouble("limit_card"));
		card.setAvailableLimit(rs.getDouble("limit_using"));
		card.setCardNumber(rs.getString("number"));
		card.setCardPassword(rs.getString("password"));
		
		return card;
	}

}
