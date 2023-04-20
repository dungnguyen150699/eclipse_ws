package dungnt.ptit.receipt.repository;

import dungnt.ptit.receipt.pojo.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}