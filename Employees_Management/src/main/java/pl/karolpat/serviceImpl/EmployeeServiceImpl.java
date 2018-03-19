package pl.karolpat.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.karolpat.entity.Employee;
import pl.karolpat.entity.Position;
import pl.karolpat.exception.InstanceNotFound;
import pl.karolpat.repository.EmployeeRepo;
import pl.karolpat.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepo employeeRepo;

	public EmployeeServiceImpl(EmployeeRepo employeeRepo) {
		this.employeeRepo = employeeRepo;
	}

	@Override
	public Employee addEmployee(String firstName, String lastName, Position position, String email) {
		// TODO Auto-generated method stub
		return null;
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
	@Transactional
	public Employee removeEmployee(long id) throws InstanceNotFound {

		Employee emp = findEmployeeById(id);
		emp.setActive(false);
		return employeeRepo.save(emp);

	}

	@Override
	public List<Employee> getAllActive() {
		return employeeRepo.findAllByActive(true);
	}

	@Override
	public List<Employee> getAll() {
		return employeeRepo.findAll();
	}

	public Employee findEmployeeById(long id) throws InstanceNotFound {

		Employee emp = employeeRepo.findOne(id);
		if (emp == null) {
			throw new InstanceNotFound("There is no entity with given ID");
		} else {
			return emp;
		}
	}

}
