package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.ReimbursementDao;
import com.dao.ReimbursementDaoDB;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.models.Reimbursement;
import com.services.EmployeeServices;

public class admin2 {
	
	public static ReimbursementDao reimDao = new ReimbursementDaoDB();
	public static EmployeeServices empServices = new EmployeeServices(reimDao);

	public static void getResolved(HttpServletRequest req, HttpServletResponse res) throws IOException {
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
			List<Reimbursement>reimList = empServices.getResolved();
			res.getWriter().write(new ObjectMapper().writeValueAsString(reimList));
		}
		
else
			
			System.out.println("Not getting the reim list");
	}
}
