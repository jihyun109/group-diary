-- MySQL dump 10.13  Distrib 8.3.0, for macos14.2 (x86_64)
--
-- Host: localhost    Database: test
-- ------------------------------------------------------
-- Server version	8.3.0

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
-- Table structure for table `diaries`
--

DROP TABLE IF EXISTS `diaries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `diaries` (
  `id` int NOT NULL AUTO_INCREMENT,
  `written_date` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `writer_id` int NOT NULL,
  `details` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `diary_title` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `diaries`
--

LOCK TABLES `diaries` WRITE;
/*!40000 ALTER TABLE `diaries` DISABLE KEYS */;
INSERT INTO `diaries` VALUES (38,'2024-06-23',1,'아빠 생신~\n족발을 먹었다~','아빠 생신'),(39,'2024-06-16',2,'오늘 \"인사이드 아웃\"을 봤다. 라일리의 머릿속 감정들이 주인공인데, 정말 신선했다. \'슬픔\'의 중요성을 다룬 부분이 특히 인상적이었다. 감정의 균형이 중요하다는 걸 깨달았다. 비주얼과 목소리 연기도 훌륭했다. 짧지만 깊은 여운이 남은 영화였다.','인사이드 아웃 후기'),(40,'2024-06-10',2,'오늘은 한국 여행 중 남산을 방문했다. 서울의 중심에서 자연을 느낄 수 있는 곳이라 기대가 컸다. 케이블카를 타고 올라가는 동안 서울의 전경이 점점 넓게 펼쳐졌다. 정상에 도착하니 탁 트인 풍경이 정말 아름다웠다.\n\n남산타워 앞에서 사진도 찍고, 주변을 산책하며 여유로운 시간을 보냈다. 한국의 전통 정자와 현대적인 도시가 어우러진 모습이 인상적이었다.\n\n또한, 서울타워에서 본 야경은 잊을 수 없을 만큼 멋졌다. 도시의 불빛들이 반짝이는 모습이 정말 환상적이었다. 한국에서의 하루가 정말 특별하고 즐거웠다. 다음에는 더 많은 곳을 둘러보고 싶다.','남산 방문 일기'),(42,'2024-06-11',19,'오늘은 친구와 동네 카페에 다녀왔다. 작은 카페였지만, 아늑하고 분위기가 좋았다. 커피 한 잔과 함께 친구와 이런저런 소소한 이야기를 나눴다.\n\n대학 시절 추억, 요즘 관심사, 앞으로의 계획 등 다양한 주제로 웃고 떠들었다. 창밖을 보며 느긋하게 시간을 보내니 정말 힐링이 되는 느낌이었다.\n\n카페의 음악도 잔잔해서 대화하기 좋았다. 따뜻한 커피와 함께한 친구와의 시간이 정말 소중했다. 오늘 하루는 소소하지만 특별한 시간이었던 것 같다. 다음에도 또 오고 싶다.','성수동 카페'),(43,'2024-06-04',19,'제주도에서 한 달 살기를 시작한 지 일주일이 지났다. 처음 며칠은 새로운 환경에 적응하느라 분주했지만, 이제는 제법 익숙해졌다. 매일 아침 바다를 보며 산책하는 시간이 가장 좋다. 깨끗한 공기와 시원한 바람이 마음을 편안하게 해준다.\n\n현지 시장에서 신선한 해산물과 과일을 사서 요리하는 재미도 쏠쏠하다. 제주도 특유의 음식들도 하나씩 도전해보고 있다. 오늘은 흑돼지 구이를 먹었는데, 정말 맛있었다.\n\n한 달 동안 제주도를 더 깊이 경험해보고 싶다. 올레길을 걸으며 자연을 만끽하고, 다양한 명소를 탐방할 계획이다. 이곳에서의 시간이 나에게 큰 힐링과 영감을 줄 것 같다. 앞으로의 한 달이 정말 기대된다.','제주도 한 달 살기'),(44,'2024-05-31',32,'오늘 친구들과 함께 하이디라오에 다녀왔다. 이미 맛있다는 소문은 들었지만, 직접 가보니 정말 대단했다. 다양한 메뉴와 신선한 재료들, 그리고 무엇보다도 환상적인 맛에 모두 감탄했다.\n\n특히 소고기와 해산물이 정말 신선했고, 소스 바에서 자신만의 소스를 만들어 먹는 재미도 쏠쏠했다. 서비스도 훌륭해서 식사 내내 편안하고 즐거운 시간을 보낼 수 있었다.\n\n식사를 마치고 나오는 길에 친구들에게 다시 한 번 꼭 가자고 했다. 모두들 다음 번에는 더 많은 친구들과 함께 가자며 기대에 부풀어 있었다. 오늘 하이디라오에서의 식사는 정말 특별한 경험이었다. 다음에도 꼭 다시 가고 싶다.','하이디라오 언제 또 가지?'),(45,'2024-06-05',33,'오늘 오랜만에 동창회에 다녀왔다. 졸업 후 각자 바쁘게 지내느라 자주 만나지 못했는데, 이렇게 한자리에 모이니 정말 반가웠다. 모두들 예전 모습 그대로여서 금세 학창 시절로 돌아간 듯한 기분이 들었다.\n\n처음에는 어색했지만, 옛날 이야기를 나누면서 금방 분위기가 풀어졌다. 학교 다닐 때의 추억, 선생님들 이야기, 그동안의 근황 등을 이야기하며 웃음이 끊이지 않았다. 특히, 장난치던 에피소드들을 회상하면서 모두 배꼽을 잡고 웃었다.\n\n맛있는 음식을 먹으며 한참을 떠들다가, 다 함께 사진도 찍었다. 시간이 지나도 변하지 않는 친구들과의 우정이 정말 소중하게 느껴졌다. 헤어질 때는 아쉬운 마음이 컸지만, 다음 동창회를 기약하며 다시 만날 날을 기대하기로 했다.\n\n오늘 동창회는 정말 즐겁고 의미 있는 시간이었고, 오래도록 기억에 남을 것 같다.',''),(46,'2024-06-12',33,'오늘 오랜만에 동창회에 다녀왔다. 졸업 후 각자 바쁘게 지내느라 자주 만나지 못했는데, 이렇게 한자리에 모이니 정말 반가웠다. 모두들 예전 모습 그대로여서 금세 학창 시절로 돌아간 듯한 기분이 들었다.\n\n처음에는 어색했지만, 옛날 이야기를 나누면서 금방 분위기가 풀어졌다. 학교 다닐 때의 추억, 선생님들 이야기, 그동안의 근황 등을 이야기하며 웃음이 끊이지 않았다. 특히, 장난치던 에피소드들을 회상하면서 모두 배꼽을 잡고 웃었다.\n\n맛있는 음식을 먹으며 한참을 떠들다가, 다 함께 사진도 찍었다. 시간이 지나도 변하지 않는 친구들과의 우정이 정말 소중하게 느껴졌다. 헤어질 때는 아쉬운 마음이 컸지만, 다음 동창회를 기약하며 다시 만날 날을 기대하기로 했다.\n\n오늘 동창회는 정말 즐겁고 의미 있는 시간이었고, 오래도록 기억에 남을 것 같다.','동창회'),(47,'2024-06-05',31,'나는 푸바오\n중학교를 졸업하고 팬더가 됐지','나는 푸바오');
/*!40000 ALTER TABLE `diaries` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `members`
--

DROP TABLE IF EXISTS `members`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `members` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `team_id` int DEFAULT NULL,
  `status` int DEFAULT NULL,
  `inviter_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=118 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `members`
