/*
 Navicat Premium Dump SQL

 Source Server Type    : MySQL
 Source Server Version : 80033 (8.0.33)

 Target Server Type    : MySQL
 Target Server Version : 80033 (8.0.33)
 File Encoding         : 65001

 Date: 16/10/2025 15:29:35
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
INSERT INTO `QRTZ_CRON_TRIGGERS` VALUES ('SanaScheduler', 'Trigger_device_deviceDataSave', '1748522', '0/20 * * * * ?', 'Asia/Shanghai');
INSERT INTO `QRTZ_CRON_TRIGGERS` VALUES ('SanaScheduler', 'Trigger_log_logsDataSave', '1748511', '0/20 * * * * ?', 'Asia/Shanghai');
INSERT INTO `QRTZ_CRON_TRIGGERS` VALUES ('SanaScheduler', 'Trigger_开始16', '1760404935751', '0 */5 * * * ?', 'Asia/Shanghai');

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
INSERT INTO `QRTZ_FIRED_TRIGGERS` VALUES ('SanaScheduler', 'NON_CLUSTERED1760599412669', 'Trigger_device_deviceDataSave', '1748522', 'NON_CLUSTERED', 1760599461862, 1760599480000, 5, 'ACQUIRED', NULL, NULL, '0', '0');

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
INSERT INTO `QRTZ_JOB_DETAILS` VALUES ('SanaScheduler', 'deviceDataSave', '1748522', NULL, 'com.sana.devices.job.DeviceDataJob', '0', '1', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787000737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F40000000000010770800000010000000007800);
INSERT INTO `QRTZ_JOB_DETAILS` VALUES ('SanaScheduler', 'logsDataSave', '1748511', NULL, 'com.sana.system.job.LogOperateJob', '0', '1', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787000737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F40000000000010770800000010000000007800);
INSERT INTO `QRTZ_JOB_DETAILS` VALUES ('SanaScheduler', '开始16', '1760404935751', NULL, 'com.sana.rules.executes.job.SchedulerJob', '0', '1', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787000737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F40000000000010770800000010000000007800);

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
INSERT INTO `QRTZ_TRIGGERS` VALUES ('SanaScheduler', 'Trigger_device_deviceDataSave', '1748522', 'deviceDataSave', '1748522', NULL, 1760599480000, 1760599460000, 5, 'ACQUIRED', 'CRON', 1760403978000, 0, NULL, 0, '');
INSERT INTO `QRTZ_TRIGGERS` VALUES ('SanaScheduler', 'Trigger_log_logsDataSave', '1748511', 'logsDataSave', '1748511', NULL, 1760599480000, 1760599460000, 5, 'WAITING', 'CRON', 1760403912000, 0, NULL, 0, '');
INSERT INTO `QRTZ_TRIGGERS` VALUES ('SanaScheduler', 'Trigger_开始16', '1760404935751', '开始16', '1760404935751', NULL, 1760599500000, 1760599416581, 5, 'WAITING', 'CRON', 1760404935000, 0, NULL, 0, '');

-- ----------------------------
-- Table structure for device_abutment
-- ----------------------------
DROP TABLE IF EXISTS `device_abutment`;
CREATE TABLE `device_abutment`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `agreement_id` bigint NULL DEFAULT NULL COMMENT 'mqtt协议表id',
  `product_id` bigint NULL DEFAULT NULL COMMENT '设备id（这里的用的是产品的id）',
  `device_type` int NULL DEFAULT NULL COMMENT '设备接入的方式，1直链设备，2边缘计算设备，3边缘计算子设备',
  `edge_id` bigint NULL DEFAULT NULL COMMENT '如果是边缘子设备，则这里就是关联的边缘计算产品实例的id；如果是边缘计算，这里就是边缘计算的id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 59 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '接入管理' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of device_abutment
-- ----------------------------
INSERT INTO `device_abutment` VALUES (52, 1, 12, 1, NULL);
INSERT INTO `device_abutment` VALUES (53, 1, 13, 1, NULL);
INSERT INTO `device_abutment` VALUES (54, NULL, 15, 1, NULL);
INSERT INTO `device_abutment` VALUES (56, 1, 16, 1, NULL);
INSERT INTO `device_abutment` VALUES (57, 1, 18, 1, NULL);
INSERT INTO `device_abutment` VALUES (58, 1, 20, 1, NULL);

-- ----------------------------
-- Table structure for device_control_data
-- ----------------------------
DROP TABLE IF EXISTS `device_control_data`;
CREATE TABLE `device_control_data`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `device_item_id` bigint NULL DEFAULT NULL COMMENT '设备id',
  `control_type` int NULL DEFAULT NULL COMMENT '1:控制功能/2：采集功能',
  `control_type_label` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '控制类型名称',
  `control_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '功能名称',
  `control_mode` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '所属模型',
  `control_data_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '数据类型名称',
  `control_data_type_label` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '数据类型',
  `control_defult_value` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '控制默认值',
  `deleted` tinyint NULL DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updater` bigint NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `creator_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人，格式：[姓名:账号]',
  `updater_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人，格式：[姓名:账号]',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 44 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '设备功能表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of device_control_data
-- ----------------------------
INSERT INTO `device_control_data` VALUES (27, 41, 1, '控制功能', '开启', 'switch', 'int', '整数', '1', 0, 1, '2025-06-18 14:45:51', NULL, NULL, '[超级管理员:admin]', NULL);
INSERT INTO `device_control_data` VALUES (28, 41, 1, '控制功能', '关闭', 'switch', 'int', '整数', '0', 0, 1, '2025-06-18 14:46:09', NULL, NULL, '[超级管理员:admin]', NULL);
INSERT INTO `device_control_data` VALUES (29, 42, 1, '控制功能', '开启', 'switch', 'int', '整数', '1', 0, 1, '2025-06-18 14:54:18', NULL, NULL, '[超级管理员:admin]', NULL);
INSERT INTO `device_control_data` VALUES (30, 42, 1, '控制功能', '关闭', 'switch', 'int', '整数', '0', 0, 1, '2025-06-18 14:54:29', NULL, NULL, '[超级管理员:admin]', NULL);
INSERT INTO `device_control_data` VALUES (31, 42, 2, '采集功能', '采集', 'gather', 'int', '整数', '1', 0, 1, '2025-06-18 14:54:47', NULL, NULL, '[超级管理员:admin]', NULL);
INSERT INTO `device_control_data` VALUES (32, 43, 1, '控制功能', '开启', 'switch', 'int', '整数', '1', 0, 1, '2025-06-18 15:05:00', NULL, NULL, '[超级管理员:admin]', NULL);
INSERT INTO `device_control_data` VALUES (33, 43, 1, '控制功能', '关闭', 'switch', 'int', '整数', '0', 0, 1, '2025-06-18 15:05:15', NULL, NULL, '[超级管理员:admin]', NULL);
INSERT INTO `device_control_data` VALUES (34, 43, 2, '采集功能', '采集', 'gather', 'int', '整数', '1', 0, 1, '2025-06-18 15:05:26', NULL, NULL, '[超级管理员:admin]', NULL);
INSERT INTO `device_control_data` VALUES (35, 44, 1, '控制功能', '开启', 'switch', 'int', '整数', '1', 0, 1, '2025-06-18 15:07:52', NULL, NULL, '[超级管理员:admin]', NULL);
INSERT INTO `device_control_data` VALUES (36, 44, 1, '控制功能', '关闭', 'switch', 'int', '整数', '0', 0, 1, '2025-06-18 15:08:01', NULL, NULL, '[超级管理员:admin]', NULL);
INSERT INTO `device_control_data` VALUES (37, 44, 1, '控制功能', '除湿', 'dehumidify', 'int', '整数', '1', 0, 1, '2025-06-18 15:08:17', NULL, NULL, '[超级管理员:admin]', NULL);
INSERT INTO `device_control_data` VALUES (38, 44, 1, '控制功能', '热风', 'hot', 'int', '整数', '1', 0, 1, '2025-06-18 15:08:36', NULL, NULL, '[超级管理员:admin]', NULL);
INSERT INTO `device_control_data` VALUES (39, 44, 1, '控制功能', '冷风', '冷风', 'int', '整数', '1', 0, 1, '2025-06-18 15:08:50', NULL, NULL, '[超级管理员:admin]', NULL);
INSERT INTO `device_control_data` VALUES (40, 45, 1, '控制功能', '开启', 'switch', 'int', '整数', '1', 0, 1, '2025-06-18 15:11:33', NULL, NULL, '[超级管理员:admin]', NULL);
INSERT INTO `device_control_data` VALUES (41, 45, 1, '控制功能', '关闭', 'switch', 'int', '整数', '0', 0, 1, '2025-06-18 15:11:43', NULL, NULL, '[超级管理员:admin]', NULL);
INSERT INTO `device_control_data` VALUES (42, 46, 1, '控制功能', '开启窗户', 'switch', 'int', '整数', '1', 0, 1, '2025-06-18 15:13:57', NULL, NULL, '[超级管理员:admin]', NULL);
INSERT INTO `device_control_data` VALUES (43, 46, 1, '控制功能', '关闭窗户', 'switch', 'int', '整数', '0', 0, 1, '2025-06-18 15:14:08', NULL, NULL, '[超级管理员:admin]', NULL);

-- ----------------------------
-- Table structure for device_edges_item
-- ----------------------------
DROP TABLE IF EXISTS `device_edges_item`;
CREATE TABLE `device_edges_item`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `edge_sn` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '边缘编码（非必填）',
  `edge_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '边缘名称',
  `edge_firmware_version` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '固件版本',
  `edge_secret_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '秘钥',
  `edge_secret_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '秘钥id',
  `edge_lon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '经度',
  `edge_lat` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '维度',
  `edge_product_type_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '所属产品类型id',
  `deleted` tinyint NULL DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updater` bigint NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `creator_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人，格式：[姓名:账号]',
  `updater_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人，格式：[姓名:账号]',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '边缘计算实体' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of device_edges_item
-- ----------------------------

-- ----------------------------
-- Table structure for device_group
-- ----------------------------
DROP TABLE IF EXISTS `device_group`;
CREATE TABLE `device_group`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '设备名称',
  `sort` int NULL DEFAULT NULL COMMENT '排序',
  `status` tinyint NULL DEFAULT NULL COMMENT '1是0否',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '分组说明',
  `deleted` tinyint NULL DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updater` bigint NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `creator_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人，格式：[姓名:账号]',
  `updater_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人，格式：[姓名:账号]',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '设备分组表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of device_group
