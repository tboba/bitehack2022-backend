package pl.wilczadruzyna.biteshare.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import pl.wilczadruzyna.biteshare.model.post.Post;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    Post findPostById(@Param("id") Long id);

    Post findPostByTitle(@Param("title") String title);

    List<Post> findAll();

    Page<Post> findAll(Pageable pageable);

    Page<Post> findPostsByPostCategoryId(Long id, Pageable pageable);

    Page<Post> findPostsByAuthorId(Long id, Pageable pageable);

    Page<Post> findPostsByAuthorIdAndPostCategoryId(Long authorId, Long categoryId, Pageable pageable);

//    Page<Post> (String startsWith);
    Page<Post> findPostsByTitleContaining(String title, Pageable pageable);
    Page<Post> findPostsByAuthorIdAndTitleContaining(Long authorId, String title, Pageable pageable);
    Page<Post> findPostsByPostCategoryIdAndTitleContaining(Long categoryId, String title, Pageable pageable);
    Page<Post> findPostsByPostCategoryIdAndAuthorIdAndTitleContaining(Long categoryId, Long authorId, String title, Pageable pageable);
}
