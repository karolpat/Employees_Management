package pl.karolpat.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import pl.karolpat.entity.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {

	/**
	 * @param firstName
	 *            first name of the employees to be searched for.
	 * @param active
	 *            active status, whether or not employee is deleted.
	 * @return List of active employees with given first name.
	 */
	List<Employee> findAllByFirstNameAndActive(String firstName, boolean active);

	/**
	 * @param lastName
	 *            last name of the employees to be searched for.
	 * @param active
	 *            active status, whether or not employee is deleted.
	 * @return List of active employees with given last name.
	 */
	List<Employee> findAllByLastNameAndActive(String lastName, boolean active);

	/**
	 * @param active
	 *            active status, whether or not employee is deleted.
	 * @param pageable
	 *            pagination of results.
	 * @return List of all active employees.
	 */
	Page<Employee> findAllByActive(boolean active, Pageable pageable);

	Page<Employee> findAll(Pageable pageable);

	/**
	 * @param email
	 *            email address of the employee to be searched for.
	 * @param active
	 *            active status, whether or not employee is deleted.
	 * @return Employee that is active and owns given email address.
	 */
	Employee findOneByEmailAndActive(String email, boolean active);

	Employee findOneByEmail(String email);
}
