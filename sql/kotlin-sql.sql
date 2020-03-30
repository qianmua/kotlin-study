
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

