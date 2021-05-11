package com.root.model;

public class CryptoCurrencyModel {
	
	private int cryptoId;
	private String cryptoName;
	private int cryptoPrice;
	
	public CryptoCurrencyModel() {
		// TODO Auto-generated constructor stub
	}

	public CryptoCurrencyModel(int cryptoId, String cryptoName, int cryptoPrice) {
		super();
		this.cryptoId = cryptoId;
		this.cryptoName = cryptoName;
		this.cryptoPrice = cryptoPrice;
	}

	public int getCryptoId() {
		return cryptoId;
	}

	public void setCryptoId(int cryptoId) {
		this.cryptoId = cryptoId;
	}

	public String getCryptoName() {
		return cryptoName;
	}

	public void setCryptoName(String cryptoName) {
		this.cryptoName = cryptoName;
	}

	public int getCryptoPrice() {
		return cryptoPrice;
	}

	public void setCryptoPrice(int cryptoPrice) {
		this.cryptoPrice = cryptoPrice;
	}

}
