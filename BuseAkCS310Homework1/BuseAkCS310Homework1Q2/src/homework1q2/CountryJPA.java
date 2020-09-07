package homework1q2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.*; 

 
@Entity
public class CountryJPA {
	@Id
	int countryId;
	String countryName;
	String continent;
	String capital;
	int population;
	
	
	public CountryJPA() {
		super();
	}
	public CountryJPA(int countryId,String countryName, String continent, String capital,int population) {
		super();
		this.countryId = countryId;
		this.countryName = countryName;
		this.continent=continent;
		this.capital=capital;
		this.population=population;
		//System.out.println("okuma basarili");
	}
	
	public int getId() {
		return countryId;
	}
	public void setId(int id) {
		this.countryId=id;
	}
	public void setName(String name) {
		this.countryName=name;
	}
	public void setContinent(String cont) {
		this.continent=cont;
	}
	public void setCapital(String capi) {
		this.capital=capi;
	}
	public void setPop(int pop) {
		this.population=pop;
	}
	public static ArrayList<CountryJPA> readFromFile(String filename) {
		ArrayList<CountryJPA> countries 
		= new ArrayList<CountryJPA>();
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
				CountryJPA c = new CountryJPA(id, countryName,continent,capital,population);
				
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
	
	public static void writeListToDB(ArrayList<CountryJPA> countries) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("cs310");
		EntityManager entityManager =emf.createEntityManager();
		
		 
		
		entityManager.getTransaction().begin();
		
		for(CountryJPA c : countries) {
			 
			entityManager.persist(c);
			//entityManager.getTransaction().commit(); 
			
		}
		entityManager.getTransaction().commit();
		
		
		
	}
	
	public static CountryJPA getCountryByID(int countryID) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("cs310");
		EntityManager entityManager =emf.createEntityManager();
		
		CountryJPA mycountry=entityManager.find(CountryJPA.class, countryID);
		return mycountry;
	}
	
	public static void deleteCountryByID(int countryID) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("cs310");
		EntityManager entityManager =emf.createEntityManager();
		
		CountryJPA mycountry=entityManager.find(CountryJPA.class, countryID);
		entityManager.getTransaction().begin();
		entityManager.remove(mycountry);
		entityManager.getTransaction().commit();
	}
	
	public static void updateCountryPopulationByID(int countryID,int population) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("cs310");
		EntityManager entityManager =emf.createEntityManager();
		
		CountryJPA mycountry=entityManager.find(CountryJPA.class, countryID);
		entityManager.getTransaction().begin();
		mycountry.setPop(population);
		entityManager.getTransaction().commit();
	}
	
	public static void main(String[] args) {
		ArrayList<CountryJPA> co=readFromFile("world.txt");
		
		writeListToDB(co);
		
		CountryJPA m=getCountryByID(3);
		System.out.println(m.countryName);
		
		deleteCountryByID(3);
		
		updateCountryPopulationByID(4,15);
		
		
		
		
	}
}
