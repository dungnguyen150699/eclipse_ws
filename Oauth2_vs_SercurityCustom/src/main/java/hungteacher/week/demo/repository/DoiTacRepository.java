package hungteacher.week.demo.repository;

import hungteacher.week.demo.entity.DoiTac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoiTacRepository extends JpaRepository<DoiTac, Long> {
    @Query(value = "select * from DoiTac dt where "
            + "(:id is null or dt.id = :id) "
            + "and (:diaChi is null or b.diaChi like %:diaChi%) "
            + "and (:maSoThue is null or b.maSoThue like %:maSoThue%) "
            + "and (:tenCty is null or b.tenCty like %:tenCty%) ", nativeQuery = true)
    List<DoiTac> findByCondition(@Param("id") Long id,@Param("diaChi") String diaChi ,@Param("maSoThue") String maSoThue,@Param("tenCty") String tenCty);
}
