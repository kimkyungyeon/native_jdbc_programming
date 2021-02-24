package native_jdbc_programming.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import native_jdbc_programming.ch01.JdbcUtil;
import native_jdbc_programming.dao.EmployeeDao;
import native_jdbc_programming.dto.Department;
import native_jdbc_programming.dto.Employee;
import native_jdbc_programming.dto.Title;

public class EmployeeDaoImpl implements EmployeeDao {
	public static EmployeeDaoImpl instance = new EmployeeDaoImpl(); // TODO Auto-generated constructor st

	private EmployeeDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	public static EmployeeDao getInstance() {
		if (instance == null) {
			instance = new EmployeeDaoImpl();
		}
		return instance;
	}

	@Override
	public List<Employee> selectEmployeeByall() {
		String sql = "select empno, empname, title_no, title_name, manager_no, manager_name, salary, deptno, deptname from vw_full_employee";
		try (Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			if (rs.next()) {

				List<Employee> list = new ArrayList<>();
				do {
					list.add(getEmployee(rs));
				} while (rs.next());
				return list;

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

//	private Employee getEmployee(ResultSet rs) throws SQLException {
//		int empNo = rs.getInt("empno");
//		String empName = rs.getString("empname");
//		Title title = new Title(rs.getInt("title_no"));
//
//		Employee manager = new Employee(rs.getInt("manager_no"));
//		
//		int salary = rs.getInt("salary");
//		Department dept = new Department(rs.getInt("deptno"));
//	if (rs.getString("title_name") != null) {
//		title.settName(rs.getString("title_name"));
//	}
//	if (rs.getString("manager_name") != null) {
//		manager.setEmpName(rs.getString("manager_name"));
//	}
//		if (rs.getString("deptname") != null) {
//			dept.setDeptName(rs.getString("deptname"));	
//		}
//
//		return new Employee(empNo, empName, title, manager, salary, dept);
//	}
	private Employee getEmployee(ResultSet rs) throws SQLException {
		int empNo = rs.getInt("empno");
		String empName = rs.getString("empname");
		Title title = new Title(rs.getInt("title_no"));
		Employee manager = new Employee(rs.getInt("manager_no"));
		int salary = rs.getInt("salary");
		Department dept = new Department(rs.getInt("deptno"));

		try {
			title.settName(rs.getNString("title_name"));
		} catch (SQLException e) {
		}
		try {
			manager.setEmpName(rs.getNString("manager_name"));
		} catch (SQLException e) {
		}
		try {
			dept.setDeptName(rs.getNString("deptname"));
		} catch (SQLException e) {
		}

		return new Employee(empNo, empName, title, manager, salary, dept);
	}

	@Override
	public Employee selectEmployeeByNo(Employee employee) {
		String sql = "select empno, empname, title as title_no , manager as manager_no, salary, dept as deptno from employee where empno = ?";
		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, employee.getEmpNo());
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next())
					return getEmployee(rs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int insertEmployee(Employee employee) {
		String sql = "Insert into employee values(?,?,?,?,?,?)";
		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, employee.getEmpNo());
			pstmt.setString(2, employee.getEmpName());
			pstmt.setInt(3, employee.getTitle().gettNo());
			pstmt.setInt(4, employee.getManager().getEmpNo());
			pstmt.setInt(5, employee.getSalary());
			pstmt.setInt(6, employee.getDept().getDeptNo());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}

	@Override
	public int updateEmployee(Employee employee) {
		String sql = "update employee set empname = ? where empno = ?";
		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, employee.getEmpName());
			pstmt.setInt(2, employee.getEmpNo());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteEmployee(Employee employee) {
		String sql = "delete from employee where empno = ?";

		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, employee.getEmpNo());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}

	@Override
	public List<Employee> selectEmployeeGroupByDept(Department department) {
		String sql = "select empno,empname,title as title_no,manager as manager_no,salary,dept as deptno from employee where dept = (select deptno from department where deptno=?)";
		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, department.getDeptNo());
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					List<Employee> list = new ArrayList<Employee>();
					do {
						list.add(getEmployee(rs));
					} while (rs.next());
					return list;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}
}
