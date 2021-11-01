package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.models.Employees;
import com.models.Reimbursement;

public interface ReimbursementDao {
List<Reimbursement> getEmpReimbursments(Employees emp)throws SQLException;
	
List<Reimbursement> reimbursementHistory(Employees emp)throws SQLException;
	
	List<Reimbursement> getAllReimbursements() throws SQLException;
	
	Employees getEmpInfo(String email);
	
	Employees getEmpInfo(int id);
	
	void submitReim(Reimbursement reim) throws SQLException;
	
	void approveReim(int id) throws SQLException;
	void denyReim(int id) throws SQLException;


	List<Reimbursement> getUnresolved() throws SQLException;
	
	void proUpdate(Employees emp) throws SQLException;

	List<Reimbursement> getResolved() throws SQLException;
	
}
