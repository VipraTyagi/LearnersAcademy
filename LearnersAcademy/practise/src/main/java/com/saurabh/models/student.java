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
@Table(name ="Student")
public class student {
	
	@Id
	@Column(name = "student_id",nullable=false)
	private String id;
	
	@Column(name = "student_name",nullable=false)
	private String name;
	
	@Column(name = "student_DOB",nullable=false)
	private String DOB;
	
	@Column(name = "student_city",nullable=false)
	private String city;
	
	@Column(name ="student_phone",nullable=false)
	private String phoneno;
	
	
	
	public static List<student> getallstudents()
	{
		List<student> allstudentlist=new ArrayList<>();
		
		Session session=HibernateConnection.getSessionfactory().openSession();
		allstudentlist=session.createNativeQuery("select * from student",student.class).getResultList();
		session.close();
		
		return(allstudentlist);
		
		
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

	

	@Override
	public String toString() {
		return "student [id=" + id + ", name=" + name + ", DOB=" + DOB + ", city=" + city + ", phoneno=" + phoneno
				+ "]";
	}

	
	public student(String id, String name, String dOB, String city, String phoneno) {
		super();
		this.id = id;
		this.name = name;
		DOB = dOB;
		this.city = city;
		this.phoneno = phoneno;
	}

	public student() {
		super();
	}
	
}
