INSERT INTO `apartment`.`dept` (`name`) VALUES ('Bảo vệ');
INSERT INTO `apartment`.`dept` (`name`) VALUES ('Kỹ thuật');
INSERT INTO `apartment`.`dept` (`name`) VALUES ('Quản lý');

INSERT INTO `apartment`.`employee` (`name`, `gender`, `date_of_birth`, 
`phone_number`, `email`, `address`, `id_card`, `username`, `password`, `role`, `dept_id`, `is_manager`) 
	VALUES 
    ('Sanero', 1, '1997-03-14', '0989889812', 'sanero@gmail.com', 'Hà Nội', '123456789', 'sanero', 
			'202cb962ac59075b964b07152d234b70', 'Manager', 3, 1), 
    ('SBE', 1, '1997-1-1', '0898998989', 'sbe@gmailcom', 'Hà Tây', '12345612', 'SBE', '202cb962ac59075b964b07152d234b70', 'Guardian', 1, 1),
    ('Son', 1, '1997-1-1', '0987681273', 'son@gmail.com', 'Hà Nội', '123456789', 'son', '202cb962ac59075b964b07152d234b70', 'Manager', 3, 0),
    ('Nguyen Tuan Anh', 1, '1985-1-1', '0987681233', 'tuananh@gmail.com', 'Hà Nội', '123456789', 'tuananh', 									'202cb962ac59075b964b07152d234b70', 'Guardian', 2, 0),
    ('Do Xuan Tung', 1, '1987-1-1', '0986121233', 'tung@gmail.com', 'Hà Nội', '123456789', 'tung', 											'202cb962ac59075b964b07152d234b70', 'Guardian', 2, 0);
    
INSERT INTO `apartment`.`building` (`name`) VALUES ('A1');

INSERT INTO `apartment`.`floor` (`name`, `building_id`) VALUES 
	('Tầng 2', 1),
	('Tầng 3', 1),
	('Tầng 4', 1),
	('Tầng 5', 1),
	('Tầng 6', 1),
	('Tầng 7', 1),
	('Tầng 8', 1),
	('Tầng 9', 1),
	('Tầng 10', 1);

