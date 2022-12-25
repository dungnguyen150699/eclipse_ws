package hungteacher.week.demo.service;


import hungteacher.week.demo.entity.DoiTac;

import java.util.List;

public interface DoiTacService {
    void create(DoiTac doiTac);

    DoiTac update(DoiTac doiTac) throws Exception;

    List<DoiTac> findByCondition(DoiTac doiTac);

    void deleteById(Long id);

}
