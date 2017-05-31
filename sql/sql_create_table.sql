/*
系别专业表，专业才有时间与parentId
*/
create table t_depart(
id int primary key,
parent_id int foreign key references t_depart(id),
name varchar(64),
time_begin varchar(32),
time_end varchar(32)
);


/*
 *教师表,包含包含学院的id
 */
create table t_teacher(
id int identity(1,1) primary key,
depart_id int foreign key references t_depart(id) not null,
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
*学生表,包含专业的id
*/
create table t_student(
id int identity(1,1) primary key,
depart_id int foreign key references t_depart(id) not null,
sno varchar(24) unique,
name varchar(24),
pwd varchar(32),
classname varchar(32),
gender int default 0 check(gender='0' or gender='1'),
tel varchar(24),
heart_beat bigint default 0,
);

/*课题表
*/
create table t_project(
id int identity(1,1) primary key,
depart_id int foreign key references t_depart(id) not null,
teacher_id int foreign key references t_teacher(id) not null,
student_id int foreign key references t_student(id),
title text not null,
detail text not null,
ranking int default 0 check(ranking>=0 and ranking<=5),
is_checked int default 0,
is_finish int default 0
);


/*
系别专业表数据
*/
insert into t_depart values('1', null, '电子与信息工程学院', null, null);
insert into t_depart values('2', null, '法学院', null,  null);
insert into t_depart values('101', '1', '计算机科学与技术', '2017-01-01 00:00:00', '2017-01-01 00:00:01');
insert into t_depart values('102', '1', '通信工程', '2017-01-01 00:00:00', '2017-01-01 00:00:01');
insert into t_depart values('103', '1', '电子信息工程', '2017-01-01 00:00:00', '2017-01-01 00:00:01');
insert into t_depart values('104', '1', '网络工程', '2017-01-01 00:00:00', '2017-01-01 00:00:01');
insert into t_depart values('201', '2', '法学', '2017-01-01 00:00:00', '2017-01-01 00:00:01');


/*
*教师表数据
*/
insert into t_teacher values('1', 't001', '周老师', '123456', '1', '教授', '18100000001', 'zhou@163.com', '1');
insert into t_teacher values('1', 't002', '吴老师', '123456', '1', '副教授', '18100000002', 'wu@163.com', '0');
insert into t_teacher values('1', 't003', '郑老师', '123456', '1', '教师', '18100000003', 'zheng@163.com', '0');
insert into t_teacher values('2', 't004', '王老师', '123456', '1', '教师', '18100000004', 'wang@163.com', '1');




/*
*学生表数据
*/
insert into t_student values('101', '201301', '田同学', '123456', '1301', '1', '18301234501', null);
insert into t_student values('101', '201302', '方同学', '123456', '1301', '1', '18301234502', null);
insert into t_student values('101', '201303', '胡同学', '123456', '1302', '0', '18301234503', null);
insert into t_student values('101', '201304', '贾同学', '123456', '1302', '1', '18301234504', null);
insert into t_student values('201', '201305', '葛同学', '123456', '1301', '0', '18301234506', null);



