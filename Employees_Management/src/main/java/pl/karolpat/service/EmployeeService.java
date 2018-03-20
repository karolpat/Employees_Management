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

	public Page<Employee> getAllHidden(Pageable pageable);

	public Page<Employee> getAll(Pageable pageable);

	public List<Employee> getAllByFirstName(String firstName);

	public List<Employee> getAllByLastName(String lastName);

	public Employee getOneByEmail(String email);

	/**
	 * Removes employee of given id. In fact, the employee is not deleted from the
	 * database. Only active status is changed to false, so that this employee will
	 * not show in the searching.
	 * 
	 * @param id id of employee to be deleted.
	 * @return Deleted employee.
	 * @throws EmployeeNotFoundException Exception thrown in case there is no employee with given id.
	 */
	public Employee removeEmployee(long id) throws EmployeeNotFoundException;

	/** Restores deleted(hidden) employee. Changes active status again to true.
	 * @param id id of the employee to be restored.
	 * @return Restored Employee.
	 * @throws EmployeeNotFoundException Exception thrown in case there is no employee with given id.
	 */
	public Employee restoreEmployee(long id) throws EmployeeNotFoundException;
}
