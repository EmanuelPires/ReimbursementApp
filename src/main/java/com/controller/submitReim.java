package com.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.ReimbursementDao;
import com.dao.ReimbursementDaoDB;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.models.Reimbursement;

public class submitReim {
	
	public static ReimbursementDao reimDao = new ReimbursementDaoDB();

	public static void submitReimbursement(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		
		System.out.println("Hello from Submit Controller");
		
		StringBuilder buffer = new StringBuilder();
		
		BufferedReader reader = req.getReader();
		
String line;
		
		while((line=reader.readLine())!=null) {
			buffer.append(line);
			buffer.append(System.lineSeparator());
		}
		
		
		String data = buffer.toString();
		System.out.println(data);
		
		ObjectMapper mapper= new ObjectMapper();
		JsonNode parsedObj = mapper.readTree(data);
		
	     Double amount	 = parsedObj.get("reimamount").asDouble();
	  
	     System.out.println("amount value as a Double is " + amount);
	    
	     BigDecimal reimAmount = BigDecimal.valueOf(amount);
	     String desc = parsedObj.get("description").asText();
	     int reimAuthor = parsedObj.get("reimauthor").asInt();
	    // int reimStatus = parsedObj.get("reimStatus").asInt();
	     int status =1;
	     int reimType = parsedObj.get("reimtype").asInt();
	     
	     Reimbursement reimb = new Reimbursement(reimAmount, desc, reimAuthor, status, reimType);
	     
	     try {
	    	 reimDao.submitReim(reimb);
	     }catch(Exception e) {
	    	 e.printStackTrace();
	     }
	}
}
