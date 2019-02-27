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

 Date: 27/02/2019 09:53:32
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
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '文章正文',
  `image` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章封面',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '发表日期',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '修改日期',
  `is_public` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否公开',
  `is_top` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否置顶',
  `visits` int(20) NULL DEFAULT NULL COMMENT '浏览量',
  `upvote` int(20) NULL DEFAULT NULL COMMENT '点赞数',
  `comment` int(20) NULL DEFAULT NULL COMMENT '评论数',
  `review_state` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审核状态',
  `url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'URL',
  `type` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型',
  `importance` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '热度',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文章板块/文章管理' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of ar_article
-- ----------------------------
INSERT INTO `ar_article` VALUES (1, 12151044, '12151044', '受脑认知和神经科学启发的人工智能', '人工智能渗透到了人类社会各个领域，但目前来看，无论是深度学习还是其它方法，解决的都是单一问题。人类大脑是一个多问题求解的结构，怎么从脑认知和神经科学中得到构造健壮的人工智能的启示，国内外都做了非常多有成效的研究。\r\n\r\n一、实现健壮的人工智能的方法\r\n\r\n人类面临的许多问题具有不确定性、脆弱性和开放性。今天人工智能的理论框架，建立在演绎逻辑和语义描述的基础方法之上，但我们不可能对人类社会的所有问题建模，因为这中间存在着条件问题，我们不可能把一个行为的所有条件都模拟出，这是传统人工智能的局限性。\r\n\r\n这个局限性主要表现在几个方面：\r\n\r\n1、需要对问题本身抽象出一个精确数学意义上的解析式的数学模型（抽象不出，即归纳为不可解问题）；\r\n\r\n2、需要为已建立的数据模型设计出确定的算法（容易产生诸如NPC等问题）；\r\n\r\n3、处理的结果无法表现现实世界所固有的不确定性；\r\n\r\n4、图灵意义下的可计算问题都是可递归的（“可递归的”都是有序的）；\r\n\r\n5、用“度量”来区分模式，只能处理可向量化的数据。\r\n\r\n我们要构造一种更加健壮的人工智能，需要脑认知和神经科学的启发。计算机和人类大脑是对问题求解的物质基础。在智力和计算能力方面，计算机远远超过了人类，但是人类面对的大部分问题都是开放的、动态的、复杂的，大脑在处理这种问题时表现出的想象和创造，还有对复杂问题的分析和描述，是传统人工智能的方法所不能企及的，我们只能够从人类大脑的神经网络结构中去获得构造新的人工智能的因素。\r\n\r\n人类大脑非常奇妙，也正是在这个物质基础之上，才演绎出人类世界的发展和对问题求解的各种方法。在神经元的结构模型中，神经元的连接并不是像我们一般理解的物理方式，而是靠突触，神经元之间突触间隙产生的反应，构成了大脑中奇妙的演进。人类大脑中的思维或学习都是发生在突触这个层面上的。实际上在大脑的神经网络连接中，不同空间对应不同功能，不同功能在自身内部产生着不同的成本函数。\r\n\r\n人出生之后，大脑会不断发展，发展到一定程度，神经元增长到一定数量，又会递减，把不需要的神经元删掉。大脑是慢性记忆神经元，它需要具有高度的容错性。\r\n\r\n实际上，人出生时大脑是一样的，如三字经所提到的“性相近，习相远”，6岁以前，大脑在发育，到6岁左右，从生物学角度上讲，这种发育就完成了，大家的记忆力、智商等都是教育上的反应。教育的基础就是大脑。所以，大脑不是通过一个统一的没有分化的神经网络来实现单一的全景优化学习的，不同的功能和区域会生成不同的成本函数。它是模块化的，同时具有独特的系统来支撑注意、记忆、语言等功能。因此，我们可以从脑认知和神经科学中去获得发展新的人工智能的灵感。\r\n\r\n二、脑认知与人工智能的结合\r\n\r\n人类大脑有800亿个神经元的容量，它主要有三种研究方式：结构研究、功能研究和有效研究。\r\n\r\n大脑的结构连接是静态的，功能连接和有效研究则具有时空动态演化的特性。在视觉和听觉神经网络的区域空间中，功能连接和有效连接是不一样的。\r\n\r\n有效连接是针对具体任务的，在同一个视觉功能连接空间中，当我们执行不同视觉任务时，它所形成的神经网络的有效连接是不一样的。有效连接描述了神经元之间的因果与相互影响关系。\r\n\r\n从这种结构化的观点来看，我们构造的神经网络还没办法模拟同时具有结构连接、功能连接、有效连接的方式。我们可以通过获取某一区域的活跃程度，或活跃状态，辨别大脑正在执行什么样的视觉任务。知道它在执行什么样的视觉任务，我们就得到了它有效连接的状态，也可以求出它的有效连接在时空演化中的特性。如果能够求出其中的规律，我们就可以设计相应的人工智能方式去实现。也就是说，我们可以采用可触的、动态的、非线性的关系网络进行认知任务的输入。\r\n\r\n再对它的科学问题做一个总结，我们要回答出三点：1、大脑是如何实现优化的；2、脑网络的监督训练信号从哪里来；3、在不同的神经功能研究区域中，存在什么样的有效连接的约束和优化。\r\n\r\n前面讲了概念，在概念基础上我们要抽象出科学问题，这样才能指导我们进一步的研究，找到解决问题的方法。那么这个方法如何和现在的方法结合？\r\n\r\n去年，谷歌和MIT联合发表了一篇文章，文章的中心思想是怎么利用神经科学构造健壮的人工智能系统。我们现在深度学习的基本框架，是通过多层神经网络输入，根据误差来调整连接，这建立在大量数据标注的基础上，通过标记数据得到网络优化的成本函数。\r\n\r\n我要强调一点，我们通常讲深度学习是从机器学习发展来的，要构造一个学习机器，关键是在不同区域、不同任务下，怎么去构造一个成本函数。\r\n\r\n三、大脑的认知活动\r\n\r\n大脑的认知活动分为三个不同层次：一是哲学，二是形象思维和逻辑思维，三是敏感性。\r\n\r\n直觉和敏感都属于创造性思维，警察在破案中，靠的是多年积累和实践，形成的直觉判断。灵感、顿悟与直觉的区别是，直觉是对当前环境的反应，它在现在人工智能的发展中扮演着十分重要的角色。我们需要一种基于直觉的人工智能，也可以将它看成一种基于直觉的推理。\r\n\r\n人的直觉反应实际上是寻找全局最优解。要构造直觉推理，需要两个关键因素：一是需要构造一个成本函数；二是需要给出一个决策结构，而这个决策结构就建立在记忆基础上。\r\n\r\n人在观察事物时，一定会形成一种与时间相关的影像。如果把直觉推理和数学归纳演绎推理两类机制组合，就可以实现基于认知计算或受神经科学启发的人工智能。\r\n\r\n我们把认知推理称为直观、朴素的物理推理。物理层面的认知推理可以化解时间与空间，追踪事物的发展轨迹。认知推理的另一个要素在心理层面，简而言之就是学习方向受心理状态的引导。我们需要把物理层面和心理层面的推理嵌入到推理的人工智能系统中。\r\n\r\n在直觉和认知推理中，我们还需要构造一种模型，其中因果模型是基础。认知计算框架下的因果模型既要满足物理因果关系所产生的物理约束，同时又要让机器理解当前认知任务下的因果关系。\r\n\r\n直觉推理、认知推理和因果模型是构建健壮的人工智能必须考虑的基本因素。那么如何来构造一个具体的系统？构造机器人需要三个基本要素：1、对环境中的所有对象进行特征识别，并且进行长期记忆；2、理出对象间的关系，并对它们相互间的作用进行描述；3、基于想象力的行为模型，人在进行具体行动之前，会想象其带来的后果，但机器就需要分析物体之间的各种关系。\r\n\r\n这三种要素是让机器像人一样理解物理世界的基础。具有想象力的人工智能，就需要：1、行动之前预想到结果；2、构造一个位置模型；3、给出环境模型，提取有用信息；4、规划想象行为，最大化任务效果。\r\n\r\n四、认知如何解决实际问题\r\n\r\n我们在2000年初就开始做无人驾驶，有人说要把无人驾驶汽车和城市真实场景的车融合，我们还面临非常艰难的挑战，有相当长的路要走。在这种局部、动态的场景中，我们怎样让自动驾驶跟环境融合，确实是一个很大的问题。\r\n\r\n无人驾驶的挑战存在于：\r\n\r\n1、必须准确感知周围环境，在所有条件下安全行驶；\r\n\r\n2、自动驾驶必须能够抽象，要完成一种交互情境中的记忆计算；\r\n\r\n3、自动驾驶必须能够理解预行为。\r\n\r\n现在绝大多数自动驾驶采取了场景感知与定位，决策规划与控制，这是一种简单的ADAS形式，但我们要如何通过新的方法来解决这个问题？\r\n\r\n场景是某个交互场合在特定时间和空间中的具体情境和影象，它可以定义为一种实体。情境是指这种实体随着时间和空间变化而产生的关联。情境计算是对场景各个关联的对象做解释，可以定义为一个行为相关体。\r\n\r\n这里的问题就是，第一，要让自动驾驶汽车像人一样理解和记忆，就要具有记忆推理和经验分析的技术；第二，进化发展的自动驾驶，其学习过程要像人类一样熟能生巧。\r\n\r\n人类视觉关注的基本机制是选择、组织、整合、编码。人对变化是非常敏感的，可以提取交通场景中的显著性变化。比如你在开车时，如果右前方突然来了一个骑自行车的人，你的注意力会转移到骑车人的身上。在自动驾驶汽车上，我们要构造一个选择性的注意机制网络，对数种图像进行理解，并根据内部状态的表示，忽略不相关的对象，选择下一步要采取的动作。\r\n\r\n把场景感知和情景认知结合起来，需要我们构建一个模型，融合先进知识概念，实现记忆学习。\r\n\r\n场景感知是将通过各种不同属性的传感器获得的不同数据，提供到深度学习中，之后再根据长短期记忆和定位网络，进行情境计算。在这种框架中，我们可以把场景感知和情境计算融合在一起。\r\n\r\n一个高效的情景计算要运用实际情境的因果关联，在最前端的数据层面进行有效计算，这就需要把数据驱动变成事件驱动。人在开车时，根据情境判断前方可不可以行驶，这就是把数据驱动变成事件驱动。\r\n\r\n怎么构造事件驱动？就是把可见光和激光点云数据融合在一起，把三维数据转化成二维图像数据。点云数据给出了每一个生物体的明确的点，二维图像没有深度信息，它是图像的几何形状变化。把人的数据和激光点云的数据融合，用数据驱动转变为事件驱动，就得出了可行驶数据和不可行驶数据大的划分。\r\n\r\n人开车的时候，他在注意什么，我们就来构建一个类似的选择性基础，把同样的场景输入到一个深度学习网络中，通过深度学习网络提取特征和人的注意力。', 'https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1229855747,4092679124&fm=26&gp=0.jpg', '2019-01-29 19:47:44', '2019-01-29 19:47:46', '1', '1', 25, 3562, 0, '0', NULL, NULL, '9');
INSERT INTO `ar_article` VALUES (2, 0, '2', '', '2', '2', '2019-01-30 10:16:49', '2019-01-30 10:16:49', '2', '2', 0, 3, 0, '2', '2', '2', '2');

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
-- Records of ar_category
-- ----------------------------
INSERT INTO `ar_category` VALUES (1, 1, '1', '1', '1', '2019-01-30 19:07:24', '1', '2019-01-31 19:07:22');

-- ----------------------------
-- Table structure for qa_answers
-- ----------------------------
DROP TABLE IF EXISTS `qa_answers`;
CREATE TABLE `qa_answers`  (
  `id` int(20) NOT NULL COMMENT '答案表id',
  `question_id` int(20) NULL DEFAULT NULL COMMENT '问题表ID',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '答案内容',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建日期',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新日期',
  `user_id` int(20) NULL DEFAULT NULL COMMENT '回答人ID',
  `nick_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '回答人昵称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '回答' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for qa_question
-- ----------------------------
DROP TABLE IF EXISTS `qa_question`;
CREATE TABLE `qa_question`  (
  `id` int(20) NOT NULL COMMENT 'ID',
  `title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容',
  `tags_id` int(20) NULL DEFAULT NULL COMMENT '问题标签表',
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
