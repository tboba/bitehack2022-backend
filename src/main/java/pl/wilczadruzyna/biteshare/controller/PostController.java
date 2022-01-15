package pl.wilczadruzyna.biteshare.controller;

import com.google.maps.errors.ApiException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.wilczadruzyna.biteshare.model.post.Location;
import pl.wilczadruzyna.biteshare.model.post.Post;
import pl.wilczadruzyna.biteshare.model.post.PostWithLocation;
import pl.wilczadruzyna.biteshare.repository.PostRepository;
import pl.wilczadruzyna.biteshare.service.LocationService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/posts")
public class PostController {

    private final PostRepository repository;
    private final LocationService locationService;

    public PostController(PostRepository repository, LocationService locationService) {
        this.repository = repository;
        this.locationService = locationService;
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<PostWithLocation>> getAll() {
        List<PostWithLocation> posts = repository.findAll().stream().map(post -> {
            try {
                return getPostWithDecodedLocation(post);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            return null;
        }).toList();

        return ResponseEntity.ok(posts);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PostWithLocation> get(@PathVariable("id") long id) throws IOException, InterruptedException, ApiException {
        return ResponseEntity.ok(getPostWithDecodedLocation(repository.getById(id)));
    }

    @PostMapping
    public ResponseEntity<Post> add(@RequestBody Post post) {
        return ResponseEntity.ok(repository.save(post));
    }

    @PatchMapping
    public ResponseEntity<Post> patch(Post post) {
        return ResponseEntity.ok(repository.save(post));
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

    @GetMapping(params = {"category", "!author"})
    public ResponseEntity<Page<Post>> findPaginated(Long category, Pageable pageable) {
        return ResponseEntity.ok(repository.findPostsByPostCategoryId(category, pageable));
    }

    @GetMapping(params = {"!category", "author"})
    public ResponseEntity<Page<Post>> findByAuthor(Long author, Pageable pageable) {
        return ResponseEntity.ok(repository.findPostsByAuthorId(author, pageable));
    }

    @GetMapping(params = {"category", "author"})
    public ResponseEntity<Page<Post>> findByAuthorAndCategory(Long category, Long author, Pageable pageable) {
        return ResponseEntity.ok(repository.findPostsByAuthorIdAndPostCategoryId(author, category, pageable));
    }

    private PostWithLocation getPostWithDecodedLocation(Post post) throws IOException, InterruptedException, ApiException {
        Location location = post.getLocation();
        String decodedLocation = locationService.findExactCityFrom(location.getLatitude(), location.getLongitude());
        return PostWithLocation.of(post, decodedLocation);
    }
}
