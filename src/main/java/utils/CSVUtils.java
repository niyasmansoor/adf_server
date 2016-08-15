package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

//import pstsassignment.ADFConstants;

import com.opencsv.CSVReader;

public class CSVUtils {
	
	@SuppressWarnings("unused")
	private  FileReader nameCSVReader;
	@SuppressWarnings("unused")
	private  FileReader zipCodeCSVReader;
	
	private String[] zipRow;
	
	public CSVUtils(){
		ClassLoader classLoader  = CSVUtils.class.getClassLoader();
		File names = new File(classLoader.getResource("datasources/names.csv").getFile());
		File zipCodes = new File(classLoader.getResource("datasources/zipcodes.csv").getFile());
		//System.out.println(names.getPath());
		try {
			nameCSVReader = new FileReader(names);
			zipCodeCSVReader = new FileReader(zipCodes);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String getName(String name) throws IOException{
		@SuppressWarnings("resource")
		CSVReader csvReader = new CSVReader(nameCSVReader);
		String[] nextLine;
	      while ((nextLine = csvReader.readNext()) != null) {
	         if (nextLine != null) {
	            //Verifying the read data here
	            if(ADFConstants.CSV_NAME_SEARCH_INDEX==Arrays.binarySearch(nextLine, name)){
	            	//System.out.println("Value '"+name+"' found in search !");
	            	return name;
	            }           	
	            
	            }
	         }
	       
		return "";
	}
	
	public boolean validateFirstName(String name){
		String firstName = "";
		try {
			firstName =  this.getName(name);
			if(name.equalsIgnoreCase(firstName))
				return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean validateLastName(String name){
		String lastName = "";
		try {
			lastName =  this.getName(name);
			if(name.equalsIgnoreCase(lastName))
				return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean validateZipCode(String zipCode) throws IOException{
		@SuppressWarnings("resource")
		CSVReader csvReader = new CSVReader(zipCodeCSVReader);
		String[] nextLine;
	      while ((nextLine = csvReader.readNext()) != null) {
	         if (nextLine != null) {
	        	 if(nextLine[ADFConstants.CSV_ZIPCODE_INDEX].trim().equalsIgnoreCase(zipCode)){
	        	 this.zipRow = nextLine;
	        	 //System.out.println(Arrays.toString(zipRow));
	        	 return true;
	        	 }
	            }
	         }
	       
		return false;
	}
	
	public boolean validateCity(String city) throws IOException{
		
	         if (this.zipRow != null) {
	        	 if(this.zipRow[ADFConstants.CSV_CITY_INDEX].trim().equalsIgnoreCase(city)){
	        	 //System.out.println(this.zipRow[ADFConstants.CSV_CITY_INDEX]);
	        	 return true;
	        	 }
	            }	         
	       
		return false;
	}
	
	public boolean validateState(String state) throws IOException{
		
        if (this.zipRow != null) {
       	 if(this.zipRow[ADFConstants.CSV_STATE_INDEX].trim().equalsIgnoreCase(state)){
       	 //System.out.println(this.zipRow[ADFConstants.CSV_STATE_INDEX]);
       	 return true;
       	 }
           }	         
      
	return false;
}
	
	public boolean validateCityState(String city,String state) throws IOException{
		
		if(this.validateCity(city) && this.validateState(state))
			return true;
		
		return false;
	}
	
	public static void main(String[] args){
		CSVUtils util = new CSVUtils();
		try {
			util.validateZipCode("96863");
			util.validateCity("M C B H Kaneohe Bay");
			util.validateState("HI");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String[] getZipRow() {
		return zipRow;
	}

	public void setZipRow(String[] zipRow) {
		this.zipRow = zipRow;
	}

}
