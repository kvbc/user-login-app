# user-login-app

The .css is 99.9% stolen

Spring dependencies
- Spring Web
- Spring Security
- Spring Data JPA
- MySQL Driver
- Lombok

SQL Table
```sql
CREATE TABLE users(
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    login VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);
```
