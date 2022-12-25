package hungteacher.week.demo.service.implService;

import hungteacher.week.demo.entity.NhanVien;
import hungteacher.week.demo.repository.NhanVienRepository;
import hungteacher.week.demo.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class NhanVienImpl implements NhanVienService {
    @Autowired
    private NhanVienRepository nhanVienRepository;

    @Override
    public void create(NhanVien NhanVien) {
        nhanVienRepository.save(NhanVien);
    }

    @Override
    public NhanVien update(NhanVien NhanVien) throws Exception {
        // M có thể dùng log của Lombok mà log lỗi ở Controller
        if(nhanVienRepository.findById(NhanVien.getId()) == null) throw new Exception("Không tìm thấy Hoa Don");
        return nhanVienRepository.save(NhanVien);
    }

    @Override
    public List<NhanVien> findByCondition(NhanVien NhanVien) {
        // Tự viết đi
        return null;
    }

    @Override
    public void deleteById(Long id) {
        nhanVienRepository.deleteById(id);
    }
}
