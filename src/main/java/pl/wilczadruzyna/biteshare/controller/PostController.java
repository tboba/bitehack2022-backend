package pl.wilczadruzyna.biteshare.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.maps.errors.ApiException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import pl.wilczadruzyna.biteshare.model.post.PostCategory;
import pl.wilczadruzyna.biteshare.model.user.User;
import pl.wilczadruzyna.biteshare.repository.LocationRepository;
import pl.wilczadruzyna.biteshare.repository.PostCategoryRepository;
import pl.wilczadruzyna.biteshare.repository.PostRepository;
import pl.wilczadruzyna.biteshare.repository.UserRepository;
import pl.wilczadruzyna.biteshare.service.LocationService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/api/posts")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class PostController {

    private final PostRepository postRepository;
    private final PostCategoryRepository postCategoryRepository;
    private final LocationRepository locationRepository;
    private final UserRepository userRepository;

    private final LocationService locationService;

    public PostController(PostRepository repository, PostCategoryRepository postCategoryRepository,
                          LocationRepository locationRepository, UserRepository userRepository, LocationService locationService) {
        this.postRepository = repository;
        this.postCategoryRepository = postCategoryRepository;
        this.locationRepository = locationRepository;
        this.userRepository = userRepository;
        this.locationService = locationService;
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Post>> getAll() {
        return ResponseEntity.ok(postRepository.findAll());
    }

    @GetMapping
    public ResponseEntity<Page<Post>> getAllPaginated(Pageable pageable) {
        return ResponseEntity.ok(postRepository.findAll(pageable));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Post> get(@PathVariable("id") long id) throws IOException, InterruptedException, ApiException {
        return ResponseEntity.ok(postRepository.getById(id));
    }

    @PostMapping
    public ResponseEntity<Post> add(@RequestBody JsonNode payload) throws IOException, InterruptedException, ApiException {
        Post post = postRepository.findPostByTitle(payload.get("title").asText());

        if (post == null) {
            post = new Post();
        } else {
            return ResponseEntity.badRequest().build();
        }

        if (payload.get("title") != null) {
            post.setTitle(payload.get("title").asText());
        }

        if (payload.get("description") != null) {
            post.setDescription(payload.get("description").asText());
        }

        if (payload.get("creationDate") != null) {
            post.setCreationDate(LocalDateTime.parse(payload.get("creationDate").asText()));
        }

        if (payload.get("expiryDate") != null) {
            post.setExpiryDate(LocalDateTime.parse(payload.get("expiryDate").asText()));
        }

        if (payload.get("imageUrl") != null) {
            post.setImageUrl(payload.get("imageUrl").asText());
        }

        if (payload.get("postCategory") != null && payload.get("postCategory").get("name") != null) {
            PostCategory postCategory = postCategoryRepository.findPostCategoryByName(payload.get("postCategory").get("name").asText());
            if (postCategory != null) {
                post.setPostCategory(postCategory);
            }
        }

        if (payload.get("author") != null && payload.get("author").get("name") != null) {
            User author = userRepository.findUserByName(payload.get("author").get("name").asText());
            if (author != null) {
                post.setAuthor(author);
            }
        }

        if (payload.get("location") != null && payload.get("location").get("latitude") != null
                && payload.get("location").get("longitude") != null) {
            Location location = locationRepository.findLocationByLatitudeAndLongitude(payload.get("location").get("latitude").asDouble(),
                    payload.get("location").get("longitude").asDouble());

            if (location == null) {
                location = new Location();
                location.setLatitude(payload.get("location").get("latitude").asDouble());
                location.setLongitude(payload.get("location").get("longitude").asDouble());
                locationRepository.save(location);
            }

            post.setLocation(location);
        }

        Location location = post.getLocation();
        String decodedCity = locationService.findExactCityFrom(location.getLatitude(), location.getLongitude());
        location.setCity(decodedCity);

        return ResponseEntity.ok(postRepository.save(post));
    }

    @PatchMapping
    public ResponseEntity<Post> patch(@RequestBody JsonNode payload) throws IOException, InterruptedException, ApiException {
        Post post = postRepository.findPostByTitle(payload.get("title").asText());

        if (post == null) {
            return ResponseEntity.badRequest().build();
        }

        if (payload.get("title") != null) {
            post.setTitle(payload.get("title").asText());
        }

        if (payload.get("description") != null) {
            post.setDescription(payload.get("description").asText());
        }

        if (payload.get("creationDate") != null) {
            post.setCreationDate(LocalDateTime.parse(payload.get("creationDate").asText()));
        }

        if (payload.get("expiryDate") != null) {
            post.setExpiryDate(LocalDateTime.parse(payload.get("expiryDate").asText()));
        }

        if (payload.get("imageUrl") != null) {
            post.setImageUrl(payload.get("imageUrl").asText());
        }

        if (payload.get("postCategory") != null && payload.get("postCategory").get("name") != null) {
            PostCategory postCategory = postCategoryRepository.findPostCategoryByName(payload.get("postCategory").get("name").asText());
            if (postCategory != null) {
                post.setPostCategory(postCategory);
            }
        }

        if (payload.get("author") != null && payload.get("author").get("name") != null) {
            User author = userRepository.findUserByName(payload.get("author").get("name").asText());
            if (author != null) {
                post.setAuthor(author);
            }
        }

        if (payload.get("location") != null && payload.get("location").get("latitude") != null
                && payload.get("location").get("longitude") != null) {
            Location location = locationRepository.findLocationByLatitudeAndLongitude(payload.get("location").get("latitude").asDouble(),
                    payload.get("location").get("longitude").asDouble());

            if (location == null) {
                location = new Location();
                location.setLatitude(payload.get("location").get("latitude").asDouble());
                location.setLongitude(payload.get("location").get("longitude").asDouble());
                locationRepository.save(location);
            }

            post.setLocation(location);
        }

        Location location = post.getLocation();
        String decodedCity = locationService.findExactCityFrom(location.getLatitude(), location.getLongitude());
        location.setCity(decodedCity);

        return ResponseEntity.ok(postRepository.save(post));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        postRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAll() {
        postRepository.deleteAll();
        return ResponseEntity.ok().build();
    }

    @GetMapping(params = {"category", "!author", "!name"})
    public ResponseEntity<Page<Post>> findPaginated(Long category, Pageable pageable) {
        return ResponseEntity.ok(postRepository.findPostsByPostCategoryId(category, pageable));
    }

    @GetMapping(params = {"!category", "author", "!name"})
    public ResponseEntity<Page<Post>> findByAuthor(Long author, Pageable pageable) {
        return ResponseEntity.ok(postRepository.findPostsByAuthorId(author, pageable));
    }

    @GetMapping(params = {"category", "author", "!name"})
    public ResponseEntity<Page<Post>> findByAuthorAndCategory(Long category, Long author, Pageable pageable) {
        return ResponseEntity.ok(postRepository.findPostsByAuthorIdAndPostCategoryId(author, category, pageable));
    }

    @GetMapping(params = {"!category", "!author", "name"})
    public ResponseEntity<Page<Post>> getByName(String name, Pageable pageable) {
        return ResponseEntity.ok(postRepository.findPostsByTitleContaining(name, pageable));
    }

    @GetMapping(params = {"!category", "author", "name"})
    public ResponseEntity<Page<Post>> getByNameAndAuthor(Long author, String name, Pageable pageable) {
        return ResponseEntity.ok(postRepository.findPostsByAuthorIdAndTitleContaining(author, name, pageable));
    }

    @GetMapping(params = {"category", "!author", "name"})
    public ResponseEntity<Page<Post>> getByNameAndCategory(Long category, String name, Pageable pageable) {
        return ResponseEntity.ok(postRepository.findPostsByPostCategoryIdAndTitleContaining(category, name, pageable));
    }

    @GetMapping(params = {"category", "author", "name"})
    public ResponseEntity<Page<Post>> getByNameAndCategoryAndAuthor(Long category, Long author, String name, Pageable pageable) {
        return ResponseEntity.ok(postRepository.findPostsByPostCategoryIdAndAuthorIdAndTitleContaining(category, author, name, pageable));
    }
}
