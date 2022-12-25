package hungteacher.week.demo.service;

import hungteacher.week.demo.entity.DSMatHang;

import java.util.List;

public interface DSMatHangService {
    void create(DSMatHang DSMatHang);

    DSMatHang update(DSMatHang DSMatHang) throws Exception;

    List<DSMatHang> findByCondition(DSMatHang DSMatHang);

    void deleteById(Long id);
}
