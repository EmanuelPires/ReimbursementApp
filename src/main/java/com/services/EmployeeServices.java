package com.services;

import java.sql.SQLException;
import java.util.List;

import com.dao.ReimbursementDao;
import com.dao.ReimbursementDao;
import com.exceptions.UserDoesNotExist;
import com.logging.Logging;
import com.models.Employees;
import com.models.Reimbursement;

public class EmployeeServices {
private ReimbursementDao reimDao;
	
	public EmployeeServices(ReimbursementDao r) {
		this.reimDao = r;
	}
	
	public Employees profile(int id) {
		System.out.println("Getting Employee Info");
		
		Employees emp = reimDao.getEmpInfo(id);
		return emp;
	}
	
	public Employees signIn(String email, String password) throws UserDoesNotExist{
		
		System.out.println("In Employee Services Sign In Method");
		
		Employees emp = reimDao.getEmpInfo(email);
		
		if(emp.getId()==0) {
			Logging.logger.warn("Username does not exist");
			throw new UserDoesNotExist();
		}else if(emp.getPassword().equals(password)) {
			return emp;
		}
		
		return null;
	}
	

	public List<Reimbursement> getEmpReimbursements(Employees emp){
		
		
		try {
			return reimDao.getEmpReimbursments(emp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	public List<Reimbursement>getUnresolved(){
		System.out.println("In Emp Services getting unresolved");
		try {
			return reimDao.getUnresolved();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public void approveReim(int id) {
		try {
			reimDao.approveReim(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void denyReim(int id) {
		try {
			reimDao.denyReim(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
public List<Reimbursement> reimbursementHistory(Employees emp){
		
		
		try {
			return reimDao.reimbursementHistory(emp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

public List<Reimbursement> getResolved(){
	try {
		return reimDao.getResolved();
	}catch(SQLException e) {
		e.printStackTrace();
	}
	return null;
}

public void proUpdate(Employees emp) {
	try {
		reimDao.proUpdate(emp);
	}catch(SQLException e) {
		e.printStackTrace();
	}
}
}