INSERT INTO `apartment`.`room` (`name`, `area`, `status`, `floor_id`, `building_id`, `room_type`) VALUES 
   ('201', 70, '0', 1, 1, 'A'),
   ('202', 70, '0', 1, 1, 'B'),
   ('203', 70, '2', 1, 1, 'A'),
   ('204', 70, '2', 1, 1, 'B'),
   ('205', 70, '2', 1, 1, 'A'),
   ('206', 70, '2', 1, 1, 'B'),
   ('207', 70, '2', 1, 1, 'A'),
   ('208', 70, '0', 1, 1, 'B'),
   ('209', 70, '0', 1, 1, 'A'),
   ('210', 70, '0', 1, 1, 'A'),
   ('301', 70, '0', 2, 1, 'B'),
   ('302', 70, '0', 2, 1, 'A'),
   ('303', 70, '0', 2, 1, 'A'),
   ('304', 70, '2', 2, 1, 'A'),
   ('305', 70, '1', 2, 1, 'A'),
   ('306', 70, '1', 2, 1, 'A'),
   ('307', 70, '2', 2, 1, 'A'),
   ('308', 70, '2', 2, 1, 'A'),
   ('309', 70, '2', 2, 1, 'B'),
   ('310', 70, '1', 2, 1, 'A'),
   ('401', 70, '2', 3, 1, 'B'),
   ('402', 70, '2', 3, 1, 'A'),
   ('403', 70, '1', 3, 1, 'A'),
   ('404', 70, '0', 3, 1, 'A'),
   ('405', 70, '0', 3, 1, 'A'),
   ('406', 70, '0', 3, 1, 'A'),
   ('407', 70, '0', 3, 1, 'A'),
   ('408', 70, '0', 3, 1, 'A'),
   ('409', 70, '0', 3, 1, 'B'),
   ('410', 70, '0', 3, 1, 'A'),
   ('501', 70, '0', 4, 1, 'A'),
   ('502', 70, '0', 4, 1, 'A'),
   ('503', 70, '0', 4, 1, 'A'),
   ('504', 70, '0', 4, 1, 'A'),
   ('505', 70, '0', 4, 1, 'A'),
   ('506', 70, '0', 4, 1, 'A'),
   ('507', 70, '0', 4, 1, 'B'),
   ('508', 70, '0', 4, 1, 'A'),
   ('509', 70, '0', 4, 1, 'A'),
   ('510', 70, '0', 4, 1, 'A'),
   ('601', 70, '0', 5, 1, 'A'),
   ('602', 70, '0', 5, 1, 'A'),
   ('603', 70, '0', 5, 1, 'A'),
   ('604', 70, '0', 5, 1, 'B'),
   ('605', 70, '0', 5, 1, 'A'),
   ('606', 70, '0', 5, 1, 'A'),
   ('607', 70, '0', 5, 1, 'A'),
   ('608', 70, '0', 5, 1, 'B'),
   ('609', 70, '0', 5, 1, 'A'),
   ('610', 70, '0', 5, 1, 'A'),
   ('701', 70, '0', 6, 1, 'B'),
   ('702', 70, '0', 6, 1, 'B'),
   ('703', 70, '0', 6, 1, 'B'),
   ('704', 70, '0', 6, 1, 'A'),
   ('705', 70, '0', 6, 1, 'B'),
   ('706', 70, '0', 6, 1, 'A'),
   ('707', 70, '0', 6, 1, 'B'),
   ('708', 70, '0', 6, 1, 'B'),
   ('709', 70, '0', 6, 1, 'A'),
   ('710', 70, '0', 6, 1, 'B'),
   ('801', 70, '0', 7, 1, 'A'),
   ('802', 70, '0', 7, 1, 'A'),
   ('803', 70, '0', 7, 1, 'B'),
   ('804', 70, '0', 7, 1, 'A'),
   ('805', 70, '0', 7, 1, 'A'),
   ('806', 70, '0', 7, 1, 'A'),
   ('807', 70, '0', 7, 1, 'B'),
   ('808', 70, '0', 7, 1, 'A'),
   ('809', 70, '0', 7, 1, 'A'),
   ('810', 70, '0', 7, 1, 'A'),
   ('901', 70, '0', 8, 1, 'B'),
   ('902', 70, '0', 8, 1, 'A'),
   ('903', 70, '0', 8, 1, 'A'),
   ('904', 70, '0', 8, 1, 'A'),
   ('905', 70, '0', 8, 1, 'B'),
   ('906', 70, '0', 8, 1, 'A'),
   ('907', 70, '0', 8, 1, 'A'),
   ('908', 70, '0', 8, 1, 'A'),
   ('909', 70, '0', 8, 1, 'B'),
   ('910', 70, '0', 8, 1, 'A'),
   ('1001', 70, '0', 9, 1, 'B'),
   ('1002', 70, '0', 9, 1, 'B'),
   ('1003', 70, '0', 9, 1, 'B'),
   ('1004', 70, '0', 9, 1, 'A'),
   ('1005', 70, '0', 9, 1, 'B'),
   ('1006', 70, '0', 9, 1, 'B'),
   ('1007', 70, '0', 9, 1, 'B'),
   ('1008', 70, '0', 9, 1, 'B'),
   ('1009', 70, '0', 9, 1, 'B'),
   ('1010', 70, '0', 9, 1, 'B');
   
INSERT INTO `apartment`.`household` (`fullname`, `id_card`, `address`, `phone_number`, `come_date`,
									`leave_date`, `is_hire`, `price`, `deposit`, `deposit_date`,`status`, `room_id`, `created_by`) 
