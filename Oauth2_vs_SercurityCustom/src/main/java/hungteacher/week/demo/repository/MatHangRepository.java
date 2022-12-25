package hungteacher.week.demo.repository;

import hungteacher.week.demo.entity.MatHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatHangRepository extends JpaRepository<MatHang , Long> {
}
