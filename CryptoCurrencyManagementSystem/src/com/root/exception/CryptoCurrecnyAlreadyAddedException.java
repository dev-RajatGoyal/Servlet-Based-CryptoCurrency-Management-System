package com.root.exception;

public class CryptoCurrecnyAlreadyAddedException extends Exception {

	@Override
	public String getMessage() {
		
		return "Cryptocurrency already exists";
	}

}
