/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost
 Source Database       : website

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : utf-8

 Date: 11/06/2017 18:22:05 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `p_accounting`
-- ----------------------------
DROP TABLE IF EXISTS `p_accounting`;
CREATE TABLE `p_accounting` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `value` varchar(20) NOT NULL,
  `icon` varchar(25) DEFAULT NULL,
  `version` bigint(10) DEFAULT NULL,
  `when_created` date DEFAULT NULL,
  `when_modified` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `accounting_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='数据统计';

-- ----------------------------
--  Records of `p_accounting`
-- ----------------------------
BEGIN;
INSERT INTO `p_accounting` VALUES ('1', '关注人数', '16850', 'fa fa-heart', null, null, null), ('2', '团队成员', '56', 'fa fa-users', null, null, null), ('3', '完成项目', '145', 'fa fa-home', null, null, null), ('4', '收入', '12000000', 'fa fa-money', null, null, null);
COMMIT;

-- ----------------------------
--  Table structure for `p_advice_feedback`
-- ----------------------------
DROP TABLE IF EXISTS `p_advice_feedback`;
CREATE TABLE `p_advice_feedback` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(32) NOT NULL COMMENT '姓名',
  `qq` varchar(32) DEFAULT NULL COMMENT 'qq',
  `email` varchar(64) DEFAULT NULL COMMENT 'email',
  `telphone` varchar(32) DEFAULT NULL COMMENT '手机号',
  `content` varchar(2000) DEFAULT NULL COMMENT '意见反馈内容',
  `remark` varchar(2000) DEFAULT NULL COMMENT '备注',
  `when_created` date DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='意见反馈';

-- ----------------------------
--  Records of `p_advice_feedback`
-- ----------------------------
BEGIN;
INSERT INTO `p_advice_feedback` VALUES ('1', '系统管理员', '333', '333', '33', null, '333333333', '2016-01-29'), ('2', '系统管理员', '330321321', '223@sina.com', '13983283282', null, '系统很不错~等待升级', '2016-01-29'), ('3', '系统管理员', '123', '123', '123', null, '123', '2016-01-29'), ('4', '系统管理员', '321', '2222', '2222', null, '222', '2016-01-29'), ('5', '系统管理员', '33333333', '33333333333333333', '33333333333', null, '3333333333333333333333', '2016-01-29'), ('6', '系统管理员', '111111111', '11111111111111', '1111111111111', null, '11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111', '2016-01-29');
COMMIT;

-- ----------------------------
--  Table structure for `p_article`
-- ----------------------------
DROP TABLE IF EXISTS `p_article`;
CREATE TABLE `p_article` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `folder_id` int(11) DEFAULT '1' COMMENT '目录id',
  `title` varchar(200) DEFAULT '' COMMENT '文章名称',
  `content` longtext COMMENT '文件内容',
  `status` varchar(20) DEFAULT '1' COMMENT '状态//radio/2,隐藏,1,显示',
  `sort` int(11) DEFAULT '1' COMMENT '排序',
  `jump_url` varchar(256) DEFAULT NULL COMMENT '跳转地址',
  `image_url` varchar(256) DEFAULT NULL COMMENT '图片路径',
  `icon` varchar(256) DEFAULT NULL COMMENT '矢量图',
  `version` bigint(10) DEFAULT NULL,
  `when_created` date DEFAULT NULL COMMENT '创建时间',
  `when_modified` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_article_folder_id` (`folder_id`),
  CONSTRAINT `fk_article_folder_id` FOREIGN KEY (`folder_id`) REFERENCES `p_folder` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4253 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='文章';

