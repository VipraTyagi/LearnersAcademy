<%@page import="com.saurabh.models.class_teachers"%>
<%@include file="dashboard.jsp" %>

<%! List<class_students> studentclasslist = new ArrayList<>(); %>
<%! List<class_teachers> teacherclasslist= new ArrayList<>();%>
<%! List<class_subjects> subjectclasslist= new ArrayList<>(); %>
<%studentclasslist=class_students.getallclass_students(); %>    
<%teacherclasslist=class_teachers.getallclass_teachers(); %>    
<%subjectclasslist=class_subjects.getallclass_subjects(); %>
<%! student getstudent(String student_id){
	
	for(student s: allstudentlist)
	{if(s.getId().equals(student_id))
		{return s;}
	}
	return null;
} %>

<%! teacher getteacher(String teacher_id){
	
	for(teacher t: allteacherlist)
	{if(t.getId().equals(teacher_id))
		{return t;}
	}
	return null;
} %>

<%! subject getsubject(String subject_id){
	
	for(subject sub: allsubjectlist)
	{if(sub.getId().equals(subject_id))
		{return sub;}
	}
	return null;
} %>     
    
  
    
<%for(class_base cu:allclasslist1) {%>
<br/><br/><br/><br/>




<h1 align="center"><span style="background-color:white;color:darkblue;font-family:Times New Roman;border-radius: 30px;">Class - <%=cu.getName()%> Details</span></h1>

<br /><br />






<h3 style="color:green;"><span style="background-color:yellow;color:black;font-family:'Century Gothic';font-weight:'bold';">List of Students in <span style="color:blue;"><%=cu.getName()%></span></span></h3><br/>

<p style="color:orange;">Total Students in <span style="color:yellow;"><%=cu.getName()%></span> : <%int totalstudents=0;for(class_students cst:studentclasslist){if(cst.getClass_id().equals(cu.getId()))totalstudents++;}%><span style="color:green;font-size:25px;font-weight:bold;"><%=totalstudents%></span></p>
<table class="table table-dark table-striped">
<thead>
<tr>
<th scope="col">Student's ID</th>
<th scope="col">Student's Name</th>
<th scope="col">Student's DOB</th>
<th scope="col">Student's City</th>
<th scope="col">Student's Phone Number</th>
</tr>
</thead>
<tbody>
<%student stu=new student();
for(class_students cstu:studentclasslist)
{
	if(cstu.getClass_id().equals(cu.getId()))
	{
		stu=getstudent(cstu.getStudent_id());
		%>
		<tr>
		<td><%=stu.getId()%></td>
		<td><%=stu.getName() %></td>
		<td><%=stu.getDOB()%></td>
		<td><%=stu.getCity()%></td>
		<td><%=stu.getPhoneno()%></td>
		</tr>
		<%
	}
}%>
</tbody>
</table>

<br/><br/><br/>









<h3 style="color:green;"><span style="background-color:yellow;color:black;font-family:'Century Gothic';font-weight:'bold';">List of Subjects in <span style="color:blue;"><%=cu.getName()%></span></span></h3><br/>

<p style="color:orange;">Total Subjects in <span style="color:yellow;"><%=cu.getName()%></span> : <%int totalsubjects=0;for(class_subjects cs:subjectclasslist){if(cs.getClass_id().equals(cu.getId()))totalsubjects++;}%><span style="color:green;font-size:25px;font-weight:bold;"><%=totalsubjects%></span></p>
<table class="table table-dark table-striped">
<thead>
<tr>
<th scope="col">Subject ID</th>
<th scope="col">Subject Name</th>
<th scope="col">Subject Domain</th>
<th scope="col">Taught By</th>
</tr>
</thead>
<tbody>
<%subject sub1=new subject();
for(class_subjects csub:subjectclasslist)
{
	if(csub.getClass_id().equals(cu.getId()))
	{
		sub1=getsubject(csub.getSubject_id());
		%>
		<tr>
		<td><%=sub1.getId()%></td>
		<td><%=sub1.getName() %></td>
		<td><%=sub1.getDomain()%></td><%teacher t1=new teacher();for(class_teachers ct:teacherclasslist){if(ct.getAssigned_class_id().equals(cu.getId())&&ct.getTeaches_subject_id().equals(sub1.getId()))t1=getteacher(ct.getTeacher_id());} %>
		<td><%if(t1.getName()==null){%><span style="color:red;font-weight:bold;">No Teacher Assigned</span><%}else{%><%=t1.getName()%><%}%></td>
		</tr>
		<%
	}
}%>
</tbody>
</table>

<br/><br/><br/>







<h3 style="color:green;"><span style="background-color:yellow;color:black;font-family:'Century Gothic';font-weight:'bold';">List of Teachers in <span style="color:blue;"><%=cu.getName()%></span></span></h3><br/>

<p style="color:orange;">Total Teachers in <span style="color:yellow;"><%=cu.getName()%></span> : <%int totalteachers=0;for(class_teachers ts:teacherclasslist){if(ts.getAssigned_class_id().equals(cu.getId()))totalteachers++;}%><span style="color:green;font-size:25px;font-weight:bold;"><%=totalteachers%></span></p>
<table class="table table-dark table-striped">
<thead>
<tr>
<th scope="col">Teacher's ID</th>
<th scope="col">Teacher's Name</th>
<th scope="col">Teacher's City</th>
<th scope="col">Teacher's DOB</th>
<th scope="col">Teacher's Phone Number</th>
<th scope="col">Teaches Subject</th>
</tr>
</thead>
<tbody>
<%teacher t=new teacher();
for(class_teachers ct:teacherclasslist)
{
	if(ct.getAssigned_class_id().equals(cu.getId()))
	{
		t=getteacher(ct.getTeacher_id());
		%>
		<tr>
		<td><%=t.getId()%></td>
		<td><%=t.getName() %></td>
		<td><%=t.getCity()%></td>
		<td><%=t.getDOB()%></td>
		<td><%=t.getPhoneno()%></td><%subject sub=new subject();for(class_teachers x:teacherclasslist){if(x.getTeacher_id().equals(t.getId()))sub=getsubject(x.getTeaches_subject_id());}%>
		<td><%=sub.getName()%></td>
		</tr>
		<%
	}
}%>
</tbody>
</table>




<%}%>














