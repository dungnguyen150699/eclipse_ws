package hungteacher.week.demo.service.implService;

import hungteacher.week.demo.entity.MatHang;
import hungteacher.week.demo.repository.DSMatHangRepository;
import hungteacher.week.demo.repository.MatHangRepository;
import hungteacher.week.demo.service.MatHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatHangImpl implements MatHangService {
    @Autowired
    private MatHangRepository matHangRepository;

    @Override
    public void create(MatHang MatHang) {
        matHangRepository.save(MatHang);
    }

    @Override
    public MatHang update(MatHang MatHang) throws Exception {
        // M có thể dùng log của Lombok mà log lỗi ở Controller
        if(matHangRepository.findById(MatHang.getId()) == null) throw new Exception("Không tìm thấy Hoa Don");
        return matHangRepository.save(MatHang);
    }

    @Override
    public List<MatHang> findByCondition(MatHang MatHang) {
        // Tự viết xem
        return null;
    }

    @Override
    public void deleteById(Long id) {
        matHangRepository.deleteById(id);
    }
}
