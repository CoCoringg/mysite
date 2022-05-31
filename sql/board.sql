-- board

desc board;
select * from board;

-- insert
insert 
	into board
values (null, "123", "123", 0, now(), 
	(select ifnull(max(g_no)+1, 1) from board b), 
    1, 1, 1);
    
delete from board where no = 5;

-- findAll
select title, 
	(select name from user where no = a.user_no),
	hit, date_format(reg_date, "%Y-%m-%d %H:%m:%s"), user_no
    from board a
order by g_no desc, o_no asc;

-- findByNo
select no, title, contents
	from board
where no = 1;
