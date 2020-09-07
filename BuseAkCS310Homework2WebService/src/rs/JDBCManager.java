package rs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

 

public class JDBCManager {

	public static boolean save(Product p1) 
	{
		try 
		{
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/cs310", "root","gofret12345");
			PreparedStatement ps =  con.prepareStatement("insert into product (productName,productPrice,productStock) values (?,?,?)");
			 
			ps.setString(1, p1.getProductName());
			ps.setDouble(2, p1.getProductPrice());
			ps.setInt(3, p1.getProductStock());
			int result = ps.executeUpdate();
			
			if(result==1)
				return true;
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean deleteByID(int productID) 
	{
		try 
		{
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/cs310", "root","gofret12345");
			PreparedStatement ps =  con.prepareStatement("delete from product where productID="+productID+"");
		 
			int result = ps.executeUpdate();
			
			if(result==1) {
				System.out.println("silindi");
				return true;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	

	public static boolean updateProductByID (int productID, double productPrice, int productStock) {
		try
		{
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/cs310", "root", "gofret12345");	
			//DOGRU MU?
			PreparedStatement ps =  connection.prepareStatement("update product set productPrice = ?,productStock = ? where productID = ?");
			ps.setDouble(1, productPrice);
			ps.setInt(2, productStock);
			ps.setInt(3, productID);
			//while(rs.next())
			//{
				 
				
			//}
			
			int result=ps.executeUpdate();
			if(result==1) {
				System.out.println("updates");
				return true;
			}
			 
			 
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	public static ArrayList<Product> getAll() {
		ArrayList<Product> products = new ArrayList<Product>();
		try 
		{
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/cs310", "root","gofret12345");
			PreparedStatement ps =  con.prepareStatement("select * from product");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				int id=rs.getInt("productID");
				String productName = rs.getString("productName");
				double productPrice = rs.getDouble("productPrice");
				int productStock = rs.getInt("productStock");
				Product p = new Product(productName,productPrice,productStock);
				p.setProductID(id);
				
				products.add(p);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return products;
	}

	 

}

