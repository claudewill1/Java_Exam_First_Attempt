<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!-- c:out ; c:forEach etc. --> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
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
    <title>Dashboard</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
   <nav class="navbar sticky-top navbar-expand-lg navbar-dark bg-dark">
	
		<!-- Company Name and Logo -->
        <a style="margin-left:10px;" class="navbar-brand text-decoration-none ml-5" href="#">Great Ideas</a>
        
        <!-- Toggler Button -->
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent">
        <span class="navbar-toggler-icon"></span>
        </button>

        <!-- Navbar Links -->
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ml-auto">
            	
            	<li class="nav-item">
                    <a class="nav-link active" href="/logout">Logout</a>
                </li>

            </ul> 
        </div>
        
	</nav>
	<main>
		<div style="margin-left:10px;" class="ml-4">
			<h3 class="ml-4">Welcome ${user.name}</h3>
			<hr>
			
			<div class="mt-3 mb-4">
				<h5>Ideas</h5>
				<div class="row">
					<div class="col-9">
						<table class="table table-dark table-striped mt-1">
							<thead>
								<tr>
									<th>Idea</th>
									<th>Created By:</th>
									<th>Likes</th>
									<th>Action</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${ideas}" var="idea">
									<tr>
										<td><a href="/idea/${idea.id}">${idea.title}</a></td>
										<td>${idea.creator}</td>
										<td>${idea.likes}</td>
										<td>
										<!-- 
											<c:choose>
												<c:when test="${!idea.users.contains(user)}">
													<a href="/ideas/like/${idea.id}">Like</a>
												</c:when>
												<c:otherwise>
													<a href="/idea/unlike/${idea.id}">UnLike</a>
												</c:otherwise>
											</c:choose>  -->
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<a href="/idea/new" class="btn btn-primary">Create An Idea</a>
					</div>
				</div>
				
				
				
			</div>
		</div>
	</main>
</body>
</html>
