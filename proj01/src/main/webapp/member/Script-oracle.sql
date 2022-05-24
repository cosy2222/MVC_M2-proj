create table t_member(
	id varchar2(10) primary key,
	pwd varchar2(10) not null ,
	name varchar2(50) not null,
    email varchar2(100) ,
    joindate date default sysdate
);

insert into t_member
values ( 'kang1' , '1234' , '°­¼º¹Î1' , 'aaa@aaa.com' , sysdate);
insert into t_member
values ( 'kang2' , '1234' , '°­¼º¹Î2' , 'bbb@aaa.com' , sysdate);
insert into t_member
values ( 'kang3' , '1234' , '°­¼º¹Î3' , 'bbb@aaa.com' , sysdate);

select * from t_member;

commit;