package pl.wilczadruzyna.biteshare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.wilczadruzyna.biteshare.model.post.Post;
import pl.wilczadruzyna.biteshare.repository.PostRepository;

@RestController
@RequestMapping(value = "/api/posts")
public class PostController {

    private final PostRepository repository;

    @Autowired
    public PostController(PostRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<Page<Post>> findPaginated(Pageable pageable) {
        return ResponseEntity.ok(repository.findAll(pageable));
    }
}
