
use ksb;

drop table if exists userinfo;
create table userinfo(
                         uid BIGINT primary key AUTO_INCREMENT,
                         name varchar(50) ,
                         password varchar(50),
                         birthday datetime,
                         email varchar(100),
                         tel varchar(12),
                         realname varchar(50),
                         classes BIGINT,
                         taskid BIGINT,
                         admin BIGINT,
                         version int,
                         status INT
);

drop table if exists task;
create table tasks(
                      tid BIGINT primary key  AUTO_INCREMENT,
                      task text,
                      title varchar(255),
                      fromto varchar(100),
                      starttime datetime,
                      endtime datetime,
                      uid BIGINT,
                      status int
);

drop table if exists sendmail;
create table sendmail(
                         mid BIGINT primary key AUTO_INCREMENT,
                         title varchar(255),
                         title2 varchar(100),
                         formuser varchar(255),
                         touser varchar(255),
                         sendname varchar(255),
                         send text,
                         imgurl varchar(255),
                         createdate varchar(100),
                         status int
);

drop table if exists syslog;
create table syslog(
                       lid BIGINT primary key AUTO_INCREMENT,
                       username varchar(255),
                       operation varchar(255),
                       method varchar(255),
                       params text,
                       ip varchar(50),
                       browser varchar(50),
                       type varchar(50),
                       version varchar(50),
                       createdate datetime
);

drop table if exists menu;
create table menu(
                     eid BIGINT primary key auto_increment,
                     roles varchar(50)
);

drop table if exists auth;
create table auth(
                     aid BIGINT primary key auto_increment,
                     url varchar(255),
                     eid BIGINT
);

drop table if exists subjects;
create table subjects(
                         jid BIGINT primary key auto_increment,
                         did BIGINT,
                         createdate datetime,
                         operation varchar(255)
);

drop table if exists subborder;
create table subborder(
                          did BIGINT primary key auto_increment,
                          mon varchar(255),
                          tue varchar(255),
                          wed varchar(255),
                          thu varchar(255),
                          fri varchar(255),
                          sat varchar(255),
                          sun varchar(255)
);

drop table if exists imgurl;
create table imgurl(
                       imgid BIGINT primary key auto_increment,
                       name varchar(255),
                       url varchar(255),
                       version varchar(100),
                       auth int,
                       status int
);

drop table if exists scheduled_task;
CREATE TABLE `scheduled_task` (
                                  `id` int(11) NOT NULL AUTO_INCREMENT,
                                  `task_key` varchar(128) NOT NULL COMMENT '任务key值（使用bean名称）',
                                  `task_desc` varchar(128) DEFAULT NULL COMMENT '任务描述',
                                  `task_cron` varchar(128) NOT NULL COMMENT '任务表达式',
                                  `init_start_flag` int(2) NOT NULL DEFAULT '1' COMMENT '程序初始化是否启动 1 是 0 否',
                                  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                  PRIMARY KEY (`id`),
                                  UNIQUE KEY `uniqu_task_key` (`task_key`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

drop table if exists suggest;
create table suggest(
                        sugid BIGINT primary key AUTO_INCREMENT,
                        sug varchar(255) not null ,
                        sug_type varchar(100) not null ,
                        sug_url varchar(255) ,
                        sug_date datetime,
                        qqAddress varchar(100),
                        status int
);

drop table if exists mailinfo;
create table mailinfo(
                         mailId BIGINT primary key AUTO_INCREMENT,
                         from_name varchar(255) unique ,
                         from_mail varchar(255),
                         status int
);

drop table if exists gloexception;
create table gloexception(
                             exid BIGINT primary key AUTO_INCREMENT,
                             err_code varchar(255) ,
                             err_msg text,
                             createtime datetime,
                             trigger_ip varchar(100),
                             status int
);





