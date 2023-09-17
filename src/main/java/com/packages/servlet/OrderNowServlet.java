package com.packages.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.packages.connections.DbCon;
import com.packages.dao.OrderDao;
import com.packages.models.Cart;
import com.packages.models.Order;
import com.packages.models.User;


public class OrderNowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try(PrintWriter out = response.getWriter();)
		{
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
			
			Date date = new Date();
			
			
			User auth = (User) request.getSession().getAttribute("auth");
			
			if(auth != null)
			{
				
				String productId = request.getParameter("id");
				int productQuantity = Integer.parseInt(request.getParameter("quantity"));
				
				Order orderModel = new Order();
				orderModel.setId(Integer.parseInt(productId));
				orderModel.setUid(auth.getId());
				orderModel.setQunatity(productQuantity);
				orderModel.setDate(formatter.format(date));
				
				OrderDao orderDao = new OrderDao(DbCon.getConnection());
				
				boolean result = orderDao.insertOrder(orderModel);
				
				
				if(result)
				{
					ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
					if (cart_list != null) 
					{
						for (Cart c : cart_list) 
						{
							if (c.getId() == Integer.parseInt(productId)) 
							{
								cart_list.remove(cart_list.indexOf(c));
								break;
							}
						}
						
					}
					response.sendRedirect("orders.jsp");
				}
				else 
				{
					out.print("order failed");
				} 	
			}
			else
			{
				response.sendRedirect("login.jsp");
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
