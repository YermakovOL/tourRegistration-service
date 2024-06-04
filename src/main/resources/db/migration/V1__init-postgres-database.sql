drop table if exists tour;
create table tour (
                      id uuid not null,
                      created_date date,
                      description varchar(255),
                      end_date date,
                      name varchar(255),
                      price numeric(38,2),
                      start_date date,
                      primary key (id)
);