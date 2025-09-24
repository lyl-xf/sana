

/*
注意：该数据非必要数据库，没有需求可以不使用
创建数据库
*/
CREATE DATABASE sana PRECISION 'ms' KEEP 3650 DURATION 10 BUFFER 16;
/*
 使用sana数据库
*/
use sana;
/*
 创建日志超级表
*/
CREATE STABLE sys_log_operate (ts timestamp,
    module NCHAR(200),
    name NCHAR(100),
    req_uri NCHAR(200),
    req_method NCHAR(200),
    req_params NCHAR(4000),
    ip NCHAR(50),
    user_agent NCHAR(300),
    operate_type INT,
    status INT,
    real_name NCHAR(50)
    ) TAGS (creator BIGINT);

