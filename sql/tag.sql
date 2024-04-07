CREATE TABLE ms_tag
(
    `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `name` varchar(50)       NOT NULL,
    `description` varchar(50)       NOT NULL,
    `user_id` bigint(20) unsigned NOT NULL,
    `status` tinyint(1) unsigned NOT NULL COMMENT '0 -> disabled, 1 -> enabled',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY `pk_id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;