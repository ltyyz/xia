-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 118.24.23.54    Database: xia
-- ------------------------------------------------------
-- Server version	5.7.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `xia_application`
--

DROP TABLE IF EXISTS `xia_application`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `xia_application` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(10) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `url` varchar(100) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `create_by` int(11) DEFAULT NULL,
  `update_by` int(11) DEFAULT NULL,
  `deleted` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xia_application`
--

LOCK TABLES `xia_application` WRITE;
/*!40000 ALTER TABLE `xia_application` DISABLE KEYS */;
INSERT INTO `xia_application` VALUES (1,'xia','夏',1,'','南国似暑北国春，大禹治水万民兴。',NULL,'2018-11-21 11:13:09',NULL,1,0),(4,'qiu','QIU',NULL,NULL,'','2018-11-05 13:45:17',NULL,1,NULL,1),(5,'qiu','QIU',NULL,NULL,'','2018-11-05 13:51:13',NULL,1,NULL,1),(6,'qiu','秋',1,'','一叶落而知求','2018-11-06 13:57:49','2018-11-21 11:13:14',1,1,0),(7,'home','HMS',NULL,NULL,'家庭管理系统','2018-11-11 06:19:49','2018-11-11 06:22:19',1,1,1),(8,'hms','HOME MS',2,'http://www.qq.com','家庭管理系统','2018-11-11 06:51:09','2018-11-20 13:00:32',1,1,1),(9,'huo','惑',1,'','传道授业解惑','2018-11-21 11:10:42','2018-11-21 11:11:32',1,1,0);
/*!40000 ALTER TABLE `xia_application` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xia_config`
--

DROP TABLE IF EXISTS `xia_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `xia_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `app_id` int(11) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `code` varchar(100) DEFAULT NULL,
  `value` varchar(1000) DEFAULT NULL,
  `ext1` varchar(1000) DEFAULT NULL,
  `ext2` varchar(1000) DEFAULT NULL,
  `ext3` varchar(1000) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `create_by` int(11) DEFAULT NULL,
  `update_by` int(11) DEFAULT NULL,
  `deleted` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xia_config`
--

LOCK TABLES `xia_config` WRITE;
/*!40000 ALTER TABLE `xia_config` DISABLE KEYS */;
INSERT INTO `xia_config` VALUES (1,1,'2','1',NULL,'2','2','2','2','2018-11-06 14:16:21','2018-11-06 14:16:33',1,1,1),(2,6,'2','2',NULL,'2','2','2','2','2018-11-06 14:18:44',NULL,1,NULL,1),(3,1,'f','Fd',NULL,'','','','','2018-11-06 14:21:15',NULL,1,NULL,1),(4,1,'是否记录日志','keepLogging','1','','','','是否记录日期，1：是，2：否','2018-11-07 13:06:38','2018-11-07 13:24:46',1,1,0),(5,1,'FTP地址','FTP_HOST','localhost','','','','','2018-11-09 14:46:11',NULL,1,NULL,0),(6,6,'FF','FTP_HOST','1','','','','','2018-11-10 03:23:16',NULL,1,NULL,1),(7,1,'自由访问的URLS','EASY_ACCESS_URLS','/html/**,/css/**,/fonts/**,/img/**,/js/**,/easyui/**,/favicon.ico','','','','','2018-11-11 06:32:52','2018-11-11 06:33:46',1,1,1),(8,1,'自由访问的URL','FREE_ACCESS_URL','/html/**,/css/**,/fonts/**,/img/**,/js/**,/easyui/**,/favicon.ico','','','','','2018-11-17 14:48:28',NULL,1,NULL,0);
/*!40000 ALTER TABLE `xia_config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xia_dic`
--

DROP TABLE IF EXISTS `xia_dic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `xia_dic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(100) DEFAULT NULL,
  `value` varchar(100) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `description` varchar(300) DEFAULT NULL,
  `ext1` varchar(200) DEFAULT NULL,
  `ext2` varchar(200) DEFAULT NULL,
  `ext3` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xia_dic`
--

