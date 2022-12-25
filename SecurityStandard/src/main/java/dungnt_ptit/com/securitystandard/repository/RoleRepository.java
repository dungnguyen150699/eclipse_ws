package dungnt_ptit.com.securitystandard.repository;

import dungnt_ptit.com.securitystandard.pojo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
}
