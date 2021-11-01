package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.models.Employees;
import com.models.Reimbursement;
import com.utils.ConnectionUtil;

public class ReimbursementDaoDB implements ReimbursementDao {
	
	ConnectionUtil conUtil = ConnectionUtil.getConnectionUtil();
	
	public List<Reimbursement> getEmpReimbursments(Employees emp) throws SQLException {
		// TODO Auto-generated method stub
		
		Connection con = conUtil.getConnection();
		
		List<Reimbursement> reimList = new ArrayList<Reimbursement>();
		
		String sql = "SELECT * FROM reimbursements WHERE reimAuthor='" +emp.getId()+ "' AND reimstatus =1";
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery(sql);
		
		while(rs.next()) {
			reimList.add(new Reimbursement(rs.getInt(1), rs.getBigDecimal(2), rs.getTimestamp(3), rs.getTimestamp(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9)));
		}
		
		
		
		return reimList;
	}

	
	
	
	
	
	
	
	
	public List<Reimbursement> getAllReimbursements() throws SQLException {
		// TODO Auto-generated method stub
		
		Connection con = conUtil.getConnection();
		
		List<Reimbursement> reimList = new ArrayList<Reimbursement>();
		String sql = "SELECT * FROM reimbursements";
		Statement s = con.createStatement();
		ResultSet rs= s.executeQuery(sql);
		
		while(rs.next()) {
			reimList.add(new Reimbursement(rs.getInt(1), rs.getBigDecimal(2), rs.getTimestamp(3), rs.getTimestamp(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9)));
		}
		
		
		return reimList;
	}
	
	@Override
	public Employees getEmpInfo(String email) {
		 Employees emp = new Employees();
			
			Connection  con = conUtil.getConnection();
			
			System.out.println("Insde of ReimDaoDB");
			
			String sql = "SELECT * FROM employees WHERE employees.email='" +email+"'";
			
			try {
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(sql);
				
				
				while(rs.next()) {
				emp.setId(rs.getInt(1));
				emp.setFirstName(rs.getString(2));
				emp.setLastName(rs.getString(3));
				emp.setEmail(rs.getString(4));
				emp.setUsername(rs.getString(5));
				emp.setPassword(rs.getString(6));
				emp.setUserRole(rs.getInt(7));
				}
				
				
				return emp;
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
			return null;
	 }









	@Override
	public Employees getEmpInfo(int id) {
		// TODO Auto-generated method stub
		 Employees emp = new Employees();
			
			Connection  con = conUtil.getConnection();
			
			
			
			String sql = "SELECT * FROM employees WHERE employees.id='" +id+"'";
			
			try {
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(sql);
				
				
				while(rs.next()) {
				emp.setId(rs.getInt(1));
				emp.setFirstName(rs.getString(2));
				emp.setLastName(rs.getString(3));
				emp.setEmail(rs.getString(4));
				emp.setUsername(rs.getString(5));
				emp.setPassword(rs.getString(6));
				emp.setUserRole(rs.getInt(7));
				}
				
				
				return emp;
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
			return null;
	}









	@Override
	public void submitReim(Reimbursement reim) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = conUtil.getConnection();
		
		String sql = "Insert into reimbursements (reimamount, description, reimauthor, reimstatus, reimtype) values(?,?,?,?,?)";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setBigDecimal(1, reim.getAmount());
		ps.setString(2, reim.getDescription());
		ps.setInt(3, reim.getSubmitterId());
		ps.setInt(4, reim.getReimStatus());
		ps.setInt(5, reim.getReimType());
		
		ps.execute();
		
	}









	@Override
	public List<Reimbursement> getUnresolved() throws SQLException {
		// TODO Auto-generated method stub
		
		System.out.println("Inside reimDaoDB getting unresolved");
		Connection con = conUtil.getConnection();
		int status = 1;
		
		String sql="SELECT * FROM reimbursements WHERE reimstatus=1";
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery(sql);
		List<Reimbursement>reimList = new ArrayList<Reimbursement>();
		
		while(rs.next()){
			reimList.add(new Reimbursement(rs.getInt(1), rs.getBigDecimal(2), rs.getTimestamp(3), rs.getTimestamp(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9)));
		}
		//System.out.println(reimList.get(0));
		return reimList;
	}









	@Override
	public void approveReim(int id) throws SQLException {
		// TODO Auto-generated method stub
		
		Connection con = conUtil.getConnection();
		
		Statement s = con.createStatement();
		String sql = "UPDATE reimbursements SET reimstatus=2 WHERE id=" + id;
	
			s.execute(sql);	
		
		
		
	}









	public void denyReim(int id) throws SQLException {
		// TODO Auto-generated method stub
	Connection con = conUtil.getConnection();
		
		Statement s = con.createStatement();
		String sql = "UPDATE reimbursements SET reimstatus=3 WHERE id=" + id;
	
			s.execute(sql);	
		
		
	}









	@Override
	public List<Reimbursement> reimbursementHistory(Employees emp) throws SQLException {
		// TODO Auto-generated method stub
Connection con = conUtil.getConnection();
		
		List<Reimbursement> reimList = new ArrayList<Reimbursement>();
		
		String sql = "SELECT * FROM reimbursements WHERE reimAuthor='" +emp.getId()+ "' AND reimstatus !=1";
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery(sql);
		
		while(rs.next()) {
			reimList.add(new Reimbursement(rs.getInt(1), rs.getBigDecimal(2), rs.getTimestamp(3), rs.getTimestamp(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9)));
		}
		
		
		
		return reimList;
	}









	@Override
	public void proUpdate(Employees emp) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = conUtil.getConnection();
		String sql = "UPDATE employees SET firstname=?, lastname=?, email=?, username=?, password=? WHERE id=?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, emp.getFirstName());
		ps.setString(2, emp.getLastName());
		ps.setString(3, emp.getEmail());
		ps.setString(4, emp.getUsername());
		ps.setString(5, emp.getPassword());
		ps.setInt(6, emp.getId());
		ps.execute();
	}









	@Override
	public List<Reimbursement> getResolved() throws SQLException {
		// TODO Auto-generated method stub
		
		Connection con = conUtil.getConnection();
		String sql = "SELECT * FROM reimbursements WHERE reimstatus!=1";
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery(sql);
		List<Reimbursement>reimList = new ArrayList<Reimbursement>();
		
		while(rs.next()){
			reimList.add(new Reimbursement(rs.getInt(1), rs.getBigDecimal(2), rs.getTimestamp(3), rs.getTimestamp(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9)));
		}
		return reimList;
	}











}
