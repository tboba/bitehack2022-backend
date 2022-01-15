package pl.wilczadruzyna.biteshare.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import pl.wilczadruzyna.biteshare.model.post.Post;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    Post findPostById(@Param("id") Long id);

    List<Post> findAll();

    public Page<Post> findAll(Pageable pageable);
}