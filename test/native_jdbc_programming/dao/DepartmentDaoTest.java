package native_jdbc_programming.dao;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import native_jdbc_programming.dao.impl.DepartmentDaoImpl;
import native_jdbc_programming.dto.Department;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DepartmentDaoTest {
	private static DepartmentDao dao = DepartmentDaoImpl.getInstance();
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test04SelectDepartmentByAll() {
		System.out.printf("%s()%n", "testSelectDepartmentByAll");
		List<Department> deptList = dao.selectDepartmentByAll();
		Assert.assertNotNull(deptList);
		
		for(Department d : deptList) {
			System.out.println(d);
		}
	}

	@Test
	public void test05SelectDepartmentByNo() {
		
		System.out.printf("%s()%n", "testSelectDepartmentByNo");
		Department newDept = new Department(3);
		Department searchDept = dao.selectDepartmentByNo(newDept);
		Assert.assertNotNull(searchDept);
		System.out.println(searchDept);
	}

	@Test
	public void test01InsertDepartment() {
		System.out.printf("%s()%n", "testInsertDepartment");
		Department newDept = new Department(6,"인사",20);
		int res = dao.insertDepartment(newDept);
		Assert.assertEquals(1, res);
		System.out.println(dao.selectDepartmentByNo(newDept));
	}

	@Test
	public void test02UpdateDepartment() {
		System.out.printf("%s()%n", "testUpdateDepartment");
		Department newDept = new Department(6,"CS",20);
		int res = dao.updateDepartment(newDept);
		Assert.assertEquals(1, res);
		System.out.println(dao.selectDepartmentByNo(newDept));
	}
	

	@Test
	public void test03DeleteDepartment() {
		System.out.printf("%s()%n", "testDeleteDepartment");
		int res = dao.deleteDepartment(6);
		Assert.assertEquals(1, res);
		
		dao.selectDepartmentByAll().stream().forEach(System.out::println);
	}

}
