/*
系别专业表，专业才有开始时间与parentId
*/
create table department(
id int primary key,
parent_id int,
name varchar(64),
time_begin varchar(32),
time_end varchar(32)
);


/*
 *教师表
 */
create table teacher(
id int identity(1,1) primary key,
department_id int foreign key references department(id),
tno varchar(24) unique,
name varchar(24),
pwd varchar(32),
gender int default 0 check(gender='0' or gender='1'),
zhicheng varchar(32) default '老师',
tel varchar(24),
email varchar(32),
is_admin int default 0 check(is_admin='0' or is_admin='1')
);


/*
*学生表
*/
create table student(
id int identity(1,1) primary key,
department_id int foreign key references department(id),
sno varchar(24) unique,
name varchar(24),
pwd varchar(32),
gender int default 0 check(gender='0' or gender='1'),
tel varchar(24),
heart_beat bigint default 0,
);


/*
审核中可修改，选题中可取消
*/
create table project(
id int identity(1,1) primary key,
teacher_id int foreign key references teacher(id),
student_id int foreign key references student(id),
title text not null,
detail text,
ranking int default 0 check(ranking>=0 and ranking<=5),
is_checked int default 0,
is_finish int default 0
);



/*
系别专业表数据
*/
insert into department values('1', null, '电子与信息工程学院', null, null);
insert into department values('2', null, '材料与化工学院', null,  null);
insert into department values('101', '1', '计算机科学与技术', null, null);
insert into department values('102', '1', '网络工程', null, null);
insert into department values('201', '2', '应用化学', null, null);

/*
*学生表数据
*/
insert into student values('101', '201301', '方同学', '123456', '1', '18301234501', null);
insert into student values('101', '201302', '田同学', '123456', '1', '18301234502', null);
insert into student values('102', '201303', '胡同学', '123456', '0', '18301234503', null);
insert into student values('102', '201304', '贾同学', '123456', '1', '18301234504', null);
insert into student values('201', '201305', '王同学', '123456', '1', '18301234505', null);
insert into student values('201', '201306', '葛同学', '123456', '0', '18301234506', null);


/*
*教师表数据
*/
insert into teacher values('1', 't001', '赵老师', '123456', '1', '老师', '18100000001', 'zhao@163.com', '0');
insert into teacher values('1', 't002', '钱老师', '123456', '1', '副教授', '18100000002', 'qian@163.com', '1');
insert into teacher values('1', 't003', '孙老师', '123456', '1', '教授', '18100000003', 'sun@163.com', '0');
insert into teacher values('2', 't004', '李老师', '123456', '1', '老师', '18100000004', 'li@163.com', '1');


