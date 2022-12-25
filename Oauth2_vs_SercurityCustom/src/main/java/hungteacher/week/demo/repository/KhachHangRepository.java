package hungteacher.week.demo.repository;

import hungteacher.week.demo.entity.HopDongTraGop;
import hungteacher.week.demo.entity.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Repository
public interface KhachHangRepository extends JpaRepository<KhachHang, Long> {
    @Query(value = "select kh from KhachHang kh where "
            + "(:#{#khachHang.getId()} is null or kh.id = :#{#khachHang.getId()}) "
            + "and (:#{#khachHang.getTen()} is null or kh.ten like %:#{#khachHang.getTen()}%)"
            + "and (:#{#khachHang.getNgaySinh()} is null or kh.ngaySinh = :#{#khachHang.getNgaySinh()})"
            + "and (:#{#khachHang.getDiaChi()} is null or kh.diaChi like %:#{#khachHang.getDiaChi()}%)"
            + "and (:#{#khachHang.getSdt()} is null or kh.sdt like %:#{#khachHang.getSdt()}%)"
            + "and (:#{#khachHang.getEmail()} is null or kh.email like %:#{#khachHang.getEmail()}%)")
    List<KhachHang> findByCondition(KhachHang khachHang);

    @Query(value = "select kh from KhachHang kh where "
            + "(:#{#khachHang.id} is null or kh.id = :#{#khachHang.id}) "
            + "and (:#{#khachHang.ten} is null or kh.ten like %:#{#khachHang.ten}%)"
            + "and (:#{#khachHang.ngaySinh} is null or kh.ngaySinh = :#{#khachHang.ngaySinh})"
            + "and (:#{#khachHang.diaChi} is null or kh.diaChi like %:#{#khachHang.diaChi}%)"
            + "and (:#{#khachHang.sdt} is null or kh.sdt like %:#{#khachHang.sdt}%)"
            + "and (:#{#khachHang.email} is null or kh.email like %:#{#khachHang.email}%)")
    List<KhachHang> findByCondition1(KhachHang khachHang);

//    @Procedure(value = "test")
//    String getStringHello(@Param(value = "hello") String var1);
    @Procedure(value = "test")// Chỉ áp dụng 1 output
    String getStringHello(@Param(value = "hello") String var1);

    @Procedure(value = "selectObject")// Chỉ áp dụng 1 output
    List<Object> selectObject();

    @Procedure(value = "selectObject")// Chỉ áp dụng 1 output
    Object[] selectObject2();

    @Procedure(value = "selectObject")// Chỉ áp dụng 1 output
    List<List<Map<String,Object>>> selectObject3();
}
