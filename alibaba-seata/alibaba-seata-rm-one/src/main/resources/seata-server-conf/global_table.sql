/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50641
Source Host           : localhost:3306
Source Database       : seata-server

Target Server Type    : MYSQL
Target Server Version : 50641
File Encoding         : 65001

Date: 2020-09-03 14:28:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for global_table
-- ----------------------------
DROP TABLE IF EXISTS `global_table`;
CREATE TABLE `global_table` (
  `xid` varchar(128) NOT NULL,
  `transaction_id` bigint(20) DEFAULT NULL,
  `status` tinyint(4) NOT NULL,
  `application_id` varchar(32) DEFAULT NULL,
  `transaction_service_group` varchar(32) DEFAULT NULL,
  `transaction_name` varchar(128) DEFAULT NULL,
  `timeout` int(11) DEFAULT NULL,
  `begin_time` bigint(20) DEFAULT NULL,
  `application_data` varchar(2000) DEFAULT NULL,
  `gmt_create` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`xid`) USING BTREE,
  KEY `idx_gmt_modified_status` (`gmt_modified`,`status`) USING BTREE,
  KEY `idx_transaction_id` (`transaction_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='全局事务表';
