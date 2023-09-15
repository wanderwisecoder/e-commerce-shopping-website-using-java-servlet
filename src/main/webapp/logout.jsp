 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Shopping Cart Login Page</title>

<%@include file="bootstrap-files/head.jsp" %>
</head>
<body>

 <%@include file="bootstrap-files/navbar.jsp" %>

<div class="container">
		<div class="card w-50 mx-auto my-5">
			<div class="card-header text-center">User Login</div>
			<div class="card-body">
				<form action="user-login" method="post">
					<div class="form-group my-2">
						<label>Email address</label> 
						<input type="email" name="login-email" class="form-control my-2" placeholder="Enter email" required>
					</div>
					<div class="form-group my-2">
						<label>Password</label> 
						<input type="password" name="login-password" class="form-control my-2" placeholder="Password" required>
					</div>
					<div class="text-center">
						<button type="submit" class="btn btn-primary">Login</button>
					</div>
				</form>
			</div>
		</div>
	</div>




<%@include file="bootstrap-files/js-cdn-link.jsp" %>
</body>

</html> 