/*------------------------CREATE TABLES------------------------*/
CREATE TABLE "role" (
	id INTEGER NOT NULL PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	description VARCHAR(250) NULL
);

CREATE TABLE user_account (
  id SERIAL NOT NULL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  username VARCHAR(255) NOT NULL,
  password VARCHAR(100) NOT NULL,
  idrole INTEGER NOT NULL,
  deleted BOOLEAN NOT NULL DEFAULT FALSE,
  CONSTRAINT user_role_FK FOREIGN KEY (idrole) REFERENCES "role" (id)
);

CREATE TABLE permission (
	id SERIAL NOT NULL PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	description VARCHAR(250) NULL,
	deleted BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE role_permission (
	idrole INTEGER NOT NULL,
	idpermission INTEGER NOT NULL,
	PRIMARY KEY (idrole, idpermission),
	CONSTRAINT role_permission_role_FK FOREIGN KEY (idrole) REFERENCES "role" (id),
	CONSTRAINT role_permission_permission_FK FOREIGN KEY (idpermission) REFERENCES permission (id)
);

/*------------------------INSERT VALUES------------------------*/

INSERT INTO "role" (id, name, description) VALUES (1, 'Super User', 'Role for a super user that allows all operations in the system.');

INSERT INTO user_account (name,username,password,idrole,deleted) VALUES ('Administrador','demo@virtus.ufcg.edu.br','4f26aeafdb2367620a393c973eddbe8f8b846ebd', 1, FALSE);

INSERT INTO permission (id, name, description) VALUES (1, 'read-*', 'Read all items.'), (2, 'insert-*', 'Insert any item.'), (3, 'update-*', 'Update any item.'), (4, 'delete-*', 'Delete any item.'), (5, 'associate-*', 'Associate any item to other item');

INSERT INTO role_permission (idrole, idpermission) VALUES (1, 1), (1, 2), (1, 3), (1, 4), (1, 5);