/*
 Navicat Premium Data Transfer

 Source Server         : 本地MySQL
 Source Server Type    : MySQL
 Source Server Version : 50130
 Source Host           : localhost:3306
 Source Schema         : cmis

 Target Server Type    : MySQL
 Target Server Version : 50130
 File Encoding         : 65001

 Date: 15/03/2021 22:22:42
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for classroom
-- ----------------------------
DROP TABLE IF EXISTS `classroom`;
CREATE TABLE `classroom`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '教室编号',
  `seatnum` int(20) NOT NULL COMMENT '座位数',
  `isstair` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '是否为阶梯教室',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of classroom
-- ----------------------------
INSERT INTO `classroom` VALUES ('北1101', 209, '否');
INSERT INTO `classroom` VALUES ('北1102', 250, '是');
INSERT INTO `classroom` VALUES ('北1104', 189, '是');
INSERT INTO `classroom` VALUES ('北1201', 190, '否');
INSERT INTO `classroom` VALUES ('北1202', 120, '是');
INSERT INTO `classroom` VALUES ('北1301', 90, '是');
INSERT INTO `classroom` VALUES ('北2101', 150, '是');
INSERT INTO `classroom` VALUES ('北2102', 200, '是');
INSERT INTO `classroom` VALUES ('南1101', 200, '否');
INSERT INTO `classroom` VALUES ('南1102', 250, '否');
INSERT INTO `classroom` VALUES ('南2101', 180, '否');
INSERT INTO `classroom` VALUES ('南2102', 250, '是');
INSERT INTO `classroom` VALUES ('南2201', 150, '是');

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课程编号',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课程全称',
  `type` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课程类型（必修/选修）',
  `weeksno` int(11) NOT NULL COMMENT '课程起始周',
  `weekeno` int(11) NOT NULL COMMENT '课程结束周',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('101', '操作系统', '必修课', 1, 16);
INSERT INTO `course` VALUES ('102', '编译原理', '必修课', 2, 14);
INSERT INTO `course` VALUES ('103', '汇编语言', '必修课', 2, 16);
INSERT INTO `course` VALUES ('104', '软件方法与过程', '必修课', 1, 16);
INSERT INTO `course` VALUES ('201', '软件开发环境', '选修课', 1, 16);
INSERT INTO `course` VALUES ('202', '面向对象技术', '选修课', 1, 8);

-- ----------------------------
-- Table structure for teachcourse
-- ----------------------------
DROP TABLE IF EXISTS `teachcourse`;
CREATE TABLE `teachcourse`  (
  `cid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课程编号',
  `tid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '教师编号',
  `tscore` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '教评得分',
  PRIMARY KEY (`cid`, `tid`) USING BTREE,
  INDEX `tc_tid_foreign`(`tid`) USING BTREE,
  INDEX `cid`(`cid`) USING BTREE,
  CONSTRAINT `tc_cid_foreign` FOREIGN KEY (`cid`) REFERENCES `course` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tc_tid_foreign` FOREIGN KEY (`tid`) REFERENCES `teacher` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of teachcourse
-- ----------------------------
INSERT INTO `teachcourse` VALUES ('101', '101', NULL);
INSERT INTO `teachcourse` VALUES ('101', '102', NULL);
INSERT INTO `teachcourse` VALUES ('101', '201', NULL);
INSERT INTO `teachcourse` VALUES ('102', '103', NULL);
INSERT INTO `teachcourse` VALUES ('102', '104', NULL);
INSERT INTO `teachcourse` VALUES ('102', '201', NULL);
INSERT INTO `teachcourse` VALUES ('103', '104', NULL);
INSERT INTO `teachcourse` VALUES ('103', '202', NULL);
INSERT INTO `teachcourse` VALUES ('103', '204', NULL);
INSERT INTO `teachcourse` VALUES ('104', '203', NULL);
INSERT INTO `teachcourse` VALUES ('104', '204', NULL);
INSERT INTO `teachcourse` VALUES ('201', '101', NULL);
INSERT INTO `teachcourse` VALUES ('201', '103', NULL);
INSERT INTO `teachcourse` VALUES ('202', '102', NULL);
INSERT INTO `teachcourse` VALUES ('202', '203', NULL);

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '教师工号',
  `name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '教师名称',
  `sex` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '教师性别',
  `title` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '教师职称',
  `dep` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所在院系',
  `pwd` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '教师密码，默认与id一致',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('101', '王红', '女', '教授', '信息工程学院', '101');
INSERT INTO `teacher` VALUES ('102', '李明', '男', '副教授', '信息工程学院', '102');
INSERT INTO `teacher` VALUES ('103', '孙磊', '男', '教师', '信息工程学院', '103');
INSERT INTO `teacher` VALUES ('104', '陶梅', '女', '教师', '信息工程学院', '104');
INSERT INTO `teacher` VALUES ('201', '吴云', '男', '副教授', '信息工程学院', '201');
INSERT INTO `teacher` VALUES ('202', '李志', '男', '副教授', '信息工程学院', '202');
INSERT INTO `teacher` VALUES ('203', '王明', '男', '教师', '信息工程学院', '203');
INSERT INTO `teacher` VALUES ('204', '吴微微', '女', '教授', '信息工程学院', '204');

-- ----------------------------
-- Table structure for timespan
-- ----------------------------
DROP TABLE IF EXISTS `timespan`;
CREATE TABLE `timespan`  (
  `ano` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '上课编号',
  `tid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '教师编号',
  `cid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课程编号',
  `rid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '教室编号',
  `type` int(10) NOT NULL COMMENT '单/双/连续周 1/2/3',
  `weekdayno` int(10) NOT NULL COMMENT '星期几',
  `secsno` int(10) NOT NULL COMMENT '当日课的节开始序号 ',
  `seceno` int(11) NOT NULL COMMENT '当日课的节结束序号',
  PRIMARY KEY (`ano`) USING BTREE,
  INDEX `timespan_foreign_cid`(`cid`) USING BTREE,
  INDEX `timespan_foreign_tid`(`tid`) USING BTREE,
  INDEX `timespan_foreign_rid`(`rid`) USING BTREE,
  CONSTRAINT `timespan_foreign_cid` FOREIGN KEY (`cid`) REFERENCES `course` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `timespan_foreign_rid` FOREIGN KEY (`rid`) REFERENCES `classroom` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `timespan_foreign_tid` FOREIGN KEY (`tid`) REFERENCES `teacher` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of timespan
-- ----------------------------
INSERT INTO `timespan` VALUES ('101-101-北1101-1-1-1-2', '101', '101', '北1101', 1, 1, 1, 2);
INSERT INTO `timespan` VALUES ('101-101-北2101-1-2-3-4', '101', '101', '北2101', 1, 2, 3, 4);
INSERT INTO `timespan` VALUES ('101-101-南2201-3-2-5-6', '101', '101', '南2201', 3, 2, 5, 6);
INSERT INTO `timespan` VALUES ('101-201-北1101-1-1-5-6', '101', '201', '北1101', 1, 1, 5, 6);
INSERT INTO `timespan` VALUES ('101-201-北1102-3-5-1-2', '101', '201', '北1102', 3, 5, 1, 2);
INSERT INTO `timespan` VALUES ('101-201-北2201-2-5-5-6', '101', '201', '北2201', 2, 5, 5, 6);
INSERT INTO `timespan` VALUES ('102-101-北1102-1-1-1-2', '102', '101', '北1102', 1, 1, 1, 2);
INSERT INTO `timespan` VALUES ('102-101-南1101-3-3-1-2', '102', '101', '南1101', 3, 3, 1, 2);
INSERT INTO `timespan` VALUES ('102-202-北1101-2-2-5-6', '102', '202', '北1101', 2, 2, 5, 6);
INSERT INTO `timespan` VALUES ('102-202-南2201-3-4-5-6', '102', '202', '南2201', 3, 4, 5, 6);
INSERT INTO `timespan` VALUES ('103-102-南1101-3-1-1-2', '103', '102', '南1101', 3, 1, 1, 2);
INSERT INTO `timespan` VALUES ('103-102-南1102-2-3-3-4', '103', '102', '南1102', 2, 3, 3, 4);
INSERT INTO `timespan` VALUES ('103-102-南2201-1-4-3-4', '103', '102', '南2201', 1, 4, 3, 4);
INSERT INTO `timespan` VALUES ('103-201-南1102-3-5-1-2', '103', '201', '南1102', 3, 5, 1, 2);
INSERT INTO `timespan` VALUES ('103-201-南2201-2-5-5-6', '103', '201', '南2201', 2, 5, 5, 6);
INSERT INTO `timespan` VALUES ('104-102-北1101-3-4-1-2', '104', '102', '北1101', 3, 4, 1, 2);
INSERT INTO `timespan` VALUES ('104-102-北1102-3-2-5-6', '104', '102', '北1102', 3, 2, 5, 6);
INSERT INTO `timespan` VALUES ('104-103-北2101-3-3-1-2', '104', '103', '北2101', 3, 3, 1, 2);
INSERT INTO `timespan` VALUES ('201-101-北1101-3-2-3-4', '201', '101', '北1101', 3, 2, 3, 4);
INSERT INTO `timespan` VALUES ('201-101-南1101-1-2-1-2', '201', '101', '南1101', 1, 2, 1, 2);
INSERT INTO `timespan` VALUES ('201-101-南1101-2-2-1-2', '201', '101', '南1101', 2, 2, 1, 2);
INSERT INTO `timespan` VALUES ('201-101-南1101-3-2-1-2', '201', '101', '南1101', 3, 2, 1, 2);
INSERT INTO `timespan` VALUES ('201-101-南1102-1-2-1-2', '201', '101', '南1102', 1, 2, 1, 2);
INSERT INTO `timespan` VALUES ('201-101-南2201-2-3-5-6', '201', '101', '南2201', 2, 3, 5, 6);
INSERT INTO `timespan` VALUES ('201-102-北1102-1-3-1-2', '201', '102', '北1102', 1, 3, 1, 2);
INSERT INTO `timespan` VALUES ('201-102-南2201-3-1-1-2', '201', '102', '南2201', 3, 1, 1, 2);
INSERT INTO `timespan` VALUES ('202-103-南1101-1-1-5-6', '202', '103', '南1101', 1, 1, 5, 6);
INSERT INTO `timespan` VALUES ('202-103-南1102-3-3-5-6', '202', '103', '南1102', 3, 3, 5, 6);
INSERT INTO `timespan` VALUES ('203-104-北2101-2-2-5-6', '203', '104', '北2101', 2, 2, 5, 6);
INSERT INTO `timespan` VALUES ('203-104-北2101-3-1-5-6', '203', '104', '北2101', 3, 1, 5, 6);
INSERT INTO `timespan` VALUES ('203-202-南1101-3-4-5-6', '203', '202', '南1101', 3, 4, 5, 6);
INSERT INTO `timespan` VALUES ('204-103-北1101-3-2-1-2', '204', '103', '北1101', 3, 2, 1, 2);
INSERT INTO `timespan` VALUES ('204-103-北2101-2-4-1-2', '204', '103', '北2101', 2, 4, 1, 2);
INSERT INTO `timespan` VALUES ('204-104-北2101-3-2-1-2', '204', '104', '北2101', 3, 2, 1, 2);
INSERT INTO `timespan` VALUES ('204-104-南1101-3-2-5-6', '204', '104', '南1101', 3, 2, 5, 6);

-- ----------------------------
-- Table structure for usr
-- ----------------------------
DROP TABLE IF EXISTS `usr`;
CREATE TABLE `usr`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账户用户名',
  `pwd` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账户密码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of usr
-- ----------------------------
INSERT INTO `usr` VALUES ('admin', 'admin');
INSERT INTO `usr` VALUES ('student', 'student');

-- ----------------------------
-- View structure for attendclassview
-- ----------------------------
DROP VIEW IF EXISTS `attendclassview`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `attendclassview` AS select timespan.cid as cid,course.name as cname,timespan.tid as tid,teacher.name as tname,timespan.rid as rid,
seatnum,isstair,weeksno,weekeno,weekdayno,secsno,seceno,timespan.type
from teacher,course,classroom,timespan
where 
			course.id=timespan.cid and teacher.id=timespan.tid and classroom.id=timespan.rid ;

-- ----------------------------
-- View structure for teachcourseview
-- ----------------------------
DROP VIEW IF EXISTS `teachcourseview`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `teachcourseview` AS select teacher.id as tid,teacher.name as tname,course.id as cid,course.name as cname,type,weeksno,weekeno from teacher,course,teachcourse
where teachcourse.tid=teacher.id and teachcourse.cid=course.id
order by teacher.id ;

SET FOREIGN_KEY_CHECKS = 1;