VALUES ('Nguyễn Văn Tuấn', '12345612134', 'Hà Nội', '098123123', '2011-01-03', '2017-12-12', 
		0, '900000000', '90000000', '2010-12-25', 0, 1, 1),
		('Vũ Tuấn Anh', '000101389121', 'Hà Nội', '0321234566', '2017-01-03', null , 
		0, '850000000', '55000000', '2016-12-23', 1, 3, 1),
        
		('Trần Văn Huy', '001093123123', 'Hà Nam', '0979965726', '2018-03-25', null , 
		0, '700000000', '75000000', '2018-03-12', 1, 4, 1),
        
		('Lê Văn Thắng', '003001765893', 'Hải Dương', '0963343688', '2017-12-24', null , 
		0, '900000000', '80000000', '2017-12-12', 1, 5, 1),
        
		('Bùi Văn Khánh', '086631234833', 'Hải Phòng', '0352312523', '2017-11-23', null , 
		0, '900000000', '90000000', '2017-11-05', 1, 6, 1),
        
		('Nguyễn Minh Long', '097886554546', 'Hà Nội', '0887678123', '2018-08-03', null , 
		0, '930000000', '90000000', '2018-07-10', 1, 7, 1),
        
		('Nguyễn Văn Hải', '102312348786', 'Hà Nam', '0989898989', '2017-01-03', null , 
		1, '3300000', '15000000', '2016-12-23', 1, 15, 1),
        
		('Nguyễn Thị Yến', '001125873639', 'Bắc Ninh', '0979234756', '2017-01-03', null , 
		1, '3300000', '15000000', '2016-12-23', 1, 16, 1),
        
		('Nguyễn Văn Cường', '101002103421', 'Thái Nguyên', '0964654789', '2017-01-03', null , 
		0, '940000000', '90000000', '2016-12-23', 1, 17, 1),
        
		('Lý Hồng Thắng', '100342182385', 'Hà Nội', '0987654567', '2017-01-03', null , 
		0, '870000000', '80000000', '2016-12-23', 1, 18, 1),
        
		('Nguyễn Hồng Quân', '121495387362', 'Hà Nội', '0398719283', '2017-01-03', null , 
		0, '760000000', '70000000', '2016-12-23', 1, 19, 1),
        
		('Nguyễn Văn Nam', '101403204506', 'Bắc Ninh', '0955389127', '2017-01-03', null , 
		1, '3300000', '15000000', '2016-12-23', 1, 20, 1),
        
		('Nguyễn Quang Tuấn', '029419395125', 'Hà Nội', '0986573921', '2013-03-03', '2013-03-03' , 
		0, '880000000', '500000000', '2013-01-30', 0, 3, 1),
        
		('Trần Trung Quyết', '047281249897', 'Hà Nội', '0975381289', '2011-12-12', '2015-12-12' , 
		0, '900000000', '500000000', '2011-11-11', 0, 4, 1),
        
		('Nguyễn Quốc Anh', '000101645764', 'Hà Nam', '0975731265', '2011-01-03', '2014-03-03' , 
		0, '900000000', '500000000', '2010-12-23', 0, 5, 1),
        
		('Lê Văn Linh', '010003004005', 'Hà Nội', '0382713214', '2013-01-03', '2015-03-03' , 
		0, '900000000', '500000000', '2012-12-23', 0, 6, 1),
        
		('Ngô Đức Anh', '000103048982', 'Hà Nội', '0969563286', '2017-01-03', null , 
		0, '900000000', '500000000', '2016-12-23', 1, 21, 1),
        
		('Nguyễn Văn Tú', '019829329819', 'Hà Nội', '0965637281', '2018-04-28', null , 
		0, '900000000', '500000000', '2018-05-18', 1, 22, 1),
        
		('Vũ Văn Việt', '000101987649', 'Hà Nội', '0965123472', '2016-07-25', null , 
		1, '3300000', '15000000', '2016-07-08', 1, 23, 1);


