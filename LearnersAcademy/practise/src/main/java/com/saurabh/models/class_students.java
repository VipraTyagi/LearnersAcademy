package com.saurabh.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import HibernateSessionFactory.HibernateConnection;

@Entity
public class class_students {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@Column(nullable = false)
	private String class_id;
	
	@Column(nullable = false,unique = true)
	private String student_id;
	
	
	public static List<class_students> getallclass_students()
	{
		List<class_students> allclass_studentslist=new ArrayList<>();
		
		Session session=HibernateConnection.getSessionfactory().openSession();
		allclass_studentslist=session.createNativeQuery("select * from class_students",class_students.class).getResultList();
		session.close();
		
		return(allclass_studentslist);
		
	}

	public String getClass_id() {
		return class_id;
	}

	public void setClass_id(String class_id) {
		this.class_id = class_id;
	}

	public String getStudent_id() {
		return student_id;
	}

	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}

	public class_students(String class_id, String student_id) {
		super();
		this.class_id = class_id;
		this.student_id = student_id;
	}

	public class_students() {
		super();
	}

	@Override
	public String toString() {
		return "class_students [class_id=" + class_id + ", student_id=" + student_id + "]";
	}
	
	
		
}
