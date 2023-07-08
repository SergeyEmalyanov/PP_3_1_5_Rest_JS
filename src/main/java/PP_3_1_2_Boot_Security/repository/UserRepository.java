package PP_3_1_2_Boot_Security.repository;

import PP_3_1_2_Boot_Security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByName(String name);
}