LOCK TABLES `xia_dic` WRITE;
/*!40000 ALTER TABLE `xia_dic` DISABLE KEYS */;
INSERT INTO `xia_dic` VALUES (1,'dic','dic','字典编码',NULL,'','',''),(7,'dic','USER_STATUS','用户状态',NULL,'','',''),(8,'USER_STATUS','1','正常',NULL,'','',''),(9,'USER_STATUS','2','停用',NULL,'','',''),(10,'dic','MENU_TYPE','菜单类型',NULL,'','',''),(11,'MENU_TYPE','1','标签',NULL,'','',''),(12,'MENU_TYPE','2','新页面',NULL,'','',''),(13,'MENU_TYPE','0','模块',NULL,'','',''),(15,'dic','IS_DEFAULT_APP','是否默认系统',NULL,'','',''),(16,'IS_DEFAULT_APP','1','是',NULL,'','',''),(17,'IS_DEFAULT_APP','0','否',NULL,'','',''),(18,'dic','PERMISSION_TYPE','权限类型',NULL,'','',''),(19,'PERMISSION_TYPE','1','模块',NULL,'','',''),(20,'PERMISSION_TYPE','2','功能',NULL,'','',''),(21,'PERMISSION_TYPE','3','查询',NULL,'','',''),(22,'PERMISSION_TYPE','4','添加',NULL,'','',''),(23,'PERMISSION_TYPE','5','修改',NULL,'','',''),(24,'PERMISSION_TYPE','6','删除',NULL,'','',''),(26,'dic','APPLICATION_TYPE','系统类型',NULL,'','',''),(27,'APPLICATION_TYPE','1','FRAME',NULL,'','',''),(28,'APPLICATION_TYPE','2','STANDALONE',NULL,'','','');
/*!40000 ALTER TABLE `xia_dic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xia_menu`
--

DROP TABLE IF EXISTS `xia_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `xia_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `app_id` int(11) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `url` varchar(1000) DEFAULT NULL,
  `sorts` int(11) DEFAULT NULL,
  `icon` varchar(50) DEFAULT NULL,
  `children` int(11) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `create_by` int(11) DEFAULT NULL,
  `update_by` int(11) DEFAULT NULL,
  `deleted` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xia_menu`
--

LOCK TABLES `xia_menu` WRITE;
/*!40000 ALTER TABLE `xia_menu` DISABLE KEYS */;
INSERT INTO `xia_menu` VALUES (1,1,'系统管理',NULL,0,'',1,'fa fa-wpforms',6,NULL,'2018-11-09 11:50:33',NULL,1,0),(2,1,'权限管理',NULL,0,'',2,'fa fa-at',6,NULL,'2018-11-09 14:31:23',NULL,1,0),(3,1,'其他',NULL,0,'',3,'fa fa-table',1,NULL,'2018-11-09 11:50:52',NULL,1,0),(4,1,'系统管理',1,1,'../../html/xia/application.html',11,'',0,NULL,'2018-11-09 14:36:44',NULL,1,0),(5,1,'用户管理',1,1,'../../html/xia/user.html',12,NULL,0,NULL,NULL,NULL,NULL,0),(6,1,'角色管理',2,1,'../../html/xia/role.html',23,'',0,NULL,'2018-11-11 06:40:45',NULL,1,0),(7,1,'组织机构',1,1,'../../html/xia/todo.html',17,'',0,NULL,'2018-11-17 14:59:06',NULL,1,0),(8,1,'字典配置',1,1,'../../html/xia/dic.html',15,'',0,NULL,'2018-11-14 11:38:46',NULL,1,0),(9,1,'参数配置',1,1,'../../html/xia/config.html',16,NULL,0,NULL,NULL,NULL,NULL,0),(10,1,'其他',3,1,'../../html/xia/todo.html',31,'',0,NULL,'2018-11-17 15:55:18',NULL,1,0),(11,1,'菜单管理',1,1,'../../html/xia/menu.html',14,NULL,0,NULL,NULL,NULL,NULL,0),(12,1,'日志管理',NULL,1,'1',1,'',0,'2018-11-09 11:35:53',NULL,1,NULL,1),(13,1,'查看日志2',3,1,'1',1,'',0,'2018-11-09 11:36:33','2018-11-09 11:41:24',1,1,1),(14,1,'其他2',3,1,'1',1,'',0,'2018-11-09 11:42:46',NULL,1,NULL,1),(15,1,'权限管理',2,0,'1',1,'',0,'2018-11-09 13:29:28',NULL,1,NULL,1),(16,1,'权限管理',15,1,'1',1,'',0,'2018-11-09 13:29:44',NULL,1,NULL,1),(17,1,'系统管理',2,1,'1',1,'',0,'2018-11-09 14:13:53','2018-11-09 14:14:09',1,1,1),(18,1,'系统管理',2,1,'1',1,'',0,'2018-11-09 14:14:32',NULL,1,NULL,1),(19,1,'测试菜单',NULL,1,'1',1,'',0,'2018-11-10 03:19:23','2018-11-10 03:19:39',1,1,1),(20,6,'系统管理',NULL,0,'',1,'',1,'2018-11-10 03:23:49',NULL,1,NULL,0),(21,6,'菜单管理',20,1,'../../html/xia/menu.html',11,'',0,'2018-11-10 03:24:09','2018-11-10 11:24:05',1,1,0),(22,1,'用户系统配置',2,1,'../../html/xia/user_application.html',21,'',0,'2018-11-11 06:28:22','2018-11-11 13:44:53',1,1,0),(23,1,'权限管理',2,1,'../../html/xia/permission.html',22,'',0,'2018-11-11 06:35:09','2018-11-11 06:40:30',1,1,0),(24,1,'用户角色配置',2,1,'../../html/xia/user_role.html',25,'',0,'2018-11-11 06:36:08','2018-11-11 06:48:00',1,1,0),(25,1,'角色权限配置',2,1,'../../html/xia/role_permission.html',24,'',0,'2018-11-11 06:36:35','2018-11-11 06:47:51',1,1,0),(26,8,'基础功能',NULL,0,'1',1,'',1,'2018-11-11 13:54:57','2018-11-11 13:55:20',1,1,0),(27,8,'人员管理',26,1,'1',1,'',0,'2018-11-11 13:55:12',NULL,1,NULL,0),(28,1,'角色菜单配置',2,1,'../../html/xia/role_menu.html',26,'',0,'2018-11-17 11:40:12','2018-11-17 14:18:20',1,1,0);
/*!40000 ALTER TABLE `xia_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xia_org`
--

DROP TABLE IF EXISTS `xia_org`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `xia_org` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) DEFAULT NULL,
  `children` int(11) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `create_by` int(11) DEFAULT NULL,
  `update_by` int(11) DEFAULT NULL,
  `deleted` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xia_org`
--

LOCK TABLES `xia_org` WRITE;
/*!40000 ALTER TABLE `xia_org` DISABLE KEYS */;
/*!40000 ALTER TABLE `xia_org` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xia_permission`
--

DROP TABLE IF EXISTS `xia_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `xia_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `app_id` int(11) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `resource` varchar(200) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `children` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xia_permission`
--

LOCK TABLES `xia_permission` WRITE;
/*!40000 ALTER TABLE `xia_permission` DISABLE KEYS */;
INSERT INTO `xia_permission` VALUES (1,1,'公共资源',1,'',NULL,6),(2,1,'系统管理',2,'/html/xia/application.html',8,5),(3,1,'系统字典查询',3,'/xia/application/dic',2,0),(4,1,'系统查询',3,'/xia/application/list',2,0),(5,1,'系统添加',4,'/xia/application/add',2,0),(6,1,'系统修改',5,'/xia/application/update',2,0),(7,1,'系统删除',6,'/xia/application/delete',2,0),(8,1,'系统管理',1,'',NULL,5),(9,1,'获取当前用户系统',3,'/xia/index/applications',1,0),(10,1,'获取当前用户菜单',3,'/xia/index/menus',1,0),(11,1,'获取当前用户',3,'/xia/index/currentUser',1,0),(12,1,'更新当前用户信息',3,'/xia/index/updateUser',1,0),(13,1,'切换当前用户默认系统',3,'/xia/index/switchDefaultApp',1,0),(14,1,'用户管理',2,'/html/xia/user.html',8,6),(15,1,'菜单管理',2,'/html/xia/menu.html',8,5),(16,1,'字典配置',2,'/html/xia/dic.html',8,4),(17,1,'参数配置',2,'/html/xia/config.html',8,4),(18,1,'权限管理',1,'',NULL,6),(19,1,'用户系统配置',2,'/html/xia/user_application.html',18,4),(20,1,'权限管理',2,'/html/xia/permission.html',18,5),(21,1,'角色管理',2,'/html/xia/role.html',18,6),(22,1,'角色权限配置',2,'/html/xia/role_permission.html',18,2),(23,1,'用户角色配置',2,'/html/xia/user_role.html',18,2),(24,1,'其他',1,'',NULL,1),(25,1,'其他',2,'/html/xia/other.html',24,0),(26,1,'用户分页查询',3,'/xia/user/list',14,0),(27,1,'用户查询所有',3,'/xia/user/list_all',14,0),(28,1,'用户字典查询',3,'/xia/user/dic',14,0),(29,1,'用户添加',4,'/xia/user/add',14,0),(30,1,'用户修改',5,'/xia/user/update',14,0),(31,1,'用户删除',6,'/xia/user/delete',14,0),(32,1,'菜单根据系统查询',3,'/xia/menu/list_by_app',15,0),(33,1,'菜单分页查询',3,'/xia/menu/list',15,0),(34,1,'菜单添加',4,'/xia/menu/add',15,0),(35,1,'菜单修改',5,'/xia/menu/update',15,0),(36,1,'菜单删除',6,'/xia/menu/delete',15,0),(37,1,'字典字典查询',3,'/xia/dic/dic',1,0),(38,1,'字典分页查询',3,'/xia/dic/list',16,0),(39,1,'字典添加',4,'/xia/dic/add',16,0),(40,1,'字典修改',5,'/xia/dic/update',16,0),(41,1,'字典删除',6,'/xia/dic/delete',16,0),(42,1,'参数分页查询',3,'/xia/config/list',17,0),(43,1,'参数添加',4,'/xia/config/add',17,0),(44,1,'参数修改',5,'/xia/config/update',17,0),(45,1,'参数删除',6,'/xia/config/delete',17,0),(46,1,'权限根据系统查询',3,'/xia/permission/list_by_app',20,0),(47,1,'权限分页查询',3,'/xia/permission/list',20,0),(48,1,'权限添加',4,'/xia/permission/add',20,0),(49,1,'权限修改',5,'/xia/permission/update',20,0),(50,1,'权限删除',6,'/xia/permission/delete',20,0),(51,1,'用户系统分页查询',3,'/xia/userApplication/list',19,0),(52,1,'用户系统添加',4,'/xia/userApplication/add',19,0),(53,1,'用户系统修改',5,'/xia/userApplication/update',19,0),(54,1,'用户系统删除',6,'/xia/userApplication/delete',19,0),(55,1,'角色字典查询',3,'/xia/role/dic',21,0),(56,1,'角色根据系统查询',3,'/xia/role/list_by_app',21,0),(57,1,'角色分页查询',3,'/xia/role/list',21,0),(58,1,'角色添加',4,'/xia/role/add',21,0),(59,1,'角色修改',5,'/xia/role/update',21,0),(60,1,'角色删除',6,'/xia/role/delete',21,0),(61,1,'角色菜单配置',2,'/html/xia/role_menu.html',18,2),(62,1,'角色权限查询',3,'/xia/rolePermission/list',22,0),(63,1,'角色权限修改',5,'/xia/rolePermission/save',22,0),(64,1,'用户角色查询',3,'/xia/userRole/list',23,0),(65,1,'用户角色修改',5,'/xia/userRole/save',23,0),(66,1,'角色菜单查询',3,'/xia/roleMenu/list',61,0),(67,1,'角色菜单修改',5,'/xia/roleMenu/save',61,0);
/*!40000 ALTER TABLE `xia_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xia_role`
--

DROP TABLE IF EXISTS `xia_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `xia_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `app_id` int(11) DEFAULT NULL,
  `code` varchar(50) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `create_by` int(11) DEFAULT NULL,
  `update_by` int(11) DEFAULT NULL,
  `deleted` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xia_role`
--

LOCK TABLES `xia_role` WRITE;
/*!40000 ALTER TABLE `xia_role` DISABLE KEYS */;
INSERT INTO `xia_role` VALUES (1,1,'admin','超级管理员','2018-11-06 13:40:53','2018-11-19 11:10:51',1,1,0),(2,1,NULL,'222','2018-11-06 13:57:33',NULL,1,NULL,1),(3,6,'admin','系统管理员','2018-11-06 13:58:06','2018-11-11 10:18:21',1,1,0),(4,1,'admin2','123','2018-11-11 10:25:12',NULL,1,NULL,1),(5,6,'admin2','123','2018-11-11 10:25:25','2018-11-11 10:25:30',1,1,1),(6,1,'admin2','sdf','2018-11-11 10:25:48',NULL,1,NULL,1),(7,1,'role1','角色1','2018-11-17 07:03:09','2018-11-17 09:18:38',1,1,1),(8,1,'role2','角色2','2018-11-17 07:03:16','2018-11-17 09:18:52',1,1,1),(9,1,'common','公共权限角色','2018-11-17 14:24:32',NULL,1,NULL,0),(10,1,'permission','权限管理员','2018-11-19 11:09:43',NULL,1,NULL,1);
/*!40000 ALTER TABLE `xia_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xia_role_menu`
--

DROP TABLE IF EXISTS `xia_role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `xia_role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `menu_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xia_role_menu`
--

LOCK TABLES `xia_role_menu` WRITE;
/*!40000 ALTER TABLE `xia_role_menu` DISABLE KEYS */;
INSERT INTO `xia_role_menu` VALUES (2,1,4),(12,1,2),(13,1,22),(14,1,23),(15,1,6),(16,1,25),(17,1,28),(18,1,24),(36,1,3),(37,1,10),(53,1,5),(54,1,11),(55,1,8),(56,1,9);
/*!40000 ALTER TABLE `xia_role_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xia_role_permission`
--

DROP TABLE IF EXISTS `xia_role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `xia_role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `permission_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=129 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xia_role_permission`
--

LOCK TABLES `xia_role_permission` WRITE;
/*!40000 ALTER TABLE `xia_role_permission` DISABLE KEYS */;
INSERT INTO `xia_role_permission` VALUES (1,1,1),(2,1,9),(3,1,10),(4,1,11),(5,1,12),(6,1,13),(13,1,2),(14,1,3),(15,1,4),(16,1,5),(17,1,6),(18,1,7),(19,1,8),(20,1,14),(21,1,15),(22,1,16),(23,1,17),(24,1,18),(25,1,19),(26,1,20),(27,1,21),(28,1,22),(29,1,23),(30,1,24),(31,1,25),(38,1,26),(39,1,27),(40,1,28),(41,1,29),(42,1,30),(43,1,31),(44,1,32),(45,1,33),(46,1,34),(47,1,35),(48,1,36),(49,1,37),(50,1,38),(51,1,39),(52,1,40),(53,1,41),(54,1,42),(55,1,43),(56,1,44),(57,1,45),(58,1,51),(59,1,52),(60,1,53),(61,1,54),(62,1,46),(63,1,47),(64,1,48),(65,1,49),(66,1,50),(67,1,55),(68,1,56),(69,1,57),(70,1,58),(71,1,59),(72,1,60),(73,1,62),(74,1,63),(75,1,64),(76,1,65),(77,1,61),(78,1,66),(79,1,67),(80,9,1),(81,9,9),(82,9,10),(83,9,11),(84,9,12),(85,9,13),(86,9,37);
/*!40000 ALTER TABLE `xia_role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xia_user`
--

DROP TABLE IF EXISTS `xia_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `xia_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `org_id` int(11) DEFAULT NULL,
  `account` varchar(20) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(256) DEFAULT NULL,
  `status` char(1) DEFAULT '1',
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `create_by` int(11) DEFAULT NULL,
  `update_by` int(11) DEFAULT NULL,
  `deleted` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xia_user`
--

LOCK TABLES `xia_user` WRITE;
/*!40000 ALTER TABLE `xia_user` DISABLE KEYS */;
INSERT INTO `xia_user` VALUES (1,NULL,'admin','奇葩的管理员','$2a$10$tfI93055LykwKyThU1AHVOX0znaV/c8MX6htwflj7D7fbD5UePq3W','1',NULL,'2018-11-21 13:50:05',NULL,1,0),(2,NULL,'root','root',NULL,'1','2018-11-06 12:07:01',NULL,1,NULL,1),(3,NULL,'root','root2','111111','2','2018-11-06 12:08:24','2018-11-08 13:41:59',1,1,1),(4,NULL,'user1','user1','111111','1','2018-11-08 13:43:57',NULL,1,NULL,1),(5,NULL,'roy','Roy','111111','1','2018-11-11 14:08:08',NULL,1,NULL,0);
/*!40000 ALTER TABLE `xia_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xia_user_application`
--

DROP TABLE IF EXISTS `xia_user_application`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `xia_user_application` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `app_id` int(11) DEFAULT NULL,
  `is_default` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xia_user_application`
--

LOCK TABLES `xia_user_application` WRITE;
/*!40000 ALTER TABLE `xia_user_application` DISABLE KEYS */;
INSERT INTO `xia_user_application` VALUES (1,1,1,1),(2,1,6,0),(5,5,1,1),(6,1,9,0);
/*!40000 ALTER TABLE `xia_user_application` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xia_user_role`
--

DROP TABLE IF EXISTS `xia_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `xia_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xia_user_role`
--

LOCK TABLES `xia_user_role` WRITE;
/*!40000 ALTER TABLE `xia_user_role` DISABLE KEYS */;
INSERT INTO `xia_user_role` VALUES (1,1,1),(7,1,3),(11,5,9);
/*!40000 ALTER TABLE `xia_user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-11-21 22:23:18
