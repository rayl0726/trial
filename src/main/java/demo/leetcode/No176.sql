编写一个 SQL 查询，获取 Employee 表中第二高的薪水（Salary） 。

+----+--------+
| Id | Salary |
+----+--------+
| 1  | 100    |
| 2  | 200    |
| 3  | 300    |
+----+--------+

例如上述 Employee 表，SQL查询应该返回 200 作为第二高的薪水。如果不存在第二高的薪水，那么查询应返回 null。

+---------------------+
| SecondHighestSalary |
+---------------------+
| 200                 |
+---------------------+

solution1:
select ifnull(
(select distinct Salary
from Employee 
order by Salary desc
limit 1,1),null) as SecondHighestSalary;

duringtime:225ms

solution2:
select max(Salary) as SecondHighestSalary
from Employee 
where Salary<(select max(Salary) from Employee);

duringtime:283ms
