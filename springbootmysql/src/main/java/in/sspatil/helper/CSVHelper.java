package in.sspatil.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import in.sspatil.model.Employee;


public class CSVHelper {



	  public static String TYPE = "text/csv";
	  static String[] HEADERs = { "Id", "Title", "Description", "Published" };

	  public static boolean hasCSVFormat(MultipartFile file) {

	    if (!TYPE.equals(file.getContentType())) {
	      return false;
	    }

	    return true;
	  }

	  public static List<Employee> readCSVFile(InputStream is) {
		  
	    try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	        CSVParser csvParser = new CSVParser(fileReader,
	            CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

	      List<Employee> employeeList = new ArrayList<Employee>();

	      Iterable<CSVRecord> csvRecords = csvParser.getRecords();

	      for (CSVRecord csvRecord : csvRecords) {	    	  	    	 
	    	  
	        Employee newEmployeeCSVRecord = new Employee(
	              Integer.parseInt(csvRecord.get("id")),
	              csvRecord.get("ename"),
	              Float.parseFloat(csvRecord.get("esal")),
	              Float.parseFloat(csvRecord.get("bonous"))
	            );

	        employeeList.add(newEmployeeCSVRecord);
	      }

	      return employeeList;
	    } catch (IOException e) {
	      throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
	    }
	  }

}
	
	
