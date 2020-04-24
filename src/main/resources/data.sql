DROP TABLE IF EXISTS User;

CREATE TABLE User (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_Name VARCHAR(250) NOT NULL,
  last_Name VARCHAR(250) NOT NULL,
  zip  VARCHAR(13) NOT NULL
);

/*INSERT INTO User (first_name, last_name, zip) VALUES
  ('Aliko', 'Dangote', '84129'),
  ('Bill', 'Gates', '84129'),
  ('Folrunsho', 'Alakija', '84129');*/
