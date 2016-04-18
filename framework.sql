/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50537
Source Host           : localhost:3306
Source Database       : framework

Target Server Type    : MYSQL
Target Server Version : 50537
File Encoding         : 65001

Date: 2016-04-18 09:58:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_auth`
-- ----------------------------
DROP TABLE IF EXISTS `t_auth`;
CREATE TABLE `t_auth` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `auth_name` varchar(100) NOT NULL COMMENT '权限名称',
  `auth_type` varchar(5) NOT NULL COMMENT '权限类型,1.按钮,2页面,3菜单',
  `parent_id` int(11) DEFAULT NULL,
  `is_enable` varchar(5) NOT NULL COMMENT '是否可用:0不可用,1可用',
  `auth_url` varchar(255) DEFAULT NULL,
  `create_user` int(11) NOT NULL COMMENT '创建人',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_user` int(11) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL,
  `auth_desc` varchar(255) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_auth
-- ----------------------------
INSERT INTO `t_auth` VALUES ('1', '1', '1', null, '1', null, '1', '2016-02-29 10:31:53', '1', '2016-02-29 10:31:57', '1');

-- ----------------------------
-- Table structure for `t_auth_group`
-- ----------------------------
DROP TABLE IF EXISTS `t_auth_group`;
CREATE TABLE `t_auth_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `auth_group_name` varchar(100) NOT NULL COMMENT '权限组名称',
  `auth_group_desc` varchar(255) DEFAULT NULL COMMENT '描述',
  `is_enable` int(11) NOT NULL COMMENT '是否可用:0.不可用,1可用',
  `create_user` int(11) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_user` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_auth_group
-- ----------------------------

-- ----------------------------
-- Table structure for `t_auth_group_relationship`
-- ----------------------------
DROP TABLE IF EXISTS `t_auth_group_relationship`;
CREATE TABLE `t_auth_group_relationship` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `auth_name` int(11) NOT NULL,
  `auth_group_name` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_auth_group_relationship
-- ----------------------------

-- ----------------------------
-- Table structure for `t_employee`
-- ----------------------------
DROP TABLE IF EXISTS `t_employee`;
CREATE TABLE `t_employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(30) NOT NULL,
  `nike_name` varchar(30) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `mobile` varchar(20) NOT NULL,
  `password` varchar(50) DEFAULT NULL,
  `create_user` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_user` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `verify_user` int(11) DEFAULT NULL,
  `verify_time` datetime DEFAULT NULL,
  `is_login` int(11) NOT NULL COMMENT '限制登录,0限制,1不限制',
  `is_enable` int(11) DEFAULT NULL COMMENT '是否可用:0不可,1可以',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_employee
-- ----------------------------
INSERT INTO `t_employee` VALUES ('12', 'admin', '昵称1', '11653786@qq.com', '15207183027', 'e10adc3949ba59abbe56e057f20f883e', null, null, null, null, null, null, '1', '1');

-- ----------------------------
-- Table structure for `t_employee_group`
-- ----------------------------
DROP TABLE IF EXISTS `t_employee_group`;
CREATE TABLE `t_employee_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL COMMENT '用户组名称',
  `is_enable` varchar(255) NOT NULL,
  `create_user` int(11) NOT NULL,
  `craete_time` datetime NOT NULL,
  `update_user` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `account_group_desc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_employee_group
-- ----------------------------

-- ----------------------------
-- Table structure for `t_employee_group_relationship`
-- ----------------------------
DROP TABLE IF EXISTS `t_employee_group_relationship`;
CREATE TABLE `t_employee_group_relationship` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `employee_id` int(11) NOT NULL,
  `employee_group_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_employee_group_relationship
-- ----------------------------

-- ----------------------------
-- Table structure for `t_log`
-- ----------------------------
DROP TABLE IF EXISTS `t_log`;
CREATE TABLE `t_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `class_name` varchar(255) NOT NULL,
  `create_user` int(11) NOT NULL,
  `create_date` datetime NOT NULL,
  `is_success` int(5) NOT NULL,
  `log_info` blob,
  `spend_time` int(11) NOT NULL,
  `entity_name` varchar(50) NOT NULL,
  `actions` varchar(50) DEFAULT NULL COMMENT '操作,增加,修改，删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_log
