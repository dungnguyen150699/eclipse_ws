package dungnt_ptit.com.securitystandard.repository;

import dungnt_ptit.com.securitystandard.pojo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
}
