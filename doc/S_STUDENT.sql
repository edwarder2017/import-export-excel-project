/*
Navicat Oracle Data Transfer
Oracle Client Version : 10.2.0.1.0

Source Server         : oracle
Source Server Version : 100200
Source Host           : 127.0.0.1:1521
Source Schema         : TEST

Target Server Type    : ORACLE
Target Server Version : 100200
File Encoding         : 65001

Date: 2018-01-10 11:28:40
*/


-- ----------------------------
-- Table structure for S_STUDENT
-- ----------------------------
DROP TABLE "TEST"."S_STUDENT";
CREATE TABLE "TEST"."S_STUDENT" (
"ID" NUMBER(11) NOT NULL ,
"NAME" VARCHAR2(11 BYTE) NULL ,
"AGE" NUMBER(11) NULL ,
"IDNUMBER" VARCHAR2(25 BYTE) NULL ,
"TEL" VARCHAR2(255 BYTE) NULL ,
"GENDER" VARCHAR2(10 BYTE) NULL ,
"SCHOOL" VARCHAR2(25 BYTE) NULL ,
"SPECIALITY" VARCHAR2(255 BYTE) NULL ,
"QUALIFICATION" VARCHAR2(255 BYTE) NULL ,
"DELFLAG" VARCHAR2(2 BYTE) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;
COMMENT ON COLUMN "TEST"."S_STUDENT"."SPECIALITY" IS '专业';

-- ----------------------------
-- Records of S_STUDENT
-- ----------------------------
INSERT INTO "TEST"."S_STUDENT" VALUES ('69', 'jack', '20', '342425199008068218', '15850012345', '男', '苏州大学', '计算机网络', '本科', '0');
INSERT INTO "TEST"."S_STUDENT" VALUES ('70', 'lili', '22', '342425199008068218', '13800000000', '女', '北京大学', '法学系', '硕士', '0');
INSERT INTO "TEST"."S_STUDENT" VALUES ('71', 'tom', '21', '342425199008068218', '15850012346', '男', '南京大学', '计算机科学与技术', '本科', '0');
INSERT INTO "TEST"."S_STUDENT" VALUES ('63', 'jack', '20', '342425199008068218', '15850012345', '男', '苏州大学', '计算机网络', '本科', '0');
INSERT INTO "TEST"."S_STUDENT" VALUES ('64', 'lili', '22', '342425199008068218', '13800000000', '女', '北京大学', '法学系', '硕士', '0');
INSERT INTO "TEST"."S_STUDENT" VALUES ('65', 'tom', '21', '342425199008068218', '15850012346', '男', '南京大学', '计算机科学与技术', '本科', '0');
INSERT INTO "TEST"."S_STUDENT" VALUES ('66', 'jack', '20', '342425199008068218', '15850012345', '男', '苏州大学', '计算机网络', '本科', '0');
INSERT INTO "TEST"."S_STUDENT" VALUES ('67', 'lili', '22', '342425199008068218', '13800000000', '女', '北京大学', '法学系', '硕士', '0');
INSERT INTO "TEST"."S_STUDENT" VALUES ('68', 'tom', '21', '342425199008068218', '15850012346', '男', '南京大学', '计算机科学与技术', '本科', '0');

-- ----------------------------
-- Indexes structure for table S_STUDENT
-- ----------------------------

-- ----------------------------
-- Checks structure for table S_STUDENT
-- ----------------------------
ALTER TABLE "TEST"."S_STUDENT" ADD CHECK ("ID" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table S_STUDENT
-- ----------------------------
ALTER TABLE "TEST"."S_STUDENT" ADD PRIMARY KEY ("ID");
