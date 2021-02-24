package native_jdbc_programming.ch01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import native_jdbc_programming.dto.Department;
import native_jdbc_programming.util.JdbcUtil;

/**
 * JDBC try-catch-resource 2021.2.15
 *
 */
public class JdbcConEx4 {
	public static void main(String[] args) {

		ArrayList<Department> list = null;


		String sql = "select deptno, deptname, floor from department";
		
		try (Connection con = JdbcUtil.getConnection();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);) {
			// 1. JDBC 드라이버 로딩
			Class.forName("com.mysql.jdbc.Driver");
			// 2. 데이터베이스 커넥션 생성
			System.out.println("con: " + con);
			// 3.statement 생성

			System.out.println("stmt: " + stmt);

			// 4. 쿼리 실행
			
			

			// 5. 쿼리 실행결과 출력
			list = new ArrayList<>();
			while (rs.next()) {
				list.add(getDepartment(rs));

//				Department dept = getDepartment(rs);
//				System.out.println(dept);
//				int deptNo = rs.getInt("deptno");
//				String deptName = rs.getNString("deptname");
//				int floor = rs.getInt("floor");
//				System.out.printf("%d %s %d%n",deptNo, deptName, floor);
			}

		} catch (ClassNotFoundException e) {
			System.err.println("JDBC Driver Not Found");
		} catch (SQLException e) {
			e.printStackTrace();

		}

		System.out.println("Departmet Query 결과는");
		for (Department d : list) {
			System.out.println(d);
		}
	}

	private static Department getDepartment(ResultSet rs) throws SQLException {
		int deptNo = rs.getInt("deptno");
		String deptName = rs.getString("deptname");
		int floor = rs.getInt("floor");
		return new Department(deptNo, deptName, floor);
	}
}