-- ----------------------------
INSERT INTO `t_log` VALUES ('15', 'com.yt.entity.mybatis.User', '0', '2016-04-07 18:26:53', '1', 0xACED00057372001A636F6D2E79742E656E746974792E6D7962617469732E55736572E3DF041216894C2A02000E4C000A637265617465446174657400104C6A6176612F7574696C2F446174653B4C000A637265617465557365727400134C6A6176612F6C616E672F496E74656765723B4C0005656D61696C7400124C6A6176612F6C616E672F537472696E673B4C0002696471007E00024C00086973456E61626C657400104C6A6176612F6C616E672F427974653B4C000769734C6F67696E71007E00044C000D6C6173744C6F67696E54696D6571007E00014C000A6C6F67696E546F74616C71007E00024C00086E696B654E616D6571007E00034C000870617373776F726471007E00034C000570686F6E6571007E00034C000A7570646174654461746571007E00014C000A7570646174655573657271007E00024C0008757365724E616D6571007E00037870707074000F31313635333738364071712E636F6D707372000E6A6176612E6C616E672E427974659C4E6084EE50F51C02000142000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B02000078700171007E00097070740006E69DA8E6B69B740020653130616463333934396261353961626265353665303537663230663838336574000B3133383138313633333933707074000779616E6774616F, '54648', '用户表', '保存');
INSERT INTO `t_log` VALUES ('16', 'com.yt.entity.mybatis.User', '0', '2016-04-07 18:27:40', '1', 0xACED00057372001A636F6D2E79742E656E746974792E6D7962617469732E55736572E3DF041216894C2A02000E4C000A637265617465446174657400104C6A6176612F7574696C2F446174653B4C000A637265617465557365727400134C6A6176612F6C616E672F496E74656765723B4C0005656D61696C7400124C6A6176612F6C616E672F537472696E673B4C0002696471007E00024C00086973456E61626C657400104C6A6176612F6C616E672F427974653B4C000769734C6F67696E71007E00044C000D6C6173744C6F67696E54696D6571007E00014C000A6C6F67696E546F74616C71007E00024C00086E696B654E616D6571007E00034C000870617373776F726471007E00034C000570686F6E6571007E00034C000A7570646174654461746571007E00014C000A7570646174655573657271007E00024C0008757365724E616D6571007E00037870707074000F31313635333738364071712E636F6D707372000E6A6176612E6C616E672E427974659C4E6084EE50F51C02000142000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B02000078700171007E00097070740009E5BE90E5B091E5869B740020653130616463333934396261353961626265353665303537663230663838336574000B3133383138313633333933707074000978757368616F6A756E, '4', '用户表', '保存');

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `nike_name` varchar(50) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(20) NOT NULL,
  `is_login` tinyint(2) NOT NULL COMMENT '0限制登录,1正常',
  `is_enable` tinyint(2) NOT NULL COMMENT '0,不可用,1可用',
  `login_total` int(11) DEFAULT NULL COMMENT '登录次数',
  `last_login_time` datetime DEFAULT NULL,
  `create_user` int(11) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_user` int(11) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'admin', '123456', '迷雾555', '11653786@qq.com', '15207183027', '0', '0', '22', '2016-03-29 14:14:53', '1', '2016-04-06 14:14:58', '1', '2016-04-06 14:15:02');
INSERT INTO `t_user` VALUES ('2', 'admin1', '123456', 'miwu555', '11653786@qq.com', '18309276656', '1', '1', '33', '2016-04-06 14:15:22', '1', '2016-04-06 14:15:26', '1', '2016-04-06 14:15:29');
INSERT INTO `t_user` VALUES ('20', 'yangtao', 'e10adc3949ba59abbe56e057f20f883e', '杨涛', '11653786@qq.com', '13818163393', '1', '1', null, null, null, null, null, null);
INSERT INTO `t_user` VALUES ('21', 'xushaojun', 'e10adc3949ba59abbe56e057f20f883e', '徐少军', '11653786@qq.com', '13818163393', '1', '0', null, null, null, null, null, null);