INSERT INTO `apartment`.`user` (`name`, `gender`, `date_of_birth`, `phone_number`, `email`, `address`, `id_card`, `is_head`, `is_leave`, `leave_date`, `household_id`, `disable`) 
VALUES ('Nguyễn Văn Tuấn', 1, '1985-12-12', '098123123', 'nvtuan@gmail.com', 'Hà Nội', '12345612134', 1
		, 1 , '2017-12-12', 1, 1), 
        
        ('Nguyễn Thị Thảo', 0, '1987-04-22', '098123652', 'ntthao@gmail.com', 'Hà Nội', '12347423134', 0
		, 1 , '2017-12-12', 1, 1),
        
        ('Nguyễn Văn Nam', 1, '2010-07-07', null, null, 'Hà Nội', null, 0
		, 1 , '2017-12-12', 1, 1),
        
        ('Vũ Tuấn Anh', 1, '1975-03-21', '0321234566', 'vtanh123@gmail.com', 'Hà Nội', '000101389121', 1
		, 0 , null,  2, 0), 
        ('Nguyễn Hồng Nhung', 0, '1978-08-28', '0332834566', 'nhnhung123@gmail.com', 'Hà Nội', '001474231234', 0
		, 0 , null,  2, 0), 
        ('Vũ Quang Tùng', 1, '1999-07-07', '0385834566', 'vqtung123@gmail.com', 'Hà Nội', '001765431234', 0
		, 0 , null,  2, 0),
        
        ('Trần Văn Huy', 1, '1991-02-15', '0979965726', 'tvhuy91@gmail.com', 'Hà Nam', '001093123123', 1
		, 0 , null,  3, 0), 
        ('Nguyễn Thị Mỹ Duyên', 0, '1991-09-16', '0978834566', 'myduyen91@gmail.com', 'Hà Nam', '001452781234', 0
		, 0 , null,  3, 0),
        
        ('Lê Văn Thắng', 1, '1978-01-01', '0963343688', 'lvthang1@gmail.com', 'Hải Dương', '003001765893', 1
		, 0 , null,  4, 0), 
        ('Nguyễn Thị Duyên', 0, '1979-04-14', '0332341566', 'ntduyen1@gmail.com', 'Hà Nội', '001123231234', 0
		, 0 , null,  4, 0), 
        ('Lê Ngọc Hà', 0, '2003-09-23', null, null, 'Hải Dương', null, 0
		, 0 , null,  4, 0) ,
        
        ('Bùi Văn Khánh', 1, '1992-05-18', '0352567423', 'bvkhanh@gmail.com', 'Hải Phòng', '086631234833', 1
		, 0 , null,  5, 0), 
        ('Trần Thị Thảo', 0, '1993-07-19', '0978834566', 'ttthao@gmail.com', 'Bắc Giang', '011252781234', 0
		, 0 , null,  5, 0) ,
        
        
        ('Nguyễn Minh Long', 1, '1986-11-18', '0887678123', 'nmlong121@gmail.com', 'Hà Nội', '097886554546', 1
		, 0 , null,  6, 0), 
        ('Nguyễn Thuỳ Dương', 0, '1986-02-19', '09324566', 'ntlinh@gmail.com', 'Hải Dương', '012352781234', 0
		, 0 , null,  6, 0), 
        ('Nguyễn Hoàng Anh', 1, '2008-09-03', null, null, 'Hà Nội', null, 0
		, 0 , null,  6, 0) , 
        ('Nguyễn Ngọc Linh', 0, '2011-11-23', null, null, 'Hà Nội', null, 0
		, 0 , null,  6, 0) ,
        
        ('Nguyễn Văn Hải', 1, '1996-12-03', '0989898989', 'nvhai96@gmail.com', 'Hà Nam', '102312348786', 1
		, 0 , null,  7, 0), 
        ('Nguyễn Tiến Anh', 1, '1996-02-19', '0932156566', 'ntanh@gmail.com', 'Hải Phòng', '012352781234', 0
		, 0 , null,  7, 0),
        
        ('Nguyễn Thị Yến', 0, '1994-06-08', '0979234756', 'ntyen@gmail.com', 'Bắc Ninh', '001125873639', 1
		, 0 , null,  8, 0), 
        ('Nguyễn Thị Mỹ Hạnh', 0, '1994-08-29', '0971267856', 'ntmhanh@gmail.com', 'Bắc Ninh', '012211781234', 0
		, 0 , null,  8, 0),
        
        ('Nguyễn Văn Cường', 1, '1973-01-11', '0964654789', 'nvcuong173@gmail.com', 'Thái Nguyên', '101002103421', 1
		, 0 , null,  9, 0), 
        ('Nguyễn Thị Hồng Ngọc', 0, '1974-04-11', '0334245566', 'nthngoc@gmail.com', 'Vĩnh Phúc', '001486451234', 0
		, 0 , null,  9, 0), 
        ('Nguyễn Văn Đông', 1, '1994-10-04', '0383874566', 'nvdong94@gmail.com', 'Thái Nguyên', '001765497364', 0
		, 0 , null,  9, 0),
        
        ('Lý Hồng Thắng', 1, '1987-01-11', '0987654567', 'lhthang@gmail.com', 'Hà Nội', '100342182385', 1
		, 0 , null,  10, 0), 
        ('Vũ Thuỳ Linh', 0, '1989-04-11', '0341245566', 'vtlinh411@gmail.com', 'Bắc Ninh', '011366451234', 0
		, 0 , null,  10, 0), 
        ('Lý Ngọc Anh', 0, '2013-10-04', null, null, 'Hà Nội', null, 0
		, 0 , null,  10, 0) ,
        
        ('Nguyễn Hồng Quân', 1, '1985-02-03', '0398719283', 'nhquan85@gmail.com', 'Hà Nội', '121495387362', 1
		, 0 , null,  11, 0), 
        ('Nguyễn Thị Hà', 0, '1986-04-14', '0393365566', 'ntha86@gmail.com', 'Hà Nội', '001123231133', 0
		, 0 , null,  11, 0), 
        ('Nguyễn Ngọc Quỳnh Chi', 0, '2010-09-23', null, null, 'Hà Nội', null, 0
		, 0 , null,  11, 0) ,
        
        ('Nguyễn Văn Nam', 1, '1997-11-12', '0955389127', 'nvnam97@gmail.com', 'Bắc Ninh', '101403204506', 1
		, 0 , null,  12, 0), 
        ('Nguyễn Văn Long', 1, '1997-06-02', '0932435566', 'nvlong97@gmail.com', 'Thái Bình', '012312398234', 0
		, 0 , null,  12, 0), 
        ('Nguyễn Hữu Trường', 1, '1997-02-23', '0938416526', 'nhtruong97@gmail.com', 'Thái Bình', '012390570234', 0
		, 0 , null,  12, 0),
        
        ('Nguyễn Quang Tuấn', 1, '1983-11-12', '0986573921', 'nqtuan12@gmail.com', 'Hà Nội', '029419395125', 1
		, 1 , '2013-03-03',  13, 1), 
        ('Nguyễn Việt Phương', 0, '1984-08-22', '0382435566', 'nvphuong@gmail.com', 'Hà Nội', '012387568234', 0
		, 1 , '2013-03-03',  13, 1), 
        ('Nguyễn Việt Anh', 1, '2006-02-23', null, null, 'Hà Nội', null, 0
		, 1 , '2013-03-03',  13, 1),
        
        ('Trần Trung Quyết', 1, '1984-11-12', '0975381289', 'ttquyet@gmail.com', 'Hà Nội', '047281249897', 1
		, 1 , '2015-12-12',  14, 1), 
        ('Nguyễn Thị Quỳnh', 0, '1985-08-22', '0382435566', 'ntquynh@gmail.com', 'Hà Nội', '012312398234', 0
		, 1 , '2015-12-12',  14, 1), 
        ('Trần Thanh Giang', 1, '2008-02-23', null, null, 'Hà Nội', null, 0
		, 1 , '2015-12-12',  14, 1),
        
        ('Nguyễn Quốc Anh', 1, '1982-11-12', '0975731265', 'nqanh82@gmail.com', 'Hà Nam', '000101645764', 1
		, 1 , '2014-03-03',  15, 1), 
        ('Nguyễn Thị Quỳnh Trang', 0, '1984-08-22', '0382436846', 'ntqtrang@gmail.com', 'Hà Nội', '012312355234', 0
		, 1 , '2014-03-03',  15, 1), 
        ('Nguyễn Quỳnh Anh', 0, '2005-02-23', null, null, 'Hà Nam', null, 0
		, 1 , '2014-03-03',  15, 1),
        
        ('Lê Văn Linh', 1, '1987-12-12', '0975731265', 'nqanh82@gmail.com', 'Hà Nội', '010003004005', 1
		, 1 , '2015-03-03',  16, 1), 
        ('Nguyễn Thị Ngọc Anh', 0, '1987-04-22', '0382436846', 'ntqtrang@gmail.com', 'Hải Dương', '018765355234', 0
		, 1 , '2015-03-03',  16, 1), 
        ('Lê Thị Hiền', 1, '2009-03-23', null, null, 'Hà Nam', null, 0
		, 1 , '2015-03-03',  16, 1) ,
        
        ('Ngô Đức Anh', 1, '1990-01-03', '0969563286', 'ngoducanh@gmail.com', 'Hà Nội', '000103048982', 1
		, 0 , null,  17, 0), 
        ('Nguyễn Quỳnh Anh', 0, '1990-04-14', '0393533276', 'nguyenquynhanh@gmail.com', 'Hà Nội', '001123985133', 0
		, 0 , null,  17, 0), 
        ('Ngô Thanh Vân', 0, '2016-06-24', null, null, 'Hà Nội', null, 0
		, 0 , null,  17, 0) ,
        
        ('Nguyễn Văn Tú', 1, '1992-01-03', '0965637281', 'nvtu92@gmail.com', 'Hà Nội', '019829329819', 1
		, 0 , null,  18, 0), 
        ('Nguyễn Thị Hiền', 0, '1993-04-14', '0393387276', 'nthien93@gmail.com', 'Hà Nội', '001123985133', 0
		, 0 , null,  18, 0), 
        ('Nguyễn Tuấn Anh', 1, '2017-08-25', null, null, 'Hà Nội', null, 0
		, 0 , null,  18, 0),
        
        ('Vũ Văn Việt', 1, '1993-05-05', '0965123472', 'vvviet@gmail.com', 'Hà Nội', '000101987649', 1
		, 0 , null,  19, 0), 
        ('Nguyễn Thu Trang', 0, '1993-08-18', '0393212786', 'nttrang@gmail.com', 'Hà Nội', '00112783462', 0
		, 0 , null,  19, 0), 
        ('Vũ Văn Tùng', 1, '2017-12-30', null, null, 'Hà Nội', null, 0
		, 0 , null,  19, 0);
        
