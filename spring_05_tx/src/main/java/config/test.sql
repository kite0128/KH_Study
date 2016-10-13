select * from mem
select * from mem order by num;

alter table mem add constraint mem_num_con primary key(num);

create sequence mem_num_seq
  start with 1
  increment by 1
  nocache
  nocycle; 
  
drop sequence mem_num_seq;

delete from mem;

