select user(),database();
show tables;

desc title;
desc department;
desc employee;

truncate table department;
desc employee;
select * from title;
select empno,empname,title,manager,salary,dept from employee;
select deptno,deptname,floor from department;
select tno,tname from title;
select tno,tname from title where tno=1;
delete from department where deptno = 7;
update title set tname = '계약직' where tno = 6;
update department set deptname = 'dd',floor = 20 where deptno = 7;
insert into department values(7,"dfsdf",8);
delete from employee where empno = 4388;
delete from title where tno = 6;

select * from employee;

insert into title values(6, '인턴');

create view vw_employee
(empno,empname,tno,tname,manager,mgr_name,salary,deptno,deptname,floor)
as
select e.empno, e.empname, e.title, t.tname, m.empno,m.empname
		,e.salary,d.deptno,d.deptname,d.floor
from employee e join title t on e.title = t.tno
	left join employee m on e.manager = m.empno 
	join department d on e.dept = d.deptno ;
	
select empno,empname,tno,tname,manager,mgr_name,salary,deptno,deptname,floor from vw_employee ;


select * from department;
select * from employee;

drop view vw_full_employee; 

create view vw_full_employee
as
select e.empno,
		e.empname,
		t.tno as title_no,
		t.tname as title_name,
		m.empno as manager_no,
		m.empname as manager_name,
		e.salary,
		d.deptno,
		d.deptname
from employee e join title t on e.title = t.tno 
				left join employee m on e.manager = m.empno
				join department d on e.dept = d.deptno ;
select * from employee;				
delete from employee where empno = 4388;
select * from employee;

select empno,empname,title_no,title_name,manager_no,manager_name,salary,deptno,deptname from vw_full_employee ;


select empno,empname,title as title_no, manager as manager_no, salary, dept as deptNo from employee;
select* from employee;
insert into employee values(1004, '천사',5,4377,2000000,1);
update employee set dept =3 where empno = 1004;
delete from employee where empno = 1004;