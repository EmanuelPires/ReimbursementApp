package com.servlets;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.controller.History;
import com.controller.Home;
import com.controller.LoginController;
import com.controller.Profile;
import com.controller.admin1;
import com.controller.admin2;
import com.controller.submitReim;

public class Dispatcher {
	
	public static void process(HttpServletRequest req, HttpServletResponse res) throws IOException {
		System.out.println("We're in the dispatcher going to " + req.getRequestURI());
		
		
		switch(req.getRequestURI()) {
		case"/ReimbursementApp/api/login":
			LoginController.login(req, res);
			break;
		case"/ReimbursementApp/api/reimbursements":
			Home.getEmpReimbursements(req, res);
			break;	
		case"/ReimbursementApp/api/reimrequest":
			submitReim.submitReimbursement(req, res);
			break;
		case"/ReimbursementApp/api/managerapprovals":
			admin1.getUnresolved(req, res);
			break;
		case"/ReimbursementApp/api/approve":
			admin1.reimApprove(req, res);
			break;
		case"/ReimbursementApp/api/deny":
			admin1.reimDeny(req, res);
			break;
		case"/ReimbursementApp/api/reimbursementhistory":
			History.getReimbursements(req, res);
			break;
		case"/ReimbursementApp/api/profile":
			Profile.getProfile(req, res);
			break;
		case"/ReimbursementApp/api/proupdate":
			Profile.proUpdate(req, res);
			break;
		case"/ReimbursementApp/api/resolved":
			admin2.getResolved(req, res);
			break;
			
		}
	}
	
	

}
