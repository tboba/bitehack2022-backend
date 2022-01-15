package pl.wilczadruzyna.biteshare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wilczadruzyna.biteshare.model.user.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findUserById(long id);

    List<User> findAll();

}
