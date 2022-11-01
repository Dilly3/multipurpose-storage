
CREATE TABLE IF NOT EXISTS IUSER (
  userId SERIAL  ,
  username VARCHAR(30) NOT NULL,
  salt VARCHAR(512),
  password VARCHAR(512),
  firstname VARCHAR(250),
  lastname VARCHAR(250),
PRIMARY KEY (userId)

);

--ALTER TABLE IUSER
--ADD CONSTRAINT Iuser_unique_column UNIQUE (userId);

CREATE TABLE IF NOT EXISTS FILES (
  fileId serial,
  filename VARCHAR(250),
  contenttype VARCHAR(250),
  filesize VARCHAR(250),
  userId INT,
  filedata BYTEA,
PRIMARY KEY (fileId),
foreign key (userId) references IUSER(userId)
);

CREATE TABLE IF NOT EXISTS NOTES (
  noteId serial,
  notetitle VARCHAR(20),
  notedescription VARCHAR (1000),
  userId INT,
PRIMARY KEY (noteId),
foreign key (userId) references IUSER(userId)
);

CREATE TABLE IF NOT EXISTS CREDENTIALS (
  credentialId SERIAL,
  url VARCHAR(150),
  username VARCHAR (30),
  credKey VARCHAR(256),
  password VARCHAR(512),
  userid INT,
PRIMARY KEY (credentialId),
foreign key (userId) references IUSER(userId)
);