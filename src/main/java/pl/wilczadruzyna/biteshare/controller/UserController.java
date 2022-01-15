package pl.wilczadruzyna.biteshare.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") long id) {
        return ResponseEntity.ok(repository.findUserById(id));
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping(value = "/custom")
    public ResponseEntity<Page<User>> getUsersPaginated(Pageable pageable) {
        return ResponseEntity.ok(repository.findAll(pageable));
    }

    @PostMapping(value = "/add")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        return ResponseEntity.ok(repository.save(user));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteAll(@PathVariable("id") long id) {
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/all")
    public ResponseEntity<?> deleteAll() {
        repository.deleteAll();
        return ResponseEntity.ok().build();
    }

}
