package dungnt_ptit.com.securitystandard.repository;

import dungnt_ptit.com.securitystandard.pojo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT u FROM User u WHERE u.username = :username")
    Optional<User> loadUserByName(@Param("username") String username);
}
