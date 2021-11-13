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
@Table(name ="Subject")
public class subject {
	
	@Id
	@Column(name = "subject_id",nullable=false)
	private String id;
	
	@Column(name = "subject_name",nullable=false)
	private String name;
	
	@Column(name = "subject_domain",nullable=false)
	private String domain;
	
	
	public static List<subject> getallsubjects()
	{
		List<subject> allsubjectlist=new ArrayList<>();
		
		Session session=HibernateConnection.getSessionfactory().openSession();
		allsubjectlist=session.createNativeQuery("select * from subject",subject.class).getResultList();
		session.close();
		
		return(allsubjectlist);
		
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


	public String getDomain() {
		return domain;
	}


	public void setDomain(String domain) {
		this.domain = domain;
	}


	public subject(String id, String name, String domain) {
		super();
		this.id = id;
		this.name = name;
		this.domain = domain;
	}


	public subject() {
		super();
	}


	@Override
	public String toString() {
		return "subject [id=" + id + ", name=" + name + ", domain=" + domain + "]";
	}
	
	

}
