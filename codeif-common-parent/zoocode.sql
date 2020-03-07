/*
 Navicat Premium Data Transfer

 Source Server         : Aliyun
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : rm-wz95iq4b1998037si9o.mysql.rds.aliyuncs.com:3306
 Source Schema         : youyd

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 07/03/2020 12:40:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ar_article
-- ----------------------------
DROP TABLE IF EXISTS `ar_article`;
CREATE TABLE `ar_article` (
  `id` varchar(20) NOT NULL,
  `create_at` bigint(20) DEFAULT NULL,
  `update_at` bigint(20) DEFAULT NULL,
  `comment` int(11) DEFAULT NULL,
  `content` text NOT NULL,
  `description` varchar(500) NOT NULL,
  `image` varchar(200) DEFAULT NULL,
  `importance` float DEFAULT NULL,
  `is_public` int(11) DEFAULT NULL,
  `is_top` int(11) DEFAULT NULL,
  `keywords` varchar(200) DEFAULT NULL,
  `origin` int(11) NOT NULL,
  `review_state` int(11) DEFAULT NULL,
  `title` varchar(50) NOT NULL,
  `type` int(11) DEFAULT NULL,
  `upvote` int(11) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  `user_id` varchar(20) DEFAULT NULL,
  `visits` int(11) DEFAULT NULL,
  `category_id` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf16 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of ar_article
-- ----------------------------
BEGIN;
INSERT INTO `ar_article` VALUES ('1235972122313101312', 1583513691867, 1583516933130, 0, '666', '555', 'http://vue-admin-guoguang.oss-cn-shanghai.aliyuncs.com/QQ20190621-163623%402x.png?Expires=264555477991&OSSAccessKeyId=LTAI4Fj5rWx5qpxXZk89QCdh&Signature=JHVxOIZvEHttBGohkEGIAYK3mOc%3D', 0, 0, 0, NULL, 1, 2, '5', 1, 0, NULL, NULL, 0, '1');
COMMIT;

-- ----------------------------
-- Table structure for ar_article_tags
-- ----------------------------
DROP TABLE IF EXISTS `ar_article_tags`;
CREATE TABLE `ar_article_tags` (
  `article_id` varchar(20) NOT NULL,
  `tags_id` varchar(20) NOT NULL,
  PRIMARY KEY (`article_id`,`tags_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf16 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of ar_article_tags
-- ----------------------------
BEGIN;
INSERT INTO `ar_article_tags` VALUES ('1', '1');
INSERT INTO `ar_article_tags` VALUES ('1235972122313101312', '1');
INSERT INTO `ar_article_tags` VALUES ('1235972122313101312', '6');
COMMIT;

-- ----------------------------
-- Table structure for ar_category
-- ----------------------------
DROP TABLE IF EXISTS `ar_category`;
CREATE TABLE `ar_category` (
  `id` varchar(20) NOT NULL,
  `create_at` bigint(20) DEFAULT NULL,
  `update_at` bigint(20) DEFAULT NULL,
  `name` varchar(20) NOT NULL,
  `parent_id` varchar(20) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `summary` varchar(200) NOT NULL,
  `user_id` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf16 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of ar_category
-- ----------------------------
BEGIN;
INSERT INTO `ar_category` VALUES ('1', 1583380077, 1583380077, '技术', '0', 1, '技术', NULL);
COMMIT;

-- ----------------------------
-- Table structure for ar_comment
-- ----------------------------
DROP TABLE IF EXISTS `ar_comment`;
CREATE TABLE `ar_comment` (
  `id` char(1) NOT NULL,
  `create_at` bigint(20) DEFAULT NULL,
  `update_at` bigint(20) DEFAULT NULL,
  `article_id` varchar(20) NOT NULL,
  `content` varchar(200) NOT NULL,
  `parent_id` varchar(20) DEFAULT NULL,
  `publish_date` varchar(13) DEFAULT NULL,
  `user_id` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf16 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for ar_tags
-- ----------------------------
DROP TABLE IF EXISTS `ar_tags`;
CREATE TABLE `ar_tags` (
  `id` varchar(20) NOT NULL,
  `create_at` bigint(20) DEFAULT NULL,
  `update_at` bigint(20) DEFAULT NULL,
  `color` varchar(30) DEFAULT NULL,
  `description` varchar(200) NOT NULL,
  `icon` varchar(50) DEFAULT NULL,
  `name` varchar(20) NOT NULL,
  `slug` varchar(20) NOT NULL,
  `state` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf16 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of ar_tags
-- ----------------------------
BEGIN;
INSERT INTO `ar_tags` VALUES ('1', 1583380077, 1583504578341, 'rgba(144, 238, 144, 1)', 'Java1', '0', 'Java1', 'Java', 1);
INSERT INTO `ar_tags` VALUES ('2', 1583380077, 1583380077, '#ffbb50', 'LeetCode', 'aa', 'LeetCode', 'LeetCode', 1);
INSERT INTO `ar_tags` VALUES ('3', 1583380077, 1583380077, '#1ac756', 'Python', 'aa', 'Python', 'Python', 1);
INSERT INTO `ar_tags` VALUES ('4', 1583380077, 1583380077, '#19B5FE', '安全', 'aa', '安全', 'security', 1);
INSERT INTO `ar_tags` VALUES ('5', 1583380077, 1583380077, '#b1a8a8', '2020', 'aa', '2020', '2020', 1);
INSERT INTO `ar_tags` VALUES ('6', 1583380077, 1583380077, '#ff9800', 'Docker', 'aa', 'Docker', 'Docker', 1);
COMMIT;

-- ----------------------------
-- Table structure for ba_dict
-- ----------------------------
DROP TABLE IF EXISTS `ba_dict`;
CREATE TABLE `ba_dict` (
  `id` varchar(20) NOT NULL,
  `create_at` bigint(20) DEFAULT NULL,
  `update_at` bigint(20) DEFAULT NULL,
  `code` varchar(10) NOT NULL,
  `description` varchar(200) NOT NULL,
  `name` varchar(10) NOT NULL,
  `parent_id` varchar(20) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `type` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf16 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for ba_login_log
-- ----------------------------
DROP TABLE IF EXISTS `ba_login_log`;
CREATE TABLE `ba_login_log` (
  `id` varchar(20) NOT NULL,
  `create_at` bigint(20) DEFAULT NULL,
  `update_at` bigint(20) DEFAULT NULL,
  `browser` varchar(50) DEFAULT NULL,
  `client_ip` varchar(20) DEFAULT NULL,
  `os_info` varchar(100) DEFAULT NULL,
  `user_id` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf16 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of ba_login_log
-- ----------------------------
BEGIN;
INSERT INTO `ba_login_log` VALUES ('1235224543237378048', 1583335455120, 1583335455120, 'Chrome 8', '127.0.0.1', 'Mac OS X', '1234533425');
INSERT INTO `ba_login_log` VALUES ('1235225000361988096', 1583335564112, 1583335564112, 'Chrome 8', '127.0.0.1', 'Mac OS X', '1234533425');
INSERT INTO `ba_login_log` VALUES ('1235225207287975936', 1583335613448, 1583335613448, 'Chrome 8', '127.0.0.1', 'Mac OS X', '1234533425');
INSERT INTO `ba_login_log` VALUES ('1235225295682932736', 1583335634522, 1583335634522, 'Chrome 8', '127.0.0.1', 'Mac OS X', '1234533425');
INSERT INTO `ba_login_log` VALUES ('1235226006604877824', 1583335804019, 1583335804019, 'Chrome 8', '127.0.0.1', 'Mac OS X', '1234533425');
INSERT INTO `ba_login_log` VALUES ('1235227058024615936', 1583336054697, 1583336054697, 'Chrome 8', '127.0.0.1', 'Mac OS X', '1234533425');
INSERT INTO `ba_login_log` VALUES ('1235229138504257536', 1583336550722, 1583336550722, 'Chrome 8', '127.0.0.1', 'Mac OS X', '1234533425');
INSERT INTO `ba_login_log` VALUES ('1235597929788608512', 1583424477395, 1583424477395, 'Chrome 8', '127.0.0.1', 'Mac OS X', '1234533425');
INSERT INTO `ba_login_log` VALUES ('1235599001395531776', 1583424732908, 1583424732908, 'Chrome 8', '127.0.0.1', 'Mac OS X', '1234533425');
INSERT INTO `ba_login_log` VALUES ('1235914876501757952', 1583500043396, 1583500043396, 'Chrome 8', '127.0.0.1', 'Mac OS X', '1234533425');
INSERT INTO `ba_login_log` VALUES ('1235914941307949056', 1583500058854, 1583500058854, 'Chrome 8', '127.0.0.1', 'Mac OS X', '1234533425');
INSERT INTO `ba_login_log` VALUES ('1235923884184834048', 1583502190993, 1583502190993, 'Chrome 8', '127.0.0.1', 'Mac OS X', '1234533425');
INSERT INTO `ba_login_log` VALUES ('1235923995577159680', 1583502217560, 1583502217560, 'Chrome 8', '127.0.0.1', 'Mac OS X', '1234533425');
INSERT INTO `ba_login_log` VALUES ('1235924574667935744', 1583502355625, 1583502355625, 'Chrome 8', '127.0.0.1', 'Mac OS X', '1234533425');
INSERT INTO `ba_login_log` VALUES ('1235940108507680768', 1583506059181, 1583506059181, 'Chrome 8', '127.0.0.1', 'Mac OS X', '1234533425');
INSERT INTO `ba_login_log` VALUES ('1236145362025189376', 1583554995406, 1583554995406, 'Chrome 8', '127.0.0.1', 'Mac OS X', '1234533425');
INSERT INTO `ba_login_log` VALUES ('1236148077916721152', 1583555642945, 1583555642945, 'Chrome 8', '127.0.0.1', 'Mac OS X', '1234533425');
COMMIT;

-- ----------------------------
-- Table structure for ba_opt_log
-- ----------------------------
DROP TABLE IF EXISTS `ba_opt_log`;
CREATE TABLE `ba_opt_log` (
  `id` varchar(20) NOT NULL,
  `create_at` bigint(20) DEFAULT NULL,
  `update_at` bigint(20) DEFAULT NULL,
  `browser` varchar(50) DEFAULT NULL,
  `client_ip` varchar(20) DEFAULT NULL,
  `exception_detail` varchar(500) DEFAULT NULL,
  `method` varchar(100) DEFAULT NULL,
  `os_info` varchar(50) DEFAULT NULL,
  `params` varchar(500) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `user_id` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf16 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of ba_opt_log
-- ----------------------------
BEGIN;
INSERT INTO `ba_opt_log` VALUES ('1235230651578454016', 1583336911468, 1583336911468, 'Chrome 8', '127.0.0.1', NULL, 'com.codeif.user.controller.ResourceController.updateByPrimaryKey()', 'Mac OS X', NULL, 3, '1234533425');
INSERT INTO `ba_opt_log` VALUES ('1235605593742512128', 1583426304645, 1583426304645, 'Chrome 8', '127.0.0.1', NULL, 'com.codeif.article.controller.backstage.ArticleController.insertArticle()', 'Mac OS X', NULL, 1, '1234533425');
INSERT INTO `ba_opt_log` VALUES ('1235930716047544320', 1583503819842, 1583503819842, 'Chrome 8', '127.0.0.1', NULL, 'com.codeif.user.controller.ResourceController.insertSelective()', 'Mac OS X', NULL, 3, '1234533425');
INSERT INTO `ba_opt_log` VALUES ('1235930841675337728', 1583503849796, 1583503849796, 'Chrome 8', '127.0.0.1', NULL, 'com.codeif.user.controller.ResourceController.updateByPrimaryKey()', 'Mac OS X', NULL, 3, '1234533425');
INSERT INTO `ba_opt_log` VALUES ('1235931116385472512', 1583503915292, 1583503915292, 'Chrome 8', '127.0.0.1', NULL, 'com.codeif.user.controller.RoleController.updateByPrimaryKey()', 'Mac OS X', NULL, 3, '1234533425');
INSERT INTO `ba_opt_log` VALUES ('1235933650655252480', 1583504519509, 1583504519509, 'Chrome 8', '127.0.0.1', NULL, 'com.codeif.user.controller.ResourceController.insertSelective()', 'Mac OS X', NULL, 3, '1234533425');
INSERT INTO `ba_opt_log` VALUES ('1235933895581634560', 1583504577903, 1583504577903, 'Chrome 8', '127.0.0.1', NULL, 'com.codeif.article.controller.backstage.TagsController.updateTagsById()', 'Mac OS X', NULL, 3, '1234533425');
INSERT INTO `ba_opt_log` VALUES ('1235939324105723904', 1583505872165, 1583505872165, 'Chrome 8', '127.0.0.1', NULL, 'com.codeif.user.controller.ResourceController.insertSelective()', 'Mac OS X', NULL, 3, '1234533425');
INSERT INTO `ba_opt_log` VALUES ('1235948219633111040', 1583507993024, 1583507993024, 'Chrome 8', '127.0.0.1', NULL, 'com.codeif.user.controller.RoleController.updateByPrimaryKey()', 'Mac OS X', NULL, 3, '1234533425');
INSERT INTO `ba_opt_log` VALUES ('1235949721848254464', 1583508351180, 1583508351180, 'Chrome 8', '127.0.0.1', NULL, 'com.codeif.user.controller.RoleController.updateByPrimaryKey()', 'Mac OS X', NULL, 3, '1234533425');
INSERT INTO `ba_opt_log` VALUES ('1235950775960735744', 1583508602500, 1583508602500, 'Chrome 8', '127.0.0.1', NULL, 'com.codeif.user.controller.RoleController.updateByPrimaryKey()', 'Mac OS X', NULL, 3, '1234533425');
INSERT INTO `ba_opt_log` VALUES ('1235954524267614208', 1583509496166, 1583509496166, 'Chrome 8', '127.0.0.1', NULL, 'com.codeif.user.controller.RoleController.updateByPrimaryKey()', 'Mac OS X', NULL, 3, '1234533425');
INSERT INTO `ba_opt_log` VALUES ('1235954990913294336', 1583509607424, 1583509607424, 'Chrome 8', '127.0.0.1', NULL, 'com.codeif.user.controller.RoleController.updateByPrimaryKey()', 'Mac OS X', NULL, 3, '1234533425');
INSERT INTO `ba_opt_log` VALUES ('1235955050745040896', 1583509621688, 1583509621688, 'Chrome 8', '127.0.0.1', NULL, 'com.codeif.user.controller.RoleController.updateByPrimaryKey()', 'Mac OS X', NULL, 3, '1234533425');
INSERT INTO `ba_opt_log` VALUES ('1235955083338977280', 1583509629459, 1583509629459, 'Chrome 8', '127.0.0.1', NULL, 'com.codeif.user.controller.RoleController.updateByPrimaryKey()', 'Mac OS X', NULL, 3, '1234533425');
INSERT INTO `ba_opt_log` VALUES ('1235955152939257856', 1583509646051, 1583509646051, 'Chrome 8', '127.0.0.1', NULL, 'com.codeif.user.controller.RoleController.updateByPrimaryKey()', 'Mac OS X', NULL, 3, '1234533425');
INSERT INTO `ba_opt_log` VALUES ('1235955323613876224', 1583509686745, 1583509686745, 'Chrome 8', '127.0.0.1', NULL, 'com.codeif.user.controller.RoleController.updateByPrimaryKey()', 'Mac OS X', NULL, 3, '1234533425');
INSERT INTO `ba_opt_log` VALUES ('1235955665420292096', 1583509768237, 1583509768237, 'Chrome 8', '127.0.0.1', NULL, 'com.codeif.user.controller.RoleController.updateByPrimaryKey()', 'Mac OS X', NULL, 3, '1234533425');
INSERT INTO `ba_opt_log` VALUES ('1235955814137729024', 1583509803694, 1583509803694, 'Chrome 8', '127.0.0.1', NULL, 'com.codeif.user.controller.RoleController.updateByPrimaryKey()', 'Mac OS X', NULL, 3, '1234533425');
INSERT INTO `ba_opt_log` VALUES ('1235955928398958592', 1583509830938, 1583509830938, 'Chrome 8', '127.0.0.1', NULL, 'com.codeif.user.controller.RoleController.updateByPrimaryKey()', 'Mac OS X', NULL, 3, '1234533425');
INSERT INTO `ba_opt_log` VALUES ('1235956152097968128', 1583509884271, 1583509884271, 'Chrome 8', '127.0.0.1', NULL, 'com.codeif.user.controller.RoleController.updateByPrimaryKey()', 'Mac OS X', NULL, 3, '1234533425');
INSERT INTO `ba_opt_log` VALUES ('1235956252937424896', 1583509908313, 1583509908313, 'Chrome 8', '127.0.0.1', 'org.springframework.orm.jpa.JpaSystemException:--->transaction timeout expired; nested exception is org.hibernate.TransactionException: transaction timeout expired', 'com.codeif.user.controller.RoleController.updateByPrimaryKey()', 'Mac OS X', NULL, NULL, '临时用户');
INSERT INTO `ba_opt_log` VALUES ('1235956323380760576', 1583509925108, 1583509925108, 'Chrome 8', '127.0.0.1', NULL, 'com.codeif.user.controller.RoleController.updateByPrimaryKey()', 'Mac OS X', NULL, 3, '1234533425');
INSERT INTO `ba_opt_log` VALUES ('1235958413876400128', 1583510423520, 1583510423520, 'Chrome 8', '127.0.0.1', NULL, 'com.codeif.user.controller.RoleController.updateByPrimaryKey()', 'Mac OS X', NULL, 3, '1234533425');
INSERT INTO `ba_opt_log` VALUES ('1235958517186301952', 1583510448152, 1583510448152, 'Chrome 8', '127.0.0.1', NULL, 'com.codeif.user.controller.RoleController.updateByPrimaryKey()', 'Mac OS X', NULL, 3, '1234533425');
INSERT INTO `ba_opt_log` VALUES ('1235958614196359168', 1583510471281, 1583510471281, 'Chrome 8', '127.0.0.1', NULL, 'com.codeif.user.controller.RoleController.deleteByIds()', 'Mac OS X', NULL, 2, '1234533425');
INSERT INTO `ba_opt_log` VALUES ('1235958737907355648', 1583510500774, 1583510500774, 'Chrome 8', '127.0.0.1', NULL, 'com.codeif.user.controller.UserController.updateByPrimaryKey()', 'Mac OS X', NULL, 3, '1234533425');
INSERT INTO `ba_opt_log` VALUES ('1235958966639529984', 1583510555309, 1583510555309, 'Chrome 8', '127.0.0.1', NULL, 'com.codeif.article.controller.backstage.ArticleController.insertArticle()', 'Mac OS X', NULL, 1, '1234533425');
INSERT INTO `ba_opt_log` VALUES ('1235959955694161920', 1583510791115, 1583510791115, 'Chrome 8', '127.0.0.1', NULL, 'com.codeif.article.controller.backstage.ArticleController.updateByPrimaryKeySelective()', 'Mac OS X', NULL, 3, '1234533425');
INSERT INTO `ba_opt_log` VALUES ('1235962319901691904', 1583511354788, 1583511354788, 'Chrome 8', '127.0.0.1', NULL, 'com.codeif.article.controller.backstage.ArticleController.updateByPrimaryKeySelective()', 'Mac OS X', NULL, 3, '1234533425');
INSERT INTO `ba_opt_log` VALUES ('1235962347655401472', 1583511361407, 1583511361407, 'Chrome 8', '127.0.0.1', 'org.springframework.orm.jpa.JpaSystemException:--->transaction timeout expired; nested exception is org.hibernate.TransactionException: transaction timeout expired', 'com.codeif.article.controller.backstage.ArticleController.updateByPrimaryKeySelective()', 'Mac OS X', NULL, NULL, '临时用户');
INSERT INTO `ba_opt_log` VALUES ('1235962359269429248', 1583511364175, 1583511364175, 'Chrome 8', '127.0.0.1', NULL, 'com.codeif.article.controller.backstage.ArticleController.updateByPrimaryKeySelective()', 'Mac OS X', NULL, 3, '1234533425');
INSERT INTO `ba_opt_log` VALUES ('1235962724756885504', 1583511451315, 1583511451315, 'Chrome 8', '127.0.0.1', NULL, 'com.codeif.article.controller.backstage.ArticleController.insertArticle()', 'Mac OS X', NULL, 1, '1234533425');
INSERT INTO `ba_opt_log` VALUES ('1235963141901389824', 1583511550770, 1583511550770, 'Chrome 8', '127.0.0.1', NULL, 'com.codeif.article.controller.backstage.ArticleController.insertArticle()', 'Mac OS X', NULL, 1, '1234533425');
INSERT INTO `ba_opt_log` VALUES ('1235966237641674752', 1583512288849, 1583512288849, 'Chrome 8', '127.0.0.1', NULL, 'com.codeif.article.controller.backstage.ArticleController.insertArticle()', 'Mac OS X', NULL, 1, '1234533425');
INSERT INTO `ba_opt_log` VALUES ('1235969485802967040', 1583513063273, 1583513063273, 'Chrome 8', '127.0.0.1', NULL, 'com.codeif.article.controller.backstage.ArticleController.insertArticle()', 'Mac OS X', NULL, 1, '1234533425');
INSERT INTO `ba_opt_log` VALUES ('1235969768088014848', 1583513130576, 1583513130576, 'Chrome 8', '127.0.0.1', NULL, 'com.codeif.article.controller.backstage.ArticleController.insertArticle()', 'Mac OS X', NULL, 1, '1234533425');
INSERT INTO `ba_opt_log` VALUES ('1235970090332196864', 1583513207401, 1583513207401, 'Chrome 8', '127.0.0.1', NULL, 'com.codeif.article.controller.backstage.ArticleController.insertArticle()', 'Mac OS X', NULL, 1, '1234533425');
INSERT INTO `ba_opt_log` VALUES ('1235985707185606656', 1583516930739, 1583516930739, 'Chrome 8', '127.0.0.1', NULL, 'com.codeif.article.controller.backstage.ArticleController.updateByPrimaryKeySelective()', 'Mac OS X', NULL, 3, '1234533425');
INSERT INTO `ba_opt_log` VALUES ('1235985861523410944', 1583516967550, 1583516967550, 'Chrome 8', '127.0.0.1', NULL, 'com.codeif.article.controller.backstage.ArticleController.updateByPrimaryKeySelective()', 'Mac OS X', NULL, 3, '1234533425');
INSERT INTO `ba_opt_log` VALUES ('1235986285034868736', 1583517068524, 1583517068524, 'Chrome 8', '127.0.0.1', NULL, 'com.codeif.article.controller.backstage.ArticleController.updateByPrimaryKeySelective()', 'Mac OS X', NULL, 3, '1234533425');
INSERT INTO `ba_opt_log` VALUES ('1236145522599923712', 1583555033717, 1583555033717, 'Chrome 8', '127.0.0.1', NULL, 'com.codeif.user.controller.ResourceController.deleteByIds()', 'Mac OS X', NULL, 2, '1234533425');
INSERT INTO `ba_opt_log` VALUES ('1236145628891975680', 1583555059059, 1583555059059, 'Chrome 8', '127.0.0.1', NULL, 'com.codeif.user.controller.RoleController.updateByPrimaryKey()', 'Mac OS X', NULL, 3, '1234533425');
COMMIT;

-- ----------------------------
-- Table structure for us_resource
-- ----------------------------
DROP TABLE IF EXISTS `us_resource`;
CREATE TABLE `us_resource` (
  `id` varchar(20) NOT NULL,
  `create_at` bigint(20) DEFAULT NULL,
  `update_at` bigint(20) DEFAULT NULL,
  `code` varchar(20) DEFAULT NULL,
  `component` varchar(30) DEFAULT NULL,
  `description` varchar(200) NOT NULL,
  `icon` varchar(50) DEFAULT NULL,
  `is_hidden` int(11) DEFAULT NULL,
  `method` varchar(20) DEFAULT NULL,
  `name` varchar(20) NOT NULL,
  `parent_id` varchar(20) DEFAULT NULL,
  `path` varchar(100) DEFAULT NULL,
  `sort` float DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  `url` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf16 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of us_resource
-- ----------------------------
BEGIN;
INSERT INTO `us_resource` VALUES ('0', 1555773930915, 1555773930915, '::', '/', '平台', 'dashboard', 0, 'GET', '平台', '', 'asd', 0, '1', 'su/user');
INSERT INTO `us_resource` VALUES ('1119533521851559939', 1555773930915, 1555773930915, '::', 'Layout', '系统管理', 'shezhi', 0, 'GET', '系统管理', '0', '/system', 6, '1', 'su/user');
INSERT INTO `us_resource` VALUES ('1136301687252221954', 1559750408205, 1559750408205, 'user:list', 'system/user/user', '用户管理', 'peoples', 0, 'GET', '用户管理', '1119533521851559939', 'user', 6.4, '2', '/su/user');
INSERT INTO `us_resource` VALUES ('1136301874045550593', 1559750452897, 1559750452897, 'role:list', 'system/user/role', '角色管理', 'jiaoseguanli', 0, 'GET', '角色管理', '1119533521851559939', 'role', 6.3, '2', '/su/role');
INSERT INTO `us_resource` VALUES ('1136302015179685890', 1559750486546, 1559750486546, 'resource:list', 'system/user/menu', '菜单管理', 'quanxianshezhi', 0, 'GET', '菜单管理', '1119533521851559939', 'menu', 6.2, '2', '/su/resource');
INSERT INTO `us_resource` VALUES ('1136504053649920002', 1559798655967, 1559798655967, '::', 'system/dict/index', '字典管理', 'dictionary', 0, 'GET', '字典管理', '1119533521851559939', 'dict', 6.1, '1', 'su/user');
INSERT INTO `us_resource` VALUES ('1136506938328633345', 1559799344032, 1559799344032, 'tree:list', 'system/dict/dict', '树形字典', '', 0, 'GET', '树形字典', '1136504053649920002', 'dict', 6.3, '2', '/ba/dict/tree,/ba/dict/type');
INSERT INTO `us_resource` VALUES ('1136507035426770945', 1559799367182, 1559799367182, 'dict:list', 'system/dict/type', '类型管理', '', 0, 'GET', '类型管理', '1136504053649920002', 'type', 6.2, '2', '/ba/dict');
INSERT INTO `us_resource` VALUES ('1136517295097008129', 1559801813279, 1559801813279, '::', 'Layout', '文章', 'list', 0, 'GET', '文章', '0', '/article', 1, '1', 'su/user');
INSERT INTO `us_resource` VALUES ('1136517500710178817', 1559801862300, 1559801862300, 'article:create', 'article/create', '创建文章', 'edit', 0, 'POST', '创建文章', '1136517295097008129', 'create', 1.1, '2', 'article,ddadasd');
INSERT INTO `us_resource` VALUES ('1136517638161715201', 1559801895071, 1559801895071, 'article:category', 'article/category', '文章分离', 'table', 0, 'GET', '文章分类', '1136517295097008129', 'category', 1.2, '2', '/ar/category');
INSERT INTO `us_resource` VALUES ('1136520792991428609', 1559802646945, 1559802646945, 'article:edit', 'article/edit', '编辑文章', 'edit', 1, 'GET', '编辑文章', '1136517295097008129', 'edit/:id', 1.3, '2', '/ar/article/*');
INSERT INTO `us_resource` VALUES ('1136521081395965954', 1559802716002, 1559802716002, 'article:list', 'article/list', '文章列表', 'list', 0, 'GET', '文章列表', '1136517295097008129', 'list', 1.4, '2', '/ar/article');
INSERT INTO `us_resource` VALUES ('1136521380156239873', 1559802787231, 1559802787231, '::', 'Layout', '日志', 'logs', 0, 'GET', '日志', '0', '/log', 2, '1', 'su/user');
INSERT INTO `us_resource` VALUES ('1136521574579007490', 1559802833586, 1583336911938, 'loginLog:list', 'system/log/loginLog', '登录日志', 'logs', 0, 'GET', '登录日志', '1136521380156239873', 'LoginLog', 2.1, '2', '/ba/loginLog');
INSERT INTO `us_resource` VALUES ('1136521751205343233', 1559802833586, 1136521380156239873, 'optLog:list', 'system/log/operationLog', '操作日志', 'denglurizhi-', 0, 'GET', '操作日志', '1136521380156239873', 'operationLog', 2, '2', '/ba/optLog');
INSERT INTO `us_resource` VALUES ('1136523115637600257', 1559803201002, 1559803201002, '::', 'Layout', '探点管理', 'denglurizhi-', 0, 'GET', '探点管理', '0', '/tweets', 4, '1', 'su/user');
INSERT INTO `us_resource` VALUES ('1136523363437080578', 1559803260083, 1559803260083, '::', 'Layout', '爬虫', 'shuchongic', 0, 'GET', '爬虫', '0', '/reptile', 5, '1', 'su/user');
INSERT INTO `us_resource` VALUES ('1136524921159643138', 1559803631473, 1559803631473, 'asdad', 'tweets/tweet', '探点', 'wechat', 0, 'LETE', '探点', '1136523115637600257', 'asdasasd', 4.1, '2', 'ddddddasdasd');
INSERT INTO `us_resource` VALUES ('1170993261294723073', 1568021524152, 1568021524152, 'asdasd', '', 'asdasd', '', 0, 'GET', '查看', '1136302015179685890', '', 0, '3', '/su/resource/*');
INSERT INTO `us_resource` VALUES ('1171110958535901186', 1568049585347, 1568049585347, 'xxxx', '', '66666', '', 0, 'PUT', '修改', '1136302015179685890', '', 1, '3', '/su/resource');
INSERT INTO `us_resource` VALUES ('1171111842829402114', 1568049796193, 1568049796193, 'dsas', '', '', '', 0, 'POST', '添加', '1136302015179685890', '', 2, '3', '/su/resource');
INSERT INTO `us_resource` VALUES ('1171112609820803073', 1568049979058, 1568049979058, 'tgd', '', '', '', 0, 'DELETE', '删除', '1136302015179685890', '', 3, '3', '/su/resource');
INSERT INTO `us_resource` VALUES ('1171115430716735489', 1568050651611, 1568050651611, 'qqq', '', '', '', 0, 'GET', '编辑', '1136301874045550593', '', 0, '3', '/su/role/*');
INSERT INTO `us_resource` VALUES ('1171134530197225474', 1568055205277, 1568055205278, 'adfgghfgh', '', '', '', 0, 'PUT', '修改', '1136301874045550593', '', 1, '3', '/su/role');
INSERT INTO `us_resource` VALUES ('1171310771613302786', 1568097224511, 1568097224511, 'ttr', '', '', '', 0, 'GET', '查看', '1136301687252221954', '', 0, '3', '/su/user/*');
INSERT INTO `us_resource` VALUES ('1171310964719058945', 1568097270552, 1568097270552, 'uui', '', '', '', 0, 'PUT', '修改', '1136301687252221954', '', 1, '3', '/su/user');
INSERT INTO `us_resource` VALUES ('1174321702530228225', 1568815086383, 1568815086383, 'article:categroy:lis', '', '', '', 0, 'GET', '查看', '1136517638161715201', '', 0, '3', '/article/category/*');
INSERT INTO `us_resource` VALUES ('1174322046597373954', 1568815168414, 1568815168414, 'article:category:edi', '', '', '', 0, 'PUT', '编辑', '1136517638161715201', '', 0, '3', '/ararticle/category/*');
INSERT INTO `us_resource` VALUES ('1174322346796294146', 1568815239987, 1568815239987, 'article:edit', '', '', '', 0, 'GET', '查看', '1136521081395965954', '', 0, '3', '/ar/article/*');
INSERT INTO `us_resource` VALUES ('1174322945713545218', 1568815382780, 1568815382780, 'article', '', '', '', 0, 'POST', '发布', '1136517500710178817', '', 0, '3', '/ar/article');
INSERT INTO `us_resource` VALUES ('1174323870792458241', 1568815603337, 1568815603337, 'article:delete', '', '删除', '', 0, 'DELETE', '删除', '1136521081395965954', '', 1, '3', '/ar/article');
INSERT INTO `us_resource` VALUES ('1215267273737113600', 1578577271221, 1578577271221, 'tag:list', 'article/tags', '标签', 'wangluotiaodushitu', 0, 'GET', '标签管理', '1136517295097008129', 'GET', 2, '2', '/ar/tags');
INSERT INTO `us_resource` VALUES ('1235930718295691264', 1583503820372, 1583503850045, 'tag:edit', '/aa', '', 'people', 0, 'GET', '编辑', '1215267273737113600', '', 0, '3', '/ar/tags/*');
INSERT INTO `us_resource` VALUES ('1235933651791908864', 1583504519780, 1583504519780, 'tags:save', '', '', '', 0, 'PUT', '保存', '1215267273737113600', '', 0, '3', '/ar/tags');
INSERT INTO `us_resource` VALUES ('1235939325175271424', 1583505872420, 1583505872420, 'user:roles', '', '', '', 0, 'GET', '查看角色', '1136301687252221954', '', 0, '3', '/su/user/roles/*');
COMMIT;

-- ----------------------------
-- Table structure for us_role
-- ----------------------------
DROP TABLE IF EXISTS `us_role`;
CREATE TABLE `us_role` (
  `id` varchar(20) NOT NULL,
  `create_at` bigint(20) DEFAULT NULL,
  `update_at` bigint(20) DEFAULT NULL,
  `role_code` varchar(20) NOT NULL,
  `role_desc` varchar(200) DEFAULT NULL,
  `role_name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf16 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of us_role
-- ----------------------------
BEGIN;
INSERT INTO `us_role` VALUES ('1119477949450088449', 1583246987537, 1583510426731, '12', '管理员', '管理员');
COMMIT;

-- ----------------------------
-- Table structure for us_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `us_role_resource`;
CREATE TABLE `us_role_resource` (
  `role_id` varchar(20) NOT NULL,
  `resource_id` varchar(20) NOT NULL,
  PRIMARY KEY (`role_id`,`resource_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf16 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of us_role_resource
-- ----------------------------
BEGIN;
INSERT INTO `us_role_resource` VALUES ('1119477949450088449', '1119533521851559939');
INSERT INTO `us_role_resource` VALUES ('1119477949450088449', '1136301687252221954');
INSERT INTO `us_role_resource` VALUES ('1119477949450088449', '1136301874045550593');
INSERT INTO `us_role_resource` VALUES ('1119477949450088449', '1136302015179685890');
INSERT INTO `us_role_resource` VALUES ('1119477949450088449', '1136504053649920002');
INSERT INTO `us_role_resource` VALUES ('1119477949450088449', '1136506938328633345');
INSERT INTO `us_role_resource` VALUES ('1119477949450088449', '1136507035426770945');
INSERT INTO `us_role_resource` VALUES ('1119477949450088449', '1136517295097008129');
INSERT INTO `us_role_resource` VALUES ('1119477949450088449', '1136517500710178817');
INSERT INTO `us_role_resource` VALUES ('1119477949450088449', '1136517638161715201');
INSERT INTO `us_role_resource` VALUES ('1119477949450088449', '1136520792991428609');
INSERT INTO `us_role_resource` VALUES ('1119477949450088449', '1136521081395965954');
INSERT INTO `us_role_resource` VALUES ('1119477949450088449', '1136521380156239873');
INSERT INTO `us_role_resource` VALUES ('1119477949450088449', '1136521574579007490');
INSERT INTO `us_role_resource` VALUES ('1119477949450088449', '1136521751205343233');
INSERT INTO `us_role_resource` VALUES ('1119477949450088449', '1136523115637600257');
INSERT INTO `us_role_resource` VALUES ('1119477949450088449', '1136523363437080578');
INSERT INTO `us_role_resource` VALUES ('1119477949450088449', '1136524921159643138');
INSERT INTO `us_role_resource` VALUES ('1119477949450088449', '1170993261294723073');
INSERT INTO `us_role_resource` VALUES ('1119477949450088449', '1171110958535901186');
INSERT INTO `us_role_resource` VALUES ('1119477949450088449', '1171111842829402114');
INSERT INTO `us_role_resource` VALUES ('1119477949450088449', '1171112609820803073');
INSERT INTO `us_role_resource` VALUES ('1119477949450088449', '1171115430716735489');
INSERT INTO `us_role_resource` VALUES ('1119477949450088449', '1171134530197225474');
INSERT INTO `us_role_resource` VALUES ('1119477949450088449', '1171310771613302786');
INSERT INTO `us_role_resource` VALUES ('1119477949450088449', '1171310964719058945');
INSERT INTO `us_role_resource` VALUES ('1119477949450088449', '1174321702530228225');
INSERT INTO `us_role_resource` VALUES ('1119477949450088449', '1174322046597373954');
INSERT INTO `us_role_resource` VALUES ('1119477949450088449', '1174322346796294146');
INSERT INTO `us_role_resource` VALUES ('1119477949450088449', '1174322945713545218');
INSERT INTO `us_role_resource` VALUES ('1119477949450088449', '1174323870792458241');
INSERT INTO `us_role_resource` VALUES ('1119477949450088449', '1215267273737113600');
INSERT INTO `us_role_resource` VALUES ('1119477949450088449', '1235930718295691264');
INSERT INTO `us_role_resource` VALUES ('1119477949450088449', '1235933651791908864');
INSERT INTO `us_role_resource` VALUES ('1119477949450088449', '1235939325175271424');
COMMIT;

-- ----------------------------
-- Table structure for us_user
-- ----------------------------
DROP TABLE IF EXISTS `us_user`;
CREATE TABLE `us_user` (
  `id` varchar(20) NOT NULL,
  `create_at` bigint(20) DEFAULT NULL,
  `update_at` bigint(20) DEFAULT NULL,
  `account` varchar(20) NOT NULL,
  `avatar` varchar(300) DEFAULT NULL,
  `birthday` bigint(20) DEFAULT NULL,
  `contact_address` varchar(200) DEFAULT NULL,
  `email` varchar(30) NOT NULL,
  `fans_count` int(11) DEFAULT NULL,
  `follow_count` int(11) DEFAULT NULL,
  `interest` varchar(30) DEFAULT NULL,
  `last_date` bigint(20) DEFAULT NULL,
  `nick_name` varchar(80) NOT NULL,
  `online_time` bigint(20) DEFAULT NULL,
  `password` varchar(100) NOT NULL,
  `personality` varchar(20) DEFAULT NULL,
  `phone` varchar(15) NOT NULL,
  `registered_type` varchar(10) DEFAULT NULL,
  `sex` int(11) NOT NULL,
  `status` int(11) DEFAULT NULL,
  `user_name` varchar(40) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf16 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of us_user
-- ----------------------------
BEGIN;
INSERT INTO `us_user` VALUES ('1234533425', 1559573395828, 1583510501512, 'admin', 'http://vue-admin-guoguang.oss-cn-shanghai.aliyuncs.com/icode/image/avatar/17e420c250804efe904a09a33796d5a16.jpg', 20181128125747, '山东省潍坊市', '1234@email.com', 121, 27, '150', NULL, 'admin', 1559573395828, '$2a$10$EPTw4OF4EHoHtWA.UjoDS.hZsjaOylEF.bBxf2YHST7VjpWr6.kd.', '', '17667198751', '1', 1, 1, '管理员');
COMMIT;

-- ----------------------------
-- Table structure for us_user_role
-- ----------------------------
DROP TABLE IF EXISTS `us_user_role`;
CREATE TABLE `us_user_role` (
  `user_id` varchar(20) NOT NULL,
  `role_id` varchar(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf16 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of us_user_role
-- ----------------------------
BEGIN;
INSERT INTO `us_user_role` VALUES ('1234533425', '1119477949450088449');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
