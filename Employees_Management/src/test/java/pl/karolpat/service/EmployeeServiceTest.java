package pl.karolpat.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import pl.karolpat.entity.Employee;
import pl.karolpat.entity.Position;
import pl.karolpat.exception.EmployeeNotFoundException;
import pl.karolpat.repository.EmployeeRepo;
import pl.karolpat.serviceImpl.EmployeeServiceImpl;

public class EmployeeServiceTest {

	private EmployeeRepo employeeRepo;
	private EmployeeService employeeService;

	private Employee emp;
	private Employee emp2;
	private Employee emp3;
	private Employee emp4;
	private Position position;
	private Pageable pageable;
	private List<Employee> list;
	private List<Employee> listOfHiddenEmployees;

	@Before
	public void setUp() throws Exception {
		employeeRepo = mock(EmployeeRepo.class);
		employeeService = new EmployeeServiceImpl(employeeRepo);
		emp = new Employee("karol.pat@gmail.com", 1);
		emp2 = new Employee("email.email@gmail.com", 2);
		emp3 = new Employee("karol.patecki@gmail.com", 3);
		emp4 = new Employee("email.email.email@gmail.com", 4);
		position = new Position("Bodyguard");
		emp.setFirstName("Jan").setLastName("Kowalski").setPosition(position).setActive(true);
		emp2.setFirstName("Jan").setLastName("Kowalski").setPosition(position).setActive(true);
		emp3.setFirstName("Elon").setLastName("Musk").setActive(false);
		emp4.setFirstName("Elon").setLastName("Musk").setActive(false);
		list = Arrays.asList(emp, emp2);
		listOfHiddenEmployees = Arrays.asList(emp3, emp4);

	}

	@Test
	public final void whenGetAllByFirstName_shouldReturnList_whereAllEmployeesFirstNamesAreLikeGiven() {

		String firstNameTest = "Jan";

		when(employeeRepo.findAllByFirstNameAndActive(firstNameTest, true)).thenReturn(list);

		List<Employee> result = employeeService.getAllByFirstName(firstNameTest);

		assertEquals(2, result.size());
		assertThat(result.get(0).getEmail()).isNotEqualTo(result.get(1).getEmail());
	}

	@Test
	public final void whenGetAllByLastName_shouldReturnList_whereAllEmployeesLastNamesAreLikeGiven() {

		String lastNameTest = "Kowalski";

		when(employeeRepo.findAllByLastNameAndActive(lastNameTest, true)).thenReturn(list);

		List<Employee> result = employeeService.getAllByLastName(lastNameTest);

		assertEquals(2, result.size());
		assertThat(result.get(0).getEmail()).isNotEqualTo(result.get(1).getEmail());
	}

	@Test
	public final void whenGetOneByEmail_shouldReturnOnlyOneEmployee() {

		String emailTest = "email.email@gmail.com";

		when(employeeRepo.findOneByEmailAndActive(emailTest, true)).thenReturn(emp2);

		Employee result = employeeService.getOneByEmail(emailTest);

		assertNotNull(result);
		assertEquals("Jan", result.getFirstName());
	}

	@Test
	public final void whenRemoveeEmployee_shouldSetActiveToFalse() {

		when(employeeRepo.findOne(1l)).thenReturn(emp);
		when(employeeRepo.save(emp)).thenReturn(emp);
		Employee result = employeeService.removeEmployee(1);

		assertThat(result).isNotNull();
		assertThat(result.isActive()).isEqualTo(false);
	}

	@Test
	public final void whenRemoveeEmployee_withIdNotPresentInDb_shouldThrowException() {

		when(employeeRepo.findOne(2l)).thenReturn(null);
		assertThatThrownBy(() -> employeeService.removeEmployee(2)).isInstanceOf(EmployeeNotFoundException.class)
				.hasMessage("There is no employee with such an ID.");
	}

	@Test
	public final void whenRestoreEmployee_shouldSetActiveToTrue() {

		when(employeeRepo.findOne(1l)).thenReturn(emp);
		when(employeeRepo.save(emp)).thenReturn(emp);
		Employee result = employeeService.restoreEmployee(1);

		assertThat(result).isNotNull();
		assertThat(result.isActive()).isEqualTo(true);
	}

	@Test
	public final void whenRestoreEmployee_withIdNotPresentInDb_shouldThrowException() {

		when(employeeRepo.findOne(2l)).thenReturn(null);
		assertThatThrownBy(() -> employeeService.restoreEmployee(2)).isInstanceOf(EmployeeNotFoundException.class)
				.hasMessage("There is no employee with such an ID.");
	}

	@Test
	public final void whenGetAllActive_shouldReturnOnlyWithActiveTrue() {

		Page<Employee> page = new PageImpl<Employee>(list, new PageRequest(1, 2), list.size());
		when(employeeRepo.findAllByActive(true, pageable)).thenReturn(page);

		Page<Employee> result = employeeService.getAllActive(pageable);

		assertEquals(2, result.getSize());
		assertThat(result.getContent().get(1).isActive()).isEqualTo(true);
	}

	@Test
	public final void whenGetAllHidden_shouldReturnOnlyWithActiveFalse() {

		Page<Employee> page = new PageImpl<Employee>(listOfHiddenEmployees, new PageRequest(1, 2),
				listOfHiddenEmployees.size());

		when(employeeRepo.findAllByActive(false, pageable)).thenReturn(page);

		Page<Employee> result = employeeService.getAllHidden(pageable);

		assertEquals(2, result.getSize());
		assertThat(result.getContent().get(1).isActive()).isEqualTo(false);
	}

}
