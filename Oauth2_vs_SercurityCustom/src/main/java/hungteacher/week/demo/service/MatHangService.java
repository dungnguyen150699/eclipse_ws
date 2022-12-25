package hungteacher.week.demo.service;

import hungteacher.week.demo.entity.MatHang;

import java.util.List;

public interface MatHangService {
    void create(MatHang MatHang);

    MatHang update(MatHang MatHang) throws Exception;

    List<MatHang> findByCondition(MatHang MatHang);

    void deleteById(Long id);
}
