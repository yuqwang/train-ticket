experiment/enhanced-log 来自于 enhanced-log，用于成熟度的实验。

其中将 ts-travel-mongo 数据库服务改造为了 ts-travel-mysql 数据库服务

## mysql改造

- ts-travel-service 访问 ts-travel-mysql 中的 ts-travel 数据库
- ts-route-service 访问 ts-travel-mysql 中的 ts-route 数据库
- ts-station-service 访问 ts-travel-mysql 中的 ts-route 数据库

## 服务合并

将原有的 ts-consign-service 服务中的所有功能合并到了 ts-travel-service 服务中

## yml部署文件变化

- part1 中，去除了 ts-travel-mongo ，加入了 ts-travel-mysql。同时去除了 ts-route-mongo 和 ts-station-mongo
- part2 中，去除了 ts-consign-service 服务，更新了 ts-notification-service（email直接返回true，避免阻塞），ts-preserve-service 和
  ts-preserve-other-service（consign 合并-> travel，修改调用 API），ts-travel-service 和 ts-route-service 和
  ts-station-service（三个服务从mongo改为了mysql，修改数据库地址和数据格式）
- part3 中，更新了 ts-ui-dashboard （consign 合并-> travel，修改 nginx 中调用 API）镜像。前端 nginx 代理中，
  以前直接访问 ts-consign-service 的 API，转向了 ts-travel-service 服务。