CREATE SCHEMA dragon;

#借书记录表，id为自动生成，student_id为学号，booknumber为书的编号
CREATE TABLE dragon.borrowrecord (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` varchar(11) NOT NULL,
  `booknumber` int(11) NOT NULL,
  PRIMARY KEY (`id`)
);

#评论纪录表，id为自动生成，student_id为学号，discuss为评价，booknumber为书的编号
CREATE TABLE dragon.discuss (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) NOT NULL,
  `discuss` varchar(200) NOT NULL,
  `booknumber` int(11) NOT NULL,
  PRIMARY KEY (`id`)
);

#管理表，booknumber为书的编号为自动生成，name为书的名称，summary为书的简介，quantity为书的库存量，author为作者名称，bookimages为书本的图片名
CREATE TABLE dragon.management (
  `booknumber` int(11) NOT NULL AUTO_INCREMENT,
`name` varchar(45) NOT NULL,
`summary` varchar(200) NOT NULL,
`quantity` int(11) NOT NULL,
`author` varchar(45) NOT NULL,
`bookimages` varchar(45) NOT NULL,
`type` varchar(45) NOT NULL,
PRIMARY KEY (`booknumber`)
);

#登录注册表
CREATE TABLE dragon.loginregist (
  `number` int(11) NOT NULL AUTO_INCREMENT,

  `student_id` int(11) NOT NULL,
  `student_name` varchar(45) DEFAULT NULL,
  
`password` varchar(45) NOT NULL,
  `sex` varchar(45) DEFAULT NULL,
 
 `email` varchar(45) DEFAULT NULL,
  `home` varchar(45) DEFAULT NULL,
  
`sign` varchar(100) DEFAULT NULL,
 
 PRIMARY KEY (`number`),
  UNIQUE KEY `student_id_UNIQUE` (`student_id`)
);
 ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8

