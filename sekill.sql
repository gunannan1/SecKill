SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '商品ID',
  `goods_name` varchar(16) DEFAULT NULL COMMENT '商品名称',
  `goods_title` varchar(64) DEFAULT NULL COMMENT '商品标题',
  `goods_img` varchar(64) DEFAULT NULL COMMENT '商品的图片',
  `goods_detail` longtext COMMENT '商品的详情介绍',
  `goods_price` decimal(10,2) DEFAULT '0.00' COMMENT '商品单价',
  `goods_stock` int(11) DEFAULT '0' COMMENT '商品库存，-1表示没有限制',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('1', 'iphoneX', 'Apple iPhone X (A1865) 64GB 银色 移动联通电信4G手机', '/img/iphonex.png', 'Apple iPhone X (A1865) 64GB 银色 移动联通电信4G手机', '8765.00', '10000');
INSERT INTO `goods` VALUES ('2', '华为Meta9', '华为 Mate 9 4GB+32GB版 月光银 移动联通电信4G手机 双卡双待', '/img/meta10.png', '华为 Mate 9 4GB+32GB版 月光银 移动联通电信4G手机 双卡双待', '3212.00', '-1');
INSERT INTO `goods` VALUES ('3', 'iphone8', 'Apple iPhone 8 (A1865) 64GB 银色 移动联通电信4G手机', '/img/iphone8.png', 'Apple iPhone 8 (A1865) 64GB 银色 移动联通电信4G手机', '5589.00', '10000');
INSERT INTO `goods` VALUES ('4', '小米6', '小米6 4GB+32GB版 月光银 移动联通电信4G手机 双卡双待', '/img/mi6.png', '小米6 4GB+32GB版 月光银 移动联通电信4G手机 双卡双待', '3212.00', '10000');

-- ----------------------------
-- Table structure for miaosha_goods
-- ----------------------------
DROP TABLE IF EXISTS `miaosha_goods`;
CREATE TABLE `miaosha_goods` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '秒杀的商品表',
  `goods_id` bigint(20) DEFAULT NULL COMMENT '商品Id',
  `miaosha_price` decimal(10,2) DEFAULT '0.00' COMMENT '秒杀价',
  `stock_count` int(11) DEFAULT NULL COMMENT '库存数量',
  `start_date` datetime DEFAULT NULL COMMENT '秒杀开始时间',
  `end_date` datetime DEFAULT NULL COMMENT '秒杀结束时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of miaosha_goods
-- ----------------------------
INSERT INTO `miaosha_goods` VALUES ('1', '1', '0.01', '9', '2017-12-04 21:51:23', '2017-12-31 21:51:27');
INSERT INTO `miaosha_goods` VALUES ('2', '2', '0.01', '9', '2017-12-04 21:40:14', '2017-12-31 14:00:24');
INSERT INTO `miaosha_goods` VALUES ('3', '3', '0.01', '9', '2017-12-04 21:40:14', '2017-12-31 14:00:24');
INSERT INTO `miaosha_goods` VALUES ('4', '4', '0.01', '9', '2017-12-04 21:40:14', '2017-12-31 14:00:24');

-- ----------------------------
-- Table structure for miaosha_message
-- ----------------------------
DROP TABLE IF EXISTS `miaosha_message`;
CREATE TABLE `miaosha_message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '消息主键',
  `messageid` bigint(20) NOT NULL COMMENT '分布式id',
  `content` text COMMENT '消息内容',
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  `status` int(1) NOT NULL COMMENT '1 有效 2 失效 ',
  `over_time` datetime DEFAULT NULL COMMENT '结束时间',
  `message_type` int(1) DEFAULT '3' COMMENT '0 秒杀消息 1 购买消息 2 推送消息',
  `send_type` int(1) DEFAULT '3' COMMENT '发送类型 0 app 1 pc 2 ios',
  `good_name` varchar(50) DEFAULT '' COMMENT '商品名称',
  `price` decimal(10,2) DEFAULT '0.00' COMMENT '商品价格',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of miaosha_message
-- ----------------------------
INSERT INTO `miaosha_message` VALUES ('1', '533324506110885888', '尊敬的用户你好，你已经成功注册！', null, '0', null, null, '0', null, null);
INSERT INTO `miaosha_message` VALUES ('2', '533324506110885888', '尊敬的用户你好，你已经成功注册！', null, '0', null, null, '0', null, null);
INSERT INTO `miaosha_message` VALUES ('3', '533324506110885888', '尊敬的用户你好，你已经成功注册！', '2019-01-11', '0', null, null, '0', null, null);
INSERT INTO `miaosha_message` VALUES ('4', '533324506110885888', '尊敬的用户你好，你已经成功注册！', '2019-01-11', '0', null, null, '0', null, null);

-- ----------------------------
-- Table structure for miaosha_message_user
-- ----------------------------
DROP TABLE IF EXISTS `miaosha_message_user`;
CREATE TABLE `miaosha_message_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `userid` bigint(20) NOT NULL COMMENT '用户ID',
  `messageid` bigint(50) NOT NULL COMMENT '消息ID',
  `goodid` int(20) DEFAULT NULL COMMENT '商品ID',
  `orderid` int(20) DEFAULT NULL COMMENT '订单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of miaosha_message_user
