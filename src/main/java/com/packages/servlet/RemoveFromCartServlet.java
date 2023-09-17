package com.packages.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.packages.models.Cart;

public class RemoveFromCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		response.setContentType("text/html;charset=UTF-8");
		
		try (PrintWriter out = response.getWriter()) 
		{
			String bookId = request.getParameter("id");
			
			if (bookId != null) 
			{
				 
			} 
		response.sendRedirect("cart.jsp");
		}
	}
}
