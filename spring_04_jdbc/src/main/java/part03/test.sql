select * from mem
select * from mem order by num;

insert into mem(num, name, age, loc) values(mem_num_seq.nextval, 'kim', 27, 'ilsan');
insert into mem(num, name, age, loc) values(mem_num_seq.nextval, 'lee', 26, 'seoul');
insert into mem(num, name, age, loc) values(mem_num_seq.nextval, 'hong', 27, 'busan');
insert into mem(num, name, age, loc) values(mem_num_seq.nextval, 'choi', 27, 'daegu');
insert into mem(num, name, age, loc) values(mem_num_seq.nextval, 'park', 27, 'gangwon')

create sequence mem_num_seq
  start with 1
  increment by 1
  nocache
  nocycle; 
  
drop sequence mem_num_seq;

delete from mem;

