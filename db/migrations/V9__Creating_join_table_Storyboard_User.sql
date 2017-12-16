CREATE TABLE storyboard_user (
  storyboard_id int NOT NULL,
  user_id int NOT NULL,
  PRIMARY KEY (storyboard_id,user_id),
  CONSTRAINT storyboard_user_fk_1
  FOREIGN KEY (storyboard_id) REFERENCES storyboard_details (id),
  CONSTRAINT storyboard_user_fk_2
  FOREIGN KEY (user_id) REFERENCES user_details (id)
);