/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 80035
 Source Host           : localhost:3306
 Source Schema         : tsglxt

 Target Server Type    : MySQL
 Target Server Version : 80035
 File Encoding         : 65001

 Date: 29/11/2023 17:24:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_book
-- ----------------------------
DROP TABLE IF EXISTS `sys_book`;
CREATE TABLE `sys_book`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '编号',
  `isbn` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '书ISBN',
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '书名',
  `author` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '作者',
  `category_id` int NULL DEFAULT NULL COMMENT '类型id',
  `detail` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '书籍详细信息',
  `cover` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '书籍封面',
  `publishdate` date NOT NULL COMMENT '出版日期',
  `publisher` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '出版商',
  `status` int(1) UNSIGNED ZEROFILL NOT NULL DEFAULT 0 COMMENT '状态：0上架1下架',
  `type` int(1) UNSIGNED ZEROFILL NOT NULL DEFAULT 0 COMMENT '书类型：0实体书1电子书2两者皆有',
  `url` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '电子书url',
  `count` int UNSIGNED NULL DEFAULT 0 COMMENT '书籍数量',
  `file_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '文件名',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `info_isbn`(`isbn`) USING BTREE,
  INDEX `info_category_id`(`category_id`) USING BTREE,
  CONSTRAINT `info_category_id` FOREIGN KEY (`category_id`) REFERENCES `sys_book_category` (`id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_book
-- ----------------------------
INSERT INTO `sys_book` VALUES (27, '9780061120084', 'To Kill a Mockingbird', 'Harper Lee', 1, 'A novel set in the American South during the 1930s, addressing issues of racism and prejudice.', 'mockingbird.jpg', '1960-07-11', 'Harper Perennial Modern Classics', 0, 1, 'E:\\\\javaStudy\\\\imau-bookmanager-file\\\\洛斯达南方分公司员工请假申请表.docx', 0, '洛斯达南方分公司员工请假申请表.docx');
INSERT INTO `sys_book` VALUES (28, '9780140283334', '1988', 'George Orwell', 1, 'A dystopian social science fiction novel portraying a totalitarian regime and its impact on society.', '1984.jpg', '1949-06-08', 'Penguin Books', 0, 1, 'E:\\\\javaStudy\\\\imau-bookmanager-file\\\\洛斯达南方分公司员工请假申请表.docx', 4, '洛斯达南方分公司员工请假申请表.docx');
INSERT INTO `sys_book` VALUES (29, '9780735219090', 'The Silent Patient', 'Alex Michaelides', 2, 'A psychological thriller about a woman who stops speaking after being accused of a brutal murder.', 'silent_patient.jpg', '2019-02-05', 'Celadon Books', 0, 2, NULL, 3, NULL);
INSERT INTO `sys_book` VALUES (30, '9781501173219', 'Educated', 'Tara Westover', 3, 'A memoir recounting the author’s journey from a survivalist family to earning a PhD from Cambridge University.', 'educated.jpg', '2018-02-20', 'Random House', 0, 2, NULL, 0, NULL);
INSERT INTO `sys_book` VALUES (31, '9780062315007', 'The Goldfinch', 'Donna Tartt', 4, 'A coming-of-age novel about a boy who survives a terrorist attack at an art museum.', 'goldfinch.jpg', '2013-10-22', 'Little, Brown and Company', 0, 1, 'E:\\\\javaStudy\\\\imau-bookmanager-file\\\\洛斯达南方分公司员工请假申请表.docx', 1, '洛斯达南方分公司员工请假申请表.docx');
INSERT INTO `sys_book` VALUES (32, '9780345391803', 'Jurassic Park', 'Michael Crichton', 5, 'A science fiction novel about the disastrous attempt to create a theme park of cloned dinosaurs.', 'jurassic_park.jpg', '1990-11-20', 'Ballantine Books', 0, 1, 'E:\\\\javaStudy\\\\imau-bookmanager-file\\\\洛斯达南方分公司员工请假申请表.docx', 45, '洛斯达南方分公司员工请假申请表.docx');
INSERT INTO `sys_book` VALUES (34, '2333', 'To Kill a Mockingbird', 'Harper Lee', 5, 'A novel set in the American South during the 1930s, addressing issues of racism and prejudice.', 'mockingbird.jpg', '1960-07-11', 'Harper Perennial Modern Classics', 0, 0, NULL, 1, NULL);
INSERT INTO `sys_book` VALUES (37, '22333333', '19999', 'luoxincong', 2, 'ABCDEFG', '234567', '2019-02-05', 'luo', 0, 0, '', 1, NULL);
INSERT INTO `sys_book` VALUES (38, 'test', 'test', 'luo', 2, '测试简介', NULL, '2023-11-23', 'lsd', 0, 2, 'E:\\javaStudy\\imau-bookmanager-file\\SpringSecurity.md', 3, 'SpringSecurity.md');

