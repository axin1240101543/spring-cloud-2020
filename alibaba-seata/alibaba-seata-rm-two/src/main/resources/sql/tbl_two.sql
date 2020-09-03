/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50641
Source Host           : localhost:3306
Source Database       : seata-rm-two

Target Server Type    : MYSQL
Target Server Version : 50641
File Encoding         : 65001

Date: 2020-09-03 14:30:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tbl_two
-- ----------------------------
DROP TABLE IF EXISTS `tbl_two`;
CREATE TABLE `tbl_two` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;
