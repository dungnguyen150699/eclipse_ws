package hungteacher.week.demo.service.implService;

import hungteacher.week.demo.entity.DoiTac;
import hungteacher.week.demo.repository.DoiTacRepository;
import hungteacher.week.demo.service.DoiTacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoiTacImpl implements DoiTacService {
    @Autowired
    private DoiTacRepository doiTacRepository;

    @Override
    public void create(DoiTac doiTac) {
        doiTacRepository.save(doiTac);
    }

    @Override
    public DoiTac update(DoiTac doiTac) throws Exception {
        if(doiTacRepository.findById(doiTac.getId()) == null){
            throw new Exception("Đối tác không tồn tại");
        }
        return doiTacRepository.save(doiTac);
    }

    @Override
    public List<DoiTac> findByCondition(DoiTac doiTac) {
        return doiTacRepository.findByCondition(doiTac.getId(),doiTac.getDiaChi(),doiTac.getMaSoThue(),doiTac.getTenCTy());
    }

    @Override
    public void deleteById(Long id) {
        doiTacRepository.deleteById(id);
    }
}