-- ----------------------------
INSERT INTO `miaosha_message_user` VALUES ('1', '1', '222', '22', '2');
INSERT INTO `miaosha_message_user` VALUES ('11', '22', '533324506110885888', null, null);
INSERT INTO `miaosha_message_user` VALUES ('12', '22', '533324506110885888', null, null);
INSERT INTO `miaosha_message_user` VALUES ('13', '22', '533324506110885888', null, null);
INSERT INTO `miaosha_message_user` VALUES ('14', '22', '533324506110885888', null, null);

-- ----------------------------
-- Table structure for miaosha_order
-- ----------------------------
DROP TABLE IF EXISTS `miaosha_order`;
CREATE TABLE `miaosha_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '秒杀订单ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `order_id` bigint(20) DEFAULT NULL COMMENT '订单ID',
  `goods_id` bigint(20) DEFAULT NULL COMMENT '商品ID',
  PRIMARY KEY (`id`),
  UNIQUE KEY `u_uid_gid` (`user_id`,`goods_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1551 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of miaosha_order
-- ----------------------------
INSERT INTO `miaosha_order` VALUES ('1547', '18912341234', '1561', '1');
INSERT INTO `miaosha_order` VALUES ('1548', '18912341234', '1562', '2');
INSERT INTO `miaosha_order` VALUES ('1549', '18912341234', '1563', '4');
INSERT INTO `miaosha_order` VALUES ('1550', '18912341234', '1564', '3');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `phone` bigint(20) NOT NULL COMMENT '手机号',
  `password` varchar(32) DEFAULT NULL COMMENT 'MD5(MD5(pass明文+固定salt) + salt)',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `salt` varchar(10) DEFAULT NULL COMMENT '盐值',
  `head` varchar(128) DEFAULT NULL COMMENT '头像，云存储的ID',
  `register_date` datetime DEFAULT NULL COMMENT '注册时间',
  `last_login_date` datetime DEFAULT NULL COMMENT '上蔟登录时间',
  `login_count` int(11) DEFAULT '0' COMMENT '登录次数',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_unique` (`username`) USING BTREE,
  UNIQUE KEY `phone_unique` (`phone`) USING BTREE,
  UNIQUE KEY `email_unique` (`email`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;



-- ----------------------------
-- Table structure for order_info
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `goods_id` bigint(20) DEFAULT NULL COMMENT '商品ID',
  `delivery_addr_id` bigint(20) DEFAULT NULL COMMENT '收获地址ID',
  `goods_name` varchar(16) DEFAULT NULL COMMENT '冗余过来的商品名称',
  `goods_count` int(11) DEFAULT '0' COMMENT '商品数量',
  `goods_price` decimal(10,2) DEFAULT '0.00' COMMENT '商品单价',
  `order_channel` tinyint(4) DEFAULT '0' COMMENT '1pc，2android，3ios',
  `status` tinyint(4) DEFAULT '0' COMMENT '订单状态，0新建未支付，1已支付，2已发货，3已收货，4已退款，5已完成',
  `create_date` datetime DEFAULT NULL COMMENT '订单的创建时间',
  `pay_date` datetime DEFAULT NULL COMMENT '支付时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1565 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES ('1561', '18912341234', '1', null, 'iphoneX', '1', '0.01', '1', '0', '2017-12-14 22:49:10', null);
INSERT INTO `order` VALUES ('1562', '18912341234', '2', null, '华为Meta9', '1', '0.01', '1', '0', '2017-12-14 22:55:42', null);
INSERT INTO `order` VALUES ('1563', '18912341234', '4', null, '小米6', '1', '0.01', '1', '0', '2017-12-16 16:19:23', null);
INSERT INTO `order` VALUES ('1564', '18912341234', '3', null, 'iphone8', '1', '0.01', '1', '0', '2017-12-16 16:35:20', null);

-- -- ----------------------------
-- -- Table structure for user
-- -- ----------------------------
-- DROP TABLE IF EXISTS `user`;
-- CREATE TABLE `user` (
--   `id` int(11) NOT NULL AUTO_INCREMENT,
--   `name` varchar(10) DEFAULT NULL,
--   PRIMARY KEY (`id`)
-- ) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;
--
-- -- ----------------------------
-- -- Records of user
-- -- ----------------------------
-- INSERT INTO `user` VALUES ('1', 'Joshua');
