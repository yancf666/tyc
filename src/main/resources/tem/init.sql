
create database qy;
use qy;
drop table qysource;
create table qysource
(
    id       int auto_increment
        primary key,
    url      varchar(500) null comment '地址',
    qymc     varchar(500) null comment '企业名称',
    lsxzbgxf int          null comment '历史被执行高消费数量',
    lssxbzxr int          null comment '历史失信被执行人',
    lsbzxr   int          null comment '历史被执行人',
    zjlarq   varchar(100) null comment '最近立案日期',
    photo    varchar(50)  null comment '电话号码'
);