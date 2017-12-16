CREATE TABLE swimlane (
  ID SERIAL PRIMARY KEY,
  NAME VARCHAR(255) NOT NULL,
  STORYBOARD_ID INTEGER NOT NULL,
  CONSTRAINT swimlanes_storyboard_fk_1 FOREIGN KEY (storyboard_id) REFERENCES storyboard_details (id)
);