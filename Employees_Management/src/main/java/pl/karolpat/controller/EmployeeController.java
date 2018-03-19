package pl.karolpat.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import pl.karolpat.entity.Employee;
import pl.karolpat.exception.ErrorRespone;
import pl.karolpat.exception.InstanceNotFound;
import pl.karolpat.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	
	private EmployeeService employeeService;
	
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService; 
	}
	
	@ExceptionHandler(InstanceNotFound.class)
	public ResponseEntity<ErrorRespone> handleException(Exception e) {
		ErrorRespone error = new ErrorRespone();
		error.setMessage(e.getMessage());
		error.setErrorCode(HttpStatus.PRECONDITION_FAILED.value());
		return new ResponseEntity<ErrorRespone>(error, HttpStatus.OK);
		
	}
	
	@GetMapping("")
	@ResponseBody
	public List<Employee> getAllActive(){
		return employeeService.getAllActive();
	}
	
	@GetMapping("/withDeleted")
	@ResponseBody
	public List<Employee> getAll(){
		return employeeService.getAll();
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
	
	@PutMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Employee deleteEmployee(@PathVariable("id") long id) throws InstanceNotFound {
		return employeeService.removeEmployee(id);
	}
	
	@PutMapping("/restore/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Employee restoreEmployee(@PathVariable("id") long id) throws InstanceNotFound {
		return employeeService.restoreEmployee(id);
	}
}
