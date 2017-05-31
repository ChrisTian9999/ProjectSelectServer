/*
ϵ��רҵ��רҵ����ʱ����parentId
*/
create table t_depart(
id int primary key,
parent_id int foreign key references t_depart(id),
name varchar(64),
time_begin varchar(32),
time_end varchar(32)
);


/*
 *��ʦ��,��������ѧԺ��id
 */
create table t_teacher(
id int identity(1,1) primary key,
depart_id int foreign key references t_depart(id) not null,
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
*ѧ����,����רҵ��id
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

/*�����
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
ϵ��רҵ������
*/
insert into t_depart values('1', null, '��������Ϣ����ѧԺ', null, null);
insert into t_depart values('2', null, '��ѧԺ', null,  null);
insert into t_depart values('101', '1', '�������ѧ�뼼��', '2017-01-01 00:00:00', '2017-01-01 00:00:01');
insert into t_depart values('102', '1', 'ͨ�Ź���', '2017-01-01 00:00:00', '2017-01-01 00:00:01');
insert into t_depart values('103', '1', '������Ϣ����', '2017-01-01 00:00:00', '2017-01-01 00:00:01');
insert into t_depart values('104', '1', '���繤��', '2017-01-01 00:00:00', '2017-01-01 00:00:01');
insert into t_depart values('201', '2', '��ѧ', '2017-01-01 00:00:00', '2017-01-01 00:00:01');


/*
*��ʦ������
*/
insert into t_teacher values('1', 't001', '����ʦ', '123456', '1', '����', '18100000001', 'zhou@163.com', '1');
insert into t_teacher values('1', 't002', '����ʦ', '123456', '1', '������', '18100000002', 'wu@163.com', '0');
insert into t_teacher values('1', 't003', '֣��ʦ', '123456', '1', '��ʦ', '18100000003', 'zheng@163.com', '0');
insert into t_teacher values('2', 't004', '����ʦ', '123456', '1', '��ʦ', '18100000004', 'wang@163.com', '1');

/**
*��������
*/
insert into t_project values('101', '1', null, '���ݹ���ϵͳ', '��Ʋ�ʵ��У�ڷ��ݹ���ϵͳ����Ҫ���ܰ������û�����������Ϣ������Ǽ���ˡ������ѯ��ͳ�Ʒ����ȡ�', '4', '0', '0');
insert into t_project values('101', '2', null, '�ϸ�γ���Դ����ƽ̨', '��ƺϸ�γ̽�������ƽ̨����ʵ�ֶ��ſγ̵����߽������Դ������Ҫ������ϵͳ�����û������ļ������μ���������ϵͳ�ȡ�', '5', '0', '0');
insert into t_project values('101', '1', null, 'Androidƽ̨�µ�У԰����ϵͳ', '������У԰��GPS�ֻ�����ϵͳ���ϣ��������ѧԺ�����ֻ�����ϵͳ��ΪУ԰������У��������Ա�ṩ���㡢��ݵ����ܵ�������', '3', '0', '0');
insert into t_project values('101', '2', null, '���ϱ���ϵͳ', '��ϵͳ���������߱���,���ڲ������,����Ա��������,У�ڼ�ְά��Ա�򶨵�ά�޹�˾ά�޽���ϴ�,���������ۼ��������ͳ�Ƽ���������� ', '4', '0', '0');
insert into t_project values('101', '1', null, 'ʵ���ҹ���ϵͳ�����ʵ��', 'ϵͳ����B/Sģʽ��ʵ��ʵ�����ʲ�����ʵ����Ϣ�����Լ�����ԤԼʵ�顢�ſεȹ��ܡ�', '5', '0', '0');
insert into t_project values('101', '2', null, 'У��¼����ϵͳ�����ʵ��', 'У��¼ΪУ��֮����н�������ϵ�ṩ��һ��ƽ̨��ͨ���ṩ���Ƶ�У��¼����͹淶У��¼�Ĺ������Դﵽ����У��֮�䡢У����ĸУ֮��ĸ��飬����У����ϵ��Ŀ�ġ���Ҫ���ܰ������༶���ԡ��༶��ᡢ�༶����ֽ�����顢������Ϣ��������Ա��Ϣ����ȹ��ܡ�', '4', '0', '0');
insert into t_project values('101', '1', null, '������Ŀ����ϵͳ�����ʵ��', '���һ��������Ŀ�����Ĺ���ϵͳ��ʵ�ֶԿ�����Ŀ�ͷ���ĿƼ����ĵļ���������������Ŀ�����Ĺ����ˮƽ���ṩ��ʱ��׼ȷ����Ϣ���񲢼��������Ա��������ͳ�Ʒ����ĸ������Ӷ����ѳ�������ͨ���˹��Կ�����Ŀ���������ĵ������ò����ϵͳ�ܶԿ�����Ŀ���Ƽ�����ʵ�ֽ�ȫ��Ĺ����ṩ����¼�롢��������ѯ��ͳ�ơ�����������ճ�����������Ĺ��ܡ�', '5', '0', '0');
insert into t_project values('101', '2', null, '��У��ʦ��Ч����ϵͳ�����ʵ��', '�Ķ�������ף���ʵ�������������������ʺϸ�У��ʦ��Ч���˹���������Ҫģ���У�������Ϣ����ѧ���������й�������Ṥ������Ŀ���ˣ�֧�ֳ����ܻ����ɲ�ѯ���ļ��ĵ��롢������', '3', '0', '0');
insert into t_project values('101', '1', null, '�˶���ɼ�����ϵͳ�����ʵ��', '����ѧУÿ���˶����ģ�Ĳ�������ͱ�����Ŀ�����࣬���˶�����Ŀ���˶�Ա�����ɼ��ĸ�Ч���������ѧԺ����ع�����Ա�Ĺ���Ч�ʣ����ٹ�����Ա�Ĺ����Ѷȣ���һ�������á��ÿ���Ҫ�����һ�����ݿ�Ӧ�ó��򣬶��˶�����Ŀ���˶�Ա�����ɼ������ݵĴ�����̽��ɼ�������У���߶��˶�����Ŀ���˶�Ա�����ɼ���Ϣ����ĸ�Ч�Ժͼ���ԡ�', '4', '0', '0');



/*
*ѧ��������
*/
insert into t_student values('101', '201301', '��ͬѧ', '123456', '1301', '1', '18301234501', null);
insert into t_student values('101', '201302', '��ͬѧ', '123456', '1301', '1', '18301234502', null);
insert into t_student values('101', '201303', '��ͬѧ', '123456', '1302', '0', '18301234503', null);
insert into t_student values('101', '201304', '��ͬѧ', '123456', '1302', '1', '18301234504', null);
insert into t_student values('201', '201305', '��ͬѧ', '123456', '1301', '0', '18301234506', null);



