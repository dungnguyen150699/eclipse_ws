package hungteacher.week.demo.service.implService;

import hungteacher.week.demo.entity.HopDongTraGop;
import hungteacher.week.demo.repository.HopDongTraGopRepository;
import hungteacher.week.demo.service.HopDongTraGopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HopDongTraGopImpl implements HopDongTraGopService {
    @Autowired
    private HopDongTraGopRepository hopDongTraGopRepository;

    @Override
    public void create(HopDongTraGop HopDongTraGop) {
        hopDongTraGopRepository.save(HopDongTraGop);
    }

    @Override
    public HopDongTraGop update(HopDongTraGop HopDongTraGop) throws Exception {
        // M có thể dùng log của Lombok mà log lỗi ở Controller
        if(hopDongTraGopRepository.findById(HopDongTraGop.getId()) == null) throw new Exception("Không tìm thấy Hoa Don");
        return hopDongTraGopRepository.save(HopDongTraGop);
    }

    @Override
    public List<HopDongTraGop> findByCondition(HopDongTraGop HopDongTraGop) {
        return hopDongTraGopRepository.findByCondition(HopDongTraGop);
    }

    @Override
    public void deleteById(Long id) {
        hopDongTraGopRepository.deleteById(id);
    }
}
