<%@page import="org.hibernate.internal.build.AllowSysOut"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.saurabh.models.student" %>
    <%@ page import="com.saurabh.models.teacher" %>
    <%@ page import="com.saurabh.models.subject" %>
    <%@ page import="com.saurabh.models.class_base" %>
    <%@ page import="com.saurabh.models.class_students" %>
    <%@ page import="com.saurabh.models.class_subjects" %>
    <%@ page import="java.util.List" %>
    <%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/css/anurag.css">
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    
    <!-- Optional JavaScript; choose one of the two! -->
    <!-- Option 1: Bootstrap Bundle with Popper -->
    
    

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">

    <title>Learner's Academy</title>
</head>
<body style="background-color:black;">
<%
if (request.getSession(false).getAttribute("username") == null)
	response.sendRedirect("/practise/login.jsp");
%>

    <nav class="navbar navbar-expand-lg navbar-dark bg-dark sticky-top">
        <div class="container-fluid">
            <a href="/practise/home.jsp"><img class="navbar-brand" src="https://raw.githubusercontent.com/saurabhkawatra/resources/main/dashboard3.gif"/></a>
            
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="/practise/home.jsp" style="font-family: 'Lucida Bright';font-weight:bolder;">Home</a>
                    </li>

                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false" style="font-family: 'Copperplate Gothic';font-weight:bold;">
                            Student
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#addstudent">Add Student</a></li>
                            <li><a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#removestudent">Remove Student</a></li>
                            <li><hr class="dropdown-divider"></li>
                            <li><a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#show_all_students">Show All Students</a></li>
                        </ul>
                    </li>

                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false" style="font-family: 'Copperplate Gothic';font-weight:bold;">
                            Teacher
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#addteacher">Add Teacher</a></li>
                            <li><a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#removeteacher">Remove Teacher</a></li>
                            <li><hr class="dropdown-divider"></li>
                            <li><a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#show_all_teachers">Show All Teachers</a></li>
                        </ul>
                    </li>

                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false" style="font-family: 'Copperplate Gothic';font-weight:bold;">
                            Subject
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#addsubject">Add Subject</a></li>
                            <li><a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#removesubject">Remove Subject</a></li>
                            <li><hr class="dropdown-divider"></li>
                            <li><a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#show_all_subjects">Show All Subjects</a></li>
                        </ul>
                    </li>

                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false" style="font-family: 'Copperplate Gothic';font-weight:bold;">
                            Class
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#addclass">Add Class</a></li>
                            <li><a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#removeclass">Remove Class</a></li>
                            <li><hr class="dropdown-divider"></li>
                            <li><a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#show_all_classes">Show All Classes</a></li>
                        </ul>
                    </li>

                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false" style="font-family: 'Copperplate Gothic';font-weight:bold;">
                            Assign
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#assignstudenttoclass">Assign Students to Class</a></li>
                            <li><a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#assignteachertoclass1">Assign Teachers to Class</a></li>
                            <li><a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#assignsubjectstoclass1">Assign Subjects to Class</a></li>
                        </ul>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="/practise/classreport.jsp" style="color:forestgreen;font-family:'Copperplate Gothic';font-weight:bolder;">Class Report</a>
                    </li>
                </ul>
                
                
                <span style='color:white'>Hello, <%
                                out.println(request.getSession(false).getAttribute("firstname"));
                                %>&emsp;</span>
                
                <form action="/practise/logoutservlet">
                <button class="btn btn-outline-danger" type="submit">Logout</button>
                </form>
            </div>
        </div>
    </nav>


    <div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
        <div class="carousel-indicators">
            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
        </div>
        <div class="carousel-inner" style="width:100%;height:400px !important;">
            <div class="carousel-item active">
                <img src="https://raw.githubusercontent.com/saurabhkawatra/resources/main/slide7.jpg" class="d-block w-5 " style="width:100%;height:400px;margin-left: auto; margin-right: auto;" alt="...">
            </div>
            <div class="carousel-item">
                <img src="https://raw.githubusercontent.com/saurabhkawatra/resources/main/slide6.jpg" class="d-block w-5" style="width:100%;height:400px;margin-left: auto; margin-right: auto;" alt="...">
            </div>
            <div class="carousel-item">
                <img src="https://raw.githubusercontent.com/saurabhkawatra/resources/main/slide4.jpg" class="d-block w-5" style="width:100%;height:400px;margin-left: auto; margin-right: auto;" alt="...">
            </div>
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
        </button>
    </div>


	<div class="modal fade" id="addstudent" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLongTitle">Add Student</h5>

                </div>
                <div class="modal-body">    
                
                		<form action="/practise/application/add_student">
                    	Enter Student Name <br /><input type="text" name="student_name" placeholder="Name" style="" required><br /><br />
                        Enter Student Date of Birth <br /> <input type="date" name="student_dob" required><br /><br />
                        Enter Student City <br /> <input type="text" name="student_city" placeholder="City Name" required><br /><br />
                        Enter Student Phone number <br /><input type="number" name="student_phoneno" placeholder="Phone Number" required><br /><br />
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-primary">Add Student</button>
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                        </div>
                        </form>

                </div>

            </div>


        </div>
    </div>
    
    <div class="modal fade" id="removestudent" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLongTitle">Remove Student</h5>

                </div>
                <div class="modal-body">    
                		
                		<form action="/practise/application/remove_student">
							<table class="table table-dark table-striped">
							<thead>
							<tr>
							<th scope="col">Select Here</th><th scope="col">Student Name</th><th scope="col">Student Date of Birth</th><th scope="col">Student City</th><th scope="col">Student Phone Number</th>
							</tr>
							</thead>
							<tbody>
							<%! List<student> allstudentlist=new ArrayList<>(); %>
							<%
							allstudentlist = student.getallstudents();
							for (student s : allstudentlist) {
								out.println("<tr>");
								out.println("<td style='text-align:center'><input type='checkbox' name=removestudents value='" + s.getId()
								+ "'</td><td>" + s.getName() + "</td><td>" + s.getDOB() + "</td><td>" + s.getCity()
								+ "</td><td>" + s.getPhoneno() + "</td>");
								out.println("</tr>");
							}
							%>
							</tbody>
							</table>
               
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-primary">Remove Selected Students</button>
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                        </div>
                        </form>
						
                </div>

            </div>


        </div>
    </div>
    
    <div class="modal fade" id="show_all_students" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered modal-xl" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLongTitle">All Students In Database</h5>

                </div>
                <div class="modal-body" style='text-align:center'>  
                
                		<table class="table table-dark table-striped">
                		<thead>  
                		<tr><th scope="col">Student Id</th><th scope="col">Student Name</th><th scope="col">Student DOB</th><th scope="col">Student City</th><th scope="col">Student Phone Number</th></tr>
                		</thead>
                		<tbody>
                		<%
                		List<student> allstudentlist1 = student.getallstudents();
                		for (student s : allstudentlist1) {
                			out.println("<tr><td>" + s.getId() + "</td><td>"
                			+ s.getName() + "</td><td>" + s.getDOB() + "</td><td>"
                			+ s.getCity() + "</td><td>" + s.getPhoneno() + "</td></tr>");
                		}
                		%>
                		</tbody>
               			</table>
               			
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">OK</button>
                        </div>
						
                </div>

            </div>


        </div>
    </div>
    
    <div class="modal fade" id="addteacher" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLongTitle">Add Teacher</h5>

                </div>
                <div class="modal-body">    
                
                		<form action="/practise/application/add_teacher">
                    	Enter Teacher Name <br /><input type="text" name="teacher_name" placeholder="Name" style="" required><br /><br />
                        Enter Teacher Date of Birth <br /> <input type="date" name="teacher_dob" required><br /><br />
                        Enter Teacher City <br /> <input type="text" name="teacher_city" placeholder="City Name" required><br /><br />
                        Enter Teacher Phone number <br /><input type="number" name="teacher_phoneno" placeholder="Phone Number" required><br /><br />
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-primary">Add Teacher</button>
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                        </div>
                        </form>

                </div>

            </div>


        </div>
    </div>
    
    <div class="modal fade" id="removeteacher" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLongTitle">Remove Teacher</h5>

                </div>
                <div class="modal-body">    
                		
                		<form action="/practise/application/remove_teacher">
								<table class="table table-dark table-striped">
								<thead>
								<tr>
								<th scope="col">Select Here</th><th scope="col">Teacher Name</th><th scope="col">Teacher Date of Birth</th><th scope="col">Teacher City</th><th scope="col">Teacher Phone Number</th>
								</tr>
								</thead>
								<tbody>
								<%! List<teacher> allteacherlist = new ArrayList<>(); %>
								<%
								allteacherlist = teacher.getallteachers();
								for (teacher s : allteacherlist) {
									out.println("<tr>");
									out.println("<td style='text-align:center'><input type='checkbox' name=removeteachers value='" + s.getId()
									+ "'</td><td>" + s.getName() + "</td><td>" + s.getDOB() + "</td><td>" + s.getCity()
									+ "</td><td>" + s.getPhoneno() + "</td>");
									out.println("</tr>");
								}
								%>
								</tbody>
								</table>
               
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-primary">Remove Selected Teachers</button>
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                        </div>
                        </form>
                        
						
                </div>

            </div>


        </div>
    </div>
    

    <div class="modal fade" id="show_all_teachers" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered modal-xl" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLongTitle">All Teachers In Database</h5>

                </div>
                <div class="modal-body" style='text-align:center'>  
                
                		<table class="table table-dark table-striped">
                		<thead>  
                		<tr><th scope="col">Teacher Id</th><th scope="col">Teacher Name</th><th scope="col">Teacher DOB</th><th scope="col">Teacher City</th><th scope="col">Teacher Phone Number</th></tr>
                		</thead>
                		<tbody>
                		<%
                		List<teacher> allteacherlist1 = teacher.getallteachers();
                		for (teacher s : allteacherlist1) {
                			out.println("<tr><td>" + s.getId() + "</td><td>"
                			+ s.getName() + "</td><td>" + s.getDOB() + "</td><td>"
                			+ s.getCity() + "</td><td>" + s.getPhoneno() + "</td></tr>");
                		}
                		%>
                		</tbody>
               			</table>
               			
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">OK</button>
                        </div>
						
                </div>

            </div>


        </div>
    </div>
    
    
    <div class="modal fade" id="addsubject" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLongTitle">Add Subject</h5>

                </div>
                <div class="modal-body">    
                
                		<form action="/practise/application/add_subject">
		                    	Enter Subject Name <br /><input type="text" name="subject_name" placeholder="Name" style="" required><br /><br />
		                       Choose Subject's Domain <br /><select name="subject_domain" required>
		                       								<option value="">Select</option>
															<option value="Science">Science</option>
															<option value="History">History</option>
															<option value="Civics">Civics</option>
															<option value="Geography">Geography</option>
															<option value="Mathematics">Mathematics</option>
															<option value="Electronic Technology">Electronic Technology</option>
															<option value="Computer Technology">Computer Technology</option>
															<option value="Elementary Science">Elementary Science</option>
															<option value="Elementary Basics">Elementary Basics</option>
														</select>
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-primary">Add Subject</button>
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                        </div>
                        </form>

                </div>

            </div>


        </div>
    </div>
    
    
    <div class="modal fade" id="removesubject" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLongTitle">Remove Subject</h5>

                </div>
                <div class="modal-body">    
                		
                		<form action="/practise/application/remove_subject">
								<table class="table table-dark table-striped">
								<thead>
								<tr>
								<th scope="col">Select Here</th><th scope="col">Subject Name</th><th scope="col">Subject Domain</th>
								</tr>
								</thead>
								<tbody>
								<%! List<subject> allsubjectlist = new ArrayList<>(); %>
								<%
								allsubjectlist = subject.getallsubjects();
								for (subject s : allsubjectlist) {
									out.println("<tr>");
									out.println("<td style='text-align:center'><input type='checkbox' name=removesubjects value='" + s.getId()
									+ "'</td><td>" + s.getName() + "</td><td>" + s.getDomain() + "</td>");
									out.println("</tr>");
								}
								%>
								</tbody>
								</table>
               
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-primary">Remove Selected Subjects</button>
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                        </div>
                        </form>
                        
						
                </div>

            </div>


        </div>
    </div>
    
    
    <div class="modal fade" id="show_all_subjects" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered modal-xl" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLongTitle">All Subjects In Database</h5>

                </div>
                <div class="modal-body" style='text-align:center'>  
                
                	<table class="table table-dark table-striped">  
                		<thead><tr><th scope="col">Subject Id</th><th scope="col">Subject Name</th><th scope="col">Subject Domain</th></tr></thead>
                		<tbody>
                		<%
                		List<subject> allsubjectlist1 = subject.getallsubjects();
                		for (subject s : allsubjectlist1) {
                			out.println("<tr><td>" + s.getId() + "</td><td>"
                			+ s.getName() + "</td><td>" + s.getDomain() + "</td></tr>");
                		}
                		%>
                		</tbody>
               		</table>
               			
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">OK</button>
                        </div>
						
                </div>

            </div>


        </div>
    </div>
    
    
    <div class="modal fade" id="addclass" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLongTitle">Add Class</h5>

                </div>
                <div class="modal-body">    
                
                		<form action="/practise/application/add_class">
		                    	Enter Class Name <br /><input type="text" name="class_name" placeholder="Name" style="" required><br /><br />
		                       
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-primary">Add Class</button>
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                        </div>
                        </form>

                </div>

            </div>


        </div>
    </div>
    
    
    <div class="modal fade" id="removeclass" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLongTitle">Remove Class</h5>

                </div>
                <div class="modal-body">    
                		
                		<form action="/practise/application/remove_class">
                										
								<table class="table table-dark table-striped">
								<thead>
								<tr>
								<th scope="col">Select Here</th><th scope="col">Class Name</th>
								</tr>
								</thead>
								<tbody>
								<%!List<class_base> allclasslist = new ArrayList<>();%>
								<%
								allclasslist = class_base.getallclasses();
								for (class_base s : allclasslist) {
									out.println("<tr>");
									out.println("<td style='text-align:center'><input type='checkbox' name=removeclasses value='" + s.getId()
									+ "'</td><td>" + s.getName() + "</td>");
									out.println("</tr>");
								}
								
								%>
								</tbody>
								</table>
               
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-primary">Remove Selected Classes</button>
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                        </div>
                        </form>
                        
						
                </div>

            </div>


        </div>
    </div>
    
    
    <div class="modal fade" id="show_all_classes" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered modal-xl" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLongTitle">All Classes In Database</h5>

                </div>
                <div class="modal-body" style='text-align:center'>  
                
                		<table class="table table-dark table-striped">  
                		<thead><tr><th scope="col">Class Id</th><th scope="col">Class Name</th></tr></thead>
                		<tbody>
                		<%
                		List<class_base> allclasslist1 = class_base.getallclasses();
                		for (class_base s : allclasslist1) {
                			out.println("<tr><td>" + s.getId() + "</td><td>"
                			+ s.getName() + "</td></tr>");
                		}
                		%>
                		</tbody>
               			</table>
               			
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">OK</button>
                        </div>
						
                </div>

            </div>


        </div>
    </div>
    
    <div class="modal fade" id="assignstudenttoclass" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLongTitle">Assign Students to Class</h5>

                </div>
                <div class="modal-body">    
                
                		<form action="/practise/application/assign_students_to_class">
                				Select the class in which selected students will be Assigned<br />
                				<select name="selected_class_for_students" required>
		                       		<option value="">Select</option>
		                       		<%
		                       		for (class_base c : allclasslist)
		                       			out.println("<option value='" + c.getId() + "'>" + c.getName() + "</option>");
		                       		%>
		                       	</select><br /><br />
                				Select from the following Un-Assigned students, to be Assigned to the Selected Class<br /><br />
		                    	<table class="table table-dark table-striped">
		                    		<thead>
									<tr>
									<th scope="col">Select Here</th><th scope="col">Student Name</th><th scope="col">Student Date of Birth</th><th scope="col">Student City</th><th scope="col">Student Phone Number</th>
									</tr>
									</thead>
									<tbody>
									<%
									List<student> allstudentlist2 = student.getallstudents();
									List<class_students> listcst = class_students.getallclass_students();
									for (student s : allstudentlist2) {
										int x = 0;
										for (class_students cs : listcst) {
											if (cs.getStudent_id().equals(s.getId()))
										x++;
										}
										if (x == 0) {
											out.println("<tr>");
											out.println("<td style='text-align:center'><input type='checkbox' name=assign_students value='" + s.getId()
											+ "'</td><td>" + s.getName() + "</td><td>" + s.getDOB() + "</td><td>"
											+ s.getCity() + "</td><td>" + s.getPhoneno() + "</td>");
											out.println("</tr>");
										}

									}
									%>
									</tbody>
								</table>
		                       
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-primary">Assign Selected Students</button>
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                        </div>
                        </form>

                </div>

            </div>


        </div>
    </div>
    
    <div class="modal fade" id="assignsubjectstoclass1" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLongTitle">Assign Subjects to Class</h5>

                </div>
                <div class="modal-body">    
                		
                		<form action="/practise/application/assign_subjects_to_class_1">
                		
                				Select the class in which Subjects are to be Assigned<br />
                				<select name="selected_class_for_subjects" required>
		                       		<option value="">Select</option>
		                       		<%
		                       		for (class_base c : allclasslist)
		                       			out.println("<option value='" + c.getId() + "'>" + c.getName() + "</option>");
		                       		%>
		                       	</select><br /><br />
		                       	
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-primary">Next</button>
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                        </div>
                        </form>
                        
						
                </div>

            </div>


        </div>
    </div>
    
    
    <div class="modal fade" id="assignteachertoclass1" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLongTitle">Assign Teacher to Class</h5>

                </div>
                <div class="modal-body">    
                		
                		<form action="/practise/application/assign_teacher_to_class_1">
                		
                				Select the class in which Teacher is to be Assigned<br />
                				<select name="selected_class_for_teacher" required>
                				<option value="">Select</option>
		                       		<%
		                       		for (class_base c : allclasslist)
		                       			out.println("<option value='" + c.getId() + "'>" + c.getName() + "</option>");
		                       		%>
		                       	</select><br /><br />
		                       	
		                <div class="modal-footer">
                            <button type="submit" class="btn btn-primary">Next</button>
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                        </div>
                        </form>
                        
						
                </div>

            </div>


        </div>
    </div>
    
    


