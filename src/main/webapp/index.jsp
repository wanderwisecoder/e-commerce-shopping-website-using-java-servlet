
<%@ page import="com.packages.connections.DbCon"%>
<%@ page import="com.packages.dao.*"%>
<%@ page import = "java.util.*" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.packages.models.*"%>
<% 
    User auth = (User) request.getSession().getAttribute("auth"); 
    if(auth!= null)
    {
    request.setAttribute("auth",auth);
    }
    
    ProductDao pd = new ProductDao(DbCon.getConnection());
    List<Product> products = pd.getAllProducts();
    
   %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome to Shopping Cart</title>

<%@include file="bootstrap-files/head.jsp"%>
</head>
<body>


	<%@include file="bootstrap-files/navbar.jsp"%>

	<% out.print(DbCon.getConnection()); %>

	<h5>Index</h5>

	<ul
		class="nav nav-pills nav-fill gap-2 p-1 small bg-primary rounded-5 shadow-sm"
		id="pillNav2" role="tablist"
		style="--bs-nav-link-color: var(--bs-white); --bs-nav-pills-link-active-color: var(--bs-primary); --bs-nav-pills-link-active-bg: var(--bs-white);">

		<li class="nav-item" role="presentation">
			<button class="nav-link active rounded-5" id="home-tab2"
				data-bs-toggle="tab" type="button" role="tab" aria-selected="true">Home</button>
		</li>
		<li class="nav-item" role="presentation">
			<button class="nav-link rounded-5" id="profile-tab2"
				data-bs-toggle="tab" type="button" role="tab" aria-selected="false">Profile</button>
		</li>
		<li class="nav-item" role="presentation">
			<button class="nav-link rounded-5" id="contact-tab2"
				data-bs-toggle="tab" type="button" role="tab" aria-selected="false">Contact</button>
		</li>
	</ul>

	<div class="container">
		<div class="card-header my-3">All Products</div>
		<div class="row">
			<%
			if (!products.isEmpty()) {
				for (Product p : products) {
			%>
			<div class="col-md-3 my-3">
				<div class="card w-100">
					<img class="card-img-top" src="product-images/<%=p.getImage() %>"
						alt="Card image cap">
					<div class="card-body">
						<h5 class="card-title"><%=p.getName() %></h5>
						<h6 class="price">Price: Rs.<%=p.getPrice() %></h6>
						<h6 class="category">Category: <%=p.getCategory() %></h6>
						<div class="mt-3 d-flex justify-content-between">
							<a class="btn btn-dark" href="add-to-cart?id=<%=p.getId()%>">Add to Cart</a> 
							<a class="btn btn-primary" href="order-now?quantity=1&id=<%=p.getId()%>">Buy Now</a>
						</div>
					</div>
				</div>
			</div>
			<%
			}
			} else {
			out.println("There is no proucts");
			}
			%>

		</div>
	</div>

			<%@include file="bootstrap-files/js-cdn-link.jsp"%>
</body>

</html>