CREATE TABLE `ms_item`
(
    `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `amount` decimal(18, 2)  NOT NULL DEFAULT '0.00',
    `description` varchar(50)    DEFAULT NULL,
    `user_id` bigint(20) unsigned NOT NULL,
    `status` tinyint(1) unsigned NOT NULL COMMENT '0 -> disabled, 1 -> enabled',
    `type` tinyint unsigned NOT NULL COMMENT '0 -> outcome, 1 -> income',
    `happen_at` datetime NOT NULL,
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY `pk_id` (`id`),
    KEY `user_key` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `ms_item_tag_mapping`
(
    `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `item_id` bigint(20) unsigned NOT NULL,
    `tag_id` bigint(20) unsigned NOT NULL,
    `status` tinyint(1) unsigned NOT NULL COMMENT '0 -> disabled, 1 -> enabled',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY `pk_id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;