/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : localhost:3306
 Source Schema         : course

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : 65001

 Date: 06/05/2023 13:31:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for audit
-- ----------------------------
DROP TABLE IF EXISTS `audit`;
CREATE TABLE `audit`  (
  `aid` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `cid` int(11) NOT NULL COMMENT '课程id',
  `uid` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `reason` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '申请原因',
  `prove` longtext CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL COMMENT '证明',
  `finaloneid` int(11) NULL DEFAULT NULL COMMENT '审核人1',
  `finaltwoid` int(11) NULL DEFAULT NULL COMMENT '审核人2',
  `status` varchar(11) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '课程主讲教师审批中  主管教师审批中  申请成功  申请驳回  完成',
  `rejectedreason` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '驳回原因',
  PRIMARY KEY (`aid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '院系实体' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of audit
-- ----------------------------

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `cid` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `cname` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '名称',
  `cremark` varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  `auditone` int(11) NULL DEFAULT NULL,
  `audittwo` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`cid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '院系实体' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES (1, '高数', '', 32, 33);
INSERT INTO `course` VALUES (2, '大物', '物理', 28, -1);
INSERT INTO `course` VALUES (3, '计算机理论', '', 0, 0);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `userid` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `userrole` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '身份/角色',
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '用户名',
  `userpwd` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '用户密码',
  `realname` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '真实姓名',
  `status` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `userremark` varchar(5000) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '用户备注',
  PRIMARY KEY (`userid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '用户实体' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '管理员', 'admin', '4297f44b13955235245b2497399d7a93', '管理员', '1', '<p>[用户名：admin，密码：123123]</p>');
INSERT INTO `user` VALUES (2, '学生', 'user', '4297f44b13955235245b2497399d7a93', '张少锋', '1', '');
INSERT INTO `user` VALUES (28, '课程主讲教师', 'T001', '4297f44b13955235245b2497399d7a93', '张露', '1', '第一审批人');
INSERT INTO `user` VALUES (29, '主管教师', 'T002', '4297f44b13955235245b2497399d7a93', '赵爽', '1', '第二审批人');
INSERT INTO `user` VALUES (32, '课程主讲教师', 'T003', '4297f44b13955235245b2497399d7a93', '艾萨', '1', '');
INSERT INTO `user` VALUES (33, '主管教师', 'T004', '4297f44b13955235245b2497399d7a93', '李亮', '1', '');

-- ----------------------------
-- View structure for scms_classinfo
-- ----------------------------
DROP VIEW IF EXISTS `scms_classinfo`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `scms_classinfo` AS select `c`.`grade` AS `grade`,`d`.`deptid` AS `deptid`,`d`.`deptname` AS `deptname`,`m`.`majorid` AS `majorid`,`m`.`majorname` AS `majorname`,`c`.`classid` AS `classid`,`c`.`classname` AS `classname`,`c`.`classremark` AS `classremark` from ((`classes` `c` join `major` `m` on((`c`.`majorid` = `m`.`majorid`))) join `department` `d` on((`m`.`deptid` = `d`.`deptid`)));

-- ----------------------------
-- View structure for scms_loginloginfo
-- ----------------------------
DROP VIEW IF EXISTS `scms_loginloginfo`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `scms_loginloginfo` AS select `loginlog`.`logid` AS `logid`,`loginlog`.`userid` AS `userid`,`loginlog`.`logintime` AS `logintime`,`loginlog`.`loginsite` AS `loginsite`,`loginlog`.`loginip` AS `loginip`,`loginlog`.`loginos` AS `loginos`,`loginlog`.`loginbrowser` AS `loginbrowser`,`loginlog`.`loginremark` AS `loginremark`,`user`.`realname` AS `realname`,`user`.`username` AS `username` from (`loginlog` join `user`) where (`loginlog`.`userid` = `user`.`userid`);

-- ----------------------------
-- View structure for scms_statinfo
-- ----------------------------
DROP VIEW IF EXISTS `scms_statinfo`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `scms_statinfo` AS select `student`.`stuname` AS `stuname`,`student`.`stuid` AS `stuid`,`classes`.`classid` AS `classid`,`classes`.`grade` AS `grade`,`major`.`majorname` AS `majorname`,`stu_team`.`sturole` AS `sturole`,`team`.`teamid` AS `teamid`,`team`.`teamname` AS `teamname`,`team`.`adviser` AS `adviser`,`team`.`award` AS `award`,`competition`.`competitionid` AS `competitionid`,`competition`.`competitionname` AS `competitionname`,`competition`.`competitiondate` AS `competitiondate`,`major`.`majorid` AS `majorid`,`classes`.`classname` AS `classname` from (((((`student` join (`classes` join `major` on((`classes`.`majorid` = `major`.`majorid`)))) join `department`) join `stu_team`) join `team`) join `competition`) where ((`student`.`classid` = `classes`.`classid`) and (`classes`.`majorid` = `major`.`majorid`) and (`major`.`deptid` = `department`.`deptid`) and (`student`.`stuid` = `stu_team`.`stuid`) and (`stu_team`.`teamid` = `team`.`teamid`) and (`team`.`competitionid` = `competition`.`competitionid`));

-- ----------------------------
-- View structure for scms_studentinfo
-- ----------------------------
DROP VIEW IF EXISTS `scms_studentinfo`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `scms_studentinfo` AS select `s`.`stuid` AS `stuid`,`s`.`stuname` AS `stuname`,`s`.`sex` AS `sex`,`s`.`mobile` AS `mobile`,`c`.`classid` AS `classid`,`c`.`classname` AS `classname`,`m`.`majorid` AS `majorid`,`m`.`majorname` AS `majorname`,`d`.`deptid` AS `deptid`,`d`.`deptname` AS `deptname`,`c`.`grade` AS `grade` from (((`student` `s` join `classes` `c` on((`s`.`classid` = `c`.`classid`))) join `major` `m` on((`c`.`majorid` = `m`.`majorid`))) join `department` `d` on((`m`.`deptid` = `d`.`deptid`)));

-- ----------------------------
-- View structure for scms_stu_competition
-- ----------------------------
DROP VIEW IF EXISTS `scms_stu_competition`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `scms_stu_competition` AS select `stu_team`.`stuid` AS `stuid`,`team`.`competitionid` AS `competitionid`,`stu_team`.`sturole` AS `sturole` from (`team` join `stu_team`) where (`stu_team`.`teamid` = `team`.`teamid`);

-- ----------------------------
-- View structure for scms_team_info
-- ----------------------------
DROP VIEW IF EXISTS `scms_team_info`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `scms_team_info` AS select `team`.`teamid` AS `teamid`,`team`.`teamgroup` AS `teamgroup`,`team`.`teamname` AS `teamname`,`team`.`leader` AS `leader`,`team`.`adviser` AS `adviser`,`team`.`registusername` AS `registusername`,`team`.`registdate` AS `registdate`,`team`.`award` AS `award`,`competition`.`competitionname` AS `competitionname` from (`team` join `competition`) where (`team`.`competitionid` = `competition`.`competitionid`);

SET FOREIGN_KEY_CHECKS = 1;
