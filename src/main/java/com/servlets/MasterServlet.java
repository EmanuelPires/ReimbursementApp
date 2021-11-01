package com.servlets;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MasterServlet extends HttpServlet  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3408654436126312353L;

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		Dispatcher.process(req, res);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
	Dispatcher.process(req, res);	
	}
}
