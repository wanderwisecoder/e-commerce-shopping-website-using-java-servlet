 package com.packages.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.packages.models.*;

public class ProductDao {
	

	private Connection con;

	private String query;
    private PreparedStatement pst;
    private ResultSet rs;
    
    
	public ProductDao(Connection con) {
	
		this.con = con;
	}
 public List<Product> getAllProducts(){
	 
	 
	 List<Product> product = new ArrayList<Product> ();
	 
	 
	 try
	 {
		 query = "select * from products"; 
		 pst = con.prepareStatement(query);
		 rs = pst.executeQuery();
		 
		 while(rs.next())
		 {
			 Product row = new Product();
			 row.setId(rs.getInt("id"));
			 row.setName(rs.getString("name"));
			 row.setCategory(rs.getString("category"));
			 row.setPrice( Double.parseDouble( rs.getString("price") ) );
			 row.setImage(rs.getString("image"));
			 
			 product.add(row);
		 }
	 }
	 catch(Exception e)
	 {
		 System.out.println(e);
		 e.printStackTrace(); 
	 }
	 
	return product;
	 
 }
 
public List<Cart> getCartProducts(ArrayList<Cart> cartList)
{
	List<Cart> products = new ArrayList<Cart>();
	
	try {
		if(cartList.size()>0)
		{
			for(Cart item : cartList)
			{
				query = "select * from products where id = ?";
				pst = this.con.prepareStatement(query);
				pst.setInt(1,item.getId());
				rs = pst.executeQuery();
				while(rs.next())
				{
					Cart row = new Cart();
					row.setId(rs.getInt("id"));
					row.setName(rs.getString("name"));
					row.setCategory(rs.getString("category"));
					row.setPrice( rs.getDouble("price")*item.getQuantity());
					row.setQuantity(item.getQuantity());
					products.add(row);
					
				}
			}
		}
	}
	catch(Exception e)
	{
		System.out.println(e);
		e.printStackTrace();
	}
	
	return products;	
	
}


public double getTotalCartPrice(ArrayList<Cart> cartList) {
    double sum = 0;
    try {
        if (cartList.size() > 0) 
        {
            for (Cart item : cartList) 
            {
                query = "select price from products where id=?";
                pst = this.con.prepareStatement(query);
                pst.setInt(1, item.getId());
                rs = pst.executeQuery();
                
                while (rs.next()) 
                {
                    sum+=rs.getDouble("price")*item.getQuantity();
                }

            }
        }

    } 
    catch (SQLException e) 
    {
        e.printStackTrace();
        System.out.println(e.getMessage());
    }
    return sum;
}


    
}
