/*
ϵ��רҵ��רҵ���п�ʼʱ����parentId
*/
create table department(
id int primary key,
parent_id int,
name varchar(64),
time_begin varchar(32),
time_end varchar(32)
);


/*
 *��ʦ��
 */
create table teacher(
id int identity(1,1) primary key,
department_id int foreign key references department(id),
tno varchar(24) unique,
name varchar(24),
pwd varchar(32),
gender int default 0 check(gender='0' or gender='1'),
zhicheng varchar(32) default '��ʦ',
tel varchar(24),
email varchar(32),
is_admin int default 0 check(is_admin='0' or is_admin='1')
);


/*
*ѧ����
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
����п��޸ģ�ѡ���п�ȡ��
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
ϵ��רҵ������
*/
insert into department values('1', null, '��������Ϣ����ѧԺ', null, null);
insert into department values('2', null, '�����뻯��ѧԺ', null,  null);
insert into department values('101', '1', '�������ѧ�뼼��', null, null);
insert into department values('102', '1', '���繤��', null, null);
insert into department values('201', '2', 'Ӧ�û�ѧ', null, null);

/*
*ѧ��������
*/
insert into student values('101', '201301', '��ͬѧ', '123456', '1', '18301234501', null);
insert into student values('101', '201302', '��ͬѧ', '123456', '1', '18301234502', null);
insert into student values('102', '201303', '��ͬѧ', '123456', '0', '18301234503', null);
insert into student values('102', '201304', '��ͬѧ', '123456', '1', '18301234504', null);
insert into student values('201', '201305', '��ͬѧ', '123456', '1', '18301234505', null);
insert into student values('201', '201306', '��ͬѧ', '123456', '0', '18301234506', null);


/*
*��ʦ������
*/
insert into teacher values('1', 't001', '����ʦ', '123456', '1', '��ʦ', '18100000001', 'zhao@163.com', '0');
insert into teacher values('1', 't002', 'Ǯ��ʦ', '123456', '1', '������', '18100000002', 'qian@163.com', '1');
insert into teacher values('1', 't003', '����ʦ', '123456', '1', '����', '18100000003', 'sun@163.com', '0');
insert into teacher values('2', 't004', '����ʦ', '123456', '1', '��ʦ', '18100000004', 'li@163.com', '1');


