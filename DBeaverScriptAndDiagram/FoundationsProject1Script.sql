create table if not exists employeeDB (employeeid serial primary key, first_name varchar(50), last_name varchar(50), username varchar(50) unique, password varchar(50), position varchar(50), role varchar(50));

alter table employeedb alter column role set default 'Employee';

create table if not exists ticket(ticketid serial primary key, employeeusername varchar(50) references employeeDB(username), amount numeric, description varchar(50), status varchar(50));

--alter table ticket  alter column status set default 'pending';

insert into employeedb values (1, 'Rory', 'Eiffe', 'REiffe', '123', 'Developer', 'Manager');


