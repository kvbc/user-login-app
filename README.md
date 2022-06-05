# user-login-app

The .css is 99.9% stolen

### Spring dependencies
- Spring Web
- Spring Security
- Spring Data JPA
- MySQL Driver
- Lombok

### SQL Table
```sql
CREATE TABLE users(
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    login VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);
```

### REST API

Retrieve the information of user of ID **MY_ID**  
`curl -X GET http://localhost:8080/api/user/MY_ID`

Retrieve the list of all registered users  
`curl -X GET http://localhost:8080/api/user`

Delete all registered users  
`curl -X DELETE http://localhost:8080/api/user`

Register user **MY_LOGIN** with password **MY_PASSWORD**  
`curl -X POST -H "Content-type: application/json" -d "{\"login\":\"MY_LOGIN\",\"password\":\"MY_PASSWORD\"}" http://localhost:8080/api/user/register`

Login as user **MY_LOGIN** with password **MY_PASSWORD**  
`curl -X POST -H "Content-type: application/json" -d "{\"login\":\"MY_LOGIN\",\"password\":\"MY_PASSWORD\"}" http://localhost:8080/api/user/login`

Delete user **MY_LOGIN** with password **MY_PASSWORD**  
`curl -X POST -H "Content-type: application/json" -d "{\"login\":\"MY_LOGIN\",\"password\":\"MY_PASSWORD\"}" http://localhost:8080/api/user/delete`
