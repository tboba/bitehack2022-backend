package pl.wilczadruzyna.biteshare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import pl.wilczadruzyna.biteshare.model.user.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findUserById(long id);

    User findUserByName(@Param("name") String name);

    List<User> findAll();

}