<br/><br/><br/>



<table class="table table-borderless">
<tr>
<td align="center"><a href="" data-bs-toggle="modal" data-bs-target="#show_all_classes"><img src="<%=request.getContextPath()%>/Resources/class.png"></a></td>
<td align="center"><a href="" data-bs-toggle="modal" data-bs-target="#show_all_students"><img src="<%=request.getContextPath()%>/Resources/student.png"></a></td>  
<td align="center"><a href="" data-bs-toggle="modal" data-bs-target="#show_all_subjects"><img src="<%=request.getContextPath()%>/Resources/subject.png"></a></td>  
<td align="center"><a href="" data-bs-toggle="modal" data-bs-target="#show_all_teachers"><img src="<%=request.getContextPath()%>/Resources/teacher.png"></a></td>
</tr>

<tr>
<td align="center"><span style="color:aqua;Copperplate Gothic;">Total Classes in the database </span><span style="color:green;font-size:25px;font-weight:bold;"><%=allclasslist.size()%></span></td>
<td align="center"><span style="color:aqua;Copperplate Gothic;">Total Students in the database </span><span style="color:green;font-size:25px;font-weight:bold;"><%=allstudentlist.size()%></span></td>        
<td align="center"><span style="color:aqua;Copperplate Gothic;">Total Subjects in the database </span><span style="color:green;font-size:25px;font-weight:bold;"><%=allsubjectlist.size()%></span></td>
<td align="center"><span style="color:aqua;Copperplate Gothic;">Total Teachers in the database </span><span style="color:green;font-size:25px;font-weight:bold;"><%=allteacherlist.size()%></span></td>
</tr>

