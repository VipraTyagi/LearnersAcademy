package com.saurabh.utils;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.query.criteria.internal.expression.function.CurrentDateFunction;
import org.hibernate.query.criteria.internal.expression.function.CurrentTimestampFunction;

import com.saurabh.models.class_base;
import com.saurabh.models.class_students;
import com.saurabh.models.class_subjects;
import com.saurabh.models.class_teachers;
import com.saurabh.models.student;
import com.saurabh.models.subject;
import com.saurabh.models.teacher;

import HibernateSessionFactory.HibernateConnection;

public class application extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public application() {
		super();

	}

	protected void add_student(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		System.out.println("add_student method invoked");

		

		student s1 = new student();
		s1.setName(request.getParameter("student_name"));
		s1.setDOB(request.getParameter("student_dob"));
		Date d = new Date();
		s1.setId(String.valueOf(d.getTime()));
		s1.setCity(request.getParameter("student_city"));
		s1.setPhoneno(request.getParameter("student_phoneno"));

		Session session = HibernateConnection.getSessionfactory().openSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		session.save(s1);

		transaction.commit();
		// session.getTransaction().commit();
		session.close();
		
		
		request.getRequestDispatcher("/dashboard.jsp").include(request, response);
		
		out.println("<script type=\"text/javascript\">\r\n" + "        $(window).on('load', function () {\r\n"
				+ "            $('#addedstudent').modal('show');\r\n" + "        });\r\n" + "    </script>\r\n"
				+ "\r\n"
				+ "    <div class=\"modal fade\" id=\"addedstudent\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"exampleModalCenterTitle\" aria-hidden=\"true\">\r\n"
				+ "        <div class=\"modal-dialog modal-dialog-centered modal-dialog-scrollable\" role=\"document\">\r\n"
				+ "            <div class=\"modal-content\">\r\n" + "                <div class=\"modal-header\">\r\n"
				+ "                    <h5 class=\"modal-title\" id=\"exampleModalLongTitle\">Removal Details</h5>\r\n"
				+ "\r\n" + "                </div>\r\n" + "                <div class=\"modal-body\">\r\n");
		
		out.println("<br/><span style='color:green'><strong>Student Added!</strong></span> You should make a note of the Student-ID---><span style='color:red'>"+s1.getId()+"</span><br/>");
		out.println("Student Name - <span style='color:green'>"+s1.getName()+"</span><br/>");
		out.println("Student DOB - <span style='color:green'>"+s1.getDOB()+"</span><br/>");
		out.println("Student City - <span style='color:green'>"+s1.getCity()+"</span><br/>");
		out.println("Student Phone No - <span style='color:green'>"+s1.getPhoneno()+"</span><br/>");
		
		out.println("<div class=\"modal-footer\">\r\n"
				+ "                            <button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\">OK</button>\r\n"
				+ "                        </div>\r\n" + "\r\n" + "                </div>\r\n" + "\r\n"
				+ "            </div>\r\n" + "\r\n" + "\r\n" + "        </div>\r\n" + "    </div>");

		
		

	}

	protected void remove_student(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		System.out.println("remove_student method invoked");

		
		Session session = HibernateConnection.getSessionfactory().openSession();
		session.beginTransaction();
		String[] removestudents_ids = request.getParameterValues("removestudents");
		int no = 0;
		List<student> list = new ArrayList<>();
		list = student.getallstudents();
		for (String s : removestudents_ids) {
			//student s1=session.get(student.class, s);
			//session.delete("student", s1);
			no = no + session.createNativeQuery("Delete from student where student_id='" + s + "'").executeUpdate();
			session.createNativeQuery("delete from class_students where student_id='" +s+"'").executeUpdate();
		}
		session.getTransaction().commit();
		System.out.println(no + " Removals done");

		session.close();
		

		RequestDispatcher rd = request.getRequestDispatcher("/dashboard.jsp");
		System.out.println("request.getRequestURI()= " + request.getRequestURI());
		System.out.println("request.getRequestURL()= " + request.getRequestURL());
		rd.include(request, response);

		out.println("<script type=\"text/javascript\">\r\n" + "        $(window).on('load', function () {\r\n"
				+ "            $('#resultremovestudent').modal('show');\r\n" + "        });\r\n" + "    </script>\r\n"
				+ "\r\n"
				+ "    <div class=\"modal fade\" id=\"resultremovestudent\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"exampleModalCenterTitle\" aria-hidden=\"true\">\r\n"
				+ "        <div class=\"modal-dialog modal-dialog-centered modal-dialog-scrollable\" role=\"document\">\r\n"
				+ "            <div class=\"modal-content\">\r\n" + "                <div class=\"modal-header\">\r\n"
				+ "                    <h5 class=\"modal-title\" id=\"exampleModalLongTitle\">Removal Details</h5>\r\n"
				+ "\r\n" + "                </div>\r\n" + "                <div class=\"modal-body\">\r\n");

		for (String e : removestudents_ids) {
			for (student s : list) {
				if (e.equals(s.getId())) {
					out.println("<p><span style='font-weight:bold'>Student:</span> " + s.getName()
							+ "  <br/><span style='font-weight:bold'>Student DOB:</span>  " + s.getDOB()
							+ " <br/><span style='font-weight:bold'>Student City:</span>  " + s.getCity()
							+ " <br/><span style='font-weight:bold'>Student Phone No:</span> " + s.getPhoneno()
							+ " <br/><span style='font-weight:bold;color:crimson;'>removed from database</span></p>");
				}
			}
		}

		out.println("<div class=\"modal-footer\">\r\n"
				+ "                            <button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\">OK</button>\r\n"
				+ "                        </div>\r\n" + "\r\n" + "                </div>\r\n" + "\r\n"
				+ "            </div>\r\n" + "\r\n" + "\r\n" + "        </div>\r\n" + "    </div>");

	}

	protected void add_teacher(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		System.out.println("add_teacher method invoked");

		

		teacher s1 = new teacher();
		s1.setName(request.getParameter("teacher_name"));
		s1.setDOB(request.getParameter("teacher_dob"));
		Date d = new Date();
		s1.setId(String.valueOf(d.getTime()));
		s1.setCity(request.getParameter("teacher_city"));
		s1.setPhoneno(request.getParameter("teacher_phoneno"));

		Session session = HibernateConnection.getSessionfactory().openSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		session.save(s1);

		transaction.commit();
		// session.getTransaction().commit();
		session.close();
		

		out.println("<div class=\"alert alert-success alert-dismissible fade show\" role=\"alert\">\r\n"
				+ "  <strong>Teacher Added!</strong> You should make a note of the Teacher-ID--->" + s1.getId()
				+ "<br />\r\n" + "  Teacher Name - " + s1.getName() + "  Teacher DOB - " + s1.getDOB()
				+ "  Teacher City - " + s1.getCity() + " Teacher Phone No - " + s1.getPhoneno() + "\r\n"
				+ "  <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"alert\" aria-label=\"Close\"></button>\r\n"
				+ "</div>");

		request.getRequestDispatcher("/dashboard.jsp").include(request, response);

	}
	
	protected void remove_teacher(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		System.out.println("remove_teacher method invoked");

		
		Session session = HibernateConnection.getSessionfactory().openSession();
		session.beginTransaction();
		
		String[] removeteachers_ids = request.getParameterValues("removeteachers");
		int no = 0;
		List<teacher> list = new ArrayList<>();
		List<class_teachers> classteacherlist=new ArrayList<>();
		list = teacher.getallteachers();
		for (String s : removeteachers_ids) {
			no = no + session.createNativeQuery("Delete from teacher where teacher_id='" + s + "'").executeUpdate();
			classteacherlist=session.createNativeQuery("Select * from class_teachers where teacher_id='"+s+"'", class_teachers.class).getResultList();
			for(class_teachers ct:classteacherlist)
			{
				session.createNativeQuery("update class_subjects set teacher_alloted='false' where class_id='"+ct.getAssigned_class_id()+"' and subject_id='"+ct.getTeaches_subject_id()+"'").executeUpdate();
			}
			
			session.createNativeQuery("delete from class_teachers where teacher_id='" +s+"'");
		}
		
		session.getTransaction().commit();
		System.out.println(no + " Removals done");
		session.close();
		

		RequestDispatcher rd = request.getRequestDispatcher("/dashboard.jsp");
		System.out.println("request.getRequestURI()= " + request.getRequestURI());
		System.out.println("request.getRequestURL()= " + request.getRequestURL());
		rd.include(request, response);

		out.println("<script type=\"text/javascript\">\r\n" + "        $(window).on('load', function () {\r\n"
				+ "            $('#resultremoveteacher').modal('show');\r\n" + "        });\r\n" + "    </script>\r\n"
				+ "\r\n"
				+ "    <div class=\"modal fade\" id=\"resultremoveteacher\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"exampleModalCenterTitle\" aria-hidden=\"true\">\r\n"
				+ "        <div class=\"modal-dialog modal-dialog-centered modal-dialog-scrollable\" role=\"document\">\r\n"
				+ "            <div class=\"modal-content\">\r\n" + "                <div class=\"modal-header\">\r\n"
				+ "                    <h5 class=\"modal-title\" id=\"exampleModalLongTitle\">Removal Details</h5>\r\n"
				+ "\r\n" + "                </div>\r\n" + "                <div class=\"modal-body\">\r\n");

		for (String e : removeteachers_ids) {
			for (teacher s : list) {
				if (e.equals(s.getId())) {
					out.println("<p><span style='font-weight:bold'>Teacher:</span> " + s.getName()
							+ "  <br/><span style='font-weight:bold'>Teacher DOB:</span>  " + s.getDOB()
							+ " <br/><span style='font-weight:bold'>Teacher City:</span>  " + s.getCity()
							+ " <br/><span style='font-weight:bold'>Teacher Phone No:</span> " + s.getPhoneno()
							+ " <br/><span style='font-weight:bold;color:crimson;'>removed from database</span></p>");
				}
			}
		}

		out.println("<div class=\"modal-footer\">\r\n"
				+ "                            <button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\">OK</button>\r\n"
				+ "                        </div>\r\n" + "\r\n" + "                </div>\r\n" + "\r\n"
				+ "            </div>\r\n" + "\r\n" + "\r\n" + "        </div>\r\n" + "    </div>");

	}
	
	
	protected void add_subject(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		System.out.println("add_subject method invoked");

		

		subject s1 = new subject();
		s1.setName(request.getParameter("subject_name"));
		s1.setDomain(request.getParameter("subject_domain"));
		Date d = new Date();
		s1.setId(String.valueOf(d.getTime()));
		
		Session session = HibernateConnection.getSessionfactory().openSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		session.save(s1);

		transaction.commit();
		// session.getTransaction().commit();
		session.close();
		

		out.println("<div class=\"alert alert-success alert-dismissible fade show\" role=\"alert\">\r\n"
				+ "  <strong>Subject Added!</strong> You should make a note of the Subject-ID--->" + s1.getId()
				+ "<br />\r\n" + "  Subject Name - " + s1.getName() + "  Subject Domain - " + s1.getDomain()
				+ "  <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"alert\" aria-label=\"Close\"></button>\r\n"
				+ "</div>");

		request.getRequestDispatcher("/dashboard.jsp").include(request, response);

	}
	
	
	protected void remove_subject(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		System.out.println("remove_subject method invoked");

		
		Session session = HibernateConnection.getSessionfactory().openSession();
		session.beginTransaction();
		String[] removesubjects_ids = request.getParameterValues("removesubjects");
		int no = 0;
		List<subject> list = new ArrayList<>();
		list = subject.getallsubjects();
		for (String s : removesubjects_ids) {
			no = no + session.createNativeQuery("Delete from subject where subject_id='" + s + "'").executeUpdate();
			session.createNativeQuery("delete from class_subjects where subject_id='"+s+"'").executeUpdate();
			session.createNativeQuery("delete from class_teachers where teaches_subject_id='"+s+"'").executeUpdate();
		}
		session.getTransaction().commit();
		System.out.println(no + " Removals done");

		session.close();
		

		RequestDispatcher rd = request.getRequestDispatcher("/dashboard.jsp");
		System.out.println("request.getRequestURI()= " + request.getRequestURI());
		System.out.println("request.getRequestURL()= " + request.getRequestURL());
		rd.include(request, response);

		out.println("<script type=\"text/javascript\">\r\n" + "        $(window).on('load', function () {\r\n"
				+ "            $('#resultremovesubject').modal('show');\r\n" + "        });\r\n" + "    </script>\r\n"
				+ "\r\n"
				+ "    <div class=\"modal fade\" id=\"resultremovesubject\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"exampleModalCenterTitle\" aria-hidden=\"true\">\r\n"
				+ "        <div class=\"modal-dialog modal-dialog-centered modal-dialog-scrollable\" role=\"document\">\r\n"
				+ "            <div class=\"modal-content\">\r\n" + "                <div class=\"modal-header\">\r\n"
				+ "                    <h5 class=\"modal-title\" id=\"exampleModalLongTitle\">Removal Details</h5>\r\n"
				+ "\r\n" + "                </div>\r\n" + "                <div class=\"modal-body\">\r\n");

		for (String e : removesubjects_ids) {
			for (subject s : list) {
				if (e.equals(s.getId())) {
					out.println("<p><span style='font-weight:bold'>Subject:</span> " + s.getName()
							+ "  <br/><span style='font-weight:bold'>Subject Domain:</span>  " + s.getDomain()
							+ " <br/><span style='font-weight:bold;color:crimson;'>removed from database</span></p>");
				}
			}
		}

		out.println("<div class=\"modal-footer\">\r\n"
				+ "                            <button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\">OK</button>\r\n"
				+ "                        </div>\r\n" + "\r\n" + "                </div>\r\n" + "\r\n"
				+ "            </div>\r\n" + "\r\n" + "\r\n" + "        </div>\r\n" + "    </div>");

	}
	
	
	protected void add_class(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		System.out.println("add_class method invoked");

		

		class_base s1 = new class_base();
		s1.setName(request.getParameter("class_name"));
		Date d = new Date();
		s1.setId(String.valueOf(d.getTime()));
		
		Session session = HibernateConnection.getSessionfactory().openSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		session.save(s1);

		transaction.commit();
		// session.getTransaction().commit();
		session.close();
		

		out.println("<div class=\"alert alert-success alert-dismissible fade show\" role=\"alert\">\r\n"
				+ "  <strong>Class Added!</strong> You should make a note of the Class-ID--->" + s1.getId()
				+ "<br />\r\n" + "  Class Name - " + s1.getName()
				+ "  <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"alert\" aria-label=\"Close\"></button>\r\n"
				+ "</div>");

		request.getRequestDispatcher("/dashboard.jsp").include(request, response);

	}
	
	
	protected void remove_class(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		System.out.println("remove_subject method invoked");

		
		Session session = HibernateConnection.getSessionfactory().openSession();
		session.beginTransaction();
		String[] removeclasses_ids = request.getParameterValues("removeclasses");
		int no = 0;
		List<class_base> list = new ArrayList<>();
		list = class_base.getallclasses();
		for (String s : removeclasses_ids) {
			no = no + session.createNativeQuery("Delete from class where class_id='" + s + "'").executeUpdate();
			session.createNativeQuery("delete from class_students where class_id='"+s+"'").executeUpdate();
			session.createNativeQuery("delete from class_subjects where class_id='"+s+"'").executeUpdate();
			session.createNativeQuery("delete from class_teachers where assigned_class_id='"+s+"'").executeUpdate();
		}
		session.getTransaction().commit();
		System.out.println(no + " Removals done");

		session.close();
		

		RequestDispatcher rd = request.getRequestDispatcher("/dashboard.jsp");
		System.out.println("request.getRequestURI()= " + request.getRequestURI());
		System.out.println("request.getRequestURL()= " + request.getRequestURL());
		rd.include(request, response);

		out.println("<script type=\"text/javascript\">\r\n" + "        $(window).on('load', function () {\r\n"
				+ "            $('#resultremoveclass').modal('show');\r\n" + "        });\r\n" + "    </script>\r\n"
				+ "\r\n"
				+ "    <div class=\"modal fade\" id=\"resultremoveclass\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"exampleModalCenterTitle\" aria-hidden=\"true\">\r\n"
				+ "        <div class=\"modal-dialog modal-dialog-centered modal-dialog-scrollable\" role=\"document\">\r\n"
				+ "            <div class=\"modal-content\">\r\n" + "                <div class=\"modal-header\">\r\n"
				+ "                    <h5 class=\"modal-title\" id=\"exampleModalLongTitle\">Removal Details</h5>\r\n"
				+ "\r\n" + "                </div>\r\n" + "                <div class=\"modal-body\">\r\n");

		for (String e : removeclasses_ids) {
			for (class_base s : list) {
				if (e.equals(s.getId())) {
					out.println("<p><span style='font-weight:bold'>Class:</span> " + s.getName()
							+ " <br/><span style='font-weight:bold;color:crimson;'>removed from database</span></p>");
				}
			}
		}

		out.println("<div class=\"modal-footer\">\r\n"
				+ "                            <button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\">OK</button>\r\n"
				+ "                        </div>\r\n" + "\r\n" + "                </div>\r\n" + "\r\n"
				+ "            </div>\r\n" + "\r\n" + "\r\n" + "        </div>\r\n" + "    </div>");

	}
	
	protected void assign_students_to_class(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		System.out.println("assign_students_to_class method invoked");
		
		
		Session session = HibernateConnection.getSessionfactory().openSession();
		Transaction transaction=session.beginTransaction();
		
		String[] assign_student_ids=request.getParameterValues("assign_students");
		String selected_class_id=request.getParameter("selected_class_for_students");
		
		for(String st_id:assign_student_ids)
		{
			 class_students c1=new class_students(selected_class_id,st_id);
			 session.save(c1);
		}
		
		
		transaction.commit();
//		session.close(); ---->shifted below because required
		
		RequestDispatcher rd = request.getRequestDispatcher("/dashboard.jsp");
		System.out.println("request.getRequestURI()= " + request.getRequestURI());
		System.out.println("request.getRequestURL()= " + request.getRequestURL());
		rd.include(request, response);
		
		out.println("<script type=\"text/javascript\">\r\n" + "        $(window).on('load', function () {\r\n"
				+ "            $('#resultassignstudentstoclass').modal('show');\r\n" + "        });\r\n" + "    </script>\r\n"
				+ "\r\n"
				+ "    <div class=\"modal fade\" id=\"resultassignstudentstoclass\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"exampleModalCenterTitle\" aria-hidden=\"true\">\r\n"
				+ "        <div class=\"modal-dialog modal-dialog-centered modal-dialog-scrollable\" role=\"document\">\r\n"
				+ "            <div class=\"modal-content\">\r\n" + "                <div class=\"modal-header\">\r\n"
				+ "                    <h5 class=\"modal-title\" id=\"exampleModalLongTitle\">Student Assignment Details</h5>\r\n"
				+ "\r\n" + "                </div>\r\n" + "                <div class=\"modal-body\">\r\n");
		
		String classname=session.get(class_base.class,selected_class_id).getName();
		
		for (String e : assign_student_ids) 
		{
			student s1=session.get(student.class,e);
			
					out.println("<p><span style='font-weight:bold'>Student:</span> " + s1.getName()+"<br/><span style='font-weight:bold'>City:</span> "+ s1.getCity() + "<br/><span style='font-weight:bold'>Phone:</span> " + s1.getPhoneno()
							+ "<br/><span style='font-weight:bold;color:green;'>Assigned to Class-></span><span style='font-weight:bold;color:blue;'>"+classname+"</span></p>");
		}
		
		session.close();
		
		
		out.println("<div class=\"modal-footer\">\r\n"
				+ "                            <button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\">OK</button>\r\n"
				+ "                        </div>\r\n" + "\r\n" + "                </div>\r\n" + "\r\n"
				+ "            </div>\r\n" + "\r\n" + "\r\n" + "        </div>\r\n" + "    </div>");
		
		
	}
	
	protected void assign_subjects_to_class_1(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		System.out.println("assign_students_to_class_1 method invoked");
		
		
		Session session = HibernateConnection.getSessionfactory().openSession();
		Transaction transaction=session.beginTransaction();
		
		String selectedclassid=request.getParameter("selected_class_for_subjects");
		List<class_subjects> csublist=class_subjects.getallclass_subjects();
		List<String> alreadyallocatedsubjectsids=new ArrayList<>();
		List<String> suitablesubjectsids=new ArrayList<>();
		List<subject> subobjlist=subject.getallsubjects();
		
		request.getSession().setAttribute("selectedclassforsubjects", selectedclassid);
		
		for(class_subjects csub:csublist)
		{
			if(csub.getClass_id().equals(selectedclassid))
			{
				alreadyallocatedsubjectsids.add(csub.getSubject_id());
			}
		}
		for(subject s:subobjlist)
		{
			int x=0;
			for(String sub_id:alreadyallocatedsubjectsids)
			{
				if(s.getId().equals(sub_id))
				{
					x++;
				}
			}
			if(x==0)
			suitablesubjectsids.add(s.getId());
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/dashboard.jsp");
		System.out.println("request.getRequestURI()= " + request.getRequestURI());
		System.out.println("request.getRequestURL()= " + request.getRequestURL());
		rd.include(request, response);
		
		out.println("<script type=\"text/javascript\">\r\n" + "        $(window).on('load', function () {\r\n"
				+ "            $('#assignsubjectstoclass2').modal('show');\r\n" + "        });\r\n" + "    </script>\r\n"
				+ "\r\n"
				+ "    <div class=\"modal fade\" id=\"assignsubjectstoclass2\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"exampleModalCenterTitle\" aria-hidden=\"true\">\r\n"
				+ "        <div class=\"modal-dialog modal-dialog-centered modal-dialog-scrollable\" role=\"document\">\r\n"
				+ "            <div class=\"modal-content\">\r\n" + "                <div class=\"modal-header\">\r\n"
				+ "                    <h5 class=\"modal-title\" id=\"exampleModalLongTitle\">Assign Subjects to Class</h5>\r\n"
				+ "\r\n" + "                </div>\r\n" + "                <div class=\"modal-body\">\r\n");
		
		class_base selecclass=session.get(class_base.class, selectedclassid);
		
		out.println("<form action='/practise/application/assign_subjects_to_class_2'>");
		out.println("<p>Select from the following Un-allocated subjects, to be assigned to the class <span style='font-weight:bold;color:blue;'>"+selecclass.getName()+"</span></p><br />");
		out.println("<table class=\"table table-dark table-striped\">\r\n"
				+ "     <thead>"
				+ "		<tr>\r\n"
				+ "		<th scope=\"col\">Select Here</th><th scope=\"col\">Subject Name</th><th scope=\"col\">Subject Domain</th>\r\n"
				+ "		</tr>\r\n"
				+ "      </thead>\r\n"
				+ "      <tbody>\r\n");
		
		for(String s:suitablesubjectsids)
			{
				subject subject=session.get(subject.class, s);
					out.println("<tr>");
					out.println("<td style='text-align:center'><input type='checkbox' name=assign_selected_subjects value='"+subject.getId()+"'</td><td style='text-align:left;'>"+subject.getName()+"</td><td>"+subject.getDomain()+"</td>");
					out.println("</tr>");
				
			}

		out.println("</tbody>");
		out.println("</table>");
		out.println("<div class=\"modal-footer\">\r\n"
				+ "                            <button type=\"submit\" class=\"btn btn-primary\">Assign Selected Subjects</button>\r\n"
				+ "                            <button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\">Cancel</button>\r\n"
				+ "                        </div>\r\n"
				+ "                        </form>\r\n"
				+ "                        \r\n"
				+ "						\r\n"
				+ "                </div>\r\n"
				+ "\r\n"
				+ "            </div>\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "        </div>\r\n"
				+ "    </div>");
		
		
		transaction.commit();
		session.close();
		
		
		
	}
	
	protected void assign_subjects_to_class_2(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		System.out.println("assign_students_to_class_2 method invoked");
		
		
		Session session = HibernateConnection.getSessionfactory().openSession();
		Transaction transaction=session.beginTransaction();
		
		String[] subjectidsforclass=request.getParameterValues("assign_selected_subjects");
		String class_id=(String)request.getSession().getAttribute("selectedclassforsubjects");
		
		class_base c1=session.get(class_base.class, class_id);
		
		for(String sbid:subjectidsforclass)
		{
			class_subjects clsub=new class_subjects(class_id, sbid, "false");
			session.save("class_subjects", clsub);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/dashboard.jsp");
		System.out.println("request.getRequestURI()= " + request.getRequestURI());
		System.out.println("request.getRequestURL()= " + request.getRequestURL());
		rd.include(request, response);
		
		out.println("<script type=\"text/javascript\">\r\n" + "        $(window).on('load', function () {\r\n"
				+ "            $('#resultassignsubjectstoclassfinal').modal('show');\r\n" + "        });\r\n" + "    </script>\r\n"
				+ "\r\n"
				+ "    <div class=\"modal fade\" id=\"resultassignsubjectstoclassfinal\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"exampleModalCenterTitle\" aria-hidden=\"true\">\r\n"
				+ "        <div class=\"modal-dialog modal-dialog-centered modal-dialog-scrollable\" role=\"document\">\r\n"
				+ "            <div class=\"modal-content\">\r\n" + "                <div class=\"modal-header\">\r\n"
				+ "                    <h5 class=\"modal-title\" id=\"exampleModalLongTitle\">Subject Assignment Details</h5>\r\n"
				+ "\r\n" + "                </div>\r\n" + "                <div class=\"modal-body\">\r\n");

					for(String sbid:subjectidsforclass)
					{
						subject s1=session.get(subject.class, sbid);
						out.println("<span style='font-weight:bold'>Subject: </span> " + s1.getName() +"<br/>"
						+"<span style='font-weight:bold'>Domain: </span> " + s1.getDomain() +"<br/>"
						+ " <span style='font-weight:bold;color:green;'>Assigned to Class: "+c1.getName()+"</span><br/><br/>");
						
					}
		out.println("<div class=\"modal-footer\">\r\n"
				+ "                            <button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\">OK</button>\r\n"
				+ "                        </div>\r\n" + "\r\n" + "                </div>\r\n" + "\r\n"
				+ "            </div>\r\n" + "\r\n" + "\r\n" + "        </div>\r\n" + "    </div>");
		
		
		transaction.commit();
		session.close();
		
		
	}
	
	
	
	protected void assign_teacher_to_class_1(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		System.out.println("assign_teacher_to_class_1 method invoked");
		
		
		
		Session session = HibernateConnection.getSessionfactory().openSession();
		Transaction transaction=session.beginTransaction();
		
		String selectedclassid=request.getParameter("selected_class_for_teacher");
		List<class_subjects> eligibleclasssubjectlist=session.createNativeQuery("select * from class_subjects where class_id='"+selectedclassid+"' and teacher_alloted!='true'",class_subjects.class).getResultList();
		List<teacher> teacherlist=teacher.getallteachers();
		request.getSession().setAttribute("classidforteacher", selectedclassid);
		
		RequestDispatcher rd = request.getRequestDispatcher("/dashboard.jsp");
		System.out.println("request.getRequestURI()= " + request.getRequestURI());
		System.out.println("request.getRequestURL()= " + request.getRequestURL());
		rd.include(request, response);
		
		out.println("<script type=\"text/javascript\">\r\n" + "        $(window).on('load', function () {\r\n"
				+ "            $('#assignteachertoclass2').modal('show');\r\n" + "        });\r\n" + "    </script>\r\n"
				+ "\r\n"
				+ "    <div class=\"modal fade\" id=\"assignteachertoclass2\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"exampleModalCenterTitle\" aria-hidden=\"true\">\r\n"
				+ "        <div class=\"modal-dialog modal-dialog-centered modal-dialog-scrollable\" role=\"document\">\r\n"
				+ "            <div class=\"modal-content\">\r\n" + "                <div class=\"modal-header\">\r\n"
				+ "                    <h5 class=\"modal-title\" id=\"exampleModalLongTitle\">Assign Teacher to Class</h5>\r\n"
				+ "\r\n" + "                </div>\r\n" + "                <div class=\"modal-body\">\r\n");
		
		
		out.println("<span style='font-weight:bold;color:green;'>Class - "+session.get(class_base.class, selectedclassid).getName()+"</span><br/><br/>");
		out.println("<form action='/practise/application/assign_teacher_to_class_2'>");
		out.println("Select the Teacher to be alloted for the respective subject<br/>");
		out.println("<Select name='selectedteacherforclass' required>");
		out.println("<option value=\"\">Select</option>");
		for(teacher t:teacherlist)
		{
			out.println("<option value='"+t.getId()+"'>"+t.getName()+"</option>");
		}
		out.println("</select><br/><br/>");
		
		
		out.println("Select the Subject that selected Teacher would be handling<br/>");
		out.println("<Select name='selectedsubjectforteacher' required>");
		if(eligibleclasssubjectlist.isEmpty())
		{
			if(session.createNativeQuery("select * from class_subjects where class_id='"+selectedclassid+"'",class_subjects.class).getResultList().isEmpty())
			{
				out.println("<option disabled>NO SUBJECTS ASSIGNED TO THIS CLASS</option>");
				
				out.println("</select><br/>");
				
				out.println("<span style='font-weight:bold;color:red;'>First Assign Subjects to this class</span>");
				
				
				out.println("<div class=\"modal-footer\">\r\n"
						+ "                            <button type=\"submit\" class=\"btn btn-primary\" disabled>Allot the Teacher</button>\r\n"
						+ "                            <button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\">Cancel</button>\r\n"
						+ "                        </div>\r\n"
						+ "                        </form>\r\n"
						+ "                        \r\n"
						+ "						\r\n"
						+ "                </div>\r\n"
						+ "\r\n"
						+ "            </div>\r\n"
						+ "\r\n"
						+ "\r\n"
						+ "        </div>\r\n"
						+ "    </div>");
			}
			else 
			{
						out.println("<option disabled>ALL SUBJECTS ALREADY HAS TEACHERS ASSIGNED</option>");
										
										out.println("</select><br/>");
										
										out.println("<p style=\"font-size:12px\"><span style='font-weight:bold;color:red;'>All Subjects in this class have already been assigned to Teachers<br/>");
										out.println("You can Assign More Subjects to this class then allot Teachers for those subjects</span></p>");
										
										
										out.println("<div class=\"modal-footer\">\r\n"
												+ "                            <button type=\"submit\" class=\"btn btn-primary\" disabled>Allot the Teacher</button>\r\n"
												+ "                            <button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\">Cancel</button>\r\n"
												+ "                        </div>\r\n"
												+ "                        </form>\r\n"
												+ "                        \r\n"
												+ "						\r\n"
												+ "                </div>\r\n"
												+ "\r\n"
												+ "            </div>\r\n"
												+ "\r\n"
												+ "\r\n"
												+ "        </div>\r\n"
												+ "    </div>");
									}
			
		}
		else
		{
			out.println("<option value=\"\">Select</option>");
			for(class_subjects s:eligibleclasssubjectlist)
			{
				out.println("<option value='"+s.getSubject_id()+"'>"+session.get(subject.class, s.getSubject_id()).getName()+"</option>");
			}
			
				out.println("</select>");
				out.println("<div class=\"modal-footer\">\r\n"
						+ "                            <button type=\"submit\" class=\"btn btn-primary\">Allot the Teacher</button>\r\n"
						+ "                            <button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\">Cancel</button>\r\n"
						+ "                        </div>\r\n"
						+ "                        </form>\r\n"
						+ "                        \r\n"
						+ "						\r\n"
						+ "                </div>\r\n"
						+ "\r\n"
						+ "            </div>\r\n"
						+ "\r\n"
						+ "\r\n"
						+ "        </div>\r\n"
						+ "    </div>");
			
		}
		
		
		

		transaction.commit();
		session.close();
		
		
	}
	
	
	protected void assign_teacher_to_class_2(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		System.out.println("assign_teacher_to_class_2 method invoked");
		
		
		Session session = HibernateConnection.getSessionfactory().openSession();
		Transaction transaction=session.beginTransaction();
		
		String selectedclassid=(String)request.getSession().getAttribute("classidforteacher");
		String selectedteacherid=request.getParameter("selectedteacherforclass");
		String selectedsubjectid=request.getParameter("selectedsubjectforteacher");
		
		session.createNativeQuery("update class_subjects set teacher_alloted='true' where class_id='"+selectedclassid+"' and subject_id='"+selectedsubjectid+"'").executeUpdate();
		class_teachers ct=new class_teachers(selectedteacherid, selectedclassid,selectedsubjectid);
		session.save(ct);
		
		RequestDispatcher rd = request.getRequestDispatcher("/dashboard.jsp");
		System.out.println("request.getRequestURI()= " + request.getRequestURI());
		System.out.println("request.getRequestURL()= " + request.getRequestURL());
		rd.include(request, response);
		
		out.println("<script type=\"text/javascript\">\r\n" + "        $(window).on('load', function () {\r\n"
				+ "            $('#resultassignteachertoclassfinal').modal('show');\r\n" + "        });\r\n" + "    </script>\r\n"
				+ "\r\n"
				+ "    <div class=\"modal fade\" id=\"resultassignteachertoclassfinal\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"exampleModalCenterTitle\" aria-hidden=\"true\">\r\n"
				+ "        <div class=\"modal-dialog modal-dialog-centered modal-dialog-scrollable\" role=\"document\">\r\n"
				+ "            <div class=\"modal-content\">\r\n" + "                <div class=\"modal-header\">\r\n"
				+ "                    <h5 class=\"modal-title\" id=\"exampleModalLongTitle\">Teacher Assignment Details</h5>\r\n"
				+ "\r\n" + "                </div>\r\n" + "                <div class=\"modal-body\">\r\n");
		
		
		out.println("<span style='font-weight:bold'>Teacher: </span> " + session.get(teacher.class, selectedteacherid).getName() +"<br/>"
				+ " <span style='font-weight:bold;color:green;'>Assigned to Class: </span><span style='color:blue;'>"+session.get(class_base.class, selectedclassid).getName()+"</span><br/>"
				+"<span style='font-weight:bold;color:green'>For the Subject: </span> " + session.get(subject.class,selectedsubjectid) +"<br/><br/>");
				

		out.println("<div class=\"modal-footer\">\r\n"
				+ "                            <button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\">OK</button>\r\n"
				+ "                        </div>\r\n" + "\r\n" + "                </div>\r\n" + "\r\n"
				+ "            </div>\r\n" + "\r\n" + "\r\n" + "        </div>\r\n" + "    </div>");
		
		transaction.commit();
		session.close();
		
	}
	

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		System.out.println("Service method invoked");
//		out.println("<h3>Service method invoked</h3>");

		System.out.println("request.getPathInfo()= " + request.getPathInfo());
		System.out.println("request.getContextPath()= " + request.getContextPath());
		System.out.println("request.getPathTranslated()= " + request.getPathTranslated());
		System.out.println("request.getServletPath()= " + request.getServletPath());
		System.out.println("request.getRequestURI()= " + request.getRequestURI());
		System.out.println("request.getRequestURL()= " + request.getRequestURL());
		
//		try
//		{
//			if(request.getSession(false).getAttribute("username")==null)
//			{
//				System.out.println("Application try-if block executed");
//				//System.out.println("request.getSession(false).getAttribute(\"username\") = "+request.getSession(false).getAttribute("username"));
//				response.sendRedirect("/practise/login.jsp");
//			}
//		}
//		catch(Exception e)
//		{
//			System.out.println("Application catch block executed");
//			response.sendRedirect("/practise/login.jsp");
//			//System.out.println("request.getSession(false).getAttribute(\"username\") = "+request.getSession(false).getAttribute("username"));
//			
//		}
		
		if(request.getSession(false).getAttribute("username")==null)
		{
			System.out.println("Application if block executed");
			//System.out.println("request.getSession(false).getAttribute(\"username\") = "+request.getSession(false).getAttribute("username"));
			response.sendRedirect("/practise/login.jsp");
		}
		else
		{
			String[] string = request.getPathInfo().split("/");
			System.out.println("Aplication else block executed");
			System.out.println("request.getSession(false).getAttribute(\"username\") = "+request.getSession(false).getAttribute("username"));

			if (string[1].equals("add_student")) 
			{
				System.out.println("jumping to add_student method");
				add_student(request, response);
			} 
			else if (string[1].equals("remove_student")) 
			{
				System.out.println("jumping to remove_student method");
				remove_student(request, response);
			} 
			else if (string[1].equals("add_teacher")) 
			{
				System.out.println("jumping to add_teacher method");
				add_teacher(request, response);
			} 
			else if (string[1].equals("remove_teacher")) 
			{
				System.out.println("jumping to remove_teacher method");
				remove_teacher(request, response);
			}
			else if (string[1].equals("add_subject")) 
			{
				System.out.println("jumping to add_subject method");
				add_subject(request, response);
			}
			else if (string[1].equals("remove_subject")) 
			{
				System.out.println("jumping to remove_student method");
				remove_subject(request, response);
			}
			else if (string[1].equals("add_class")) 
			{
				System.out.println("jumping to add_class method");
				add_class(request, response);
			}
			else if (string[1].equals("remove_class"))
			{
				System.out.println("jumping to remove_class method");
				remove_class(request, response);
			}
			else if (string[1].equals("assign_students_to_class"))
			{
				System.out.println("jumping to assign_students_to_class method");
				assign_students_to_class(request, response);
			}
			else if (string[1].equals("assign_subjects_to_class_1"))
			{
				System.out.println("jumping to assign_subjects_to_class_1 method");
				assign_subjects_to_class_1(request, response);
			}
			else if (string[1].equals("assign_subjects_to_class_2"))
			{
				System.out.println("jumping to assign_subjects_to_class_2 method");
				assign_subjects_to_class_2(request, response);
			}
			else if (string[1].equals("assign_teacher_to_class_1"))
			{
				System.out.println("jumping to assign_teacher_to_class_1 method");
				assign_teacher_to_class_1(request, response);
			}
			else if (string[1].equals("assign_teacher_to_class_2"))
			{
				System.out.println("jumping to assign_teacher_to_class_2 method");
				assign_teacher_to_class_2(request, response);
			}
			else 
			{
				System.out.println("else block");
				out.println("<h1>Invalid URL Entered</h1>");
			}
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
