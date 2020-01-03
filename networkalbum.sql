/*
Navicat MySQL Data Transfer

Source Server         : 106.15.251.188_3306
Source Server Version : 50727
Source Host           : 106.15.251.188:3306
Source Database       : networkalbum

Target Server Type    : MYSQL
Target Server Version : 50727
File Encoding         : 65001

Date: 2020-01-03 14:44:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `a_id` int(11) NOT NULL AUTO_INCREMENT,
  `a_account` varchar(15) NOT NULL,
  `a_pwd` varchar(20) NOT NULL,
  `a_addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `a_updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`a_id`),
  UNIQUE KEY `a_account` (`a_account`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for album
-- ----------------------------
DROP TABLE IF EXISTS `album`;
CREATE TABLE `album` (
  `a_id` int(11) NOT NULL AUTO_INCREMENT,
  `a_u_id` int(11) NOT NULL,
  `a_t_id` int(11) NOT NULL,
  `a_name` varchar(50) NOT NULL,
  `a_desc` varchar(100) DEFAULT NULL,
  `a_auth` tinyint(4) DEFAULT '1',
  `a_addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `a_updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`a_id`),
  UNIQUE KEY `a_name` (`a_name`),
  KEY `fk_album_user` (`a_u_id`),
  KEY `fk_album_theme` (`a_t_id`),
  CONSTRAINT `fk_album_theme` FOREIGN KEY (`a_t_id`) REFERENCES `theme` (`t_id`) ON DELETE CASCADE,
  CONSTRAINT `fk_album_user` FOREIGN KEY (`a_u_id`) REFERENCES `user` (`u_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for image
-- ----------------------------
DROP TABLE IF EXISTS `image`;
CREATE TABLE `image` (
  `i_id` int(11) NOT NULL AUTO_INCREMENT,
  `i_a_id` int(11) NOT NULL,
  `i_name` varchar(100) NOT NULL,
  `i_path` varchar(255) NOT NULL,
  `i_addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `i_updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`i_id`),
  KEY `fk_image_album` (`i_a_id`),
  CONSTRAINT `fk_image_album` FOREIGN KEY (`i_a_id`) REFERENCES `album` (`a_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for theme
-- ----------------------------
DROP TABLE IF EXISTS `theme`;
CREATE TABLE `theme` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_name` varchar(50) NOT NULL,
  `t_addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `t_updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`t_id`),
  UNIQUE KEY `t_name` (`t_name`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `u_id` int(11) NOT NULL AUTO_INCREMENT,
  `u_nickname` varchar(40) DEFAULT NULL,
  `u_phone` char(11) NOT NULL,
  `u_pwd` varchar(20) NOT NULL DEFAULT '123456',
  `u_gender` char(2) DEFAULT NULL,
  `u_qq` varchar(12) DEFAULT NULL,
  `u_addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `u_updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`u_id`),
  UNIQUE KEY `u_phone` (`u_phone`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
