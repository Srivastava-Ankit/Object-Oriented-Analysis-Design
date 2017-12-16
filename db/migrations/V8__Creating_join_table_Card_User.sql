CREATE TABLE card_user (
  card_id int NOT NULL,
  user_id int NOT NULL,
  PRIMARY KEY (card_id,user_id),
  CONSTRAINT card_user_fk_1
  FOREIGN KEY (card_id) REFERENCES card (id),
  CONSTRAINT card_user_fk_2
  FOREIGN KEY (user_id) REFERENCES user_details (id)
);