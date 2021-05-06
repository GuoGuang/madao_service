/*
 Navicat Premium Data Transfer
 Source Server Type    : MySQL
 Source Server Version : 80016
 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001
 Date: 23/07/2020 11:12:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ar_article
-- ----------------------------
DROP TABLE IF EXISTS `ar_article`;
CREATE TABLE `ar_article`  (
                               `id` varchar(20) CHARACTER SET utf16 COLLATE utf16_general_ci NOT NULL,
                               `create_at` bigint(20) NULL DEFAULT NULL,
                               `update_at` bigint(20) NULL DEFAULT NULL,
                               `comment` int(11) NULL DEFAULT NULL,
                               `content` text CHARACTER SET utf16 COLLATE utf16_general_ci NOT NULL,
                               `description` varchar(500) CHARACTER SET utf16 COLLATE utf16_general_ci NOT NULL,
                               `importance` float NULL DEFAULT NULL,
                               `is_public` int(11) NULL DEFAULT NULL,
                               `is_top` int(11) NULL DEFAULT NULL,
                               `keywords` varchar(200) CHARACTER SET utf16 COLLATE utf16_general_ci NULL DEFAULT NULL,
                               `origin` int(11) NOT NULL,
                               `review_state` int(11) NULL DEFAULT NULL,
                               `thumb` varchar(200) CHARACTER SET utf16 COLLATE utf16_general_ci NULL DEFAULT NULL,
                               `title` varchar(50) CHARACTER SET utf16 COLLATE utf16_general_ci NOT NULL,
                               `type` int(11) NULL DEFAULT NULL,
                               `upvote` int(11) NULL DEFAULT NULL,
                               `url` varchar(200) CHARACTER SET utf16 COLLATE utf16_general_ci NULL DEFAULT NULL,
                               `user_id` varchar(20) CHARACTER SET utf16 COLLATE utf16_general_ci NULL DEFAULT NULL,
                               `visits` int(11) NULL DEFAULT NULL,
                               `category_id` varchar(20) CHARACTER SET utf16 COLLATE utf16_general_ci NULL DEFAULT NULL,
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf16 COLLATE = utf16_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ar_article
-- ----------------------------
INSERT INTO `ar_article` VALUES ('1238640274759094272', 1584178628978, 1587804880130, 0, '### test'	, 'test article', 0, 0, 0, NULL, 3, 1, 'https://vue-admin-guoguang.oss-cn-shanghai.aliyuncs.com/%E5%B1%8F%E5%B9%95%E5%BF%AB%E7%85%A7%202019-12-21%2009.35.30%20PM.png', 'test article', 1, 2, NULL, '1234533425', 0, '1');
-- ----------------------------
-- Table structure for ar_article_tags
-- ----------------------------
DROP TABLE IF EXISTS `ar_article_tags`;
CREATE TABLE `ar_article_tags`  (
                                    `article_id` varchar(20) CHARACTER SET utf16 COLLATE utf16_general_ci NOT NULL,
                                    `tags_id` varchar(20) CHARACTER SET utf16 COLLATE utf16_general_ci NOT NULL,
                                    PRIMARY KEY (`article_id`, `tags_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf16 COLLATE = utf16_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ar_article_tags
-- ----------------------------
INSERT INTO `ar_article_tags` VALUES ('1238640274759094272', '5');

-- ----------------------------
-- Table structure for ar_category
-- ----------------------------
DROP TABLE IF EXISTS `ar_category`;
CREATE TABLE `ar_category`  (
                                `id` varchar(20) CHARACTER SET utf16 COLLATE utf16_general_ci NOT NULL,
                                `create_at` bigint(20) NULL DEFAULT NULL,
                                `update_at` bigint(20) NULL DEFAULT NULL,
                                `name` varchar(20) CHARACTER SET utf16 COLLATE utf16_general_ci NOT NULL,
                                `parent_id` varchar(20) CHARACTER SET utf16 COLLATE utf16_general_ci NULL DEFAULT NULL,
                                `state` int(11) NULL DEFAULT NULL,
                                `summary` varchar(200) CHARACTER SET utf16 COLLATE utf16_general_ci NOT NULL,
                                `user_id` varchar(20) CHARACTER SET utf16 COLLATE utf16_general_ci NULL DEFAULT NULL,
                                PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf16 COLLATE = utf16_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ar_category
-- ----------------------------
INSERT INTO `ar_category` VALUES ('1', 1583380077, 1583380077, '技术', '0', 1, '技术', NULL);
INSERT INTO `ar_category` VALUES ('2', 1588003346277, 1588003346277, '福利', '0', 1, '福利', NULL);
INSERT INTO `ar_category` VALUES ('3', 1588003346277, 1588003346277, '安全', '0', 1, '安全', NULL);

-- ----------------------------
-- Table structure for ar_comment
-- ----------------------------
DROP TABLE IF EXISTS `ar_comment`;
CREATE TABLE `ar_comment`  (
                               `id` varchar(20) CHARACTER SET utf16 COLLATE utf16_general_ci NOT NULL,
                               `user_id` varchar(20) CHARACTER SET utf16 COLLATE utf16_general_ci NOT NULL,
                               `parent_id` varchar(20) CHARACTER SET utf16 COLLATE utf16_general_ci NULL DEFAULT NULL,
                               `article_id` varchar(20) CHARACTER SET utf16 COLLATE utf16_general_ci NOT NULL,
                               `content` varchar(3000) CHARACTER SET utf16 COLLATE utf16_general_ci NOT NULL,
                               `upvote` int(11) NULL DEFAULT NULL,
                               `avatar` varchar(2000) CHARACTER SET utf16 COLLATE utf16_general_ci NULL DEFAULT NULL,
                               `user_name` varchar(300) CHARACTER SET utf16 COLLATE utf16_general_ci NULL DEFAULT NULL,
                               `to_id` varchar(20) CHARACTER SET utf16 COLLATE utf16_general_ci NULL DEFAULT NULL,
                               `to_avatar` varchar(2000) CHARACTER SET utf16 COLLATE utf16_general_ci NULL DEFAULT NULL,
                               `to_name` varchar(300) CHARACTER SET utf16 COLLATE utf16_general_ci NULL DEFAULT NULL,
                               `create_at` bigint(20) NOT NULL,
                               `update_at` bigint(20) NOT NULL,
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf16 COLLATE = utf16_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ar_comment
-- ----------------------------
INSERT INTO `ar_comment` VALUES ('comment0001', 'errhefe232213', NULL, '1280520740491366400', 'hhh', 21, 'https://images.nowcoder.com/images/20180218/6617757_1518920311404_48DBFD0E780C1F7DCB9ABC4D5083B2BD@0e_100w_100h_0c_1i_1o_90Q_1x', '犀利的评论家', NULL, NULL, NULL, 1, 1);
INSERT INTO `ar_comment` VALUES ('comment0002', 'errhefe232213', NULL, '1280520740491366400', '66666', 22, 'https://images.nowcoder.com/images/20180102/63_1514861814371_E573EC9DA05DFAC7340D94F1A6D246E3@0e_100w_100h_0c_1i_1o_90Q_1x', '毒蛇郭德纲', NULL, NULL, NULL, 2, 2);

-- ----------------------------
-- Table structure for ar_tags
-- ----------------------------
DROP TABLE IF EXISTS `ar_tags`;
CREATE TABLE `ar_tags`  (
                            `id` varchar(20) CHARACTER SET utf16 COLLATE utf16_general_ci NOT NULL,
                            `create_at` bigint(20) NULL DEFAULT NULL,
                            `update_at` bigint(20) NULL DEFAULT NULL,
                            `color` varchar(20) CHARACTER SET utf16 COLLATE utf16_general_ci NULL DEFAULT NULL,
                            `description` varchar(200) CHARACTER SET utf16 COLLATE utf16_general_ci NOT NULL,
                            `icon` varchar(50) CHARACTER SET utf16 COLLATE utf16_general_ci NULL DEFAULT NULL,
                            `name` varchar(20) CHARACTER SET utf16 COLLATE utf16_general_ci NOT NULL,
                            `slug` varchar(20) CHARACTER SET utf16 COLLATE utf16_general_ci NOT NULL,
                            `state` int(11) NULL DEFAULT NULL,
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf16 COLLATE = utf16_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ar_tags
-- ----------------------------
INSERT INTO `ar_tags` VALUES ('1', 1583380077, 1583504578341, 'rgba(144, 238, 144, ', 'Java', '0', 'Java', 'Java', 1);
INSERT INTO `ar_tags` VALUES ('2', 1583380077, 1583380077, '#ffbb50', 'LeetCode', 'aa', 'LeetCode', 'LeetCode', 1);
INSERT INTO `ar_tags` VALUES ('3', 1583380077, 1583380077, '#1ac756', 'Python', 'aa', 'Python', 'Python', 1);
INSERT INTO `ar_tags` VALUES ('4', 1583380077, 1583380077, '#19B5FE', 'Hack', 'aa', 'Hack', 'Hack', 1);
INSERT INTO `ar_tags` VALUES ('5', 1583380077, 1583380077, '#b1a8a8', '2020', 'aa', '2020', '2020', 1);
INSERT INTO `ar_tags` VALUES ('6', 1583380077, 1583380077, '#ff9800', 'Docker', 'aa', 'Docker', 'Docker', 1);

-- ----------------------------
-- Table structure for ba_dict
-- ----------------------------
DROP TABLE IF EXISTS `ba_dict`;
CREATE TABLE `ba_dict`  (
                            `id` varchar(20) CHARACTER SET utf16 COLLATE utf16_general_ci NOT NULL,
                            `create_at` bigint(20) NULL DEFAULT NULL,
                            `update_at` bigint(20) NULL DEFAULT NULL,
                            `code` varchar(10) CHARACTER SET utf16 COLLATE utf16_general_ci NOT NULL,
                            `description` varchar(200) CHARACTER SET utf16 COLLATE utf16_general_ci NOT NULL,
                            `name` varchar(10) CHARACTER SET utf16 COLLATE utf16_general_ci NOT NULL,
                            `parent_id` varchar(20) CHARACTER SET utf16 COLLATE utf16_general_ci NULL DEFAULT NULL,
                            `state` int(11) NULL DEFAULT NULL,
                            `type` varchar(10) CHARACTER SET utf16 COLLATE utf16_general_ci NOT NULL,
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf16 COLLATE = utf16_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ba_dict
-- ----------------------------
INSERT INTO `ba_dict` VALUES ('1254829256031211520', 1588009582890, 1588009582890, '001', '0', 'bar', '0', NULL, '0');

-- ----------------------------
-- Table structure for ba_login_log
-- ----------------------------
DROP TABLE IF EXISTS `ba_login_log`;
CREATE TABLE `ba_login_log`  (
                                 `id` varchar(20) CHARACTER SET utf16 COLLATE utf16_general_ci NOT NULL,
                                 `create_at` bigint(20) NULL DEFAULT NULL,
                                 `update_at` bigint(20) NULL DEFAULT NULL,
                                 `browser` varchar(50) CHARACTER SET utf16 COLLATE utf16_general_ci NULL DEFAULT NULL,
                                 `client_ip` varchar(20) CHARACTER SET utf16 COLLATE utf16_general_ci NULL DEFAULT NULL,
                                 `os_info` varchar(100) CHARACTER SET utf16 COLLATE utf16_general_ci NULL DEFAULT NULL,
                                 `user_id` varchar(20) CHARACTER SET utf16 COLLATE utf16_general_ci NULL DEFAULT NULL,
                                 PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf16 COLLATE = utf16_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ba_login_log
-- ----------------------------
INSERT INTO `ba_login_log` VALUES ('1235224543237378048', 1583335455120, 1583335455120, 'Chrome 8', '127.0.0.1', 'Mac OS X', '1234533425');

-- ----------------------------
-- Table structure for ba_opt_log
-- ----------------------------
DROP TABLE IF EXISTS `ba_opt_log`;
CREATE TABLE `ba_opt_log`  (
                               `id` varchar(20) CHARACTER SET utf16 COLLATE utf16_general_ci NOT NULL,
                               `create_at` bigint(20) NULL DEFAULT NULL,
                               `update_at` bigint(20) NULL DEFAULT NULL,
                               `browser` varchar(50) CHARACTER SET utf16 COLLATE utf16_general_ci NULL DEFAULT NULL,
                               `client_ip` varchar(20) CHARACTER SET utf16 COLLATE utf16_general_ci NULL DEFAULT NULL,
                               `exception_detail` varchar(500) CHARACTER SET utf16 COLLATE utf16_general_ci NULL DEFAULT NULL,
                               `method` varchar(100) CHARACTER SET utf16 COLLATE utf16_general_ci NULL DEFAULT NULL,
                               `os_info` varchar(50) CHARACTER SET utf16 COLLATE utf16_general_ci NULL DEFAULT NULL,
                               `params` varchar(500) CHARACTER SET utf16 COLLATE utf16_general_ci NULL DEFAULT NULL,
                               `type` int(11) NULL DEFAULT NULL,
                               `user_id` varchar(20) CHARACTER SET utf16 COLLATE utf16_general_ci NULL DEFAULT NULL,
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf16 COLLATE = utf16_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ba_opt_log
-- ----------------------------
INSERT INTO `ba_opt_log` VALUES ('1235230651578454016', 1583336911468, 1583336911468, 'Chrome 8', '127.0.0.1', NULL, 'com.codeif.user.controller.ResourceController.updateByPrimaryKey()', 'Mac OS X', NULL, 3, '1234533425');

-- ----------------------------
-- Table structure for movie
-- ----------------------------
DROP TABLE IF EXISTS `movie`;
CREATE TABLE `movie`  (
                          `id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                          `name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '电影名称',
                          `desc` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '电影描述',
                          `classify` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '类别',
                          `actor` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '主演',
                          `director` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '导演',
                          `cover_pic` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '封面图',
                          `pics` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '图片地址',
                          `magnet_url` varchar(5000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '磁力下载地址',
                          `online _url` varchar(5000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '在线播放地址',
                          `pub_date` bigint(20) NOT NULL COMMENT '发布日期',
                          `rating` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '评分',
                          `source` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '来源',
                          `visits` int(11) NOT NULL DEFAULT 0 COMMENT '阅读数',
                          `is_recommend` int(11) NOT NULL DEFAULT 0 COMMENT '是否推荐，0不推荐，1推荐',
                          `update_at` bigint(20) NOT NULL,
                          `create_at` bigint(20) NOT NULL,
                          PRIMARY KEY (`id`) USING BTREE,
                          INDEX `idx_pu_date`(`pub_date`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of movie
-- ----------------------------
INSERT INTO `movie` VALUES ('a3fbe912b2b111eabd2b001a7dda7113', '一条狗的回家路', '萌宠生死逃亡为爱涉险', '乔纳·豪尔,艾什莉·贾德,布莱丝·达拉斯·霍华德', '喜剧', 'Charles Martin Smith', 'http://img01.sogoucdn.com/v2/thumb/resize/w/150/h/201?appid=100140019&url=http%3A%2F%2Fpic4.iqiyipic.com%2Fimage%2F20190810%2Ff1%2F0d%2Fv_120905851_m_601_m9_195_260.jpg', '1', '1', '1', 1, '8.8分', '', 1, 1, 2020, 2020);
INSERT INTO `movie` VALUES ('a449b3aeb2b111eab91d001a7dda7113', '环太平洋:雷霆再起', '景甜开挂拯救世界', '约翰·波耶加,斯科特·伊斯特伍德,卡莉·史派妮', '科幻', '斯蒂文·S·迪奈特', 'http://img04.sogoucdn.com/v2/thumb/resize/w/150/h/201?appid=100140019&url=http%3A%2F%2Fr1.ykimg.com%2F050E40005B2CCEFB859B5E46450CAECB', '1', '1', '1', 1, '5.5分', '', 1, 1, 2020, 2020);
INSERT INTO `movie` VALUES ('a454a11ab2b111ea84f2001a7dda7113', '亡命救赎', '在枪林弹雨中求生', '梅尔·吉布森,伊丽莎白·霍尔姆,威廉姆·h·梅西', '动作', '让-弗朗西斯·瑞切', 'http://img04.sogoucdn.com/v2/thumb/resize/w/150/h/201?appid=100140019&url=http%3A%2F%2F1img.mgtv.com%2Fpreview%2Finternettv%2Fsp_images%2Fott%2F2018%2Fdianying%2F304933%2F20181031162608298-new.jpg', '1', '1', '1', 1, '5.8分', '', 1, 1, 2020, 2020);
INSERT INTO `movie` VALUES ('a45f9d7ab2b111eabd38001a7dda7113', '同等族群', '暂无', '克莉絲汀·史都華,尼古拉斯·霍尔特,杰基·韦佛', '喜剧', '德雷克·多雷穆斯', 'http://img02.sogoucdn.com/v2/thumb/resize/w/150/h/201?appid=100140019&url=http%3A%2F%2Fi0.letvimg.com%2Flc05_isvrs%2F201611%2F25%2F09%2F52%2Fc8b849d9-5044-4318-b976-88173c3ee9ea.jpg', '1', '1', '1', 1, '6.2分', '', 1, 1, 2020, 2020);
INSERT INTO `movie` VALUES ('a46a9264b2b111ea841b001a7dda7113', '记忆重现', '反套路烧脑大片', '朱莉娅·奥蒙德,colin lawrence,彼特·丁拉基', '科幻', '马克·波兰斯基', 'http://img02.sogoucdn.com/v2/thumb/resize/w/150/h/201?appid=100140019&url=http%3A%2F%2Fimg03.sogoucdn.com%2Fapp%2Fa%2F200547%2Fc0fe93cc6573eeb65076e1f0e202f48a.jpg', '1', '1', '1', 1, '6.2分', '', 1, 1, 2020, 2020);
INSERT INTO `movie` VALUES ('a475a6e8b2b111eaa8f2001a7dda7113', '安德的游戏', '高智商熊孩子拯救世界', '阿沙·巴特菲尔德,阿比盖尔·布蕾斯琳,哈里森·福特', '动作', '加文·胡德', 'http://img02.sogoucdn.com/v2/thumb/resize/w/150/h/201?appid=100140019&url=http%3A%2F%2Fi1.letvimg.com%2Fvrs%2F201308%2F07%2F645907bdf9974f17a722f09687e56355.jpg', '1', '1', '1', 1, '6.9分', '', 1, 1, 2020, 2020);
INSERT INTO `movie` VALUES ('a4809beeb2b111ea99a1001a7dda7113', '屠魔战士', '科学怪人乱斗哥特怪物', '艾伦·艾克哈特,伊冯娜·斯特拉霍夫斯基,miranda otto', '动作', '暂无', 'http://img01.sogoucdn.com/v2/thumb/resize/w/150/h/201?appid=100140019&url=http%3A%2F%2Fpic6.qiyipic.com%2Fimage%2F20150713%2Fa8%2F21%2Fv_109287044_m_601_m1_195_260.jpg', '1', '1', '1', 1, '5.2分', '', 1, 1, 2020, 2020);
INSERT INTO `movie` VALUES ('a48b910ab2b111eab2b1001a7dda7113', '战犬瑞克斯', '英雄战犬迅猛出击', '凯特·玛拉,汤姆·费尔顿', '战争', '加芙列拉·考珀斯维特', 'http://img01.sogoucdn.com/v2/thumb/resize/w/150/h/201?appid=100140019&url=http%3A%2F%2Fpic8.iqiyipic.com%2Fimage%2F20180530%2F93%2F01%2Fv_115453877_m_601_m4_195_260.jpg', '1', '1', '1', 1, '6.8分', '', 1, 1, 2020, 2020);
INSERT INTO `movie` VALUES ('a49685e8b2b111ea933f001a7dda7113', '通勤营救', '硬汉连姆搏命缉凶', '连姆·尼森,维拉·法米加,帕特里克·威尔森', '动作', '佐米·希尔拉', 'http://img01.sogoucdn.com/v2/thumb/resize/w/150/h/201?appid=100140019&url=http%3A%2F%2Fpic8.iqiyipic.com%2Fimage%2F20180511%2Fde%2Fdd%2Fv_113702708_m_601_m6_195_260.jpg', '1', '1', '1', 1, '8.2分', '', 1, 1, 2020, 2020);
INSERT INTO `movie` VALUES ('a4a18236b2b111eaa372001a7dda7113', '布拉德的中年危机', '本斯蒂勒治愈家庭喜剧', '本·斯蒂勒,卢克·威尔逊,麦克·怀特', '喜剧', '麦克·怀特', 'http://img02.sogoucdn.com/v2/thumb/resize/w/150/h/201?appid=100140019&url=https%3A%2F%2Fimg3.doubanio.com%2Fview%2Fphoto%2Fm%2Fpublic%2Fp2497190395.jpg', '1', '1', '1', 1, '0分', '', 1, 1, 2020, 2020);
INSERT INTO `movie` VALUES ('a4ae6ab6b2b111ea896d001a7dda7113', '24小时:末路重生', '职业杀手极速复仇', '伊桑·霍克,保罗·安德森', '科幻', '布莱恩·史莫兹', 'http://img01.sogoucdn.com/v2/thumb/resize/w/150/h/201?appid=100140019&url=http%3A%2F%2Fpic9.iqiyipic.com%2Fimage%2F20180330%2F25%2F81%2Fv_112855606_m_601_m7_195_260.jpg', '1', '1', '1', 1, '7.3分', '', 1, 1, 2020, 2020);
INSERT INTO `movie` VALUES ('a4b9876cb2b111ea9449001a7dda7113', '英伦对决', '成龙对决“007”', '成龙,皮尔斯·布鲁斯南,刘涛', '动作', '马丁·坎贝尔', 'http://img01.sogoucdn.com/v2/thumb/resize/w/150/h/201?appid=100140019&url=http%3A%2F%2Fpic3.iqiyipic.com%2Fimage%2F20180220%2Fec%2F51%2Fv_112881096_m_601_m4_195_260.jpg', '1', '1', '1', 1, '7.1分', '', 1, 1, 2020, 2020);
INSERT INTO `movie` VALUES ('a4c4545ab2b111ea8508001a7dda7113', '王牌保镖', '贱萌搭档爆笑逃亡', '瑞安·雷诺兹,塞缪尔·杰克逊,加里·奥德曼', '喜剧', '帕特里克·休斯', 'http://img01.sogoucdn.com/v2/thumb/resize/w/150/h/201?appid=100140019&url=http%3A%2F%2Fpic7.iqiyipic.com%2Fimage%2F20180220%2F5d%2F97%2Fv_113659968_m_601_m2_195_260.jpg', '1', '1', '1', 1, '7.2分', '', 1, 1, 2020, 2020);
INSERT INTO `movie` VALUES ('a4cf9742b2b111ea9267001a7dda7113', '鲨海', '长腿姐妹撕巨鲨', '克莱尔·霍尔特,曼迪·摩尔,马修·莫迪恩', '剧情', '约翰内斯·罗伯茨', 'http://img01.sogoucdn.com/v2/thumb/resize/w/150/h/201?appid=100140019&url=http%3A%2F%2Fpic5.qiyipic.com%2Fimage%2F20180220%2F16%2F42%2Fv_112875567_m_601_m7_195_260.jpg', '1', '1', '1', 1, '4.8分', '', 1, 1, 2020, 2020);
INSERT INTO `movie` VALUES ('a4da45a8b2b111eaba23001a7dda7113', '长城', '景甜鹿晗决战饕餮', '马特·达蒙,景甜,威廉·达福', '动作', '张艺谋', 'http://img01.sogoucdn.com/v2/thumb/resize/w/150/h/201?appid=100140019&url=http%3A%2F%2Fpic9.qiyipic.com%2Fimage%2F20170601%2F3c%2F4d%2Fv_112396596_m_601_m2_195_260.jpg', '1', '1', '1', 1, '4.9分', '', 1, 1, 2020, 2020);
INSERT INTO `movie` VALUES ('a4e55a50b2b111ea9cdb001a7dda7113', '掠夺者', '暴力烧脑 杀戮游戏', 'bruce willis,克里斯托弗·米洛尼,艾德里安·格尼尔', '动作', '史蒂芬·C·米勒', 'http://img01.sogoucdn.com/v2/thumb/resize/w/150/h/201?appid=100140019&url=http%3A%2F%2Fpic7.qiyipic.com%2Fimage%2F20170928%2F19%2F87%2Fv_112874706_m_601_m2_195_260.jpg', '1', '1', '1', 1, '5.4分', '', 1, 1, 2020, 2020);
INSERT INTO `movie` VALUES ('a4f04f52b2b111eaa390001a7dda7113', '当怪物来敲门', '年度暗黑之作来袭', '刘易斯·麦克杜格尔,西格妮·韦弗,菲丽希缇·琼斯', '奇幻', '胡安·安东尼奥·巴亚纳', 'http://img04.sogoucdn.com/v2/thumb/resize/w/150/h/201?appid=100140019&url=http%3A%2F%2Fr1.ykimg.com%2F050E0000591924A3ADBC09DE780CE6EA', '1', '1', '1', 1, '7.2分', '', 1, 1, 2020, 2020);
INSERT INTO `movie` VALUES ('a4fb447ab2b111ea89e2001a7dda7113', '雄狮', '澳洲版”失孤”戳泪腺', '戴夫·帕特尔,鲁妮·玛拉,大卫·文翰', '剧情', '加斯·戴维斯', 'http://img04.sogoucdn.com/v2/thumb/resize/w/150/h/201?appid=100140019&url=http%3A%2F%2Fr1.ykimg.com%2F050E0000594384E5859B5D81AA0CD9C7', '1', '1', '1', 1, '7.4分', '', 1, 1, 2020, 2020);
INSERT INTO `movie` VALUES ('a506d582b2b111eab1a0001a7dda7113', '刺客信条', '法鲨半裸出镜穿梭古今', '迈克尔·法斯宾德,玛丽昂·歌迪亚,杰瑞米·艾恩斯', '科幻', '贾斯汀·库泽尔', 'http://img01.sogoucdn.com/v2/thumb/resize/w/150/h/201?appid=100140019&url=http%3A%2F%2Fpic0.qiyipic.com%2Fimage%2F20170427%2Ffa%2F2f%2Fv_112191714_m_601_m1_195_260.jpg', '1', '1', '1', 1, '5.4分', '', 1, 1, 2020, 2020);
INSERT INTO `movie` VALUES ('a51460a8b2b111eaa8d4001a7dda7113', '一条狗的使命', '忠犬八公导演催泪新作', '布丽特·罗伯森,丹尼斯·奎德,佩吉·利普顿', '喜剧', '拉斯·霍尔斯道姆', 'http://img01.sogoucdn.com/v2/thumb/resize/w/150/h/201?appid=100140019&url=http%3A%2F%2Fpic5.qiyipic.com%2Fimage%2F20170503%2F19%2F13%2Fv_112223809_m_601_m1_195_260.jpg', '1', '1', '1', 1, '7.7分', '', 1, 1, 2020, 2020);
INSERT INTO `movie` VALUES ('a51f55d4b2b111eabb64001a7dda7113', '比利·林恩的中场战事', '小鲜肉上演铁骨柔情', '乔·阿尔文,克里斯汀·斯图尔特,加内特·赫德兰', '喜剧', '李安', 'http://img04.sogoucdn.com/v2/thumb/resize/w/150/h/201?appid=100140019&url=https%3A%2F%2Fimg3.doubanio.com%2Fview%2Fphoto%2Fphoto%2Fpublic%2Fp2391542403.jpg', '1', '1', '1', 1, '8.5分', '', 1, 1, 2020, 2020);
INSERT INTO `movie` VALUES ('a52a7262b2b111eaa402001a7dda7113', '血战钢锯岭', '美国抗日神片', '安德鲁·加菲尔德,萨姆·沃辛顿,文斯·沃恩', '战争', '梅尔·吉布森', 'http://img02.sogoucdn.com/v2/thumb/resize/w/150/h/201?appid=100140019&url=http%3A%2F%2Fp9.123.sogoucdn.com%2Fimgu%2F2017%2F04%2F20170410123014_875.jpg', '1', '1', '1', 1, '8.7分', '', 1, 1, 2020, 2020);
INSERT INTO `movie` VALUES ('a5354740b2b111eab22d001a7dda7113', '圆梦巨人', '斯皮尔伯格打造奇异世界', '马克·里朗斯,鲁比·巴恩希尔,比尔·哈德尔', '剧情', '史蒂文·斯皮尔伯格', 'http://img04.sogoucdn.com/v2/thumb/resize/w/150/h/201?appid=100140019&url=http%3A%2F%2F4img.mgtv.com%2Fpreview%2Finternettv%2Fsp_images%2Fott%2F2016%2Fdianying%2F292444%2F20161227155148916-new.jpg', '1', '1', '1', 1, '6.7分', '', 1, 1, 2020, 2020);
INSERT INTO `movie` VALUES ('a5403498b2b111ea9a99001a7dda7113', '黑海夺金', '裘德洛率十二怒汉摸金', '裘德·洛,茱蒂·威泰克,本·门德尔森', '科幻', '暂无', 'http://img01.sogoucdn.com/v2/thumb/resize/w/150/h/201?appid=100140019&url=http%3A%2F%2Fpic8.qiyipic.com%2Fimage%2F20161020%2F96%2Fd6%2Fv_111147266_m_601_m2_195_260.jpg', '1', '1', '1', 1, '6.8分', '', 1, 1, 2020, 2020);
INSERT INTO `movie` VALUES ('a54b30c0b2b111eaa4eb001a7dda7113', '废柴特工', '卷毛暮光女变搞怪CP', '杰西·艾森伯格,克里斯汀·斯图尔特,托弗·戈瑞斯', '科幻', '尼玛·诺里扎德', 'http://img04.sogoucdn.com/v2/thumb/resize/w/150/h/201?appid=100140019&url=http%3A%2F%2Fimg02.sogoucdn.com%2Fapp%2Fa%2F200547%2Fcd68b1971662ebe17320f87be9547bb3.jpg', '1', '1', '1', 1, '6分', '', 1, 1, 2020, 2020);
INSERT INTO `movie` VALUES ('a5564566b2b111eaab75001a7dda7113', '伦敦陷落', '美总统伦敦街头被追杀', '杰拉德·巴特勒,艾伦·艾克哈特,摩根·弗里曼', '警匪', '巴巴克·纳加非', 'http://img01.sogoucdn.com/v2/thumb/resize/w/150/h/201?appid=100140019&url=http%3A%2F%2Fpic2.qiyipic.com%2Fimage%2F20160525%2F76%2F6c%2Fv_110422490_m_601_m1_195_260.jpg', '1', '1', '1', 1, '6.1分', '', 1, 1, 2020, 2020);
INSERT INTO `movie` VALUES ('a5613e4ab2b111ea8b1b001a7dda7113', '疯狂外星人', '谢耳朵献声Q萌外星人', '吉姆·帕森斯,蕾哈娜,史蒂夫·马丁', '喜剧', '蒂姆·约翰逊', 'http://img01.sogoucdn.com/v2/thumb/resize/w/150/h/201?appid=100140019&url=http%3A%2F%2Fpic7.qiyipic.com%2Fimage%2F20150805%2Fdc%2F0a%2Fv_109133513_m_601_m2_195_260.jpg', '1', '1', '1', 1, '7.5分', '', 1, 1, 2020, 2020);
INSERT INTO `movie` VALUES ('a56c3706b2b111ea8464001a7dda7113', '饥饿游戏3:嘲笑鸟(上)', '游戏残酷再升级', '詹妮弗·劳伦斯,乔什·哈切森,利亚姆·海姆斯沃斯', '科幻', '弗朗西斯·劳伦斯', 'http://img02.sogoucdn.com/v2/thumb/resize/w/150/h/201?appid=100140019&url=http%3A%2F%2Fp7.123.sogoucdn.com%2Fimgu%2F2017%2F03%2F20170308172616_375.jpg', '1', '1', '1', 1, '5.8分', '', 1, 1, 2020, 2020);
INSERT INTO `movie` VALUES ('a577248cb2b111ea8d4f001a7dda7113', '第七子:降魔之战', '征服黑暗女王之旅', '本·巴恩斯,杰夫·布里吉斯,朱丽安·摩尔', '奇幻', '谢尔盖·波德罗夫', 'http://img04.sogoucdn.com/v2/thumb/resize/w/150/h/201?appid=100140019&url=http%3A%2F%2Fp5.123.sogoucdn.com%2Fimgu%2F2017%2F03%2F20170308173334_883.jpg', '1', '1', '1', 1, '5分', '', 1, 1, 2020, 2020);
INSERT INTO `movie` VALUES ('a5824064b2b111ea94ea001a7dda7113', '依然爱丽丝', '朱丽安·摩尔冲击奥斯卡', '朱丽安·摩尔,凯特·波茨沃斯,肖恩·麦克雷', '剧情', '理查德·格雷泽', 'http://img02.sogoucdn.com/v2/thumb/resize/w/150/h/201?appid=100140019&url=http%3A%2F%2Fp7.123.sogoucdn.com%2Fimgu%2F2015%2F04%2F20150417143712_671.jpg', '1', '1', '1', 1, '7.8分', '', 1, 1, 2020, 2020);

-- ----------------------------
-- Table structure for us_resource
-- ----------------------------
DROP TABLE IF EXISTS `us_resource`;
CREATE TABLE `us_resource`  (
                                `id` varchar(20) CHARACTER SET utf16 COLLATE utf16_general_ci NOT NULL,
                                `create_at` bigint(20) NULL DEFAULT NULL,
                                `update_at` bigint(20) NULL DEFAULT NULL,
                                `code` varchar(20) CHARACTER SET utf16 COLLATE utf16_general_ci NULL DEFAULT NULL,
                                `component` varchar(30) CHARACTER SET utf16 COLLATE utf16_general_ci NULL DEFAULT NULL,
                                `description` varchar(200) CHARACTER SET utf16 COLLATE utf16_general_ci NOT NULL,
                                `icon` varchar(50) CHARACTER SET utf16 COLLATE utf16_general_ci NULL DEFAULT NULL,
                                `is_hidden` int(11) NULL DEFAULT NULL,
                                `method` varchar(20) CHARACTER SET utf16 COLLATE utf16_general_ci NULL DEFAULT NULL,
                                `name` varchar(20) CHARACTER SET utf16 COLLATE utf16_general_ci NOT NULL,
                                `parent_id` varchar(20) CHARACTER SET utf16 COLLATE utf16_general_ci NULL DEFAULT NULL,
                                `path` varchar(100) CHARACTER SET utf16 COLLATE utf16_general_ci NULL DEFAULT NULL,
                                `sort` float NULL DEFAULT NULL,
                                `type` varchar(10) CHARACTER SET utf16 COLLATE utf16_general_ci NULL DEFAULT NULL,
                                `url` varchar(30) CHARACTER SET utf16 COLLATE utf16_general_ci NULL DEFAULT NULL,
                                PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf16 COLLATE = utf16_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of us_resource
-- ----------------------------
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

-- ----------------------------
-- Table structure for us_role
-- ----------------------------
DROP TABLE IF EXISTS `us_role`;
CREATE TABLE `us_role`  (
                            `id` varchar(20) CHARACTER SET utf16 COLLATE utf16_general_ci NOT NULL,
                            `create_at` bigint(20) NULL DEFAULT NULL,
                            `update_at` bigint(20) NULL DEFAULT NULL,
                            `role_code` varchar(20) CHARACTER SET utf16 COLLATE utf16_general_ci NOT NULL,
                            `role_desc` varchar(200) CHARACTER SET utf16 COLLATE utf16_general_ci NULL DEFAULT NULL,
                            `role_name` varchar(20) CHARACTER SET utf16 COLLATE utf16_general_ci NOT NULL,
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf16 COLLATE = utf16_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of us_role
-- ----------------------------
INSERT INTO `us_role` VALUES ('1119477949450088449', 1583246987537, 1583510426731, '12', '管理员', '管理员');
INSERT INTO `us_role` VALUES ('1257559802842845184', 1588689395980, 1588689395980, 'guest', 'guest', '访客');

-- ----------------------------
-- Table structure for us_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `us_role_resource`;
CREATE TABLE `us_role_resource`  (
                                     `role_id` varchar(20) CHARACTER SET utf16 COLLATE utf16_general_ci NOT NULL,
                                     `resource_id` varchar(20) CHARACTER SET utf16 COLLATE utf16_general_ci NOT NULL,
                                     PRIMARY KEY (`role_id`, `resource_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf16 COLLATE = utf16_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of us_role_resource
-- ----------------------------
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
INSERT INTO `us_role_resource` VALUES ('1257559802842845184', '1136521380156239873');
INSERT INTO `us_role_resource` VALUES ('1257559802842845184', '1136521574579007490');
INSERT INTO `us_role_resource` VALUES ('1257559802842845184', '1136521751205343233');

-- ----------------------------
-- Table structure for us_user
-- ----------------------------
DROP TABLE IF EXISTS `us_user`;
CREATE TABLE `us_user`  (
                            `id` varchar(20) CHARACTER SET utf16 COLLATE utf16_general_ci NOT NULL,
                            `create_at` bigint(20) NULL DEFAULT NULL,
                            `update_at` bigint(20) NULL DEFAULT NULL,
                            `account` varchar(20) CHARACTER SET utf16 COLLATE utf16_general_ci NOT NULL,
                            `avatar` varchar(300) CHARACTER SET utf16 COLLATE utf16_general_ci NULL DEFAULT NULL,
                            `birthday` bigint(20) NULL DEFAULT NULL,
                            `contact_address` varchar(200) CHARACTER SET utf16 COLLATE utf16_general_ci NULL DEFAULT NULL,
                            `email` varchar(30) CHARACTER SET utf16 COLLATE utf16_general_ci NOT NULL,
                            `fans_count` int(11) NULL DEFAULT NULL,
                            `follow_count` int(11) NULL DEFAULT NULL,
                            `interest` varchar(30) CHARACTER SET utf16 COLLATE utf16_general_ci NULL DEFAULT NULL,
                            `last_date` bigint(20) NULL DEFAULT NULL,
                            `nick_name` varchar(80) CHARACTER SET utf16 COLLATE utf16_general_ci NOT NULL,
                            `online_time` bigint(20) NULL DEFAULT NULL,
                            `password` varchar(100) CHARACTER SET utf16 COLLATE utf16_general_ci NOT NULL,
                            `personality` varchar(20) CHARACTER SET utf16 COLLATE utf16_general_ci NULL DEFAULT NULL,
                            `phone` varchar(15) CHARACTER SET utf16 COLLATE utf16_general_ci NOT NULL,
                            `registered_type` varchar(10) CHARACTER SET utf16 COLLATE utf16_general_ci NULL DEFAULT NULL,
                            `sex` int(11) NOT NULL,
                            `status` int(11) NULL DEFAULT NULL,
                            `user_name` varchar(40) CHARACTER SET utf16 COLLATE utf16_general_ci NOT NULL,
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf16 COLLATE = utf16_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of us_user
-- ----------------------------
INSERT INTO `us_user` VALUES ('123', 1559573395828, 1583510501512, 'guest', 'https://vue-admin-guoguang.oss-cn-shanghai.aliyuncs.com/cover.jpg', 20181128125747, '省市县', '1234@email.com', 121, 27, '150', NULL, 'zhangsan', 1559573395828, '$2a$10$XxVXtdhNBHBMHm4QEc0jOukm5YjcsO4cJ/UEOzeMS/PgjAPXg..FW', '', '17667198751', '1', 1, 1, 'guest');
INSERT INTO `us_user` VALUES ('1234533425', 1559573395828, 1594847528545, 'admin', 'https://vue-admin-guoguang.oss-cn-shanghai.aliyuncs.com/cover.jpg', 20181128125747, '省市县', '1234@email.com', 121, 27, '150', NULL, 'admin', 1559573395828, '$2a$10$mB5vXNWA8oCg7euvJiYbl.Uno9ov.Kcf4ySfQQ3wFTnRtvk5bHJcu', '', '17667198751', '1', 1, 1, '管理员');

-- ----------------------------
-- Table structure for us_user_role
-- ----------------------------
DROP TABLE IF EXISTS `us_user_role`;
CREATE TABLE `us_user_role`  (
                                 `user_id` varchar(20) CHARACTER SET utf16 COLLATE utf16_general_ci NOT NULL,
                                 `role_id` varchar(20) CHARACTER SET utf16 COLLATE utf16_general_ci NOT NULL,
                                 PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf16 COLLATE = utf16_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of us_user_role
-- ----------------------------
INSERT INTO `us_user_role` VALUES ('123', '1257559802842845184');
INSERT INTO `us_user_role` VALUES ('1234533425', '1119477949450088449');

SET FOREIGN_KEY_CHECKS = 1;
