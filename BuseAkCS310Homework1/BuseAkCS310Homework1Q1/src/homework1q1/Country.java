package homework1q1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement; 
import java.sql.ResultSet;
//import java.sql.SQLException;

public class Country {
	
	int countryId;
	String countryName;
	String continent;
	String capital;
	int population;
	
	
	public Country() {
		super();
	}
	public Country(int countryId,String countryName, String continent, String capital,int population) {
		super();
		this.countryId = countryId;
		this.countryName = countryName;
		this.continent=continent;
		this.capital=capital;
		this.population=population;
		//System.out.println("okuma basarili");
	}
	
	public static ArrayList<Country> readFromFile(String filename) {
		ArrayList<Country> countries 
		= new ArrayList<Country>();
		try 
		{
			FileReader reader = new FileReader(filename);
			BufferedReader bfr = new BufferedReader(reader);
			int id=-1;
			while(true)
			{
				String line = bfr.readLine();
				if (line == null)
				{
					break;
				}
				id++;
				System.out.println(line);
				String[] arr = line.split(",");
				String countryName = arr[0];
				String continent = arr[1];
				String capital=arr[2];
				int population=Integer.parseInt(arr[3]);
				Country c = new Country(id, countryName,continent,capital,population);
				
				countries.add(c);
			}
			reader.close();
		
		}
		catch (FileNotFoundException e) {
			System.out.println("no file");
			e.printStackTrace();
		}
		catch (IOException e) {
			System.out.println("no have rights to read that file");
			e.printStackTrace();
		}
		
		//
		return countries;
		//System.out.println("okuma basarili");
	}

	public static void writeListToDB(ArrayList<Country> countries) {
		
		try
		{
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/cs310", "root", "gofret12345");		
			for (Country c : countries)
			{
				PreparedStatement ps =  connection.prepareStatement("insert into Countries (countryId,countryName,continent,capital,population) values (?,?,?,?,?) ");
				//System.out.println("BURDA");
				ps.setInt(1, c.countryId);
				ps.setString(2, c.countryName);
				ps.setString(3, c.continent);
				ps.setString(4, c.capital);
				ps.setInt(5, c.population);
				
				ps.execute();
			}
			 
			
			System.out.println("Data inserted!!!");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
}

	public static Country getCountryByID (int countryID) {
		Country c=new Country();
		try
		{
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/cs310", "root", "gofret12345");
			System.out.println("connected!!");
			PreparedStatement ps=connection.prepareStatement("select countryId, countryName, continent, capital, population from countries where countryId="+countryID+"");
			
			ResultSet rs =	ps.executeQuery();
			//rs.next();
			
			//Country c=new Country();
			
			while(rs.next())
			{
				int id = rs.getInt("countryId");
				String cName  = rs.getString("countryName");
				String cont  = rs.getString("continent");
				String capi	=rs.getString("capital");
				int pop = rs.getInt("population");
				c.countryId=id;
				c.countryName=cName;
				c.continent=cont;
				c.capital=capi;
				c.population=pop;
				//return c;
			}
			
		}
		//return c;
		catch (Exception e) {
			e.printStackTrace();
		}
		//return c;
		return c;
	}

	public static void deleteCountryByID(int countryID) {
		try
		{
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/cs310", "root", "gofret12345");		
			//DOGRU MU?
			PreparedStatement ps =  connection.prepareStatement("delete from countries where countryId="+countryID+"");
			//idsi silinenden fazla olanlarin idsini 1 azalt. 
			
			ps.executeUpdate();
			connection.close();
			 
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void updateCountryPopulationByID (int countryID, int population) {
		try
		{
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/cs310", "root", "gofret12345");	
			//DOGRU MU?
			PreparedStatement ps =  connection.prepareStatement("update countries set population = ? where countryId = ?");
			ps.setInt(1, population); 
			ps.setInt(2, countryID);
			//while(rs.next())
			//{
				Country mycountry=getCountryByID(countryID);
				mycountry.population=population;
				
			//}
			
			ps.executeUpdate();
			connection.close();
			 
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		 ArrayList<Country>myc=readFromFile("world.txt");
		 //writeListToDB(myc);
		 Country mycountry=getCountryByID(3);
		 System.out.println(mycountry.countryName);
		 //deleteCountryByID(3);
		 System.out.println(mycountry.countryName);
		 updateCountryPopulationByID(3,15);
		 Country mycountry1=getCountryByID(3);
		 System.out.println(mycountry1.population);
		 
		 
	}

}


