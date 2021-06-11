drop database shoplaptop;
create database shoplaptop;
use shoplaptop;

create table category (
  category_id bigint not null auto_increment,
  category_name nvarchar(255) not null,
  primary key (category_id)
);
create table payment (
  payment_id bigint not null auto_increment,
  payment_name nvarchar(100) not null,
  primary key (payment_id)
);
create table role (
  role_id bigint not null auto_increment,
  role_name varchar(255),
  primary key (role_id)
);
create table users (
  user_id bigint not null auto_increment,
  address nvarchar(255) not null,
  email varchar(50) not null,
  name nvarchar(50) not null,
  phone varchar(15) not null,
  role_id bigint,
  primary key (user_id),
  constraint fk_role_id foreign key (role_id) references role (role_id)
  on delete cascade on update cascade
);
create table account (
  account_id bigint not null auto_increment,
  password nvarchar(30) not null,
  status integer,
  username nvarchar(30) not null,
  user_id bigint not null,
  primary key (account_id),
  constraint fk_user_id foreign key (user_id) references users (user_id)
  on delete cascade on update cascade
);
create table shipper (
  shipper_id bigint not null auto_increment,
  company_name nvarchar(255) not null,
  user_id bigint,
  primary key (shipper_id),
  constraint fk_user_shipper foreign key (user_id) references users (user_id)
  on delete cascade on update cascade
);
create table laptop (
  laptop_id bigint not null auto_increment,
  description text not null,
  entered_date date,
  link_image nvarchar(255) not null,
  name nvarchar(255) not null,
  status integer not null,
  unit_price float not null,
  category_id bigint,
  primary key (laptop_id),
  constraint fk_category_id foreign key (category_id) references category (category_id)
  on delete cascade on update cascade
);
create table orders (
  order_id bigint not null auto_increment,
  freight_cost float not null,
  ordered_date date,
  ship_address nvarchar(255) not null,
  status integer not null,
  payment_id bigint,
  shipper_id bigint,
  user_id bigint,
  primary key (order_id),
  constraint fk_payment_orders foreign key (payment_id) references payment (payment_id)
  on delete cascade on update cascade,
  constraint fk_shipper_orders foreign key (shipper_id) references shipper (shipper_id)
  on delete cascade on update cascade,
  constraint fk_user_orders foreign key (user_id) references users (user_id)
  on delete cascade on update cascade
);
create table order_detail (
  laptop_id bigint not null,
  order_id bigint not null,
  discount integer,
  quantity integer not null,
  unit_price float not null,
  primary key (laptop_id, order_id),
  constraint fk_laptop_order_detail foreign key (laptop_id) references laptop (laptop_id)
  on delete cascade on update cascade,
  constraint fk_order_order_detail foreign key (order_id) references orders (order_id)
  on delete cascade on update cascade
);





