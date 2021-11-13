package com.saurabh.utils;

import java.io.IOException;

import HibernateSessionFactory.HibernateConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;




public class logoutservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public logoutservlet() {
        super();
    }

    
    
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("logoutservlet invoked");
		System.out.println("request.getSession(false).getAttributeNames()= "+request.getSession(false).getAttributeNames());
		System.out.println("request.getSession(false).getAttribute(\"username\")= "+request.getSession(false).getAttribute("username"));
		System.out.println("request.getSession(false).getId()= "+request.getSession(false).getId());
		System.out.println("request.getSession(false).getSessionContext()= "+request.getSession(false).getSessionContext());
		System.out.println("request.getSession(false).getServletContext()= "+request.getSession(false).getServletContext());
		System.out.println("request.getAttributeNames()= "+request.getAttributeNames());
		System.out.println("request.getRequestedSessionId()= "+request.getRequestedSessionId());
		
		request.getSession(false).invalidate();
		//HibernateConnection.getSessionfactory().close();
		response.sendRedirect("login.jsp");
		
	}

}
