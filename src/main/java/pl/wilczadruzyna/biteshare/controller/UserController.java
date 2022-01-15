package pl.wilczadruzyna.biteshare.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wilczadruzyna.biteshare.model.user.User;
import pl.wilczadruzyna.biteshare.repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping(value = "/api/users")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class UserController {

    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping
    public ResponseEntity<Page<User>> getAllPaginated(Pageable pageable) {
        return ResponseEntity.ok(repository.findAll(pageable));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> get(@PathVariable("id") long id) {
        return ResponseEntity.ok(repository.findUserById(id));
    }

    @PostMapping
    public ResponseEntity<User> add(@RequestBody User user) {
        return ResponseEntity.ok(repository.save(user));
    }

    @PostMapping(value = "/{id}", params = "rate")
    public ResponseEntity<User> addRating(@PathVariable("id") Long id, Integer rate) {
        User u = repository.findUserById(id);
        u.addRating(rate);
        repository.save(u);
        return ResponseEntity.ok(u);
    }

    @PatchMapping
    public ResponseEntity<User> patch(@RequestBody User user) {
        return ResponseEntity.ok(repository.save(user));
    }



    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAll() {
        repository.deleteAll();
        return ResponseEntity.ok().build();
    }



}
