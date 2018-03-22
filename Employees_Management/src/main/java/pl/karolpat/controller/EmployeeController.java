package pl.karolpat.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import pl.karolpat.entity.Employee;
import pl.karolpat.entity.Position;
import pl.karolpat.exception.EmployeeNotFoundException;
import pl.karolpat.exception.ErrorRespone;
import pl.karolpat.exception.NonuniqueEmailException;
import pl.karolpat.exception.PositionNotFoundException;
import pl.karolpat.service.EmployeeService;
import pl.karolpat.service.PositionService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	private EmployeeService employeeService;
	private PositionService positionService;

	public EmployeeController(EmployeeService employeeService, PositionService positionService) {
		this.employeeService = employeeService;
		this.positionService = positionService;
	}

	/**
	 * Exception handling in case searching for the Employee by given id results as
	 * null.
	 * 
	 * @param e
	 *            - Exception thrown by any method.
	 * @return HttpStatus number and a message.
	 */
	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<ErrorRespone> handlePreconditionException(Exception e) {
		ErrorRespone error = new ErrorRespone();
		error.setMessage(e.getMessage());
		error.setErrorCode(HttpStatus.PRECONDITION_FAILED.value());
		return new ResponseEntity<ErrorRespone>(error, HttpStatus.OK);

	}

	/**
	 * Exception handling in case an internal server error occurs. It would be
	 * caused by given email that is nonunique or position id that does not exist.
	 * 
	 * @param e
	 *            - Exception thrown by any method.
	 * @return HttpStatus number and a message.
	 */
	@ExceptionHandler({ PositionNotFoundException.class, NonuniqueEmailException.class })
	public ResponseEntity<ErrorRespone> handleServerErrorException(Exception e) {
		ErrorRespone error = new ErrorRespone();
		error.setMessage(e.getMessage());
		error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<ErrorRespone>(error, HttpStatus.OK);
	}

	/**
	 * @param pageable params of the page. page=? and size=?
	 * @return List of active employees
	 */
	@GetMapping("")
	@ResponseBody
	public Page<Employee> getAllActive(Pageable pageable) {
		Page<Employee> page = employeeService.getAllActive(pageable);
		return page;
	}
	
	/** Shows list of only deleted employees.
	 * @param pageable params of the page. page=? and size=?
	 * @return List of deleted employees.
	 */
	@GetMapping("/deleted")
	@ResponseBody
	public Page<Employee> getAllDeleted(Pageable pageable) {
		Page<Employee> page = employeeService.getAllHidden(pageable);
		return page;
	}

	/** Shows list of all employees whether or not employee is removed (hidden).
	 * @param pageable params of the page. page=? and size=?
	 * @return List of all employees.
	 */
	@GetMapping("/withDeleted")
	@ResponseBody
	public Page<Employee> getAll(Pageable pageable) {
		return employeeService.getAll(pageable);
	}

	@GetMapping("/firstName/{firstName}")
	@ResponseBody
	public List<Employee> employeesByFirstName(@PathVariable("firstName") String firstName) {
		return employeeService.getAllByFirstName(firstName);
	}

	@GetMapping("/lastName/{lastName}")
	@ResponseBody
	public List<Employee> employeesByLastName(@PathVariable("lastName") String lastName) {
		return employeeService.getAllByLastName(lastName);
	}

	@GetMapping("/email/{email:.+}")
	@ResponseBody
	public Employee employeeByemail(@PathVariable("email") String email) {
		return employeeService.getOneByEmail(email);
	}

	/**
	 * Changing employee active status to false. Thanks to that, such employee will
	 * not be present in searching for employees by first name, last name or email
	 * but remains in the database and is easy to restore. No employee disappears.
	 * 
	 * @param id
	 *            id of employee to delete (hide in fact).
	 * @return Deleted employee.
	 * @throws EmployeeNotFoundException
	 *             in case there is no employee with given id.
	 */
	@PutMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Employee deleteEmployee(@PathVariable("id") long id) throws EmployeeNotFoundException {
		return employeeService.removeEmployee(id);
	}

	/**
	 * Changing employee active status to true so that this employee will be present
	 * in searching for employees by first name, last name or email again.
	 * 
	 * @param id
	 *            id of employee to be restored.
	 * @return Restored employee.
	 * @throws EmployeeNotFoundException
	 *             in case there is no employee with given id.
	 */
	@PutMapping("/restore/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Employee restoreEmployee(@PathVariable("id") long id) throws EmployeeNotFoundException {
		return employeeService.restoreEmployee(id);
	}

	/**
	 * Adding new Employee to database.
	 * 
	 * @param firstName
	 *            first name of new Employee - String
	 * @param lastName
	 *            last name of new Employee - String
	 * @param position
	 *            position of new Employee - long (id of the position)
	 * @param email
	 *            email of new Employee - String
	 * @return Just added new employee.
	 * @throws PositionNotFoundException
	 *             in case there is no position of given id.
	 * @throws NonuniqueEmailException
	 *             in case given email is already present.
	 */
	@PostMapping("/add/{firstName}/{lastName}/{position}/{email:.+}")
	@ResponseStatus(HttpStatus.CREATED)
	public Employee addNewEmployee(@PathVariable("firstName") String firstName,
			@PathVariable("lastName") String lastName, @PathVariable("position") long position,
			@PathVariable("email") String email) throws PositionNotFoundException, NonuniqueEmailException {

		Position pos = positionService.findOneById(position);
		return employeeService.addEmployee(firstName, lastName, pos, email);
	}
}