SET SQL_SAFE_UPDATES = 0; 
update user set is_enable = 0;

insert into card_type(name) values ('Thẻ ra vào'), ('Thẻ gửi xe');

insert into verhicle_type(name) values ('Xe máy'), ('Ô tô'), ('Xe đạp');


insert into card(card_numb, card_type_id, user_id, created_by) 
values ('1234567', 1, 4, 1),
	   ('12345678', 1, 5, 1),
       ('12345679', 1, 6, 1),
       ('12345671', 1, 7, 1),
       ('12345672', 1, 8, 1),
       ('12345673', 2, 4, 1),
       ('12345674', 2, 4, 1);

insert into verhicle(plate_numb, card_numb, user_id, verhicle_type) 
values ('29A536689', '12345673', 4, 1),
	   ('29V636499', '12345674', 4, 2);
       

insert into device_group (name) values ('Thang máy'), ('Điện'), ('Phòng cháy chữa cháy'), ('Cấp thoát nước'), ('Căn hộ');

INSERT INTO `apartment`.`device_type` (`name`) VALUES ('Thang máy'), ('Điện'), ('Phòng cháy chữa cháy'), ('Cấp thoát nước'), ('Căn hộ');

INSERT INTO `apartment`.`spec` (`name`, `device_type`) VALUES ('Loại thang', 1);
INSERT INTO `apartment`.`spec` (`name`, `device_type`) VALUES ('Tốc độ (M/P)', 1);
INSERT INTO `apartment`.`spec` (`name`, `device_type`) VALUES ('Tải trọng (KG)', 1);
INSERT INTO `apartment`.`spec` (`name`, `device_type`) VALUES ('Số người', 1);
INSERT INTO `apartment`.`spec` (`name`, `device_type`) VALUES ('Cửa thang (WD)', 1);
INSERT INTO `apartment`.`spec` (`name`, `device_type`) VALUES ('Phòng thang (AA x BB)', 1);
INSERT INTO `apartment`.`spec` (`name`, `device_type`) VALUES ('Hố thang (WH x DM)', 1);
INSERT INTO `apartment`.`spec` (`name`, `device_type`) VALUES ('Hố PIT', 1);
INSERT INTO `apartment`.`spec` (`name`, `device_type`) VALUES ('Overhead', 1);
        
