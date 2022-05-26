-- UserRepository

desc user;
select * from user;

-- join
insert 
	into user
values (null, '관리자', 'admin@mysite.com', '1234', 'female', now());

-- login
select no, name 
	from user 
where email = 'dooly@gmail.com' 
	and password = '1234';
    
-- findByNo
select no, name, email, gender
	from user 
where no=2;

-- update 
update user
	set name = '둘리2',
		password = '1111',
        gender = 'male'
	where no = 2;
    
update user
	set name = '둘리2',
        gender = 'male'
	where no = 2;


