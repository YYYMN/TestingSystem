--
-- Table structure for table `answer`
--

DROP TABLE IF EXISTS `answer`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
SET character_set_client = utf8;
CREATE TABLE `answer` (
  `answerId`    INT(11) NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(45)      DEFAULT NULL,
  `correct`     TINYINT(1)       DEFAULT NULL,
  `questionId`  INT(11)          DEFAULT NULL,
  PRIMARY KEY (`answerId`),
  KEY `questionId_idx` (`questionId`),
  CONSTRAINT `questionId` FOREIGN KEY (`questionId`) REFERENCES `question` (`questionId`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 311
  DEFAULT CHARSET = utf8
;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answer`
--

LOCK TABLES `answer` WRITE;
/*!40000 ALTER TABLE `answer`
  DISABLE KEYS */;
INSERT INTO `answer`
VALUES (1, 'я не знаю', 0, 1), (2, 'ООП - это ООП', 1, 1), (3, 'наследование - это наследование', 1, 2),
  (4, 'я не знаю', 0, 2), (5, 'полиморфизм - это полиморфизм', 1, 3), (6, 'я не знаю', 0, 3),
  (7, 'инкапсуляция - это инкапсуляция', 1, 4), (8, 'я не знаю', 0, 4), (9, 'абстракция - это абстракция', 1, 5),
  (10, 'я не знаю', 0, 5), (11, 'final - это final', 1, 6), (12, 'я не знаю', 0, 6),
  (13, 'коллекция - это коллекция', 1, 8), (14, 'я не знаю', 0, 8), (15, 'Set - это Set', 1, 9),
  (16, 'я не знаю', 0, 9), (17, 'static - это static', 1, 10), (18, 'я не знаю', 0, 10),
  (286, 'БД - база данных', 1, 274), (287, 'БД - боевая дивизия', 0, 274), (289, 'ороло', 0, 3),
  (290, 'Аспект - это аспект', 1, 275), (291, 'хз', 0, 275), (292, 'я точно не знаю', 0, 2), (303, '2', 0, 276),
  (305, '1', 1, 276), (306, 'Логироание - это логирование', 1, 277), (308, ',knlknlk', 0, 277), (309, '3', 0, 276),
  (310, '123', 1, 277);
/*!40000 ALTER TABLE `answer`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `link`
--

DROP TABLE IF EXISTS `link`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
SET character_set_client = utf8;
CREATE TABLE `link` (
  `linkId`       INT(11) NOT NULL,
  `link`         VARCHAR(45) DEFAULT NULL,
  `literatureId` INT(11)     DEFAULT NULL,
  PRIMARY KEY (`linkId`),
  KEY `literatureId_idx` (`literatureId`),
  CONSTRAINT `literatureId` FOREIGN KEY (`literatureId`) REFERENCES `literature` (`literatureId`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `link`
--

LOCK TABLES `link` WRITE;
/*!40000 ALTER TABLE `link`
  DISABLE KEYS */;
/*!40000 ALTER TABLE `link`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `literature`
--

DROP TABLE IF EXISTS `literature`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
SET character_set_client = utf8;
CREATE TABLE `literature` (
  `literatureId` INT(11) NOT NULL AUTO_INCREMENT,
  `description`  VARCHAR(45)      DEFAULT NULL,
  `questionId`   INT(11)          DEFAULT NULL,
  PRIMARY KEY (`literatureId`),
  KEY `questionId_idx` (`questionId`),
  CONSTRAINT `questionId1` FOREIGN KEY (`questionId`) REFERENCES `question` (`questionId`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `literature`
--

LOCK TABLES `literature` WRITE;
/*!40000 ALTER TABLE `literature`
  DISABLE KEYS */;
/*!40000 ALTER TABLE `literature`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qt_conn`
--

DROP TABLE IF EXISTS `qt_conn`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
SET character_set_client = utf8;
CREATE TABLE `qt_conn` (
  `qtconn_Id`  INT(11) NOT NULL AUTO_INCREMENT,
  `testId`     INT(11)          DEFAULT NULL,
  `questionId` INT(11)          DEFAULT NULL,
  PRIMARY KEY (`qtconn_Id`),
  KEY `qt_conn_question_questionId_fk` (`questionId`),
  KEY `qt_conn_test_testId_fk` (`testId`),
  CONSTRAINT `qt_conn_question_questionId_fk` FOREIGN KEY (`questionId`) REFERENCES `question` (`questionId`),
  CONSTRAINT `qt_conn_test_testId_fk` FOREIGN KEY (`testId`) REFERENCES `test` (`testId`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 77
  DEFAULT CHARSET = utf8
;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qt_conn`
--

LOCK TABLES `qt_conn` WRITE;
/*!40000 ALTER TABLE `qt_conn`
  DISABLE KEYS */;
INSERT INTO `qt_conn`
VALUES (18, 26, 1), (19, 26, 2), (20, 26, 3), (26, 32, 1), (27, 32, 2), (28, 32, 3), (29, 32, 4), (30, 33, 1),
  (31, 33, 2), (32, 33, 3), (33, 33, 4), (34, 33, 5), (35, 34, 1), (36, 34, 2), (37, 34, 3), (38, 34, 4), (39, 34, 5),
  (40, 34, 5), (41, 35, 3), (42, 36, 2), (43, 36, 4), (44, 36, 5), (45, 37, 2), (46, 37, 4), (47, 37, 5), (48, 37, 274),
  (49, 38, 2), (50, 38, 4), (51, 38, 5), (52, 38, 274), (53, 39, 2), (54, 39, 4), (55, 39, 5), (56, 40, 4), (57, 40, 5),
  (58, 40, 276), (59, 41, 274), (60, 41, 9), (61, 42, 4), (62, 42, 5), (63, 42, 276), (64, 42, 10), (65, 43, 4),
  (66, 43, 5), (67, 43, 276), (68, 43, 10), (69, 44, 4), (70, 44, 5), (71, 44, 276), (72, 45, 4), (73, 45, 5),
  (74, 45, 276), (75, 46, 274), (76, 46, 275);
/*!40000 ALTER TABLE `qt_conn`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
SET character_set_client = utf8;
CREATE TABLE `question` (
  `questionId`  INT(11) NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(200)     DEFAULT NULL,
  PRIMARY KEY (`questionId`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 278
  DEFAULT CHARSET = utf8
;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question`
  DISABLE KEYS */;
INSERT INTO `question` VALUES (1, 'Что такое ООП?'), (2, 'Что такое наследование?'), (3, 'Что такое полиморфизм?'),
  (4, 'Что такое инкапсуляция'), (5, 'Что такое абстракция?'), (6, 'Ключевое слово final'), (8, 'Что такое коллекция?'),
  (9, 'В чём особенность коллекций реализующих интерфейс Set?'), (10, 'Ключевое слово static'), (274, 'Что такое БД?'),
  (275, 'Что такое аспект'), (276, 'New question'), (277, 'Что такое логирование');
/*!40000 ALTER TABLE `question`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
SET character_set_client = utf8;
CREATE TABLE `role` (
  `roleId` INT(11) NOT NULL AUTO_INCREMENT,
  `user`   TINYINT(4)       DEFAULT NULL,
  `tutor`  TINYINT(4)       DEFAULT NULL,
  `admin`  TINYINT(4)       DEFAULT NULL,
  PRIMARY KEY (`roleId`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 15
  DEFAULT CHARSET = utf8
;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role`
  DISABLE KEYS */;
INSERT INTO `role`
VALUES (1, 1, 0, 0), (2, 0, 1, 0), (3, 0, 0, 1), (4, 1, 1, 0), (5, 0, 1, 1), (6, 1, 0, 1), (7, 1, 1, 1);
/*!40000 ALTER TABLE `role`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `statistic`
--

DROP TABLE IF EXISTS `statistic`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
SET character_set_client = utf8;
CREATE TABLE `statistic` (
  `statisticId` INT(11) NOT NULL AUTO_INCREMENT,
  `data`        DATE             DEFAULT NULL,
  `correct`     TINYINT(4)       DEFAULT NULL,
  `questionId`  INT(11)          DEFAULT NULL,
  `userId`      INT(11)          DEFAULT NULL,
  `testId`      INT(11) NOT NULL,
  PRIMARY KEY (`statisticId`),
  KEY `userId_idx` (`userId`),
  KEY `questionIddd_idx` (`questionId`),
  CONSTRAINT `questionIddd` FOREIGN KEY (`questionId`) REFERENCES `question` (`questionId`),
  CONSTRAINT `userId` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 95
  DEFAULT CHARSET = utf8
;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `statistic`
--

LOCK TABLES `statistic` WRITE;
/*!40000 ALTER TABLE `statistic`
  DISABLE KEYS */;
INSERT INTO `statistic`
VALUES (21, '2019-02-16', 1, 1, 1, 1), (22, '2019-02-16', 1, 2, 1, 1), (23, '2019-02-16', 1, 3, 1, 1),
  (27, '2019-02-19', 1, 4, 2, 2), (28, '2019-02-19', 0, 5, 2, 2), (29, '2019-02-19', 0, 6, 2, 2),
  (30, '2019-02-19', 0, 4, 2, 2), (31, '2019-02-19', 1, 5, 2, 2), (32, '2019-02-19', 1, 6, 2, 2),
  (33, '2019-02-19', 1, 8, 2, 3), (34, '2019-02-19', 1, 9, 2, 3), (35, '2019-02-19', 1, 10, 2, 3),
  (36, '2019-02-19', 1, 4, 3, 2), (37, '2019-02-19', 0, 5, 3, 2), (67, '2019-02-19', 1, 6, 3, 2),
  (68, '2019-02-19', 0, 4, 3, 2), (69, '2019-02-19', 0, 5, 3, 2), (70, '2019-02-19', 1, 6, 3, 2),
  (71, '2019-02-19', 1, 4, 3, 2), (72, '2019-02-19', 0, 5, 3, 2), (73, '2019-02-19', 1, 6, 3, 2),
  (83, '2019-02-17', 1, 1, 1, 1), (84, '2019-02-17', 0, 2, 1, 1), (85, '2019-02-17', 1, 3, 1, 1),
  (86, '2019-02-18', 0, 1, 1, 1), (87, '2019-02-18', 0, 2, 1, 1), (88, '2019-02-18', 1, 3, 1, 1),
  (89, '2019-02-19', 0, 1, 1, 1), (90, '2019-02-19', 0, 2, 1, 1), (91, '2019-02-19', 1, 3, 1, 1),
  (92, '2019-02-20', 1, 1, 1, 1), (93, '2019-02-20', 1, 2, 1, 1), (94, '2019-02-20', 1, 3, 1, 1);
/*!40000 ALTER TABLE `statistic`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test`
--

DROP TABLE IF EXISTS `test`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
SET character_set_client = utf8;
CREATE TABLE `test` (
  `testId`      INT(11) NOT NULL AUTO_INCREMENT,
  `name`        VARCHAR(45)      DEFAULT NULL,
  `description` VARCHAR(45)      DEFAULT NULL,
  `topicId`     INT(11)          DEFAULT NULL,
  PRIMARY KEY (`testId`),
  KEY `topicId_idx` (`topicId`),
  CONSTRAINT `topicId` FOREIGN KEY (`topicId`) REFERENCES `topic` (`topicId`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 47
  DEFAULT CHARSET = utf8
;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test`
--

LOCK TABLES `test` WRITE;
/*!40000 ALTER TABLE `test`
  DISABLE KEYS */;
INSERT INTO `test`
VALUES (1, 'JavaTest1', 'JavaTest1', 1), (2, 'JavaTest2', 'JavaTest1', 1), (3, 'JavaTest3', 'JavaTest2', 1),
  (26, 'JavaTEst4', 'JavaOOPTest', 1), (32, 'JavaOOPTest', 'JavaOOPTest', 1), (33, 'JavaOOPTest', 'JavaOOPTest', 1),
  (34, 'JavaOOPTest', 'JavaOOPTest', 1), (35, 'TETSTSTTE', 'TETSTSTTE', 1), (36, 'NewTest', 'NewTest', 1),
  (37, 'NewTest', 'NewTest', 1), (38, 'NewTest1', 'NewTest1', 1), (39, 'NewTest1', 'NewTest1', 1),
  (40, 'NewTest1', 'NewTest1', 1), (41, 'NewTest2', 'NewTest2', 1), (42, 'NewTest1', 'NewTest1', 1),
  (43, 'NewTest1', 'NewTest1', 1), (44, 'NewTest1', 'NewTest1', 1), (45, 'NewTest1', 'NewTest1', 1),
  (46, 'NewJsTest', 'NewJsTest', 5);
/*!40000 ALTER TABLE `test`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `topic`
--

DROP TABLE IF EXISTS `topic`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
SET character_set_client = utf8;
CREATE TABLE `topic` (
  `topicId`     INT(11) NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(45)      DEFAULT NULL,
  `name`        VARCHAR(45)      DEFAULT NULL,
  PRIMARY KEY (`topicId`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 8
  DEFAULT CHARSET = utf8
;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `topic`
--

LOCK TABLES `topic` WRITE;
/*!40000 ALTER TABLE `topic`
  DISABLE KEYS */;
INSERT INTO `topic`
VALUES (1, 'Topic about Java', 'Java'), (5, 'TopicAboutJS', 'TopicAboutJS'), (7, '123', 'New Topic');
/*!40000 ALTER TABLE `topic`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
SET character_set_client = utf8;
CREATE TABLE `user` (
  `userId`    INT(11) NOT NULL AUTO_INCREMENT,
  `lastName`  VARCHAR(45)      DEFAULT NULL,
  `firstName` VARCHAR(45)      DEFAULT NULL,
  `login`     VARCHAR(45)      DEFAULT NULL,
  `password`  VARCHAR(45)      DEFAULT NULL,
  `email`     VARCHAR(45)      DEFAULT NULL,
  `roleId`    INT(11)          DEFAULT NULL,
  PRIMARY KEY (`userId`),
  KEY `roleId_idx` (`roleId`),
  CONSTRAINT `roleId` FOREIGN KEY (`roleId`) REFERENCES `role` (`roleId`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 37
  DEFAULT CHARSET = utf8
;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user`
  DISABLE KEYS */;
INSERT INTO `user` VALUES (1, 'Ivanov', 'Petya', 'petyaLogin', '123', '123@gmail.com', 3),
  (2, 'Pupkin', 'Sasha', 'sashaLogin', '123', NULL, 1), (3, 'Fedorova', 'Maria', 'mashaLogin', '123', NULL, 1),
  (27, 'Pupkin', 'Vasya', 'vladislavnovikovU00@gmail.com', '6666666', 'vasya@gmail.com', 1),
  (33, 'Galynin', 'Timur', 'Sitx', '123', 'tsimur.galynin@gmail.com', 2);
/*!40000 ALTER TABLE `user`
  ENABLE KEYS */;
UNLOCK TABLES;