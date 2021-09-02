package spring.sample.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.sample.app.entities.User;

import java.util.List;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {

    @Override
    List<User> findAll();

    User findByEmail(String email);
    User findByUsername(String username);
    User findById(long id);
    void delete(User user);
}
