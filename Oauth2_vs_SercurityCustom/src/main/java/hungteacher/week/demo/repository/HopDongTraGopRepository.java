package hungteacher.week.demo.repository;

import hungteacher.week.demo.entity.HoaDon;
import hungteacher.week.demo.entity.HopDongTraGop;
import hungteacher.week.demo.entity.KhachHang;
import hungteacher.week.demo.service.implService.HopDongTraGopImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HopDongTraGopRepository extends JpaRepository<HopDongTraGop, Long> {
    @Query(value = "select * from HopDongTraGop hdtg where "
            + "(:#{#hopDongTraGop.id} is null or hdtg.id = :#{#hopDongTraGop.id}) "
            + "and (:#{#hopDongTraGop.thoiHanVay} is null or hdtg.thoiHanVay <= :#{#hopDongTraGop.thoiHanVay})"
            + "and (:#{#hopDongTraGop.dsThoiDiemTT} is null or hdtg.dsThoiDiemTT >= :#{#hopDongTraGop.dsThoiDiemTT})"
            + "and (:#{#hopDongTraGop.laiXuat} is null or hdtg.laiXuat = :#{#hopDongTraGop.laiXuat})"
            , nativeQuery = true)
    List<HopDongTraGop> findByCondition(HopDongTraGop hopDongTraGop);


}