-- ----------------------------
--  Records of `p_article`
-- ----------------------------
BEGIN;
INSERT INTO `p_article` VALUES ('3283', '2', '祝贺我公司节能监管系统入选武汉市优秀创业项目滚动扶持项目', '我公司基于WebGIS的节能监管综合信息管理系统成功入选武汉市优秀创业项目滚动扶持项目。 “基于WebGIS的节能监管综合信息管理系统”是根据建设资源节约型社会的基本国策和高校节能降耗工作的迫切需要，采用新一代SilverLight技术，以管网综合空间地理信息、远程监控、物联网等核心技术为基础，为大型企事业单位构建管网综合空间地理信息监控、海量用能信息采集统计分析的网络化动态节能管理系统，给用能单位提供全方位的实时的节能诊断和警示服务，从而实现节能优化管理。', '1', '10', null, 'http://static.ydcss.com/uploads/ydui/1.jpg', null, null, null, null), ('3284', '2', '我公司受邀参加2014年度湖北高校能源管理及能源监管平台工作', '为进一步推进湖北高校节约型校园建设的步伐，推广高校水电管理新理念，新模式，交流先进经验，由湖北省高校后勤管理研究会水电管理专业部主办，湖北仙桃职业技术学院协办的“湖北省高校能源管理及能源监管平台工作研讨会”于2014年5月29日在仙桃召开，武汉格事化信息科技有限公司作为一家专业能源管理系统平台建设及综合节能管理企业受邀参加本次会议，并在大会就“平台建设相关经验”作出汇报演讲。', '1', '10', null, 'http://static.ydcss.com/uploads/ydui/1.jpg', null, null, null, null), ('3285', '2', '我公司入选“低碳武汉•2013年度突出贡献企业”', '认真贯彻党的十八大提出的我国经济要坚持“循环发展、绿色发展、低碳发展”的方针，落实《武汉市低碳城市试点工作方案》，由武汉市慈善总会•武汉低碳公益发展基金发起，武汉低碳经济促进会、武汉现代管理科学研究会联合举办“表彰低碳武汉•2013年度突出贡献企业暨低碳公益发展基金优质合作惠民项目征选活动”日前启动。', '1', '10', null, 'http://static.ydcss.com/uploads/ydui/1.jpg', null, null, null, null), ('3286', '3', '三维数字校园信息系统', '三维数字校园管理系统是一种集数字化、信息化、可视化等多种技术为一体的校园计算机管理应用系统。三维数字校园能真实地反映校园地上和地下的全貌。', '1', '10', null, 'http://static.ydcss.com/uploads/ydui/1.jpg', null, null, null, null), ('3288', '3', '供电监控信息管理系统', '系统采用工业级应用的专用多功能通讯网关，实时监控各种RS-485接口的电表的工作状态，以及实时采集各种在线监控数据，通过局域网与后台服务器连接，保持数据同步。', '1', '10', null, 'http://static.ydcss.com/uploads/ydui/1.jpg', null, null, null, null), ('4231', '3', '供气监控信息管理系统', '该系统对各用气点实现分区、分户、分类实时计量。并通过WEB发布的形式，使得用气单位各级管理人员都可以轻松地对用气情况进行监控与管理。', '1', '9', null, 'http://static.ydcss.com/uploads/ydui/1.jpg', null, null, null, null), ('4232', '3', '路灯智能监管系统', '该系统通过灵活的组网方式，实现无线数据传输与远程控制，实现对校内各路段的路灯与景观灯根据预先设置的时间、区域等参数，自动控制照明的开启与关闭。', '1', '10', null, 'http://static.ydcss.com/uploads/ydui/1.jpg', '/jflyfox/ueditor/image/20160617/1466154234042051193.png', null, null, null), ('4233', '4', '节水分享方案', '在合同期限内，用水单位和我公司根据约定的比例共同分享节水效益，合同能源管理项目的投入由我公司单独承担.', '1', '10', null, 'http://static.ydcss.com/uploads/ydui/1.jpg', 'fa fa-connectdevelop', null, null, null), ('4234', '4', '节水保证方案', '由我公司和用水单位双方共同或任意一方单独出资实施节水项目，我公司保证承诺的节水量。', '1', '10', null, 'http://static.ydcss.com/uploads/ydui/1.jpg', 'fa fa-building-o', null, null, null), ('4235', '4', '节水托管方案', '在合同期内，用水单位按照约定的费用委托我公司进行地下供水管网系统的运行管理和/或节水改造。', '1', '10', null, 'http://static.ydcss.com/uploads/ydui/1.jpg', 'fa fa-hand-pointer-o', null, null, null), ('4236', '4', '治理漏水方案', '在合同期内，用水单位按照约定的费用委托我公司进行地下供水管网系统的运行管理和/或治理漏水。', '1', '10', null, 'http://static.ydcss.com/uploads/ydui/1.jpg', 'fa fa-align-justify', null, null, null), ('4237', '4', '建筑方案', '在合同期内，用水单位按照约定的费用委托我公司进行地下供水管网系统的运行管理和/或新建住房。', '1', '10', null, 'http://static.ydcss.com/uploads/ydui/1.jpg', 'fa fa-home', null, null, null), ('4238', '4', '运输方案', '在合同期内，用水单位按照约定的费用委托我公司进行地下供水管网系统的运行管理和/或饮用水运输。', '1', '10', null, 'http://static.ydcss.com/uploads/ydui/1.jpg/project/article_image/20160621_122708_874079.png', 'fa fa-ambulance', null, null, null);
COMMIT;

