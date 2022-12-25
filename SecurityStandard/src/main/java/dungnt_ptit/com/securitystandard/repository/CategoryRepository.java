package dungnt_ptit.com.securitystandard.repository;

import dungnt_ptit.com.securitystandard.pojo.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
}
