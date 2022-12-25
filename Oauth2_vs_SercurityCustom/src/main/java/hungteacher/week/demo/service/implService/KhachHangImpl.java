package hungteacher.week.demo.service.implService;

import hungteacher.week.demo.entity.KhachHang;
import hungteacher.week.demo.repository.KhachHangRepository;
import hungteacher.week.demo.service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KhachHangImpl implements KhachHangService {
    @Autowired
    private KhachHangRepository khachHangRepository;

    @Override
    public void create(KhachHang KhachHang) {
        khachHangRepository.save(KhachHang);
    }

    @Override
    public KhachHang update(KhachHang KhachHang) throws Exception {
        // M có thể dùng log của Lombok mà log lỗi ở Controller
        if(khachHangRepository.findById(KhachHang.getId()) == null) throw new Exception("Không tìm thấy Hoa Don");
        return khachHangRepository.save(KhachHang);
    }

    @Override
    public List<KhachHang> findByCondition(KhachHang KhachHang) {
        return khachHangRepository.findByCondition(KhachHang);
    }

    @Override
    public void deleteById(Long id) {
        khachHangRepository.deleteById(id);
    }
}
