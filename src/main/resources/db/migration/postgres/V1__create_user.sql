CREATE TABLE IF NOT EXISTS ROLE(
   ID  BIGSERIAL NOT NULL,
   ROLE_NAME VARCHAR(255),
   PRIMARY KEY (ID),
   UNIQUE (ROLE_NAME)
);


CREATE TABLE IF NOT EXISTS AUTHORITY(
   ID  BIGSERIAL NOT NULL,
   NAME VARCHAR(255),
   PRIMARY KEY (ID)
);


CREATE TABLE IF NOT EXISTS ROLES_AUTHORITIES (
   ROLE_ID INT8 NOT NULL,
   AUTHORITY_ID INT8 NOT NULL,
   PRIMARY KEY (ROLE_ID, AUTHORITY_ID),
   CONSTRAINT fk_role_id FOREIGN KEY (ROLE_ID) REFERENCES ROLE(ID) ON UPDATE CASCADE,
   CONSTRAINT fk_authority_id FOREIGN KEY (AUTHORITY_ID) REFERENCES AUTHORITY(ID) ON UPDATE CASCADE
);


CREATE TABLE USERS(
   ID  BIGSERIAL NOT NULL,
   PASSWORD VARCHAR(255),
   USER_NAME VARCHAR(255),
   EMAIL VARCHAR(255),
   ROLE_ID int NOT NULL,
   PRIMARY KEY (ID),
   FOREIGN KEY (ROLE_ID) REFERENCES ROLE(id) ON DELETE CASCADE
);



