-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: db_website
-- ------------------------------------------------------
-- Server version	8.3.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `quantity` int DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Web',0),(2,'Android',0),(3,'iOS',0),(4,'Backend',0),(5,'Frontend',0),(6,'Machine Learning',0),(7,'Data',0),(8,'Game',0),(9,'Embedded',0),(10,'Network',0),(11,'Computer Science',0),(12,'Software',0),(13,'Security',0),(14,'Robot',0),(15,'Cloud',0),(16,'AI',0),(17,'Nhúng',0),(18,'Bridge',0),(19,'Software Designer',0),(20,'Scrum',0),(21,'BrSE',0),(22,'Tester',0),(23,'Comtor',0);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `languages`
--

DROP TABLE IF EXISTS `languages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `languages` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `quantity` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `languages`
--

LOCK TABLES `languages` WRITE;
/*!40000 ALTER TABLE `languages` DISABLE KEYS */;
INSERT INTO `languages` VALUES (1,'English',0),(2,'Japanese',0),(3,'Chinese',0);
/*!40000 ALTER TABLE `languages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `level`
--

DROP TABLE IF EXISTS `level`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `level` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `quantity` int DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `level`
--

LOCK TABLES `level` WRITE;
/*!40000 ALTER TABLE `level` DISABLE KEYS */;
INSERT INTO `level` VALUES (1,'Intern',0),(2,'Fresher',0),(3,'Senior',0),(4,'Junior',0),(5,'Middle',0),(6,'Associate',0),(7,'Manager',0),(8,'Director',0),(9,'Principal',0),(10,'Lead',0);
/*!40000 ALTER TABLE `level` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `location`
--

DROP TABLE IF EXISTS `location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `location` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `quantity` int DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location`
--

LOCK TABLES `location` WRITE;
/*!40000 ALTER TABLE `location` DISABLE KEYS */;
INSERT INTO `location` VALUES (1,'Hà Nội',0),(2,'Hồ Chí Minh',0),(3,'Đà Nẵng',0),(4,'Hải Phòng',0),(5,'Cần Thơ',0),(6,'Bà Rịa - Vũng Tàu',0),(7,'Bắc Giang',0),(8,'Bắc Kạn',0),(9,'Bạc Liêu',0),(10,'Bắc Ninh',0),(11,'Bến Tre',0),(12,'Bình Định',0),(13,'Bình Dương',0),(14,'Bình Phước',0),(15,'Bình Thuận',0),(16,'Cà Mau',0),(17,'Cao Bằng',0),(18,'Đắk Lắk',0),(19,'Đắk Nông',0),(20,'Điện Biên',0),(21,'Đồng Nai',0);
/*!40000 ALTER TABLE `location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profile`
--

DROP TABLE IF EXISTS `profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `profile` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `date_of_birth` date NOT NULL,
  `img_url` varchar(255) NOT NULL,
  `gender` varchar(10) NOT NULL,
  `address` varchar(255) NOT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Profile_User` (`user_id`),
  CONSTRAINT `FK_Profile_User` FOREIGN KEY (`user_id`) REFERENCES `users` (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profile`
--

LOCK TABLES `profile` WRITE;
/*!40000 ALTER TABLE `profile` DISABLE KEYS */;
/*!40000 ALTER TABLE `profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `programminglanguages`
--

DROP TABLE IF EXISTS `programminglanguages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `programminglanguages` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `quantity` int DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `programminglanguages`
--

LOCK TABLES `programminglanguages` WRITE;
/*!40000 ALTER TABLE `programminglanguages` DISABLE KEYS */;
INSERT INTO `programminglanguages` VALUES (37,'C',0),(38,'C++',0),(39,'Rust',0),(40,'Python',0),(41,'Java',0),(42,'JavaScript',0),(43,'Ruby',0),(44,'Swift',0),(45,'Kotlin',0),(46,'Go',0),(47,'TypeScript',0),(48,'PHP',0),(49,'SQL',0),(50,'MATLAB',0),(51,'R',0),(52,'Perl',0),(53,'Lua',0),(54,'Scala',0),(55,'Haskell',0),(56,'Dart',0),(57,'Objective-C',0),(58,'Assembly language',0),(59,'Visual Basic',0),(60,'Cobol',0),(61,'Fortran',0),(62,'Lisp',0),(63,'Scheme',0),(64,'Prolog',0),(65,'C#',0),(66,'HTML',0),(67,'CSS',0),(68,'Shell',0),(69,'Assembly',0),(70,'.NET',0),(71,'IOS',0);
/*!40000 ALTER TABLE `programminglanguages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recruitment_post`
--

DROP TABLE IF EXISTS `recruitment_post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recruitment_post` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `CompanyName` varchar(255) DEFAULT NULL,
  `idLocation` int DEFAULT NULL,
  `idProgramingLanguages` int DEFAULT NULL,
  `idLanguages` int DEFAULT NULL,
  `idLevel` int DEFAULT NULL,
  `maxSalary` decimal(10,2) DEFAULT NULL,
  `minSalary` decimal(10,2) DEFAULT NULL,
  `phoneNumber` varchar(20) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `content` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci,
  `images` text,
  `experience` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1820000000000035 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recruitment_post`
--

LOCK TABLES `recruitment_post` WRITE;
/*!40000 ALTER TABLE `recruitment_post` DISABLE KEYS */;
INSERT INTO `recruitment_post` VALUES (1780000000000000,NULL,NULL,NULL,NULL,NULL,26000000.00,2000000.00,NULL,'Unknown','Tìm ki?m Middle Fullstack upto 26M\\nM?nh Frontend, t?i thi?u 3 n?m làm Vuejs, Nuxtjs,\\nBackend: Nodejs ho?c ngôn ng? khác','[]',3),(1790000000000000,'HOT JOBS!!! WORKING HYBRID',2,NULL,1,NULL,80000000.00,0.00,'Unknown','Unknown','HOT JOBS!!! WORKING HYBRID\\n- Location: Tan Binh District, HCM City\\n1. Senior Data Scientist (Upto 3K USD Gross)\\n2. Middle/ Senior Scrum Master (Salary Negotiate)\\n3. Technical Architect (Upto 80M VND Gross)\\nYÊU CẦU: NHƯ HÌNH ẢNH DƯỚI BÀI VIẾT\\n- Có từ 1-5 năm kinh nghiệm ở vị trí tương đương (tùy từng vị trí)\\n- Tiếng Anh tốt (công ty sử dụng 100% tiếng Anh trong công việc)\\n_______________________________________________________________________\\nCHI TIẾT VỀ MỨC LƯƠNG, CHẾ ĐỘ ĐÃI NGỘ VÀ CÁC THÔNG TIN KHÁC, ANH/ CHỊ COMMENT HOẶC INBOX ĐỂ EM GỬI JD CHI TIẾT NHÉ.',NULL,NULL),(1790000000000042,'VieCoi',2,NULL,1,NULL,80000000.00,NULL,'0911 3908 27','ntuongvi.viecoi@gmail.com','[Quận 3, HCMC] Tuyển dụng Senior Software Engineer (Java/C#/Python) - tiếng anh tốt\nOffer: ~ 80 mil gross\nThời gian làm việc linh động, chế độ làm việc hybrid\nYêu cầu:\n- Tiếng anh giao tiếp lưu loát (làm việc với team Global)\n- Tối thiểu 7 năm kinh nghiệm Full Stack Developer\n- Kinh nghiệm 5 năm trở lên với Java/ C#/Python, Typescript\n- Kinh nghiệm làm việc với AWS\nBenefits\n- Phụ cấp ngoài lương\n- Quà sinh nhật, quà lễ Tết\n-Môi trường làm việc thoải mái, hybrid khi pass probation\n-Teambuilding hằng năm.\n-BH trên mức full lương\n-Premium healthcare\n-Khám sức khỏe hằng năm.\n-Review lương hằng năm.\n-Lương tháng 13.\n-Bonus hằng năm.\n-----------------------------------------------------\nĐể trao đổi thông tin chi tiết và ứng tuyển, vui lòng gửi mail với tiêu đề: [Tên _ Vị trí ứng tuyển]\ntuongvi.viecoi@gmail.com\nSkype: tuongvi.tv\nZalo: 0911 3908 27 (Ms. Vi)\n',NULL,NULL),(1790000000000043,NULL,NULL,NULL,NULL,NULL,75000000.00,NULL,'Unknown','Unknown','Hi all,\nMình cần tuyển gấp 1 senior C# developer (3 năm kinh nghiệm), 1 senior backend developer (Python) (5 năm kinh nghiệm).\nMức lương từ 2500-3000 USD.\nĐịa điểm làm việc: Hà Nội.\nYêu cầu biết tiếng Anh.\nNếu ai có hứng thú thì comment để mình gửi JD cụ thể nhé!\nMình xin cảm ơn!',NULL,NULL),(1790000000000044,NULL,NULL,NULL,NULL,NULL,70000000.00,NULL,'Unknown','manhglobal76@gmail.com','Hiring Senior Game Developer Remote\nLương: từ 1500- 2800 usd( theo năng lực)\nƯu tiên:\n- Có kinh nghiệm 6 năm làm lập trình game\nTrách nhiệm:\n- Phát triển game với Unity Game Engine\n- Lập trình C#, Lua\n-Git(VCS)\nTrình độ:\n- Sơ yếu lý lịch, Portfolio\n- Tiếng Anh căn bản\n- Tốt nghiệp chuyên ngành máy tính hoặc CNTT\nNhắn tin trực tiếp để biết thêm chi tiết!\nGửi CV ứng tuyển về Mail: manhglobal76@gmail.com',NULL,NULL),(1790000000000045,NULL,NULL,NULL,NULL,NULL,70000000.00,NULL,'Unknown','Unknown','[Hà Nội] List job bên mình đang tuyển: Linux Embedded, Android Embedded, System,...\nInbox mình gửi JD chi tiết nha!\n1.Linux Embedded System Engineer\nTừ 5 năm Linux, C++, tiếng Anh giao tiếp tốt\nMức lương up to gross 70M, môi trường ổn định, dự án lớn\n2. Android Embedded Engineer\nTừ 5 năm Android, kinh nghiệm thiết bị network, tiếng Anh giao tiếp tốt\nMức lương up to gross 65M, môi trường senior, global\n3. System Engineer\nTừ 2 năm làm system, IT support,... tiếng Anh giao tiếp tốt\nMức lương up to gross 35M, giờ làm việc linh hoạt, cơ hội onsite',NULL,NULL),(1800000000000000,NULL,NULL,NULL,NULL,NULL,65000000.00,6000000.00,NULL,'Unknown','[Hà N?i] G?i CV ra t?t ph?ng v?n nha!!!\\n1.C++ Developer\\nQuy?n l?i: M?c l??ng up to gross 65M, môi tr??ng ti?ng Anh, product l?n\\nYêu c?u: t? 2 n?m kinh nghi?m C++, kinh nghi?m làm network, ti?ng Anh giao ti?p t?t\\n2. Java/ Node.js Developer\\nQuy?n l?i: m?c l??ng up to gross 40M, môi tr??ng global, d? án n??c ngoài\\nYêu c?u: t? 3 n?m kinh nghi?m t? khi t?t nghi?p ??i h?c, ti?ng Anh giao ti?p\\n3. Java Fullstack Developer (Junior level)\\nQuy?n l?i: m?c l??ng gross up to 50M, môi tr??ng châu Âu, c? h?i onsite\\nYêu c?u: t? 1 n?m kinh nghi?m Java, kinh nghi?m React.js, ti?ng Anh t?t, thành tích h?c t?p t?t\\n4. Junior Frontend Developer\\nQuy?n l?i: m?c l??ng gross up to 50M, môi tr??ng châu Âu, c? h?i onsite\\nYêu c?u: t? 1 n?m kinh nghi?m React.js ho?c Vue.js, ti?ng Anh t?t, thành tích h?c t?p t?t','[]',2),(1810000000000000,NULL,NULL,NULL,NULL,NULL,70000000.00,7000000.00,NULL,'Unknown','[Hà N?i] List job bên mình ?ang tuy?n: Linux Embedded, Android Embedded, System,...\\nInbox mình g?i JD chi ti?t nha!\\n1.Linux Embedded System Engineer\\nT? 5 n?m Linux, C++, ti?ng Anh giao ti?p t?t\\nM?c l??ng up to gross 70M, môi tr??ng ?n ??nh, d? án l?n\\n2. Android Embedded Engineer\\nT? 5 n?m Android, kinh nghi?m thi?t b? network, ti?ng Anh giao ti?p t?t\\nM?c l??ng up to gross 65M, môi tr??ng senior, global\\n3. System Engineer\\nT? 2 n?m làm system, IT support,... ti?ng Anh giao ti?p t?t\\nM?c l??ng up to gross 35M, gi? làm vi?c linh ho?t, c? h?i onsite','[]',5),(1820000000000000,NULL,NULL,NULL,NULL,NULL,70000000.00,37500000.00,NULL,'manhglobal76@gmail.com','Hiring Senior Game Developer Remote\\nL??ng: t? 1500- 2800 usd( theo n?ng l?c)\\n?u tiên:\\n- Có kinh nghi?m 6 n?m làm l?p trình game\\nTrách nhi?m:\\n- Phát tri?n game v?i Unity Game Engine\\n- L?p trình C#, Lua\\n-Git(VCS)\\nTrình ??:\\n- S? y?u lý l?ch, Portfolio\\n- Ti?ng Anh c?n b?n\\n- T?t nghi?p chuyên ngành máy tính ho?c CNTT\\nNh?n tin tr?c ti?p ?? bi?t thêm chi ti?t!\\nG?i CV ?ng tuy?n v? Mail: manhglobal76@gmail.com','[]',6),(1820000000000001,'FPT SYNEX',NULL,NULL,NULL,NULL,25000000.00,23000000.00,'Unknown','Unknown','[HN] FPT SYNNEX TUYỂN SENIOR .NET DEVELOPER\nYêu Cầu Công Việc:\nMin 4 năm với .NET và .NET Core.\nMin 3 năm kinh nghiệm với Microservices architecture.\nCó kinh nghiệm làm việc với ReactJS là một lợi thế\nLợi Ích:\nMôi trường làm việc chuyên nghiệp và năng động.\nReview lương hàng tháng, lương tháng 13 + thưởng\nLương: 23 - 25M Gross\nĐóng bảo hiểm theo luật LĐ\nLàm việc từ t2 - t6\nCó cơ hội phát triển nghề nghiệp và học hỏi từ các chuyên gia hàng đầu trong ngành.\nib nhận JD chi tiết ạ','[]',4),(1820000000000002,'null',NULL,NULL,NULL,NULL,23000000.00,15000000.00,'Unknown','Unknown','#JDtháng3\nTiếp tục tuyển #remote 100%\nEm cần tìm cho 3 dự án thị trường Úc, Sing, Nhật:\n+ Junior Angular\n+ Fullstack( Nodej, Vuejs )\n+ PHP ( Laravel, Symfony ): có PHP cake là 1 lợi thế\nYêu cầu tiếng anh thành thạo.\n=> Lương: Angular 15-23M, Fullstack + PHP 30-48M\nKinh nghiệm > 1 năm ( ko tuyển thực tập sinh ạ )','[]',1),(1820000000000003,'null',NULL,NULL,NULL,NULL,20000000.00,2000000.00,'Unknown','Unknown','[Hanoi] Tuyển dụng 5 vị trí Senior .NET (VB/ WPF) + ko yêu cầu tiếng Anh, thưởng nóng từ 20tr-40tr. Yêu cầu từ 5 năm kn trở lên. Offer up #750triệu/ năm. Ib mình nhận JD chi tiết nhé cả nhà','[]',5),(1820000000000004,'',NULL,NULL,NULL,NULL,60000000.00,6000000.00,'Unknown','Unknown','[ HOT JOBS - HOT HOT ]\nDear cả nhà,\nEm đang tìm RẤT GẤP ứng viên cho các vị trí HẤP DẪN sau:\n1. BackEnd Golang Developer, công ty IT, làm tại Hà Nội, ít nhất 5 năm kinh nghiệm software trong đó ít nhất 2 năm kinh nghiệm Golang, tiếng Anh giao tiếp, lương lên đến 3500 USD\n2. FrontEnd Developer, công ty IT, làm tại Hà Nội, yêu cầu ít nhất 4 năm kinh nghiệm ReactJS hoặc AngularJS, tiếng Anh giao tiếp, lương lên đến 2500 USD\n3. Automation and Manual Tester, ít nhất 2 năm kinh nghiệm, tiếng Pháp giao tiếp tốt, lương lên đến 3000 USD\n4. Technical Project/ Product Manager, công ty IT, làm tại Hà Nội, ít nhất 5 năm kinh nghiệm, tiếng Pháp giao tiếp tốt, lương lên đến 6000 USD\n5. Business Analyst, công ty IT, làm tại Hà Nội, ít nhất 3 năm kinh nghiệm, tiếng Pháp giao tiếp tốt, lương lên đến 60 Mil\n6. Senior AI Engineer, công ty IT, làm tại Hà Nội, ít nhất 5 năm kinh nghiệm, tiếng Pháp giao tiếp tốt, lương lên đến 8000 USD\n7. FrontEnd ReactJS Developer, công ty IT, làm tại HCM, ít nhất 5 năm kinh nghiệm ReactJS, tiếng Anh đọc hiểu, lương lên đến 70 Mil Gross\n8. BackEnd NodeJS Developer, công ty IT, làm tại HCM, ít nhất 4 năm kinh nghiệm, tiếng Anh đọc hiểu, lương lên đến 60 Mil Gross\n9. Fullstack Developer, công ty IT, làm tại HCM, ít nhất 5 năm kinh nghiệm NodeJS + ReactJS, tiếng Anh giao tiếp, lương lên đến 40 Mil Gross\n10. Fullstack NodeJS Software Manager, công ty IT, làm tại HCM, ít nhất 10 năm kinh nghiệm Software trong đó ít nhất 5 năm kinh nghiệm Fullstack NodeJS + kinh nghiệm lead team, tiếng Anh giao tiếp, lương lên đến 6000 USD\n11. DevOps Engineer, công ty IT, làm tại HCM, ít nhất 5 năm kinh nghiệm, tiếng Anh tốt, lương lên đến 2500 USD\n12. Business Analyst, công ty IT, làm tại HCM, ít nhất 5 năm kinh nghiệm, tiếng Anh giao tiếp, lương lên đến 55 Mil\nCả nhà hãy INBOX em để APPLY nhé $$$$\nEm cám ơn cả nhà.\n#fullstacknodejsdeveloper #nodejsdeveloper #nodejs #reactjs #frontenddeveloper #ReactJSDeveloper #HeadofEngineering #TechLead #businessanalyst','[]',5),(1820000000000005,'Chứng khoán DSC',NULL,NULL,NULL,NULL,35000000.00,3000000.00,'Unknown','Unknown','[Hà Nội] Chứng khoán DSC tuyển dụng các vị trí sau:\n- Quản trị hệ thống/mạng (Junior/Senior)\n- IT Helpdesk\nMức lương: upto 35M\nYêu cầu:\n- Tốt nghiệp Đại học trở lên chuyên ngành CNTT, Điện tử viễn thông...\n- Có các chứng chỉ CCNA, CCNP, MSCA (Ưu tiên)\n- Tối thiểu 2-3 năm kinh nghiệm trong lĩnh vực vận hành, quản trị hệ thống/mạng\nCác bạn inbox mình để gửi CV nhé.\n#chungkhoandsc #dscsecurities #dsc #systemadmin #networkadmin #ithelpdesk','[]',3),(1820000000000006,'null',NULL,NULL,NULL,NULL,30000000.00,3000000.00,'Unknown','Unknown','[Hanoi] Tuyển gấp 3 vị trí Senior #JAVA (fullstack), tặng ngay bonus từ 30tr-60tr, join dự án chuyển đổi số siêu lớn của Úc nYêu cầu từ 4 năm kn trở lên + good Eng. Offer up #900tr/ năm kèm bonus. Ib mình nhận JD chi tiết nhé cả nhà','[]',4),(1820000000000007,'null',NULL,NULL,NULL,NULL,40000000.00,4000000.00,'Unknown','Unknown','HCM - cty outsource TOP của Nhật cần tuyển:\n- Middle/Senior Fullstack NodeJS\n- Tối thiểu 3-4 năm với NodeJS (bắt buộc)\n- Có exp về ReactJS, biết AWS là điểm cộng lớn\n- Lương upto 40tr tùy exp\n- 1 round interview ONLY\n- Ko yêu cầu tiếng Anh.\n----------\nUV quan tâm có thể inbox mình để trao đổi thêm nhé.','[]',4),(1820000000000008,'null',NULL,NULL,NULL,NULL,43000000.00,4000000.00,'Unknown','Unknown','Cần gấp Senior Nodejs Dev từ 5 năm kn, có thể onsite 3 tháng ở Cầu Giấy, Hà Nội. Rate up to 43tr NET. Inbox trao đổi chi tiết','[]',5),(1820000000000009,'null',NULL,NULL,NULL,NULL,37000000.00,3000000.00,'Unknown','Unknown','Tuyển gấp Tester ở HCM chỉ từ 1-3 năm KN với mức LƯƠNG up to 37M. Có open cho Fresher, ib nhận ngay JD nhé!','[]',3),(1820000000000010,'VIVAS',NULL,NULL,NULL,NULL,30000000.00,3000000.00,'Unknown','career@topdev.vn','[HN] Chỉ cần có kinh nghiệm với Flutter, quá dễ để Kỹ sư Lập trình Mobile App từ Fresher trở lên có thể chốt ngay cơ hội việc làm xịn sò từ nhà VIVAS.\n️ Link JD: https://topdev.vn/s/wMZ0VdV7\n Yêu cầu:\nKN  từ 1 năm làm việc với Flutter.\nĐãi ngộ:\nMức lương thỏa thuận lên đến 30 Mil + Xét tăng lưương theo quy định.\nMột năm có thể đạt 15 đến 20 tháng lương.\nĐược đóng BHXH, BHYT, BHTN +  hưởng đầy đủ các quyền lợi theo Luật lao động Việt Nam.\nDu lịch hàng năm.\nThời gian làm việc: Từ thứ 2 đến thứ 6.\n===============================\nNhanh tay gửi ngay CV về mail career@topdev.vn kèm tiêu đề:\n[2032837 - VIVAS - Mobile].','[]',1),(1820000000000011,'null',NULL,NULL,NULL,NULL,50000000.00,35000000.00,'Unknown','annhiennt.hr@gmail.com','Nam Từ Liêm |HN| tìm : IT MANAGER ( .NET)\n⭐️Lương : 35- 50M net\n⭐️Yêu cầu:\n- từ 7 năm kn trong phát triển các hệ thống phần mềm sử dụng n nền tảng .NET (Winforms, ASP.NET MVC, .NET Framework, .NET Core)\n- 2Y+ kn ở vị trí quản lý đội ngũ kỹ thuật hoặc tương đương\n- 3Y+ năm kinh nghiệm ở vị trí quản lý dự án, có hiểu biết về các quy trình phát triển dự án phần mềm (Predictive, Agile, Hybrid) là một lợi thế\n- Có hiểu biết sâu sắc về lập trình OOP, SOLID\n- Có kinh nghiệm thiết kế CSDL quan hệ trên các hệ quản trị như Oracle, MSSQL Server\n___________________________________________________\n---> contacts : \n\nEmail : annhiennt.hr@gmail.com\nSkype : live:.cid.9cac78b3689a70ff.','[]',7),(1820000000000012,'ARBIN INSTRUCMENT VIETNAM',NULL,NULL,NULL,NULL,5000000.00,0.00,'Unknown','hr.vietnam@arbin.com','[DI AN BINH DUONG sát THU DUC, HCMC] \nCTY ARBIN INSTRUMENTS VIET NAM\ncần tuyển các vị trí sau: \n⚡️SALARY $1000 - $2000⚡️\n1 Middle Front-End   Developer (React, Angular) \n1 Senior Software Testing Engineer (C#, MySQL, QA) \n1 Senior Java Backend Developer (J2EE, SQL, Linux) \n1 Senior C# Back-End Developer (C#, SQL , asp.net) \n1 Senior Full-stack Developer (J2EE, Java, Angular) \n\nYêu cầu kinh nghiệm từ 3 năm, tốt nghiệp Đại học, tiếng Anh khá giỏi. Xin vui lòng gửi CV về : hr.vietnam@arbin.com','[]',3),(1820000000000013,'null',NULL,NULL,NULL,NULL,40000000.00,4000000.00,'Unknown','career@topdev.vn','[TP.HCM] Mức lương cực đã lên tới 40 triệu với vị trí [Senior] PHP Developer tại Click Digisol, các bạn nhanh tay ứng tuyển nhé!\nTiêu chí:\nTối thiểu 3 năm kinh nghiệm. (Ưu tiên các bạn có kinh nghiệm Outsourcing).\nCó kinh nghiệm về PHP, HTML, Wordpress, Laravel, Restful Api.\nó kinh nghiệm về Vuejs, Reactjs là một điểm cộng.\nĐãi ngộ:\nMức lương rất hấp dẫn (UPTO 40 triệu/tháng) tương xứng với kinh nghiệm làm việc.\nCung cấp thiết bị phục vụ công việc.\nCó cơ hội phát triển sự nghiệp bằng cách tham gia các khóa học Online và offline\nBảo hiểm sức khỏe cao cấp và quà tặng dịp lễ tết\nCompany trip, các hoạt động Team Building và tiệc tùng.\nLương T13, thưởng Lễ Tết, Sinh nhật…\nĐừng ngần ngại cho ib mình hoặc ứng tuyển nhanh tại đường link bên dưới nhé!\nLink JD & APPLY NHANH: https://topdev.vn/s/67uh9fXT\nGửi CV về: career@topdev.vn\nVới tiêu đề: Click Digisol - PHP Developer','[]',3),(1820000000000014,'OCG Technology',NULL,NULL,NULL,NULL,30000000.00,3000000.00,'796037216','truclinh@ocg.vn',' [HN] OCG TECHNOLOGY CẦN TÌM KIẾM ĐỒNG ĐỘI\n QA - MIDDLE/SENIOR (UPTO 30M)\nÍt nhất 2 năm kinh nghiệm QA trong lĩnh vực IT phần mềm\nNắm vững kiến thức về các phương pháp, công cụ và quy trình QA phần mềm\nCó kiến thức về SQL và script là một lợi thế\nTiếng Anh đọc hiểu tài liệu và giao tiếp cơ bản\nBiết tiếng Nhật là một điểm cộng\n Ưu tiên ứng viên đã có kinh nghiệm làm việc với các dự án outsource\nQuan tâm vui lòng inb mình hoặc gửi CV qua:\nEmail: truclinh@ocg.vn\nSMS/ Zalo: 0796037216','[]',2),(1820000000000015,'null',NULL,NULL,NULL,NULL,70000000.00,7000000.00,'Unknown','Unknown','[Hà Nội] List job bên mình đang tuyển: Linux Embedded, Android Embedded, System,...\nInbox mình gửi JD chi tiết nha!\n1.Linux Embedded System Engineer\nTừ 5 năm Linux, C++, tiếng Anh giao tiếp tốt\nMức lương up to gross 70M, môi trường ổn định, dự án lớn\n2. Android Embedded Engineer\nTừ 5 năm Android, kinh nghiệm thiết bị network, tiếng Anh giao tiếp tốt\nMức lương up to gross 65M, môi trường senior, global\n3. System Engineer\nTừ 2 năm làm system, IT support,... tiếng Anh giao tiếp tốt\nMức lương up to gross 35M, giờ làm việc linh hoạt, cơ hội onsite','[]',5),(1820000000000016,'null',NULL,NULL,NULL,NULL,30000000.00,3000000.00,'Unknown','Unknown','Hoà Lạc tuyển dụng #Senior #Nodejs #Reactjs từ 5 năm kinh nghiệm trở lên. No ENG join dự án ạ. Offer upto 2K5 + bonus 30M. Ib mình gửi thông tin cụ thể nha','[]',5),(1820000000000017,'null',NULL,NULL,NULL,NULL,60000000.00,6000000.00,'Unknown','Unknown','[ HOT JOBS - HOT HOT ]\nDear cả nhà,\nEm đang tìm RẤT GẤP ứng viên cho các vị trí HẤP DẪN sau:\n1. BackEnd Golang Developer, công ty IT, làm tại Hà Nội, ít nhất 5 năm kinh nghiệm software trong đó ít nhất 2 năm kinh nghiệm Golang, tiếng Anh giao tiếp, lương lên đến 3500 USD\n2. FrontEnd Developer, công ty IT, làm tại Hà Nội, yêu cầu ít nhất 4 năm kinh nghiệm ReactJS hoặc AngularJS, tiếng Anh giao tiếp, lương lên đến 2500 USD\n3. Automation and Manual Tester, ít nhất 2 năm kinh nghiệm, tiếng Pháp giao tiếp tốt, lương lên đến 3000 USD\n4. Technical Project/ Product Manager, công ty IT, làm tại Hà Nội, ít nhất 5 năm kinh nghiệm, tiếng Pháp giao tiếp tốt, lương lên đến 6000 USD\n5. Business Analyst, công ty IT, làm tại Hà Nội, ít nhất 3 năm kinh nghiệm, tiếng Pháp giao tiếp tốt, lương lên đến 60 Mil\n6. Senior AI Engineer, công ty IT, làm tại Hà Nội, ít nhất 5 năm kinh nghiệm, tiếng Pháp giao tiếp tốt, lương lên đến 8000 USD\n7. FrontEnd ReactJS Developer, công ty IT, làm tại HCM, ít nhất 5 năm kinh nghiệm ReactJS, tiếng Anh đọc hiểu, lương lên đến 65 Mil Gross\n8. BackEnd NodeJS Developer, công ty IT, làm tại HCM, ít nhất 4 năm kinh nghiệm, tiếng Anh đọc hiểu, lương lên đến 60 Mil Gross\n9. Fullstack Developer, công ty IT, làm tại HCM, ít nhất 5 năm kinh nghiệm NodeJS + ReactJS, tiếng Anh giao tiếp, lương lên đến 40 Mil Gross\n10. Fullstack NodeJS Software Manager, công ty IT, làm tại HCM, ít nhất 10 năm kinh nghiệm Software trong đó ít nhất 5 năm kinh nghiệm Fullstack NodeJS + kinh nghiệm lead team, tiếng Anh giao tiếp, lương lên đến 6000 USD\n11. DevOps Engineer, công ty IT, làm tại HCM, ít nhất 5 năm kinh nghiệm, tiếng Anh tốt, lương lên đến 2500 USD\n12. Business Analyst, công ty IT, làm tại HCM, ít nhất 5 năm kinh nghiệm, tiếng Anh giao tiếp, lương lên đến 55 Mil\nCả nhà hãy INBOX em để APPLY nhé $$$$\nEm cám ơn cả nhà.\n#fullstacknodejsdeveloper #nodejsdeveloper #nodejs #reactjs #frontenddeveloper #ReactJSDeveloper #HeadofEngineering #TechLead #businessanalyst','[]',5),(1820000000000018,'null',NULL,NULL,NULL,NULL,25000000.00,2000000.00,'Unknown','Unknown','[Hanoi] Tuyển dụng gấp 2 vị trí Middle/ Senior #NodeJS từ 3 năm kn trở lên, join dự án Smart tech tại Fsoft. Offer up 25tr-45tr kèm bonus. Ib Cindy nhận JD chi tiết nhé cả nhà','[]',3),(1820000000000019,'null',NULL,NULL,NULL,NULL,16000000.00,1000000.00,'0902.591.062','tuyendung3.worklink@gmail.com','TỔNG HỢP JOB IT KHU VỰC ĐỒNG NAI\n-----------------------------------------\n Mong được hỗ trợ và kết nối với các Anh/Chị ứng viên có nhu cầu tìm việc !\nGửi CV theo cú pháp: [Vị trí ứng tuyển] - [Họ và tên]\n Email: tuyendung3.worklink@gmail.com\n Phone/Zalo: 0902.591.062 (Ms.Vy)\n-----------------------------------------\n1. Nhân viên IT Software: 15 – 16 triệu\nĐịa điểm: KCN Long Đức – Đồng Nai\n- Nam/ Nữ, 21 – 28 tuổi, tốt nghiệp CĐ/ ĐH\n- 1 nămm kinh nghiệm phát triển phần mềm thực tế\n- Có kiến thức C# vững chắc\n- Thành thạo HTML, CSS, JavaScript\n- Ưu tiên có kiến thức về Ruby, WebAI, xamarin\n----------\n☘️2. Nhân viên IT lập trình (senior): 17 - 20 triệu + PC\nĐịa điểm: KCN Long Đức – Đồng Nai\nCó xe đưa rước từ Từ Hàng Xanh, Ngã tư Thủ Đức, Biên HHòa và Bà Rịa – Vũng Tàu\n- Nam, 28 – 40 tuổi, tốt nghiệp Đại học\n- 3 – 5 năm kinh nghiệm vị trí IT lập trình\n- Tiếng Anh giao tiếp tốt\n- Có kiến thức về các framework Front-End và Back-End\n----------\n3. IT Manager: Thỏa thuận\nĐịa điểm: KCN Amata – Đồng Nai\nCó xe đưa đón từ Hồ Chí Minh\n- Nam/ Nữ, tốt nghhiệp Đại học trở lên\n- Tiếng Anh hoặc tiếng Nhật giao tiếp\n- 10 năm kinh nghiệm trong lĩnh vực IT\n- Trên 2 năm kinh nghiệm tại vị trí IT Manager\n- Có kinh nghiệm phát triển, vận hành và quản lý hệ thống CNTT; Quản lý mạng, windows và máy chủ','[]',1),(1820000000000020,'null',NULL,NULL,NULL,NULL,35000000.00,20000000.00,'Unknown','tuyettta@omigroup.vn','Bình Thạnh HCM - Tuyển Ruby On Rails developer (Middle trở lên) CÓ THỂ với thông tin job như sau:\nThu nhập: 20-35M\nMÔ TẢ CÔNG VIỆC\nPhát triển ứng dụng Web sử dụng Ruby on Rails.\nPhân tích yêu cầu hệ thống/đặc điểm kĩ thuật.\nHỗ trợ các thành viên khác để giải quyết các vấn đề kĩ thuật.\nChỉnh sửa và bảo trì hệ thống.\nViết tài liệu kĩ thuật.\nYÊU CẦU CÔNG VIỆC\nCó kinh nghiệm làm việc với Ruby on Rails và các thư viện phổ biến như RSpec, các gem như Devise, sidekiq …\nCó kiến thức về OOP, nắm rõ các khái niệm MVC, ORM, RESTful.\nBiết TDD và CI là một lợi thế.\nSử dụng tốt các database như MySQL, MongoDB, Redis.\nBiết sử dung các công cụ hỗ trợ như GIT, redmine, các hệ điều hành UNIX là một lợi thế.\nBiết deployment là một lợi thế.\nKinh nghiệm: Có ít nhất 3 năm kinh  nghiệm ở vị trí tương đương.\n==========\nAnh chị bạn nào qua tâm đến job có thể ứng tuyển hoặc liên hệ qua mail em nhé: tuyettta@omigroup.vn','[]',3),(1820000000000021,'null',NULL,NULL,NULL,NULL,60000000.00,6000000.00,'Unknown','Unknown','[Hanoi] Tuyển gấp 3 vị trí Senior #Java (fullstack), tặng ngay bonus từ 30-60tr, join dự án chuyển đổi số siêu lớn của Úc \nYêu cầu từ 4 năm kn trở lên + tiếng Anh giao tiếp tốt. Offer 28tr-58tr kèm bonus. Ib mình nhận JD chi tiết nhé cả nhà','[]',4),(1820000000000022,'null',NULL,NULL,NULL,NULL,50000000.00,5000000.00,'944562538','Unknown','[Hà Nội] IT Project Manager cho công ty Nhật\nUp to 50M gross\nCó khoảng 3 năm kinh nghiệm ở vị trí Engineer/ Architecth/ Programmar\nCó kinh nghiệm quản lý team\nCó kinh nghiệm quản lý dự án\nTiếng Nhật 4 kĩ năng tốt\nA/c/e liên hệ 0944562538 (Trang) hoặc inbox!','[]',3),(1820000000000023,'null',NULL,NULL,NULL,NULL,65000000.00,6000000.00,'Unknown','Unknown','[ HOT JOBS - HOT HOT ]\nDear cả nhà,\nEm đang tìm RẤT GẤP ứng viên cho các vị trí HẤP DẪN sau:\n1. BackEnd Golang Developer, công ty IT, làm tại Hà Nội, ít nhất 4 năm kinh nghiệm, tiếng Anh giao tiếp, lương lên đến 3500 USD\n2. FrontEnd Developer, công ty IT, làm tại Hà Nội, yêu cầu ít nhất 4 năm kinh nghiệm ReactJS hoặc AngularJS, tiếng Anh giao tiếp, lương lên đến 2500 USD\n3. Automation and Manual Tester, ít nhất 2 năm kinh nghiệm, tiếng Pháp giao tiếp tốt, lương lên đến 3000 USD\n4. Technical Project/ Product Manager, làm tại Hà Nội, ít nhất 5 năm kinh nghiệm, tiếng Pháp giao tiếp tốt, lương lên đến 6000 USD\n5. FrontEnd ReactJS Developer, công ty IT, làm tại HCM, ít nhất 5 năm kinh nghiệm ReactJS, tiếng Anh đọc hiểu, lương lên đến 65 Mil Gross\n6. BackEnd NodeJS Developer, công ty IT, làm tại HCM, ít nhất 4 năm kinh nghiệm, tiếng Anh đọc hiểu, lương lên đến 60 Mil Gross\n7. Fullstack Developer, công ty IT, làm tại HCM, ít nhất 5 năm kinh nghiệm NodeJS + ReactJS, tiếng Anh giao tiếp, lương lên đến 40 Mil Gross\n8. Fullstack NodeJS Software Manager, công ty IT, làm tại HCM, ít nhất 10 năm kinh nghiệm Software trong đó ít nhất 5 năm kinh nghiệm Fullstack NodeJS + kinh nghiệm lead team, tiếng Anh giao tiếp, lương lên đến 6000 USD\nCả nhà hãy INBOX em để APPLY nhé $$$$\nEm cám ơn cả nhà.\n#fullstacknodejsdeveloper #nodejsdeveloper #nodejs #reactjs #frontenddeveloper #ReactJSDeveloper #HeadofEngineering #TechLead','[]',4),(1820000000000024,'null',NULL,NULL,NULL,NULL,40000000.00,30000000.00,'0934 653 259','globalitrecruitment513@gmail.com','{Remote, Freelancer} Bên Mình đang tuyển\n1. 01 Tech lead (Reactjs/Ruby)\nLương: 30-40M\nY/c: - >3 năm kinh nghiệm với React.js, hiểu biết sâu về React.js và core principles của nó\n- Ít nhất 1 năm kinh nghiệm tại vị trí Techlead\n- Có kinh nghiệm với React.js workflows\n- Tiếng Anh giao tiếp tốt\n2. 01 Ruby Developer\nLương: 30- 40M\nY/c:\n- 3 năm kinh nghiệm với Ruby on Rails\n- Kinh nghiệm với REST API, JSONAPI\n- Kinh nghiệm với database systems (PostgreSQL)\n- Tiếng Anh giao tiếp tốt\nIb: 0934 653 259\nEmail: globalitrecruitment513@gmail.com\nThanks.','[]',3),(1820000000000025,'null',NULL,NULL,NULL,NULL,15000000.00,1000000.00,'Unknown','info@ztech.asia','[TUYỂN DỤNG ] VỊ TRÍ:  FRESHER REACT NATIVE\nCÔNG TY:\nLương: Min 9 triệu\nMô tả công việc\nTham gia vào các dự án về phát triển phần mềm của công ty\nPhối hợp với các thành viên trong team nghiên cứu, phát triển những tính năng của app\nTạo giao diện người dùng đáp ứng để truy vấn dữ liệu một cách hiệu quả và tối ưu.\nChi tiết sẽ trao đổi cụ thể khi phỏng vấn\nYêu cầu ứng viên\nƯu tiên ứng viên đã có ít nhất 6 tháng đến 1 năm kinh nghiệm React Native\nCó kinh nghiệm lập trình native: Android IOS\nCó kinh nghiệm build và public ứng dụng lên play-store, app-store;\nNắm vững kiến thức về JavaScript, RESTful API.\nCó kinh nghiệm làm việc với Google Cloud Service, Firebase Service ...\nHiểu biết về một số quy trình phát triển phần mềm như: scrum, agile ...\nCó khả năng học hỏi nhanh, tích cực và chủ động trong công việc. Khả năng làm việc độc lập và làm việc nhóm tốt\nQuyền lợi\na. Thu nhập\nTrao đổi deal thỏa đáng theo năng lực upto lên đến 15 triệu\nThưởng nóng, thưởng mỗi dự án.\nXét tăng lương tùy vào năng lực.\nb. Bảo hiểm và các chính sách đãi ngộ\nBảo hiểm theo chế độ nhà nước ban hành.\nChế độ sinh nhật, hiếu, hỉ.\nc. Môi trường làm việc\nLàm việc trong tập thể toàn những người trẻ, năng động, cá tính; máu lửa và nhiệt huyết trong công việc\nMôi trường làm việc mở, khuyến khích tối đa sự sáng tạo của nhân viên.\nd. Thời gian làm việc\nLàm việc từ thứ 2 - thứ 6, thứ 7 cách tuần.\nThời gian linh hoạt  8 tiếng / ngày\n=========================\n[Liên hệ gửi CV]\nIb trực tiếp\nEmail: info@ztech.asia\nĐịa chỉ: 35 Đường Số 3, KDC Cityland, Phường 10, Gò Vấp, Thành phố Hồ Chí Minh','[]',1),(1820000000000026,'null',NULL,NULL,NULL,NULL,12000000.00,1000000.00,'Unknown','Unknown','[Tôn Quang Phiệt, Bắc Từ Liêm, Hà Nội]\nCần gấp 01 Nhân viên .Net Developer (C#,Junior/Middle)\nCông ty Cổ phần CyberLotus\nĐịa chỉ: Tòa 6-08 Tôn Quang Phiệt, Bắc Từ Liêm\nThời gian làm việc T2-T6 (8h00-17h30)\nQuyền lợi\n- Lương: 10-12triệu /tháng.\n- Được hưởng phụ cấp ăn trưa (40.000/ngày), teambuilding ,...\n- Lương tháng 13, thưởng cuối năm…\nYêu cầu ứng viên\n- Có trên 01 năm kinh nghiệm làm dự án .NET\n- Hiểu biết về .NET Core, .NET Frameworks, .NET MVC, OOP, Design Pattern; có kiến thức về web service (RESTful); kinh nghiệm làm việc với API..\nIb để nhận JD chi tiết','[]',1),(1820000000000027,'null',NULL,NULL,NULL,NULL,60000000.00,6000000.00,'Unknown','Unknown','[ HOT JOBS]\nDear cả nhà,\nEm đang tìm RẤT GẤP ứng viên cho các vị trí HẤP DẪN sau:\n1. FrontEnd Developer, công ty IT, làm tại Hà Nội, yêu cầu ít nhất 4 năm kinh nghiệm ReactJS hoặc AngularJS, tiếng Anh giao tiếp, lương lên đến 2500 USD\n2. BackEnd NodeJS Developer, công ty IT, làm tại HCM, ít nhất 4 năm kinh nghiệm BackEnd Nodejs, tiếng Anh đọc hiểu, lương lên đến 60 Mil Gross\n3. FrontEnd ReactJS Developer, công ty IT, làm tại HCM, ít nhất 5 năm kinh nghiệm ReactJS, tiếng Anh đọc hiểu, lương lên đến 65 Mil Gross\n4. Fullstack NodeJS Software Manager, công ty IT, làm tại HCM, ít nhất 10 năm kinh nghiệm Software trong đó ít nhất 5 năm kinh nghiệm Fullstack NodeJS + kinh nghiệm lead team, tiếng Anh giao tiếp, lương lên đến 6000 USD\nCả nhà hãy INBOX em để APPLY nhé $$$$\nEm cám ơn cả nhà.\n#fullstacknodejsdeveloper #nodejsdeveloper #nodejs #reactjs #frontenddeveloper #ReactJSDeveloper #HeadofEngineering #TechLead','[]',4),(1820000000000028,'null',NULL,NULL,NULL,NULL,17000000.00,1000000.00,'Unknown','Unknown','[Thủ Đức-HCM] chiêu mộ #Junior/ #Middle PHP Dev (CodeIgniter, Laravel). Offer 17tr-25tr, YC: >2 năm KN. PV 2 vòng. Inbox nhận JD.','[]',2),(1820000000000029,'null',NULL,NULL,NULL,NULL,25000000.00,15000000.00,'Unknown','thuyntp@runsystem.net','[HN - Đống Đa] TUYỂN GẤP 4 JAVA JUNIOR\nTừ 2 năm kinh nghiệm làm dự án thực tế với Java\nĐã có kinh nghiệm làm dự án Nhật\nCó kinh nghiệm unit test và viết detail design là lợi thế\nOFFER 15 - 25M; 13 tháng lương; thưởng dự án 6 tháng/lần\nBảo hiểm 50% lương; nghỉ & thưởng Lễ Tết, du lịch cty; teambuilding..\nGửi CV về email: thuyntp@runsystem.net\n---------------------------------------------------\nGMO-Z.com RUNSYSTEM (quy mô > 900 nhân sự, hơn 18 năm phát triển\nĐịa chỉ: Tòa Ocean Park, số 1 Đào Duy Anh, Đống Đa, HN\nThời gian làm việc: Thứ 2 - Thứ 6 (8h-12h; 13h30 - 17h30); check-in linh hoạt ','[]',2),(1820000000000030,'null',NULL,NULL,NULL,NULL,40000000.00,4000000.00,'Unknown','Unknown','TUYỂN GẤP MIDDLE/SENIOR BACK-END DEVELOPER\nLàm việc tại Hà Nội \nTối thiểu 4 năm kinh nghiệm\nLương lên đến 40tr/tháng\nIb mình gửi JD nhé','[]',4),(1820000000000031,'null',NULL,NULL,NULL,NULL,51000000.00,5000000.00,'Unknown','Unknown','[Thủ Đức, HCM] Công ty của Pháp (50 năm) làm dự án chuyên về Bảo hiểm đang tìm Java Developer - thành thạo tiếng Pháp\nRange up to 51tr\nBHXH Full lương\nLevel: From Junior\nGood knowledge of J2EE architecture, JAVA, HTML, database, Eclipse.\n-----------------------------------------------------------\nMọi người quan tâm inbox hoặc liên hệ\nSkype: live:.cid.33d520fc3158edac','[]',50),(1820000000000032,'null',NULL,NULL,NULL,NULL,65000000.00,6000000.00,'Unknown','Unknown','[Hà Nội] Gửi CV ra tết phỏng vấn nha!!!\n1.C++ Developer\nQuyền lợi: Mức lương up to gross 65M, môi trường tiếng Anh, product lớn\nYêu cầu: từ 2 năm kinh nghiệm C++, kinh nghiệm làm network, tiếng Anh giao tiếp tốt\n2. Java/ Node.js Developer\nQuyền lợi: mức lương up to gross 40M, môi trường global, dự án nước ngoài\nYêu cầu: từ 3 năm kinh nghiệm từ khi tốt nghiệp đại học, tiếng Anh giao tiếp\n3. Java Fullstack Developer (Junior level)\nQuyền lợi: mức lương gross up to 50M, môi trường châu Âu, cơ hội onsite\nYêu cầu: từ 1 năm kinh nghiệm Java, kinh nghiệm React.js, tiếng Anh tốt, thành tích học tập tốt\n4. Junior Frontend Developer\nQuyền lợi: mức lương gross up to 50M, môi trường châu Âu, cơ hội onsite\nYêu cầu: từ 1 năm kinh nghiệm React.js hoặc Vue.js, tiếng Anh tốt, thành tích học tập tốt','[]',2),(1820000000000033,'null',NULL,NULL,NULL,NULL,65000000.00,6000000.00,'Unknown','Unknown','[Hà Nội] Mình vẫn đang tuyển các vị trí C++, Java, Node.js, Frontend. Inb mình để nhận JD nha!!!\n1.C++ Developer\nQuyền lợi: Mức lương up to gross 65M, môi trường tiếng Anh, product lớn\nYêu cầu: từ 2 năm kinh nghiệm C++, kinh nghiệm làm network, tiếng Anh giao tiếp tốt\n2. Java/ Node.js Developer \nQuyền lợi: mức lương up to gross 40M, môi trường global, dự án nước ngoài\nYêu cầu: từ 3 năm kinh nghiệm từ khi tốt nghiệp đại học, tiếng Anh giao tiếp\n3. Java Fullstack Developer (Junior level)\nQuyền lợi: mức lương gross up to 50M, môi trường châu Âu, cơ hội onsite\nYêu cầu: từ 1 năm kinh nghiệm Java, kinh nghiệm React.js, tiếng Anh tốt, thành tích học tập tốt\n4. Junior Frontend Developer \nQuyền lợi: mức lương gross up to 50M, môi trường châu Âu, cơ hội onsite\nYêu cầu: từ 1 năm kinh nghiệm React.js hoặc Vue.js, tiếng Anh tốt, thành tích học tập tốt','[]',2),(1820000000000034,'null',NULL,NULL,NULL,NULL,40000000.00,4000000.00,'Unknown','blthuyen@cmcglobal.vn','CMC GLOBAL ĐÀ NẴNG TÌM KIẾM \nPHP/ ReactJS/ Manual Test/ Automation test\nUpto 40M Kỳ vọng:\nMiddle/ Senior\nGood English\n~4 năm kinh nghiệm\n--> Apply now <--\nEmail: blthuyen@cmcglobal.vn','[]',4);
/*!40000 ALTER TABLE `recruitment_post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `UserID` int NOT NULL AUTO_INCREMENT,
  `Username` varchar(255) NOT NULL,
  `Password` varchar(255) NOT NULL,
  `Email` varchar(255) NOT NULL,
  `Role` enum('admin','user') NOT NULL,
  PRIMARY KEY (`UserID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'john_doe','password123','john@example.com','user'),(2,'admin','admin','admin','admin'),(3,'Nguyễn Bá Xuân An','wwwwwwwwwww','nguyenbaxuanan.pdr.888@gmail.com','user');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-11 14:24:59
