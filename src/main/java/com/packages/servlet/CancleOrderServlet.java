package com.packages.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.packages.connections.DbCon;
import com.packages.dao.OrderDao;
import com.packages.models.User;

@WebServlet("/cancel-order")
public class CancleOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//User authentication 
		User auth = (User) request.getSession().getAttribute("auth");
		

		//Get Product details through product id
		String id = request.getParameter("id");
		
		try(PrintWriter out = response.getWriter()) 
		{
			if(id != null && auth != null) 
			{
				OrderDao orderDao = new OrderDao(DbCon.getConnection());
				orderDao.cancelOrder(Integer.parseInt(id));
			}
			else
			{
				response.sendRedirect("login.jsp");
			}
			response.sendRedirect("orders.jsp");
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
	}

}