--

LOCK TABLES `members` WRITE;
/*!40000 ALTER TABLE `members` DISABLE KEYS */;
INSERT INTO `members` VALUES (92,19,67,0,1),(93,28,67,1,1),(94,29,67,1,1),(95,1,67,0,1),(96,26,68,1,1),(97,2,68,0,1),(98,1,68,0,1),(99,1,69,0,1),(102,19,70,1,1),(103,1,71,1,19),(104,19,71,0,19),(105,32,72,0,33),(106,34,72,1,33),(107,1,72,1,33),(108,33,72,0,33),(109,29,73,1,2),(110,26,73,1,2),(111,30,73,1,2),(112,28,73,1,2),(113,2,73,0,2),(114,20,73,1,2),(115,32,69,1,1),(116,33,69,0,1),(117,31,69,0,1);
/*!40000 ALTER TABLE `members` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `team_diary`
--

DROP TABLE IF EXISTS `team_diary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `team_diary` (
  `id` int NOT NULL AUTO_INCREMENT,
  `diary_id` int NOT NULL,
  `team_id` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=116 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `team_diary`
--

LOCK TABLES `team_diary` WRITE;
/*!40000 ALTER TABLE `team_diary` DISABLE KEYS */;
INSERT INTO `team_diary` VALUES (99,38,67),(100,38,68),(101,38,69),(102,0,68),(103,0,73),(104,0,73),(105,0,73),(106,0,73),(107,39,73),(108,40,68),(109,0,71),(110,42,71),(111,43,67),(112,44,72),(113,0,69),(114,46,69),(115,47,69);
/*!40000 ALTER TABLE `team_diary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teams`
--

DROP TABLE IF EXISTS `teams`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teams` (
  `id` int NOT NULL AUTO_INCREMENT,
  `team_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `creator_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teams`
--

LOCK TABLES `teams` WRITE;
/*!40000 ALTER TABLE `teams` DISABLE KEYS */;
INSERT INTO `teams` VALUES (67,'여행모임~',1),(68,'클라이밍 동아리',1),(69,'중학교 동창',1),(71,'가족',19),(72,'하이디라오 모임',33),(73,'영화 리뷰',2);
/*!40000 ALTER TABLE `teams` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `last_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `initial` varchar(100) DEFAULT NULL,
  `color` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'지현','장',NULL,'#949ab1','test','test'),(2,'티모시','샬라메',NULL,'#f3b6b6','TMT','Timothee@gmail.com'),(19,'Seohyun','Jang','sh','#db8a8a','sh','sh'),(20,'Wosuk','Byeon','WS','#6ea7c4','ws','ws'),(25,'민기','이',NULL,'#c1c67b','lmg','lmg'),(26,'Emma','Stone',NULL,'#db8061','es','ES@gmail.com'),(28,'동원','강',NULL,'#8a6fa5','dw','dw@gmail.com'),(29,'우희','천',NULL,'#e8bfce','wh','wh@gmail.com'),(30,'빈','원',NULL,'#316381','wb','wb'),(31,'바오','푸',NULL,'#6366ab','pbo','pbo@gmail.com'),(32,'정아','오',NULL,'#8de28e','ja','oja@gmail.com'),(33,'서연','황',NULL,'#e66060','sy','hsy@gmail.com'),(34,'환효','정',NULL,'#5d60b1','hh','jhh@gmail.com'),(35,'창식','문',NULL,'#628736','cs','mcs');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'test'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-23 21:36:31
