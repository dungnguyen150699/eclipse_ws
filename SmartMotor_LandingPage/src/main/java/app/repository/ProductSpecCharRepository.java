package vn.viettel.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.viettel.app.model.entity.ProductSpecChar;

@Repository
interface ProductSpecCharRepository extends JpaRepository<ProductSpecChar, Integer> {
}
