ALTER TABLE user_details ADD COLUMN email VARCHAR(255) NOT NULL,
                         ADD COLUMN  salt VARCHAR(255),
                         ADD COLUMN passwd_hash VARCHAR(255);