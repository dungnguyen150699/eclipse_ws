package hungteacher.week.demo.service.implService;

import hungteacher.week.demo.entity.DSMatHang;
import hungteacher.week.demo.repository.DSMatHangRepository;
import hungteacher.week.demo.service.DSMatHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DSMatHangImpl implements DSMatHangService {
    @Autowired
    private DSMatHangRepository dsMatHangRepository;

    @Override
    public void create(DSMatHang DSMatHang) {
        dsMatHangRepository.save(DSMatHang);
    }

    @Override
    public DSMatHang update(DSMatHang DSMatHang) throws Exception {
        if(dsMatHangRepository.findById(DSMatHang.getId()) == null) throw new Exception("Không tìm thấy DS Mat Hang");
        return dsMatHangRepository.save(DSMatHang);
    }

    @Override
    public List<DSMatHang> findByCondition(DSMatHang dsMatHang) {
        return dsMatHangRepository.findByCondition(dsMatHang);
    }

    @Override
    public void deleteById(Long id) {
        dsMatHangRepository.deleteById(id);
    }
}
