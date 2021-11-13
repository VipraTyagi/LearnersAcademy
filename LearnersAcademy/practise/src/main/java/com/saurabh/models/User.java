package com.saurabh.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column
	String firstname;
	@Column
	String lastname;
	@Column	
	String username;
	@Column
	String password;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public User(String firstname, String lastname, String username, String password) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
	}
	public User() {
		super();
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", username=" + username
				+ ", password=" + password + "]";
	}
	
	public static void main(String[] args)
	{
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		Session session=cfg.buildSessionFactory().openSession();
		session.beginTransaction();
		
		User u=new User("Saurabh","Kawatra","sk","123");
		User u1=new User("Saurabh1","Kawatra1","sk1","123");
		User u2=new User("Saurabh2","Kawatra2","sk2","123");
		User u3=new User("Saurabh3","Kawatra3","sk3","123");
		User u4=new User("Saurabh4","Kawatra4","sk4","123");
		
		session.save("User", u);session.save("User", u1);session.save("User", u2);session.save("User", u3);session.save("User", u4);
		
		session.getTransaction().commit();
		session.getSessionFactory().close();
		session.close();
	}

}
