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

/**
*课题数据
*/
insert into t_project values('101', '1', null, '房屋管理系统', '设计并实现校内房屋管理系统，主要功能包括：用户管理、房屋信息变更、登记审核、分类查询、统计分析等。', '4', '0', '0');
insert into t_project values('101', '2', null, '合格课程资源建设平台', '设计合格课程建设网络平台，可实现多门课程的在线建设和资源管理。主要包括：系统管理、用户管理、文件管理、课件管理、答疑系统等。', '5', '0', '0');
insert into t_project values('101', '1', null, 'Android平台下的校园导航系统', '将数字校园与GPS手机导航系统相结合，设计皖西学院智能手机导航系统，为校园新生及校外来访人员提供方便、快捷的智能导航服务。', '3', '0', '0');
insert into t_project values('101', '2', null, '网上报修系统', '该系统包括报修者报修,所在部门审核,管理员分派任务,校内兼职维修员或定点维修公司维修结果上传,报修者评价及相关数据统计及报表输出。 ', '4', '0', '0');
insert into t_project values('101', '1', null, '实验室管理系统设计与实现', '系统采用B/S模式，实现实验室资产管理、实验信息管理以及网上预约实验、排课等功能。', '5', '0', '0');
insert into t_project values('101', '2', null, '校友录管理系统设计与实现', '校友录为校友之间进行交流和联系提供了一个平台，通过提供完善的校友录服务和规范校友录的管理，可以达到增进校友之间、校友与母校之间的感情，方便校友联系的目的。主要功能包括：班级留言、班级相册、班级共享、纸条传情、个人信息管理、管理员信息管理等功能。', '4', '0', '0');
insert into t_project values('101', '1', null, '科研项目管理系统设计与实现', '设计一个科研项目及论文管理系统，实现对科研项目和发表的科技论文的计算机管理，以提高项目及论文管理的水平，提供及时、准确的信息服务并减轻管理人员制作报表、统计分析的负担，从而摆脱长期以来通过人工对科研项目及发表论文的落后面貌。该系统能对科研项目及科技论文实现较全面的管理，提供数据录入、多条件查询、统计、报表输出等日常管理所必需的功能。', '5', '0', '0');
insert into t_project values('101', '2', null, '高校教师绩效管理系统设计与实现', '阅读相关文献，从实用需求出发，开发设计适合高校教师绩效考核管理工作。主要模块有：基本信息、教学工作、科研工作和社会工作等项目考核，支持超智能化自由查询与文件的导入、导出。', '3', '0', '0');
insert into t_project values('101', '1', null, '运动会成绩管理系统设计与实现', '随着学校每届运动会规模的不断扩大和比赛项目的增多，对运动会项目和运动员参赛成绩的高效管理能提高学院的相关工作人员的工作效率，减少管理人员的工作难度，有一定的作用。该课题要求设计一个数据库应用程序，对运动会项目和运动员参赛成绩等数据的处理过程交由计算机进行，提高对运动会项目和运动员参赛成绩信息处理的高效性和简洁性。', '4', '0', '0');



/*
*学生表数据
*/
insert into t_student values('101', '201301', '田同学', '123456', '1301', '1', '18301234501', null);
insert into t_student values('101', '201302', '方同学', '123456', '1301', '1', '18301234502', null);
insert into t_student values('101', '201303', '胡同学', '123456', '1302', '0', '18301234503', null);
insert into t_student values('101', '201304', '贾同学', '123456', '1302', '1', '18301234504', null);
insert into t_student values('201', '201305', '葛同学', '123456', '1301', '0', '18301234506', null);



