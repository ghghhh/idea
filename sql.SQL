CREATE TABLE `sys_menu` (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(32) NOT NULL,
  `parent_id` int(6) NOT NULL,
  `menu_type` varchar(32) DEFAULT NULL,
  `menu_url` varchar(64) DEFAULT NULL,
  `menu_state` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `sys_permission` (
  `id` smallint(4) NOT NULL AUTO_INCREMENT,
  `permission_name` varchar(16) DEFAULT NULL,
  `permission_url` varchar(32) DEFAULT NULL,
  `enabled` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `sys_role` (
  `id` smallint(4) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(16) DEFAULT NULL,
  `cn_name` varchar(16) DEFAULT NULL,
  `enabled` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `sys_role_menu` (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `role_id` int(6) NOT NULL,
  `menu_id` int(6) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `sys_role_permission` (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `role_id` int(6) NOT NULL,
  `permission_id` int(6) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `sys_user` (
  `id` bigint(8) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_name` varchar(16) NOT NULL,
  `user_password` varchar(128) NOT NULL,
  `info` varchar(120) DEFAULT NULL,
  `sex` char(1) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `mobile_phone` char(11) DEFAULT NULL,
  `email` varchar(32) DEFAULT NULL,
  `enabled` char(1) DEFAULT NULL,
  `createBy` varchar(32) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `updateBy` varchar(32) DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `sys_user_role` (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `user_id` int(6) NOT NULL,
  `role_id` int(6) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
