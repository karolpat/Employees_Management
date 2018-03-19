package pl.karolpat.service;

import java.util.List;

import pl.karolpat.entity.Employee;
import pl.karolpat.entity.Position;
import pl.karolpat.exception.InstanceNotFound;

public interface EmployeeService {

	public Employee addEmployee(String firstName, String lastName, Position position, String email);

	public List<Employee> getAllActive();
	public List<Employee> getAll();

	public List<Employee> getAllByFirstName(String firstName);

	public List<Employee> getAllByLastName(String lastName);

	public Employee getOneByEmail(String email);

	public Employee removeEmployee(long id) throws InstanceNotFound;
}
