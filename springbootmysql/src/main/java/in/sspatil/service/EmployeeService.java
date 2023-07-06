package in.sspatil.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import in.sspatil.helper.CSVHelper;
import in.sspatil.model.Employee;
import in.sspatil.repository.EmployeeRepository;

@Component
public class EmployeeService {

@Autowired	
EmployeeRepository empRepository;	
	

	public void saveEmployeesFromCSVFile(MultipartFile file) {
		try {
			List<Employee> employeeListFromCSVFile = CSVHelper.readCSVFile(file.getInputStream());
			empRepository.saveAll(employeeListFromCSVFile);
		} catch (IOException e) {
			throw new RuntimeException("fail to store csv data: " + e.getMessage());
		}
	}

	
	public Employee saveEmployee(Employee e) {			
		return empRepository.save(e);				
	}
	
	public List<Employee> getAllEmployee()
	{
	
		return empRepository.findAll();
	}
	
}
