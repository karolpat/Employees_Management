package pl.karolpat.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pl.karolpat.entity.Employee;
import pl.karolpat.entity.Position;
import pl.karolpat.exception.EmployeeNotFoundException;
import pl.karolpat.exception.NonuniqueEmailException;

public interface EmployeeService {

	public Employee addEmployee(String firstName, String lastName, Position position, String email)
			throws NonuniqueEmailException;

	public Page<Employee> getAllActive(Pageable pageable);

	public Page<Employee> getAll(Pageable pageable);

	public List<Employee> getAllByFirstName(String firstName);

	public List<Employee> getAllByLastName(String lastName);

	public Employee getOneByEmail(String email);

	public Employee removeEmployee(long id) throws EmployeeNotFoundException;

	public Employee restoreEmployee(long id) throws EmployeeNotFoundException;
}
