/*
 Navicat Premium Data Transfer

 Source Server         : 39.108.191.56
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : 39.108.191.56:3306
 Source Schema         : youyd

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 20/05/2019 17:22:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for QRTZ_BLOB_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_BLOB_TRIGGERS`;
CREATE TABLE `QRTZ_BLOB_TRIGGERS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `BLOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `QRTZ_BLOB_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for QRTZ_CALENDARS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_CALENDARS`;
CREATE TABLE `QRTZ_CALENDARS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CALENDAR_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `CALENDAR_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for QRTZ_CRON_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_CRON_TRIGGERS`;
CREATE TABLE `QRTZ_CRON_TRIGGERS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CRON_EXPRESSION` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TIME_ZONE_ID` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `QRTZ_CRON_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of QRTZ_CRON_TRIGGERS
-- ----------------------------
INSERT INTO `QRTZ_CRON_TRIGGERS` VALUES ('TASK_EXECUTOR', 'TEST_TRIGGER', 'TEST_GROUP', '* * * * * ? *', 'Asia/Shanghai');

-- ----------------------------
-- Table structure for QRTZ_FIRED_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_FIRED_TRIGGERS`;
CREATE TABLE `QRTZ_FIRED_TRIGGERS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ENTRY_ID` varchar(95) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `INSTANCE_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `ENTRY_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for QRTZ_JOB_DETAILS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_JOB_DETAILS`;
CREATE TABLE `QRTZ_JOB_DETAILS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DESCRIPTION` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `IS_DURABLE` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `IS_NONCONCURRENT` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `IS_UPDATE_DATA` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of QRTZ_JOB_DETAILS
-- ----------------------------
INSERT INTO `QRTZ_JOB_DETAILS` VALUES ('TASK_EXECUTOR', 'TestJob', 'TEST_GROUP', 'Test Job for SpringBoot', 'com.youyd.base.job.TestJob', '0', '0', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787000737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F40000000000010770800000010000000007800);

-- ----------------------------
-- Table structure for QRTZ_LOCKS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_LOCKS`;
CREATE TABLE `QRTZ_LOCKS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `LOCK_NAME` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `LOCK_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of QRTZ_LOCKS
-- ----------------------------
INSERT INTO `QRTZ_LOCKS` VALUES ('TASK_EXECUTOR', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for QRTZ_PAUSED_TRIGGER_GRPS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_PAUSED_TRIGGER_GRPS`;
CREATE TABLE `QRTZ_PAUSED_TRIGGER_GRPS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for QRTZ_SCHEDULER_STATE
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_SCHEDULER_STATE`;
CREATE TABLE `QRTZ_SCHEDULER_STATE`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `INSTANCE_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `INSTANCE_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for QRTZ_SIMPLE_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_SIMPLE_TRIGGERS`;
CREATE TABLE `QRTZ_SIMPLE_TRIGGERS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `QRTZ_SIMPLE_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for QRTZ_SIMPROP_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_SIMPROP_TRIGGERS`;
CREATE TABLE `QRTZ_SIMPROP_TRIGGERS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `STR_PROP_1` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `STR_PROP_2` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `STR_PROP_3` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `INT_PROP_1` int(11) NULL DEFAULT NULL,
  `INT_PROP_2` int(11) NULL DEFAULT NULL,
  `LONG_PROP_1` bigint(20) NULL DEFAULT NULL,
  `LONG_PROP_2` bigint(20) NULL DEFAULT NULL,
  `DEC_PROP_1` decimal(13, 4) NULL DEFAULT NULL,
  `DEC_PROP_2` decimal(13, 4) NULL DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `QRTZ_SIMPROP_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for QRTZ_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_TRIGGERS`;
CREATE TABLE `QRTZ_TRIGGERS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DESCRIPTION` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) NULL DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) NULL DEFAULT NULL,
  `PRIORITY` int(11) NULL DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_TYPE` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) NULL DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) NULL DEFAULT NULL,
  `JOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `SCHED_NAME`(`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) USING BTREE,
  CONSTRAINT `QRTZ_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `QRTZ_JOB_DETAILS` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of QRTZ_TRIGGERS
-- ----------------------------
INSERT INTO `QRTZ_TRIGGERS` VALUES ('TASK_EXECUTOR', 'TEST_TRIGGER', 'TEST_GROUP', 'TestJob', 'TEST_GROUP', 'Test Job for SpringBoot', 1557030918000, -1, 5, 'PAUSED', 'CRON', 1557030918000, 0, NULL, 2, '');

-- ----------------------------
-- Table structure for ar_article
-- ----------------------------
DROP TABLE IF EXISTS `ar_article`;
CREATE TABLE `ar_article`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
  `category_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类ID',
  `label` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签（逗号相隔多个标签）',
  `user_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户ID',
  `title` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `description` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章描述（概述）',
  `image` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章封面',
  `create_at` bigint(20) NULL DEFAULT NULL COMMENT '发表日期',
  `update_at` bigint(20) NULL DEFAULT NULL COMMENT '修改日期',
  `is_public` int(2) NULL DEFAULT NULL COMMENT '是否公开（草稿）',
  `is_top` int(2) NULL DEFAULT NULL COMMENT '是否置顶',
  `visits` int(20) NULL DEFAULT NULL COMMENT '浏览量',
  `upvote` int(20) NULL DEFAULT NULL COMMENT '点赞数',
  `comment` int(20) NULL DEFAULT NULL COMMENT '评论数',
  `review_state` int(2) NULL DEFAULT NULL COMMENT '审核状态（1:通过，2：审核中，3：拒绝）',
  `url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'URL',
  `type` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型',
  `importance` float(2, 1) NULL DEFAULT NULL COMMENT '热度',
  `keywords` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关键字',
  `origin` int(1) NULL DEFAULT NULL COMMENT '来源（1：原创，2：转载，3：混撰）',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '文章正文',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文章板块/文章管理' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of ar_article
-- ----------------------------
INSERT INTO `ar_article` VALUES ('1', '3', 'kkkk,jjjj', '12151044', '受脑认知和神经科学启发的人工智能', '大概描', 'https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1229855747,4092679124&fm=26&gp=0.jpg', 20190129194744, 20190129194746, 1, 1, 25, 3562, 11, 1, '1', '1', 3.0, '1', 1, '<h3>第一条文章测试</h3>\n<ol>\n<li>6啊</li>\n<li>哈哈哈<img src=\"https://p.nanrenwo.net/uploads/allimg/160907/8122-160ZG10T5-50.jpg\" alt=\"&acirc;&auml;&ordm;&aring;&acirc;&ccedil;&aring;&frac34;&ccedil;&aelig;&ccedil;&acute;&cent;&ccedil;&raquo;&aelig;\" /></li>\n</ol>');
INSERT INTO `ar_article` VALUES ('1125040614373310466', '1', '言情,假的', NULL, '第三个文章哦', '第三个文章哦', NULL, 1557065559366, 1557065559366, 0, 1, 0, 0, 0, 1, NULL, NULL, 0.0, NULL, 2, '<p>第三个文章哦</p>');
INSERT INTO `ar_article` VALUES ('2', '2', '第一个标签', '2', '测试', '2', 'https://ss1.baidu.com/9vo3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=c8a9d4e2841363270aedc433a18fa056/11385343fbf2b2114a65cd70c48065380cd78e41.jpg', 20190130101649, 20190130101649, 2, 2, 0, 3, 11, 2, '2', '2', 2.0, '2', 1, '1');

-- ----------------------------
-- Table structure for ar_article_tags
-- ----------------------------
DROP TABLE IF EXISTS `ar_article_tags`;
CREATE TABLE `ar_article_tags`  (
  `id` int(20) NOT NULL COMMENT 'ID',
  `article_id` int(20) NULL DEFAULT NULL COMMENT '文章id',
  `tags_id` int(20) NULL DEFAULT NULL COMMENT '标签id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文章板块/文章_标签中间表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of ar_article_tags
-- ----------------------------
INSERT INTO `ar_article_tags` VALUES (1111101, 1, 1);
INSERT INTO `ar_article_tags` VALUES (1111102, 2, 3);
INSERT INTO `ar_article_tags` VALUES (1111103, 3, 1);

-- ----------------------------
-- Table structure for ar_category
-- ----------------------------
DROP TABLE IF EXISTS `ar_category`;
CREATE TABLE `ar_category`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
  `parent_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父ID',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类名称',
  `summary` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类简介',
  `user_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户ID',
  `create_at` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `state` int(2) NULL DEFAULT NULL COMMENT '状态',
  `update_at` bigint(20) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文章板块/文章分类表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of ar_category
-- ----------------------------
INSERT INTO `ar_category` VALUES ('0', NULL, NULL, NULL, NULL, NULL, 1, NULL);
INSERT INTO `ar_category` VALUES ('1', '0', 'java', 'spring开发标签', '1151577', 1555773930915, 1, 1555773930915);
INSERT INTO `ar_category` VALUES ('1123854055171100673', '3', '121', '1212', '1151577', 1555773930915, 1, 1555773930915);
INSERT INTO `ar_category` VALUES ('1123968229603758082', '', '', '', NULL, NULL, 0, NULL);
INSERT INTO `ar_category` VALUES ('2', '0', 'javaScript', 'javaScript', '1151577', 1555773930915, 1, 1555773930915);
INSERT INTO `ar_category` VALUES ('3', '1', 'mybatis', 'mybatis', '1151577', 1555773930915, 1, 1555773930915);

-- ----------------------------
-- Table structure for ar_comment
-- ----------------------------
DROP TABLE IF EXISTS `ar_comment`;
CREATE TABLE `ar_comment`  (
  `id` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评论表id',
  `article_id` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '外键文章表ID',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评论内容',
  `user_id` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评论人ID',
  `parent_id` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父评论ID(如果为0表示文章的顶级评论,每一条评论都可以被评论)',
  `publish_date` bigint(20) NULL DEFAULT NULL COMMENT '评论日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文章板块/文章评论' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ar_tags
-- ----------------------------
DROP TABLE IF EXISTS `ar_tags`;
CREATE TABLE `ar_tags`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签名称',
  `slug` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '英文名称',
  `description` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `icon` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签图标',
  `create_at` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `state` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  `update_at` bigint(20) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文章板块/文章标签表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of ar_tags
-- ----------------------------
INSERT INTO `ar_tags` VALUES ('1', '2019', '2019', '2019年标签', 'icon-math', 20190130190724, '1', 20190309021015);
INSERT INTO `ar_tags` VALUES ('2', '多肉植物', 'succulent', '多肉植物标签', 'icon-physics', 20190130190724, '1', 20190309021011);
INSERT INTO `ar_tags` VALUES ('3', 'LeetCode', 'LeetCode', 'LeetCode标签', 'icon-physics', 20190130190724, '1', 20190309020958);

-- ----------------------------
-- Table structure for ba_dict
-- ----------------------------
DROP TABLE IF EXISTS `ba_dict`;
CREATE TABLE `ba_dict`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '树形字典',
  `parent_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父节点ID',
  `code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编码',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `description` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `state` int(2) NULL DEFAULT NULL COMMENT '状态',
  `type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '基础板块/字典管理' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of ba_dict
-- ----------------------------
INSERT INTO `ba_dict` VALUES ('1', NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for ba_job
-- ----------------------------
DROP TABLE IF EXISTS `ba_job`;
CREATE TABLE `ba_job`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '任务表',
  `job_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务id',
  `class_name` varchar(70) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务执行时调用哪个类的方法 包名+类名，完全限定名',
  `cron_expression` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'cron 表达式',
  `job_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务名称',
  `job_group` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务组',
  `trigger_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '触发器名称',
  `trigger_group` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '触发组',
  `pause` int(2) NULL DEFAULT NULL COMMENT '暂停',
  `enable` int(2) NULL DEFAULT NULL COMMENT '是否启用',
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create_at` bigint(20) NULL DEFAULT NULL COMMENT '创建日期',
  `update_at` bigint(20) NULL DEFAULT NULL COMMENT '修改日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '基础板块/任务调度' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of ba_job
-- ----------------------------
INSERT INTO `ba_job` VALUES ('1', NULL, 'com.youyd.base.job.TestJob', '* * * * * ? *', 'TestJob', 'TEST_GROUP', 'TEST_TRIGGER', 'TEST_GROUP', 1, 1, 'Test Job for SpringBoot', 1557030916263, 1557030916264);

-- ----------------------------
-- Table structure for ba_login_log
-- ----------------------------
DROP TABLE IF EXISTS `ba_login_log`;
CREATE TABLE `ba_login_log`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '日志表',
  `user_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录人',
  `client_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录ip',
  `browser` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '浏览器',
  `os_info` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作系统信息',
  `create_at` bigint(20) NULL DEFAULT NULL COMMENT '创建日期（登录）',
  `update_at` bigint(20) NULL DEFAULT NULL COMMENT '修改日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '基础板块/用户登录日志' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of ba_login_log
-- ----------------------------
INSERT INTO `ba_login_log` VALUES ('1124361845681676290', '1', '127.0.0.1', 'Chrome', 'Windows 10', 1556903721289, 1556903721289);
INSERT INTO `ba_login_log` VALUES ('1124361936127647745', '1', '127.0.0.1', 'Chrome', 'Windows 10', 1556903745784, 1556903745784);
INSERT INTO `ba_login_log` VALUES ('1124543839216467969', '1', '127.0.0.1', 'Chrome', 'Windows 10', 1556947114934, 1556947114936);
INSERT INTO `ba_login_log` VALUES ('1124545211714371585', '1', '127.0.0.1', 'Chrome', 'Windows 10', 1556947446153, 1556947446153);
INSERT INTO `ba_login_log` VALUES ('1124570801762893826', '1', '127.0.0.1', 'Chrome', 'Windows 10', 1556953547263, 1556953547263);
INSERT INTO `ba_login_log` VALUES ('1124593773974298625', '1', '127.0.0.1', 'Chrome', 'Windows 10', 1556959020567, 1556959020568);
INSERT INTO `ba_login_log` VALUES ('1124595063701807105', '1', '127.0.0.1', 'Chrome', 'Windows 10', 1556959331817, 1556959331817);
INSERT INTO `ba_login_log` VALUES ('1124595763810836482', '1', '127.0.0.1', 'Chrome', 'Windows 10', 1556959498706, 1556959498706);
INSERT INTO `ba_login_log` VALUES ('1124597652224909313', '1', '127.0.0.1', 'Chrome', 'Windows 10', 1556959948942, 1556959948942);
INSERT INTO `ba_login_log` VALUES ('1124662492410875905', '1', '127.0.0.1', 'Chrome', 'Windows 10', 1556975403775, 1556975403775);
INSERT INTO `ba_login_log` VALUES ('1124668670389981186', '1', '127.0.0.1', 'Chrome', 'Windows 10', 1556976881003, 1556976881003);
INSERT INTO `ba_login_log` VALUES ('1124679862332342273', '1', '127.0.0.1', 'Chrome', 'Windows 10', 1556979546153, 1556979546153);
INSERT INTO `ba_login_log` VALUES ('1124890792349659137', '1', '127.0.0.1', 'Chrome', 'Windows 10', 1557029835042, 1557029835042);
INSERT INTO `ba_login_log` VALUES ('1124891216486068226', '1', '127.0.0.1', 'Chrome', 'Windows 10', 1557029940118, 1557029940118);
INSERT INTO `ba_login_log` VALUES ('1124958446154330113', '1', '127.0.0.1', 'Chrome', 'Windows 10', 1557045968545, 1557045968545);
INSERT INTO `ba_login_log` VALUES ('1124959596463173634', '1', '127.0.0.1', 'Chrome', 'Windows 10', 1557046243178, 1557046243178);
INSERT INTO `ba_login_log` VALUES ('1124960148093841410', '1', '127.0.0.1', 'Chrome', 'Windows 10', 1557046374701, 1557046374701);
INSERT INTO `ba_login_log` VALUES ('1124960340457205762', '1', '127.0.0.1', 'Chrome', 'Windows 10', 1557046420564, 1557046420564);
INSERT INTO `ba_login_log` VALUES ('1124971570269954050', '1', '127.0.0.1', 'Chrome', 'Windows 10', 1557049094206, 1557049094206);
INSERT INTO `ba_login_log` VALUES ('1125032574286712834', '1', '127.0.0.1', 'Chrome', 'Windows 10', 1557063640245, 1557063640246);
INSERT INTO `ba_login_log` VALUES ('1125038956608114690', '1', '127.0.0.1', 'Chrome', 'Windows 10', 1557065164106, 1557065164106);
INSERT INTO `ba_login_log` VALUES ('1125039066599542785', '1', '127.0.0.1', 'Chrome', 'Windows 10', 1557065190339, 1557065190339);

-- ----------------------------
-- Table structure for ba_opt_log
-- ----------------------------
DROP TABLE IF EXISTS `ba_opt_log`;
CREATE TABLE `ba_opt_log`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '日志表',
  `user_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人',
  `client_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作ip',
  `type` int(2) NULL DEFAULT NULL COMMENT '操作类型（1：增，2：删，3：改）',
  `browser` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '浏览器',
  `os_info` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作系统信息',
  `method` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作方法名称',
  `params` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作方法的参数（json）',
  `exception_detail` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '异常详情',
  `create_at` bigint(20) NULL DEFAULT NULL COMMENT '创建日期（操作）',
  `update_at` bigint(20) NULL DEFAULT NULL COMMENT '修改日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '基础板块/用户操作日志' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of ba_opt_log
-- ----------------------------
INSERT INTO `ba_opt_log` VALUES ('1124598762213908481', '1', '127.0.0.1', 1, 'Chrome', 'Windows 10', 'com.youyd.user.controller.UserController.findByCondition()', '[{\"id\":null,\"password\":null,\"userName\":\"\",\"email\":null,\"status\":null,\"contactAddress\":null,\"registeredType\":null,\"account\":null,\"avatar\":null,\"sex\":null,\"lastDate\":null,\"birthday\":null,\"interest\":null,\"personality\":null,\"fansCount\":null,\"followCount\":null,\"phone\":null,\"onlineTime\":null,\"nickName\":null,\"createAt\":null,\"updateAt\":null},{\"pageSize\":10,\"searchSort\":null,\"searchValue\":null,\"orderBy\":null,\"fieldSort\":null,\"pageNum\":1}]', NULL, 1556960213585, 1556960213585);
INSERT INTO `ba_opt_log` VALUES ('1124667727644659713', '临时用户', '127.0.0.1', NULL, 'Chrome', 'Windows 10', 'com.youyd.user.controller.UserController.findByCondition()', '[{\"id\":null,\"password\":null,\"userName\":\"\",\"interest\":null,\"personality\":null,\"nickName\":null,\"followCount\":null,\"lastDate\":null,\"fansCount\":null,\"sex\":null,\"phone\":null,\"birthday\":null,\"onlineTime\":null,\"account\":null,\"avatar\":null,\"status\":null,\"email\":null,\"registeredType\":null,\"contactAddress\":null,\"createAt\":null,\"updateAt\":null},{\"pageSize\":10,\"orderBy\":null,\"fieldSort\":null,\"searchSort\":null,\"searchValue\":null,\"pageNum\":1}]', 'io.jsonwebtoken.ExpiredJwtException:--->JWT expired at 2019-05-04T21:15:59+0800. Current time: 2019-05-04T21:30:56+0800', 1556976656150, 1556976656150);
INSERT INTO `ba_opt_log` VALUES ('1124668546599292929', '临时用户', '127.0.0.1', NULL, 'Chrome', 'Windows 10', 'com.youyd.user.controller.UserController.findByCondition()', '[{\"id\":null,\"password\":null,\"userName\":\"\",\"interest\":null,\"personality\":null,\"nickName\":null,\"followCount\":null,\"lastDate\":null,\"fansCount\":null,\"sex\":null,\"phone\":null,\"birthday\":null,\"onlineTime\":null,\"account\":null,\"avatar\":null,\"status\":null,\"email\":null,\"registeredType\":null,\"contactAddress\":null,\"createAt\":null,\"updateAt\":null},{\"pageSize\":10,\"orderBy\":null,\"fieldSort\":null,\"searchSort\":null,\"searchValue\":null,\"pageNum\":1}]', 'io.jsonwebtoken.ExpiredJwtException:--->JWT expired at 2019-05-04T21:15:59+0800. Current time: 2019-05-04T21:34:11+0800', 1556976851480, 1556976851480);
INSERT INTO `ba_opt_log` VALUES ('1124668628761513986', '临时用户', '127.0.0.1', NULL, 'Chrome', 'Windows 10', 'com.youyd.user.controller.UserController.findByCondition()', '[{\"id\":null,\"password\":null,\"userName\":\"\",\"interest\":null,\"personality\":null,\"nickName\":null,\"followCount\":null,\"lastDate\":null,\"fansCount\":null,\"sex\":null,\"phone\":null,\"birthday\":null,\"onlineTime\":null,\"account\":null,\"avatar\":null,\"status\":null,\"email\":null,\"registeredType\":null,\"contactAddress\":null,\"createAt\":null,\"updateAt\":null},{\"pageSize\":10,\"orderBy\":null,\"fieldSort\":null,\"searchSort\":null,\"searchValue\":null,\"pageNum\":1}]', 'io.jsonwebtoken.ExpiredJwtException:--->JWT expired at 2019-05-04T21:15:59+0800. Current time: 2019-05-04T21:34:31+0800', 1556976871073, 1556976871073);
INSERT INTO `ba_opt_log` VALUES ('1124668673397297153', '1', '127.0.0.1', 1, 'Chrome', 'Windows 10', 'com.youyd.user.controller.UserController.findByCondition()', '[{\"id\":null,\"password\":null,\"userName\":\"\",\"interest\":null,\"personality\":null,\"nickName\":null,\"followCount\":null,\"lastDate\":null,\"fansCount\":null,\"sex\":null,\"phone\":null,\"birthday\":null,\"onlineTime\":null,\"account\":null,\"avatar\":null,\"status\":null,\"email\":null,\"registeredType\":null,\"contactAddress\":null,\"createAt\":null,\"updateAt\":null},{\"pageSize\":10,\"orderBy\":null,\"fieldSort\":null,\"searchSort\":null,\"searchValue\":null,\"pageNum\":1}]', NULL, 1556976881712, 1556976881712);
INSERT INTO `ba_opt_log` VALUES ('1124668862593961985', '1', '127.0.0.1', 1, 'Chrome', 'Windows 10', 'com.youyd.user.controller.UserController.findByCondition()', '[{\"id\":null,\"password\":null,\"userName\":\"\",\"interest\":null,\"personality\":null,\"nickName\":null,\"followCount\":null,\"lastDate\":null,\"fansCount\":null,\"sex\":null,\"phone\":null,\"birthday\":null,\"onlineTime\":null,\"account\":null,\"avatar\":null,\"status\":null,\"email\":null,\"registeredType\":null,\"contactAddress\":null,\"createAt\":null,\"updateAt\":null},{\"pageSize\":10,\"orderBy\":null,\"fieldSort\":null,\"searchSort\":null,\"searchValue\":null,\"pageNum\":1}]', NULL, 1556976926822, 1556976926822);
INSERT INTO `ba_opt_log` VALUES ('1124891299713642497', '1', '127.0.0.1', 1, 'Chrome', 'Windows 10', 'com.youyd.user.controller.UserController.findByCondition()', '[{\"id\":null,\"password\":null,\"userName\":\"\",\"account\":null,\"avatar\":null,\"email\":null,\"status\":null,\"contactAddress\":null,\"registeredType\":null,\"nickName\":null,\"lastDate\":null,\"onlineTime\":null,\"interest\":null,\"personality\":null,\"fansCount\":null,\"followCount\":null,\"sex\":null,\"birthday\":null,\"phone\":null,\"updateAt\":null,\"createAt\":null},{\"pageNum\":1,\"searchSort\":null,\"orderBy\":null,\"fieldSort\":null,\"searchValue\":null,\"pageSize\":10}]', NULL, 1557029959922, 1557029959922);
INSERT INTO `ba_opt_log` VALUES ('1124973507866435586', '临时用户', '127.0.0.1', NULL, 'Chrome', 'Windows 10', 'com.youyd.user.controller.UserController.findByCondition()', '[{\"id\":null,\"password\":null,\"userName\":\"\",\"registeredType\":null,\"contactAddress\":null,\"avatar\":null,\"account\":null,\"sex\":null,\"interest\":null,\"fansCount\":null,\"nickName\":null,\"birthday\":null,\"lastDate\":null,\"onlineTime\":null,\"personality\":null,\"followCount\":null,\"phone\":null,\"email\":null,\"status\":null,\"createAt\":null,\"updateAt\":null},{\"pageNum\":1,\"fieldSort\":null,\"searchValue\":null,\"searchSort\":null,\"orderBy\":null,\"pageSize\":10}]', 'io.jsonwebtoken.ExpiredJwtException:--->JWT expired at 2019-05-05T17:44:12+0800. Current time: 2019-05-05T17:45:59+0800', 1557049559893, 1557049559893);
INSERT INTO `ba_opt_log` VALUES ('1124973574228713474', '临时用户', '127.0.0.1', NULL, 'Chrome', 'Windows 10', 'com.youyd.user.controller.UserController.findByCondition()', '[{\"id\":null,\"password\":null,\"userName\":\"\",\"registeredType\":null,\"contactAddress\":null,\"avatar\":null,\"account\":null,\"sex\":null,\"interest\":null,\"fansCount\":null,\"nickName\":null,\"birthday\":null,\"lastDate\":null,\"onlineTime\":null,\"personality\":null,\"followCount\":null,\"phone\":null,\"email\":null,\"status\":null,\"createAt\":null,\"updateAt\":null},{\"pageNum\":1,\"fieldSort\":null,\"searchValue\":null,\"searchSort\":null,\"orderBy\":null,\"pageSize\":10}]', 'io.jsonwebtoken.ExpiredJwtException:--->JWT expired at 2019-05-05T17:44:12+0800. Current time: 2019-05-05T17:46:15+0800', 1557049575745, 1557049575745);
INSERT INTO `ba_opt_log` VALUES ('1124973602104057858', '临时用户', '127.0.0.1', NULL, 'Chrome', 'Windows 10', 'com.youyd.user.controller.UserController.findByCondition()', '[{\"id\":null,\"password\":null,\"userName\":\"\",\"registeredType\":null,\"contactAddress\":null,\"avatar\":null,\"account\":null,\"sex\":null,\"interest\":null,\"fansCount\":null,\"nickName\":null,\"birthday\":null,\"lastDate\":null,\"onlineTime\":null,\"personality\":null,\"followCount\":null,\"phone\":null,\"email\":null,\"status\":null,\"createAt\":null,\"updateAt\":null},{\"pageNum\":1,\"fieldSort\":null,\"searchValue\":null,\"searchSort\":null,\"orderBy\":null,\"pageSize\":10}]', 'io.jsonwebtoken.ExpiredJwtException:--->JWT expired at 2019-05-05T17:44:12+0800. Current time: 2019-05-05T17:46:22+0800', 1557049582381, 1557049582381);

-- ----------------------------
-- Table structure for my_dept
-- ----------------------------
DROP TABLE IF EXISTS `my_dept`;
CREATE TABLE `my_dept`  (
  `deptno` int(11) NOT NULL,
  `dname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `loc` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`deptno`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of my_dept
-- ----------------------------
INSERT INTO `my_dept` VALUES (10, '教研部', '北京');
INSERT INTO `my_dept` VALUES (20, '学工部', '上海');
INSERT INTO `my_dept` VALUES (30, '销售部', '广州');
INSERT INTO `my_dept` VALUES (40, '财务部', '武汉');

-- ----------------------------
-- Table structure for my_emp
-- ----------------------------
DROP TABLE IF EXISTS `my_emp`;
CREATE TABLE `my_emp`  (
  `empno` int(11) NOT NULL,
  `ename` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `jo_b` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `mgr` int(11) NULL DEFAULT NULL,
  `hiredate` date NULL DEFAULT NULL,
  `sal` decimal(7, 2) NULL DEFAULT NULL,
  `COMM` decimal(7, 2) NULL DEFAULT NULL,
  `deptno` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`empno`) USING BTREE,
  INDEX `fk_emp`(`mgr`) USING BTREE,
  INDEX `emp_deptno`(`deptno`) USING BTREE,
  CONSTRAINT `emp_deptno` FOREIGN KEY (`deptno`) REFERENCES `my_dept` (`deptno`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_emp` FOREIGN KEY (`mgr`) REFERENCES `my_emp` (`empno`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of my_emp
-- ----------------------------
INSERT INTO `my_emp` VALUES (1001, '甘宁', '文员', 1013, '2000-12-17', 8000.00, NULL, 20);
INSERT INTO `my_emp` VALUES (1002, '黛绮丝', '销售员', 1006, '2001-02-20', 16000.00, 3000.00, 30);
INSERT INTO `my_emp` VALUES (1003, '殷天正', '销售员', 1006, '2001-02-22', 12500.00, 5000.00, 30);
INSERT INTO `my_emp` VALUES (1004, '半混', '经理', 1009, '2001-04-02', 29750.00, NULL, 30);
INSERT INTO `my_emp` VALUES (1005, '谢逊', '销售员', 1006, '2001-09-28', 12500.00, 14000.00, 10);
INSERT INTO `my_emp` VALUES (1006, '关羽', '经理', 1009, '2001-05-01', 28500.00, NULL, 30);
INSERT INTO `my_emp` VALUES (1007, '张飞', '经理', 1009, '2001-09-01', 24500.00, NULL, 10);
INSERT INTO `my_emp` VALUES (1008, '半混', '分析师', 1004, '2007-04-19', 30000.00, NULL, 20);
INSERT INTO `my_emp` VALUES (1009, '曾阿牛', '董事长', NULL, '2001-11-17', 50000.00, NULL, 10);
INSERT INTO `my_emp` VALUES (1010, '韦一笑', '销售员', 1006, '2001-09-08', 15000.00, 0.00, 30);
INSERT INTO `my_emp` VALUES (1011, '周泰', '文员', 1008, '2007-05-23', 11000.00, NULL, 20);
INSERT INTO `my_emp` VALUES (1012, '程普', '文员', 1006, '2001-12-03', 9500.00, NULL, 30);
INSERT INTO `my_emp` VALUES (1013, '庞统', '分析师', 1004, '2001-12-03', 30000.00, 5845.00, 20);

-- ----------------------------
-- Table structure for my_user
-- ----------------------------
DROP TABLE IF EXISTS `my_user`;
CREATE TABLE `my_user`  (
  `id` decimal(65, 30) NOT NULL COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` decimal(65, 30) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of my_user
-- ----------------------------
INSERT INTO `my_user` VALUES (1.000000000000000000000000000000, '张三', 18.000000000000000000000000000000);
INSERT INTO `my_user` VALUES (2.000000000000000000000000000000, '李四', 20.000000000000000000000000000000);
INSERT INTO `my_user` VALUES (3.000000000000000000000000000000, '王五', 30.000000000000000000000000000000);
INSERT INTO `my_user` VALUES (4.000000000000000000000000000000, '张三', 18.000000000000000000000000000000);
INSERT INTO `my_user` VALUES (5.000000000000000000000000000000, '王五', 19.000000000000000000000000000000);
INSERT INTO `my_user` VALUES (6.000000000000000000000000000000, '张三', 20.000000000000000000000000000000);

-- ----------------------------
-- Table structure for tc_tweets
-- ----------------------------
DROP TABLE IF EXISTS `tc_tweets`;
CREATE TABLE `tc_tweets`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '吐槽表ID',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '吐槽内容',
  `user_id` int(20) NOT NULL COMMENT '发布人ID',
  `nick_name` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发布人昵称',
  `visits_count` bigint(255) NULL DEFAULT NULL COMMENT '浏览量',
  `thumb_up_count` bigint(255) NULL DEFAULT NULL COMMENT '点赞数',
  `share_count` bigint(255) NULL DEFAULT NULL COMMENT '分享数',
  `reply_count` bigint(255) NULL DEFAULT NULL COMMENT '回复数',
  `is_visible` int(1) NULL DEFAULT NULL COMMENT '是否可见',
  `update_at` bigint(20) NULL DEFAULT NULL COMMENT '更新时间',
  `create_at` bigint(20) NULL DEFAULT NULL COMMENT '发布日期(创建时间)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '交流版块/吐槽，微博' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for tc_tweets_comment
-- ----------------------------
DROP TABLE IF EXISTS `tc_tweets_comment`;
CREATE TABLE `tc_tweets_comment`  (
  `id` int(20) NOT NULL COMMENT '吐槽评论ID',
  `content` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评论内容',
  `user_id` int(20) NOT NULL COMMENT '评论人ID',
  `nick_name` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评论人昵称',
  `thumb_up_count` bigint(255) NULL DEFAULT NULL COMMENT '点赞数',
  `reply_count` bigint(20) NULL DEFAULT NULL COMMENT '回复数',
  `is_visible` int(1) NULL DEFAULT NULL COMMENT '是否可见',
  `update_at` bigint(20) NULL DEFAULT NULL COMMENT '更新时间',
  `tweets_id` int(20) NOT NULL COMMENT '吐槽表id',
  `parent_id` int(20) NULL DEFAULT NULL COMMENT '父级评论',
  `create_at` bigint(20) NULL DEFAULT NULL COMMENT '发布日期(创建时间)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '交流版块/吐槽-评论' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for us_menu
-- ----------------------------
DROP TABLE IF EXISTS `us_menu`;
CREATE TABLE `us_menu`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单表主键',
  `name` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `parent_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父资源id',
  `component` varchar(90) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'vue组件名称/名称必须真实存在',
  `icon` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '路径',
  `status` int(1) NULL DEFAULT NULL COMMENT '状态（禁用：0，启用：1）',
  `sort` int(30) NULL DEFAULT NULL COMMENT '排序',
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create_at` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_at` bigint(20) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户板块/菜单' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of us_menu
-- ----------------------------
INSERT INTO `us_menu` VALUES ('0', '平台', '', '/', 'dashboard-line', 'asd', 0, 0, '平台', 1555773930915, 1555773930915);
INSERT INTO `us_menu` VALUES ('1119533521851559938', '首页', '0', '/', 'dashboard-line', 'asd', 0, 0, '首页', 1555773930915, 1555773930915);
INSERT INTO `us_menu` VALUES ('11195335218515599381', '首页1', '1119533521851559938', '/', '', 'asd', 0, 0, '首页', 1555773930915, 1555773930915);
INSERT INTO `us_menu` VALUES ('1119533521851559939', '系统管理', '0', '/', 'thumb-down-line', 'asd', 0, 0, '系统管理', 1555773930915, 1555773930915);
INSERT INTO `us_menu` VALUES ('1119625510232195074', '用户管理', '1119533521851559939', NULL, '', '/system/menu', NULL, NULL, '用户的一些配置', 1555774497830, 1555774497830);

-- ----------------------------
-- Table structure for us_role
-- ----------------------------
DROP TABLE IF EXISTS `us_role`;
CREATE TABLE `us_role`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `parent_role_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '父级角色id',
  `role_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `role_desc` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  `role_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色编码',
  `create_at` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_at` bigint(20) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户板块/角色' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of us_role
-- ----------------------------
INSERT INTO `us_role` VALUES ('1119477949450088449', '0', '7', '7', '7', 20190420134837, 20190420134837);
INSERT INTO `us_role` VALUES ('1119477963140296706', '0', '8', '8', '8', 20190420134840, 20190420134840);
INSERT INTO `us_role` VALUES ('1119477979514859522', '0', '9', '9', '9', 20190420134844, 20190420134844);
INSERT INTO `us_role` VALUES ('1119478007188877314', '0', '10', '10', '10', 20190420134850, 20190420134850);
INSERT INTO `us_role` VALUES ('1119478028072316929', '0', '11', '11', '11', 20190420134855, 20190420134855);
INSERT INTO `us_role` VALUES ('1119478294389649409', '0', '11', '11', '11', 20190420134959, 20190420134959);
INSERT INTO `us_role` VALUES ('1119478415353376770', '0', '11', '11', '11', 20190420135028, 20190420135028);
INSERT INTO `us_role` VALUES ('1119478601412702209', '0', '11', '11', '11', 20190420135112, 20190420135112);
INSERT INTO `us_role` VALUES ('1119478701941780482', '0', '11', '11', '11', 20190420135136, 20190420135136);
INSERT INTO `us_role` VALUES ('1119478833819086850', '0', '11', '11', '11', 20190420135208, 20190420135208);

-- ----------------------------
-- Table structure for us_role_resources
-- ----------------------------
DROP TABLE IF EXISTS `us_role_resources`;
CREATE TABLE `us_role_resources`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色菜单表',
  `us_role_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色表_id',
  `us_menu_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单表_id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户板块/角色-菜单表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of us_role_resources
-- ----------------------------
INSERT INTO `us_role_resources` VALUES ('1', '1', '1');

-- ----------------------------
-- Table structure for us_user
-- ----------------------------
DROP TABLE IF EXISTS `us_user`;
CREATE TABLE `us_user`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
  `account` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账号',
  `nick_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `sex` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '性别（1：男，2：女）',
  `birthday` timestamp(0) NULL DEFAULT NULL COMMENT '出生年月日',
  `avatar` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'E-Mail',
  `online_time` bigint(20) NULL DEFAULT NULL COMMENT '在线时长（分钟）',
  `interest` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '兴趣',
  `personality` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '个性',
  `fans_count` int(20) NULL DEFAULT NULL COMMENT '粉丝数',
  `follow_count` int(20) NULL DEFAULT NULL COMMENT '关注数',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机',
  `contact_address` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系地址',
  `registered_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '注册类型/方式',
  `status` int(1) NULL DEFAULT NULL COMMENT '是否锁定(0:未锁定,1已锁定)',
  `create_at` bigint(20) NULL DEFAULT NULL COMMENT '注册日期',
  `update_at` bigint(20) NULL DEFAULT NULL COMMENT '修改日期',
  `last_date` bigint(20) NULL DEFAULT NULL COMMENT '最后登陆日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户板块/用户' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of us_user
-- ----------------------------
INSERT INTO `us_user` VALUES ('1', 'admin', '管理员', 'ZH001', '$2a$10$fJzQj58QQrNFCbE7xGVULO/Mg6ziERy3MyoIUVdeOwetSO1juQORC', '男', '2018-11-28 12:57:47', '头像是dd', '121@qq.com', 27, '150', NULL, 1, 0, '15866969696', '山东省潍坊市昌邑市', '', 0, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for us_user_role
-- ----------------------------
DROP TABLE IF EXISTS `us_user_role`;
CREATE TABLE `us_user_role`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户/角色中间表',
  `us_user_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户表id',
  `us_role_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色表id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户板块/用户-角色关联表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of us_user_role
-- ----------------------------
INSERT INTO `us_user_role` VALUES ('1', '1', '1');

SET FOREIGN_KEY_CHECKS = 1;
