-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: elm
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `adminId` int NOT NULL AUTO_INCREMENT COMMENT '管理员编号',
  `adminName` varchar(20) NOT NULL COMMENT '管理员名称',
  `password` varchar(100) NOT NULL COMMENT '密码',
  PRIMARY KEY (`adminId`),
  UNIQUE KEY `adminName_UNIQUE` (`adminName`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'王磊','OxvrTfYELUiPvfm+WhaHKQ==');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `business`
--

DROP TABLE IF EXISTS `business`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `business` (
  `businessId` int unsigned NOT NULL AUTO_INCREMENT COMMENT '商家编号',
  `businessName` varchar(40) NOT NULL COMMENT '商家名称',
  `businessAddress` varchar(50) DEFAULT NULL COMMENT '商家地址',
  `businessExplain` varchar(40) DEFAULT NULL COMMENT '商家介绍',
  `businessImg` mediumtext NOT NULL COMMENT '商家图片（base64）',
  `orderTypeId` int NOT NULL COMMENT '点餐分类',
  `starPrice` decimal(5,2) DEFAULT '0.00' COMMENT '起送费',
  `deliveryPrice` decimal(5,2) DEFAULT '0.00' COMMENT '配送费',
  `remarks` varchar(40) DEFAULT NULL COMMENT '备注',
  `password` varchar(20) DEFAULT NULL COMMENT '商家密码',
  `walletId` int DEFAULT NULL,
  `hot` int DEFAULT 0 COMMENT '热度',
  PRIMARY KEY (`businessId`),
  KEY `walletId` (`walletId`),
  CONSTRAINT `business_ibfk_1` FOREIGN KEY (`walletId`) REFERENCES `virtualwallet` (`walletId`)
) ENGINE=InnoDB AUTO_INCREMENT=10010 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `business`
--