<tr>
<td><table class="table table-borderless"><tr><td align="center"><a href="" data-bs-toggle="modal" data-bs-target="#addclass"><img src="<%=request.getContextPath()%>/Resources/addstudent.png"></a><br/><span style="color:aqua;Copperplate Gothic;">Add Class</span></td><td align="center"><a href="" data-bs-toggle="modal" data-bs-target="#removeclass"><img src="<%=request.getContextPath()%>/Resources/removeuser.png"></a><br/><span style="color:aqua;Copperplate Gothic;">Remove Class</span></td></tr></table></td>                                                                                     
<td><table class="table table-borderless"><tr><td align="center"><a href="" data-bs-toggle="modal" data-bs-target="#addstudent"><img src="<%=request.getContextPath()%>/Resources/addstudent.png"></a><br/><span style="color:aqua;Copperplate Gothic;">Add Student</span></td><td align="center"><a href="" data-bs-toggle="modal" data-bs-target="#removestudent"><img src="<%=request.getContextPath()%>/Resources/removeuser.png"></a><br/><span style="color:aqua;Copperplate Gothic;">Remove Student</span></td></tr></table></td>                                                                                     
<td><table class="table table-borderless"><tr><td align="center"><a href="" data-bs-toggle="modal" data-bs-target="#addsubject"><img src="<%=request.getContextPath()%>/Resources/addstudent.png"></a><br/><span style="color:aqua;Copperplate Gothic;">Add Subject</span></td><td align="center"><a href="" data-bs-toggle="modal" data-bs-target="#removesubject"><img src="<%=request.getContextPath()%>/Resources/removeuser.png"></a><br/><span style="color:aqua;Copperplate Gothic;">Remove Subject</span></td></tr></table></td>                                                                                     
<td><table class="table table-borderless"><tr><td align="center"><a href="" data-bs-toggle="modal" data-bs-target="#addteacher"><img src="<%=request.getContextPath()%>/Resources/addstudent.png"></a><br/><span style="color:aqua;Copperplate Gothic;">Add Teacher</span></td><td align="center"><a href="" data-bs-toggle="modal" data-bs-target="#removeteacher"><img src="<%=request.getContextPath()%>/Resources/removeuser.png"></a><br/><span style="color:aqua;Copperplate Gothic;">Remove Teacher</span></td></tr></table></td>                                                                                     
</tr>

  
</table> 



<br/><br/><br/><br/>
    
    
    
    
    
    

    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
    
    <!-- Option 2: Separate Popper and Bootstrap JS -->
    <!--
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js" integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT" crossorigin="anonymous"></script>
    -->
</body>
</html>