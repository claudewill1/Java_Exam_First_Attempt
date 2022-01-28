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
    <title>Index</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
   <nav class="navbar sticky-top navbar-expand-lg navbar-dark bg-dark">
	
		<!-- Company Name and Logo -->
        <a style="margin-left:10px;" class="navbar-brand text-decoration-none" href="#">Great Ideas</a>
                
	</nav>
	<main>
		<div class="container d-flex flex-direction-row justify-content-between">
    
		   <div id="registrationContainer">	
		   		
				<h2 class="my-3">Register</h2>
			         
			        <form:form action="/register" method="POST" modelAttribute="user">
			        	
						
			        	<div class="form-group row">
				        	<form:label class="col-3 col-form-label" path="name">Name</form:label>
				       		 <div class="col-8">
				        		<form:input class="form-control" type="text" path="name"/>
				        		<p><form:errors class="small" path="name"/></p>
				        	</div>
				    	</div>
				    	
				    	
				    	
			        	<div class="form-group row">
				        	<form:label class="col-3 col-form-label" path="email">Email</form:label>
				       		 <div class="col-8">
				        		<form:input class="form-control" type="email" path="email"/>
				        		<p><form:errors class="small" path="email"/></p>
				        	</div>
				    	</div>
				    			    	
				    	
				    	<div class="form-group row">
				        	<form:label class="col-3 col-form-label" path="password">Password</form:label>
				       		 <div class="col-8">
				        		<form:input class="form-control" type="password" path="password"/>
				        		<p><form:errors class="small" path="password"/></p>
				        	</div>
				    	</div>
				    	
				    	
				    	<div class="form-group row">
				        	<form:label class="col-3 col-form-label" path="passwordConfirm">Password Confirm</form:label>
				       		 <div class="col-8">
				        		<form:input class="form-control" type="password" path="passwordConfirm"/>
				        		<p><form:errors class="small" path="passwordConfirm"/></p>
				        	</div>
				    	</div>
				    	
				    	<!-- Submit Button -->
				    	<div class="form-group row">
			    			<div class="col-3 offset-3">
			    				<input class = "btn btn-success" type="submit" value="Register"/>
			    			</div>	
			    		</div>
				    	
			    	</form:form>
			</div>
		    <div id="loginContainer">
			    <h2 class="my-3">Login</h2>
			        
					<p id="errors" class="mb-3"><c:out value="${LoginError}"/></p>
					
			        <form action="/login" method="POST">
			        	
						<!-- Email Field -->
			        	<div class="form-group row">
				        	<label class="col-3 col-form-label" for="email">Email</label>
				       		 <div class="col-8">
				        		<input class="form-control" type="email" id="email" name="email"/>
				        		
				        	</div>
				    	</div>
				    	
				    	<!-- Password Field -->
				    	<div class="form-group row">
				        	<label class="col-3 col-form-label" for="password">Password</label>
				       		 <div class="col-8">
				        		<input class="form-control" type="password" id="password" name="password"/>
				        		
				        	</div>
				    	</div>
				    	
				    	<!-- Submit Button -->
				    	<div class="form-group row">
			    			<div class="col-3 offset-3">
			    				<input class = "btn btn-success" type="submit" value="Log In"/>
			    			</div>	
			    		</div>
			    		
			    	</form>
		    </div>
		 </div> 
	</main>
</body>
<html>
