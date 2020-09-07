package rs;

public class Product {
	
	private int productID; 
	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}
	private String productName;
	private double productPrice;
	private int productStock; 
	
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Product(String productName, double productPrice, int productStock) {
		super();
		 
		this.productName = productName;
		this.productPrice = productPrice;
		this.productStock = productStock;
	}
	 
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	public int getProductStock() {
		return productStock;
	}
	public void setProductStock(int productStock) {
		this.productStock = productStock;
	}
	
	
	
	
}
