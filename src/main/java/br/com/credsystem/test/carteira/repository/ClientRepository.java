package br.com.credsystem.test.carteira.repository;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import br.com.credsystem.test.carteira.mapper.ClientMapper;
import br.com.credsystem.test.carteira.mapper.IntegerMapper;
import br.com.credsystem.test.carteira.model.Client;

@Repository
public class ClientRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static final String queryAllClients = "SELECT id, name, salario, email FROM CLIENT";
	
	private static final String queryClientById = "SELECT id, name, salario, email FROM CLIENT where id = ?";
	
	private static final String deleteClientById = "DELETE FROM CLIENT WHERE id = ?";
	
	private static final String updateClient = "UPDATE CLIENT SET name = ?, salario = ?, email = ? where id = ?";
	
	private static final String addClient = "INSERT INTO CLIENT (name, salario, email) VALUES (?,?,?)";
	
	private static final String verifyEmail = "SELECT 1 as int FROM CLIENT where email = ?";
	
	public List<Client> listAllClient() {
		return jdbcTemplate.query(queryAllClients, new ClientMapper());
	}

	public Client listClientById(Integer clientId){
		Object[] params = new Object[] {clientId};
		return jdbcTemplate.queryForObject(queryClientById, params, new ClientMapper());
	}

	public Boolean deleteClient(Integer clientId) {
		Object[] params = new Object[] {clientId};
		return jdbcTemplate.update(deleteClientById,params) == 1;
	}

	public Boolean updateClient(Client client) {
		Object[] params = new Object[] {client.getName(), client.getSalary(), client.getEmail(), client.getId()};
		return jdbcTemplate.update(updateClient,params) == 1;
	}

	public Integer addClient(String name, String email, BigDecimal salary) {
		
		KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(addClient, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, name);
				ps.setBigDecimal(2, salary);
				ps.setString(3, email);
				return ps;
			}
		}, holder);

		int id = holder.getKey().intValue();
		return id;
	}

	public Boolean verifyDuplicity(String email) {
		Object[] params = new Object[] {email};
		Integer value = null;
		try {		
			value = jdbcTemplate.queryForObject(verifyEmail, params, new IntegerMapper());
		} catch(EmptyResultDataAccessException e) {
			value = 0;
		}
		
		return value != null && value > 0 ? true : false;
	}
	
}
