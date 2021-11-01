package com.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.ReimbursementDao;
import com.dao.ReimbursementDaoDB;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.models.Employees;
import com.models.Reimbursement;
import com.services.EmployeeServices;

public class Profile {
	public static ReimbursementDao reimDao = new ReimbursementDaoDB();
	public static EmployeeServices empServices = new EmployeeServices(reimDao);
	
	public static void getProfile(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession();
		
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode sesInfo = mapper.createObjectNode();
		
sesInfo.put("userId", session.getAttribute("id").toString());
		
		
		String curEmpId = session.getAttribute("id").toString();
		int curEmp = Integer.parseInt(curEmpId);
	
		//System.out.println("this is current user ID " + curEmp );
		Employees seshEmp =reimDao.getEmpInfo(curEmp);
		
		
			res.getWriter().write(new ObjectMapper().writeValueAsString(seshEmp));
		
		
			
			System.out.println("Not getting the reim list");
	}
	
	public static void proUpdate(HttpServletRequest req, HttpServletResponse res) throws IOException {
		System.out.println("Profile Update Starting");
		
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
		
		HttpSession session = req.getSession();
		ObjectMapper mapper1 = new ObjectMapper();
		ObjectNode sesInfo = mapper1.createObjectNode();
		sesInfo.put("userId", session.getAttribute("id").toString());
		String curEmpId = session.getAttribute("id").toString();
		int curEmp = Integer.parseInt(curEmpId);
		
		
		
	     String firstName	 = parsedObj.get("firstName").asText();
	     String lastName = parsedObj.get("lastName").asText();
	     String email = parsedObj.get("email").asText();
	     String username =parsedObj.get("username").asText();
	     String password = parsedObj.get("password").asText();
	     
	     
	     Employees emp = new Employees(curEmp, firstName, lastName, email, username, password);
	     
	     empServices.proUpdate(emp);
	     
	}

}
