package com.root.model;

public class ClientModel {
	
	private int clientId;
	private String clientName;
	private String clientEmail;
	private String clientPassword;
	private String clientPhone;
	private String clientAddress;
	private int clientBalance;
	private int clientInvestment;
	
	public ClientModel() {
		// TODO Auto-generated constructor stub
	}

	public ClientModel(int clientId, String clientName, String clientEmail, String clientPassword, String clientPhone,
			String clientAddress, int clientBalance, int clientInvestment) {
		super();
		this.clientId = clientId;
		this.clientName = clientName;
		this.clientEmail = clientEmail;
		this.clientPassword = clientPassword;
		this.clientPhone = clientPhone;
		this.clientAddress = clientAddress;
		this.clientBalance = clientBalance;
		this.clientInvestment = clientInvestment;
	}
	
	public int getClientId() {
		return clientId;
	}

	public int getClientInvestment() {
		return clientInvestment;
	}

	public void setClientInvestment(int clientInvestment) {
		this.clientInvestment = clientInvestment;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientEmail() {
		return clientEmail;
	}

	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}

	public String getClientPassword() {
		return clientPassword;
	}

	public void setClientPassword(String clientPassword) {
		this.clientPassword = clientPassword;
	}

	public String getClientPhone() {
		return clientPhone;
	}

	public void setClientPhone(String clientPhone) {
		this.clientPhone = clientPhone;
	}

	public String getClientAddress() {
		return clientAddress;
	}

	public void setClientAddress(String clientAddress) {
		this.clientAddress = clientAddress;
	}

	public int getClientBalance() {
		return clientBalance;
	}

	public void setClientBalance(int clientBalance) {
		this.clientBalance = clientBalance;
	}

}


