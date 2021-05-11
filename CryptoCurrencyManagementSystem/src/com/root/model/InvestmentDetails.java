package com.root.model;

import java.util.Date;

public class InvestmentDetails {

	private int investmentId;
	private Date investmentDate;
	private CryptoCurrencyModel cryptoCurrency;
	private ClientModel clientModel;
	private int investment;
	
	public InvestmentDetails() {
		// TODO Auto-generated constructor stub
	}

	public InvestmentDetails(int investmentId, Date investmentDate, CryptoCurrencyModel cryptoCurrency,
			ClientModel clientModel, int investment) {
		super();
		this.investmentId = investmentId;
		this.investmentDate = investmentDate;
		this.cryptoCurrency = cryptoCurrency;
		this.clientModel = clientModel;
		this.investment = investment;
	}

	public int getInvestmentId() {
		return investmentId;
	}

	public void setInvestmentId(int investmentId) {
		this.investmentId = investmentId;
	}

	public Date getInvestmentDate() {
		return investmentDate;
	}

	public void setInvestmentDate(Date investmentDate) {
		this.investmentDate = investmentDate;
	}

	public CryptoCurrencyModel getCryptoCurrency() {
		return cryptoCurrency;
	}

	public void setCryptoCurrency(CryptoCurrencyModel cryptoCurrency) {
		this.cryptoCurrency = cryptoCurrency;
	}

	public ClientModel getClientModel() {
		return clientModel;
	}

	public void setClientModel(ClientModel clientModel) {
		this.clientModel = clientModel;
	}

	public int getInvestment() {
		return investment;
	}

	public void setInvestment(int investment) {
		this.investment = investment;
	}

	
}
