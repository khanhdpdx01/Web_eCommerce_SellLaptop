create table account (account_id bigint not null auto_increment, password nvarchar(30) not null, status integer, username nvarchar(30) not null, user_id bigint, primary key (account_id)) engine=MyISAM
create table category (category_id bigint not null auto_increment, category_name nvarchar(255) not null, primary key (category_id)) engine=MyISAM
create table laptop (laptop_id bigint not null auto_increment, description text not null, entered_date date, link_image nvarchar(255) not null, name nvarchar(255) not null, status integer not null, unit_price float not null, category_id bigint, primary key (laptop_id)) engine=MyISAM
create table order_detail (laptop_id bigint not null, order_id bigint not null, discount integer, quantity integer not null, unit_price float not null, primary key (laptop_id, order_id)) engine=MyISAM
create table orders (order_id bigint not null auto_increment, freight_cost float not null, ordered_date date, ship_address nvarchar(255) not null, status integer not null, payment_id bigint, shipper_id bigint, user_id bigint, primary key (order_id)) engine=MyISAM
create table payment (payment_id bigint not null auto_increment, payment_name nvarchar(100) not null, primary key (payment_id)) engine=MyISAM
create table role (role_id bigint not null auto_increment, role_name varchar(255), primary key (role_id)) engine=MyISAM
create table shipper (shipper_id bigint not null auto_increment, company_name nvarchar(255) not null, user_id bigint, primary key (shipper_id)) engine=MyISAM
create table users (user_id bigint not null auto_increment, address nvarchar(255) not null, email varchar(50) not null, name nvarchar(50) not null, phone varchar(15) not null, role_id bigint, primary key (user_id)) engine=MyISAM
alter table account add constraint FKra7xoi9wtlcq07tmoxxe5jrh4 foreign key (user_id) references users (user_id)
alter table laptop add constraint FK9pyl5kc7liw32o40n5i3ruhj0 foreign key (category_id) references category (category_id)
alter table orders add constraint FKag8ppnkjvx255gj7lm3m18wkj foreign key (payment_id) references payment (payment_id)
alter table orders add constraint FKcw9s4yihuqduodjn391d630i8 foreign key (shipper_id) references shipper (shipper_id)
alter table orders add constraint FK32ql8ubntj5uh44ph9659tiih foreign key (user_id) references users (user_id)
alter table shipper add constraint FKki4q63rrx3nol0b7dpfqk3w9n foreign key (user_id) references users (user_id)
alter table users add constraint FK4qu1gr772nnf6ve5af002rwya foreign key (role_id) references role (role_id)
