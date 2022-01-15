package pl.wilczadruzyna.biteshare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import pl.wilczadruzyna.biteshare.model.post.PostCategory;

public interface PostCategoryRepository extends JpaRepository<PostCategory, Long> {

    PostCategory findPostCategoryByName(@Param("name") String name);

}
