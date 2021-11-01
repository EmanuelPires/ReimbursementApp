package com.controller;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.ReimbursementDao;
import com.dao.ReimbursementDaoDB;
import com.dao.ReimbursementDao;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.models.Employees;
import com.services.EmployeeServices;

public class LoginController {
	
	public static ReimbursementDao reimDao = new ReimbursementDaoDB();
	public static EmployeeServices empServices = new EmployeeServices(reimDao);
	
	public static void login(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		StringBuilder buffer = new StringBuilder();
		
		BufferedReader reader = req.getReader();
		
		String line;
		
		while((line=reader.readLine())!=null) {
			buffer.append(line);
			buffer.append(System.lineSeparator());
		}
		
		
		String data = buffer.toString();
		//System.out.println(data);
		
		ObjectMapper mapper= new ObjectMapper();
		JsonNode parsedObj = mapper.readTree(data);
		
		
		String email = parsedObj.get("email").asText();
		String password = parsedObj.get("password").asText();
		
	//	System.out.println(email);
	//	System.out.println(password);
		
		try {
			//System.out.println("In Login Controller try block");
			Employees emp = empServices.signIn(email, password);
			//System.out.println(emp.toString());
			//System.out.println(emp.getId());
			req.getSession().setAttribute("id", emp.getId());
			
			res.setStatus(200);
			res.getWriter().write(new ObjectMapper().writeValueAsString(emp));
		}catch(Exception e) {
			res.setStatus(403);
			res.getWriter().print("Username or password incorrect");
		}
		
		
		
	}

}
