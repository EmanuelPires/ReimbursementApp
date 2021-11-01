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
import com.models.Employees;
import com.models.Reimbursement;
import com.services.EmployeeServices;

public class History {
	public static ReimbursementDao reimDao = new ReimbursementDaoDB();
	public static EmployeeServices empServices = new EmployeeServices(reimDao);
	
	
	


	public static void getReimbursements(HttpServletRequest req, HttpServletResponse res) throws IOException {
		// TODO Auto-generated method stub
HttpSession session = req.getSession();
		
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode sesInfo = mapper.createObjectNode();
		
		
		
		if(session.getAttribute("id") == null) {
			res.setStatus(404);
			res.getWriter().println("User is not logged in");
			return;
		}
		
		sesInfo.put("userId", session.getAttribute("id").toString());
		
		
		String curEmpId = session.getAttribute("id").toString();
		int curEmp = Integer.parseInt(curEmpId);
	
		//System.out.println("this is current user ID " + curEmp );
		Employees seshEmp =reimDao.getEmpInfo(curEmp);
		

		if(req.getMethod().equals("GET")) {
			List<Reimbursement> reimList = empServices.reimbursementHistory(seshEmp);
			//System.out.println("reimList item one" + reimList.get(1));
			res.getWriter().write(new ObjectMapper().writeValueAsString(reimList));
			//res.getWriter().write(new ObjectMapper().writeValueAsString(seshEmp));
		}
		
		else
			
			System.out.println("Not getting the reim list");
	}

	
	
	
	public static void getResolved(HttpServletRequest req, HttpServletResponse res) throws IOException {
		// TODO Auto-generated method stub
HttpSession session = req.getSession();
		
		
		
		if(session.getAttribute("id") == null) {
			res.setStatus(404);
			res.getWriter().println("User is not logged in");
			return;
		}
		
		

		if(req.getMethod().equals("GET")) {
			List<Reimbursement> reimList = empServices.getResolved();
			//System.out.println("reimList item one" + reimList.get(1));
			res.getWriter().write(new ObjectMapper().writeValueAsString(reimList));
			//res.getWriter().write(new ObjectMapper().writeValueAsString(seshEmp));
		}
		
		else
			
			System.out.println("Not getting the reim list");
	}
	
}
