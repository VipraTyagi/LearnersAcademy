package com.saurabh.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapKey;
import javax.persistence.MapKeyClass;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.MapKeyType;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import HibernateSessionFactory.HibernateConnection;

@Entity
@Table(name ="Class")
public class class_base {
	
	@Id
	@Column(name="class_id")
	private String Id;
	
	@Column(name="class_name")
	private String Name;
	
	public static List<class_base> getallclasses()
	{
		List<class_base> allclasslist=new ArrayList<>();
		
		Session session=HibernateConnection.getSessionfactory().openSession();
		allclasslist=session.createNativeQuery("select * from class",class_base.class).getResultList();
		session.close();
		
		return(allclasslist);
		
	}
	
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	
	public class_base() {
		super();
	}
	public class_base(String id, String name) {
		super();
		Id = id;
		Name = name;
	}

	@Override
	public String toString() {
		return "class_base [Id=" + Id + ", Name=" + Name + "]";
	}
	
	



	public static void main(String[] args)
	{
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
//------------>wrong		cfg.configure().setProperties((Properties)(new Properties().setProperty("hbm2ddl.auto", "create")));
		cfg.setProperty(Environment.HBM2DDL_AUTO, "create");
		
		
		
		
		student s1=new student("studentid1","stu_Name1","stu_dob1","stu_city1","stu_phno1");
		student s2=new student("studentid2","stu_Name2","stu_dob2","stu_city2","stu_phno2");
		student s3=new student("studentid3","stu_Name3","stu_dob3","stu_city3","stu_phno3");
		student s4=new student("studentid4","stu_Name4","stu_dob4","stu_city4","stu_phno4");
		student s5=new student("studentid5","stu_Name5","stu_dob5","stu_city5","stu_phno5");
		student s6=new student("studentid6","stu_Name6","stu_dob6","stu_city6","stu_phno6");
		student s7=new student("studentid7","stu_Name7","stu_dob7","stu_city7","stu_phno7");
		student s8=new student("studentid8","stu_Name8","stu_dob8","stu_city8","stu_phno8");
		student s9=new student("studentid9","stu_Name9","stu_dob9","stu_city9","stu_phno9");
		student s10=new student("studentid10","stu_Name10","stu_dob10","stu_city10","stu_phno10");
		student s11=new student("studentid11","stu_Name11","stu_dob11","stu_city11","stu_phno11");
		student s12=new student("studentid12","stu_Name12","stu_dob12","stu_city12","stu_phno12");
		student s13=new student("studentid13","stu_Name13","stu_dob13","stu_city13","stu_phno13");
		student s14=new student("studentid14","stu_Name14","stu_dob14","stu_city14","stu_phno14");
		student s15=new student("studentid15","stu_Name15","stu_dob15","stu_city15","stu_phno15");
		student s16=new student("studentid16","stu_Name16","stu_dob16","stu_city16","stu_phno16");
		student s17=new student("studentid17","stu_Name17","stu_dob17","stu_city17","stu_phno17");
		student s18=new student("studentid18","stu_Name18","stu_dob18","stu_city18","stu_phno18");
		student s19=new student("studentid19","stu_Name19","stu_dob19","stu_city19","stu_phno19");
		student s20=new student("studentid20","stu_Name20","stu_dob20","stu_city20","stu_phno20");
		student s21=new student("studentid21","stu_Name21","stu_dob21","stu_city21","stu_phno21");
		student s22=new student("studentid22","stu_Name22","stu_dob22","stu_city22","stu_phno22");
		student s23=new student("studentid23","stu_Name23","stu_dob23","stu_city23","stu_phno23");
		student s24=new student("studentid24","stu_Name24","stu_dob24","stu_city24","stu_phno24");
		student s25=new student("studentid25","stu_Name25","stu_dob25","stu_city25","stu_phno25");
		student s26=new student("studentid26","stu_Name26","stu_dob26","stu_city26","stu_phno26");
		student s27=new student("studentid27","stu_Name27","stu_dob27","stu_city27","stu_phno27");
		student s28=new student("studentid28","stu_Name28","stu_dob28","stu_city28","stu_phno28");
		student s29=new student("studentid29","stu_Name29","stu_dob29","stu_city29","stu_phno29");
		student s30=new student("studentid30","stu_Name30","stu_dob30","stu_city30","stu_phno30");
		student s31=new student("studentid31","stu_Name31","stu_dob31","stu_city31","stu_phno31");
		student s32=new student("studentid32","stu_Name32","stu_dob32","stu_city32","stu_phno32");
		student s33=new student("studentid33","stu_Name33","stu_dob33","stu_city33","stu_phno33");
		
		subject sub1=new subject("subjectid1","subjectname1","subjectdomain1");
		subject sub2=new subject("subjectid2","subjectname2","subjectdomain2");
		subject sub3=new subject("subjectid3","subjectname3","subjectdomain3");
		subject sub4=new subject("subjectid4","subjectname4","subjectdomain4");
		subject sub5=new subject("subjectid5","subjectname5","subjectdomain5");
		subject sub6=new subject("subjectid6","subjectname6","subjectdomain6");
		subject sub7=new subject("subjectid7","subjectname7","subjectdomain7");
		subject sub8=new subject("subjectid8","subjectname8","subjectdomain8");
		subject sub9=new subject("subjectid9","subjectname9","subjectdomain9");
		subject sub10=new subject("subjectid10","subjectname10","subjectdomain10");
		subject sub11=new subject("subjectid11","subjectname11","subjectdomain11");
		subject sub12=new subject("subjectid12","subjectname12","subjectdomain12");
		subject sub13=new subject("subjectid13","subjectname13","subjectdomain13");
		subject sub14=new subject("subjectid14","subjectname14","subjectdomain14");
		subject sub15=new subject("subjectid15","subjectname15","subjectdomain15");
		subject sub16=new subject("subjectid16","subjectname16","subjectdomain16");
		subject sub17=new subject("subjectid17","subjectname17","subjectdomain17");
		subject sub18=new subject("subjectid18","subjectname18","subjectdomain18");
		
		teacher t1=new teacher("teacherid1", "teachername1", "tea_dob1","tea_city1","tea_phno1");
		teacher t2=new teacher("teacherid2", "teachername2", "tea_dob2","tea_city2","tea_phno2");
		teacher t3=new teacher("teacherid3", "teachername3", "tea_dob3","tea_city3","tea_phno3");
		teacher t4=new teacher("teacherid4", "teachername4", "tea_dob4","tea_city4","tea_phno4");
		teacher t5=new teacher("teacherid5", "teachername5", "tea_dob5","tea_city5","tea_phno5");
		teacher t6=new teacher("teacherid6", "teachername6", "tea_dob6","tea_city6","tea_phno6");
		teacher t7=new teacher("teacherid7", "teachername7", "tea_dob7","tea_city7","tea_phno7");
		teacher t8=new teacher("teacherid8", "teachername8", "tea_dob8","tea_city8","tea_phno8");
		teacher t9=new teacher("teacherid9", "teachername9", "tea_dob9","tea_city9","tea_phno9");
		teacher t10=new teacher("teacherid10", "teachername10", "tea_dob10","tea_city10","tea_phno10");
		teacher t11=new teacher("teacherid11", "teachername11", "tea_dob11","tea_city11","tea_phno11");
		teacher t12=new teacher("teacherid12", "teachername12", "tea_dob12","tea_city12","tea_phno12");
		teacher t13=new teacher("teacherid13", "teachername13", "tea_dob13","tea_city13","tea_phno13");
		
		
		class_base c1=new class_base();c1.setId("classid1");c1.setName("classname1");
		class_base c2=new class_base();c2.setId("classid2");c2.setName("classname2");
		class_base c3=new class_base();c3.setId("classid3");c3.setName("classname3");
		class_base c4=new class_base();c4.setId("classid4");c4.setName("classname4");
		class_base c5=new class_base();c5.setId("classid5");c5.setName("classname5");
		class_base c6=new class_base();c6.setId("classid6");c6.setName("classname6");
		class_base c7=new class_base();c7.setId("classid7");c7.setName("classname7");
		class_base c8=new class_base();c8.setId("classid8");c8.setName("classname8");
		
		SessionFactory sessionfactory=cfg.buildSessionFactory();
		Session session=sessionfactory.openSession();
		Transaction transaction=session.beginTransaction();
		
		
		session.save(s1);session.save(s2);session.save(s3);session.save(s4);session.save(s5);session.save(s6);session.save(s7);session.save(s8);session.save(s9);session.save(s10);session.save(s11);session.save(s12);session.save(s13);session.save(s14);session.save(s15);session.save(s16);session.save(s17);session.save(s18);session.save(s19);session.save(s20);session.save(s21);session.save(s22);session.save(s23);session.save(s24);session.save(s25);session.save(s26);session.save(s27);session.save(s28);session.save(s29);session.save(s30);session.save(s31);session.save(s32);session.save(s33);
		
		session.save(sub1);session.save(sub2);session.save(sub3);session.save(sub4);session.save(sub5);session.save(sub6);session.save(sub7);session.save(sub8);session.save(sub9);session.save(sub10);session.save(sub11);session.save(sub12);session.save(sub13);session.save(sub14);session.save(sub15);session.save(sub16);session.save(sub17);session.save(sub18);
		
		session.save(t1);session.save(t2);session.save(t3);session.save(t4);session.save(t5);session.save(t6);session.save(t7);session.save(t8);session.save(t9);session.save(t10);session.save(t11);session.save(t12);session.save(t13);
		
		session.save("class_base",c1);session.save("class_base",c2);session.save("class_base",c3);session.save("class_base",c4);session.save("class_base",c5);session.save("class_base",c6);session.save("class_base",c7);session.save("class_base",c8);
		

		
		
		transaction.commit();
		session.close();
		
	}


	
}
