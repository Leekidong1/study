CREATE TABLE REVIEW(
    REVIEW_SEQ NUMBER(5) NOT NULL,
    GROUPS_SEQ NUMBER(5) NOT NULL,
    ID VARCHAR2(50) NOT NULL,
    TITLE VARCHAR2(100) NOT NULL,
    WDATE DATE NOT NULL,
    SCORE NUMBER(1) NOT NULL,
    CONTENT VARCHAR2(2000) NOT NULL,
    DEL NUMBER(1),
    CONSTRAINT PK_REVIEW PRIMARY KEY(REVIEW_SEQ)
);

DROP TABLE REVIEW
CASCADE CONSTRAINTS;

CREATE SEQUENCE SEQ_REVIEW
START WITH 1
INCREMENT BY 1;

DROP SEQUENCE SEQ_REVIEW;

ALTER TABLE REVIEW
ADD CONSTRAINT FK_REVIEW_ID FOREIGN KEY(ID)
REFERENCES MEMBER(ID);

ALTER TABLE REVIEW
ADD CONSTRAINT FK_REVIEW_SEQ FOREIGN KEY(GROUPS_SEQ)
REFERENCES GROUPS(GROUPS_SEQ);

ALTER TABLE REVIEW DROP CONSTRAINT ID;

SELECT * FROM  REVIEW;

SELECT ROUND(AVG(SCORE))
FROM REVIEW
WHERE GROUPS_SEQ=2;
