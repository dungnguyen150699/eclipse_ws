package dungnt_ptit.com.securitystandard.repository;

import dungnt_ptit.com.securitystandard.pojo.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail,Long> {
}
