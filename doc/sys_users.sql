/*
Navicat MySQL Data Transfer

Source Server         : local_mysql
Source Server Version : 50713
Source Host           : localhost:3306
Source Database       : rms

Target Server Type    : MYSQL
Target Server Version : 50713
File Encoding         : 65001

Date: 2017-08-22 20:51:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_users
-- ----------------------------
DROP TABLE IF EXISTS `sys_users`;
CREATE TABLE `sys_users` (
  `user_id` int(50) NOT NULL AUTO_INCREMENT COMMENT '用户的id',
  `user_name` varchar(50) NOT NULL COMMENT '用户名称',
  `user_login_name` varchar(50) DEFAULT NULL COMMENT '登录名称',
  `user_password` varchar(50) NOT NULL COMMENT '登录密码',
  `user_phone` varchar(22) NOT NULL COMMENT '用户手机号码',
  `user_email` varchar(60) DEFAULT NULL,
  `user_token` varchar(50) DEFAULT NULL,
  `user_type_cd` char(1) DEFAULT NULL,
  `user_status_cd` char(1) NOT NULL COMMENT '用户状态',
  `creat_id` int(50) DEFAULT NULL COMMENT '创建人',
  `creat_dt` datetime(6) DEFAULT NULL COMMENT '创建日期',
  `modify_id` int(50) DEFAULT NULL,
  `modify_dt` datetime(6) DEFAULT NULL,
  `valid_dt` datetime(6) NOT NULL COMMENT '有效日期',
  `user_manager` char(1) DEFAULT NULL COMMENT '是否是系统管理员（1是，0为普通用户）',
  `comments` varchar(50) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_users
-- ----------------------------
INSERT INTO `sys_users` VALUES ('2', 'test_1', 'test_1', '123456', '15377674320', '1@qq.com', null, '2', '1', '1', '2016-12-18 21:22:22.000000', null, null, '3000-01-01 01:01:01.000000', null, '');
INSERT INTO `sys_users` VALUES ('3', 'test_2', 'test_2', '123456', '15377674320', '1@qq.com', null, '2', '1', '1', '2016-12-18 21:26:23.000000', null, null, '3000-01-01 01:01:01.000000', null, '');
INSERT INTO `sys_users` VALUES ('4', 'test_03', null, '123', '111', '111', null, null, '0', '1', '2017-08-22 10:40:14.711000', null, null, '2017-08-22 10:40:14.711000', null, null);
INSERT INTO `sys_users` VALUES ('5', 'test-04', null, '111', '111', '111', null, null, '0', '1', '2017-08-22 10:43:35.760000', null, null, '2017-08-22 10:43:35.760000', null, null);
INSERT INTO `sys_users` VALUES ('7', 'rms', null, 'craft', '111', '111', null, null, '0', '1', '2017-08-22 17:54:10.259000', null, null, '2017-08-22 17:54:10.259000', null, null);
