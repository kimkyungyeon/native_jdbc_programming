package nativa_jdbc_programming.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


import native_jdbc_programming.dao.impl.DepartmentDaoImpl;
import native_jdbc_programming.dto.Department;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DepartmentUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JPanel pDept;
	private JPanel pBtn;
	private JPanel pList;
	private JLabel lblDeptNo;
	private JTextField tfDeptno;
	private JLabel lblDeptName;
	private JTextField tfDeptName;
	private JLabel lblFloor;
	private JTextField tfFloor;
	private JButton btnAdd;
	private JButton btnCancel;
	private JScrollPane scrollPane;
	private JTable deptTable;
	private JButton btnUpdate;
	private JButton btnDelete;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DepartmentUI frame = new DepartmentUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DepartmentUI() {
		setTitle("부서정보");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		pDept = new JPanel();
		contentPane.add(pDept);
		pDept.setLayout(new GridLayout(0, 2, 10, 0));

		lblDeptNo = new JLabel("부서 번호");
		lblDeptNo.setHorizontalAlignment(SwingConstants.RIGHT);
		pDept.add(lblDeptNo);

		tfDeptno = new JTextField();
		pDept.add(tfDeptno);
		tfDeptno.setColumns(10);

		lblDeptName = new JLabel("부서명");
		lblDeptName.setHorizontalAlignment(SwingConstants.RIGHT);
		pDept.add(lblDeptName);

		tfDeptName = new JTextField();
		pDept.add(tfDeptName);
		tfDeptName.setColumns(10);

		lblFloor = new JLabel("위치");
		lblFloor.setHorizontalAlignment(SwingConstants.RIGHT);
		pDept.add(lblFloor);

		tfFloor = new JTextField();
		pDept.add(tfFloor);
		tfFloor.setColumns(10);

		pBtn = new JPanel();
		contentPane.add(pBtn);

		btnAdd = new JButton("추가");
		btnAdd.addActionListener(insertDept());
		pBtn.add(btnAdd);

		btnUpdate = new JButton("수정");
		btnUpdate.addActionListener(updateDept());
		pBtn.add(btnUpdate);

		btnDelete = new JButton("삭제");
		btnDelete.addActionListener(delete);
		pBtn.add(btnDelete);

		btnCancel = new JButton("취소");
//		btnCancel.addActionListener();
		pBtn.add(btnCancel);

		pList = new JPanel();
		contentPane.add(pList);
		pList.setLayout(new BorderLayout(0, 0));

		scrollPane = new JScrollPane();
		pList.add(scrollPane, BorderLayout.CENTER);

		deptTable = new JTable();

		deptTable.setModel(getModel());
		scrollPane.setViewportView(deptTable);
	}

	ActionListener delete = new Delete();
//	ActionListener update = new Update();
	
	//수정
	private ActionListener updateDept() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Department newDept = getDepartment();
				DepartmentDaoImpl.getInstance().updateDepartment(newDept);
				deptTable.setModel(getModel());
				JOptionPane.showMessageDialog(null, "수정 완료");
			}
		};
	}
	
	//추가
	private ActionListener insertDept() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Department newDept = getDepartment();
				DepartmentDaoImpl.getInstance().insertDepartment(newDept);
				deptTable.setModel(getModel());
				JOptionPane.showMessageDialog(null, "추가 완료");
			}
		};
	}

	class Delete implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Department newDept = getDepartment();
			DepartmentDaoImpl.getInstance().deleteDepartment(newDept.getDeptNo());
			deptTable.setModel(getModel());
			JOptionPane.showMessageDialog(null, "삭제 완료");
		}

	}
//	삭제
//	private ActionListener deleteDept() {
//		return new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				Department newDept = getDepartment();
//				DepartmentDaoImpl.getInstance().deleteDepartment(newDept.getDeptNo());
//				deptTable.setModel(getModel());
//				JOptionPane.showMessageDialog(null, "삭제 완료");
//			}
//		};
//	}

	public String[] getColumnNames() {
		return new String[] { "부서 번호", "부서명", "위치" };
	}

	public Object[][] getDepartments() {
		List<Department> list = DepartmentDaoImpl.getInstance().selectDepartmentByAll();
		Object[][] arr = new Object[list.size()][];
		for (int i = 0; i < list.size(); i++) {
			Department dept = list.get(i);
			arr[i] = new Object[] { dept.getDeptNo(), dept.getDeptName(), dept.getFloor() };
		}
		return arr;
	}

//	@Override
//	public void actionPerformed(ActionEvent e) {
//		Department newDept = getDepartment();
//		DepartmentDaoImpl.getInstance().insertDepartment(newDept);
//		deptTable.setModel(getModel());
//		JOptionPane.showMessageDialog(null, "추가 완료");
//	}

	public DefaultTableModel getModel() {
		return new DefaultTableModel(getDepartments(), getColumnNames());

	}

	private Department getDepartment() {
		int deptNo = Integer.parseInt(tfDeptno.getText().trim());
		String deptName = tfDeptName.getText().trim();
		int floor = Integer.parseInt(tfFloor.getText().trim());
		return new Department(deptNo, deptName, floor);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