LOCK TABLES `business` WRITE;
/*!40000 ALTER TABLE `business` DISABLE KEYS */;
INSERT INTO `business` VALUES (10001,'小锅饭豆腐馆（全运店）','天津市津南区全运路99-35号(3门)','全运路小锅饭全体员工祝大家生活愉快。','https://cube.elemecdn.com/9/de/6d3512da01fa610e00c4676e4ad29jpeg.jpeg?x-oss-process=image/resize,m_mfit,w_400,h_400/format,webp/quality,q_85',1,15.00,3.00,NULL,'123',NULL,5),(10002,'齐品达烤肉饭(新市府直营店)','天津市津南区全运路99-44号3门','十四年不忘初心，只为做一碗有温度的烤肉饭。','https://cube.elemecdn.com/2/91/3794c5bc79d91d0886cb87170772bjpeg.jpeg?x-oss-process=image/resize,m_mfit,w_400,h_400/format,webp/quality,q_85',1,15.00,0.00,NULL,'123',NULL,8),(10003,'奈雪的茶(龙湖天街店)','天津市津区智慧四街5-24号A栋-1F-37号','如对口感有特别要求的顾客请选择奶盖分装','https://img.alicdn.com/imgextra/i4/2214715223183/O1CN01B4hYmz1ZNrSPXATBz_!!2214715223183-0-koubei.jpg_400x400Q85s50.jpg_.webp',5,15.00,3.50,NULL,'123',NULL,7),(10004,'瑞幸咖啡luckin coffee(全运万达广场店)','天津市津南区全运路70号2F2039','推出酱香拿铁~','https://img.alicdn.com/imgextra/i2/2209551521732/O1CN01ho0vbl1OfIkylC05f_!!2209551521732-2-koubei.png_400x400Q85s50.jpg_.webp',5,29.00,0.00,NULL,'123',NULL,4),(10005,'淮乡里·淮南牛肉汤·特色锅贴(白塔堡店)','天津市津南区红椿东路2甲-152号(13门)','饮食无忧','https://img.alicdn.com/imgextra/i2/2214583274033/O1CN01ltjNzi1ffA6iIwg6b_!!2214583274033-0-koubei.jpg_400x400Q85s50.jpg_.webp',7,15.00,7.40,NULL,'123',NULL,2),(10006,'米村拌饭(全运龙湖天街店)','天津市津南区智慧四街5-24号A-4F-32、A-4F-33','2023年8月25日起小菜不再赠送！','https://img.alicdn.com/imgextra/i1/2214698001566/O1CN01YYIlOG1NRH13Gvexq_!!2214698001566-2-koubei.png_400x400Q85s50.jpg_.webp',1,15.00,3.50,NULL,'123',NULL,1),(10007,'聚友园老菜馆(白塔店)','天津市津南区红椿东路2甲-179号1门','本店所有菜品现点现做，让您买的放心，吃的舒心','https://cube.elemecdn.com/a/60/507ab9f80ea017bc9da3e2d33871cpng.png?x-oss-process=image/resize,m_mfit,w_400,h_400/format,webp/quality,q_85',1,20.00,2.50,NULL,'123',NULL,3),(10008,'晨曦炖品鲍鱼饭(小石城店)','天津市津南区红椿东路2甲-183号8门','晨曦炖品原盅瓦罐熬制，让您每天都能享用一份热腾腾的美食！','https://cube.elemecdn.com/7/28/493841b5c8a0eb692560fdeed02bepng.png?x-oss-process=image/resize,m_mfit,w_400,h_400/format,webp/quality,q_85',1,15.00,6.00,NULL,'123',NULL,6);
/*!40000 ALTER TABLE `business` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `cartId` int NOT NULL AUTO_INCREMENT COMMENT '无意义编号',
  `foodId` int NOT NULL COMMENT '食品编号',
  `businessId` int NOT NULL COMMENT '所属商家编号',
  `userId` varchar(20) NOT NULL COMMENT '所属用户编号',
  `quantity` int NOT NULL COMMENT '同一类型食品的购买数量',
  PRIMARY KEY (`cartId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `creditrecord`
--

DROP TABLE IF EXISTS `creditrecord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `creditrecord` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `userId` varchar(20) NOT NULL COMMENT '用户id',
  `ruleCode` varchar(10) NOT NULL COMMENT '积分规则代码',
  `eventId` int NOT NULL COMMENT '相关事件id（订单id，充值id）',
  `credit` int NOT NULL COMMENT '积分（获得为正值，消费为负值）',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  `expiredTime` datetime NOT NULL COMMENT '过期时间',
  PRIMARY KEY (`id`),
  KEY `creditRecord_user_fk` (`userId`),
  CONSTRAINT `creditRecord_user_fk` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='积分记录表（流水表）';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `creditrecord`
--

LOCK TABLES `creditrecord` WRITE;
/*!40000 ALTER TABLE `creditrecord` DISABLE KEYS */;
/*!40000 ALTER TABLE `creditrecord` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `creditrule`
--

DROP TABLE IF EXISTS `creditrule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `creditrule` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `ruleCode` varchar(10) NOT NULL COMMENT '积分规则代码',
  `type` int NOT NULL COMMENT '1获取2消费3获取回滚（暂不使用）4消费回滚（暂不使用）',
  `priority` int NOT NULL DEFAULT '5' COMMENT '优先级，值越低优先级越高',
  `credit` int DEFAULT NULL COMMENT '积分值',
  `formula` decimal(3,2) DEFAULT NULL COMMENT '公式（目前为一个小数）',
  `dailyCap` int DEFAULT NULL COMMENT '日上限',
  `totCap` int DEFAULT NULL COMMENT '总上限',
  `startTime` datetime NOT NULL DEFAULT '1900-01-01 00:00:00' COMMENT '开始时间（缺省值1900-01-01 00:00:00）',
  `endTime` datetime NOT NULL DEFAULT '2900-01-01 23:59:59' COMMENT '结束时间（缺省值2900-01-01 23:59:59）',
  `lifespan` int DEFAULT NULL COMMENT '有效期，默认为天',
  `state` int NOT NULL DEFAULT '0' COMMENT '0停用1启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3 COMMENT='积分规则表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `creditrule`
--

LOCK TABLES `creditrule` WRITE;
/*!40000 ALTER TABLE `creditrule` DISABLE KEYS */;
INSERT INTO `creditrule` VALUES (1,'900',1,5,10,NULL,1,NULL,'1900-01-01 00:00:00','2900-01-01 23:59:59',90,1),(2,'901',1,5,NULL,0.50,NULL,NULL,'1900-01-01 00:00:00','2900-01-01 23:59:59',90,1),(3,'950',2,5,NULL,0.10,NULL,NULL,'1900-01-01 00:00:00','2900-01-01 23:59:59',NULL,1);
/*!40000 ALTER TABLE `creditrule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `deliveryaddress`
--

DROP TABLE IF EXISTS `deliveryaddress`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `deliveryaddress` (
  `daId` int NOT NULL AUTO_INCREMENT COMMENT '送货地址编号',
  `contactName` varchar(20) NOT NULL COMMENT '联系人姓名',
  `contactSex` int NOT NULL COMMENT '联系人性别',
  `contactTel` varchar(20) NOT NULL COMMENT '联系人电话',
  `address` varchar(100) NOT NULL COMMENT '送货地址',
  `userId` varchar(20) NOT NULL COMMENT '所属用户编号',
  PRIMARY KEY (`daId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `deliveryaddress`
--

LOCK TABLES `deliveryaddress` WRITE;
/*!40000 ALTER TABLE `deliveryaddress` DISABLE KEYS */;
INSERT INTO `deliveryaddress` VALUES (1,'小亮',1,'18186889323','天津市津南区海河教育园区雅观路135号天津大学北洋园校区诚园八斋','18186889323'),(2,'小忆',0,'19330531351','天津市津南区海河教育园区雅观路135号天津大学北洋园校区诚园七斋','18186889323');
/*!40000 ALTER TABLE `deliveryaddress` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `food`
--

DROP TABLE IF EXISTS `food`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `food` (
  `foodId` int NOT NULL AUTO_INCREMENT COMMENT '食品编号',
  `foodName` varchar(30) NOT NULL COMMENT '食品名称',
  `foodExplain` varchar(30) NOT NULL COMMENT '食品介绍',
  `foodImg` mediumtext NOT NULL COMMENT '食品图片',
  `foodPrice` decimal(5,2) NOT NULL COMMENT '食品价格',
  `businessId` int NOT NULL COMMENT '所属商家编号',
  `remarks` varchar(40) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`foodId`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `food`
--

LOCK TABLES `food` WRITE;
/*!40000 ALTER TABLE `food` DISABLE KEYS */;
INSERT INTO `food` VALUES (1,'小辣椒炒鸡蛋','原料：辣椒,鸡蛋','https://cube.elemecdn.com/f/e5/4f2a8f2ac61880424731c473a0a70png.png?x-oss-process=image/resize,m_fill,w_190,h_190/format,webp/quality,q_75',15.90,10001,NULL),(2,'外婆红烧肉','咸鲜微甜','https://cube.elemecdn.com/f/e3/7db2da4524c7c261a0997bca8065ejpg.jpg?x-oss-process=image/resize,m_fill,w_190,h_190/format,webp/quality,q_75',15.20,10001,NULL),(3,'溜肉段','原料：猪肉','https://cube.elemecdn.com/f/af/e0f0ab624494ad37f1b444f44c85djpeg.jpeg?x-oss-process=image/resize,m_fill,w_190,h_190/format,webp/quality,q_75',28.00,10001,NULL),(4,'老式锅包肉','原料：猪里脊','https://cube.elemecdn.com/a/19/84aa9aafd6cc03c5e488d93fbb7e6jpeg.jpeg?x-oss-process=image/resize,m_fill,w_190,h_190/format,webp/quality,q_75',28.00,10001,NULL),(5,'老味地三鲜(大份)','原料：茄子,土豆,甜椒','https://cube.elemecdn.com/b/49/747c668123967ca82aa46d1afadd2jpeg.jpeg?x-oss-process=image/resize,m_fill,w_190,h_190/format,webp/quality,q_75',19.50,10001,NULL),(6,'鱼香肉丝','原料：木耳,胡萝卜,猪肉,辣椒,笋','https://cube.elemecdn.com/c/a0/fb9439e5417c35acf428202b17d8apng.png?x-oss-process=image/resize,m_fill,w_190,h_190/format,webp/quality,q_75',24.90,10001,NULL),(7,'烤肉鸡排饭','烤肉遇上鸡排，便是执子之手与子偕老','https://cube.elemecdn.com/2/91/3794c5bc79d91d0886cb87170772bjpeg.jpeg?x-oss-process=image/resize,m_fill,w_204,h_204/format,webp/quality,q_75',19.00,10002,NULL),(8,'烤肉卤肉饭','烤肉卤肉饭配菜可任选2种~','https://cube.elemecdn.com/e/1e/2256a503b04a004bdd812624dc7bejpeg.jpeg?x-oss-process=image/resize,m_fill,w_204,h_204/format,webp/quality,q_75',21.00,10002,NULL),(9,'火山石烤肉饭','鲜辣开胃，出类拔萃','https://cube.elemecdn.com/6/11/42e2afeb25b317471d0e1ee80a37cjpeg.jpeg?x-oss-process=image/resize,m_fill,w_204,h_204/format,webp/quality,q_75',17.90,10002,NULL),(10,'品达烤肉饭','能让深藏的心灵复苏','https://cube.elemecdn.com/f/7b/eb8f599e36c3d1219a21bc674010ajpeg.jpeg?x-oss-process=image/resize,m_fill,w_204,h_204/format,webp/quality,q_75',17.00,10002,NULL),(11,'单点泰式豆皮肉卷','口感微辣哦，单点不予配送','https://cube.elemecdn.com/4/c5/faacc86858129b2d2014fafc5c987jpeg.jpeg?x-oss-process=image/resize,m_fill,w_204,h_204/format,webp/quality,q_75',8.00,10002,NULL),(12,'杨枝甘露','冷饮，茶冻，鲜果粒','https://cube.elemecdn.com/6/0b/b2d7b77f1170670cf851660bc9e60jpg.jpg?x-oss-process=image/resize,m_fill,w_188,h_188/format,webp/quality,q_75',19.00,10003,NULL),(13,'金色山脉珍珠鲜奶茶','冷热皆宜，黑糖珍珠，红茶','https://cube.elemecdn.com/0/a2/fb6d92be8c82bc465b73a6dfe250bjpg.jpg?x-oss-process=image/resize,m_fill,w_188,h_188/format,webp/quality,q_75',15.00,10003,NULL),(14,'霸气一桶凤梨百香果','冷饮，百香果汁，鲜果肉','https://cube.elemecdn.com/c/15/4f461bd595e795ff79e54ca7db437jpg.jpg?x-oss-process=image/resize,m_fill,w_188,h_188/format,webp/quality,q_75',26.00,10003,NULL),(15,'水蜜桃蛋糕卷','鸡蛋，淡奶油','https://cube.elemecdn.com/b/58/f5220be1e99ae2716e3627b6db824jpg.jpg?x-oss-process=image/resize,m_fill,w_188,h_188/format,webp/quality,q_75',9.80,10003,NULL),(16,'芋泥雪蓉小贝（单个）','牛奶，奶油，麻薯，奶粉','https://cube.elemecdn.com/f/a6/742673a6a8becf104e072f14e110djpg.jpg?x-oss-process=image/resize,m_fill,w_188,h_188/format,webp/quality,q_75',8.80,10003,NULL),(17,'金色山脉鲜奶茶','冷热皆宜，金色山脉红茶','https://cube.elemecdn.com/4/66/122993f1bb7a6a286bb5b3458e1c9jpg.jpg?x-oss-process=image/resize,m_fill,w_188,h_188/format,webp/quality,q_75',13.00,10003,NULL),(18,'牛奶曲奇套餐','建议送达后尽快饮用。到店饮用口感更佳','https://cube.elemecdn.com/5/55/7ca010e76145ff26e6617ca615f3fpng.png?x-oss-process=image/resize,m_fill,w_188,h_188/format,webp/quality,q_75',15.90,10004,NULL),(19,'厚乳拿铁','冷热皆宜，浓缩咖啡','https://cube.elemecdn.com/e/78/b38173c537a65ffa6b2dc0f6517c5png.png?x-oss-process=image/resize,m_fill,w_188,h_188/format,webp/quality,q_75',19.20,10004,NULL),(20,'加浓 美式','冷热皆宜，香醇浓郁','https://cube.elemecdn.com/4/76/a2707ae75958d49aac54d9bdfdd66png.png?x-oss-process=image/resize,m_fill,w_188,h_188/format,webp/quality,q_75',19.14,10004,NULL),(21,'青森苹果丝绒拿铁','冷热皆宜，丝滑口感','https://cube.elemecdn.com/8/f6/5abc0ec1d8f6b56405ec717390b66png.png?x-oss-process=image/resize,m_fill,w_188,h_188/format,webp/quality,q_75',23.80,10004,NULL),(22,'抹茶瑞纳冰','浓香，奶油','https://cube.elemecdn.com/6/0d/5d46ed4d0bfc305b13343f12c9f94png.png?x-oss-process=image/resize,m_fill,w_188,h_188/format,webp/quality,q_75',24.50,10004,NULL),(23,'牛肉粉丝汤+二两锅贴+一个烧饼','原料：牛肉,粉丝汤,锅贴,烧饼','https://cube.elemecdn.com/7/f8/5095312fcff37e311a9ff343eb008jpeg.jpeg?x-oss-process=image/resize,m_fill,w_197,h_197/format,webp/quality,q_75',41.00,10005,NULL),(24,'牛肉粉丝汤+一两锅贴（5个）','原料：牛肉','https://cube.elemecdn.com/c/33/80dd9ae1b60854a3a9dbda2921e80jpeg.jpeg?x-oss-process=image/resize,m_fill,w_197,h_197/format,webp/quality,q_75',14.90,10005,NULL),(25,'招牌牛肉汤+锅贴（5只）+烧饼（1个）','超级好吃的烧饼','https://cube.elemecdn.com/7/f8/5095312fcff37e311a9ff343eb008jpeg.jpeg?x-oss-process=image/resize,m_fill,w_197,h_197/format,webp/quality,q_75',17.90,10005,NULL),(26,'招牌牛肉粉丝汤+烧饼（1个）','原料：牛肉','https://cube.elemecdn.com/4/fa/1d5e98b0841584b61b0763fa22f31jpeg.jpeg?x-oss-process=image/resize,m_fill,w_197,h_197/format,webp/quality,q_75',14.90,10005,NULL),(27,'牛肉粉丝汤+2两锅贴（10只）','原料：牛肉','https://cube.elemecdn.com/c/33/80dd9ae1b60854a3a9dbda2921e80jpeg.jpeg?x-oss-process=image/resize,m_fill,w_197,h_197/format,webp/quality,q_75',18.90,10005,NULL),(28,'烧饼1个（单点不送）','原料：烧饼','https://cube.elemecdn.com/8/2e/c9925eb966a2a7a7ac4d40fffec98jpeg.jpeg?x-oss-process=image/resize,m_fill,w_197,h_197/format,webp/quality,q_75',3.00,10005,NULL),(29,'烧饼2个（泡汤吃绝绝子）','原料：烧饼','https://cube.elemecdn.com/f/4d/da22472fe31db793175e4d8398808jpeg.jpeg?x-oss-process=image/resize,m_fill,w_197,h_197/format,webp/quality,q_75',5.00,10005,NULL),(30,'大粒牛肉石锅拌饭','累计销售3000万份朝鲜族非遗美食，营养丰富。','https://cube.elemecdn.com/1/d5/62ffa8b9d2f59e21ea8c8a1f0c054jpeg.jpeg?x-oss-process=image/resize,m_fill,w_197,h_197/format,webp/quality,q_75',21.00,10006,NULL),(31,'烤牛肉拌饭','香味浓郁，咸甜适口','https://cube.elemecdn.com/a/30/fae17593f7b735624ebe3cf850ff2jpeg.jpeg?x-oss-process=image/resize,m_fill,w_197,h_197/format,webp/quality,q_75',25.00,10006,NULL),(32,'金枪鱼拌饭','金枪鱼肉质柔嫩鲜美，口感丰富','https://cube.elemecdn.com/9/3b/2c8a127ceafc38ed76a6cfce5fd12jpeg.jpeg?x-oss-process=image/resize,m_fill,w_197,h_197/format,webp/quality,q_75',24.00,10006,NULL),(33,'安格斯肥牛(不含米饭)','老少皆宜，超级下饭','https://cube.elemecdn.com/b/69/fdc5517d6a15a1e6363fe6ffddc7ajpeg.jpeg?x-oss-process=image/resize,m_fill,w_197,h_197/format,webp/quality,q_75',26.00,10006,NULL),(34,'烧牛肉炖土豆(不含米饭)','精选谷饲牛肉，黄瓤土豆，绵软入味','https://cube.elemecdn.com/b/69/fdc5517d6a15a1e6363fe6ffddc7ajpeg.jpeg?x-oss-process=image/resize,m_fill,w_197,h_197/format,webp/quality,q_75',30.00,10006,NULL),(35,'香煎青花鱼','肉质酥软，味道咸香','https://cube.elemecdn.com/a/b0/01110a724dd2986d14065ec3d4ce0jpeg.jpeg?x-oss-process=image/resize,m_fill,w_197,h_197/format,webp/quality,q_75',11.00,10006,NULL),(36,'农家一锅出2000ml‖本店招牌','俺家菜园子出啥，俺就给你炖啥！','https://cube.elemecdn.com/a/60/507ab9f80ea017bc9da3e2d33871cpng.png?x-oss-process=image/resize,m_fill,w_197,h_197/format,webp/quality,q_75',29.80,10007,NULL),(37,'水煮肉片2000ml‖本店招牌','秘制火锅底料，够麻够辣','https://cube.elemecdn.com/2/62/515040f51760ed4b58a1a374f9aa4png.png?x-oss-process=image/resize,m_fill,w_197,h_197/format,webp/quality,q_75',28.80,10007,NULL),(38,'招牌酱脊骨（七块）','秘制酱料，酱香浓郁','https://cube.elemecdn.com/e/6e/97aa0feb7c0e2321c508dd73c576apng.png?x-oss-process=image/resize,m_fill,w_197,h_197/format,webp/quality,q_75',29.90,10007,NULL),(39,'菠萝咕咾肉','酸辣可口，女士和孩子得最爱','https://cube.elemecdn.com/f/21/f89413ac2582831e2fa79c66ad793png.png?x-oss-process=image/resize,m_fill,w_197,h_197/format,webp/quality,q_75',28.00,10007,NULL),(40,'老式锅包肉','色泽金黄，口味儿酸甜','https://cube.elemecdn.com/e/7d/dd7ee3c3b838e5df12745cdfb5450png.png?x-oss-process=image/resize,m_fill,w_197,h_197/format,webp/quality,q_75',29.90,10007,NULL),(41,'晨曦鲍鱼饭','【加量不加价】','https://cube.elemecdn.com/7/28/493841b5c8a0eb692560fdeed02bepng.png?x-oss-process=image/resize,m_fill,w_197,h_197/format,webp/quality,q_75',37.80,10008,NULL),(42,'我要加鲍鱼','单加鲍鱼放在炖品里面','https://cube.elemecdn.com/6/f6/2d9acbc956c2ec8df42f3c0c82b76png.png?x-oss-process=image/resize,m_fill,w_197,h_197/format,webp/quality,q_75',8.80,10008,NULL),(43,'（大份）香辣鲜蛤','嗨吃到爽','https://cube.elemecdn.com/5/47/12d294f74234b123b4e9389a7843ejpeg.jpeg?x-oss-process=image/resize,m_fill,w_197,h_197/format,webp/quality,q_75',16.90,10008,NULL),(44,'如意佛跳墙捞饭','满满一碗捞饭，满满的温暖！','https://cube.elemecdn.com/3/11/c0ba3de2820a7ddb53d7286f5d31epng.png?x-oss-process=image/resize,m_fill,w_197,h_197/format,webp/quality,q_75',38.80,10008,NULL),(45,'瓦罐松茸鸡汤（米饭需单点）','含有虫草花，婴儿、儿童及真菌过敏者不宜食用！','https://cube.elemecdn.com/8/44/f5d42f4c14feae2e00687010086a5png.png?x-oss-process=image/resize,m_fill,w_197,h_197/format,webp/quality,q_75',45.90,10008,NULL);
/*!40000 ALTER TABLE `food` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderdetailet`
--

DROP TABLE IF EXISTS `orderdetailet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orderdetailet` (
  `odId` int NOT NULL AUTO_INCREMENT COMMENT '订单明细编号',
  `orderId` int NOT NULL COMMENT '所属订单编号',
  `foodId` int NOT NULL COMMENT '食品编号',
  `quantity` int NOT NULL COMMENT '数量',
  PRIMARY KEY (`odId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderdetailet`
--

LOCK TABLES `orderdetailet` WRITE;
/*!40000 ALTER TABLE `orderdetailet` DISABLE KEYS */;
/*!40000 ALTER TABLE `orderdetailet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `orderId` int NOT NULL AUTO_INCREMENT COMMENT '订单编号',
  `userId` varchar(20) NOT NULL COMMENT '用户编号',
  `businessId` int NOT NULL COMMENT '商家编号',
  `orderDate` varchar(20) NOT NULL COMMENT '订购日期',
  `orderTotal` decimal(7,2) NOT NULL DEFAULT '0.00' COMMENT '订单总价',
  `daId` int NOT NULL COMMENT '送货地址编号',
  `orderState` int NOT NULL DEFAULT '0' COMMENT '订单状态（0：未支付； 1：已支付）',
  PRIMARY KEY (`orderId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reducecredit`
--

DROP TABLE IF EXISTS `reducecredit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reducecredit` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `userId` varchar(20) NOT NULL COMMENT '用户id',
  `recordId` int NOT NULL COMMENT '记录表中消费积分记录id',
  `usableId` int NOT NULL COMMENT '可用积分表中对应记录id',
  `credit` int NOT NULL COMMENT '花费的积分值',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  `expiredTime` datetime NOT NULL COMMENT '原记录（可用积分）的过期时间',
  PRIMARY KEY (`id`),
  KEY `reduceCredit_creditRecord_fk` (`recordId`),
  KEY `reduceCredit_usableCredit_fk` (`usableId`),
  KEY `reduceCredit_user_fk` (`userId`),
  CONSTRAINT `reduceCredit_creditRecord_fk` FOREIGN KEY (`recordId`) REFERENCES `creditrecord` (`id`),
  CONSTRAINT `reduceCredit_usableCredit_fk` FOREIGN KEY (`usableId`) REFERENCES `usablecredit` (`id`),
  CONSTRAINT `reduceCredit_user_fk` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='扣减积分详情表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reducecredit`
--

LOCK TABLES `reducecredit` WRITE;
/*!40000 ALTER TABLE `reducecredit` DISABLE KEYS */;
/*!40000 ALTER TABLE `reducecredit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction` (
  `transactionId` int NOT NULL AUTO_INCREMENT COMMENT '交易流水Id',
  `time` datetime NOT NULL COMMENT '交易时间',
  `money` decimal(7,2) NOT NULL COMMENT '交易金额',
  `type` int NOT NULL COMMENT '0充值、1提现、2转账',
  `inputwalletId` int DEFAULT NULL COMMENT '入账的walletId',
  `outputwalletId` int DEFAULT NULL COMMENT '出账的walletId',
  PRIMARY KEY (`transactionId`),
  KEY `inputwalletId` (`inputwalletId`),
  KEY `outputwalletId` (`outputwalletId`),
  CONSTRAINT `transaction_ibfk_1` FOREIGN KEY (`inputwalletId`) REFERENCES `virtualwallet` (`walletId`),
  CONSTRAINT `transaction_ibfk_2` FOREIGN KEY (`outputwalletId`) REFERENCES `virtualwallet` (`walletId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usablecredit`
--

DROP TABLE IF EXISTS `usablecredit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usablecredit` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `userId` varchar(20) NOT NULL COMMENT '用户id',
  `recordId` int NOT NULL COMMENT '记录表中获得积分记录id',
  `credit` int NOT NULL COMMENT '积分值',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  `expiredTime` datetime NOT NULL COMMENT '过期时间',
  `deleted` int NOT NULL COMMENT '删除标记（1删除0未删除）',
  PRIMARY KEY (`id`),
  KEY `usableCredit_creditRecord_fk` (`recordId`),
  KEY `usableCredit_user_fk` (`userId`),
  CONSTRAINT `usableCredit_creditRecord_fk` FOREIGN KEY (`recordId`) REFERENCES `creditrecord` (`id`),
  CONSTRAINT `usableCredit_user_fk` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='可用积分表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usablecredit`
--

LOCK TABLES `usablecredit` WRITE;
/*!40000 ALTER TABLE `usablecredit` DISABLE KEYS */;
/*!40000 ALTER TABLE `usablecredit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `userId` varchar(20) NOT NULL COMMENT '用户编号',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `userName` varchar(20) NOT NULL COMMENT '用户名称',
  `userSex` int NOT NULL DEFAULT '1' COMMENT '用户性别（1：男； 0：女）',
  `userImg` mediumtext COMMENT '用户头像',
  `delTag` int NOT NULL DEFAULT '1' COMMENT '删除标记（1：正常； 0：删除）',
  `walletId` int DEFAULT NULL,
  PRIMARY KEY (`userId`),
  KEY `walletId` (`walletId`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`walletId`) REFERENCES `virtualwallet` (`walletId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('123','OxvrTfYELUiPvfm+WhaHKQ==','123',1,'https://img2.baidu.com/it/u=2484555436,2349420889&fm=253&fmt=auto&app=138&f=JPEG?w=400&h=400',1,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `virtualwallet`
--

DROP TABLE IF EXISTS `virtualwallet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `virtualwallet` (
  `walletId` int NOT NULL AUTO_INCREMENT COMMENT '虚拟钱包Id',
  `balance` decimal(7,2) NOT NULL DEFAULT '0.00' COMMENT '钱包余额',
  PRIMARY KEY (`walletId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `virtualwallet`
--

LOCK TABLES `virtualwallet` WRITE;
/*!40000 ALTER TABLE `virtualwallet` DISABLE KEYS */;
/*!40000 ALTER TABLE `virtualwallet` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-09-07 21:25:00
