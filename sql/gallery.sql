desc gallery;
select * from gallery;

-- insert
insert into gallery
	values (null, "url", "comments");
    
-- findAll
select no, url, comments 
	from gallery
order by no;