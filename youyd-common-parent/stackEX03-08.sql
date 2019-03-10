/*
 Navicat Premium Data Transfer

 Source Server         : 39.108.191.56
 Source Server Type    : MySQL
 Source Server Version : 50641
 Source Host           : 39.108.191.56:3306
 Source Schema         : youyd

 Target Server Type    : MySQL
 Target Server Version : 50641
 File Encoding         : 65001

 Date: 08/03/2019 23:21:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ar_article
-- ----------------------------
DROP TABLE IF EXISTS `ar_article`;
CREATE TABLE `ar_article`  (
  `id` int(20) NOT NULL COMMENT 'ID',
  `category_id` int(11) NULL DEFAULT NULL COMMENT '专栏ID',
  `user_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户ID',
  `title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `description` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章描述（概述）',
  `image` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章封面',
  `create_at` timestamp(0) NULL DEFAULT NULL COMMENT '发表日期',
  `update_at` timestamp(0) NULL DEFAULT NULL COMMENT '修改日期',
  `is_public` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否公开',
  `is_top` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否置顶',
  `visits` int(20) NULL DEFAULT NULL COMMENT '浏览量',
  `upvote` int(20) NULL DEFAULT NULL COMMENT '点赞数',
  `comment` int(20) NULL DEFAULT NULL COMMENT '评论数',
  `review_state` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审核状态',
  `url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'URL',
  `type` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型',
  `importance` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '热度',
  `keywords` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关键字',
  `origin` int(1) NULL DEFAULT NULL COMMENT '来源（1：原创，2：转载，3：混撰）',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '文章正文',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文章板块/文章管理' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of ar_article
-- ----------------------------
INSERT INTO `ar_article` VALUES (1, 1, '12151044', '受脑认知和神经科学启发的人工智能', '大概描述', 'https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1229855747,4092679124&fm=26&gp=0.jpg', '2019-01-29 19:47:44', '2019-01-29 19:47:46', '1', '1', 25, 3562, 11, '0', '1', '1', '9', '1', 2, '人工智能渗透到了人类社会各个领域，但目前来看，无论是深度学习还是其它方法，解决的都是单一问题。人类大脑是一个多问题求解的结构，怎么从脑认知和神经科学中得到构造健壮的人工智能的启示，国内外都做了非常多有成效的研究。\\r\\n\\r\\n一、实现健壮的人工智能的方法\\r\\n\\r\\n人类面临的许多问题具有不确定性、脆弱性和开放性。今天人工智能的理论框架，建立在演绎逻辑和语义描述的基础方法之上，但我们不可能对人类社会的所有问题建模，因为这中间存在着条件问题，我们不可能把一个行为的所有条件都模拟出，这是传统人工智能的局限性。\\r\\n\\r\\n这个局限性主要表现在几个方面：\\r\\n\\r\\n1、需要对问题本身抽象出一个精确数学意义上的解析式的数学模型（抽象不出，即归纳为不可解问题）');
INSERT INTO `ar_article` VALUES (2, 2, '2', '测试', '2', 'https://ss1.baidu.com/9vo3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=c8a9d4e2841363270aedc433a18fa056/11385343fbf2b2114a65cd70c48065380cd78e41.jpg', '2019-01-30 10:16:49', '2019-01-30 10:16:49', '2', '2', 0, 3, 11, '2', '2', '2', '2', '2', 1, '1');

-- ----------------------------
-- Table structure for ar_category
-- ----------------------------
DROP TABLE IF EXISTS `ar_category`;
CREATE TABLE `ar_category`  (
  `id` int(11) NOT NULL COMMENT 'ID',
  `parent_id` int(11) NULL DEFAULT NULL COMMENT '父ID',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类名称',
  `summary` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类简介',
  `user_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户ID',
  `create_at` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `state` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  `update_at` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文章板块/文章分类表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of ar_category
-- ----------------------------
INSERT INTO `ar_category` VALUES (1, 0, 'java', 'java开发标签', '1151577', '2019-01-30 19:07:24', '1', '2019-03-08 19:32:35');
INSERT INTO `ar_category` VALUES (2, 0, 'python', 'python开发标签', '1151577', '2019-01-30 19:07:24', '1', '2019-03-08 19:32:35');
INSERT INTO `ar_category` VALUES (3, 1, 'python', 'spring开发标签', '1151577', '2019-01-30 19:07:24', '1', '2019-03-08 19:32:35');

-- ----------------------------
-- Table structure for qa_answers
-- ----------------------------
DROP TABLE IF EXISTS `qa_answers`;
CREATE TABLE `qa_answers`  (
  `id` int(20) NOT NULL COMMENT '答案表id',
  `question_id` int(20) NULL DEFAULT NULL COMMENT '问题表ID',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '答案内容',
  `create_at` timestamp(0) NULL DEFAULT NULL COMMENT '创建日期',
  `update_at` timestamp(0) NULL DEFAULT NULL COMMENT '更新日期',
  `user_id` int(20) NULL DEFAULT NULL COMMENT '回答人ID',
  `nick_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '回答人昵称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '问答版块/答案' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for qa_question
-- ----------------------------
DROP TABLE IF EXISTS `qa_question`;
CREATE TABLE `qa_question`  (
  `id` int(20) NOT NULL COMMENT 'ID',
  `title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容',
  `tags_id` int(20) NULL DEFAULT NULL COMMENT '问题标签表',
  `create_at` timestamp(0) NULL DEFAULT NULL COMMENT '创建日期',
  `update_at` timestamp(0) NULL DEFAULT NULL COMMENT '修改日期',
  `user_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户ID',
  `nick_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `visits` bigint(20) NULL DEFAULT NULL COMMENT '浏览量',
  `thumb_up` bigint(20) NULL DEFAULT NULL COMMENT '点赞数',
  `reply` bigint(20) NULL DEFAULT NULL COMMENT '回复数',
  `solve` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否解决',
  `reply_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '回复人昵称',
  `reply_time` timestamp(0) NULL DEFAULT NULL COMMENT '回复日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '问答版块/问题' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of qa_question
-- ----------------------------
INSERT INTO `qa_question` VALUES (1, '这是个问题', '代码调试不通咋办？', NULL, '2018-01-08 11:50:50', '2018-01-09 11:50:54', '2', NULL, 101, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for tb_city
-- ----------------------------
DROP TABLE IF EXISTS `tb_city`;
CREATE TABLE `tb_city`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '城市名称',
  `is_hot` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否热门',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '城市' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_city
-- ----------------------------
INSERT INTO `tb_city` VALUES ('1', '北京', '1');
INSERT INTO `tb_city` VALUES ('2', '上海', '1');
INSERT INTO `tb_city` VALUES ('3', '广州', '1');
INSERT INTO `tb_city` VALUES ('4', '深圳', '1');
INSERT INTO `tb_city` VALUES ('5', '天津', '0');
INSERT INTO `tb_city` VALUES ('6', '重庆', '0');
INSERT INTO `tb_city` VALUES ('7', '西安', '0');

-- ----------------------------
-- Table structure for tb_comment
-- ----------------------------
DROP TABLE IF EXISTS `tb_comment`;
CREATE TABLE `tb_comment`  (
  `id` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评论表id',
  `article_id` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '外键文章表ID',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评论内容',
  `user_id` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评论人ID',
  `parent_id` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父评论ID(如果为0表示文章的顶级评论,每一条评论都可以被评论)',
  `publish_date` datetime(0) NULL DEFAULT NULL COMMENT '评论日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for tb_follow
-- ----------------------------
DROP TABLE IF EXISTS `tb_follow`;
CREATE TABLE `tb_follow`  (
  `user_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户ID',
  `target_user_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '被关注用户ID',
  PRIMARY KEY (`user_id`, `target_user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_follow
-- ----------------------------
INSERT INTO `tb_follow` VALUES ('1', '1');
INSERT INTO `tb_follow` VALUES ('1', '10');

-- ----------------------------
-- Table structure for tb_gather
-- ----------------------------
DROP TABLE IF EXISTS `tb_gather`;
CREATE TABLE `tb_gather`  (
  `id` int(20) NOT NULL DEFAULT '' COMMENT '编号',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '活动名称',
  `summary` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '大会简介',
  `detail` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '详细说明',
  `sponsor` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主办方',
  `image` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '活动图片',
  `start_time` timestamp(0) NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` timestamp(0) NULL DEFAULT NULL COMMENT '截止时间',
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '举办地点',
  `enroll_time` timestamp(0) NULL DEFAULT NULL COMMENT '报名截止',
  `state` int(1) NULL DEFAULT NULL COMMENT '是否可见',
  `city` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '城市',
  `create_at` timestamp(0) NULL DEFAULT NULL,
  `update_at` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '活动版块/活动' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_gather
-- ----------------------------
INSERT INTO `tb_gather` VALUES (1, '测试活动', '喝茶看电影，不亦乐乎', '喝茶看电影，不亦乐乎', '黑马程序员', NULL, '2017-12-14 15:39:32', '2017-12-21 15:39:36', NULL, NULL, 1, '1', NULL, NULL);

-- ----------------------------
-- Table structure for tb_label
-- ----------------------------
DROP TABLE IF EXISTS `tb_label`;
CREATE TABLE `tb_label`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标签ID',
  `label_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签名称',
  `state` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  `count` bigint(20) NULL DEFAULT NULL COMMENT '使用数量',
  `recommend` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否推荐',
  `fans` bigint(20) NULL DEFAULT NULL COMMENT '粉丝数',
  `update_at` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `create_at` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '标签' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_label
-- ----------------------------
INSERT INTO `tb_label` VALUES ('1', 'java', '1', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tb_label` VALUES ('2', 'PHP', '1', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tb_label` VALUES ('3', 'C++', '1', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tb_label` VALUES ('4', 'python', '1', NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for tb_pl
-- ----------------------------
DROP TABLE IF EXISTS `tb_pl`;
CREATE TABLE `tb_pl`  (
  `problem_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '问题ID',
  `label_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标签ID',
  PRIMARY KEY (`problem_id`, `label_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '问答标签中间表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_pl
-- ----------------------------
INSERT INTO `tb_pl` VALUES ('1', '1');

-- ----------------------------
-- Table structure for tb_ul
-- ----------------------------
DROP TABLE IF EXISTS `tb_ul`;
CREATE TABLE `tb_ul`  (
  `user_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `label_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`user_id`, `label_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_ul
-- ----------------------------
INSERT INTO `tb_ul` VALUES ('1', '1');
INSERT INTO `tb_ul` VALUES ('1', '2');
INSERT INTO `tb_ul` VALUES ('1', '3');

-- ----------------------------
-- Table structure for tb_usergath
-- ----------------------------
DROP TABLE IF EXISTS `tb_usergath`;
CREATE TABLE `tb_usergath`  (
  `user_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户ID',
  `gath_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '活动ID',
  `click_time` timestamp(0) NULL DEFAULT NULL COMMENT '点击时间',
  PRIMARY KEY (`user_id`, `gath_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户关注活动' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_usergath
-- ----------------------------
INSERT INTO `tb_usergath` VALUES ('1', '200', '2018-01-06 15:44:04');

-- ----------------------------
-- Table structure for tc_tweets
-- ----------------------------
DROP TABLE IF EXISTS `tc_tweets`;
CREATE TABLE `tc_tweets`  (
  `id` int(20) NOT NULL COMMENT '吐槽表ID',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '吐槽内容',
  `create_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '发布日期(创建时间)',
  `user_id` int(20) NOT NULL COMMENT '发布人ID',
  `nick_name` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发布人昵称',
  `visits_count` bigint(255) NULL DEFAULT NULL COMMENT '浏览量',
  `thumb_up_count` bigint(255) NULL DEFAULT NULL COMMENT '点赞数',
  `share_count` bigint(255) NULL DEFAULT NULL COMMENT '分享数',
  `reply_count` bigint(255) NULL DEFAULT NULL COMMENT '回复数',
  `is_visible` int(1) NULL DEFAULT NULL COMMENT '是否可见',
  `update_at` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '交流版块/吐槽，微博' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for tc_tweets_comment
-- ----------------------------
DROP TABLE IF EXISTS `tc_tweets_comment`;
CREATE TABLE `tc_tweets_comment`  (
  `id` int(20) NOT NULL COMMENT '吐槽评论ID',
  `content` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评论内容',
  `create_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '发布日期(创建时间)',
  `user_id` int(20) NOT NULL COMMENT '评论人ID',
  `nick_name` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评论人昵称',
  `thumb_up_count` bigint(255) NULL DEFAULT NULL COMMENT '点赞数',
  `reply_count` bigint(20) NULL DEFAULT NULL COMMENT '回复数',
  `is_visible` int(1) NULL DEFAULT NULL COMMENT '是否可见',
  `update_at` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  `tweets_id` int(20) NOT NULL COMMENT '吐槽表id',
  `parent_id` int(20) NULL DEFAULT NULL COMMENT '父级评论',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '交流版块/吐槽-评论' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for us_menu
-- ----------------------------
DROP TABLE IF EXISTS `us_menu`;
CREATE TABLE `us_menu`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单表主键',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `name` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `parent_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父资源id',
  `component` varchar(90) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'vue组件名称/名称必须真实存在',
  `icon` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '路径',
  `state` int(1) NULL DEFAULT NULL COMMENT '状态（禁用：0，启用：1）',
  `sort` int(30) NULL DEFAULT NULL COMMENT '排序',
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create_at` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_at` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户板块/菜单' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of us_menu
-- ----------------------------
INSERT INTO `us_menu` VALUES ('1', '测试001', '66661', '0', 'system/user', 'form', 'user', 1, 0, '测试动态菜单', '2019-01-06 11:47:10', '2019-01-06 11:47:13');

-- ----------------------------
-- Table structure for us_role
-- ----------------------------
DROP TABLE IF EXISTS `us_role`;
CREATE TABLE `us_role`  (
  `role_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色表主键',
  `parent_role_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '父级角色id',
  `role_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `role_desc` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  `role_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色编码',
  `create_at` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_at` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户板块/角色' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of us_role
-- ----------------------------
INSERT INTO `us_role` VALUES ('1', '0', '管理员', '一般是后台管理员用户', 'CU-001', '2018-11-24 20:33:49', '2018-12-23 20:33:55');
INSERT INTO `us_role` VALUES ('2', '1', '普通用户', '网站的普通用户', 'CU-002', '2018-11-24 20:33:49', '2018-11-24 20:33:49');

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
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
  `account` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账号',
  `nick_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `sex` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '性别',
  `birthday` timestamp(0) NULL DEFAULT NULL COMMENT '出生年月日',
  `avatar` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'E-Mail',
  `create_at` timestamp(0) NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '注册日期',
  `update_at` timestamp(0) NULL DEFAULT NULL COMMENT '修改日期',
  `last_date` timestamp(0) NULL DEFAULT NULL COMMENT '最后登陆日期',
  `online_time` bigint(20) NULL DEFAULT NULL COMMENT '在线时长（分钟）',
  `interest` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '兴趣',
  `personality` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '个性',
  `fans_count` int(20) NULL DEFAULT NULL COMMENT '粉丝数',
  `follow_count` int(20) NULL DEFAULT NULL COMMENT '关注数',
  `mobile` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机',
  `contact_address` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系地址',
  `registered_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '注册类型/方式',
  `status` int(1) NULL DEFAULT NULL COMMENT '是否锁定(0:未锁定,1已锁定)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户板块/用户' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of us_user
-- ----------------------------
INSERT INTO `us_user` VALUES ('1', 'admin', '管理员', 'ZH001', '$2a$10$fJzQj58QQrNFCbE7xGVULO/Mg6ziERy3MyoIUVdeOwetSO1juQORC', '男', '2018-11-28 12:57:47', '头像是dd', '121@qq.com', '2018-11-28 12:57:47', NULL, NULL, 150, NULL, NULL, 1, 0, '15866969696', '山东省潍坊市昌邑市', '', 0);
INSERT INTO `us_user` VALUES ('1067076025403432962', '157002', NULL, 'ZH001', '123456', '男', '2018-12-02 21:48:51', '头像是qq', '121@qq.com', '2018-12-02 21:48:51', NULL, NULL, 150, NULL, NULL, NULL, NULL, '15866969696', '山东省潍坊市昌邑市', '', 0);
INSERT INTO `us_user` VALUES ('1067644187756732417', '157003', NULL, 'ZH001', '123456', '男', '2018-11-28 12:59:43', '头像是qq', '121@qq.com', '2018-11-28 12:59:43', NULL, NULL, 150, NULL, NULL, NULL, NULL, '15866969696', '山东省潍坊市昌邑市', '', 0);

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
