# Spring Cloud Alibaba 微服务示例项目

这是一个基于 **Spring Cloud Alibaba** 的微服务示例项目，包含用户服务、订单服务、服务调用方和网关模块。项目适合用于学习 Nacos 服务注册与配置管理、OpenFeign 远程调用、Spring Cloud Gateway 网关路由和 Sentinel 限流等后端微服务基础能力。

## 项目特点

- 使用 Nacos 进行服务注册与配置管理。
- 使用 OpenFeign 实现服务间远程调用。
- 使用 Spring Cloud Gateway 提供统一网关入口。
- 集成 Sentinel，用于接口限流和稳定性保护。
- 提供 Docker Compose 配置，便于启动基础环境。
- 新增网关健康检查接口，便于验证网关状态和服务注册情况。

## 技术栈

- Java
- Spring Boot
- Spring Cloud Alibaba
- Nacos
- OpenFeign
- Spring Cloud Gateway
- Sentinel
- Maven
- Docker / Docker Compose

## 项目结构

```text
.
├── order-service/                 # 订单服务
├── user-service-provider/         # 用户服务提供者
├── user-service-invoker/          # 用户服务调用方
├── spring-cloud-gateway/          # 网关服务
├── sentinel-dashboard/            # Sentinel 控制台说明
├── docker-compose.yml             # Docker 编排配置
├── nacos-standalone.yml           # Nacos 单机配置
└── pom.xml                        # Maven 父工程配置
```

## 快速启动

### 1. 启动 Nacos

```bash
docker-compose -f nacos-standalone.yml up -d
```

Nacos 控制台地址：

```text
http://localhost:8848/nacos
```

默认账号密码：

```text
nacos / nacos
```

### 2. 启动各个服务

可以分别启动以下模块：

```bash
mvn -pl user-service-provider spring-boot:run
mvn -pl user-service-invoker spring-boot:run
mvn -pl order-service spring-boot:run
mvn -pl spring-cloud-gateway spring-boot:run
```

### 3. 常用验证接口

```bash
curl http://localhost:8701/invoke/user/1?s=1
curl http://localhost:8701/invoke/feign/user/1?s=6
curl http://localhost:8702/order/detail/1
curl http://localhost:8709/scadusi/invoke/feign/user/1
curl http://localhost:8709/scadusp/user/1
curl http://localhost:8709/gateway/health
```

## 新增接口

### 网关健康检查

```text
GET /gateway/health
```

返回内容包含：

- 网关服务名称
- 当前状态
- 检查时间
- Nacos 中已注册的服务列表

该接口适合用于部署后验证网关是否启动成功，以及服务注册是否正常。

## 本次改造点

新增 `GatewayHealthController`，在网关模块提供 `/gateway/health` 接口，用于查看网关服务状态和注册服务列表。

## 编译说明

如果在较新 JDK 上遇到 Lombok 与 `javac` 模块权限相关的编译问题，建议使用 JDK 8/11，或升级项目中的 Lombok 版本后再编译。
