package native_jdbc_programming.dao;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import native_jdbc_programming.dao.impl.EmployeeDaoImpl;
import native_jdbc_programming.dto.Department;
import native_jdbc_programming.dto.Employee;
import native_jdbc_programming.dto.Title;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class EmployeeDaoTest {
	private static EmployeeDao dao = EmployeeDaoImpl.getInstance();

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test04SelectEmployeeByall() {
		System.out.printf("%s()%n", "testSelectEmployeeByAll");
		List<Employee> empList = dao.selectEmployeeByall();
		Assert.assertNotNull(empList);

		for (Employee e : empList) {
			System.out.println(e);
		}
	}

	@Test
	public void test05SelectEmployeeByNo() {
		System.out.printf("%s()%n", "testSelectEmployeeByNo");
		Employee newEmp = new Employee(2106);
		Employee searchEmp = dao.selectEmployeeByNo(newEmp);
		Assert.assertNotNull(searchEmp);
		System.out.println(searchEmp);
	}

	@Test
	public void test01InsertEmployee() {
		System.out.printf("%s()%n", "testInsertEmployee");
		Title title = new Title(5, "사원");
		Employee manager = new Employee(4377);
		Department department = new Department(2);
		Employee newEmp = new Employee(4388, "김경연", title, manager, 3000000, department);
		int res = dao.insertEmployee(newEmp);
		Assert.assertEquals(1, res);

//		System.out.println(newEmp);
//		System.out.println();
		List<Employee> ls = dao.selectEmployeeByall();
		for(Employee e : ls) {
			System.out.println(e);
		}
	}

	@Test
	public void test02UpdateEmployee() {
		System.out.printf("%s()%n", "testUpdateEmployee");
		Employee newEmp = new Employee(4388, "이종윤", new Title(5, "사원"), new Employee(4377), 3000000, new Department(2));
		int res = dao.updateEmployee(newEmp);
		Assert.assertEquals(1, res);
		System.out.println(newEmp);
	}

	@Test
	public void test03DeleteEmployee() {
		System.out.printf("%s()%n", "testDeleteEmployee");
		int res = dao.deleteEmployee(new Employee(4388));
		Assert.assertEquals(1, res);

	}
	
	@Test
	public void test06selectbygroup() {
		System.out.printf("%s()%n", "testselectbygroup");
		Department dept = new Department(3);
		List<Employee> list = dao.selectEmployeeGroupByDept(dept);
		
		Assert.assertNotNull(list);
		System.out.println(list);

	}
}
