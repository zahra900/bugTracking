INSERT INTO ROLE(ID, ROLE_NAME)
  VALUES (1, 'ADMIN');


INSERT INTO AUTHORITY(ID, NAME)
  VALUES (1, 'ROLE_ADMIN');

  
INSERT INTO ROLES_AUTHORITIES(ROLE_ID, AUTHORITY_ID) VALUES (1, 1);


INSERT INTO USERS(ID, USER_NAME, PASSWORD, EMAIL, ROLE_ID)
  VALUES (1, 'admin', 'admin123', 'admin@gmail.com', 1);