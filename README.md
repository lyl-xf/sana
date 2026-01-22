# sana 轻量级的IOT设备管理平台

[使用说明看这里！](https://lyl-xf.github.io/)
请下载最新发布的版本进行使用哦！

## 项目介绍
1、sana是一个轻量级的IOT设备云管理平台，后端springboot3.5开发，前端基于scui进行修改开发、前后端分离的、单体的项目。

2、它的主要功能就是：
对边缘网关设备、边缘计算程序接入的设备，接入到云端来进行统一的管理。

3、不包含端到端的设备接入协议
暂时本程序不考虑以下接入协议，比如西门子、施耐德、三菱的plc协议，亦或者CNC、OPC-UA协议等等，这些都是边缘接入程序或者边缘智能网关要干的活，本程序在设计之初就只做云平台。想做设备接入可以用node-red或者是其他软件项目。

3、关于云平台的集群方案与边缘采集程序/边缘智能网关程序正在构思中，相信不久的将来就可以和大家说再见啦。

## 项目结构

| 项目结构          | 描述        
|---------------|-----------|
| sana-abutment | 接入管理模块    |
| sana-base     | 基础功能模块    |
| sana-camera   | 摄像头、流媒体模块 |
|sana-combination|大屏管理|
| sana-devices  | 设备管理模块    |
| sana-rules    | 规则、情景模式模块 |
| sana-server   | 运行服务模块    |           |
| sana-system   | 系统基础功能模块  |
| sana-web      | 前端项目      |


## 技术栈


| 序号 | 项目架构 | 架构描述                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 | 
|----|------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 1  | 后端技术栈 | [springboot3.5.3](https://github.com/spring-projects/spring-boot) + [Sa-Token1.44](https://github.com/dromara/Sa-Token) + [Mybatis-Plus3.5.5](https://github.com/baomidou/mybatis-plus) + [caffeine3.1.8](https://github.com/ben-manes/caffeine) + [hutool5.8.21](https://github.com/chinabugotech/hutool) + [captcha1.6.2](https://github.com/ele-admin/EasyCaptcha) + [knife4j4.3](https://github.com/xiaoymin/knife4j) + [mapstruct1.5.5](https://github.com/mapstruct/mapstruct) + [dynamic4.2](https://github.com/baomidou/dynamic-datasource) + [mica-mqtt](https://gitee.com/dromara/mica-mqtt) + [ZLMediaKit](https://github.com/ZLMediaKit/ZLMediaKit) + [go-view](https://gitee.com/dromara/go-view) ... |
| 2  | 必要的服务 | mysql8 + redis7.0 + minio8.5.1 + jdk17 + node.js 18.20.2                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             |
| 3  | 前端技术栈 | Vue3 + Element-Plus（由[scui](https://gitee.com/lolicode/scui)项目进行修改开发）                                                                                                                                                                                                                                                                                                                                                                                                                                                                                |                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       |
| 4  | 登录信息 | 数据库初始超级管理员用户名/密码(切记，是数据库初始化之后默认的登录信息，不是演示环境的登录信息哦！)：admin/admin123456                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   |


