package hungteacher.week.demo.service;

import hungteacher.week.demo.entity.HopDongTraGop;

import java.util.List;

public interface HopDongTraGopService {
    void create(HopDongTraGop HopDongTraGop);

    HopDongTraGop update(HopDongTraGop HopDongTraGop) throws Exception;

    List<HopDongTraGop> findByCondition(HopDongTraGop HopDongTraGop);

    void deleteById(Long id);
}
