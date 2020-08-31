/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50641
Source Host           : localhost:3306
Source Database       : online-taxi

Target Server Type    : MYSQL
Target Server Version : 50641
File Encoding         : 65001

Date: 2020-08-31 14:57:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tbl_order_lock
-- ----------------------------
DROP TABLE IF EXISTS `tbl_order_lock`;
CREATE TABLE `tbl_order_lock` (
  `order_id` int(11) NOT NULL,
  `driver_id` int(11) NOT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单锁表';
