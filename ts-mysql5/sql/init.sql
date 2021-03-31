create database `ts-travel` default character set utf8 collate utf8_general_ci;
create database `ts-route` default character set utf8 collate utf8_general_ci;

use mysql;
SELECT host, user FROM user;

-- 将数据库的权限授权给root用户，密码为123456：
GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY 'fdsefdse';

-- 刷新权限这一条命令一定要有：
flush privileges;