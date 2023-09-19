package com.packages.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import com.packages.connections.DbCon;
import com.packages.dao.*;
import com.packages.models.*;

public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try(PrintWriter out = response.getWriter()){
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            
            //Retrieve all cart products
			ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
			
			//User authentication 
			User auth = (User) request.getSession().getAttribute("auth");
			
			//Check auth and cart list
			if(cart_list != null && auth!=null) {
				for(Cart c:cart_list) {
					
				    //Prepared the order object
					Order order = new Order();
					order.setId(c.getId());
					order.setUid(auth.getId());
					order.setQunatity(c.getQuantity());
					order.setDate(formatter.format(date));
					
					//Instantiate the Dao class
					OrderDao oDao = new OrderDao(DbCon.getConnection());
					
					//Calling insert method
					boolean result = oDao.insertOrder(order);
					if(!result) break;
				}
				cart_list.clear();
				response.sendRedirect("orders.jsp");
			}
			else 
			{
				if(auth==null) 
				{
					response.sendRedirect("login.jsp");
				}
				else
				{
				response.sendRedirect("cart.jsp");
				}
			}
		} 
		catch (Exception e) 
		{
			System.out.println(e);
			
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}