package hungteacher.week.demo.service;

import hungteacher.week.demo.entity.KhachHang;

import java.util.List;

public interface KhachHangService {
    void create(KhachHang KhachHang);

    KhachHang update(KhachHang KhachHang) throws Exception;

    List<KhachHang> findByCondition(KhachHang KhachHang);

    void deleteById(Long id);
}
