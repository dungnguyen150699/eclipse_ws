package hungteacher.week.demo.service;

import hungteacher.week.demo.entity.NhanVien;

import java.util.List;

public interface NhanVienService {
    void create(NhanVien NhanVien);

    NhanVien update(NhanVien NhanVien) throws Exception;

    List<NhanVien> findByCondition(NhanVien NhanVien);

    void deleteById(Long id);
}
