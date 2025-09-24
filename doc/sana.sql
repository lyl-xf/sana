/*
 Navicat Premium Dump SQL

 Target Server Type    : MySQL
 Target Server Version : 80033 (8.0.33)
 File Encoding         : 65001

 Date: 24/09/2025 10:16:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for QRTZ_BLOB_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_BLOB_TRIGGERS`;
CREATE TABLE `QRTZ_BLOB_TRIGGERS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `BLOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `QRTZ_BLOB_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of QRTZ_BLOB_TRIGGERS
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_CALENDARS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_CALENDARS`;
CREATE TABLE `QRTZ_CALENDARS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `CALENDAR_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `CALENDAR_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of QRTZ_CALENDARS
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_CRON_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_CRON_TRIGGERS`;
CREATE TABLE `QRTZ_CRON_TRIGGERS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `CRON_EXPRESSION` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TIME_ZONE_ID` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `QRTZ_CRON_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of QRTZ_CRON_TRIGGERS
-- ----------------------------
INSERT INTO `QRTZ_CRON_TRIGGERS` VALUES ('SanaScheduler', 'Trigger_device_deviceDataSave', '174851145', '0/20 * * * * ?', 'Asia/Shanghai');
INSERT INTO `QRTZ_CRON_TRIGGERS` VALUES ('SanaScheduler', 'Trigger_log_logsDataSave', '174851135', '0/20 * * * * ?', 'Asia/Shanghai');

-- ----------------------------
-- Table structure for QRTZ_FIRED_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_FIRED_TRIGGERS`;
CREATE TABLE `QRTZ_FIRED_TRIGGERS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `ENTRY_ID` varchar(95) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `INSTANCE_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `FIRED_TIME` bigint NOT NULL,
  `SCHED_TIME` bigint NOT NULL,
  `PRIORITY` int NOT NULL,
  `STATE` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `ENTRY_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of QRTZ_FIRED_TRIGGERS
-- ----------------------------
INSERT INTO `QRTZ_FIRED_TRIGGERS` VALUES ('SanaScheduler', 'NON_CLUSTERED1758676855687', 'Trigger_device_deviceDataSave', '174851145', 'NON_CLUSTERED', 1758680202240, 1758680220000, 5, 'ACQUIRED', NULL, NULL, '0', '0');

-- ----------------------------
-- Table structure for QRTZ_JOB_DETAILS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_JOB_DETAILS`;
CREATE TABLE `QRTZ_JOB_DETAILS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `DESCRIPTION` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `IS_DURABLE` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `IS_NONCONCURRENT` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `IS_UPDATE_DATA` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `JOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of QRTZ_JOB_DETAILS
-- ----------------------------
INSERT INTO `QRTZ_JOB_DETAILS` VALUES ('SanaScheduler', 'deviceDataSave', '174851145', NULL, 'com.sana.system.job.DeviceDataJob', '0', '1', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787000737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F40000000000010770800000010000000007800);
INSERT INTO `QRTZ_JOB_DETAILS` VALUES ('SanaScheduler', 'logsDataSave', '174851135', NULL, 'com.sana.system.job.LogOperateJob', '0', '1', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787000737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F40000000000010770800000010000000007800);

-- ----------------------------
-- Table structure for QRTZ_LOCKS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_LOCKS`;
CREATE TABLE `QRTZ_LOCKS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `LOCK_NAME` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `LOCK_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of QRTZ_LOCKS
-- ----------------------------
INSERT INTO `QRTZ_LOCKS` VALUES ('SanaScheduler', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for QRTZ_PAUSED_TRIGGER_GRPS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_PAUSED_TRIGGER_GRPS`;
CREATE TABLE `QRTZ_PAUSED_TRIGGER_GRPS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of QRTZ_PAUSED_TRIGGER_GRPS
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_SCHEDULER_STATE
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_SCHEDULER_STATE`;
CREATE TABLE `QRTZ_SCHEDULER_STATE`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `INSTANCE_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `LAST_CHECKIN_TIME` bigint NOT NULL,
  `CHECKIN_INTERVAL` bigint NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `INSTANCE_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of QRTZ_SCHEDULER_STATE
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_SIMPLE_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_SIMPLE_TRIGGERS`;
CREATE TABLE `QRTZ_SIMPLE_TRIGGERS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `REPEAT_COUNT` bigint NOT NULL,
  `REPEAT_INTERVAL` bigint NOT NULL,
  `TIMES_TRIGGERED` bigint NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `QRTZ_SIMPLE_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of QRTZ_SIMPLE_TRIGGERS
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_SIMPROP_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_SIMPROP_TRIGGERS`;
CREATE TABLE `QRTZ_SIMPROP_TRIGGERS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `STR_PROP_1` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `STR_PROP_2` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `STR_PROP_3` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `INT_PROP_1` int NULL DEFAULT NULL,
  `INT_PROP_2` int NULL DEFAULT NULL,
  `LONG_PROP_1` bigint NULL DEFAULT NULL,
  `LONG_PROP_2` bigint NULL DEFAULT NULL,
  `DEC_PROP_1` decimal(13, 4) NULL DEFAULT NULL,
  `DEC_PROP_2` decimal(13, 4) NULL DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `QRTZ_SIMPROP_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of QRTZ_SIMPROP_TRIGGERS
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_TRIGGERS`;
CREATE TABLE `QRTZ_TRIGGERS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `DESCRIPTION` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint NULL DEFAULT NULL,
  `PREV_FIRE_TIME` bigint NULL DEFAULT NULL,
  `PRIORITY` int NULL DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_TYPE` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `START_TIME` bigint NOT NULL,
  `END_TIME` bigint NULL DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `MISFIRE_INSTR` smallint NULL DEFAULT NULL,
  `JOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `SCHED_NAME`(`SCHED_NAME` ASC, `JOB_NAME` ASC, `JOB_GROUP` ASC) USING BTREE,
  CONSTRAINT `QRTZ_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `QRTZ_JOB_DETAILS` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of QRTZ_TRIGGERS
-- ----------------------------
INSERT INTO `QRTZ_TRIGGERS` VALUES ('SanaScheduler', 'Trigger_device_deviceDataSave', '174851145', 'deviceDataSave', '174851145', NULL, 1758680220000, 1758680200000, 5, 'ACQUIRED', 'CRON', 1758101465000, 0, NULL, 0, '');
INSERT INTO `QRTZ_TRIGGERS` VALUES ('SanaScheduler', 'Trigger_log_logsDataSave', '174851135', 'logsDataSave', '174851135', NULL, 1758680220000, 1758680200000, 5, 'WAITING', 'CRON', 1758182276000, 0, NULL, 0, '');

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `dic` bigint NOT NULL COMMENT '字典类型ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '字典名字',
  `key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '字典值',
  `label_class` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标签样式',
  `yx` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '是否有效',
  `sort` int NULL DEFAULT NULL COMMENT '排序',
  `deleted` tinyint NULL DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updater` bigint NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `creator_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人，格式：[姓名:账号]',
  `updater_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人，格式：[姓名:账号]',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 48 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '字典子项数据' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------
INSERT INTO `sys_dict_data` VALUES (1, 1, '停用', '0', 'danger', '1', 1, 0, 1, '2024-01-10 06:13:37', 1, '2024-07-05 21:00:39', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` VALUES (2, 1, '正常', '1', 'primary', '1', 0, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` VALUES (3, 2, '男', '0', 'primary', '1', 0, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` VALUES (4, 2, '女', '1', 'success', '1', 1, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` VALUES (5, 2, '未知', '2', 'warning', '1', 2, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` VALUES (6, 3, '正常', '1', 'primary', '1', 0, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` VALUES (7, 3, '停用', '0', 'danger', '1', 1, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` VALUES (8, 4, '全部数据', '0', '', '1', 0, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` VALUES (9, 4, '本机构及子机构数据', '1', '', '1', 0, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` VALUES (10, 4, '本机构数据', '2', '', '1', 0, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` VALUES (11, 4, '本人数据', '3', '', '1', 0, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` VALUES (12, 4, '自定义数据', '4', '', '1', 0, 1, 1, '2024-01-10 06:13:37', 1, '2024-07-07 22:18:02', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` VALUES (13, 5, '禁用', '0', 'danger', '1', 1, 1, 1, '2024-01-10 06:13:37', 1, '2024-04-26 13:58:57', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` VALUES (14, 5, '启用', '1', 'primary', '1', 0, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` VALUES (15, 6, '失败', '0', 'danger', '1', 1, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` VALUES (16, 6, '成功', '1', 'primary', '1', 0, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` VALUES (17, 7, '登录成功', '0', 'primary', '1', 0, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` VALUES (18, 7, '退出成功', '1', 'warning', '1', 1, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` VALUES (19, 7, '验证码错误', '2', 'danger', '1', 2, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` VALUES (20, 7, '账号密码错误', '3', 'danger', '1', 3, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` VALUES (21, 8, '否', '0', 'primary', '1', 1, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` VALUES (22, 8, '是', '1', 'danger', '1', 0, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` VALUES (23, 9, '是', '1', 'danger', '1', 1, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` VALUES (24, 9, '否', '0', 'primary', '1', 0, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` VALUES (25, 10, '其它', '0', 'info', '1', 10, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` VALUES (26, 10, '查询', '1', 'primary', '1', 0, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` VALUES (27, 10, '新增', '2', 'success', '1', 1, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` VALUES (28, 10, '修改', '3', 'warning', '1', 2, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` VALUES (29, 10, '删除', '4', 'danger', '1', 3, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` VALUES (30, 10, '导出', '5', 'info', '1', 4, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` VALUES (31, 10, '导入', '6', 'info', '1', 5, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` VALUES (36, 12, '默认', 'default', '', '1', 0, 1, 1, '2024-01-10 06:15:04', 1, '2025-09-02 15:31:20', '[超级管理员:admin]', '[admin:+1]');
INSERT INTO `sys_dict_data` VALUES (37, 12, '系统', 'system', '', '1', 1, 1, 1, '2024-01-10 06:15:04', 1, '2025-09-02 15:33:23', '[超级管理员:admin]', '[admin:+1]');
INSERT INTO `sys_dict_data` VALUES (38, 13, '暂停', '0', 'danger', '1', 0, 0, 1, '2024-01-10 06:15:04', 1, '2024-01-10 06:15:04', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` VALUES (39, 13, '正常', '1', 'primary', '1', 1, 0, 1, '2024-01-10 06:15:04', 1, '2024-01-10 06:15:04', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` VALUES (45, 13, '其他2', '3', NULL, '0', NULL, 1, 1, '2024-04-26 17:38:20', 1, '2024-04-26 17:40:18', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` VALUES (46, 13, '1', '1', NULL, '1', 4, 1, 1, '2024-04-26 17:47:55', 1, '2024-04-28 10:38:24', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` VALUES (47, 1714118012723, '开始', '1', NULL, '1', 1, 0, 1, '2025-09-24 09:22:38', NULL, NULL, '[admin:+1]', NULL);

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '字典类型',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '字典名称',
  `deleted` tinyint NULL DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updater` bigint NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `creator_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人，格式：[姓名:账号]',
  `updater_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人，格式：[姓名:账号]',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1714118012724 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '字典类型数据' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
INSERT INTO `sys_dict_type` VALUES (1, 'post_status', '请求状态', 0, 1, '2024-01-10 06:13:37', 1, '2024-04-21 16:08:57', NULL, '[超级管理员:admin]');
INSERT INTO `sys_dict_type` VALUES (2, 'user_gender', '性别', 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (3, 'user_status', '用户状态', 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (4, 'role_data_scope', '数据范围', 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (5, 'enable_disable', '启用状态', 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (6, 'success_fail', '是否成功', 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (7, 'login_operation', '操作信息', 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (8, 'params_type', '系统参数', 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (9, 'user_super_admin', '用户是否是超管', 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (10, 'log_operate_type', '操作类型', 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (11, 'sms_platform', '平台类型', 1, 1, '2024-01-10 06:14:40', 1, '2024-01-10 06:14:40', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (12, 'schedule_group', '任务组名', 1, 1, '2024-01-10 06:15:04', 1, '2024-01-10 06:15:04', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (13, 'schedule_status', '状态', 0, 1, '2024-01-10 06:15:04', 1, '2024-01-10 06:15:04', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (1714118012721, 'test_cache', '测试', 1, 1, '2025-09-23 08:41:58', NULL, NULL, '[admin:+1]', NULL);
INSERT INTO `sys_dict_type` VALUES (1714118012722, 'test', '测试', 1, 1, '2025-09-23 11:01:45', NULL, NULL, '[admin:+1]', NULL);
INSERT INTO `sys_dict_type` VALUES (1714118012723, 'test', '测试', 0, 1, '2025-09-23 11:05:34', NULL, NULL, '[admin:+1]', NULL);

-- ----------------------------
-- Table structure for sys_log_operate
-- ----------------------------
DROP TABLE IF EXISTS `sys_log_operate`;
CREATE TABLE `sys_log_operate`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `module` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '模块名',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '操作名',
  `req_uri` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '请求URI',
  `req_method` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '请求方法',
  `req_params` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '请求参数',
  `ip` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '操作IP',
  `user_agent` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'User Agent',
  `operate_type` tinyint NULL DEFAULT NULL COMMENT '操作类型',
  `duration` int NOT NULL COMMENT '执行时长',
  `status` tinyint NULL DEFAULT 1 COMMENT '操作状态  0：失败   1：成功',
  `user_id` bigint NULL DEFAULT NULL COMMENT '用户ID',
  `real_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '操作人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3000 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '操作日志' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_log_operate
-- ----------------------------
INSERT INTO `sys_log_operate` VALUES (2864, '用户管理', '分页', '/sana/sys/user/page', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"name\":null,\"groupId\":null}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-17 09:29:59');
INSERT INTO `sys_log_operate` VALUES (2865, '用户管理', '分页', '/sana/sys/user/page', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"name\":null,\"groupId\":null}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-17 09:29:59');
INSERT INTO `sys_log_operate` VALUES (2866, '机构管理', '列表', '/sana/sys/org/list', 'GET', '[{\"name\":null}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-17 09:29:59');
INSERT INTO `sys_log_operate` VALUES (2867, '用户管理', '分页', '/sana/sys/user/page', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"name\":null,\"groupId\":null}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-17 09:29:59');
INSERT INTO `sys_log_operate` VALUES (2868, '角色管理', '分页', '/sana/sys/role/page', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"name\":null}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-17 09:29:59');
INSERT INTO `sys_log_operate` VALUES (2869, '机构管理', '列表', '/sana/sys/org/list', 'GET', '[{\"name\":null}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-17 09:29:59');
INSERT INTO `sys_log_operate` VALUES (2870, '字典类型', '字典列表', '/sana/sys/dict/list', 'GET', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-17 09:29:59');
INSERT INTO `sys_log_operate` VALUES (2871, '字典数据', '分页', '/sana/sys/dic/page', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"code\":null}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-17 09:29:59');
INSERT INTO `sys_log_operate` VALUES (2872, '认证管理', '账号密码登录', '/sana/sys/auth/login', 'POST', '[{\"username\":\"admin\",\"password\":\"admin123456\",\"key\":\"cc0faa63-1f6d-4048-b3f6-50910574d0dc715298300\",\"captcha\":\"nf3cu\"}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-18 03:28:18');
INSERT INTO `sys_log_operate` VALUES (2873, '菜单管理', '菜单导航', '/sana/sys/menus/nav', 'GET', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-18 03:28:18');
INSERT INTO `sys_log_operate` VALUES (2874, '菜单管理', '菜单列表', '/sana/sys/menus/list', 'GET', '[null]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-18 03:28:59');
INSERT INTO `sys_log_operate` VALUES (2875, '字典类型', '字典列表', '/sana/sys/dict/list', 'GET', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-18 03:28:59');
INSERT INTO `sys_log_operate` VALUES (2876, '字典数据', '分页', '/sana/sys/dic/page', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"code\":null}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-18 03:28:59');
INSERT INTO `sys_log_operate` VALUES (2877, '机构管理', '列表', '/sana/sys/org/list', 'GET', '[{\"name\":null}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-18 03:28:59');
INSERT INTO `sys_log_operate` VALUES (2878, '角色管理', '分页', '/sana/sys/role/page', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"name\":null}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-18 03:28:59');
INSERT INTO `sys_log_operate` VALUES (2879, '用户管理', '分页', '/sana/sys/user/page', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"name\":null,\"groupId\":null}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-18 03:28:59');
INSERT INTO `sys_log_operate` VALUES (2880, '机构管理', '列表', '/sana/sys/org/list', 'GET', '[{\"name\":null}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-18 03:28:59');
INSERT INTO `sys_log_operate` VALUES (2881, '操作日志', '系统操作日志分页', '/sana/sys/log/operate/sysPage', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"operateType\":11,\"startTime\":null,\"endTime\":null}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-18 08:00:39');
INSERT INTO `sys_log_operate` VALUES (2882, '操作日志', '系统操作日志分页', '/sana/sys/log/operate/sysPage', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"operateType\":2,\"startTime\":null,\"endTime\":null}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-18 08:00:59');
INSERT INTO `sys_log_operate` VALUES (2883, '操作日志', '系统操作日志分页', '/sana/sys/log/operate/sysPage', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"operateType\":11,\"startTime\":null,\"endTime\":null}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-18 08:00:59');
INSERT INTO `sys_log_operate` VALUES (2884, '操作日志', '系统操作日志分页', '/sana/sys/log/operate/sysPage', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"operateType\":11,\"startTime\":null,\"endTime\":null}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-18 08:47:59');
INSERT INTO `sys_log_operate` VALUES (2885, '操作日志', '系统操作日志分页', '/sana/sys/log/operate/sysPage', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"operateType\":11,\"startTime\":null,\"endTime\":null}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-18 09:33:39');
INSERT INTO `sys_log_operate` VALUES (2886, '操作日志', '系统操作日志分页', '/sana/sys/log/operate/sysPage', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"operateType\":2,\"startTime\":null,\"endTime\":null}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-18 09:33:59');
INSERT INTO `sys_log_operate` VALUES (2887, '操作日志', '系统操作日志分页', '/sana/sys/log/operate/sysPage', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"operateType\":4,\"startTime\":null,\"endTime\":null}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-18 09:33:59');
INSERT INTO `sys_log_operate` VALUES (2888, '操作日志', '系统操作日志分页', '/sana/sys/log/operate/sysPage', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"operateType\":3,\"startTime\":null,\"endTime\":null}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-18 09:33:59');
INSERT INTO `sys_log_operate` VALUES (2889, '操作日志', '系统操作日志分页', '/sana/sys/log/operate/sysPage', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"operateType\":1,\"startTime\":null,\"endTime\":null}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-18 09:33:59');
INSERT INTO `sys_log_operate` VALUES (2890, '操作日志', '系统操作日志分页', '/sana/sys/log/operate/sysPage', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"operateType\":11,\"startTime\":null,\"endTime\":null}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-18 09:36:59');
INSERT INTO `sys_log_operate` VALUES (2891, '操作日志', '系统操作日志分页', '/sana/sys/log/operate/sysPage', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"operateType\":11,\"startTime\":null,\"endTime\":null}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-18 09:42:19');
INSERT INTO `sys_log_operate` VALUES (2892, '操作日志', '系统操作日志分页', '/sana/sys/log/operate/sysPage', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"operateType\":11,\"startTime\":null,\"endTime\":null}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-18 09:43:39');
INSERT INTO `sys_log_operate` VALUES (2893, '操作日志', '系统操作日志分页', '/sana/sys/log/operate/sysPage', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"operateType\":11,\"startTime\":null,\"endTime\":null}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-18 09:47:43');
INSERT INTO `sys_log_operate` VALUES (2894, '操作日志', '系统操作日志分页', '/sana/sys/log/operate/sysPage', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"operateType\":11,\"startTime\":null,\"endTime\":null}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-18 09:49:21');
INSERT INTO `sys_log_operate` VALUES (2895, '操作日志', '系统操作日志分页', '/sana/sys/log/operate/sysPage', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"operateType\":11,\"startTime\":null,\"endTime\":null}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-18 09:50:21');
INSERT INTO `sys_log_operate` VALUES (2896, '操作日志', '系统操作日志分页', '/sana/sys/log/operate/sysPage', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"operateType\":11,\"startTime\":null,\"endTime\":null}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-18 09:50:21');
INSERT INTO `sys_log_operate` VALUES (2897, '操作日志', '系统操作日志分页', '/sana/sys/log/operate/sysPage', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"operateType\":11,\"startTime\":null,\"endTime\":null}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-18 09:51:41');
INSERT INTO `sys_log_operate` VALUES (2898, '操作日志', '系统操作日志分页', '/sana/sys/log/operate/sysPage', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"operateType\":11,\"startTime\":null,\"endTime\":null}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-18 09:51:41');
INSERT INTO `sys_log_operate` VALUES (2899, '操作日志', '系统操作日志分页', '/sana/sys/log/operate/sysPage', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"operateType\":2,\"startTime\":null,\"endTime\":null}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-18 09:52:01');
INSERT INTO `sys_log_operate` VALUES (2900, '操作日志', '系统操作日志分页', '/sana/sys/log/operate/sysPage', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"operateType\":4,\"startTime\":null,\"endTime\":null}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-18 09:52:01');
INSERT INTO `sys_log_operate` VALUES (2901, '操作日志', '系统操作日志分页', '/sana/sys/log/operate/sysPage', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"operateType\":3,\"startTime\":null,\"endTime\":null}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-18 09:52:01');
INSERT INTO `sys_log_operate` VALUES (2902, '操作日志', '系统操作日志分页', '/sana/sys/log/operate/sysPage', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"operateType\":1,\"startTime\":null,\"endTime\":null}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-18 09:52:01');
INSERT INTO `sys_log_operate` VALUES (2903, '认证管理', '账号密码登录', '/sana/sys/auth/login', 'POST', '[{\"username\":\"admin\",\"password\":\"admin123456\",\"key\":\"82989411-c982-4622-841d-5ac514186509956436000\",\"captcha\":\"mu2sh\"}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-22 08:41:41');
INSERT INTO `sys_log_operate` VALUES (2904, '菜单管理', '菜单导航', '/sana/sys/menus/nav', 'GET', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-22 08:41:41');
INSERT INTO `sys_log_operate` VALUES (2905, '操作日志', '数据存储定时任务查询', '/sana/sys/log/operate/getJobData', 'GET', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-22 08:41:41');
INSERT INTO `sys_log_operate` VALUES (2906, '操作日志', '系统操作日志分页', '/sana/sys/log/operate/sysPage', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"operateType\":11,\"startTime\":null,\"endTime\":null}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-22 08:41:41');
INSERT INTO `sys_log_operate` VALUES (2907, '字典类型', '字典列表', '/sana/sys/dict/list', 'GET', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-22 08:42:01');
INSERT INTO `sys_log_operate` VALUES (2908, '字典数据', '分页', '/sana/sys/dic/page', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"code\":null}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-22 08:42:01');
INSERT INTO `sys_log_operate` VALUES (2909, '操作日志', '系统操作日志分页', '/sana/sys/log/operate/sysPage', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"operateType\":11,\"startTime\":null,\"endTime\":null}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-22 08:42:01');
INSERT INTO `sys_log_operate` VALUES (2910, '字典类型', '字典列表', '/sana/sys/dict/list', 'GET', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-22 08:42:21');
INSERT INTO `sys_log_operate` VALUES (2911, '字典数据', '分页', '/sana/sys/dic/page', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"code\":null}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-22 08:42:21');
INSERT INTO `sys_log_operate` VALUES (2912, '字典数据', '分页', '/sana/sys/dic/page', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"code\":null}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-22 08:49:44');
INSERT INTO `sys_log_operate` VALUES (2913, '操作日志', '系统操作日志分页', '/sana/sys/log/operate/sysPage', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"operateType\":11,\"startTime\":null,\"endTime\":null}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-22 08:50:36');
INSERT INTO `sys_log_operate` VALUES (2914, '字典类型', '字典列表', '/sana/sys/dict/list', 'GET', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-22 08:50:36');
INSERT INTO `sys_log_operate` VALUES (2915, '操作日志', '系统操作日志分页', '/sana/sys/log/operate/sysPage', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"operateType\":11,\"startTime\":null,\"endTime\":null}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-22 08:57:44');
INSERT INTO `sys_log_operate` VALUES (2916, '字典类型', '字典列表', '/sana/sys/dict/list', 'GET', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-22 08:58:03');
INSERT INTO `sys_log_operate` VALUES (2917, '字典数据', '分页', '/sana/sys/dic/page', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"code\":null}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-22 08:58:03');
INSERT INTO `sys_log_operate` VALUES (2918, '字典类型', '字典列表', '/sana/sys/dict/list', 'GET', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-22 09:03:00');
INSERT INTO `sys_log_operate` VALUES (2919, '字典数据', '分页', '/sana/sys/dic/page', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"code\":\"post_status\"}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-22 09:03:21');
INSERT INTO `sys_log_operate` VALUES (2920, '字典类型', '字典列表', '/sana/sys/dict/list', 'GET', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-22 09:07:01');
INSERT INTO `sys_log_operate` VALUES (2921, '字典数据', '分页', '/sana/sys/dic/page', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"code\":null}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-22 09:07:01');
INSERT INTO `sys_log_operate` VALUES (2922, '字典类型', '字典列表', '/sana/sys/dict/list', 'GET', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-22 09:12:24');
INSERT INTO `sys_log_operate` VALUES (2923, '字典数据', '分页', '/sana/sys/dic/page', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"code\":\"post_status\"}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-22 09:12:24');
INSERT INTO `sys_log_operate` VALUES (2924, '字典类型', '字典列表', '/sana/sys/dict/list', 'GET', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-22 09:12:24');
INSERT INTO `sys_log_operate` VALUES (2925, '字典数据', '分页', '/sana/sys/dic/page', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"code\":\"post_status\"}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-22 09:12:24');
INSERT INTO `sys_log_operate` VALUES (2926, '字典类型', '字典列表', '/sana/sys/dict/list', 'GET', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-22 09:16:20');
INSERT INTO `sys_log_operate` VALUES (2927, '字典数据', '分页', '/sana/sys/dic/page', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"code\":\"post_status\"}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-22 09:16:20');
INSERT INTO `sys_log_operate` VALUES (2928, '字典类型', '字典列表', '/sana/sys/dict/list', 'GET', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-23 00:41:38');
INSERT INTO `sys_log_operate` VALUES (2929, '字典数据', '分页', '/sana/sys/dic/page', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"code\":\"post_status\"}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-23 00:41:38');
INSERT INTO `sys_log_operate` VALUES (2930, '字典类型', '字典列表', '/sana/sys/dict/list', 'GET', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-23 00:41:58');
INSERT INTO `sys_log_operate` VALUES (2931, '字典类型', '新增', '/sana/sys/dict/save', 'POST', '[{\"id\":null,\"code\":\"test_cache\",\"name\":\"测试\",\"sort\":null,\"createTime\":null,\"updateTime\":null}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 2, 0, NULL, 1, '超级管理员', '2025-09-23 00:41:58');
INSERT INTO `sys_log_operate` VALUES (2932, '字典数据', '分页', '/sana/sys/dic/page', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"code\":\"test_cache\"}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-23 00:41:58');
INSERT INTO `sys_log_operate` VALUES (2933, '字典类型', '字典列表', '/sana/sys/dict/list', 'GET', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-23 00:49:58');
INSERT INTO `sys_log_operate` VALUES (2934, '字典数据', '分页', '/sana/sys/dic/page', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"code\":\"post_status\"}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-23 00:49:58');
INSERT INTO `sys_log_operate` VALUES (2935, '字典数据', '分页', '/sana/sys/dic/page', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"code\":\"test_cache\"}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-23 00:49:58');
INSERT INTO `sys_log_operate` VALUES (2936, '字典类型', '字典列表', '/sana/sys/dict/list', 'GET', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-23 00:50:48');
INSERT INTO `sys_log_operate` VALUES (2937, '字典数据', '分页', '/sana/sys/dic/page', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"code\":\"post_status\"}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-23 00:50:48');
INSERT INTO `sys_log_operate` VALUES (2938, '字典类型', '字典列表', '/sana/sys/dict/list', 'GET', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-23 00:50:48');
INSERT INTO `sys_log_operate` VALUES (2939, '字典数据', '分页', '/sana/sys/dic/page', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"code\":\"post_status\"}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-23 00:50:48');
INSERT INTO `sys_log_operate` VALUES (2940, '字典数据', '分页', '/sana/sys/dic/page', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"code\":\"test_cache\"}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-23 00:50:48');
INSERT INTO `sys_log_operate` VALUES (2941, '字典类型', '字典列表', '/sana/sys/dict/list', 'GET', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-23 00:51:58');
INSERT INTO `sys_log_operate` VALUES (2942, '字典数据', '分页', '/sana/sys/dic/page', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"code\":\"post_status\"}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-23 00:51:58');
INSERT INTO `sys_log_operate` VALUES (2943, '字典类型', '字典列表', '/sana/sys/dict/list', 'GET', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-23 00:52:53');
INSERT INTO `sys_log_operate` VALUES (2944, '字典数据', '分页', '/sana/sys/dic/page', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"code\":\"post_status\"}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-23 00:52:53');
INSERT INTO `sys_log_operate` VALUES (2945, '字典数据', '分页', '/sana/sys/dic/page', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"code\":\"test_cache\"}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-23 00:52:53');
INSERT INTO `sys_log_operate` VALUES (2946, '字典类型', '字典列表', '/sana/sys/dict/list', 'GET', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-23 00:53:44');
INSERT INTO `sys_log_operate` VALUES (2947, '字典数据', '分页', '/sana/sys/dic/page', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"code\":\"post_status\"}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-23 00:53:44');
INSERT INTO `sys_log_operate` VALUES (2948, '字典类型', '字典列表', '/sana/sys/dict/list', 'GET', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-23 01:00:58');
INSERT INTO `sys_log_operate` VALUES (2949, '字典数据', '分页', '/sana/sys/dic/page', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"code\":null}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-23 01:00:58');
INSERT INTO `sys_log_operate` VALUES (2950, '字典类型', '字典列表', '/sana/sys/dict/list', 'GET', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-23 01:10:18');
INSERT INTO `sys_log_operate` VALUES (2951, '字典数据', '分页', '/sana/sys/dic/page', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"code\":null}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-23 01:10:38');
INSERT INTO `sys_log_operate` VALUES (2952, '字典类型', '字典列表', '/sana/sys/dict/list', 'GET', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-23 01:12:58');
INSERT INTO `sys_log_operate` VALUES (2953, '字典数据', '分页', '/sana/sys/dic/page', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"code\":null}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-23 01:12:58');
INSERT INTO `sys_log_operate` VALUES (2954, '字典类型', '字典列表', '/sana/sys/dict/list', 'GET', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-23 01:20:18');
INSERT INTO `sys_log_operate` VALUES (2955, '字典数据', '分页', '/sana/sys/dic/page', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"code\":null}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-23 01:20:18');
INSERT INTO `sys_log_operate` VALUES (2956, '字典类型', '字典列表', '/sana/sys/dict/list', 'GET', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-23 01:23:38');
INSERT INTO `sys_log_operate` VALUES (2957, '字典数据', '分页', '/sana/sys/dic/page', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"code\":\"post_status\"}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-23 01:23:38');
INSERT INTO `sys_log_operate` VALUES (2958, '菜单管理', '菜单列表', '/sana/sys/menus/list', 'GET', '[null]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-23 01:23:38');
INSERT INTO `sys_log_operate` VALUES (2959, '机构管理', '列表', '/sana/sys/org/list', 'GET', '[{\"name\":null}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-23 01:23:38');
INSERT INTO `sys_log_operate` VALUES (2960, '角色管理', '分页', '/sana/sys/role/page', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"name\":null}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-23 01:23:38');
INSERT INTO `sys_log_operate` VALUES (2961, '用户管理', '分页', '/sana/sys/user/page', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"name\":null,\"groupId\":null}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-23 01:23:38');
INSERT INTO `sys_log_operate` VALUES (2962, '机构管理', '列表', '/sana/sys/org/list', 'GET', '[{\"name\":null}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-23 01:23:38');
INSERT INTO `sys_log_operate` VALUES (2963, '字典类型', '字典列表', '/sana/sys/dict/list', 'GET', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-23 01:26:38');
INSERT INTO `sys_log_operate` VALUES (2964, '字典数据', '分页', '/sana/sys/dic/page', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"code\":\"post_status\"}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-23 01:26:38');
INSERT INTO `sys_log_operate` VALUES (2965, '字典类型', '删除', '/sana/sys/dict/deletDicType', 'GET', '[1714118012721]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 4, 0, NULL, 1, '超级管理员', '2025-09-23 01:26:38');
INSERT INTO `sys_log_operate` VALUES (2966, '字典类型', '字典列表', '/sana/sys/dict/list', 'GET', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-23 03:01:38');
INSERT INTO `sys_log_operate` VALUES (2967, '字典数据', '分页', '/sana/sys/dic/page', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"code\":\"post_status\"}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-23 03:01:38');
INSERT INTO `sys_log_operate` VALUES (2968, '字典类型', '字典列表', '/sana/sys/dict/list', 'GET', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-23 03:01:38');
INSERT INTO `sys_log_operate` VALUES (2969, '字典类型', '新增', '/sana/sys/dict/save', 'POST', '[{\"id\":null,\"code\":\"test\",\"name\":\"测试\",\"createTime\":null,\"updateTime\":null}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 2, 0, NULL, 1, '超级管理员', '2025-09-23 03:01:58');
INSERT INTO `sys_log_operate` VALUES (2970, '字典数据', '分页', '/sana/sys/dic/page', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"code\":\"test\"}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-23 03:01:58');
INSERT INTO `sys_log_operate` VALUES (2971, '字典类型', '字典列表', '/sana/sys/dict/list', 'GET', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-23 03:05:18');
INSERT INTO `sys_log_operate` VALUES (2972, '字典数据', '分页', '/sana/sys/dic/page', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"code\":\"post_status\"}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-23 03:05:18');
INSERT INTO `sys_log_operate` VALUES (2973, '字典类型', '删除', '/sana/sys/dict/deletDicType', 'GET', '[1714118012722]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 4, 0, NULL, 1, '超级管理员', '2025-09-23 03:05:18');
INSERT INTO `sys_log_operate` VALUES (2974, '字典类型', '字典列表', '/sana/sys/dict/list', 'GET', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-23 03:05:38');
INSERT INTO `sys_log_operate` VALUES (2975, '字典类型', '新增', '/sana/sys/dict/save', 'POST', '[{\"id\":null,\"code\":\"test\",\"name\":\"测试\",\"createTime\":null,\"updateTime\":null}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 2, 0, NULL, 1, '超级管理员', '2025-09-23 03:05:38');
INSERT INTO `sys_log_operate` VALUES (2976, '字典数据', '分页', '/sana/sys/dic/page', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"code\":\"test\"}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-23 03:05:38');
INSERT INTO `sys_log_operate` VALUES (2977, '字典类型', '字典列表', '/sana/sys/dict/list', 'GET', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-23 03:21:38');
INSERT INTO `sys_log_operate` VALUES (2978, '字典类型', '字典列表', '/sana/sys/dict/list', 'GET', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-23 03:26:18');
INSERT INTO `sys_log_operate` VALUES (2979, '字典类型', '字典列表', '/sana/sys/dict/list', 'GET', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-23 05:51:39');
INSERT INTO `sys_log_operate` VALUES (2980, '字典数据', '分页', '/sana/sys/dic/page', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"code\":\"post_status\"}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-23 05:51:39');
INSERT INTO `sys_log_operate` VALUES (2981, '字典数据', '分页', '/sana/sys/dic/page', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"code\":\"test\"}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-23 05:51:39');
INSERT INTO `sys_log_operate` VALUES (2982, '字典类型', '字典列表', '/sana/sys/dict/list', 'GET', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-23 05:51:39');
INSERT INTO `sys_log_operate` VALUES (2983, '字典类型', '字典列表', '/sana/sys/dict/list', 'GET', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-23 06:42:19');
INSERT INTO `sys_log_operate` VALUES (2984, '字典数据', '分页', '/sana/sys/dic/page', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"code\":\"post_status\"}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-23 06:42:19');
INSERT INTO `sys_log_operate` VALUES (2985, '字典数据', '分页', '/sana/sys/dic/page', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"code\":\"test\"}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-23 06:42:19');
INSERT INTO `sys_log_operate` VALUES (2986, '字典类型', '字典列表', '/sana/sys/dict/list', 'GET', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-23 06:42:38');
INSERT INTO `sys_log_operate` VALUES (2987, '字典类型', '字典列表', '/sana/sys/dict/list', 'GET', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-23 06:55:59');
INSERT INTO `sys_log_operate` VALUES (2988, '字典数据', '分页', '/sana/sys/dic/page', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"code\":\"post_status\"}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-23 06:55:59');
INSERT INTO `sys_log_operate` VALUES (2989, '字典数据', '分页', '/sana/sys/dic/page', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"code\":\"test\"}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-23 06:55:59');
INSERT INTO `sys_log_operate` VALUES (2990, '字典类型', '字典列表', '/sana/sys/dict/list', 'GET', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-23 06:55:59');
INSERT INTO `sys_log_operate` VALUES (2991, '字典类型', '字典列表', '/sana/sys/dict/list', 'GET', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-23 07:18:38');
INSERT INTO `sys_log_operate` VALUES (2992, '字典数据', '分页', '/sana/sys/dic/page', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"code\":\"post_status\"}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-23 07:18:38');
INSERT INTO `sys_log_operate` VALUES (2993, '认证管理', '账号密码登录', '/sana/sys/auth/login', 'POST', '[{\"username\":\"admin\",\"password\":\"admin123456\",\"key\":\"5741bee5-99b7-45b0-aec8-4ac451780b9b181718000\",\"captcha\":\"km7sa\"}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-24 01:22:19');
INSERT INTO `sys_log_operate` VALUES (2994, '菜单管理', '菜单导航', '/sana/sys/menus/nav', 'GET', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-24 01:22:19');
INSERT INTO `sys_log_operate` VALUES (2995, '字典类型', '字典列表', '/sana/sys/dict/list', 'GET', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-24 01:22:39');
INSERT INTO `sys_log_operate` VALUES (2996, '字典数据', '分页', '/sana/sys/dic/page', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"code\":\"post_status\"}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-24 01:22:39');
INSERT INTO `sys_log_operate` VALUES (2997, '字典数据', '分页', '/sana/sys/dic/page', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"code\":\"test\"}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-24 01:22:39');
INSERT INTO `sys_log_operate` VALUES (2998, '字典类型', '字典列表', '/sana/sys/dict/list', 'GET', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-09-24 01:22:39');
INSERT INTO `sys_log_operate` VALUES (2999, '字典数据', '新增', '/sana/sys/dic/saveDicData', 'POST', '[{\"id\":null,\"dic\":1714118012723,\"name\":\"开始\",\"key\":\"1\",\"yx\":\"1\",\"sort\":1,\"createTime\":null,\"updateTime\":null}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 2, 0, NULL, 1, '超级管理员', '2025-09-24 01:22:39');

-- ----------------------------
-- Table structure for sys_menus
-- ----------------------------
DROP TABLE IF EXISTS `sys_menus`;
CREATE TABLE `sys_menus`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `pid` bigint NULL DEFAULT NULL COMMENT '上级ID',
  `name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单英文名称',
  `path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单页面地址',
  `component` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '内容',
  `redirect` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '重定向地址',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '权限标识',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单请求地址,可不填',
  `sort` int NULL DEFAULT NULL COMMENT '排序',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建者',
  `updater_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人，格式：[姓名:账号]',
  `deleted` tinyint NULL DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '显示名称。展示在菜单，标签和面包屑等中',
  `hidden` tinyint NULL DEFAULT NULL COMMENT '是否隐藏菜单，大部分用在无需显示在左侧菜单中的页面，比如详情页',
  `affix` tinyint NULL DEFAULT NULL COMMENT '是否固定，类似首页控制台在标签中是没有关闭按钮的',
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '显示图标，建立2级菜单都设置图标，否则菜单折叠都将显示空白',
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '类型：菜单，Iframe，外链，按钮',
  `hiddenBreadcrumb` tinyint NULL DEFAULT NULL COMMENT '是否隐藏面包屑',
  `active` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '左侧菜单的路由地址活动状态，比如打开详情页需要列表页的菜单活动状态',
  `color` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '颜色值',
  `fullpage` tinyint NULL DEFAULT NULL COMMENT '是否整页打开路由（脱离框架系），例如：fullpage: true',
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '静态路由时，所能访问路由的角色，例如：role: [\"SA\"]',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updater` bigint NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `creator_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人，格式：[姓名:账号]',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 271 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '菜单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_menus
-- ----------------------------
INSERT INTO `sys_menus` VALUES (75, NULL, 'home', '/home', 'home', NULL, NULL, NULL, 1, 1, '[超级管理员:admin]', 0, '首页', NULL, NULL, 'el-icon-home-filled', NULL, NULL, NULL, NULL, NULL, NULL, '2024-04-03 17:30:09', 1, '2024-05-09 10:04:17', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (76, 75, 'dashboard', '/dashboard', 'home', NULL, NULL, NULL, 2, 1, '[超级管理员:admin]', 0, '控制台', NULL, NULL, 'el-icon-menu', NULL, NULL, NULL, NULL, NULL, NULL, '2024-04-03 17:30:09', 1, '2024-05-10 23:26:08', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (77, 75, 'userCenter', '/usercenter', 'userCenter', NULL, NULL, NULL, 3, 1, '[超级管理员:admin]', 0, '帐号信息', NULL, NULL, 'el-icon-avatar', NULL, NULL, NULL, NULL, NULL, NULL, '2024-04-03 17:30:09', 1, '2024-05-09 10:09:43', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (78, NULL, 'setting', '/setting', 'setting', NULL, NULL, NULL, 2, 1, '[超级管理员:admin]', 0, '系统设置', NULL, NULL, 'el-icon-setting', NULL, NULL, NULL, NULL, NULL, NULL, '2024-04-03 17:08:42', 1, '2024-05-09 10:03:51', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (79, 78, 'logs', '/setting/logs', 'setting/logs', NULL, NULL, NULL, 7, 1, '[超级管理员:admin]', 0, '日志信息', NULL, NULL, 'el-icon-user', NULL, NULL, NULL, NULL, NULL, NULL, '2024-04-03 17:30:09', 1, '2025-05-27 15:24:33', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (80, 78, 'user', '/setting/user', 'setting/user', NULL, NULL, NULL, 1, 1, '[超级管理员:admin]', 0, '用户管理', NULL, NULL, 'el-icon-user', NULL, NULL, NULL, NULL, NULL, NULL, '2024-04-03 17:30:09', 1, '2024-05-09 10:04:54', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (81, 78, 'role', '/setting/role', 'setting/role', NULL, NULL, NULL, 3, 1, '[超级管理员:admin]', 0, '角色管理', NULL, NULL, 'el-icon-operation', NULL, NULL, NULL, NULL, NULL, NULL, '2024-04-03 17:30:09', 1, '2024-05-09 10:06:57', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (82, 78, 'dept', '/setting/dept', 'setting/dept', NULL, NULL, NULL, 4, 1, '[超级管理员:admin]', 0, '部门管理', NULL, NULL, 'sc-icon-organization', NULL, NULL, NULL, NULL, NULL, NULL, '2024-04-03 17:30:09', 1, '2024-05-09 10:08:11', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (83, 78, 'dic', '/setting/dic', 'setting/dic', NULL, NULL, NULL, 5, 1, '[超级管理员:admin]', 0, '字典管理', NULL, NULL, 'el-icon-cellphone', NULL, NULL, NULL, NULL, NULL, NULL, '2024-04-03 17:30:09', 1, '2024-05-09 10:08:28', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (85, 78, 'settingMenu', '/setting/menu', 'setting/menu', NULL, NULL, NULL, 6, 1, '[超级管理员:admin]', 0, '菜单管理', NULL, NULL, 'el-icon-menu', NULL, NULL, NULL, NULL, NULL, NULL, '2024-04-03 17:30:09', 1, '2024-05-09 10:08:40', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (105, 78, 'system', '/setting/system', 'setting/system', NULL, NULL, NULL, 8, 1, '[超级管理员:admin]', 0, '日志存储设置', NULL, NULL, 'el-icon-set-up', NULL, NULL, NULL, NULL, NULL, NULL, '2025-07-06 13:51:18', 1, '2025-07-06 13:53:20', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (200, 85, 'menus.save', '', '', NULL, 'sys:menus:save', 'api/sys/menus/save', NULL, 1, '[超级管理员:admin]', 0, '新增', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-07 17:26:27', 1, '2024-05-07 17:26:58', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (201, 85, 'menus.update', '', '', NULL, 'sys:menus:update', 'api/sys/menus/update', NULL, 1, '[超级管理员:admin]', 0, '修改', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-07 17:27:35', 1, '2024-05-07 17:33:13', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (202, 85, 'menus.delete', '', '', NULL, 'sys:menus:delete', 'api/sys/menus/delete', NULL, 1, '[超级管理员:admin]', 0, '删除', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-07 17:33:36', 1, '2024-05-07 17:35:35', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (203, 85, 'menus.list', '', '', NULL, 'sys:menus:list', 'api/sys/menus/list', NULL, 1, '[超级管理员:admin]', 0, '查看', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-07 17:36:00', 1, '2024-05-07 17:42:25', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (205, 83, 'dict.save', '', '', NULL, 'sys:dict:save', 'api/sys/dict/save', NULL, 1, '[超级管理员:admin]', 0, '字典类型新增', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 17:01:06', 1, '2024-05-08 17:57:05', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (206, 83, 'dict.update', '', '', NULL, 'sys:dict:update', 'api/sys/dict/update', NULL, 1, '[超级管理员:admin]', 0, '字典类型修改', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 17:56:12', 1, '2024-05-08 17:59:49', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (207, 83, 'dict.delete', '', '', NULL, 'sys:dict:delete', 'api/sys/dict/delete', NULL, 1, '[超级管理员:admin]', 0, '字典类型删除', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 17:57:46', 1, '2024-05-08 17:59:54', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (208, 83, 'dict.list', '', '', NULL, 'sys:dict:list', 'api/sys/dict/list', NULL, 1, '[超级管理员:admin]', 0, '字典类型列表', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 17:58:35', 1, '2024-05-08 18:00:00', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (209, 82, 'org.save', '', '', NULL, 'sys:org:save', 'api/sys/org/save', NULL, 1, '[超级管理员:admin]', 0, '保存', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 17:01:06', 1, '2024-05-08 17:57:05', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (210, 82, 'org.update', '', '', NULL, 'sys:org:update', 'api/sys/org/update', NULL, 1, '[超级管理员:admin]', 0, '更新', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 17:01:06', 1, '2024-05-08 17:57:05', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (211, 82, 'org.delete', '', '', NULL, 'sys:org:delete', 'api/sys/org/delete', NULL, 1, '[超级管理员:admin]', 0, '删除', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 17:01:06', 1, '2024-05-08 17:57:05', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (212, 82, 'org.list', '', '', NULL, 'sys:org:list', 'api/sys/org/list', NULL, 1, '[超级管理员:admin]', 0, '列表', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 17:01:06', 1, '2024-05-08 17:57:05', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (213, 81, 'role.save', '', '', NULL, 'sys:role:save', 'api/sys/role/save', NULL, 1, '[超级管理员:admin]', 0, '新增', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 20:28:01', 1, '2024-05-08 20:28:55', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (214, 81, 'role.update', '', '', NULL, 'sys:role:update', 'api/sys/role/update', NULL, 1, '[超级管理员:admin]', 0, '修改', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 17:01:06', 1, '2024-05-08 17:57:05', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (215, 81, 'role.delete', '', '', NULL, 'sys:role:delete', 'api/sys/role/delete', NULL, 1, '[超级管理员:admin]', 0, '删除', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 17:01:06', 1, '2024-05-08 17:57:05', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (216, 81, 'role.page', '', '', NULL, 'sys:role:page', 'api/sys/role/page', NULL, 1, '[超级管理员:admin]', 0, '列表', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 17:01:06', 1, '2024-05-08 17:57:05', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (217, 80, 'user.save', '', '', NULL, 'sys:user:save', 'api/sys/user/page', NULL, 1, '[超级管理员:admin]', 0, '保存用户', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 17:01:06', 1, '2025-05-28 15:36:36', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (218, 81, 'role.roleLinkMenus', '', '', NULL, 'sys:role:roleLinkMenus', 'api/sys/role/roleLinkMenus', NULL, 1, '[超级管理员:admin]', 0, '权限设置', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 20:28:01', 1, '2024-05-08 20:28:55', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (219, 80, 'user.save', '', '', NULL, 'sys:user:save', 'api/sys/user/save', NULL, 1, '[超级管理员:admin]', 0, '新增用户', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 20:28:01', 1, '2024-05-08 20:28:55', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (220, 80, 'user.update', '', '', NULL, 'sys:user:update', 'api/sys/user/update', NULL, 1, '[超级管理员:admin]', 0, '修改用户', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 20:28:01', 1, '2024-05-08 20:28:55', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (221, 80, 'user.delete', '', '', NULL, 'sys:user:delete', 'api/sys/user/delete', NULL, 1, '[超级管理员:admin]', 0, '删除用户', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 20:28:01', 1, '2024-05-08 20:28:55', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (222, 80, 'user.resetPassword', '', '', NULL, 'sys:user:password', 'api/sys/user/resetPassword', NULL, 1, '[超级管理员:admin]', 0, '重置密码', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 20:28:01', 1, '2025-05-27 15:36:17', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (223, 80, 'user.page', '', '', NULL, 'sys:user:page', 'api/sys/user/page', NULL, 1, '[超级管理员:admin]', 0, '用户列表', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 20:28:01', 1, '2025-05-27 15:35:05', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (224, 83, 'dict.page', '', '', NULL, 'sys:dic:page', 'api/sys/dic/page', NULL, 1, '[超级管理员:admin]', 0, '字典子项列表', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 17:58:35', 1, '2024-05-08 18:00:00', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (225, 83, 'dict.save', '', '', NULL, 'sys:dic:save', 'api/sys/dic/save', NULL, 1, '[超级管理员:admin]', 0, '字典子项新增', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 17:58:35', 1, '2024-05-08 18:00:00', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (226, 83, 'dict.update', '', '', NULL, 'sys:dic:update', 'api/sys/dic/update', NULL, 1, '[超级管理员:admin]', 0, '字典子项修改', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 17:58:35', 1, '2024-05-08 18:00:00', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (227, 83, 'dict.delete', '', '', NULL, 'sys:dic:delete', 'api/sys/dic/delete', NULL, 1, '[超级管理员:admin]', 0, '字典子项删除', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 17:58:35', 1, '2024-05-08 18:00:00', '[超级管理员:admin]');

-- ----------------------------
-- Table structure for sys_org
-- ----------------------------
DROP TABLE IF EXISTS `sys_org`;
CREATE TABLE `sys_org`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `pid` bigint NULL DEFAULT NULL COMMENT '上级ID',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '机构名称',
  `sort` int NULL DEFAULT NULL COMMENT '排序',
  `status` tinyint NULL DEFAULT NULL COMMENT '1是0否',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `deleted` tinyint NULL DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updater` bigint NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `creator_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人，格式：[姓名:账号]',
  `updater_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人，格式：[姓名:账号]',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_pid`(`pid` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '机构管理' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_org
-- ----------------------------
INSERT INTO `sys_org` VALUES (1, NULL, '鲶鱼科技技术公司', 1, 1, '备注备注', 0, 1, '2024-01-10 15:27:38', 1, '2024-04-04 15:27:50', NULL, NULL);
INSERT INTO `sys_org` VALUES (2, 1, '研发子公司', 1, 1, '备注备注', 0, 1, '2024-01-10 15:27:38', 1, '2024-04-04 15:27:50', NULL, NULL);
INSERT INTO `sys_org` VALUES (3, 1, '风投子公司', 2, 1, '备注备注', 0, 1, '2024-01-10 15:27:38', 1, '2024-09-30 17:03:33', NULL, '[超级管理员:admin]');
INSERT INTO `sys_org` VALUES (4, 1, '财务子公司', 3, 1, '备注备注', 0, 1, '2024-01-10 15:27:38', 1, '2024-04-04 15:27:50', NULL, NULL);
INSERT INTO `sys_org` VALUES (12, 2, '研发部', 1, 1, '备注备注', 0, 1, '2024-01-10 15:27:38', 1, '2024-04-04 15:27:50', NULL, NULL);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色名称',
  `role_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色编码',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `data_scope` tinyint NULL DEFAULT NULL COMMENT '数据范围  0：全部数据  1：所在部门及子级可见  2：所在部门可见 3：本人数据 ',
  `org_id` bigint NULL DEFAULT NULL COMMENT '机构ID',
  `sort` int NULL DEFAULT NULL COMMENT '排序',
  `status` bigint NULL DEFAULT 1 COMMENT '是否启用',
  `deleted` tinyint NULL DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updater` bigint NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `creator_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人，格式：[姓名:账号]',
  `updater_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人，格式：[姓名:账号]',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_org_id`(`org_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色管理' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '小研发', 'RD', '研发人员', 3, NULL, 7, 1, 1, 1, '2024-01-10 15:30:16', 1, '2024-07-25 17:38:21', NULL, '[超级管理员:admin]');
INSERT INTO `sys_role` VALUES (2, '技术主管', 'TS', '测试', 1, NULL, 2, 1, 1, 1, '2024-01-10 15:30:31', 1, '2024-07-25 17:38:21', NULL, '[超级管理员:admin]');
INSERT INTO `sys_role` VALUES (3, '研发组长', 'TL', '开发leader啊', 3, NULL, 3, 1, 1, 1, '2024-01-10 15:31:36', 1, '2024-07-25 17:38:21', NULL, '[超级管理员:admin]');
INSERT INTO `sys_role` VALUES (4, '小运维', 'OM', '运维人员', 3, NULL, 4, 1, 1, 1, '2024-01-10 15:32:03', 1, '2024-07-25 17:38:21', NULL, '[超级管理员:admin]');
INSERT INTO `sys_role` VALUES (5, '小产品', 'PM', '是个小产品啊', 3, NULL, 5, 1, 1, 1, '2024-01-10 15:32:51', 1, '2024-07-25 17:38:21', NULL, '[超级管理员:admin]');
INSERT INTO `sys_role` VALUES (7, '管理员', 'Administrators', '1', 0, NULL, 1, 1, 0, 1, '2024-05-11 13:29:30', 1, '2024-07-16 15:59:13', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_role` VALUES (10, '测试', 'cs', '', 3, NULL, 8, 1, 0, 1, '2024-07-16 17:34:26', 1, '2024-07-16 17:34:26', '[超级管理员:admin]', '[超级管理员:admin]');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_id` bigint NULL DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint NULL DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_role_id`(`role_id` ASC) USING BTREE,
  INDEX `idx_menu_id`(`menu_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1064 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色菜单关系' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (1014, 10, 75);
INSERT INTO `sys_role_menu` VALUES (1015, 10, 76);
INSERT INTO `sys_role_menu` VALUES (1016, 10, 77);
INSERT INTO `sys_role_menu` VALUES (1017, 10, 78);
INSERT INTO `sys_role_menu` VALUES (1018, 10, 79);
INSERT INTO `sys_role_menu` VALUES (1019, 10, 80);
INSERT INTO `sys_role_menu` VALUES (1020, 10, 81);
INSERT INTO `sys_role_menu` VALUES (1021, 10, 82);
INSERT INTO `sys_role_menu` VALUES (1022, 10, 83);
INSERT INTO `sys_role_menu` VALUES (1023, 10, 85);
INSERT INTO `sys_role_menu` VALUES (1024, 10, 86);
INSERT INTO `sys_role_menu` VALUES (1025, 10, 88);
INSERT INTO `sys_role_menu` VALUES (1026, 10, 89);
INSERT INTO `sys_role_menu` VALUES (1027, 10, 90);
INSERT INTO `sys_role_menu` VALUES (1028, 10, 92);
INSERT INTO `sys_role_menu` VALUES (1029, 10, 94);
INSERT INTO `sys_role_menu` VALUES (1030, 10, 95);
INSERT INTO `sys_role_menu` VALUES (1031, 10, 96);
INSERT INTO `sys_role_menu` VALUES (1032, 10, 98);
INSERT INTO `sys_role_menu` VALUES (1033, 10, 99);
INSERT INTO `sys_role_menu` VALUES (1034, 10, 100);
INSERT INTO `sys_role_menu` VALUES (1035, 10, 101);
INSERT INTO `sys_role_menu` VALUES (1036, 10, 102);
INSERT INTO `sys_role_menu` VALUES (1037, 10, 103);
INSERT INTO `sys_role_menu` VALUES (1038, 10, 104);
INSERT INTO `sys_role_menu` VALUES (1039, 10, 105);
INSERT INTO `sys_role_menu` VALUES (1040, 10, 203);
INSERT INTO `sys_role_menu` VALUES (1041, 10, 208);
INSERT INTO `sys_role_menu` VALUES (1042, 10, 212);
INSERT INTO `sys_role_menu` VALUES (1043, 10, 216);
INSERT INTO `sys_role_menu` VALUES (1044, 10, 217);
INSERT INTO `sys_role_menu` VALUES (1045, 10, 219);
INSERT INTO `sys_role_menu` VALUES (1046, 10, 223);
INSERT INTO `sys_role_menu` VALUES (1047, 10, 224);
INSERT INTO `sys_role_menu` VALUES (1048, 10, 231);
INSERT INTO `sys_role_menu` VALUES (1049, 10, 232);
INSERT INTO `sys_role_menu` VALUES (1050, 10, 236);
INSERT INTO `sys_role_menu` VALUES (1051, 10, 243);
INSERT INTO `sys_role_menu` VALUES (1052, 10, 246);
INSERT INTO `sys_role_menu` VALUES (1053, 10, 250);
INSERT INTO `sys_role_menu` VALUES (1054, 10, 254);
INSERT INTO `sys_role_menu` VALUES (1055, 10, 258);
INSERT INTO `sys_role_menu` VALUES (1056, 10, 261);
INSERT INTO `sys_role_menu` VALUES (1057, 10, 262);
INSERT INTO `sys_role_menu` VALUES (1058, 10, 264);
INSERT INTO `sys_role_menu` VALUES (1059, 10, 265);
INSERT INTO `sys_role_menu` VALUES (1060, 10, 266);
INSERT INTO `sys_role_menu` VALUES (1061, 10, 267);
INSERT INTO `sys_role_menu` VALUES (1062, 10, 268);
INSERT INTO `sys_role_menu` VALUES (1063, 10, 269);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `real_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '填写真实姓名，填写后不允许修改',
  `avatar` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像',
  `gender` tinyint NULL DEFAULT NULL COMMENT '性别   0：男   1：女   2：未知',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号',
  `signature` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '个人签名',
  `super_admin` tinyint NULL DEFAULT 0 COMMENT '超级管理员   0：否   1：是',
  `status` tinyint NULL DEFAULT 1 COMMENT '状态  0：停用   1：正常',
  `deleted` tinyint NULL DEFAULT 0 COMMENT '删除标识  0：正常   1：已删除',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updater` bigint NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `creator_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人，格式：[姓名:账号]',
  `updater_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人，格式：[姓名:账号]',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10007 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户管理' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '$2a$10$zFcjZlqGuJyglnToPdXJeuxKcEHN.ztB0gVmIT2BS1UBno1qIFyeK', '超级管理员', 'http://127.0.0.1:9000/dev/20250527/1748330038908_54870.jpg', 1, 'lyl@163.com', '13245677645', '三月来百草开 盈香满袖万物苏\n虫鸣和着欢笑 心事舒', 1, 1, 0, 10000, '2024-01-10 06:13:37', 1, '2025-05-27 15:14:33', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_user` VALUES (2, 'sana', '$2a$10$zFcjZlqGuJyglnToPdXJeuxKcEHN.ztB0gVmIT2BS1UBno1qIFyeK', 'sana0101', 'http://127.0.0.1:9000/dev/20250527/1748330038908_54853.jpg', 2, 'lyl@163.com', '13245677656', '牛马干活\\韭菜施肥\\蘸料上色', 0, 1, 0, 10000, '2024-07-22 09:49:43', 1, '2025-05-27 15:33:27', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_user` VALUES (10006, 'test', '$2a$10$zFcjZlqGuJyglnToPdXJeuxKcEHN.ztB0gVmIT2BS1UBno1qIFyeK', '测试用户1', 'http://127.0.0.1:9000/dev/20250527/1748330038908_54878.jpg', NULL, NULL, NULL, NULL, 0, 1, 1, 1, '2024-11-26 17:25:34', 1, '2025-05-27 15:33:31', '[超级管理员:admin]', '[超级管理员:admin]');

-- ----------------------------
-- Table structure for sys_user_org
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_org`;
CREATE TABLE `sys_user_org`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint NULL DEFAULT NULL COMMENT '用户ID',
  `org_id` bigint NULL DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_user_id`(`user_id` ASC) USING BTREE,
  INDEX `idx_post_id`(`org_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 72 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户组织关系' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_org
-- ----------------------------
INSERT INTO `sys_user_org` VALUES (67, 1, 1);
INSERT INTO `sys_user_org` VALUES (70, 2, 1);
INSERT INTO `sys_user_org` VALUES (71, 2, 2);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_id` bigint NULL DEFAULT NULL COMMENT '角色ID',
  `user_id` bigint NULL DEFAULT NULL COMMENT '用户ID',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_role_id`(`role_id` ASC) USING BTREE,
  INDEX `idx_user_id`(`user_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户角色关系' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (47, 7, 1);
INSERT INTO `sys_user_role` VALUES (49, 10, 2);

SET FOREIGN_KEY_CHECKS = 1;
