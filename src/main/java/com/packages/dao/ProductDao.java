 package com.packages.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    
}
