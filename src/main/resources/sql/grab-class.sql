/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50711
 Source Host           : localhost:3306
 Source Schema         : grab-class

 Target Server Type    : MySQL
 Target Server Version : 50711
 File Encoding         : 65001

 Date: 08/04/2023 10:03:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for course_selection_arrangement
-- ----------------------------
DROP TABLE IF EXISTS `course_selection_arrangement`;
CREATE TABLE `course_selection_arrangement`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '抢课课程id',
  `courses_id` bigint(20) NULL DEFAULT NULL COMMENT '课程id',
  `stock_count` int(11) NULL DEFAULT NULL COMMENT '课程容量',
  `start_date` datetime NULL DEFAULT NULL COMMENT '抢课开始时间',
  `end_date` datetime NULL DEFAULT NULL COMMENT '抢课结束时间',
  `state` tinyint(255) NULL DEFAULT NULL COMMENT '0 未生效 1 生效',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of course_selection_arrangement
-- ----------------------------
INSERT INTO `course_selection_arrangement` VALUES (1, 3, 187, '2023-03-29 11:59:19', '2023-03-29 10:59:19', 1);
INSERT INTO `course_selection_arrangement` VALUES (2, 2, 190, '2023-03-29 19:28:20', '2023-03-29 20:28:20', 1);

