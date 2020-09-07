package rs;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("ProductWebService")
public class ProductWebService {
	
	@GET
	@Path("addNewProduct/{productName}/{productPrice}/{productStock}")
	public boolean addNewProduct(@PathParam("productName") String productName, 
			@PathParam("productPrice")double productPrice, @PathParam("productStock")int productStock)
	{
		Product p = new Product(productName,productPrice,productStock);
		boolean result =  JDBCManager.save(p);
		return  result;
	}
	
	@GET
	@Path("deleteProduct/{productID}")
	public boolean deleteProduct(@PathParam("productID") int productID)
	{
		 
		boolean result =  JDBCManager.deleteByID(productID);
		return result;
	}
	
	@GET
	@Path("updateProductStock/{productID}/{productPrice}/{productStock}")
	public boolean updateProductStock(@PathParam("productID") int productID, 
			@PathParam("productPrice")double productPrice,@PathParam("productStock")int productStock)
	{
		 boolean result=JDBCManager.updateProductByID(productID,productPrice,productStock);
		 return result;
	}
	
	@GET
	@Path("getAllProducts")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getAllProducts()
	{
		ArrayList<Product> products = JDBCManager.getAll();
		return products;
	}
	
	

}