-- ----------------------------
-- Table structure for sys_book_category
-- ----------------------------
DROP TABLE IF EXISTS `sys_book_category`;
CREATE TABLE `sys_book_category`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '类别id',
  `name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '类别名称',
  `info` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '类别信息',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_book_category
-- ----------------------------
INSERT INTO `sys_book_category` VALUES (1, '小说', '小说类图书');
INSERT INTO `sys_book_category` VALUES (2, '科幻', '科幻类图书');
INSERT INTO `sys_book_category` VALUES (3, '悬疑', '悬疑类图书');
INSERT INTO `sys_book_category` VALUES (4, '传记', '传记类图书');
INSERT INTO `sys_book_category` VALUES (5, '恐怖', '恐怖类图书');
INSERT INTO `sys_book_category` VALUES (6, '科普', '科普类图书');
INSERT INTO `sys_book_category` VALUES (7, '古典', '古典类图书');

-- ----------------------------
-- Table structure for sys_borrowing_ticket
-- ----------------------------
DROP TABLE IF EXISTS `sys_borrowing_ticket`;
CREATE TABLE `sys_borrowing_ticket`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '借阅单id',
  `book_id` int NOT NULL COMMENT '借阅书id',
  `plan_return_date` date NULL DEFAULT NULL COMMENT '应归还日期',
  `rent_date` date NULL DEFAULT NULL COMMENT '借阅日期-一般3个月',
  `is_fine` int NOT NULL DEFAULT 0 COMMENT '状态是否结束-0结束1未结束',
  `return_date` date NULL DEFAULT NULL COMMENT '实际归还日期',
  `is_continue` int NOT NULL DEFAULT 0 COMMENT '是否续借-0还未续借1续借2归还',
  `user_id` int NOT NULL COMMENT '用户id',
  `todo_status` int NOT NULL DEFAULT 0 COMMENT '待办状态0审核中1成功2退回',
  `approver_id` int NOT NULL COMMENT '审批人id',
  `create_time` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `user_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `book_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '书名',
  `is_notice` int NULL DEFAULT 0 COMMENT '是否已提醒用户，0需要提醒，1不需要提醒',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 103 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_borrowing_ticket
-- ----------------------------
INSERT INTO `sys_borrowing_ticket` VALUES (92, 34, '2024-02-23', '2023-11-21', 0, NULL, 1, 17, 2, 0, '2023-11-23 15:53:21', '罗1某', 'To Kill a Mockingbird', 0);
INSERT INTO `sys_borrowing_ticket` VALUES (94, 28, '2024-05-23', '2023-11-23', 0, NULL, 1, 37, 1, 0, '2023-11-23 00:00:00', 'user', '1988', 1);
INSERT INTO `sys_borrowing_ticket` VALUES (95, 29, '2024-02-23', '2023-11-23', 0, NULL, 0, 37, 1, 0, '2023-11-23 00:00:00', 'user', 'The Silent Patient', 1);
INSERT INTO `sys_borrowing_ticket` VALUES (97, 28, '2024-02-28', '2023-11-28', 0, NULL, 0, 37, 0, 0, '2023-11-28 00:00:00', 'user', '1988', 0);
INSERT INTO `sys_borrowing_ticket` VALUES (100, 29, '2024-02-29', '2023-11-29', 0, NULL, 0, 37, 1, 0, '2023-11-29 00:00:00', 'user', 'The Silent Patient', 0);
INSERT INTO `sys_borrowing_ticket` VALUES (101, 34, '2024-02-29', '2023-11-29', 0, NULL, 0, 37, 1, 0, '2023-11-29 00:00:00', 'user', 'To Kill a Mockingbird', 0);
INSERT INTO `sys_borrowing_ticket` VALUES (102, 37, '2024-02-29', '2023-11-29', 0, NULL, 0, 37, 1, 0, '2023-11-29 00:00:00', 'user', '19999', 0);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` bigint NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT 'NULL' COMMENT '菜单名称',
  `parent_id` bigint NULL DEFAULT 0 COMMENT '父菜单ID',
  `order_num` int NULL DEFAULT 0 COMMENT '显示顺序',
  `path` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '' COMMENT '路由地址',
  `component` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '组件路径',
  `query` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '路由参数',
  `is_frame` int NULL DEFAULT 1 COMMENT '是否为外链（0是 1否）',
  `is_cache` int NULL DEFAULT 0 COMMENT '是否缓存（0缓存 1不缓存）',
  `menu_type` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `status` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
  `perms` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '#' COMMENT '菜单图标',
  `create_by` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2006 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '菜单权限表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '书库', 0, 1, '/book', 'Layout', '', 1, 0, 'M', '0', '0', '', 'el-icon-notebook-1', 'admin', '2022-10-27 21:02:17', '', NULL, '书库目录');
