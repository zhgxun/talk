/* 分类 */
CREATE TABLE IF NOT EXISTS `category` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增标识',
  `parent_id` int(11) unsigned NOT NULL DEFAULT 0 COMMENT '父类目标识',
  `level` int(11) unsigned NOT NULL DEFAULT 1 COMMENT '类目等级',
  `name` varchar(120) NOT NULL DEFAULT "" COMMENT '标题',
  `creator` int(11) unsigned NOT NULL DEFAULT 0 COMMENT '创建者',
  `updater` int(11) unsigned NOT NULL DEFAULT 0 COMMENT '创建者',
  `create_time` int(11) NOT NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` int(11) NOT NULL DEFAULT 0 COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='分类';

/* 书 */
CREATE TABLE IF NOT EXISTS `book` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增标识',
  `category_id` int(11) unsigned NOT NULL DEFAULT 0 COMMENT '类目主键',
  `title` varchar(255) NOT NULL DEFAULT "" COMMENT '标题',
  `author` varchar(120) NOT NULL DEFAULT "" COMMENT '作者',
  `nick_name` varchar(120) NOT NULL DEFAULT "" COMMENT '主播昵称',
  `url` varchar(255) NOT NULL DEFAULT "" COMMENT '封面地址',
  `description` varchar(255) NOT NULL DEFAULT "" COMMENT '描述',
  `play_count` int(11) unsigned NOT NULL DEFAULT 0 COMMENT '播放次数',
  `creator` int(11) unsigned NOT NULL DEFAULT 0 COMMENT '创建者',
  `updater` int(11) unsigned NOT NULL DEFAULT 0 COMMENT '创建者',
  `create_time` int(11) NOT NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` int(11) NOT NULL DEFAULT 0 COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `category_id` (`category_id`),
  KEY `title` (`title`),
  KEY `author` (`author`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='书';

/* 用户基本信息, 不维护用户注册, 统一由第三方登陆即可 */
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增标识',
  `oauth_id` varchar(120) NOT NULL DEFAULT "" COMMENT '认证平台标识, 同认证平台一起去重用户',
  `nick_name` varchar(120) NOT NULL DEFAULT "" COMMENT '昵称',
  `home` varchar(255) NOT NULL DEFAULT "" COMMENT '三方平台用户主页地址',
  `url` varchar(255) NOT NULL DEFAULT "" COMMENT '头像地址',
  `role` tinyint(1) NOT NULL DEFAULT 2 COMMENT '角色, 1为管理员, 2为普通用户',
  `type` tinyint(1) NOT NULL DEFAULT 1 COMMENT '认证方式, 目前仅1:QQ,2:微博,3:微信',
  `creator` int(11) unsigned NOT NULL DEFAULT 0 COMMENT '创建者',
  `updater` int(11) unsigned NOT NULL DEFAULT 0 COMMENT '创建者',
  `create_time` int(11) NOT NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` int(11) NOT NULL DEFAULT 0 COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `nick_name` (`nick_name`),
  KEY `oauth_id_type` (`oauth_id`, `type`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='用户';

/* 认证 */
CREATE TABLE IF NOT EXISTS `oauth` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增标识',
  `user_id` int(11) unsigned NOT NULL DEFAULT 0 COMMENT '用户基础标识',
  `oauth_name` varchar(120) NOT NULL DEFAULT "" COMMENT '认证平台名称QQ,微博,微信',
  `oauth_access_token` varchar(120) NOT NULL DEFAULT "" COMMENT '认证平台token',
  `oauth_expires` int(11) unsigned NOT NULL DEFAULT 0 COMMENT '认证平台过期时间',
  `creator` int(11) unsigned NOT NULL DEFAULT 0 COMMENT '创建者',
  `updater` int(11) unsigned NOT NULL DEFAULT 0 COMMENT '创建者',
  `create_time` int(11) NOT NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` int(11) NOT NULL DEFAULT 0 COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='认证';

/* 详情 */
CREATE TABLE IF NOT EXISTS `item` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增标识',
  `book_id` int(11) unsigned NOT NULL DEFAULT 0 COMMENT '图书主键',
  `author` varchar(120) NOT NULL DEFAULT "" COMMENT '作者',
  `name` varchar(120) NOT NULL DEFAULT "" COMMENT '名称',
  `duration` varchar(255) NOT NULL DEFAULT "" COMMENT '描述',
  `format` varchar(255) NOT NULL DEFAULT "" COMMENT '音频格式',
  `url` varchar(255) NOT NULL DEFAULT "" COMMENT '地址',
  `description` varchar(255) NOT NULL DEFAULT "" COMMENT '描述',
  `creator` int(11) unsigned NOT NULL DEFAULT 0 COMMENT '创建者',
  `updater` int(11) unsigned NOT NULL DEFAULT 0 COMMENT '创建者',
  `create_time` int(11) NOT NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` int(11) NOT NULL DEFAULT 0 COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `book_id` (`book_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='详情';