INSERT INTO `apartment`.`device` 
(`name`, `sign`, `provider`, `installed_date`, `operation_date`, `unit`, `quantity`, `price`, `description`, `maintenance_cycle`,
 `status`, `device_type`, `device_group`) VALUES 
('Thang máy 750', 'TM1', 'Mitsubishi', '2009-02-02', '2010-01-01', 'Cái', 
	4, '300000000', 'Thang máy 750KG', '6', 1, 1, 1),
('Thang máy 900', 'TM2', 'Mitsubishi', '2009-02-02', '2010-01-01', 'Cái', 
	2, '0', 'Thang máy 900KG', '6', 1, 1, 1),
('Điện lạnh', 'ĐL', 'Mitsubishi', '2009-02-02', '2010-01-01', 'HT', 
	2, '0', 'HT điện lạnh', '6', 1, 2, 2),
('PCCC', 'ĐL', 'Mitsubishi', '2009-02-02', '2010-01-01', 'HT', 
	2, '0', 'HT PCCC', '6', 1, 3, 3),
('Cấp thoát nước', 'ĐL', 'Mitsubishi', '2009-02-02', '2010-01-01', 'HT', 
	2, '0', 'HT Cấp thoát nước', '6', 1, 4, 4),
('Căn hộ', 'ĐL', 'Mitsubishi', '2009-02-02', '2010-01-01', 'HT', 
	2, '0', 'Sửa chữa căn hô', '6', 1, 5, 5);


