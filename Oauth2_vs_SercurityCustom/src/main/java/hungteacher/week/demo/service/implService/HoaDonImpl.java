package hungteacher.week.demo.service.implService;

import hungteacher.week.demo.entity.HoaDon;
import hungteacher.week.demo.repository.HoaDonRepository;
import hungteacher.week.demo.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HoaDonImpl implements HoaDonService {

    @Autowired
    private HoaDonRepository hoaDonRepository;

    @Override
    public void create(HoaDon HoaDon) {
        hoaDonRepository.save(HoaDon);
    }

    @Override
    public HoaDon update(HoaDon HoaDon) throws Exception {
        // M có thể dùng log của Lombok mà log lỗi ở Controller
        if(hoaDonRepository.findById(HoaDon.getId()) == null) throw new Exception("Không tìm thấy Hoa Don");
        return hoaDonRepository.save(HoaDon);
    }

    @Override
    public List<HoaDon> findByCondition(HoaDon hoaDon) {
        return hoaDonRepository.findByCondition(hoaDon);
    }

    @Override
    public void deleteById(Long id) {
        hoaDonRepository.deleteById(id);
    }
}
