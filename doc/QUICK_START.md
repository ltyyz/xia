## 快速上手指南

通过本指南可以快速搭建运行和开发环境

#### 1. 环境

开发语言为 Java ，请确认安装正确：
```bash
java -version
```
至少需要 JDK8

XIA 使用 maven 架构，所以请安装 maven ，验证方式：
```bash
mvn -version
``` 

数据库为 MySQL，请先安装或者找一台已经安装好 MySQL 服务的环境，
建议安装5.7 版本，其他版本未经验证，

#### 2. 下载 

使用`git`下载
```bash
git clone https://github.com/loutai/xia.git
```
或者直接通过网页下载

在 MySQL 中创建一个新的数据库`xia`，字符集为`utf8`，collation为`utf8_general_ci`，
导入初始化脚本`src/main/resources/db/xia.sql`

修改配置文件`src/main/resources/application.properties`中的数据库相关信息，如:
```properties
spring.datasource.username=root
spring.datasource.password=password
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/xia?useUnicode=true&characterEncoding=utf-8&useSSL=false
```

#### 3. 运行
```bash
cd xia
mvn spring-boot:run
```
初次启动由于需要下载 SpringBoot 依赖和插件会慢一点，推荐使用国内镜像加速下载，

启动完成后访问：[http://localhost:8080/html/xia/login.html](http://localhost:8080/html/xia/login.html) 进入系统，
默认用户名密码：admin/admin

#### 4. 继续

然后导入 IDE 开始你的表演，遇到问题可以提 [issues](https://github.com/loutai/xia/issues)

