-- 내 스키마2
DROP SCHEMA IF EXISTS student;

-- 내 스키마2
CREATE SCHEMA student;

-- 학생
CREATE TABLE student.Student (
	stdno   INT         NOT NULL COMMENT '학생번호', -- 학생번호
	stdname VARCHAR(20) NULL     COMMENT '학생이름', -- 학생이름
	kor     INT         NULL     COMMENT '국어점수', -- 국어점수
	eng     INT         NULL     COMMENT '영어점수', -- 영어점수
	math    INT         NULL     COMMENT '수학' -- 수학
)
COMMENT '학생';

-- 학생
ALTER TABLE student.Student
	ADD CONSTRAINT PK_Student -- 학생 기본키
		PRIMARY KEY (
			stdno -- 학생번호
		);