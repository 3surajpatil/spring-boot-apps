package in.sspatil.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import in.sspatil.helper.CSVHelper;
import in.sspatil.message.ResponseMessage;
import in.sspatil.model.Employee;
import in.sspatil.service.EmployeeService;


@RestController
@CrossOrigin("http://localhost:8080")
@RequestMapping("/api")
public class EmployeeController {

@Autowired	
EmployeeService employeeService;	
	
	@PostMapping(path="/saveEmployee")
	public ResponseEntity saveEmployee(@RequestBody Employee e)
	{						
			Employee employeeDetailsSaved=employeeService.saveEmployee(e);
			
			HttpHeaders headers = new HttpHeaders();
	        headers.add("Responded", "EmployeeController");
	        
	        return ResponseEntity.accepted().headers(headers).body(employeeDetailsSaved);	       			
	}
	
	@GetMapping(path="/getAllEmployee")
	public ResponseEntity getAllEmployee()
	{		
		try {
		      List<Employee> employeeList = employeeService.getAllEmployee();
		      if (employeeList.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }		      
		      return new ResponseEntity<>(employeeList, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }					
	}
	
	
	@PostMapping("/csv/upload")
	  public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
	    String message = "";

	    if (CSVHelper.hasCSVFormat(file)) {
	      try {
	        employeeService.saveEmployeesFromCSVFile(file);

	        message = "Uploaded the file successfully: " + file.getOriginalFilename();
	        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
	      } catch (Exception e) {
	    	  e.printStackTrace();
	        message = "Could not upload the file: " + file.getOriginalFilename() + "!";
	        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
	      }
	    }

	    message = "Please upload a csv file!";
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
	  }
}