-- ----------------------------
INSERT INTO `device_group` VALUES (7, '上班离家组', 1, 1, '上班，关窗户，关灯', 0, 1, '2025-06-18 15:20:37', 1, '2025-06-18 15:20:56', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `device_group` VALUES (8, '下班回家组', 1, 1, '下班，开窗户啊', 0, 1, '2025-06-18 15:21:14', NULL, NULL, '[超级管理员:admin]', NULL);

-- ----------------------------
-- Table structure for device_item
-- ----------------------------
DROP TABLE IF EXISTS `device_item`;
CREATE TABLE `device_item`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `imgs` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '设备图片',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '设备名称',
  `device_number` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '设备编码',
  `status` tinyint NULL DEFAULT NULL COMMENT '设备状态1正常是0离线',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '设备说明',
  `product_type_id` bigint NULL DEFAULT NULL COMMENT '所属上级产品类型id',
  `product_type_show` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '所属产品树结构id，一般用于回显',
  `deleted` tinyint NULL DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updater` bigint NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `creator_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人，格式：[姓名:账号]',
  `updater_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人，格式：[姓名:账号]',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 47 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '设备表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of device_item
-- ----------------------------
INSERT INTO `device_item` VALUES (41, 'http://8.139.4.114:9000/dev/20250618/1749801180997_52998.jpg', '客厅粉色的加湿器', 'sn9000000000001', 1, '放在客厅的，粉色的加湿器', 15, '14,15', 0, 1, '2025-06-18 14:44:43', 1, '2025-06-18 15:10:14', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `device_item` VALUES (42, 'http://8.139.4.114:9000/dev/20250618/1749801180997_53396.jpg', '粉色的室内空气检测器', 'sn666666666', 1, '品牌暂不好说', 12, '11,12', 0, 1, '2025-06-18 14:51:24', 1, '2025-06-18 15:10:03', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `device_item` VALUES (43, 'http://8.139.4.114:9000/dev/20250618/1749801180997_54215.jpg', '烤漆的室外空气检测器', 'sn3421513423', 0, '放在室外，烤漆的', 13, '11,13', 0, 1, '2025-06-18 15:04:09', 1, '2025-06-18 15:09:56', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `device_item` VALUES (44, 'http://8.139.4.114:9000/dev/20250618/1749801180997_54380.jpg', '客厅白色的立式空调', 'snlllllllll666', 1, '大白', 16, '14,16', 0, 1, '2025-06-18 15:07:08', 1, '2025-06-18 15:10:21', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `device_item` VALUES (45, 'http://8.139.4.114:9000/dev/20250618/1749801180997_54637.jpg', '阳台窗帘控制器', 'snyt', 1, '三层的窗帘哦，', 18, '17,18', 0, 1, '2025-06-18 15:11:14', NULL, NULL, '[超级管理员:admin]', NULL);
INSERT INTO `device_item` VALUES (46, 'http://8.139.4.114:9000/dev/20250618/1749801180997_54785.jpg', '客厅窗户', 'snctrollerWindows', 1, '我的窗户听我的', 20, '19,20', 0, 1, '2025-06-18 15:13:36', NULL, NULL, '[超级管理员:admin]', NULL);

-- ----------------------------
-- Table structure for device_item_group
-- ----------------------------
DROP TABLE IF EXISTS `device_item_group`;
CREATE TABLE `device_item_group`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `device_group_id` bigint NULL DEFAULT NULL COMMENT '设备分组表id',
  `device_item_id` bigint NULL DEFAULT NULL COMMENT '设备实例表id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 44 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '设备分组关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of device_item_group
-- ----------------------------
INSERT INTO `device_item_group` VALUES (40, 7, 46);
INSERT INTO `device_item_group` VALUES (41, 7, 45);
INSERT INTO `device_item_group` VALUES (42, 8, 45);
INSERT INTO `device_item_group` VALUES (43, 8, 46);

-- ----------------------------
-- Table structure for device_mode
-- ----------------------------
DROP TABLE IF EXISTS `device_mode`;
CREATE TABLE `device_mode`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `mode_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '属性名称',
  `mode_signs` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '属性标志',
  `mode_data_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '数据类型',
  `mode_data_source` tinyint NULL DEFAULT NULL COMMENT '数据来源',
  `mode_control_att` int NULL DEFAULT NULL COMMENT '冗余字段，预留给后面的功能',
  `mode_remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '补充说明',
  `device_item_id` bigint NULL DEFAULT NULL COMMENT '设备id',
  `defult_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '冗余字段，预留给后面的功能',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 386 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '设备物模型' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of device_mode
-- ----------------------------
INSERT INTO `device_mode` VALUES (357, '开关', 'switch', 'int', 1, 0, NULL, 41, NULL);
INSERT INTO `device_mode` VALUES (363, 'PM2.5', 'pm25', 'float', 1, 0, 'μg/m³', 42, NULL);
INSERT INTO `device_mode` VALUES (364, 'PM10', 'pm10', 'float', 1, 0, 'μg/m³', 42, NULL);
INSERT INTO `device_mode` VALUES (365, '温度', 'temperature', 'float', 1, 0, '°C', 42, NULL);
INSERT INTO `device_mode` VALUES (366, '湿度', 'humidity', 'float', 1, 0, '%rh', 42, NULL);
INSERT INTO `device_mode` VALUES (367, '开关', 'switch', 'int', 1, 0, NULL, 42, NULL);
INSERT INTO `device_mode` VALUES (368, '采集', 'gather', 'int', 0, NULL, NULL, 42, NULL);
INSERT INTO `device_mode` VALUES (374, 'PM2.5', 'pm25', 'float', 1, 0, 'μg/m³', 43, NULL);
INSERT INTO `device_mode` VALUES (375, 'PM10', 'pm10', 'float', 1, 0, 'μg/m³', 43, NULL);
INSERT INTO `device_mode` VALUES (376, '温度', 'temperature', 'float', 1, 0, '°C', 43, NULL);
INSERT INTO `device_mode` VALUES (377, '湿度', 'humidity', 'float', 1, 0, '%rh', 43, NULL);
INSERT INTO `device_mode` VALUES (378, '开关', 'switch', 'int', 1, 0, NULL, 43, NULL);
INSERT INTO `device_mode` VALUES (379, '采集', 'gather', 'int', 0, NULL, NULL, 43, NULL);
INSERT INTO `device_mode` VALUES (380, '开关', 'switch', 'int', 1, 0, NULL, 44, NULL);
INSERT INTO `device_mode` VALUES (381, '除湿', 'dehumidify', 'int', 1, 0, NULL, 44, NULL);
INSERT INTO `device_mode` VALUES (382, '热风', 'hot', 'int', 1, 0, NULL, 44, NULL);
INSERT INTO `device_mode` VALUES (383, '冷风', 'cold', 'int', 1, 0, NULL, 44, NULL);
INSERT INTO `device_mode` VALUES (384, '开关', 'switch', 'int', 1, 0, NULL, 45, NULL);
INSERT INTO `device_mode` VALUES (385, '开关', 'switch', 'int', 1, 0, NULL, 46, NULL);

-- ----------------------------
-- Table structure for device_product_mode
-- ----------------------------
DROP TABLE IF EXISTS `device_product_mode`;
CREATE TABLE `device_product_mode`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `mode_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '属性名称',
  `mode_signs` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '属性标志',
  `mode_data_type` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '数据类型',
  `mode_remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '补充说明',
  `product_type_id` bigint NULL DEFAULT NULL COMMENT '产品类型id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '产品物模型' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of device_product_mode
-- ----------------------------
INSERT INTO `device_product_mode` VALUES (13, 'PM2.5', 'pm25', 'float', 'μg/m³', 12);
INSERT INTO `device_product_mode` VALUES (14, 'PM10', 'pm10', 'float', 'μg/m³', 12);
INSERT INTO `device_product_mode` VALUES (15, '温度', 'temperature', 'float', '°C', 12);
INSERT INTO `device_product_mode` VALUES (16, '湿度', 'humidity', 'float', '%rh', 12);
INSERT INTO `device_product_mode` VALUES (17, '开关', 'switch', 'int', NULL, 12);
INSERT INTO `device_product_mode` VALUES (18, 'PM2.5', 'pm25', 'float', 'μg/m³', 13);
INSERT INTO `device_product_mode` VALUES (19, 'PM10', 'pm10', 'float', 'μg/m³', 13);
INSERT INTO `device_product_mode` VALUES (20, '温度', 'temperature', 'float', '°C', 13);
INSERT INTO `device_product_mode` VALUES (21, '湿度', 'humidity', 'float', '%rh', 13);
INSERT INTO `device_product_mode` VALUES (22, '开关', 'switch', 'int', NULL, 13);
INSERT INTO `device_product_mode` VALUES (23, '开关', 'switch', 'int', NULL, 15);
INSERT INTO `device_product_mode` VALUES (25, '开关', 'switch', 'int', NULL, 16);
INSERT INTO `device_product_mode` VALUES (26, '除湿', 'dehumidify', 'int', NULL, 16);
INSERT INTO `device_product_mode` VALUES (27, '热风', 'hot', 'int', NULL, 16);
INSERT INTO `device_product_mode` VALUES (28, '冷风', 'cold', 'int', NULL, 16);
INSERT INTO `device_product_mode` VALUES (29, '开关', 'switch', 'int', NULL, 18);
INSERT INTO `device_product_mode` VALUES (30, '开关', 'switch', 'int', NULL, 20);

-- ----------------------------
-- Table structure for device_product_type
-- ----------------------------
DROP TABLE IF EXISTS `device_product_type`;
CREATE TABLE `device_product_type`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `pid` bigint NULL DEFAULT NULL COMMENT '上级ID',
  `label` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '产品名称',
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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '产品管理' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of device_product_type
-- ----------------------------
INSERT INTO `device_product_type` VALUES (11, NULL, '空气检测', 1, NULL, '空气检测大类', 0, 1, '2025-06-18 14:00:42', 1, '2025-06-18 14:06:48', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `device_product_type` VALUES (12, 11, '室内空气检测器', 1, NULL, '空气检测下面的室内空气检测器', 0, 1, '2025-06-18 14:01:06', 1, '2025-06-18 14:06:27', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `device_product_type` VALUES (13, 11, '室外空气检测器', 2, NULL, '空气检测下面的室外空气检测器', 0, 1, '2025-06-18 14:01:21', 1, '2025-06-18 14:06:33', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `device_product_type` VALUES (14, NULL, '气体净化器', NULL, NULL, '这个是气体净化器大类', 0, 1, '2025-06-18 14:05:05', 1, '2025-06-18 14:07:32', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `device_product_type` VALUES (15, 14, '室内加湿器', NULL, NULL, '这是一个非常漂亮的加湿器哦！', 0, 1, '2025-06-18 14:11:11', NULL, NULL, '[超级管理员:admin]', NULL);
INSERT INTO `device_product_type` VALUES (16, 14, '室内空调', NULL, NULL, '室内台式大空调', 0, 1, '2025-06-18 14:12:46', 1, '2025-06-18 14:33:14', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `device_product_type` VALUES (17, NULL, '窗帘', NULL, NULL, '非常漂亮的一个窗帘', 0, 1, '2025-06-18 14:14:37', NULL, NULL, '[超级管理员:admin]', NULL);
INSERT INTO `device_product_type` VALUES (18, 17, '客厅窗帘控制器', NULL, NULL, '客厅紫色花纹的窗帘的控制器', 0, 1, '2025-06-18 14:15:33', 1, '2025-06-18 14:15:46', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `device_product_type` VALUES (19, NULL, '窗户', NULL, NULL, '玻璃窗户', 0, 1, '2025-06-18 14:38:49', NULL, NULL, '[超级管理员:admin]', NULL);
INSERT INTO `device_product_type` VALUES (20, 19, '客厅窗户', NULL, NULL, '客厅的窗户', 0, 1, '2025-06-18 14:39:13', NULL, NULL, '[超级管理员:admin]', NULL);

-- ----------------------------
-- Table structure for protocols_http
-- ----------------------------
DROP TABLE IF EXISTS `protocols_http`;
CREATE TABLE `protocols_http`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `http_name` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '名称',
  `http_method` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '请求方法（GET、POST、PUT、DELETE）',
  `http_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '所要请求的url地址',
  `http_content_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '请求类型application/json',
  `http_request_data` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '数据请求格式',
  `deleted` tinyint NULL DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updater` bigint NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `creator_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人，格式：[姓名:账号]',
  `updater_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人，格式：[姓名:账号]',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '协议-http' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of protocols_http
-- ----------------------------

-- ----------------------------
-- Table structure for protocols_kafka
-- ----------------------------
DROP TABLE IF EXISTS `protocols_kafka`;
CREATE TABLE `protocols_kafka`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `kafka_name` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '名称',
  `kafka_topic` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '请求方法（GET、POST、PUT、DELETE）',
  `kafka_ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '所要请求的url地址',
  `kafka_push_data` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '数据请求格式',
  `deleted` tinyint NULL DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updater` bigint NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `creator_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人，格式：[姓名:账号]',
  `updater_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人，格式：[姓名:账号]',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of protocols_kafka
-- ----------------------------

-- ----------------------------
-- Table structure for protocols_mqtt
-- ----------------------------
DROP TABLE IF EXISTS `protocols_mqtt`;
CREATE TABLE `protocols_mqtt`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'mqtt IP地址（所部署的服务器的ip）',
  `mqtt_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'mqtt名称',
  `mqtt_type` int NULL DEFAULT NULL COMMENT '1:本地mqtt，2：代理mqtt（emqx）',
  `tcp_port` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'tcp端口，默认：1883',
  `websocket_port` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'http、websocket 端口，默认：8083',
  `auth_enable` tinyint NULL DEFAULT NULL COMMENT 'mqtttcp是否开启认证1是0否',
  `username` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'mqtt 认证用户名',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'mqtt 认证密码',
  `http_enable` tinyint NULL DEFAULT NULL COMMENT 'http-api是否开启,1是0否',
  `http_basic_auth` tinyint NULL DEFAULT NULL COMMENT 'http-api如果开启，则设置该选项是否开启 http basic auth，默认： false（0）',
  `http_basic_username` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'http basic auth 用户名',
  `http_basic_password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'http basic auth 密码',
  `ssl_enabled` tinyint NULL DEFAULT NULL COMMENT '是否开启 ssl 认证,1是0否',
  `keystore_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '必须参数：ssl keystore 目录',
  `keystore_pass` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '必选参数：ssl keystore 密码',
  `client_auth` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '是否需要客户端认证（双向认证），默认：NONE（不需要），REQUIRE（需要）',
  `truststore_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '可选参数：ssl 双向认证 truststore 目录',
  `truststore_pass` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '可选参数：ssl 双向认证 truststore 密码',
  `mqtt_enabled` tinyint NULL DEFAULT NULL COMMENT 'mqtt是否启用1是0否',
  `link_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `deleted` tinyint NULL DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updater` bigint NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `creator_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人，格式：[姓名:账号]',
  `updater_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人，格式：[姓名:账号]',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '协议-mqtt' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of protocols_mqtt
-- ----------------------------
INSERT INTO `protocols_mqtt` VALUES (1, '0.0.0.0', 'Mqtt-broker(测试环境不开放端口)', 1, '1883', '8083', 1, 'root', 'MqttNB!%#...', 1, 1, 'root', 'MqttNB!%#...', 0, '', '', 'none', '', '', 1, '0', 0, 1, '2025-10-16 15:23:33', 1, '2025-10-16 15:23:33', '系统初始化', '系统初始化');
INSERT INTO `protocols_mqtt` VALUES (2, '12am.cn', '其他emqx代理', 2, '1884', '8084', 1, 'admin', 'admin1234!@#$', 0, NULL, '', '', 0, '', '', 'NONE', '', '', 0, '0', 1, 1, '2024-12-03 16:51:28', 1, '2024-09-04 18:21:41', '[超级管理员:admin]', '[超级管理员:admin]');

-- ----------------------------
-- Table structure for rules_action_instanc
-- ----------------------------
DROP TABLE IF EXISTS `rules_action_instanc`;
CREATE TABLE `rules_action_instanc`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `ac_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '动作编码',
  `ac_instancing` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '动作内容',
  `rule_id` bigint NULL DEFAULT NULL COMMENT '规则id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 253 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '规则引擎--动作内容实例化存储（一般使用是存在redis中）' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rules_action_instanc
-- ----------------------------
INSERT INTO `rules_action_instanc` VALUES (249, 'actionMap1760404925719', '{\"@type\":\"java.util.HashMap\",\"nodeName\":\"数据流转\",\"timeoutExecution\":1,\"seqExecute\":1,\"pushPlatformInformation\":[{\"@type\":\"com.alibaba.fastjson.JSONObject\",\"name\":\"Mqtt-broker(测试环境不开放端口)\",\"id\":1}],\"pushTopic\":\"1750232528537\",\"controlActionType\":3,\"userName\":\"[超级管理员:admin]\",\"userId\":1L,\"actionType\":1,\"controlType\":1,\"pushData\":\"温度大于35、湿度大于50，开启空调冷风，开启加湿器！\",\"deviceList\":[{\"@type\":\"com.alibaba.fastjson.JSONObject\",\"controlDeviceAbilityLabel\":[\"开启\"],\"controlType\":1,\"isSet\":false,\"name\":\"客厅粉色的加湿器\",\"controlDataTypeLabel\":[\"控制功能\"],\"controlAbility\":27,\"id\":41},{\"@type\":\"com.alibaba.fastjson.JSONObject\",\"controlDeviceAbilityLabel\":[\"冷风\"],\"controlType\":1,\"isSet\":false,\"name\":\"客厅白色的立式空调\",\"controlDataTypeLabel\":[\"控制功能\"],\"controlAbility\":39,\"id\":44}],\"sendType\":1,\"controlGroupId\":[]}', 15);
INSERT INTO `rules_action_instanc` VALUES (250, 'actionMap1760404925896', '{\"@type\":\"java.util.HashMap\",\"nodeName\":\"数据流转\",\"timeoutExecution\":1,\"seqExecute\":1,\"pushPlatformInformation\":[{\"@type\":\"com.alibaba.fastjson.JSONObject\",\"name\":\"Mqtt-broker(测试环境不开放端口)\",\"id\":1}],\"pushTopic\":\"1750232631469\",\"controlActionType\":3,\"userName\":\"[超级管理员:admin]\",\"userId\":1L,\"actionType\":1,\"controlType\":1,\"pushData\":\"温度小于35、湿度小于50，关闭空调冷风，关闭加湿器！\",\"deviceList\":[{\"@type\":\"com.alibaba.fastjson.JSONObject\",\"controlDeviceAbilityLabel\":[\"关闭\"],\"controlType\":1,\"isSet\":false,\"name\":\"客厅粉色的加湿器\",\"controlDataTypeLabel\":[\"控制功能\"],\"controlAbility\":28,\"id\":41},{\"@type\":\"com.alibaba.fastjson.JSONObject\",\"controlDeviceAbilityLabel\":[\"关闭\"],\"controlType\":1,\"isSet\":false,\"name\":\"客厅白色的立式空调\",\"controlDataTypeLabel\":[\"控制功能\"],\"controlAbility\":36,\"id\":44}],\"sendType\":2,\"controlGroupId\":[]}', 15);
INSERT INTO `rules_action_instanc` VALUES (251, 'actionMap1760404936947', '{\"@type\":\"java.util.HashMap\",\"nodeName\":\"数据流转\",\"timeoutExecution\":1,\"seqExecute\":1,\"pushPlatformInformation\":[{\"@type\":\"com.alibaba.fastjson.JSONObject\",\"name\":\"Mqtt-broker(测试环境不开放端口)\",\"id\":1}],\"pushTopic\":\"1750233172301\",\"controlActionType\":3,\"userName\":\"[超级管理员:admin]\",\"userId\":1L,\"actionType\":1,\"controlType\":1,\"pushData\":\"温度过高\",\"deviceList\":[{\"@type\":\"com.alibaba.fastjson.JSONObject\",\"controlDeviceAbilityLabel\":[\"关闭窗户\"],\"controlType\":1,\"isSet\":false,\"name\":\"客厅窗户\",\"controlDataTypeLabel\":[\"控制功能\"],\"controlAbility\":43,\"id\":46},{\"@type\":\"com.alibaba.fastjson.JSONObject\",\"controlDeviceAbilityLabel\":[\"关闭\"],\"controlType\":1,\"isSet\":false,\"name\":\"阳台窗帘控制器\",\"controlDataTypeLabel\":[\"控制功能\"],\"controlAbility\":41,\"id\":45}],\"sendType\":1,\"controlGroupId\":[]}', 16);
INSERT INTO `rules_action_instanc` VALUES (252, 'actionMap1760404937357', '{\"@type\":\"java.util.HashMap\",\"nodeName\":\"数据流转\",\"timeoutExecution\":1,\"seqExecute\":1,\"pushPlatformInformation\":[{\"@type\":\"com.alibaba.fastjson.JSONObject\",\"name\":\"Mqtt-broker(测试环境不开放端口)\",\"id\":1}],\"pushTopic\":\"1750233187251\",\"controlActionType\":3,\"userName\":\"[超级管理员:admin]\",\"userId\":1L,\"actionType\":1,\"controlType\":1,\"pushData\":\"温度降下来了\",\"deviceList\":[{\"@type\":\"com.alibaba.fastjson.JSONObject\",\"controlDeviceAbilityLabel\":[\"开启窗户\"],\"controlType\":1,\"isSet\":false,\"name\":\"客厅窗户\",\"controlDataTypeLabel\":[\"控制功能\"],\"controlAbility\":42,\"id\":46},{\"@type\":\"com.alibaba.fastjson.JSONObject\",\"controlDeviceAbilityLabel\":[\"开启\"],\"controlType\":1,\"isSet\":false,\"name\":\"阳台窗帘控制器\",\"controlDataTypeLabel\":[\"控制功能\"],\"controlAbility\":40,\"id\":45}],\"sendType\":2,\"controlGroupId\":[]}', 16);

-- ----------------------------
-- Table structure for rules_item
-- ----------------------------
DROP TABLE IF EXISTS `rules_item`;
CREATE TABLE `rules_item`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `as_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '规则脚本名称',
  `as_type` int NULL DEFAULT NULL COMMENT '1:脚本，2:表达式，3：图形化配置',
  `as_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '表达式内容',
  `as_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '脚本路径',
  `as_remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '规则说明',
  `as_enable` tinyint NULL DEFAULT 1 COMMENT '1是0否',
  `rule_type` int NULL DEFAULT NULL COMMENT '规则类型：4条件类型，1设备控制类型，2数据流转类型。冗余字段，后期增加功能',
  `deleted` tinyint NULL DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updater` bigint NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `creator_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人，格式：[姓名:账号]',
  `updater_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人，格式：[姓名:账号]',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '规则实例' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of rules_item
-- ----------------------------
INSERT INTO `rules_item` VALUES (15, '测试规则', 3, 'let m = seq.list();\r\nif ((deviceId == 42 && temperature > 35) && (deviceId == 42 && humidity > 50)) {\r\nlet hit = seq.map();\r\nseq.put(hit, \"actionMap\", \"actionMap1760404925719\");\r\nseq.put(hit, \"message\", \"命中条件：粉色的室内空气检测器 的 温度 > 35 与 粉色的室内空气检测器 的 湿度 > 50\");\r\nseq.add(m, hit);\r\n}\r\nif(deviceId == 42){\r\n    if(temperature < 35){\r\n    let hit = seq.map();\r\n    seq.put(hit, \"actionMap\", \"actionMap1760404925896\");\r\n    seq.put(hit, \"message\", \"命中条件：粉色的室内空气检测器 的 温度 < 35\");\r\n    seq.add(m, hit);\r\n    }\r\n}\r\nif(deviceId == 42){\r\n    if(humidity < 30){\r\n    let hit = seq.map();\r\n    seq.put(hit, \"actionMap\", \"actionMap1760404925896\");\r\n    seq.put(hit, \"message\", \"命中条件：粉色的室内空气检测器 的 湿度 < 30\");\r\n    seq.add(m, hit);\r\n    }\r\n}\r\nreturn m;\r\n', NULL, '当温度大于35，开启空调，开启窗帘，开启窗户！', 1, 4, 0, 1, '2025-06-18 15:37:41', 1, '2025-06-18 15:44:26', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `rules_item` VALUES (16, '定时测试规则', 3, 'let m = seq.list();\r\nif ((deviceId == 43 && temperature > 40) && (deviceId == 43 && temperature > 70)) {\r\nlet hit = seq.map();\r\nseq.put(hit, \"actionMap\", \"actionMap1760404936947\");\r\nseq.put(hit, \"message\", \"命中条件：烤漆的室外空气检测器 的 温度 > 40 与 烤漆的室外空气检测器 的 温度 > 70\");\r\nseq.add(m, hit);\r\n}\r\nif ((deviceId == 43 &&  temperature < 40) && (deviceId == 43 &&  humidity < 70)) {\r\nlet hit = seq.map();\r\nseq.put(hit, \"actionMap\", \"actionMap1760404937357\");\r\nseq.put(hit, \"message\", \"命中条件： 烤漆的室外空气检测器 的 温度 < 40 与  烤漆的室外空气检测器 的 湿度 < 70\");\r\nseq.add(m, hit);\r\n}\r\nreturn m;\r\n', NULL, '定时监测规则', 1, 4, 0, 1, '2025-06-18 15:45:50', 1, '2025-06-18 15:54:15', '[超级管理员:admin]', '[超级管理员:admin]');

-- ----------------------------
-- Table structure for rules_item_node
-- ----------------------------
DROP TABLE IF EXISTS `rules_item_node`;
CREATE TABLE `rules_item_node`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `rules_id` bigint NULL DEFAULT NULL COMMENT '所属规则实例id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '实例名称',
  `node_config` json NULL COMMENT '节点设置',
  `deleted` tinyint NULL DEFAULT 0 COMMENT '删除标识  0：正常   1：已删除',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updater` bigint NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `creator_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人，格式：[姓名:账号]',
  `updater_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人，格式：[姓名:账号]',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 43 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '规则节点信息' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of rules_item_node
-- ----------------------------
INSERT INTO `rules_item_node` VALUES (41, 15, '', '{\"cron\": \"\", \"type\": 0, \"setType\": 1, \"nodeName\": \"设备监听\", \"childNode\": {\"type\": 4, \"nodeName\": \"条件路由\", \"conditionNodes\": [{\"type\": 3, \"nodeName\": \"温度、湿度判断\", \"childNode\": {\"term\": 0, \"type\": 1, \"setType\": 1, \"nodeName\": \"设备控制\", \"termAuto\": false, \"termMode\": 1, \"childNode\": {\"type\": 2, \"nodeName\": \"数据流转\", \"pushData\": \"温度大于35、湿度大于50，开启空调冷风，开启加湿器！\", \"sendType\": 1, \"pushTopic\": \"1750232528537\", \"pushDataFormat\": true, \"pushPlatformInformation\": [{\"id\": 1, \"name\": \"Mqtt-broker(测试环境不开放端口)\"}]}, \"actionType\": 3, \"selectMode\": 1, \"examineMode\": 2, \"nodeGroupList\": [], \"nodeDeviceList\": [{\"id\": 41, \"name\": \"客厅粉色的加湿器\", \"isSet\": false, \"controlType\": 1, \"controlAbility\": 27, \"controlDataTypeLabel\": [\"控制功能\"], \"controlDeviceAbilityLabel\": [\"开启\"]}, {\"id\": 44, \"name\": \"客厅白色的立式空调\", \"isSet\": false, \"controlType\": 1, \"controlAbility\": 39, \"controlDataTypeLabel\": [\"控制功能\"], \"controlDeviceAbilityLabel\": [\"冷风\"]}]}, \"conditionList\": [{\"id\": 42, \"label\": \"粉色的室内空气检测器\", \"value\": \"35\", \"unicode\": \"1750232331955\", \"operator\": \"1\", \"modeSigns\": \"temperature\", \"modeSignsName\": \"温度\"}, {\"id\": 42, \"label\": \"粉色的室内空气检测器\", \"value\": \"50\", \"unicode\": \"1750232357619\", \"operator\": \"1\", \"modeSigns\": \"humidity\", \"modeSignsName\": \"湿度\"}], \"conditionMode\": 1, \"priorityLevel\": 1, \"nodeDeviceList\": []}, {\"type\": 3, \"nodeName\": \"条件2\", \"childNode\": {\"term\": 0, \"type\": 1, \"setType\": 1, \"nodeName\": \"设备控制\", \"termAuto\": false, \"termMode\": 1, \"childNode\": {\"type\": 2, \"nodeName\": \"数据流转\", \"pushData\": \"温度小于35、湿度小于50，关闭空调冷风，关闭加湿器！\", \"sendType\": 2, \"pushTopic\": \"1750232631469\", \"pushDataFormat\": true, \"pushPlatformInformation\": [{\"id\": 1, \"name\": \"Mqtt-broker(测试环境不开放端口)\"}]}, \"actionType\": 3, \"selectMode\": 1, \"examineMode\": 2, \"nodeGroupList\": [], \"nodeDeviceList\": [{\"id\": 41, \"name\": \"客厅粉色的加湿器\", \"isSet\": false, \"controlType\": 1, \"controlAbility\": 28, \"controlDataTypeLabel\": [\"控制功能\"], \"controlDeviceAbilityLabel\": [\"关闭\"]}, {\"id\": 44, \"name\": \"客厅白色的立式空调\", \"isSet\": false, \"controlType\": 1, \"controlAbility\": 36, \"controlDataTypeLabel\": [\"控制功能\"], \"controlDeviceAbilityLabel\": [\"关闭\"]}]}, \"conditionList\": [{\"id\": 42, \"label\": \"粉色的室内空气检测器\", \"value\": \"35\", \"unicode\": \"1750232391234\", \"operator\": \"2\", \"modeSigns\": \"temperature\", \"modeSignsName\": \"温度\"}, {\"id\": 42, \"label\": \"粉色的室内空气检测器\", \"value\": \"30\", \"unicode\": \"1750232417234\", \"operator\": \"2\", \"modeSigns\": \"humidity\", \"modeSignsName\": \"湿度\"}], \"conditionMode\": 2, \"priorityLevel\": 2, \"nodeDeviceList\": []}]}, \"nodeDeviceList\": [{\"id\": 42, \"name\": \"粉色的室内空气检测器\"}]}', 0, 1, '2025-06-18 15:44:25', 1, '2025-06-18 17:05:26', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `rules_item_node` VALUES (42, 16, '', '{\"cron\": \"0 */5 * * * ?\", \"type\": 0, \"setType\": 2, \"nodeName\": \"开始\", \"childNode\": {\"type\": 4, \"nodeName\": \"条件路由\", \"conditionNodes\": [{\"type\": 3, \"nodeName\": \"条件1\", \"childNode\": {\"term\": 0, \"type\": 1, \"setType\": 1, \"nodeName\": \"设备控制\", \"termAuto\": false, \"termMode\": 1, \"childNode\": {\"type\": 2, \"nodeName\": \"数据流转\", \"pushData\": \"温度过高\", \"sendType\": 1, \"pushTopic\": \"1750233172301\", \"pushDataFormat\": true, \"pushPlatformInformation\": [{\"id\": 1, \"name\": \"Mqtt-broker(测试环境不开放端口)\"}]}, \"actionType\": 3, \"selectMode\": 1, \"examineMode\": 2, \"nodeGroupList\": [], \"nodeDeviceList\": [{\"id\": 46, \"name\": \"客厅窗户\", \"isSet\": false, \"controlType\": 1, \"controlAbility\": 43, \"controlDataTypeLabel\": [\"控制功能\"], \"controlDeviceAbilityLabel\": [\"关闭窗户\"]}, {\"id\": 45, \"name\": \"阳台窗帘控制器\", \"isSet\": false, \"controlType\": 1, \"controlAbility\": 41, \"controlDataTypeLabel\": [\"控制功能\"], \"controlDeviceAbilityLabel\": [\"关闭\"]}]}, \"conditionList\": [{\"id\": 43, \"label\": \"烤漆的室外空气检测器\", \"value\": \"40\", \"unicode\": \"1750232933771\", \"operator\": \"1\", \"modeSigns\": \"temperature\", \"modeSignsName\": \"温度\"}, {\"id\": 43, \"label\": \"烤漆的室外空气检测器\", \"value\": \"70\", \"unicode\": \"1750232957528\", \"operator\": \"1\", \"modeSigns\": \"temperature\", \"modeSignsName\": \"温度\"}], \"conditionMode\": 1, \"priorityLevel\": 1, \"nodeDeviceList\": []}, {\"type\": 3, \"nodeName\": \"条件2\", \"childNode\": {\"term\": 0, \"type\": 1, \"setType\": 1, \"nodeName\": \"设备控制\", \"termAuto\": false, \"termMode\": 1, \"childNode\": {\"type\": 2, \"nodeName\": \"数据流转\", \"pushData\": \"温度降下来了\", \"sendType\": 2, \"pushTopic\": \"1750233187251\", \"pushDataFormat\": true, \"pushPlatformInformation\": [{\"id\": 1, \"name\": \"Mqtt-broker(测试环境不开放端口)\"}]}, \"actionType\": 3, \"selectMode\": 1, \"examineMode\": 2, \"nodeGroupList\": [], \"nodeDeviceList\": [{\"id\": 46, \"name\": \"客厅窗户\", \"isSet\": false, \"controlType\": 1, \"controlAbility\": 42, \"controlDataTypeLabel\": [\"控制功能\"], \"controlDeviceAbilityLabel\": [\"开启窗户\"]}, {\"id\": 45, \"name\": \"阳台窗帘控制器\", \"isSet\": false, \"controlType\": 1, \"controlAbility\": 40, \"controlDataTypeLabel\": [\"控制功能\"], \"controlDeviceAbilityLabel\": [\"开启\"]}]}, \"conditionList\": [{\"id\": 43, \"label\": \"烤漆的室外空气检测器\", \"value\": \"40\", \"unicode\": \"1750233008596\", \"operator\": \"2\", \"modeSigns\": \"temperature\", \"modeSignsName\": \"温度\"}, {\"id\": 43, \"label\": \"烤漆的室外空气检测器\", \"value\": \"70\", \"unicode\": \"1750233023687\", \"operator\": \"2\", \"modeSigns\": \"humidity\", \"modeSignsName\": \"湿度\"}], \"conditionMode\": 1, \"priorityLevel\": 2, \"nodeDeviceList\": []}]}, \"nodeDeviceList\": []}', 0, 1, '2025-06-18 15:54:14', 1, '2025-06-18 17:05:35', '[超级管理员:admin]', '[超级管理员:admin]');

-- ----------------------------
-- Table structure for rules_item_quratz
-- ----------------------------
DROP TABLE IF EXISTS `rules_item_quratz`;
CREATE TABLE `rules_item_quratz`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `rules_id` bigint NULL DEFAULT NULL COMMENT '所属规则实例id',
  `cron` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'cron表达式',
  `job_group` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '作业组',
  `trigger_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '触发名称',
  `trigger_group` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '触发作业组',
  `deleted` tinyint NULL DEFAULT 0 COMMENT '删除标识  0：正常   1：已删除',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updater` bigint NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `creator_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人，格式：[姓名:账号]',
  `updater_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人，格式：[姓名:账号]',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `unique_rules_id`(`rules_id` ASC, `trigger_name` ASC) USING BTREE COMMENT '每个规则只能有一个对应的关联关系'
) ENGINE = InnoDB AUTO_INCREMENT = 80 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '规则-任务调度关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rules_item_quratz
-- ----------------------------
INSERT INTO `rules_item_quratz` VALUES (79, 16, '0 */5 * * * ?', '1760404935751', 'Trigger_开始16', '1760404935751', 0, 1, '2025-10-14 09:22:16', NULL, NULL, '[admin:+1]', NULL);

-- ----------------------------
-- Table structure for rules_job_device_id
-- ----------------------------
DROP TABLE IF EXISTS `rules_job_device_id`;
CREATE TABLE `rules_job_device_id`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `device_id` bigint NULL DEFAULT NULL COMMENT '定时任务中的设备id',
  `rule_id` bigint NULL DEFAULT NULL COMMENT '规则id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 57 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '定时规则中每个设备对应的规则信息，用于规则执行的时候的检索' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rules_job_device_id
-- ----------------------------
INSERT INTO `rules_job_device_id` VALUES (55, 43, 16);
INSERT INTO `rules_job_device_id` VALUES (56, 43, 16);

-- ----------------------------
-- Table structure for rules_job_push_device
-- ----------------------------
DROP TABLE IF EXISTS `rules_job_push_device`;
CREATE TABLE `rules_job_push_device`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `device_ids` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '定时任务中，会存在多少',
  `rule_id` bigint NULL DEFAULT NULL COMMENT '规则id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 58 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '定时任务中，要主动发起采集指令的设备列表信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rules_job_push_device
-- ----------------------------
INSERT INTO `rules_job_push_device` VALUES (56, '43', 16);
INSERT INTO `rules_job_push_device` VALUES (57, '43', 16);

-- ----------------------------
-- Table structure for rules_listen_device_id
-- ----------------------------
DROP TABLE IF EXISTS `rules_listen_device_id`;
CREATE TABLE `rules_listen_device_id`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `device_id` bigint NULL DEFAULT NULL COMMENT '监听任务中的设备id',
  `rule_id` bigint NULL DEFAULT NULL COMMENT '规则id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 36 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '监听规则中，设备与规则的对应关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rules_listen_device_id
-- ----------------------------
INSERT INTO `rules_listen_device_id` VALUES (35, 42, 15);

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
) ENGINE = InnoDB AUTO_INCREMENT = 47 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '字典子项数据' ROW_FORMAT = DYNAMIC;

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
) ENGINE = InnoDB AUTO_INCREMENT = 1714118012721 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '字典类型数据' ROW_FORMAT = DYNAMIC;

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
) ENGINE = InnoDB AUTO_INCREMENT = 2876 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '操作日志' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_log_operate
-- ----------------------------
INSERT INTO `sys_log_operate` VALUES (2864, '操作日志', '系统操作日志分页', '/sana/sys/log/operate/sysPage', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"operateType\":11,\"startTime\":null,\"endTime\":null}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-10-13 10:11:13');
INSERT INTO `sys_log_operate` VALUES (2865, '操作日志', '系统操作日志分页', '/sana/sys/log/operate/sysPage', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"operateType\":11,\"startTime\":null,\"endTime\":null}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-10-13 10:12:13');
INSERT INTO `sys_log_operate` VALUES (2866, '操作日志', '系统操作日志分页', '/sana/sys/log/operate/sysPage', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"operateType\":11,\"startTime\":null,\"endTime\":null}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-10-13 10:12:13');
INSERT INTO `sys_log_operate` VALUES (2867, '字典类型', '字典列表', '/sana/sys/dict/list', 'GET', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-10-14 01:05:12');
INSERT INTO `sys_log_operate` VALUES (2868, '字典数据', '分页', '/sana/sys/dic/page', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"code\":\"post_status\"}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-10-14 01:05:12');
INSERT INTO `sys_log_operate` VALUES (2869, '操作日志', '数据存储定时任务查询', '/sana/sys/log/operate/getJobData', 'GET', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-10-14 01:07:52');
INSERT INTO `sys_log_operate` VALUES (2870, '机构管理', '列表', '/sana/sys/org/list', 'GET', '[{\"name\":null}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-10-14 01:11:33');
INSERT INTO `sys_log_operate` VALUES (2871, '用户管理', '分页', '/sana/sys/user/page', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"name\":null,\"groupId\":null}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-10-14 01:11:33');
INSERT INTO `sys_log_operate` VALUES (2872, '规则实例', '规则实例分页查询', '/sana/rules/rulesItem/page', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"name\":null}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-10-14 01:21:53');
INSERT INTO `sys_log_operate` VALUES (2873, '规则节点', '获取规则详情', '/sana/rules/rulesItemNode/getRulesItem', 'POST', '[{\"rulesId\":15}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-10-14 01:21:53');
INSERT INTO `sys_log_operate` VALUES (2874, '规则节点', '获取规则详情', '/sana/rules/rulesItemNode/getRulesItem', 'POST', '[{\"rulesId\":16}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-10-14 01:22:13');
INSERT INTO `sys_log_operate` VALUES (2875, '操作日志', '情景模式日志分页查询', '/sana/sys/log/operate/sysRulesPage', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"operateType\":9,\"startTime\":null,\"endTime\":null}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36', 1, 0, NULL, 1, '超级管理员', '2025-10-14 01:22:33');

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
INSERT INTO `sys_menus` VALUES (86, NULL, 'devices', '/devices', 'devices', NULL, NULL, NULL, 3, 1, '[超级管理员:admin]', 0, '设备信息', NULL, NULL, 'el-icon-message-box', 'menu', NULL, NULL, NULL, NULL, NULL, '2024-07-26 14:32:31', 1, '2024-07-26 14:34:18', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (87, 86, 'devices:product', '/devices/product', 'devices/product', NULL, NULL, NULL, 2, 1, '[超级管理员:admin]', 1, '测试分组', NULL, NULL, 'el-icon-cpu', 'menu', NULL, NULL, NULL, NULL, NULL, '2024-07-26 15:34:22', 1, '2024-07-29 17:55:31', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (88, 86, 'devices:deviceItem', '/devices/deviceItem', 'devices/deviceItem', NULL, NULL, NULL, 3, 1, '[超级管理员:admin]', 0, '设备管理', NULL, NULL, 'el-icon-monitor', 'menu', NULL, NULL, NULL, NULL, NULL, '2024-07-26 15:34:22', 1, '2024-07-26 15:51:04', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (89, 86, 'devices:deviceGroup', '/devices/deviceGroup', 'devices/deviceGroup', NULL, NULL, NULL, 4, 1, '[超级管理员:admin]', 0, '设备分组', NULL, NULL, 'el-icon-folder-add', 'menu', NULL, NULL, NULL, NULL, NULL, '2024-07-26 15:34:22', 1, '2024-07-26 17:34:10', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (90, NULL, 'abutment', '/abutment', 'abutment', NULL, NULL, NULL, 4, 1, '[超级管理员:admin]', 0, '接入管理', NULL, NULL, 'el-icon-expand', 'menu', NULL, NULL, NULL, NULL, NULL, '2024-07-26 14:32:31', 1, '2024-07-26 17:35:50', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (91, 90, 'abutment:deviceComm', '/abutment/communica', 'abutment/communica', NULL, NULL, NULL, 3, 1, '[超级管理员:admin]', 1, '通讯组件', NULL, NULL, 'el-icon-guide', 'menu', NULL, NULL, NULL, NULL, NULL, '2024-07-26 15:34:22', 1, '2024-07-26 17:24:11', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (92, 90, 'abutment:protocols', '/abutment/protocols', 'abutment/protocols', NULL, NULL, NULL, 2, 1, '[超级管理员:admin]', 0, '接入协议', NULL, NULL, 'el-icon-link', 'menu', NULL, NULL, NULL, NULL, NULL, '2024-07-26 15:34:22', 1, '2024-07-26 17:24:31', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (93, 90, 'abutment:edges', '/abutment/edges', 'abutment/edges', NULL, NULL, NULL, 1, 1, '[超级管理员:admin]', 1, '边缘计算实例', NULL, NULL, 'el-icon-connection', 'menu', NULL, NULL, NULL, NULL, NULL, '2024-07-26 15:34:22', 1, '2024-07-26 17:24:40', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (94, 86, 'devices:productType', '/devices/productType', 'devices/productType', NULL, NULL, NULL, 1, 1, '[超级管理员:admin]', 0, '产品管理', NULL, NULL, 'el-icon-copy-document', 'menu', NULL, NULL, NULL, NULL, NULL, '2024-07-26 15:34:22', 1, '2024-07-26 15:51:35', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (95, 90, 'abutment:client', '/abutment/client', 'abutment/client', NULL, NULL, NULL, 4, 1, '[超级管理员:admin]', 0, '客户端管理', NULL, NULL, 'el-icon-connection', 'menu', NULL, NULL, NULL, NULL, NULL, '2024-07-26 15:34:22', 1, '2025-06-12 10:41:01', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (96, NULL, 'warnings', '/warnings', 'warnings', NULL, NULL, NULL, 6, 1, '[超级管理员:admin]', 0, '预警信息', NULL, NULL, 'el-icon-warning', 'menu', NULL, NULL, NULL, NULL, NULL, '2024-07-26 14:32:31', 1, '2024-07-30 11:49:45', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (97, 96, 'warnings:settings', '/warnings/settings', 'warnings/settings', NULL, NULL, NULL, 1, 1, '[超级管理员:admin]', 1, '预警设置', NULL, NULL, 'el-icon-warning', 'menu', NULL, NULL, NULL, NULL, NULL, '2024-07-26 15:34:22', 1, '2024-07-30 11:49:56', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (98, 96, 'warnings:messages', '/warnings/messages', 'warnings/messages', NULL, NULL, NULL, 2, 1, '[超级管理员:admin]', 0, '预警消息记录', NULL, NULL, 'el-icon-message', 'menu', NULL, NULL, NULL, NULL, NULL, '2024-07-26 15:34:22', 1, '2025-04-11 15:23:09', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (99, NULL, 'rules', '/rules', 'rules', NULL, NULL, NULL, 5, 1, '[超级管理员:admin]', 0, '规则编排', NULL, NULL, 'el-icon-scale-to-original', 'menu', NULL, NULL, NULL, NULL, NULL, '2024-07-26 14:32:31', 1, '2024-07-30 11:49:12', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (100, 99, 'rules:settings', '/rules/settings', 'rules/settings', NULL, NULL, NULL, 1, 1, '[超级管理员:admin]', 0, '情景模式', NULL, NULL, 'el-icon-setting', 'menu', NULL, NULL, NULL, NULL, NULL, '2024-07-26 15:34:22', 1, '2024-07-30 11:45:59', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (101, 99, 'rules:logs', '/rules/logs', 'rules/logs', NULL, NULL, NULL, 2, 1, '[超级管理员:admin]', 0, '情景日志', NULL, NULL, 'el-icon-switch', 'menu', NULL, NULL, NULL, NULL, NULL, '2024-07-26 15:34:22', 1, '2025-05-23 10:20:23', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (102, NULL, 'combination', '/combination', 'combination', NULL, NULL, NULL, 7, 1, '[超级管理员:admin]', 0, '组态管理', NULL, NULL, 'el-icon-files', 'menu', NULL, NULL, NULL, NULL, NULL, '2024-07-26 14:32:31', 1, '2024-07-30 16:37:01', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (103, 102, 'combination:settings', '/combination/settings', 'combination/settings', NULL, NULL, NULL, 1, 1, '[超级管理员:admin]', 0, '组态设置', NULL, NULL, 'el-icon-crop', 'menu', NULL, NULL, NULL, NULL, NULL, '2024-07-26 15:34:22', 1, '2024-07-30 16:37:40', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (104, 102, 'combination:showing', '/combination/showing', 'combination/showing', NULL, NULL, NULL, 2, 1, '[超级管理员:admin]', 0, '组态展示', NULL, NULL, 'el-icon-full-screen', 'menu', NULL, NULL, NULL, NULL, NULL, '2024-07-26 15:34:22', 1, '2024-07-30 16:37:33', '[超级管理员:admin]');
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
INSERT INTO `sys_menus` VALUES (228, 94, 'deviceProductType.save', '', '', NULL, 'devices:deviceProductType:save', 'api/devices/deviceProductType/save', NULL, 1, '[超级管理员:admin]', 0, '新增', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 17:01:06', 1, '2024-05-08 17:57:05', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (229, 94, 'deviceProductType.update', '', '', NULL, 'devices:deviceProductType:update', 'api/devices/deviceProductType/update', NULL, 1, '[超级管理员:admin]', 0, '更新', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 17:01:06', 1, '2024-05-08 17:57:05', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (230, 94, 'deviceProductType.delete', '', '', NULL, 'devices:deviceProductType:delete', 'api/devices/deviceProductType/delete', NULL, 1, '[超级管理员:admin]', 0, '删除', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 17:01:06', 1, '2024-05-08 17:57:05', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (231, 94, 'deviceProductType.list', '', '', NULL, 'devices:deviceProductType:list', 'api/devices/deviceProductType/list', NULL, 1, '[超级管理员:admin]', 0, '列表', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 17:01:06', 1, '2024-05-08 17:57:05', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (232, 88, 'deviceItem.page', '', '', NULL, 'devices:deviceItem:page', 'api/devices/deviceItem/page', NULL, 1, '[超级管理员:admin]', 0, '列表', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 17:01:06', 1, '2024-05-08 17:57:05', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (233, 88, 'deviceItem.save', '', '', NULL, 'devices:deviceItem:save', 'api/devices/deviceItem/save', NULL, 1, '[超级管理员:admin]', 0, '新增', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 17:01:06', 1, '2024-05-08 17:57:05', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (234, 88, 'deviceItem.update', '', '', NULL, 'devices:deviceItem:update', 'api/devices/deviceItem/update', NULL, 1, '[超级管理员:admin]', 0, '修改', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 17:01:06', 1, '2024-05-08 17:57:05', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (235, 88, 'deviceItem.delete', '', '', NULL, 'devices:deviceItem:delete', 'api/devices/deviceItem/delete', NULL, 1, '[超级管理员:admin]', 0, '删除', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 17:01:06', 1, '2024-05-08 17:57:05', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (236, 89, 'deviceGroup.page', '', '', NULL, 'devices:deviceGroup:page', 'api/devices/deviceGroup/page', NULL, 1, '[超级管理员:admin]', 0, '列表', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 17:01:06', 1, '2024-05-08 17:57:05', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (237, 89, 'deviceGroup.save', '', '', NULL, 'devices:deviceGroup:save', 'api/devices/deviceGroup/save', NULL, 1, '[超级管理员:admin]', 0, '新增', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 17:01:06', 1, '2024-05-08 17:57:05', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (238, 89, 'deviceGroup.update', '', '', NULL, 'devices:deviceGroup:update', 'api/devices/deviceGroup/update', NULL, 1, '[超级管理员:admin]', 0, '修改', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 17:01:06', 1, '2024-05-08 17:57:05', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (239, 89, 'deviceGroup.delete', '', '', NULL, 'devices:deviceGroup:delete', 'api/devices/deviceGroup/delete', NULL, 1, '[超级管理员:admin]', 0, '删除', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 17:01:06', 1, '2024-05-08 17:57:05', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (243, 94, 'deviceProductType.detail', '', '', NULL, 'devices:deviceProductType:detail', 'devices/deviceProductType/detail', NULL, 1, '[超级管理员:admin]', 0, '产品详情', NULL, NULL, '', 'button', NULL, NULL, NULL, NULL, NULL, '2025-05-27 16:15:01', 1, '2025-05-27 17:07:15', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (244, 94, 'deviceProductType.model', '', '', NULL, 'devices:deviceProductType:model', 'devices/deviceProductMode/save', NULL, 1, '[超级管理员:admin]', 0, '产品详情-产品物模型修改', NULL, NULL, '', 'button', NULL, NULL, NULL, NULL, NULL, '2025-05-27 16:20:27', 1, '2025-05-27 17:07:10', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (245, 94, 'deviceProductType.abutment', '', '', NULL, 'devices:deviceProductType:abutment', 'devices/deviceProductType/abutment', NULL, 1, '[超级管理员:admin]', 0, '产品详情-接入管理', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2025-05-27 16:24:28', 1, '2025-05-27 17:07:02', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (246, 88, 'devices.deviceDetail', '', '', NULL, 'devices:deviceDetail:detail', 'devices/deviceDetail/detail', NULL, 1, '[超级管理员:admin]', 0, '设备详情', NULL, NULL, '', 'button', NULL, NULL, NULL, NULL, NULL, '2025-05-27 17:26:20', 1, '2025-05-27 17:36:51', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (247, 88, 'deviceDetail.modelSave', '', '', NULL, 'devices:deviceDetail:modelSave', 'devices/deviceDetail/modelSave', NULL, 1, '[超级管理员:admin]', 0, '设备详情-物模型-保存', NULL, NULL, '', 'button', NULL, NULL, NULL, NULL, NULL, '2025-05-27 17:28:30', 1, '2025-05-27 17:48:14', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (248, 88, 'deviceDetail.serveAdd', '', '', NULL, 'devices:deviceDetail:serveAdd', 'devices/deviceDetail/serveAdd', NULL, 1, '[超级管理员:admin]', 0, '设备详情-设备功能-保存', NULL, NULL, '', 'button', NULL, NULL, NULL, NULL, NULL, '2025-05-27 17:31:56', 1, '2025-05-27 17:33:03', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (249, 88, 'deviceDetail.serveDel', '', '', NULL, 'devices:deviceDetail:serveDel', 'devices:deviceDetail:serveDel', NULL, 1, '[超级管理员:admin]', 0, '设备详情-设备功能-删除', NULL, NULL, '', 'button', NULL, NULL, NULL, NULL, NULL, '2025-05-27 17:33:32', 1, '2025-05-27 17:34:16', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (250, 89, 'deviceGroup.binding', '', '', NULL, 'devices:deviceGroup:binding', 'devices/deviceGroup/binding', NULL, 1, '[超级管理员:admin]', 0, '绑定设备', NULL, NULL, '', 'button', NULL, NULL, NULL, NULL, NULL, '2025-05-27 17:52:54', 1, '2025-05-27 18:00:14', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (251, 89, 'deviceGroup.unbind', '', '', NULL, 'devices:deviceGroup:unbind', 'devices/deviceGroup/unbind', NULL, 1, '[超级管理员:admin]', 0, '取消绑定', NULL, NULL, '', 'button', NULL, NULL, NULL, NULL, NULL, '2025-05-27 17:57:46', 1, '2025-05-27 17:59:06', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (252, 89, 'deviceGroup.bindSava', '', '', NULL, 'devices:deviceGroup:bindSava', 'devices/deviceGroup/bindSava', NULL, 1, '[超级管理员:admin]', 0, '绑定设备-保存', NULL, NULL, '', 'button', NULL, NULL, NULL, NULL, NULL, '2025-05-27 18:00:46', 1, '2025-05-27 18:01:43', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (253, 92, 'protocols.del', '', '', NULL, 'abutment:protocols:del', 'abutment/protocols/del', NULL, 1, '[超级管理员:admin]', 0, '删除', NULL, NULL, '', 'button', NULL, NULL, NULL, NULL, NULL, '2025-05-28 11:10:22', 1, '2025-05-28 11:21:03', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (254, 92, 'protocols.logs', '', '', NULL, 'abutment:protocols:logs', 'abutment/protocols/logs', NULL, 1, '[超级管理员:admin]', 0, '日志', NULL, NULL, '', 'button', NULL, NULL, NULL, NULL, NULL, '2025-05-28 11:16:25', 1, '2025-05-28 11:17:21', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (255, 100, 'rules.save', '', '', NULL, 'rules:rules:save', 'rules/rules/save', NULL, 1, '[超级管理员:admin]', 0, '新增', NULL, NULL, '', 'button', NULL, NULL, NULL, NULL, NULL, '2025-05-28 11:30:15', 1, '2025-05-28 11:38:14', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (256, 100, 'rules.update', '', '', NULL, 'rules:rules:update', 'rules/rules/update', NULL, 1, '[超级管理员:admin]', 0, '编辑', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2025-05-28 11:39:55', 1, '2025-05-28 11:40:22', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (257, 100, 'rules.delete', '', '', NULL, 'rules:rules:delete', 'rules/rules/delete', NULL, 1, '[超级管理员:admin]', 0, '删除', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2025-05-28 11:40:58', 1, '2025-05-28 11:44:23', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (258, 100, 'rules.setRule', '', '', NULL, 'rules:rules:setRule', 'rules/rules/setRule', NULL, 1, '[超级管理员:admin]', 0, '规则设置', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2025-05-28 11:42:14', 1, '2025-05-28 11:47:03', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (259, 100, 'rules.updateAndSave', '', '', NULL, 'rules:rules:updateAndSave', 'rules/rules/updateAndSave', NULL, 1, '[超级管理员:admin]', 0, '保存规则信息', NULL, NULL, '', 'button', NULL, NULL, NULL, NULL, NULL, '2025-05-28 11:45:44', 1, '2025-05-28 11:47:11', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (260, 77, 'password.save', '', '', NULL, 'serve:password:save', 'serve/password/save', NULL, 1, '[超级管理员:admin]', 0, '保存密码', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2025-05-28 14:56:54', 1, '2025-05-28 15:01:24', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (261, 77, 'user.save', '', '', NULL, 'serve:user:save', 'serve/user/save', NULL, 1, '[超级管理员:admin]', 0, '保存账号信息', NULL, NULL, '', 'button', NULL, NULL, NULL, NULL, NULL, '2025-05-28 15:01:49', 1, '2025-05-28 15:06:25', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (262, 77, 'user.show', '', '', NULL, 'serve:user:show', 'serve/user/show', NULL, 1, '[超级管理员:admin]', 0, '查看', NULL, NULL, '', 'button', NULL, NULL, NULL, NULL, NULL, '2025-05-28 15:04:19', 1, '2025-05-28 15:05:32', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (263, 77, '1', '', '', NULL, 'qw', '', NULL, 1, '[超级管理员:admin]', 1, 'test', NULL, NULL, '', 'menu', NULL, NULL, NULL, NULL, NULL, '2025-05-28 15:06:41', 1, '2025-05-28 15:06:52', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (264, 88, 'deviceItem.historyData', '', '', NULL, 'devices:deviceItem:historyData', '', NULL, 1, '[超级管理员:admin]', 0, '设备历史数据', NULL, NULL, '', 'button', NULL, NULL, NULL, NULL, NULL, '2025-06-17 11:19:31', 1, '2025-06-17 11:22:10', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (265, NULL, 'camera', '/camera', 'camera', NULL, NULL, NULL, 8, 1, '[超级管理员:admin]', 0, '摄像头管理', NULL, NULL, 'el-icon-video-camera', 'menu', NULL, NULL, NULL, NULL, NULL, '2025-07-03 10:33:23', 1, '2025-07-03 11:09:08', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (266, 265, 'camera:settings', '/camera/settings', 'camera/settings', NULL, NULL, NULL, 1, 1, '[超级管理员:admin]', 0, '平台设置', NULL, NULL, 'el-icon-watermelon', 'menu', NULL, NULL, NULL, NULL, NULL, '2025-07-03 11:06:58', 1, '2025-07-03 11:24:48', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (267, 265, 'camera:list', '/camera/list', 'camera/list', NULL, NULL, NULL, 2, 1, '[超级管理员:admin]', 0, '设备列表', NULL, NULL, 'el-icon-scale-to-original', 'menu', NULL, NULL, NULL, NULL, NULL, '2025-07-03 11:21:21', 1, '2025-07-03 11:26:20', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (268, 265, 'camera:multiscreen', '/camera/multiscreen', 'camera/multiscreen', NULL, NULL, NULL, 3, 1, '[超级管理员:admin]', 0, '分屏播放', NULL, NULL, 'el-icon-menu', 'menu', NULL, NULL, NULL, NULL, NULL, '2025-07-03 11:26:30', 1, '2025-07-03 11:28:28', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (269, 265, 'camera:record', '/camera/record', 'camera/record', NULL, NULL, NULL, 4, 1, '[超级管理员:admin]', 0, '录制管理', NULL, NULL, 'el-icon-video-play', 'menu', NULL, NULL, NULL, NULL, NULL, '2025-07-03 11:29:42', 1, '2025-07-03 11:31:12', '[超级管理员:admin]');

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
INSERT INTO `sys_user` VALUES (1, 'admin', '$2a$10$zFcjZlqGuJyglnToPdXJeuxKcEHN.ztB0gVmIT2BS1UBno1qIFyeK', '超级管理员', 'http://8.139.4.114:9000/dev/20250527/1748330038908_54870.jpg', 1, 'lyl@163.com', '13245677645', '三月来百草开 盈香满袖万物苏\n虫鸣和着欢笑 心事舒', 1, 1, 0, 10000, '2024-01-10 06:13:37', 1, '2025-05-27 15:14:33', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_user` VALUES (2, 'lana', '{bcrypt}$2a$10$9u1PD9leVDpJEeIFb4jbo.GwD403Zobjg82HBzxJfYPVpHtvJu2xK', 'lana0101', 'http://8.139.4.114:9000/dev/20250527/1748330038908_54853.jpg', 2, 'lyl@163.com', '13245677656', '牛马干活\\韭菜施肥\\蘸料上色', 0, 1, 0, 10000, '2024-07-22 09:49:43', 1, '2025-05-27 15:33:27', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_user` VALUES (10006, 'test', '{bcrypt}$2a$10$IjToP406QrLlpW/RpSL.RumkMqKsNUTr.4wD1wA3DeB8ImGU/e02W', '测试用户1', 'http://8.139.4.114:9000/dev/20250527/1748330038908_54878.jpg', NULL, NULL, NULL, NULL, 0, 1, 1, 1, '2024-11-26 17:25:34', 1, '2025-05-27 15:33:31', '[超级管理员:admin]', '[超级管理员:admin]');

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
