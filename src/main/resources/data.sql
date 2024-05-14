insert into user_details(id, birth_date, name)
values(10001, current_date(), 'smilejk930');

insert into user_details(id, birth_date, name)
values(10002, current_date(), '김진광');

insert into user_details(id, birth_date, name)
values(10003, current_date(), '홍길동');

insert into post(id, description, user_id)
values(20001, 'I want to lean AWS', 10001);

insert into post(id, description, user_id)
values(20002, 'I want to lean DevOps', 10001);

insert into post(id, description, user_id)
values(20003, 'I want to lean Restful API', 10002);

insert into post(id, description, user_id)
values(20004, 'I want to lean Next.js', 10003);