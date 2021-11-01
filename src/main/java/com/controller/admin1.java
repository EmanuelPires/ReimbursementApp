package com.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.ReimbursementDao;
import com.dao.ReimbursementDaoDB;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.models.Reimbursement;
import com.services.EmployeeServices;

public class admin1 {

	public static ReimbursementDao reimDao = new ReimbursementDaoDB();
	public static EmployeeServices empServices = new EmployeeServices(reimDao);
	
	
	public static void getUnresolved(HttpServletRequest req, HttpServletResponse res) throws IOException {
	HttpSession session = req.getSession();
		
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode sesInfo = mapper.createObjectNode();
		
		System.out.println("Getting Unresolved");
		
		if(session.getAttribute("id") == null) {
			res.setStatus(404);
			res.getWriter().println("User is not logged in");
			return;
		}
		if(req.getMethod().equals("GET")) {
			System.out.println("Getting reims from get Unresolved");
			List<Reimbursement>reimList = empServices.getUnresolved();
			res.getWriter().write(new ObjectMapper().writeValueAsString(reimList));
		}
		
else
			
			System.out.println("Not getting the reim list");
		
	}
	
	
	public static void reimApprove(HttpServletRequest req, HttpServletResponse res) throws IOException {
		System.out.println("Made it to approval function");
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = req.getReader();
		String line;
		while((line=reader.readLine())!=null) {
			buffer.append(line);
			buffer.append(System.lineSeparator());
		}
		String data = buffer.toString();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode parsedObj = mapper.readTree(data);
		
		int reimId = parsedObj.get("reimId").asInt();
		System.out.println("reim ID = " + reimId);
		try {
			empServices.approveReim(reimId);
		}catch(Exception e) {
			res.setStatus(403);
		}
		
	}
	
	
	
	
	public static void reimDeny(HttpServletRequest req, HttpServletResponse res) throws IOException {
		System.out.println("Made it to deny function");
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = req.getReader();
		String line;
		while((line=reader.readLine())!=null) {
			buffer.append(line);
			buffer.append(System.lineSeparator());
		}
		String data = buffer.toString();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode parsedObj = mapper.readTree(data);
		
		int reimId = parsedObj.get("reimId").asInt();
		System.out.println("reim ID = " + reimId);
		try {
			empServices.denyReim(reimId);
		}catch(Exception e) {
			res.setStatus(403);
		}
		
	}
	
}
