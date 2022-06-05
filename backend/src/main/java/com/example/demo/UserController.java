package com.example.demo;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("api/user")
public class UserController {
    @Autowired private UserRepository repo;

    @AllArgsConstructor
    static private class Response {
        public String msg;
        public Optional<User> user;

        static public Response error             () { return new Response("BAD", Optional.empty()); }
        static public Response success (User user)  { return new Response("OK", Optional.of(user)); }
        static public Response userNotFound      () { return new Response("Account with that username doesn't exist", Optional.empty()); }
        static public Response userAlreadyExists () { return new Response("Account with that username already exists", Optional.empty()); }
        static public Response emptyLogin        () { return new Response("Login can't be empty", Optional.empty()); }
        static public Response emptyPassword     () { return new Response("Password can't be empty", Optional.empty()); }
        static public Response wrongPassword     () { return new Response("Incorrect password", Optional.empty()); }
    };

    @AllArgsConstructor
    static private class Credentials {
        public String login;
        public String password;

        public Boolean areWrong () {
            return login.isEmpty() || password.isEmpty();
        }

        public Response getErrorResponse () {
            if (login.isEmpty())    return Response.emptyLogin();
            if (password.isEmpty()) return Response.emptyPassword();
            return Response.error();
        }
    }

    static private String encodePassword (String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    static private Boolean userPasswordMatches (User user, String password) {
        return new BCryptPasswordEncoder().matches(password, user.getPassword());
    }

    /*
     *
     * Mappings
     *
     */

    @GetMapping public List<User> getUsers () {
        return repo.findAll();
    }

    @DeleteMapping public void deleteUsers () {
        repo.deleteAll();
    }

    @GetMapping("/{id}")
    Response getUser (@PathVariable Long id) {
        Optional<User> user = repo.findById(id);
        if (user.isPresent())
            return Response.success(user.get());
        return Response.userNotFound();
    }

    @PostMapping("/register")
    Response registerUser (@RequestBody Credentials cred) {
        if (cred.areWrong())
            return cred.getErrorResponse();

        if (!repo.findByLogin(cred.login).isEmpty())
            return Response.userAlreadyExists();

        User user = new User(cred.login, encodePassword(cred.password));
        repo.save(user);
        return Response.success(user);
    }

    @PostMapping("/login")
    Response loginUser (@RequestBody Credentials cred) {
        if (cred.areWrong())
            return cred.getErrorResponse();

        List<User> users = repo.findByLogin(cred.login);
        if (users.isEmpty())
            return Response.userNotFound();

        User user = users.get(0); // since each login is unique
        if (!userPasswordMatches(user, cred.password))
            return Response.wrongPassword();

        return Response.success(user);
    }

    @DeleteMapping("/delete")
    public Response deleteUser (@RequestBody Credentials cred) {
        Response res = loginUser(cred);
        if (!res.user.isPresent()) // error trying to log in, return original response
            return res;
        repo.delete(res.user.get());
        return res;
    }
}
