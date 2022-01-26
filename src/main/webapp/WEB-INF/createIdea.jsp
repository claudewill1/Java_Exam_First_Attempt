<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. --> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!-- Formatting (dates) --> 
<%@taglib prefix="fmt"uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><!--Insert Page Title--></title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
   <nav class="navbar sticky-top navbar-expand-lg navbar-dark bg-dark">
   		<a class="navbar-brand text-decoration-none" href="#">Great Ideas</a>
   		
   		<!-- Toggler Button -->
   		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent">
   			<span class="navbar-toggler-icon"></span>
   		</button>
   		
   		<div class="collapse navbar-collapse" id="navbarSupportedContent">
   			<ul class="navbar-nav ml-auto">
   				<li class="nav-item">
   					<a class="nav-link active" href="/dashboard">Dashboard</a>
   				</li>
   				<li class="nav-item">
   					<a class="nav-link active" href="/logout">Logout</a>
   				</li>
   			</ul>
   		</div>
   </nav>
   <main class="ml-3">
   		<h5 class="mb-4">Create a new Idea</h5>
   		<div class="col-6">
   			<form:form action="/ideas/newIdea" method="POST" modelAttribute="idea">
   				<form:hidden value="${user.id}" path="creator"/>
   				<div class="form-group row">
   					<form:label path="idea">Idea:</form:label>
   					<div class="col-8">
   						<form:input class="form-control" type="text" path="idea"/>
   						<form:errors class="small" path="idea"/>
   					</div>
   				</div>
   				
   				<div class="form-group row">
   					<input class="btn btn-success" type="submit" value="Create"/>
   				</div>
   			</form:form>
   		</div>
   </main>
</body>
</html>
