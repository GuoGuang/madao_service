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

 Date: 24/01/2019 16:58:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ar_article
-- ----------------------------
DROP TABLE IF EXISTS `ar_article`;
CREATE TABLE `ar_article`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
  `category_id` int(11) NULL DEFAULT NULL COMMENT '专栏ID',
  `user_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户ID',
  `title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '文章正文',
  `image` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章封面',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '发表日期',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '修改日期',
  `is_public` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否公开',
  `is_top` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否置顶',
  `visits` int(20) NULL DEFAULT NULL COMMENT '浏览量',
  `thumb_up` int(20) NULL DEFAULT NULL COMMENT '点赞数',
  `comment` int(20) NULL DEFAULT NULL COMMENT '评论数',
  `state` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审核状态',
  `url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'URL',
  `type` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型',
  `importance` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '热度',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文章板块/文章管理' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of ar_article
-- ----------------------------
INSERT INTO `ar_article` VALUES ('1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL);

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
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `state` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文章板块/文章分类表' ROW_FORMAT = Compact;

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
-- Table structure for tb_enterprise
-- ----------------------------
DROP TABLE IF EXISTS `tb_enterprise`;
CREATE TABLE `tb_enterprise`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业名称',
  `summary` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业简介',
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业地址',
  `labels` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签列表',
  `coordinate` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '坐标',
  `is_hot` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否热门',
  `logo` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'LOGO',
  `job_count` int(11) NULL DEFAULT NULL COMMENT '职位数',
  `url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'URL',
  `create_time` timestamp(0) NULL DEFAULT NULL,
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '企业' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_enterprise
-- ----------------------------
INSERT INTO `tb_enterprise` VALUES ('', '传智播客', '国内著名IT教育机构', '金燕龙办公楼', 'IT 培训', '1019,2223', '1', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tb_enterprise` VALUES ('2', '小米', '手机厂商', '中关村软件园', '手机', '0211,3333', '0', NULL, NULL, NULL, NULL, NULL);

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
-- Table structure for tb_gathering
-- ----------------------------
DROP TABLE IF EXISTS `tb_gathering`;
CREATE TABLE `tb_gathering`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '编号',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '活动名称',
  `summary` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '大会简介',
  `detail` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '详细说明',
  `sponsor` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主办方',
  `image` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '活动图片',
  `start_time` timestamp(0) NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` timestamp(0) NULL DEFAULT NULL COMMENT '截止时间',
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '举办地点',
  `enroll_time` timestamp(0) NULL DEFAULT NULL COMMENT '报名截止',
  `state` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否可见',
  `city` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '城市',
  `create_time` timestamp(0) NULL DEFAULT NULL,
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '活动' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_gathering
-- ----------------------------
INSERT INTO `tb_gathering` VALUES ('1', '测试活动', '喝茶看电影，不亦乐乎', '喝茶看电影，不亦乐乎', '黑马程序员', NULL, '2017-12-14 15:39:32', '2017-12-21 15:39:36', NULL, NULL, '1', '1', NULL, NULL);
INSERT INTO `tb_gathering` VALUES ('94377594140', 'aaaa', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', '1', NULL, '2018-10-16 14:17:32');
INSERT INTO `tb_gathering` VALUES ('943776146707845', 'aaaa', '1', NULL, 'ssss', NULL, NULL, NULL, 'cccc', NULL, '1', '1', NULL, '2018-10-16 14:17:33');
INSERT INTO `tb_gathering` VALUES ('943776663576121344', 'aaaa', '2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', '2', NULL, '2018-10-16 14:17:34');
INSERT INTO `tb_gathering` VALUES ('943783521749700608', '2342423', NULL, NULL, '23454534', NULL, NULL, NULL, '545435435', NULL, '1', '2', NULL, NULL);
INSERT INTO `tb_gathering` VALUES ('944085821768732672', 'JAVAEE茶话会', NULL, NULL, '传智', NULL, NULL, NULL, '金燕龙', NULL, '1', '2', NULL, NULL);
INSERT INTO `tb_gathering` VALUES ('944086086991351808', '是', NULL, NULL, '11', NULL, NULL, NULL, '11', NULL, '1', '3', NULL, NULL);
INSERT INTO `tb_gathering` VALUES ('944090372710207488', '大讨论', NULL, NULL, '小马', NULL, NULL, NULL, '消息', NULL, '1', '3', NULL, NULL);
INSERT INTO `tb_gathering` VALUES ('944105652622594048', '测试测试', NULL, NULL, '测试者', NULL, NULL, NULL, '测试地址', NULL, '1', '4', NULL, NULL);
INSERT INTO `tb_gathering` VALUES ('945227340642914304', '111', NULL, NULL, '222', NULL, NULL, NULL, '333', NULL, '1', '5', NULL, NULL);
INSERT INTO `tb_gathering` VALUES ('945227678527655936', '111', NULL, NULL, '222', NULL, NULL, NULL, '333', NULL, '1', '5', NULL, NULL);
INSERT INTO `tb_gathering` VALUES ('945235087564345344', '啊啊', NULL, NULL, '1', NULL, NULL, NULL, '1', NULL, '1', '1', NULL, NULL);
INSERT INTO `tb_gathering` VALUES ('945235534329024512', '1', NULL, NULL, '1', NULL, NULL, NULL, '1', NULL, '1', '2', NULL, NULL);
INSERT INTO `tb_gathering` VALUES ('945235859786043392', '1', NULL, NULL, '1', NULL, NULL, NULL, '1', NULL, '1', '3', NULL, NULL);
INSERT INTO `tb_gathering` VALUES ('951384896167874560', '??', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

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
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `create_time` timestamp(0) NULL DEFAULT NULL,
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
-- Table structure for tb_problem
-- ----------------------------
DROP TABLE IF EXISTS `tb_problem`;
CREATE TABLE `tb_problem`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
  `title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建日期',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '修改日期',
  `user_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户ID',
  `nick_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `visits` bigint(20) NULL DEFAULT NULL COMMENT '浏览量',
  `thumb_up` bigint(20) NULL DEFAULT NULL COMMENT '点赞数',
  `reply` bigint(20) NULL DEFAULT NULL COMMENT '回复数',
  `solve` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否解决',
  `reply_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '回复人昵称',
  `reply_time` timestamp(0) NULL DEFAULT NULL COMMENT '回复日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '问题' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_problem
-- ----------------------------
INSERT INTO `tb_problem` VALUES ('1', '这是个问题', '代码调试不通咋办？', '2018-01-08 11:50:50', '2018-01-09 11:50:54', '2', NULL, 101, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for tb_recruit
-- ----------------------------
DROP TABLE IF EXISTS `tb_recruit`;
CREATE TABLE `tb_recruit`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
  `job_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职位名称',
  `salary` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '薪资范围',
  `condition` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '经验要求',
  `education` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学历要求',
  `type` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任职方式',
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '办公地址',
  `eid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业ID',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建日期',
  `state` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  `url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '网址',
  `label` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签',
  `job_description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职位描述',
  `job_require` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职位要求',
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '职位' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_recruit
-- ----------------------------
INSERT INTO `tb_recruit` VALUES ('', '大数据工程师', '20000-30000', '八年以上开发经验', '本科', '1', '国贸', '1', '2018-01-06 16:21:12', '1', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tb_recruit` VALUES ('1', 'java开发工程师', '15000-20000', '五年以上开发经验', '本科', '1', '中关村软件园', '1', '2018-01-05 15:38:05', '1', 'http://www.baidu.com', NULL, NULL, NULL, NULL);
INSERT INTO `tb_recruit` VALUES ('2', 'php开发工程师', '4000-6000', '一年以上开发经验', '专科', '1', '王府街宏福创业园', '1', '2018-01-07 16:10:20', '1', 'http://www.baidu.com', NULL, NULL, NULL, NULL);
INSERT INTO `tb_recruit` VALUES ('3', '.net开发工程师', '2000-3000', '一年以上开发经验', '专科', '1', '大望路', '1', '2018-01-06 16:20:27', '2', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tb_recruit` VALUES ('5', '前端开发工程师', '8000-12000', '三年以上开发经验', '本科', '1', '上地', '1', '2018-01-18 16:22:11', '2', NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for tb_reply
-- ----------------------------
DROP TABLE IF EXISTS `tb_reply`;
CREATE TABLE `tb_reply`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '编号',
  `problem_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '问题ID',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '回答内容',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建日期',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新日期',
  `user_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '回答人ID',
  `nick_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '回答人昵称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '回答' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_reply
-- ----------------------------
INSERT INTO `tb_reply` VALUES ('', '1', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tb_reply` VALUES ('2', '1', '问老师呗', '2018-01-10 14:14:06', NULL, '1', NULL);
INSERT INTO `tb_reply` VALUES ('3', '2', '明天再调', '2018-01-07 14:14:13', NULL, '1', NULL);

-- ----------------------------
-- Table structure for tb_spit
-- ----------------------------
DROP TABLE IF EXISTS `tb_spit`;
CREATE TABLE `tb_spit`  (
  `id` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '吐槽表ID',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '吐槽内容',
  `publish_time` timestamp(0) NULL DEFAULT NULL COMMENT '发布日期(创建时间)',
  `user_id` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发布人ID',
  `nick_name` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发布人昵称',
  `visits_count` bigint(255) NULL DEFAULT NULL COMMENT '浏览量',
  `thumb_up_count` bigint(255) NULL DEFAULT NULL COMMENT '点赞数',
  `share_count` bigint(255) NULL DEFAULT NULL COMMENT '分享数',
  `reply_count` bigint(255) NULL DEFAULT NULL COMMENT '回复数',
  `is_visible` int(255) NULL DEFAULT NULL COMMENT '是否可见',
  `parent_id` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上级ID',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_spit
-- ----------------------------
INSERT INTO `tb_spit` VALUES ('1', NULL, NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL);

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
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
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
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
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
  `create_time` timestamp(0) NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '注册日期',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '修改日期',
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
