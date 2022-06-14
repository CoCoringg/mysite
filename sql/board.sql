-- board

desc board;
select * from board;
select count(*) from board;
select title from board limit 5 offset 5;
-- insert
insert 
	into board
values (null, "6", "123", 0, now(), 
	(select ifnull(max(g_no)+1, 1) from board b), 
    1, 1, 1);
    
delete from board where no = 19;

-- findAll
select title, 
	(select name from user where no = a.user_no),
	hit, date_format(reg_date, "%Y-%m-%d %H:%m:%s"), user_no
    from board a
order by g_no desc, o_no asc limit 5,5;

-- findByNo
select no, title, contents
	from board
where no = 1;

-- update
update board set title = '3새우', contents = '배고프다' 
	where no = 3;
    
-- replyInsert
insert into board
	values (null, ?, ?, 0, now(), g_no, o_no, depth, user_no);
    
-- replyUpdate
update board set o_no = o_no+1 where o_no >= 1;
update board set o_no = (o_no+1) where ?+1 >= o_no;

-- updateHit
update board set hit = hit+1 where no = ?;

-- listCount 
select count(*) from board;
