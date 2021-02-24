select * from student;
use  student;
grant all on student.* to 'user_native_jdbc'@'localhost' identified by 'rootroot';

insert into student values (1, "전수린", 90, 90, 90),
(2, "김상건", 91, 91, 91),
(3, "이태훈", 92, 92, 92);
select * from title;
select *from department;
select * from employee;

delete from title where tno= 6;

delete from department where deptno =5;


select empno,empname,title,manager,salary,dept from employee where dept = (select deptno from department where deptno=3);
