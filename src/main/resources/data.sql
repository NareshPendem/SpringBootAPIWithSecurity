DROP TABLE IF EXISTS User;

CREATE TABLE User (
  id INT PRIMARY KEY,
  user_name VARCHAR(250) NOT NULL,
  password VARCHAR(250) NOT NULL,
  active BOOLEAN,
  roles VARCHAR(250) NOT NULL

);

INSERT INTO User (id, user_name, password, active,roles) VALUES
  ('123456','pendem', '{bcrypt}$2a$10$GjWjoze29ErXfEEQS2Wz.ONvqZPXf0C807jaDx0LrOE0YXg4GAjPi', true,'USER'),-- bcrypt password : pendem
  ('385204','admin', '{bcrypt}$2a$10$2kqPiC91G6F0p319.pH7J.yk9SxwvLZ85RBrshg39rz9qWf1MF2ye', true,'ADMIN'); -- admin