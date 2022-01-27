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
	
		<!-- Company Name and Logo -->
        <a class="navbar-brand text-decoration-none" href="#">Great Ideas</a>
        
        <!-- Toggler Button -->
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent">
        <span class="navbar-toggler-icon"></span>
        </button>

        <!-- Navbar Links -->
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
		<h3 style="margin-left:10px;">${idea.title}</h3>
		<hr>
		
		<div style="margin-left:10px;" class="ml-3">
			<p>Created By: <c:out value="${idea.creator}"/></p>
			<h5 class="mt-4 mb-2">Users who liked your idea:</h5>
			<div class="row">
				<div class="col-9">
					<table class="table table-dark table-striped mt-1">
						<thead>
							<tr>
								<th>Name</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${idea.users}" var="user">
								<tr>
									<td>${user.name}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<a class="btn btn-primary" href="/idea/edit/${idea.id}">Edit</a>
		</div>
	</main>
</body>
</html>
