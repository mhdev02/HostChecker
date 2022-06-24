insert into hosts (name, ip) values ('naver', '223.130.195.200');
insert into hosts (name, ip) values ('google', '142.250.207.46');

update hosts SET name='naver1' WHERE name='naver';
delete from hosts where name='naver1';
insert into hosts (name, ip) values ('naver', '223.130.195.200');