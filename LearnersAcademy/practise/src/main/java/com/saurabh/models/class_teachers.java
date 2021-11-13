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
public class class_teachers {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	
	@Column
	String teacher_id;
	@Column
	String assigned_class_id;
	@Column
	String teaches_subject_id;
	
	
	public static List<class_teachers> getallclass_teachers()
	{
		List<class_teachers> alllist=new ArrayList<>();
		
		Session session=HibernateConnection.getSessionfactory().openSession();
		alllist=session.createNativeQuery("select * from class_teachers",class_teachers.class).getResultList();
		session.close();
		
		return(alllist);
		
	}
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTeacher_id() {
		return teacher_id;
	}
	public void setTeacher_id(String teacher_id) {
		this.teacher_id = teacher_id;
	}
	public String getAssigned_class_id() {
		return assigned_class_id;
	}
	public void setAssigned_class_id(String assigned_class_id) {
		this.assigned_class_id = assigned_class_id;
	}
	public String getTeaches_subject_id() {
		return teaches_subject_id;
	}
	public void setTeaches_subject_id(String teaches_subject_id) {
		this.teaches_subject_id = teaches_subject_id;
	}
	
	public class_teachers(String teacher_id, String assigned_class_id, String teaches_subject_id) {
		super();
		this.teacher_id = teacher_id;
		this.assigned_class_id = assigned_class_id;
		this.teaches_subject_id = teaches_subject_id;
	}
	public class_teachers() {
		super();
	}
	
	@Override
	public String toString() {
		return "class_teachers [id=" + id + ", teacher_id=" + teacher_id + ", assigned_class_id=" + assigned_class_id
				+ ", teaches_subject_id=" + teaches_subject_id + "]";
	}
	
	
	
}
