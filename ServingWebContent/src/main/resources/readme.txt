mysql> create database db_example; -- Create the new database
mysql> create user 'springuser'@'%' identified by 'ThePassword'; -- Creates the user
mysql> grant all on db_example.* to 'springuser'@'%'; -- Gives all the privileges to the new user on the newly created database

-- Перед сдавчей данного проекта заказчику необходимо изменить права доступа --

mysql> revoke all on db_example.* from 'springuser'@'%';
mysql> grant select, insert, delete, update on db_example.* to 'springuser'@'%';
spring.jpa.hibernate.ddl-auto=none

-- Если вы хотите внести изменения в базе данных, regrant разрешения, изменить spring.jpa.hibernate.ddl-autoк update,
а затем повторно запускать приложения, а затем повторите. Или, лучше, используйте специальный инструмент миграции,
такой как Flyway или Liquibase. --