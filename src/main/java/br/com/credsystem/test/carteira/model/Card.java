package br.com.credsystem.test.carteira.model;

import javax.validation.constraints.Max;

public class Card {

	private String cardNumber;
	private Double limit;
	private Double availableLimit;
	private String cardPassword;
	/**
	 * @return the cardNumber
	 */
	@Max(value = 8)
	public String getCardNumber() {
		return cardNumber;
	}
	/**
	 * @param cardNumber the cardNumber to set
	 */
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	/**
	 * @return the limit
	 */
	public Double getLimit() {
		return limit;
	}
	/**
	 * @param limit the limit to set
	 */
	public void setLimit(Double limit) {
		this.limit = limit;
	}
	/**
	 * @return the availableLimit
	 */
	public Double getAvailableLimit() {
		return availableLimit;
	}
	/**
	 * @param availableLimit the availableLimit to set
	 */
	public void setAvailableLimit(Double availableLimit) {
		this.availableLimit = availableLimit;
	}
	/**
	 * @return the cardPassword
	 */
	public String getCardPassword() {
		return cardPassword;
	}
	/**
	 * @param cardPassword the cardPassword to set
	 */
	public void setCardPassword(String cardPassword) {
		this.cardPassword = cardPassword;
	}
	
	
	
}
