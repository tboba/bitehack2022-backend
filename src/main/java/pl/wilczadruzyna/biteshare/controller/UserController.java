package pl.wilczadruzyna.biteshare.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.wilczadruzyna.biteshare.model.user.User;
import pl.wilczadruzyna.biteshare.repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {

    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") long id) {
        return ResponseEntity.ok(repository.findUserById(id));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(repository.findAll());
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/add/{user}")
    public ResponseEntity<User> addUser(@PathVariable("user") User user) {
        return ResponseEntity.ok(repository.save(user));
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/deleteAll")
    public ResponseEntity<?> deleteAll() {
        repository.deleteAll();
        return ResponseEntity.ok().build();
    }

}
