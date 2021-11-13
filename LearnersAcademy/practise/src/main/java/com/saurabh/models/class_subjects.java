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
public class class_subjects {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column
	private String class_id;
	@Column
	private String subject_id;
	@Column
	private String teacher_alloted;
	
	
	public static List<class_subjects> getallclass_subjects()
	{
		List<class_subjects> allclass_subjectslist=new ArrayList<>();
		
		Session session=HibernateConnection.getSessionfactory().openSession();
		allclass_subjectslist=session.createNativeQuery("select * from class_subjects",class_subjects.class).getResultList();
		session.close();
		
		return(allclass_subjectslist);
		
	}
	
	
	
	public String getClass_id() {
		return class_id;
	}
	public void setClass_id(String class_id) {
		this.class_id = class_id;
	}
	public String getSubject_id() {
		return subject_id;
	}
	public void setSubject_id(String subject_id) {
		this.subject_id = subject_id;
	}
	public String getTeacher_alloted() {
		return teacher_alloted;
	}
	public void setTeacher_alloted(String teacher_alloted) {
		this.teacher_alloted = teacher_alloted;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public class_subjects(String class_id, String subject_id, String teacher_alloted) {
		super();
		this.class_id = class_id;
		this.subject_id = subject_id;
		this.teacher_alloted = teacher_alloted;
	}
	
	public class_subjects() {
		super();
	}
	
	@Override
	public String toString() {
		return "class_subjects [id=" + id + ", class_id=" + class_id + ", subject_id=" + subject_id
				+ ", teacher_alloted=" + teacher_alloted + "]";
	}

	
	
}