INSERT INTO `apartment`.`device_spec` (`device_id`, `spec_name`, `val`) 
VALUES 	  (1, 'Loại thang', 'P11-CO')
        , (1, 'Tốc độ (M/P)', '60')
        , (1, 'Tải trọng (KG)', '750')
        , (1, 'Số người', '12')
        , (1, 'Cửa thang (WD)', '800')
        , (1, 'Phòng thang (AA x BB)', '1400 x 1300')
        , (1, 'Hố thang (WH x DM)', '1800 x 1900')
        , (1, 'Hố PIT', '1400')
        , (1, 'Overhead', '4200')
        
        , (2, 'Loại thang', 'P13-CO')
        , (2, 'Tốc độ (M/P)', '90')
        , (2, 'Tải trọng (KG)', '900')
        , (2, 'Số người', '15')
        , (2, 'Cửa thang (WD)', '900')
        , (2, 'Phòng thang (AA x BB)', '2000 x 2000')
        , (2, 'Hố thang (WH x DM)', '2000 x 2500')
        , (2, 'Hố PIT', '1500')
        , (2, 'Overhead', '4500');
        
        
INSERT INTO `apartment`.`maintenance` (`device_group`, `maintenance_date`, `description`, `maintenance_price`, `is_excuted`, `number_personnel`, `paid`) 
VALUES ('1', '2019-04-20', 'Kiểm tra vận hành thang máy', 0, 1, 2,1),
		('1', '2019-04-19', 'Kiểm tra vận hành thang máy', 0, 1, 2, 1),
        ('1', '2019-04-21', 'Kiểm tra vận hành thang máy', 0, 1, 2, 1),
        ('1', '2019-04-22', 'Khắc phục sự cố thang máy', 0, 1, 2, 1);
        
INSERT INTO `apartment`.`maintenance_personnel` (`maintenance_id`, `employee_id`, `is_supervisor`) 
VALUES (1, 4, 1), (1, 5, 0), (2, 4, 1), (2, 5, 0), (3, 4, 0), (3, 5, 1), (4, 4, 0), (4, 5, 1);


INSERT INTO `apartment`.`maintenance_detail` (`maintenance_id`, `device_id`, `price`, `description`, `location`) 
VALUES ('1', '1', '0', 'Thang máy hoạt động bình thường', null), 
	   ('1', '2', '0', 'Thang máy hoạt động bình thường', null),
		('2', '1', '0', 'Thang máy hoạt động bình thường', null),
        ('2', '2', '0', 'Thang máy hoạt động bình thường', null),
        ('3', '1', '0', 'Thang máy hoạt động bình thường', null),
        ('3', '2', '0', 'Thang máy hoạt động bình thường', null),
        ('4', '1', '0', 'Kẹt cửa thang máy số 1', 'Thang máy số 1 khu A');
	

INSERT INTO `apartment`.`service_type` (`name`, `price`, `unit`, `supplier`, `increase`) 
Values  ('Điện', '2300', 'đồng/Kwh', 'Tổng công ty Điện lực  Việt Nam', '10 - 1360;20 - 1680; 50 - 2300'),
	   ('Nước', '9969', 'đồng/m3', 'Công ty nước sạch Hà Nội', '10 - 7890;20 - 8568; 50 - 9969');
       
INSERT INTO `apartment`.`service_type` (`name`, `price`, `unit`, `supplier`) 
VALUES  ('Quản lý chung cư', '8000', 'đồng/m2', 'Ban quản lý chung cư');