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
    <title>Index</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
   <nav class="navbar sticky-top navbar-expand-lg navbar-dark bg-dark">
	
		<!-- Company Name and Logo -->
        <a class="navbar-brand text-decoration-none" href="#"><!-- App Name Here --></a>
                
	</nav>
	<main>
		<div class="container my-5">
			<div class="row justify-content-around">
				
				<div class="col-5 border border-dark pl-5">
					<h2 class="my-3">Register</h2>
				</div>
				
				<form:form action="/register" method="POST" modelAttribute="user">
					<div class="form-group row">
						<form:label class="col-3 col-form-label" path="firstName">First Name</form:label>
						<div class="col-8">
							<form:input class="form-control" type="text" path="firstName"/>
							<form:errors class="small" path="firstName"/>
						</div>
					</div>
					
					<div class="form-group row">
						<form:label class="col-3 col-form-label" path="lastName">Last Name</form:label>
						<div class="col-8">
							<form:input class="form-control" type="text" path="lastName"/>
							<form:errors class="small" path="lastName"/>
						</div>
					</div>
					
					<div class="form-group row">
						<form:label class="col-3 col-form-label" path="email">Email</form:label>
						<div class="col-8">
							<form:input class="form-control" type="email" path="email"/>
							<form:errors class="small" path="email"/>
						</div>
					</div>
					
					<div class="form-group row">
						<form:label class="col-3 col-form-label" path="firstName">First Name</form:label>
						<div class="col-8">
							<form:input class="form-control" type="text" path="firstName"/>
							<form:errors class="small" path="firstName"/>
						</div>
					</div>
					
					<div class="form-group row">
						<form:label class="col-3 col-form-label" path="firstName">First Name</form:label>
						<div class="col-8">
							<form:input class="form-control" type="text" path="firstName"/>
							<form:errors class="small" path="firstName"/>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</main>
</body>
<html>
