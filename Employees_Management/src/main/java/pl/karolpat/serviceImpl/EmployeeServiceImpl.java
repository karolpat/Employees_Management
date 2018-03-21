package pl.karolpat.serviceImpl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.karolpat.entity.Employee;
import pl.karolpat.entity.Position;
import pl.karolpat.exception.EmployeeNotFoundException;
import pl.karolpat.exception.NonuniqueEmailException;
import pl.karolpat.repository.EmployeeRepo;
import pl.karolpat.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepo employeeRepo;

	public EmployeeServiceImpl(EmployeeRepo employeeRepo) {
		this.employeeRepo = employeeRepo;
	}

	@Override
	@Transactional(rollbackFor=NonuniqueEmailException.class)
	public Employee addEmployee(String firstName, String lastName, Position position, String email)
			throws NonuniqueEmailException {
		Employee emp = new Employee();

		emp.setFirstName(firstName).setLastName(lastName).setPosition(position).setEmail(checkEmail(email))
				.setActive(true);
		;

		return employeeRepo.save(emp);
	}

	@Override
	public List<Employee> getAllByFirstName(String firstName) {
		return employeeRepo.findAllByFirstNameAndActive(firstName, true);
	}

	@Override
	public List<Employee> getAllByLastName(String lastName) {
		return employeeRepo.findAllByLastNameAndActive(lastName, true);
	}

	@Override
	public Employee getOneByEmail(String email) {
		return employeeRepo.findOneByEmailAndActive(email, true);
	}

	@Override
	@Transactional(rollbackFor=EmployeeNotFoundException.class)
	public Employee removeEmployee(long id) throws EmployeeNotFoundException {

		Employee emp = findEmployeeById(id);
		emp.setActive(false);
		return employeeRepo.save(emp);
	}

	@Override
	@Transactional(rollbackFor=EmployeeNotFoundException.class)
	public Employee restoreEmployee(long id) throws EmployeeNotFoundException {

		Employee emp = findEmployeeById(id);
		emp.setActive(true);
		return employeeRepo.save(emp);
	}

	@Override
	public Page<Employee> getAllActive(Pageable pageable) {
		return employeeRepo.findAllByActive(true, pageable);
	}

	@Override
	public Page<Employee> getAllHidden(Pageable pageable) {
		return employeeRepo.findAllByActive(false, pageable);
	}

	@Override
	public Page<Employee> getAll(Pageable pageable) {
		return employeeRepo.findAll(pageable);
	}

	private Employee findEmployeeById(long id) throws EmployeeNotFoundException {

		Employee emp = employeeRepo.findOne(id);
		if (emp == null) {
			throw new EmployeeNotFoundException();
		} else {
			return emp;
		}
	}

	private String checkEmail(String email) throws NonuniqueEmailException {

		Employee emp = employeeRepo.findOneByEmail(email);
		if (emp != null) {
			throw new NonuniqueEmailException();
		} else {
			return email;
		}
	}

}