-- ----------------------------
--  Table structure for `p_class`
-- ----------------------------
DROP TABLE IF EXISTS `p_class`;
CREATE TABLE `p_class` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `bh` varchar(32) DEFAULT NULL,
  `bjmc` varchar(100) DEFAULT NULL,
  `major_id` bigint(10) DEFAULT NULL,
  `xy_id` bigint(10) DEFAULT NULL,
  `nj` varchar(20) DEFAULT NULL,
  `bz` varchar(300) DEFAULT NULL,
  `version` bigint(10) NOT NULL,
  `when_created` date NOT NULL,
  `when_modified` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `ix_p_class_major_id` (`major_id`),
  KEY `ix_p_class_xy_id` (`xy_id`),
  CONSTRAINT `fk_p_class_major_id` FOREIGN KEY (`major_id`) REFERENCES `p_major` (`id`),
  CONSTRAINT `fk_p_class_xy_id` FOREIGN KEY (`xy_id`) REFERENCES `p_dept` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `p_code_item`
-- ----------------------------
DROP TABLE IF EXISTS `p_code_item`;
CREATE TABLE `p_code_item` (
  `id` varchar(32) NOT NULL,
  `item_name` varchar(200) NOT NULL,
  `status` varchar(2) DEFAULT NULL,
  `order_no` bigint(5) DEFAULT NULL,
  `kind_id` varchar(32) DEFAULT NULL,
  `tran_code` varchar(200) DEFAULT NULL,
  `parent_id` varchar(32) DEFAULT NULL,
  `when_created` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `ix_p_code_item_kind_id` (`kind_id`),
  CONSTRAINT `fk_p_code_item_kind_id` FOREIGN KEY (`kind_id`) REFERENCES `p_code_kind` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `p_code_item`
-- ----------------------------
BEGIN;
INSERT INTO `p_code_item` VALUES ('2000102', '女', '1', null, '20001', '0', '20001', '2008-04-02'), ('200101', '男', '1', null, '20001', '1', '20001', '2008-04-02');
COMMIT;

-- ----------------------------
--  Table structure for `p_code_kind`
-- ----------------------------
DROP TABLE IF EXISTS `p_code_kind`;
CREATE TABLE `p_code_kind` (
  `id` varchar(32) NOT NULL,
  `kind_name` varchar(200) NOT NULL,
  `status` varchar(2) DEFAULT NULL,
  `display_width` bigint(5) DEFAULT NULL,
  `code_group` varchar(100) DEFAULT NULL,
  `when_created` date NOT NULL,
  `when_modified` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `p_code_kind`
-- ----------------------------
BEGIN;
INSERT INTO `p_code_kind` VALUES ('20001', '性别', '1', null, '系统代码', '2008-04-02', '2008-04-02');
COMMIT;

-- ----------------------------
--  Table structure for `p_config`
-- ----------------------------
DROP TABLE IF EXISTS `p_config`;
CREATE TABLE `p_config` (
  `id` varchar(32) NOT NULL,
  `key1` varchar(50) DEFAULT NULL,
  `value1` varchar(300) DEFAULT NULL,
  `remark` varchar(300) DEFAULT NULL,
  `when_created` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `p_config`
-- ----------------------------
BEGIN;
INSERT INTO `p_config` VALUES ('1', 'SYSTEM_NAME', '格事化门户系统', '系统名称', '2008-04-02'), ('2', 'COPYRIGHT', '2017-2018 lyy. All rights reserved.', '版权信息', '2008-04-02');
COMMIT;

-- ----------------------------
--  Table structure for `p_dept`
-- ----------------------------
DROP TABLE IF EXISTS `p_dept`;
CREATE TABLE `p_dept` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `nick_name` varchar(100) DEFAULT NULL,
  `code` varchar(50) NOT NULL,
  `type` varchar(20) DEFAULT NULL,
  `status` varchar(2) NOT NULL,
  `order_number` bigint(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `lxr` varchar(50) DEFAULT NULL,
  `is_jxdw` varchar(2) NOT NULL,
  `father_id` bigint(10) DEFAULT NULL,
  `version` bigint(10) NOT NULL,
  `when_created` date NOT NULL,
  `when_modified` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `ix_p_dept_father_id` (`father_id`),
  CONSTRAINT `fk_p_dept_father_id` FOREIGN KEY (`father_id`) REFERENCES `p_dept` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `p_dept`
-- ----------------------------
BEGIN;
INSERT INTO `p_dept` VALUES ('1', '公安局', '', '1', 'xy', '1', '1', '', '110', '1', '1', '1', '2008-04-02', '2008-04-02');
COMMIT;

-- ----------------------------
--  Table structure for `p_employee`
-- ----------------------------
DROP TABLE IF EXISTS `p_employee`;
CREATE TABLE `p_employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL COMMENT '姓名',
  `photo_id` bigint(10) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `version` bigint(10) DEFAULT NULL,
  `when_created` date DEFAULT NULL,
  `when_modified` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `p_employee_id_uindex` (`id`),
  KEY `fk_p_accounting_photo_id` (`photo_id`),
  CONSTRAINT `fk_p_accounting_photo_id` FOREIGN KEY (`photo_id`) REFERENCES `p_file` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='优秀员工';

-- ----------------------------
--  Records of `p_employee`
-- ----------------------------
BEGIN;
INSERT INTO `p_employee` VALUES ('1', '张帆', '1', '猥琐男，外号蛋蛋', null, null, null), ('2', '刘梦婷', '1', '人美声甜，辣妈', null, null, null), ('3', '六医院', '1', '不知道怎么形容了，帅的一比', null, null, null), ('4', '唐斌', '1', '闷骚男，外表平静，内心风骚', null, null, null);
COMMIT;

-- ----------------------------
--  Table structure for `p_evaluate`
-- ----------------------------
DROP TABLE IF EXISTS `p_evaluate`;
CREATE TABLE `p_evaluate` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `title` varchar(20) DEFAULT NULL COMMENT '标题',
  `content` varchar(255) DEFAULT NULL COMMENT '内容',
  `descrption` varchar(500) DEFAULT NULL COMMENT '描述',
  `image_url` varchar(100) DEFAULT NULL,
  `version` bigint(10) DEFAULT NULL,
  `when_created` date DEFAULT NULL,
  `when_modified` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `evaluate_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='客户评价';

-- ----------------------------
--  Records of `p_evaluate`
-- ----------------------------
BEGIN;
INSERT INTO `p_evaluate` VALUES ('1', '好', '真好', '很好', 'http://static.ydcss.com/uploads/ydui/1.jpg', '1', null, null), ('2', '好', '真好', '很好', 'http://static.ydcss.com/uploads/ydui/2.jpg', '1', null, null), ('3', '好', '真好', '很好', 'http://static.ydcss.com/uploads/ydui/3.jpg', '1', null, null), ('4', '好', '真好', '很好', 'http://static.ydcss.com/uploads/ydui/2.jpg', '1', null, null);
COMMIT;

-- ----------------------------
--  Table structure for `p_file`
-- ----------------------------
DROP TABLE IF EXISTS `p_file`;
CREATE TABLE `p_file` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `object_id` bigint(20) DEFAULT NULL,
  `object_name` varchar(50) DEFAULT NULL,
  `path` varchar(200) DEFAULT NULL,
  `note` varchar(200) DEFAULT NULL,
  `version` bigint(10) NOT NULL,
  `when_created` date NOT NULL,
  `when_modified` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `object_id` (`object_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `p_file`
-- ----------------------------
BEGIN;
INSERT INTO `p_file` VALUES ('1', '辣妈', '1', null, 'http://static.ydcss.com/uploads/ydui/1.jpg', null, '1', '2017-11-06', '2017-11-06'), ('2', '辣妈', '2', null, 'http://static.ydcss.com/uploads/ydui/2.jpg', null, '1', '2017-11-06', '2017-11-06'), ('3', '辣妈', '3', null, 'http://static.ydcss.com/uploads/ydui/3.jpg', null, '1', '2017-11-06', '2017-11-06'), ('4', '辣妈', '4', null, 'http://static.ydcss.com/uploads/ydui/1.jpg', null, '1', '2017-11-06', '2017-11-06'), ('5', '辣妈', '5', null, 'http://static.ydcss.com/uploads/ydui/2.jpg', null, '1', '2017-11-06', '2017-11-06');
COMMIT;

-- ----------------------------
--  Table structure for `p_folder`
-- ----------------------------
DROP TABLE IF EXISTS `p_folder`;
CREATE TABLE `p_folder` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '目录id',
  `name` varchar(100) NOT NULL DEFAULT '' COMMENT '中文名',
  `content` varchar(500) DEFAULT NULL COMMENT '描述',
  `sort` int(11) DEFAULT '1' COMMENT '排序',
  `status` int(11) DEFAULT '1' COMMENT '状态//radio/2,隐藏,1,显示',
  `jump_url` varchar(200) DEFAULT NULL COMMENT '跳转地址',
  `version` bigint(10) NOT NULL,
  `when_created` date DEFAULT NULL,
  `when_modified` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=268 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='目录';

-- ----------------------------
--  Records of `p_folder`
-- ----------------------------
BEGIN;
INSERT INTO `p_folder` VALUES ('1', '首页', null, '1', '1', 'home', '0', null, null), ('2', '新闻动态', null, '1', '1', 'news', '0', null, null), ('3', '软件推荐', null, '1', '1', 'causes', '0', null, null), ('4', '解决方案', null, '1', '1', 'service', '0', null, null), ('5', '工程服务', null, '1', '1', 'gallery', '0', null, null), ('6', '优秀员工', null, '1', '1', 'team', '0', null, null), ('7', '客户评价', null, '1', '1', 'testimonial', '0', null, null), ('8', '意见反馈', null, '1', '1', 'contact', '0', null, null);
COMMIT;

-- ----------------------------
--  Table structure for `p_job`
-- ----------------------------
DROP TABLE IF EXISTS `p_job`;
CREATE TABLE `p_job` (
  `id` varchar(32) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  `execcommand` varchar(100) DEFAULT NULL,
  `start_time` date DEFAULT NULL,
  `end_time` date DEFAULT NULL,
  `freq` bigint(10) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `when_created` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `p_log`
-- ----------------------------
DROP TABLE IF EXISTS `p_log`;
CREATE TABLE `p_log` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `category` varchar(50) DEFAULT NULL,
  `log_level` varchar(10) DEFAULT NULL,
  `title` varchar(50) DEFAULT NULL,
  `content` varchar(500) DEFAULT NULL,
  `ip` varchar(20) DEFAULT NULL,
  `action` varchar(50) DEFAULT NULL,
  `client` varchar(50) DEFAULT NULL,
  `user_id` bigint(10) DEFAULT NULL,
  `version` bigint(10) NOT NULL,
  `when_created` date NOT NULL,
  `when_modified` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `ix_p_log_user_id` (`user_id`),
  CONSTRAINT `fk_p_log_user_id` FOREIGN KEY (`user_id`) REFERENCES `p_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `p_log`
-- ----------------------------
BEGIN;
INSERT INTO `p_log` VALUES ('1', '登录', 'info', '登录系统', '成功登录系统', '0:0:0:0:0:0:0:1', '/login', 'Mac OS X Chrome', '1', '1', '2017-11-03', '2017-11-03'), ('2', '登录', 'info', '登录系统', '成功登录系统', '0:0:0:0:0:0:0:1', '/login', 'Mac OS X Chrome', '1', '1', '2017-11-03', '2017-11-03'), ('3', '登录', 'info', '登录系统', '成功登录系统', '0:0:0:0:0:0:0:1', '/login', 'Mac OS X Chrome', '1', '1', '2017-11-03', '2017-11-03'), ('4', '登录', 'info', '登录系统', '成功登录系统', '0:0:0:0:0:0:0:1', '/login', 'Mac OS X Chrome', '1', '1', '2017-11-03', '2017-11-03'), ('5', '登录', 'info', '登录系统', '成功登录系统', '0:0:0:0:0:0:0:1', '/login', 'Mac OS X Chrome', '1', '1', '2017-11-03', '2017-11-03'), ('6', '登录', 'info', '登录系统', '成功登录系统', '0:0:0:0:0:0:0:1', '/login', 'Mac OS X Chrome', '1', '1', '2017-11-06', '2017-11-06'), ('7', '登录', 'info', '登录系统', '成功登录系统', '0:0:0:0:0:0:0:1', '/login', 'Mac OS X Chrome', '1', '1', '2017-11-06', '2017-11-06'), ('8', '登录', 'info', '登录系统', '成功登录系统', '0:0:0:0:0:0:0:1', '/login', 'Mac OS X Chrome', '1', '1', '2017-11-06', '2017-11-06');
COMMIT;

-- ----------------------------
--  Table structure for `p_major`
-- ----------------------------
DROP TABLE IF EXISTS `p_major`;
CREATE TABLE `p_major` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `zyh` varchar(32) DEFAULT NULL,
  `zy` varchar(100) DEFAULT NULL,
  `xz` varchar(10) DEFAULT NULL,
  `xkml` varchar(100) DEFAULT NULL,
  `xy_id` bigint(10) DEFAULT NULL,
  `version` bigint(10) NOT NULL,
  `when_created` date NOT NULL,
  `when_modified` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `ix_p_major_xy_id` (`xy_id`),
  CONSTRAINT `fk_p_major_xy_id` FOREIGN KEY (`xy_id`) REFERENCES `p_dept` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `p_print_scheme`
-- ----------------------------
DROP TABLE IF EXISTS `p_print_scheme`;
CREATE TABLE `p_print_scheme` (
  `id` varchar(32) NOT NULL,
  `name` varchar(40) DEFAULT NULL,
  `content` longtext,
  `type` varchar(10) DEFAULT NULL,
  `when_created` date NOT NULL,
  `when_modified` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `p_project_server`
-- ----------------------------
DROP TABLE IF EXISTS `p_project_server`;
CREATE TABLE `p_project_server` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `filter` varchar(100) DEFAULT NULL COMMENT '数据过滤',
  `version` bigint(10) DEFAULT NULL,
  `when_created` date DEFAULT NULL,
  `when_modified` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `p_project_server_id_uindex` (`id`),
  KEY `filter` (`filter`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='工程服务';

-- ----------------------------
--  Records of `p_project_server`
-- ----------------------------
BEGIN;
INSERT INTO `p_project_server` VALUES ('1', '所有', '1', '1', null, null), ('2', '专业漏损控制', '2', '1', null, null), ('3', '地基测绘', '3', '1', null, null), ('4', '工程测量', '4', '1', null, null), ('5', '供水CCADA系统', '5', '1', null, null);
COMMIT;

-- ----------------------------
--  Table structure for `p_role`
-- ----------------------------
DROP TABLE IF EXISTS `p_role`;
CREATE TABLE `p_role` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `code` varchar(20) DEFAULT NULL,
  `type` varchar(2) DEFAULT NULL,
  `scope` varchar(20) DEFAULT NULL,
  `is_std_info` varchar(2) DEFAULT NULL,
  `version` bigint(10) NOT NULL,
  `when_created` date NOT NULL,
  `when_modified` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `p_role`
-- ----------------------------
BEGIN;
INSERT INTO `p_role` VALUES ('1', '系统管理员', 'SYS_SCHOOL', '1', 'xx', '1', '1', '2008-04-02', '2008-04-02');
COMMIT;

-- ----------------------------
--  Table structure for `p_role_perm`
-- ----------------------------
DROP TABLE IF EXISTS `p_role_perm`;
CREATE TABLE `p_role_perm` (
  `role_id` bigint(10) DEFAULT NULL,
  `perm_string` varchar(255) DEFAULT NULL,
  KEY `ix_p_role_perm_role_id` (`role_id`),
  CONSTRAINT `fk_p_role_perm_role_id` FOREIGN KEY (`role_id`) REFERENCES `p_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `p_role_perm`
-- ----------------------------
BEGIN;
INSERT INTO `p_role_perm` VALUES ('1', 'system:manage');
COMMIT;

-- ----------------------------
--  Table structure for `p_user`
-- ----------------------------
DROP TABLE IF EXISTS `p_user`;
CREATE TABLE `p_user` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `userid` varchar(50) DEFAULT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(100) DEFAULT NULL,
  `truename` varchar(200) DEFAULT NULL,
  `salt` varchar(50) DEFAULT NULL,
  `type` varchar(2) DEFAULT NULL,
  `sex` varchar(20) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `mobile` varchar(50) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `photo_id` bigint(10) DEFAULT NULL,
  `dept_id` bigint(10) DEFAULT NULL,
  `version` bigint(10) NOT NULL,
  `when_created` date NOT NULL,
  `when_modified` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `ix_p_user_photo_id` (`photo_id`),
  KEY `ix_p_user_dept_id` (`dept_id`),
  CONSTRAINT `fk_p_user_dept_id` FOREIGN KEY (`dept_id`) REFERENCES `p_dept` (`id`),
  CONSTRAINT `fk_p_user_photo_id` FOREIGN KEY (`photo_id`) REFERENCES `p_file` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `p_user`
-- ----------------------------
BEGIN;
INSERT INTO `p_user` VALUES ('1', null, 'admin', '3407e516120df1202a900c4113664e62d69950fc', '管理员', 'af0a010d9aaa4978', '1', '男', '1', null, null, null, null, null, '1', '2008-04-02', '2008-04-02');
COMMIT;

-- ----------------------------
--  Table structure for `p_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `p_user_role`;
CREATE TABLE `p_user_role` (
  `user_id` bigint(10) NOT NULL,
  `role_id` bigint(10) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `ix_p_user_role_p_user` (`user_id`),
  KEY `ix_p_user_role_p_role` (`role_id`),
  CONSTRAINT `fk_p_user_role_p_role` FOREIGN KEY (`role_id`) REFERENCES `p_role` (`id`),
  CONSTRAINT `fk_p_user_role_p_user` FOREIGN KEY (`user_id`) REFERENCES `p_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `p_user_role`
-- ----------------------------
BEGIN;
INSERT INTO `p_user_role` VALUES ('1', '1');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
