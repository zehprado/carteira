package br.com.credsystem.test.carteira.model;

import java.math.BigDecimal;
import java.util.List;

public class Client {

	private Integer id;
	private String name;
	private Double salary;
	private String email;
	private List<Card> listCard;
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the salary
	 */
	public Double getSalary() {
		return salary;
	}
	/**
	 * @param salary the salary to set
	 */
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the listCard
	 */
	public List<Card> getListCard() {
		return listCard;
	}
	/**
	 * @param listCard the listCard to set
	 */
	public void setListCard(List<Card> listCard) {
		this.listCard = listCard;
	}
	
	
	
}
