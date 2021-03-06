# user-login-app

![Przechwytywanie](https://user-images.githubusercontent.com/64214044/172055916-8e28d59b-d298-45f6-91b6-3ba81eae38f5.JPG)

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

Retrieve the information of user with the ID of **MY_ID**  
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
