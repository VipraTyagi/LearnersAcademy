package com.saurabh.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import com.saurabh.models.User;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;




public class loginservlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
       
    
    public loginservlet() {
        super();
       
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		System.out.println("loginservlet do post method execution");
		
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		Session session=cfg.buildSessionFactory().openSession();
		
		List<User> userlist=session.createQuery("from User", User.class).getResultList();
		int x=0;
		
		session.getSessionFactory().close();
		session.close();
		
		for(User u:userlist)
		{
			if(u.getUsername().equals(username))
				if(u.getPassword().equals(password))
				{
					x++;
					request.getSession().setAttribute("username", username);
					request.getSession().setAttribute("firstname", u.getFirstname());
				}
		}
		if(x==1)
		{
			request.getRequestDispatcher("home.jsp").forward(request, response);
		}
		else
		{
			request.getRequestDispatcher("login.jsp").include(request, response);
			out.println("<script>");
			out.println("window.onload=function myfunc() {document.getElementById('invalidspan').innerHTML=\"<h5 align='center' style='color:red'>Invalid Credentials!!!</h5>\";};");
			out.println("</script>");
		}
		
		
	
	}
	


}
