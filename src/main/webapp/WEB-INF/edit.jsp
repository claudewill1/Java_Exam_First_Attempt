<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!-- c:out ; c:forEach etc. --> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!-- Formatting (dates) --> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
   <main>
   		<h3 class="mb-4">Edit ${idea.title}</h3>
   		
   		<div class="row">
   			<div class="col-6">
   				<form:form action="/idea/${idea.id}/edit" method="POST" modelAttribute="idea">
   					<input type="hidden" name="_method" value="put"/>
   					
   					<form:hidden value="${user.id}" path="creator"/>
   					
   					<div class="form-group row">
   						<form:label class="col-3 col-form-label" path="title">Content:</form:label>
   						<div class="col-8">
   							<form:input class="form-control" type="text" path="title"/>
   							<form:errors class="small" path="title"/>
   						</div>
   					</div>
   					<div class="form-group row">
   						<div class="col-1 offset-2">
   							<input class="btn btn-success" type="submit" value="Update"/>
   						</div>
   						<div class="col-1 ml-2">
   							<a class="btn btn-danger text-decoration-none" href="/ideas/delete/${idea.id}">Delete</a>
   						</div>
   					</div>
   					<p id="errors" class="mb-3">${error}</p>
   				</form:form>
   			</div>
   		</div>
   </main>
</body>
</html>
