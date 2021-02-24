package native_jdbc_programming.dao;

import java.util.List;

import native_jdbc_programming.dto.Department;
import native_jdbc_programming.dto.Employee;

public interface EmployeeDao {
	List<Employee> selectEmployeeByall();
	Employee selectEmployeeByNo(Employee employee);
	List<Employee> selectEmployeeGroupByDept(Department department);
	
	
	int insertEmployee(Employee employee);
	int updateEmployee(Employee employee);
	int deleteEmployee(Employee employee);
}
