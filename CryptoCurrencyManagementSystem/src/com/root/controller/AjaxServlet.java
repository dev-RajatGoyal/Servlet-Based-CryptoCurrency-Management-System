package com.root.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AjaxServlet
 */
 
public class AjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String cryptoName = request.getParameter("CryptoName");
		if(cryptoName!=null)
		{
			response.setContentType("text/html");
			response.getWriter().write(generateJSONData(cryptoName));
		}
	}
	
	public String generateJSONData(String CryptoName)
	{
		StringBuffer returnName = null;
		if(CryptoName.equals("cyrptocurrency")) {
			
			returnName = new StringBuffer("{\"topic\":{");
			returnName.append("\"Name\":\"CryptoCurrency\",");
			returnName.append("\"Names\":[");
			returnName.append("{\"name\":\"BitCoin\"},");
			returnName.append("{\"name\":\"Ethereum\"},");
			returnName.append("{\"name\":\"BinanceCoin\"},");
			returnName.append("{\"name\":\"Tether\"}],");
			returnName.append("}}");
		}
		else {
			
			returnName = new StringBuffer("{\"topic\":{");
			returnName.append("\"name\":\"Topic:"+CryptoName+"\",");
			returnName.append("\"Names\":[");
			returnName.append("{\"name\":\"no cryptocurrency found\"},");
			returnName.append("}}");
			
		}
		return returnName.toString();
	}

}