-- ----------------------------
-- Table structure for courses
-- ----------------------------
DROP TABLE IF EXISTS `courses`;
CREATE TABLE `courses`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '课程id',
  `courses_name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '课程名称',
  `courses_title` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '课程标题',
  `courses_img` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '课程封面',
  `courses_detail` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '课程详情',
  `courses_teacher` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '任课老师',
  `courses_stock` int(11) NULL DEFAULT 0 COMMENT '课程余量',
  `courses_capacity` int(11) NULL DEFAULT NULL COMMENT '课程容量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1641021084546969602 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of courses
-- ----------------------------
INSERT INTO `courses` VALUES (1, '计算机网络', '计算机网络', 'http://baidu.com/500x500', '这是计算机网络', '王小刚', 100, 100);
INSERT INTO `courses` VALUES (2, '数据结构', '数据结构', 'http://baidu.com/500x500', '数据结构与算法非常重要', '军', 141, 189);
INSERT INTO `courses` VALUES (1641020907329236993, '马克思主义思想', '马克思主义思想', 'http://picture/io/png', '马克思主义思想是近代史最伟大的思想之一', '曾黎', 33, 33);
INSERT INTO `courses` VALUES (1641021084546969601, '马克思主义思想', '马克思主义思想', 'http://picture/io/png', '马克思主义思想是近代史最伟大的思想之一', '老王', 33, 33);

-- ----------------------------
-- Table structure for grab_class_records
-- ----------------------------
DROP TABLE IF EXISTS `grab_class_records`;
CREATE TABLE `grab_class_records`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '抢课课程id',
  `student_id` bigint(20) NULL DEFAULT NULL COMMENT '学生id',
  `record_id` bigint(20) NULL DEFAULT NULL COMMENT '记录id',
  `courses_id` bigint(20) NULL DEFAULT NULL COMMENT '课程id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of grab_class_records
-- ----------------------------

-- ----------------------------
-- Table structure for record_info
-- ----------------------------
DROP TABLE IF EXISTS `record_info`;
CREATE TABLE `record_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '选课id',
  `student_id` bigint(20) NULL DEFAULT NULL COMMENT '学生id',
  `courses_id` bigint(20) NULL DEFAULT NULL COMMENT '课程id',
  `courses_name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '课程名称',
  `create_date` datetime NULL DEFAULT NULL COMMENT '选课时间',
  `status` tinyint(4) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1641080614570438657 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of record_info
-- ----------------------------
INSERT INTO `record_info` VALUES (1641042767998156800, 202005618, 2, '数据结构', '2023-03-29 19:40:37', 0);
INSERT INTO `record_info` VALUES (1641042801489674240, 202005618, 2, '数据结构', '2023-03-29 19:41:08', 0);
INSERT INTO `record_info` VALUES (1641042809144279040, 202005618, 2, '数据结构', '2023-03-29 19:41:09', 0);
INSERT INTO `record_info` VALUES (1641042816517865472, 202005618, 2, '数据结构', '2023-03-29 19:41:11', 0);
INSERT INTO `record_info` VALUES (1641042821760745472, 202005618, 2, '数据结构', '2023-03-29 19:41:12', 0);
INSERT INTO `record_info` VALUES (1641042829490847744, 202005618, 2, '数据结构', '2023-03-29 19:41:14', 0);
INSERT INTO `record_info` VALUES (1641043097662062592, 202005618, 2, '数据结构', '2023-03-29 19:42:18', 0);
INSERT INTO `record_info` VALUES (1641043114485415936, 202005618, 2, '数据结构', '2023-03-29 19:42:22', 0);
INSERT INTO `record_info` VALUES (1641043150287994880, 202005618, 2, '数据结构', '2023-03-29 19:42:31', 0);
INSERT INTO `record_info` VALUES (1641043152385146880, 202005618, 2, '数据结构', '2023-03-29 19:42:31', 0);
INSERT INTO `record_info` VALUES (1641043153081401344, 202005618, 2, '数据结构', '2023-03-29 19:42:31', 0);
INSERT INTO `record_info` VALUES (1641043153756684288, 202005618, 2, '数据结构', '2023-03-29 19:42:32', 0);
INSERT INTO `record_info` VALUES (1641043154406801408, 202005618, 2, '数据结构', '2023-03-29 19:42:32', 0);
INSERT INTO `record_info` VALUES (1641045645957586944, 202005618, 2, '数据结构', '2023-03-29 19:52:22', 0);
INSERT INTO `record_info` VALUES (1641047981715206144, 202005618, 2, '数据结构', '2023-03-29 20:01:39', 0);
INSERT INTO `record_info` VALUES (1641048005509492736, 202005618, 2, '数据结构', '2023-03-29 20:01:48', 0);
INSERT INTO `record_info` VALUES (1641049399280906240, 202005618, 2, '数据结构', '2023-03-29 20:07:21', 0);
INSERT INTO `record_info` VALUES (1641064929567977472, 202005618, 2, '数据结构', '2023-03-29 21:08:58', 1);
INSERT INTO `record_info` VALUES (1641070821277536256, 202005618, 2, '数据结构', '2023-03-29 21:32:15', 1);
INSERT INTO `record_info` VALUES (1641070930950197248, 202005618, 2, '数据结构', '2023-03-29 21:32:54', 0);
INSERT INTO `record_info` VALUES (1641070932044910592, 202005618, 2, '数据结构', '2023-03-29 21:32:54', 0);
INSERT INTO `record_info` VALUES (1641070932657278976, 202005618, 2, '数据结构', '2023-03-29 21:32:55', 0);
INSERT INTO `record_info` VALUES (1641070933433225216, 202005618, 2, '数据结构', '2023-03-29 21:32:55', 0);
INSERT INTO `record_info` VALUES (1641070934167228416, 202005618, 2, '数据结构', '2023-03-29 21:32:55', 0);
INSERT INTO `record_info` VALUES (1641070934888648704, 202005618, 2, '数据结构', '2023-03-29 21:32:55', 0);
INSERT INTO `record_info` VALUES (1641070935601680384, 202005618, 2, '数据结构', '2023-03-29 21:32:55', 0);
INSERT INTO `record_info` VALUES (1641070936616701952, 202005618, 2, '数据结构', '2023-03-29 21:32:56', 0);
INSERT INTO `record_info` VALUES (1641070937413619712, 202005618, 2, '数据结构', '2023-03-29 21:32:56', 0);
INSERT INTO `record_info` VALUES (1641070937937907712, 202005618, 2, '数据结构', '2023-03-29 21:32:56', 0);
INSERT INTO `record_info` VALUES (1641070938663522304, 202005618, 2, '数据结构', '2023-03-29 21:32:56', 0);
INSERT INTO `record_info` VALUES (1641070939707904000, 202005618, 2, '数据结构', '2023-03-29 21:32:56', 0);
INSERT INTO `record_info` VALUES (1641070958741655552, 202005618, 2, '数据结构', '2023-03-29 21:33:01', 0);
INSERT INTO `record_info` VALUES (1641070959928643584, 202005618, 2, '数据结构', '2023-03-29 21:33:01', 0);
INSERT INTO `record_info` VALUES (1641070960587149312, 202005618, 2, '数据结构', '2023-03-29 21:33:01', 0);
INSERT INTO `record_info` VALUES (1641070961329541120, 202005618, 2, '数据结构', '2023-03-29 21:33:01', 0);
INSERT INTO `record_info` VALUES (1641070967537111040, 202005618, 2, '数据结构', '2023-03-29 21:33:03', 0);
INSERT INTO `record_info` VALUES (1641070969042866176, 202005618, 2, '数据结构', '2023-03-29 21:33:03', 0);
INSERT INTO `record_info` VALUES (1641070970024333312, 202005618, 2, '数据结构', '2023-03-29 21:33:04', 0);
INSERT INTO `record_info` VALUES (1641070973094563840, 202005618, 2, '数据结构', '2023-03-29 21:33:04', 0);
INSERT INTO `record_info` VALUES (1641070974067642368, 202005618, 2, '数据结构', '2023-03-29 21:33:04', 0);
INSERT INTO `record_info` VALUES (1641070974508044288, 202005618, 2, '数据结构', '2023-03-29 21:33:05', 0);
INSERT INTO `record_info` VALUES (1641070975409819648, 202005618, 2, '数据结构', '2023-03-29 21:33:05', 0);
INSERT INTO `record_info` VALUES (1641070975728586752, 202005618, 2, '数据结构', '2023-03-29 21:33:05', 0);
INSERT INTO `record_info` VALUES (1641070976634556416, 202005618, 2, '数据结构', '2023-03-29 21:33:05', 0);
INSERT INTO `record_info` VALUES (1641070977041403904, 202005618, 2, '数据结构', '2023-03-29 21:33:05', 0);
INSERT INTO `record_info` VALUES (1641070977850904576, 202005618, 2, '数据结构', '2023-03-29 21:33:05', 0);
INSERT INTO `record_info` VALUES (1641070978580713472, 202005618, 2, '数据结构', '2023-03-29 21:33:06', 0);
INSERT INTO `record_info` VALUES (1641070979297939456, 202005618, 2, '数据结构', '2023-03-29 21:33:06', 0);
INSERT INTO `record_info` VALUES (1641070979713175552, 202005618, 2, '数据结构', '2023-03-29 21:33:06', 0);
INSERT INTO `record_info` VALUES (1641070980585590784, 202005618, 2, '数据结构', '2023-03-29 21:33:06', 0);
INSERT INTO `record_info` VALUES (1641070981181181952, 202005618, 2, '数据结构', '2023-03-29 21:33:06', 0);
INSERT INTO `record_info` VALUES (1641070981885825024, 202005618, 2, '数据结构', '2023-03-29 21:33:06', 0);
INSERT INTO `record_info` VALUES (1641070982615633920, 202005618, 2, '数据结构', '2023-03-29 21:33:07', 0);
INSERT INTO `record_info` VALUES (1641070983303499776, 202005618, 2, '数据结构', '2023-03-29 21:33:07', 0);
INSERT INTO `record_info` VALUES (1641070983920062464, 202005618, 2, '数据结构', '2023-03-29 21:33:07', 0);
INSERT INTO `record_info` VALUES (1641070984792477696, 202005618, 2, '数据结构', '2023-03-29 21:33:07', 0);
INSERT INTO `record_info` VALUES (1641070985371291648, 202005618, 2, '数据结构', '2023-03-29 21:33:07', 0);
INSERT INTO `record_info` VALUES (1641070986130460672, 202005618, 2, '数据结构', '2023-03-29 21:33:07', 0);
INSERT INTO `record_info` VALUES (1641070986843492352, 202005618, 2, '数据结构', '2023-03-29 21:33:08', 0);
INSERT INTO `record_info` VALUES (1641070987527163904, 202005618, 2, '数据结构', '2023-03-29 21:33:08', 0);
INSERT INTO `record_info` VALUES (1641070988185669632, 202005618, 2, '数据结构', '2023-03-29 21:33:08', 0);
INSERT INTO `record_info` VALUES (1641070989322326016, 202005618, 2, '数据结构', '2023-03-29 21:33:08', 0);
INSERT INTO `record_info` VALUES (1641070989733367808, 202005618, 2, '数据结构', '2023-03-29 21:33:08', 0);
INSERT INTO `record_info` VALUES (1641070990488342528, 202005618, 2, '数据结构', '2023-03-29 21:33:08', 0);
INSERT INTO `record_info` VALUES (1641070991251705856, 202005618, 2, '数据结构', '2023-03-29 21:33:09', 0);
INSERT INTO `record_info` VALUES (1641070991876657152, 202005618, 2, '数据结构', '2023-03-29 21:33:09', 0);
INSERT INTO `record_info` VALUES (1641070992648409088, 202005618, 2, '数据结构', '2023-03-29 21:33:09', 0);
INSERT INTO `record_info` VALUES (1641070993457909760, 202005618, 2, '数据结构', '2023-03-29 21:33:09', 0);
INSERT INTO `record_info` VALUES (1641070993852174336, 202005618, 2, '数据结构', '2023-03-29 21:33:09', 0);
INSERT INTO `record_info` VALUES (1641070996037406720, 202005618, 2, '数据结构', '2023-03-29 21:33:10', 0);
INSERT INTO `record_info` VALUES (1641070996045795328, 202005618, 2, '数据结构', '2023-03-29 21:33:10', 0);
INSERT INTO `record_info` VALUES (1641070996670746624, 202005618, 2, '数据结构', '2023-03-29 21:33:10', 0);
INSERT INTO `record_info` VALUES (1641070997199228928, 202005618, 2, '数据结构', '2023-03-29 21:33:10', 0);
INSERT INTO `record_info` VALUES (1641070997572521984, 202005618, 2, '数据结构', '2023-03-29 21:33:10', 0);
INSERT INTO `record_info` VALUES (1641070998499463168, 202005618, 2, '数据结构', '2023-03-29 21:33:10', 0);
INSERT INTO `record_info` VALUES (1641070998881144832, 202005618, 2, '数据结构', '2023-03-29 21:33:10', 0);
INSERT INTO `record_info` VALUES (1641070999711617024, 202005618, 2, '数据结构', '2023-03-29 21:33:11', 0);
INSERT INTO `record_info` VALUES (1641071000294625280, 202005618, 2, '数据结构', '2023-03-29 21:33:11', 0);
INSERT INTO `record_info` VALUES (1641071001628413952, 202005618, 2, '数据结构', '2023-03-29 21:33:11', 0);
INSERT INTO `record_info` VALUES (1641071002026872832, 202005618, 2, '数据结构', '2023-03-29 21:33:11', 0);
INSERT INTO `record_info` VALUES (1641071002488246272, 202005618, 2, '数据结构', '2023-03-29 21:33:11', 0);
INSERT INTO `record_info` VALUES (1641071003134169088, 202005618, 2, '数据结构', '2023-03-29 21:33:11', 0);
INSERT INTO `record_info` VALUES (1641071004044333056, 202005618, 2, '数据结构', '2023-03-29 21:33:12', 0);
INSERT INTO `record_info` VALUES (1641071004467957760, 202005618, 2, '数据结构', '2023-03-29 21:33:12', 0);
INSERT INTO `record_info` VALUES (1641071004870610944, 202005618, 2, '数据结构', '2023-03-29 21:33:12', 0);
INSERT INTO `record_info` VALUES (1641071006263119872, 202005618, 2, '数据结构', '2023-03-29 21:33:12', 0);
INSERT INTO `record_info` VALUES (1641071008234442752, 202005618, 2, '数据结构', '2023-03-29 21:33:13', 0);
INSERT INTO `record_info` VALUES (1641071008918114304, 202005618, 2, '数据结构', '2023-03-29 21:33:13', 0);
INSERT INTO `record_info` VALUES (1641071009811501056, 202005618, 2, '数据结构', '2023-03-29 21:33:13', 0);
INSERT INTO `record_info` VALUES (1641071010247708672, 202005618, 2, '数据结构', '2023-03-29 21:33:13', 0);
INSERT INTO `record_info` VALUES (1641071010973323264, 202005618, 2, '数据结构', '2023-03-29 21:33:13', 0);
INSERT INTO `record_info` VALUES (1641071011627634688, 202005618, 2, '数据结构', '2023-03-29 21:33:13', 0);
INSERT INTO `record_info` VALUES (1641071012579741696, 202005618, 2, '数据结构', '2023-03-29 21:33:14', 0);
INSERT INTO `record_info` VALUES (1641071013343105024, 202005618, 2, '数据结构', '2023-03-29 21:33:14', 0);
INSERT INTO `record_info` VALUES (1641071014068719616, 202005618, 2, '数据结构', '2023-03-29 21:33:14', 0);
INSERT INTO `record_info` VALUES (1641071014647533568, 202005618, 2, '数据结构', '2023-03-29 21:33:14', 0);
INSERT INTO `record_info` VALUES (1641071015423479808, 202005618, 2, '数据结构', '2023-03-29 21:33:14', 0);
INSERT INTO `record_info` VALUES (1641071016048431104, 202005618, 2, '数据结构', '2023-03-29 21:33:15', 0);
INSERT INTO `record_info` VALUES (1641071016702742528, 202005618, 2, '数据结构', '2023-03-29 21:33:15', 0);
INSERT INTO `record_info` VALUES (1641071017579352064, 202005618, 2, '数据结构', '2023-03-29 21:33:15', 0);
INSERT INTO `record_info` VALUES (1641071018518876160, 202005618, 2, '数据结构', '2023-03-29 21:33:15', 0);
INSERT INTO `record_info` VALUES (1641071018887974912, 202005618, 2, '数据结构', '2023-03-29 21:33:15', 0);
INSERT INTO `record_info` VALUES (1641071020678942720, 202005618, 2, '数据结构', '2023-03-29 21:33:16', 0);
INSERT INTO `record_info` VALUES (1641071021295505408, 202005618, 2, '数据结构', '2023-03-29 21:33:16', 0);
INSERT INTO `record_info` VALUES (1641071022105006080, 202005618, 2, '数据结构', '2023-03-29 21:33:16', 0);
INSERT INTO `record_info` VALUES (1641071115625402368, 202005618, 2, '数据结构', '2023-03-29 21:33:38', 0);
INSERT INTO `record_info` VALUES (1641071254247149568, 202005618, 2, '数据结构', '2023-03-29 21:34:11', 0);
INSERT INTO `record_info` VALUES (1641072963233107968, 202005618, 2, '数据结构', '2023-03-29 21:40:47', 0);
INSERT INTO `record_info` VALUES (1641073444034564096, 202005618, 2, '数据结构', '2023-03-29 21:42:53', 0);
INSERT INTO `record_info` VALUES (1641073649941336064, 202005618, 2, '数据结构', '2023-03-29 21:43:42', 0);
INSERT INTO `record_info` VALUES (1641074644662358016, 202005618, 2, '数据结构', '2023-03-29 21:47:36', 1);
INSERT INTO `record_info` VALUES (1641074721455869952, 202005618, 2, '数据结构', '2023-03-29 21:47:58', 0);
INSERT INTO `record_info` VALUES (1641077342220607488, 202005618, 2, '数据结构', '2023-03-29 21:58:17', 0);
INSERT INTO `record_info` VALUES (1641078139893342208, 202005618, 2, '数据结构', '2023-03-29 22:01:33', 1);
INSERT INTO `record_info` VALUES (1641078793584009216, 202005618, 2, '数据结构', '2023-03-29 22:04:09', 0);
INSERT INTO `record_info` VALUES (1641078855257055232, 202005618, 2, '数据结构', '2023-03-29 22:04:24', 0);
INSERT INTO `record_info` VALUES (1641079618305802240, 202005618, 2, '数据结构', '2023-03-29 22:07:22', 1);
INSERT INTO `record_info` VALUES (1641079883503255552, 202005618, 2, '数据结构', '2023-03-29 22:08:29', 1);
INSERT INTO `record_info` VALUES (1641080318876200960, 202005618, 2, '数据结构', '2023-03-29 22:10:07', 1);
INSERT INTO `record_info` VALUES (1641080475273408512, 202005618, 2, '数据结构', '2023-03-29 22:10:50', 1);
INSERT INTO `record_info` VALUES (1641080510585253888, 202005618, 2, '数据结构', '2023-03-29 22:10:58', 1);
INSERT INTO `record_info` VALUES (1641080547105058816, 202005618, 2, '数据结构', '2023-03-29 22:11:07', 1);
INSERT INTO `record_info` VALUES (1641080548111691776, 202005618, 2, '数据结构', '2023-03-29 22:11:07', 1);
INSERT INTO `record_info` VALUES (1641080548451430400, 202005618, 2, '数据结构', '2023-03-29 22:11:07', 1);
INSERT INTO `record_info` VALUES (1641080549151879168, 202005618, 2, '数据结构', '2023-03-29 22:11:07', 1);
INSERT INTO `record_info` VALUES (1641080549957185536, 202005618, 2, '数据结构', '2023-03-29 22:11:08', 1);
INSERT INTO `record_info` VALUES (1641080550280146944, 202005618, 2, '数据结构', '2023-03-29 22:11:08', 1);
INSERT INTO `record_info` VALUES (1641080551504883712, 202005618, 2, '数据结构', '2023-03-29 22:11:08', 1);
INSERT INTO `record_info` VALUES (1641080551827845120, 202005618, 2, '数据结构', '2023-03-29 22:11:08', 1);
INSERT INTO `record_info` VALUES (1641080552511516672, 202005618, 2, '数据结构', '2023-03-29 22:11:08', 1);
INSERT INTO `record_info` VALUES (1641080553211965440, 202005618, 2, '数据结构', '2023-03-29 22:11:08', 1);
INSERT INTO `record_info` VALUES (1641080553723670528, 202005618, 2, '数据结构', '2023-03-29 22:11:08', 1);
INSERT INTO `record_info` VALUES (1641080554319261696, 202005618, 2, '数据结构', '2023-03-29 22:11:09', 1);
INSERT INTO `record_info` VALUES (1641080555095207936, 202005618, 2, '数据结构', '2023-03-29 22:11:09', 1);
INSERT INTO `record_info` VALUES (1641080555598524416, 202005618, 2, '数据结构', '2023-03-29 22:11:09', 1);
INSERT INTO `record_info` VALUES (1641080556286390272, 202005618, 2, '数据结构', '2023-03-29 22:11:09', 1);
INSERT INTO `record_info` VALUES (1641080556919730176, 202005618, 2, '数据结构', '2023-03-29 22:11:09', 1);
INSERT INTO `record_info` VALUES (1641080557527904256, 202005618, 2, '数据结构', '2023-03-29 22:11:09', 1);
INSERT INTO `record_info` VALUES (1641080558136078336, 202005618, 2, '数据结构', '2023-03-29 22:11:10', 1);
INSERT INTO `record_info` VALUES (1641080558773612544, 202005618, 2, '数据结构', '2023-03-29 22:11:10', 1);
INSERT INTO `record_info` VALUES (1641080559474061312, 202005618, 2, '数据结构', '2023-03-29 22:11:10', 1);
INSERT INTO `record_info` VALUES (1641080560182898688, 202005618, 2, '数据结构', '2023-03-29 22:11:10', 1);
INSERT INTO `record_info` VALUES (1641080560786878464, 202005618, 2, '数据结构', '2023-03-29 22:11:10', 1);
INSERT INTO `record_info` VALUES (1641080561525075968, 202005618, 2, '数据结构', '2023-03-29 22:11:10', 1);
INSERT INTO `record_info` VALUES (1641080562166804480, 202005618, 2, '数据结构', '2023-03-29 22:11:10', 1);
INSERT INTO `record_info` VALUES (1641080562842087424, 202005618, 2, '数据结构', '2023-03-29 22:11:11', 1);
INSERT INTO `record_info` VALUES (1641080605695291392, 202005618, 2, '数据结构', '2023-03-29 22:11:21', 1);
INSERT INTO `record_info` VALUES (1641080606366380032, 202005618, 2, '数据结构', '2023-03-29 22:11:21', 1);
INSERT INTO `record_info` VALUES (1641080607045857280, 202005618, 2, '数据结构', '2023-03-29 22:11:21', 1);
INSERT INTO `record_info` VALUES (1641080607729528832, 202005618, 2, '数据结构', '2023-03-29 22:11:21', 1);
INSERT INTO `record_info` VALUES (1641080608388034560, 202005618, 2, '数据结构', '2023-03-29 22:11:21', 1);
INSERT INTO `record_info` VALUES (1641080608987820032, 202005618, 2, '数据结构', '2023-03-29 22:11:22', 1);
INSERT INTO `record_info` VALUES (1641080609742794752, 202005618, 2, '数据结构', '2023-03-29 22:11:22', 1);
INSERT INTO `record_info` VALUES (1641080610476797952, 202005618, 2, '数据结构', '2023-03-29 22:11:22', 1);
INSERT INTO `record_info` VALUES (1641080611126915072, 202005618, 2, '数据结构', '2023-03-29 22:11:22', 1);
INSERT INTO `record_info` VALUES (1641080611714117632, 202005618, 2, '数据结构', '2023-03-29 22:11:22', 1);
INSERT INTO `record_info` VALUES (1641080612452315136, 202005618, 2, '数据结构', '2023-03-29 22:11:22', 1);
INSERT INTO `record_info` VALUES (1641080613115015168, 202005618, 2, '数据结构', '2023-03-29 22:11:23', 1);
INSERT INTO `record_info` VALUES (1641080613857406976, 202005618, 2, '数据结构', '2023-03-29 22:11:23', 1);
INSERT INTO `record_info` VALUES (1641080614570438656, 202005618, 2, '数据结构', '2023-03-29 22:11:23', 1);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL COMMENT '学号\\工号',
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '昵称',
  `password` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '暗文存储：MD5(MD5(pass明文, 固定SALT), 随机SALT)',
  `register_date` datetime NULL DEFAULT NULL COMMENT '注册时间',
  `last_login_date` datetime NOT NULL COMMENT '上一次登录时间',
  `login_count` int(11) NULL DEFAULT 0 COMMENT '登录次数',
  `role` smallint(6) NULL DEFAULT NULL COMMENT '0-student 1-teacher 3-admin',
  PRIMARY KEY (`id`, `last_login_date`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (202005618, '大熊', '111111', '2023-03-29 19:17:02', '2023-03-29 19:17:06', 0, 1);

SET FOREIGN_KEY_CHECKS = 1;
