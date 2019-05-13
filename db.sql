drop database if exists apartment;
create database  apartment;
use apartment;

create table dept(
	id int auto_increment primary key,
    name varchar(100),
    disable bit default 0
);

create table employee(
	id int auto_increment primary key,
    name varchar(255),
    gender bit,
    date_of_birth date,
    phone_number varchar(50),
    email varchar(50),
    address varchar(100),
    id_card varchar(50),
    photo varchar(255) default '',
    username varchar(255),
    password varchar(255),
    role enum('Manager','Guardian') default 'Guardian',
    dept_id int,
    is_manager bit,
    disable bit default 0,
    foreign key (dept_id) references dept(id)
);

create table building(
	id int auto_increment primary key,
    name varchar(50),
    disable bit default 0
);


create table floor (
	id int  auto_increment primary key,
    name varchar(50),
    building_id int,
    disable bit default 0,
    foreign key (building_id) references building(id)
);


create table room (
	id int auto_increment primary key,
    name varchar(50),
    area double,
    status enum('0','1','2') default '0',
    floor_id int,
    building_id int,
    room_type enum('A', 'B') default 'B',
    disable bit default 0,
    foreign key (building_id) references building(id),
    foreign key (floor_id) references floor(id)
);

create table household(
	id int auto_increment primary key,
    fullname varchar(255),
    id_card varchar(50),
    address varchar(255),
    phone_number  varchar(50),
    come_date date,
    leave_date date,
    is_hire bit,
    price double,
    deposit double,
    deposit_date date,
    room_id int,
    status bit,
    created_by int,
    disable bit default 0,
    foreign key (room_id) references room(id),
    foreign key (created_by) references employee(id)
);

create  table user(
	id int auto_increment primary key,
    name varchar(255),
    gender bit,
    date_of_birth date,
    phone_number varchar(50),
    email varchar(50),
    address varchar(100),
    id_card varchar(50),
    is_head bit,
    is_leave bit default 0,
    leave_date date,
    is_enable bit default 0,
    household_id int,
    disable bit default 0,
    foreign key(household_id) references household(id)
);

create table card_type(
	id int auto_increment primary key,
    name varchar(50),
    disable bit default 0
);

create table card (
    card_numb varchar(50) primary key,
    created_date datetime default CURRENT_TIMESTAMP,
    card_type_id int,
    user_id int,
    created_by int, 
    disable bit default 0,
    foreign key (user_id) references user(id), 
    foreign key (card_type_id) references card_type(id), 
    foreign key (created_by) references employee(id)
);

create table verhicle_type(
	id int auto_increment primary key,
    name varchar(50),
    disable bit default 0
);

create  table verhicle(
	plate_numb varchar(50) primary key,
    card_numb varchar(50),
    user_id int,
    verhicle_type int,
    foreign key (user_id) references user(id),
    foreign key (card_numb) references card(card_numb),
    foreign key (verhicle_type) references verhicle_type(id),
    disable bit default 0
);

create  table service_type (
	id int auto_increment primary key,
    name varchar(50),
    price  double,
    unit varchar(50),
    supplier varchar(255),
    disable bit default 0
);


create table service (
	id int auto_increment primary key,
    room_id int,
    service_type int,
    collect_month varchar(20),
    payment_date date,
    detail text,
    price double,
    description varchar(255),
    paid bit,
    created_by int,
    created_date datetime default CURRENT_TIMESTAMP,
    foreign key (room_id) references room(id),
    foreign key (service_type) references service_type(id),
    foreign key (created_by) references employee(id)
);


create table device_group (
	id int auto_increment primary key,
    name varchar(255),
    disable bit default 0
);


create table device_type (
	id int auto_increment primary key,
    name varchar(255),
    disable bit default 0
);

create table spec(
	id int auto_increment primary key,
    name varchar(255),
    disable bit default 0,
    device_type int,
    foreign key (device_type) references device_type(id)
);


create table device (
	id int auto_increment primary key,
    name varchar(255),
    sign varchar(50),
    provider varchar(255),
    installed_date date,
    operation_date date,
    unit varchar(50),
    quantity int,
    price double,
    description varchar(255),
    maintenance_cycle int,
    status bit,
    device_type int,
    device_group int,
    foreign key (device_type ) references device_type (id),
    foreign key (device_group) references device_group(id)
);


create table device_spec(
	id int  auto_increment primary key,
	device_id int,
    spec_name varchar(255),
    val varchar(255),
    foreign key (device_id) references device(id)
);

create table maintenance(
	id int auto_increment primary key,
    device_group int,
    maintenance_date date,
    description varchar(255),
    maintenance_price double,
    is_excuted bit,
    number_personnel int,
    paid bit,
    foreign key (device_group) references device_group(id)
);

create table maintenance_personnel(
	id int auto_increment primary key,
    maintenance_id int,
    employee_id int,
    is_supervisor bit,
    foreign key (maintenance_id) references maintenance(id),
    foreign key (employee_id) references employee(id)
);

create table maintenance_detail (
	id int auto_increment primary key,
    maintenance_id int,
    device_id int,
    price double,
    description varchar(255),
    location varchar(100),
    foreign key (maintenance_id) references maintenance(id),
    foreign key (device_id) references device(id)
);