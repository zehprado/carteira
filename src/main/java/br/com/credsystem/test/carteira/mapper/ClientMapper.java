package br.com.credsystem.test.carteira.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.credsystem.test.carteira.model.Client;

public class ClientMapper implements RowMapper<Client> {

	@Override
	public Client mapRow(ResultSet rs, int rowNum) throws SQLException {
		Client client = new Client();
		
		client.setId(rs.getInt("id"));
		client.setName(rs.getString("name"));
		client.setSalary(rs.getDouble("salario"));
		client.setEmail(rs.getString("email"));
		
		return client;
	}

}
