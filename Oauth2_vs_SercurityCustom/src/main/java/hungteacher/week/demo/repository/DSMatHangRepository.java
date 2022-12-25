package hungteacher.week.demo.repository;

import hungteacher.week.demo.entity.DSMatHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DSMatHangRepository extends JpaRepository<DSMatHang , Long> {
    @Query(value = "select * from DSMatHang dsmh where "
            + "(:#{#dsMatHang.id} is null or dsmh.id = :#{#dsMatHang.id}) "
            + "and (:#{#dsMatHang.count} is null or dsmh.count =  :#{#dsMatHang.count}) "
            + "and (:tenCty is null or b.tenCty like %:tenCty%) ", nativeQuery = true)
    List<DSMatHang> findByCondition(DSMatHang dsMatHang);

    @Query(value = "select * from dsmat_hang dsmh where "
            + "(tbl_hop_dong_tra_gopmahd = :id )", nativeQuery = true)
    List<DSMatHang>findDSMatHangByIdHdtg(@Param("id") Long id);
}
