package com.packages.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.packages.models.Order;

public class OrderDao {
	
	private Connection con;

	private String query;
    private PreparedStatement pst;
    private ResultSet rs;

	public OrderDao(Connection con) {
		this.con = con;
	
	}
	
	public boolean insertOrder(Order model)
	{
		boolean result = false;
		
		try
		{
			query = "insert into orders (p_id, u_id, o_quantity, o_date) value(?,?,?,?)";
			pst = this.con.prepareStatement(query);
			pst.setInt(1,model.getId());
			pst.setInt(2, model.getUid());
			pst.setInt(3, model.getQunatity());
			pst.setString(4, model.getDate());
			pst.executeUpdate();
			
			result = true;
		}
		catch(Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
		
		
		
		
		return result;
		
		
		
		
		
		
	}
	
	
	
}
