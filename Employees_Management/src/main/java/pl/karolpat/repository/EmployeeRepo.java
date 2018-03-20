package pl.karolpat.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import pl.karolpat.entity.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {

	List<Employee> findAllByFirstNameAndActive(String firstName, boolean active);

	List<Employee> findAllByLastNameAndActive(String lastName, boolean active);
	
	Page<Employee> findAllByActive(boolean active, Pageable pageable);
	
	Page<Employee> findAll(Pageable pageable);

	Employee findOneByEmailAndActive(String email, boolean active);
	
	Employee findOneByEmail(String email);
}
