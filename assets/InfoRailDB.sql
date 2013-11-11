DROP TABLE IF EXISTS "answer";
CREATE TABLE "answer" ("id" INTEGER PRIMARY KEY  NOT NULL ,"textStringId" TEXT NOT NULL  DEFAULT (null), "question_id" INTEGER , "isCorrect" BOOL , FOREIGN KEY("question_id") REFERENCES question("id"));
INSERT INTO "answer" VALUES(1,'Steam',1,1);
INSERT INTO "answer" VALUES(2,'Coal',1,0);
INSERT INTO "answer" VALUES(3,'Electricity',1,0);
INSERT INTO "answer" VALUES(4,'an incorrect answer',2,0);
INSERT INTO "answer" VALUES(5,'another incorrect answer',2,0);
INSERT INTO "answer" VALUES(6,'the correct answer!',2,1);
INSERT INTO "answer" VALUES(7,'Pizza, Pie, Pinapple',3,0);
INSERT INTO "answer" VALUES(8,'Papaya, Paella, Panzarotti',3,0);
INSERT INTO "answer" VALUES(9,'Product, Price, Promotion, Product, Price, Promotion, Place',3,1);
INSERT INTO "answer" VALUES(10,'You',4,0);
INSERT INTO "answer" VALUES(11,'Him',4,0);
INSERT INTO "answer" VALUES(12,'Me',4,1);
DROP TABLE IF EXISTS "question";
CREATE TABLE "question" ("id" INTEGER PRIMARY KEY  NOT NULL ,"train_id" INTEGER , "textStringId" TEXT DEFAULT (null) ,"questionType_id" INTEGER, FOREIGN KEY("train_id") REFERENCES 
train("id") , FOREIGN KEY("questionType_id") REFERENCES questionType("id"));
INSERT INTO "question" VALUES(1,1,'What powers this train?',1);
INSERT INTO "question" VALUES(2,1,'What is the meaning of life?',1);
INSERT INTO "question" VALUES(3,1,'What are the 4 P''s?',1);
INSERT INTO "question" VALUES(4,1,'Who decided to leave studying to the last minute?',1);
DROP TABLE IF EXISTS "questionType";
CREATE TABLE "questionType" ("id" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , "type" TEXT);
INSERT INTO "questionType" VALUES(1,'multipleChoice
multipleChoice');
INSERT INTO "questionType" VALUES(2,'dragAndDrop');
DROP TABLE IF EXISTS "train";
CREATE TABLE "train" ("id" INTEGER PRIMARY KEY  NOT NULL ,"nameStringId" TEXT DEFAULT (null) ,"iconUrl" TEXT,"imageUrl" TEXT,"trainPart_id" INTEGER , FOREIGN KEY("trainPart_id") REFERENCES trainPart

("id"));
INSERT INTO "train" VALUES(1,'Train1',NULL,NULL,NULL);
INSERT INTO "train" VALUES(2,'Train2',NULL,NULL,NULL);
INSERT INTO "train" VALUES(3,'Train3',NULL,NULL,NULL);
INSERT INTO "train" VALUES(4,'Train4
Train4',NULL,NULL,NULL);
DROP TABLE IF EXISTS "trainInfo";
CREATE TABLE "trainInfo" ("id" INTEGER PRIMARY KEY  NOT NULL ,"train_id" INTEGER , "textStringId" TEXT DEFAULT (null) ,"imageUrl" TEXT,"xPosition" FLOAT,"yPosition" FLOAT , FOREIGN KEY("train_id") 

REFERENCES train("id"));
DROP TABLE IF EXISTS "trainPart";
CREATE TABLE "trainPart" ("id" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , "trainPartType_id" INTEGER , "imageUrl" TEXT, "won" BOOL , FOREIGN KEY("trainPartType_id") REFERENCES trainPartType("id"));
DROP TABLE IF EXISTS "trainPartType";
CREATE TABLE "trainPartType" ("id" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , "type" TEXT);
