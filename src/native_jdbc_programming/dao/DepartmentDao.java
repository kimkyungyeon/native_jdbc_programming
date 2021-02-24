package native_jdbc_programming.dao;

import java.util.List;

import native_jdbc_programming.dto.Department;
import native_jdbc_programming.dto.Employee;

public interface DepartmentDao {
	List<Department> selectDepartmentByAll();
	Department selectDepartmentByNo(Department department);
	
	
	int insertDepartment(Department department);
	int updateDepartment(Department department);
	int deleteDepartment(int deptNo);
	
}
