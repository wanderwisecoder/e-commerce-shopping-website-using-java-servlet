package com.packages.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.*;

import com.packages.models.Cart;


public class QuantityIncDecServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		try(PrintWriter out = response.getWriter();	)
		{
			String action = request.getParameter("action");
			int id = Integer.parseInt(request.getParameter("id"));
			
			ArrayList <Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
			
			
			if(action != null & id>=1)
			{
				if(action.equals("inc"))
				{
					for( Cart c : cart_list )
					{
						if ( c.getId() == id && c.getQuantity()>=1 )
						{
							int quantity = c.getQuantity();
							quantity++;
							c.setQuantity(quantity);
							
						}
					}
				}
				else if(action.equals("dec"))
				{
					for( Cart c:cart_list)
					{
						if ( c.getId() == id && c.getQuantity()>1 )
						{
							int quantity = c.getQuantity();
							quantity--;
							c.setQuantity(quantity);
//							response.sendRedirect("cart.jsp");
						}
					}
				}
				response.sendRedirect("cart.jsp");
			}
		}
		
		
	}

}
