INSERT INTO `apartment`.`dept` (`name`) VALUES ('Bảo vệ');
INSERT INTO `apartment`.`dept` (`name`) VALUES ('Kỹ thuật');
INSERT INTO `apartment`.`dept` (`name`) VALUES ('Quản lý');

INSERT INTO `apartment`.`employee` (`name`, `gender`, `date_of_birth`, 
`phone_number`, `email`, `address`, `id_card`, `username`, `password`, `role`, `dept_id`, `is_manager`) 
	VALUES 
    ('Sanero', 1, '1997-03-14', '0989889812', 'sanero@gmail.com', 'Hà Nội', '123456789', 'sanero', '123', 'Manager', 3, 1), 
    ('SBE', 1, '1997-1-1', '0898998989', 'sbe@gmailcom', 'Hà Tây', '12345612', 'SBE', '12', 'Guardian', 1, 1),
    ('Son', 1, '1997-1-1', '0987681273', 'son@gmail.com', 'Hà Nội', '123456789', 'son', '123', 'Manager', 3, 0);