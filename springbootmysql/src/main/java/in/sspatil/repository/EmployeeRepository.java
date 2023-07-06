package in.sspatil.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.sspatil.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Integer>{

	
	// save(), findOne(), findById(), findAll(), count(), delete(), deleteById()
}
