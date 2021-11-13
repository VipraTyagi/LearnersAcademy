package com.saurabh.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import HibernateSessionFactory.HibernateConnection;

@Entity
@Table(name="Teacher")
public class teacher {
	
	@Id
	@Column(name = "teacher_id",nullable=false)
	private String id;
	
	@Column(name = "teacher_name",nullable=false)
	private String name;
	
	@Column(name = "teacher_DOB",nullable=false)
	private String DOB;
	
	@Column(name = "teacher_city",nullable=false)
	private String city;
	
	@Column(name ="teacher_phone",nullable=false)
	private String phoneno;

	
	public static List<teacher> getallteachers()
	{
		List<teacher> allteacherlist=new ArrayList<>();
		
		Session session=HibernateConnection.getSessionfactory().openSession();
		allteacherlist=session.createNativeQuery("select * from teacher",teacher.class).getResultList();
		session.close();
		
		return(allteacherlist);
		
	}
	
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDOB() {
		return DOB;
	}

	public void setDOB(String dOB) {
		DOB = dOB;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}

	public teacher(String id, String name, String dOB, String city, String phoneno) {
		super();
		this.id = id;
		this.name = name;
		DOB = dOB;
		this.city = city;
		this.phoneno = phoneno;
	}

	public teacher() {
		super();
	}
	
	
	

}
