package hungteacher.week.demo.repository;

import hungteacher.week.demo.entity.DSMatHang;
import hungteacher.week.demo.entity.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon , Long> {
    @Query(value = "select hd from hoa_don hd where "
            + "(:#{#hoaDon.id} is null or hd.id = :#{#hoaDon.id}) "
            + "and (:#{#hoaDon.dsThoiDiemTTConLai} is null or hd.dsThoiDiemTTConLai <= :#{#hoaDon.dsThoiDiemTTConLai})")
    List<HoaDon> findByCondition(@Param("hoaDon")HoaDon hoaDon);
}
