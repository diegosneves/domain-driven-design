-- init.sql
ALTER USER 'root'@'%' IDENTIFIED WITH mysql_native_password BY 'Teste@123';
ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'Teste@123';
FLUSH PRIVILEGES;