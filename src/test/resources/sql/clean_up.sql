set foreign_key_checks = 0;
truncate table cat_images;
alter table cat_images auto_increment = 1;

truncate table cat_breeds;
alter table cat_breeds auto_increment = 1;
set foreign_key_checks  = 1;