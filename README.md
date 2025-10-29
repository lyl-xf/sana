# sana 轻量级的IOT设备管理平台

## 项目介绍
1、sana是一个轻量级的IOT设备云管理平台，后端springboot3.3开发，前端基于scui进行修改开发、前后端分离的、单体的项目。

2、它的主要功能就是：
对边缘网关设备、边缘计算程序接入的设备，接入到云端来进行统一的管理。所以不会考虑太多的接入协议，比如西门子、施耐德、三菱的plc协议，亦或者CNC、OPC-UA协议等等，这些都是边缘接入程序或者边缘智能网关要干的活，本程序在设计之初就只做云平台。

3、关于云平台的集群方案与边缘采集程序/边缘智能网关程序正在构思中，相信不久的将来就可以和大家说再见啦。

## 项目结构

| 项目结构          | 描述        
|---------------|-----------|
| sana-abutment | 接入管理模块    |
| sana-base     | 基础功能模块    |
| sana-camera   | 摄像头、流媒体模块 |
| sana-devices  | 设备管理模块    |
| sana-rules    | 规则、情景模式模块 |
| sana-server   | 运行服务模块    |           |
| sana-system   | 系统基础功能模块  |
| sana-web      | 前端项目      |

## 技术栈


| 序号 | 项目架构 | 架构描述                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 | 
|----|------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 1  | 后端技术栈 | [springboot3.3](https://github.com/spring-projects/spring-boot) + [Sa-Token1.44](https://github.com/dromara/Sa-Token) + [Mybatis-Plus3.5.5](https://github.com/baomidou/mybatis-plus) + [caffeine3.1.8](https://github.com/ben-manes/caffeine) + [hutool5.8.21](https://github.com/chinabugotech/hutool) + [captcha1.6.2](https://github.com/ele-admin/EasyCaptcha) + [knife4j4.3](https://github.com/xiaoymin/knife4j) + [mapstruct1.5.5](https://github.com/mapstruct/mapstruct) + [dynamic4.2](https://github.com/baomidou/dynamic-datasource) ... |
| 2  | 必要的服务 | mysql8 + redis7.0 + minio8.5.1 + jdk17 + node.js 18.20.2                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             |
| 3  | 前端技术栈 | Vue3 + Element-Plus（由[scui](https://gitee.com/lolicode/scui)项目进行修改开发）                                                                                                                                                                                                                                                                                                                                                                                                                                                                                |                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       |
| 4  | 登录信息 | 数据库初始超级管理员用户名/密码：admin/admin123456                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   |

## 图片一览
![img.png](doc%2Fimg%2Fimg.png)
![img_1.png](doc%2Fimg%2Fimg_1.png)
![img_2.png](doc%2Fimg%2Fimg_2.png)


## 强制要求
### 使用自带的mqtt-broker的一些小要求。
1、设备客户端clientID默认为GeneralPrefixEnum.TABLE_PREFIX

如：sb42（sb是前缀，42为设备id）

2、设备上报客户端topic默认为GeneralPrefixEnum.DEVICE_TOPIC_PREFIX

如：/SB42（/SB是topic前缀，42为设备id）

注意：以上主题中的SB、sb，统一为设备的拼音首字母的简称。
