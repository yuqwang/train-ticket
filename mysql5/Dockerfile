#创建一个基于5.7版本的MySql
FROM mysql:5.7

MAINTAINER xywu
EXPOSE 3306
LABEL version="0.1" description="Mysql服务器" by="xywu"

#设置免密登录
ENV MYSQL_ALLOW_EMPTY_PASSWORD yes

#将所需文件放到容器中
COPY sql/*.sql /docker-entrypoint-initdb.d/