INSERT INTO `sys_menu` VALUES (2, '借阅记录', 0, 2, '/bookBorrowing', 'Layout', '', 1, 0, 'M', '0', '0', '', 'el-icon-s-order', 'admin', '2022-10-20 09:07:15', '', NULL, '借阅记录目录');
INSERT INTO `sys_menu` VALUES (3, '人员管理', 0, 3, '/personnelManagement', 'Layout', '', 1, 0, 'M', '0', '0', '', 'system', 'admin', '2022-10-20 09:07:15', '', NULL, '人员管理目录');
INSERT INTO `sys_menu` VALUES (4, '待办', 0, 4, '/todo', 'Layout', '', 1, 0, 'M', '0', '0', '', 'el-icon-message-solid', 'admin', '2022-10-17 21:02:17', '', NULL, '待办目录');
INSERT INTO `sys_menu` VALUES (5, '个人信息', 0, 5, '/info', 'Layout', '', 1, 0, 'M', '0', '0', '', 'system', 'admin', '2022-10-20 09:07:15', '', NULL, '个人信息目录');
INSERT INTO `sys_menu` VALUES (100, '书库', 1, 1, 'index', 'book/index', '', 1, 0, 'C', '0', '0', 'book:list', 'el-icon-notebook-1', 'admin', '2022-10-27 21:02:17', '', NULL, '书库');
INSERT INTO `sys_menu` VALUES (200, '借阅记录', 2, 2, 'index', 'bookBorrowing/index', '', 1, 0, 'C', '0', '0', 'bookBorrowing:list', 'el-icon-s-order', 'admin', '2022-10-17 21:02:17', '', NULL, '借阅记录');
INSERT INTO `sys_menu` VALUES (300, '人员管理', 3, 3, 'index', 'personnelManagement/index', '', 1, 0, 'C', '0', '0', 'user:list', 'user', 'admin', '2022-10-17 21:02:17', '', NULL, '人员管理');
INSERT INTO `sys_menu` VALUES (400, '待办', 4, 4, 'index', 'todo/index', '', 1, 0, 'C', '0', '0', 'todo:list', 'el-icon-message-solid', 'admin', '2022-10-17 21:02:17', '', NULL, '待办');
INSERT INTO `sys_menu` VALUES (500, '个人信息', 5, 5, 'index', 'info/index', '', 1, 0, 'C', '0', '0', 'info:list', 'user', 'admin', '2022-10-17 21:02:17', '', NULL, '个人信息');
INSERT INTO `sys_menu` VALUES (1000, '新增图书', 100, 1, '', '', '', 1, 0, 'F', '0', '0', 'book:add', '#', 'admin', '2022-10-27 21:02:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1001, '修改图书', 100, 2, '', '', '', 1, 0, 'F', '0', '0', 'book:edit', '#', 'admin', '2022-10-27 21:02:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1002, '删除图书', 100, 3, '', '', '', 1, 0, 'F', '0', '0', 'book:delete', '#', 'admin', '2022-10-27 21:02:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1003, '查询图书', 100, 4, '', '', '', 1, 0, 'F', '0', '0', 'book:query', '#', 'admin', '2022-10-27 21:02:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1004, '图书借阅', 100, 5, '', NULL, NULL, 1, 0, 'F', '0', '0', 'book:borrowing', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1005, '图书归还', 100, 6, '', NULL, NULL, 1, 0, 'F', '0', '0', 'book:return', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1006, '图书续借', 100, 7, '', NULL, NULL, 1, 0, 'F', '0', '0', 'book:renew', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1007, '新增待办', 400, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'todo:add', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1008, '删除待办', 400, 2, '', NULL, NULL, 1, 0, 'F', '0', '0', 'todo:delete', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1009, '修改待办', 400, 3, '', NULL, NULL, 1, 0, 'F', '0', '0', 'todo:edit', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1010, '查询待办', 400, 4, '', NULL, NULL, 1, 0, 'F', '0', '0', 'todo:query', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1011, '待办通过', 400, 5, '', NULL, NULL, 1, 0, 'F', '0', '0', 'todo:agree', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1012, '待办拒绝', 400, 6, '', NULL, NULL, 1, 0, 'F', '0', '0', 'todo:reject', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1013, '用户查询', 300, 1, '', '', '', 1, 0, 'F', '0', '0', 'user:query', '#', 'admin', '2022-10-20 09:07:15', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1014, '用户新增', 300, 2, '', '', '', 1, 0, 'F', '0', '0', 'user:add', '#', 'admin', '2022-10-20 09:07:15', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1015, '用户修改', 300, 3, '', '', '', 1, 0, 'F', '0', '0', 'user:edit', '#', 'admin', '2022-10-20 09:07:15', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1016, '用户删除', 300, 4, '', '', '', 1, 0, 'F', '0', '0', 'user:remove', '#', 'admin', '2022-10-20 09:07:15', '', NULL, '');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `role_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色权限字符串',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '角色状态（0正常 1停用）',
  `del_flag` int NULL DEFAULT 0 COMMENT 'del_flag',
  `create_by` bigint NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_by` bigint NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '超管', 'admin', '0', 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role` VALUES (2, '高级用户', 'libaryManage', '0', 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role` VALUES (3, '管理员', 'reader', '0', 0, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `menu_id` bigint NOT NULL DEFAULT 0 COMMENT '菜单id',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (1, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2);
INSERT INTO `sys_role_menu` VALUES (1, 3);
INSERT INTO `sys_role_menu` VALUES (1, 4);
INSERT INTO `sys_role_menu` VALUES (1, 5);
INSERT INTO `sys_role_menu` VALUES (1, 100);
INSERT INTO `sys_role_menu` VALUES (1, 200);
INSERT INTO `sys_role_menu` VALUES (1, 300);
INSERT INTO `sys_role_menu` VALUES (1, 400);
INSERT INTO `sys_role_menu` VALUES (1, 500);
INSERT INTO `sys_role_menu` VALUES (1, 1000);
INSERT INTO `sys_role_menu` VALUES (1, 1001);
INSERT INTO `sys_role_menu` VALUES (1, 1002);
INSERT INTO `sys_role_menu` VALUES (1, 1003);
INSERT INTO `sys_role_menu` VALUES (1, 1004);
INSERT INTO `sys_role_menu` VALUES (1, 1005);
INSERT INTO `sys_role_menu` VALUES (1, 1006);
INSERT INTO `sys_role_menu` VALUES (1, 1007);
INSERT INTO `sys_role_menu` VALUES (1, 1008);
INSERT INTO `sys_role_menu` VALUES (1, 1009);
INSERT INTO `sys_role_menu` VALUES (1, 1010);
INSERT INTO `sys_role_menu` VALUES (1, 1011);
INSERT INTO `sys_role_menu` VALUES (1, 1012);
INSERT INTO `sys_role_menu` VALUES (1, 1013);
INSERT INTO `sys_role_menu` VALUES (1, 1014);
INSERT INTO `sys_role_menu` VALUES (1, 1015);
INSERT INTO `sys_role_menu` VALUES (1, 1016);
INSERT INTO `sys_role_menu` VALUES (2, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2);
INSERT INTO `sys_role_menu` VALUES (2, 4);
INSERT INTO `sys_role_menu` VALUES (2, 5);
INSERT INTO `sys_role_menu` VALUES (2, 100);
INSERT INTO `sys_role_menu` VALUES (2, 200);
INSERT INTO `sys_role_menu` VALUES (2, 400);
INSERT INTO `sys_role_menu` VALUES (2, 500);
INSERT INTO `sys_role_menu` VALUES (2, 1003);
INSERT INTO `sys_role_menu` VALUES (2, 1004);
INSERT INTO `sys_role_menu` VALUES (2, 1005);
INSERT INTO `sys_role_menu` VALUES (2, 1006);
INSERT INTO `sys_role_menu` VALUES (2, 1007);
INSERT INTO `sys_role_menu` VALUES (2, 1008);
INSERT INTO `sys_role_menu` VALUES (2, 1009);
INSERT INTO `sys_role_menu` VALUES (2, 1010);
INSERT INTO `sys_role_menu` VALUES (2, 1011);
INSERT INTO `sys_role_menu` VALUES (2, 1012);
INSERT INTO `sys_role_menu` VALUES (3, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2);
INSERT INTO `sys_role_menu` VALUES (3, 4);
INSERT INTO `sys_role_menu` VALUES (3, 5);
INSERT INTO `sys_role_menu` VALUES (3, 100);
INSERT INTO `sys_role_menu` VALUES (3, 200);
INSERT INTO `sys_role_menu` VALUES (3, 400);
INSERT INTO `sys_role_menu` VALUES (3, 500);
INSERT INTO `sys_role_menu` VALUES (3, 1003);
INSERT INTO `sys_role_menu` VALUES (3, 1004);
INSERT INTO `sys_role_menu` VALUES (3, 1005);
INSERT INTO `sys_role_menu` VALUES (3, 1006);
INSERT INTO `sys_role_menu` VALUES (3, 1007);
INSERT INTO `sys_role_menu` VALUES (3, 1008);
INSERT INTO `sys_role_menu` VALUES (3, 1009);
INSERT INTO `sys_role_menu` VALUES (3, 1010);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'NULL' COMMENT '用户名',
  `nick_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'NULL' COMMENT '昵称',
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'NULL' COMMENT '密码',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '账号状态（0正常 1停用）',
  `email` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phonenumber` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号',
  `sex` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户性别（0男，1女，2未知）',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif' COMMENT '头像',
  `user_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '3' COMMENT '用户类型（1管理员，2高级用户，3普通用户）',
  `create_by` bigint NULL DEFAULT NULL COMMENT '创建人的用户id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `del_flag` int NULL DEFAULT 0 COMMENT '删除标志（0代表未删除，1代表已删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 44 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (17, '罗1某', 'luo', '$2a$10$WiZyPhnPMVDPzUEFqRgXReUcoBWN4ery6fuVMSUBon9yWotvaiBPq', '0', '4491@qq.com', '17875306076', '1', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '1', NULL, '2023-11-10 17:02:31', NULL, NULL, 0);
INSERT INTO `sys_user` VALUES (28, '罗9某', 'luo', '$2a$10$EgoQ0x/Hnc1Cm66VsMcecOt5t4sCxF./nd6LP82Vxf00LfXAP6OVe', '0', '4491@qq.com', '17875306076', '1', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '3', NULL, '2023-11-14 11:13:56', NULL, NULL, 0);
INSERT INTO `sys_user` VALUES (29, 'ceshi', 'luo', '$2a$10$pxbFSwcl0wqgreykpBJjre3EHzFy9PCxgkyWwHko7RBMrI0BtAaoO', '0', '4491@qq.com', '17875306076', '1', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '3', NULL, '2023-11-14 11:20:21', NULL, NULL, 0);
INSERT INTO `sys_user` VALUES (30, 'ceshi1', 'luo', '$2a$10$7Vt8ohTBkTnosv2nhlQfiulyvD3skfbvgnzmtiwgARkuspk9YLlSu', '0', '4491@qq.com', '17875306076', '1', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '3', NULL, '2023-11-14 11:37:11', NULL, NULL, 0);
INSERT INTO `sys_user` VALUES (32, 'ceshi22222233', 'luo', '$2a$10$0U4VhxcIiSA6HtgmKzDBgeyAaFRasztacLcBT96//rgnnyYSzRowm', '0', '4491@qq.com', '17875306076', '1', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '3', NULL, '2023-11-14 17:04:54', NULL, '2023-11-14 17:05:58', 0);
INSERT INTO `sys_user` VALUES (33, 'ceshi22222233', 'luo', '$2a$10$nMyzdjfM51zcdMrqQ6/SSubCelmHgcb2zpR7rUWblP4LCkxVtjsvi', '0', '4491@qq.com', '17875306076', '1', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '3', NULL, '2023-11-14 17:05:40', NULL, NULL, 0);
INSERT INTO `sys_user` VALUES (34, '罗某', 'luo', '$2a$10$7s7FKGAwMkZUsZ.eh3a8o.bAQ9o0PqJJew/rvweXenaQjSEc1Nl7m', '0', '4491@qq.com', '17875306076', '1', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '3', NULL, '2023-11-22 11:17:02', NULL, NULL, 0);
INSERT INTO `sys_user` VALUES (35, 'admin', 'luo', '$2a$10$wkGFtcwhuR7RIfi7t7ihaODAa3egg9Gq.hAgepgDK5THpoP3FVoKy', '0', '4491@qq.com', '17875306076', '1', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '1', NULL, '2023-11-23 10:11:33', NULL, NULL, 0);
INSERT INTO `sys_user` VALUES (36, 'test', 'luo', '$2a$10$3itcnIqgJez4uZ46fLxzHe4UOThhfSz/x//MxKCRfNaIu41Zb.L16', '0', '449186@qq.c', '123', '0', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '2', NULL, '2023-11-23 10:12:05', NULL, '2023-11-28 10:38:17', 0);
INSERT INTO `sys_user` VALUES (37, 'user', 'luo', '$2a$10$AjcXtPmnW.wywREUPxh0Y.bMMIjdsYZULG8DFIv1K7shkPLjbfznq', '0', '4491@qq.com', '17875306076', '0', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '3', NULL, '2023-11-23 10:12:16', NULL, NULL, 0);
INSERT INTO `sys_user` VALUES (38, '8888', '121212', '$2a$10$iYbPUu9Hs8krpXG6mZC33OzqW0CAIutN0NcpSXScd6tp5iHWcrduS', '0', '12345', '12345', '1', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '3', NULL, '2023-11-24 10:06:44', NULL, NULL, 0);
INSERT INTO `sys_user` VALUES (39, '12345', '12345', '$2a$10$kLoZXNL0zVc4eePzH83kU.2fOFk/iEFtivEWIb3hUi2SnyVPKsvx6', '0', '12345', '12345', '1', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '3', NULL, '2023-11-24 10:07:58', NULL, NULL, 0);
INSERT INTO `sys_user` VALUES (41, '123432', '123432', '$2a$10$qY0/UI/n819zCqjwMcaGv.PcMVyZ0/rz9M/LcFbamHFXq2BCUuz3m', '0', '123432', '123432', '0', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '3', NULL, '2023-11-28 15:08:03', NULL, NULL, 0);
INSERT INTO `sys_user` VALUES (42, 'NULL', 'NULL', '$2a$10$1peUMebNpQMwxINDcT2c7ezaF8txeBZ8FtRsd3CLugJsTK.GbEwhO', '0', '123', '123', NULL, 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '3', NULL, '2023-11-28 15:20:58', NULL, NULL, 0);
INSERT INTO `sys_user` VALUES (43, '456', 'NULL', '$2a$10$VlqgqCG1F23uHlo/5pkd7.feXGme2YMBP.GCMP1evaF4.8hQO2kUa', '0', '456', '456', NULL, 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '3', NULL, '2023-11-28 15:24:15', NULL, NULL, 0);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `role_id` bigint NOT NULL DEFAULT 0 COMMENT '角色id',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 44 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (28, 3);
INSERT INTO `sys_user_role` VALUES (29, 3);
INSERT INTO `sys_user_role` VALUES (30, 3);
INSERT INTO `sys_user_role` VALUES (32, 3);
INSERT INTO `sys_user_role` VALUES (33, 3);
INSERT INTO `sys_user_role` VALUES (34, 3);
INSERT INTO `sys_user_role` VALUES (35, 1);
INSERT INTO `sys_user_role` VALUES (36, 2);
INSERT INTO `sys_user_role` VALUES (37, 3);
INSERT INTO `sys_user_role` VALUES (38, 3);
INSERT INTO `sys_user_role` VALUES (39, 3);
INSERT INTO `sys_user_role` VALUES (41, 3);
INSERT INTO `sys_user_role` VALUES (42, 3);
INSERT INTO `sys_user_role` VALUES (43, 3);

-- ----------------------------
-- Triggers structure for table sys_user
-- ----------------------------
DROP TRIGGER IF EXISTS `sys_user_insert_trigger`;
delimiter ;;
CREATE TRIGGER `sys_user_insert_trigger` AFTER INSERT ON `sys_user` FOR EACH ROW BEGIN
    IF NEW.user_type IS NOT NULL THEN
        INSERT INTO sys_user_role (user_id, role_id) VALUES (NEW.id, NEW.user_type);
    END IF;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table sys_user
-- ----------------------------
DROP TRIGGER IF EXISTS `sys_user_update_trigger`;
delimiter ;;
CREATE TRIGGER `sys_user_update_trigger` AFTER UPDATE ON `sys_user` FOR EACH ROW BEGIN
    IF NEW.user_type <> OLD.user_type THEN -- 判断 user_type 字段是否发生变化
        UPDATE sys_user_role SET role_id = NEW.user_type WHERE user_id = OLD.id;
    END IF;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table sys_user
-- ----------------------------
DROP TRIGGER IF EXISTS `sys_user_delete_trigger`;
delimiter ;;
CREATE TRIGGER `sys_user_delete_trigger` AFTER DELETE ON `sys_user` FOR EACH ROW BEGIN
    DELETE FROM sys_user_role WHERE user_id = OLD.id;
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
