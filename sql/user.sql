-- UserRepository

desc user;

insert 
	into user
values (null, '관리자', 'admin@mysite.com', '1234', 'female', now());

select * from user;

select * from guestbook;