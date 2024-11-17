CREATE TABLE student (
                            id int(10) unsigned NOT NULL,
                            first_name varchar(50),
                            last_name varchar(50),
                            age int(100),
                            sexuality enum('MALE', 'FEMALE', 'OTHER')
);

ALTER TABLE `staging`.`student`
MODIFY COLUMN
    `sexuality` enum('MALE', 'FEMALE', 'OTHER') not null;

update staging.student
set sexuality = upper(sexuality);