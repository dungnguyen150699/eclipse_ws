package dungnt_ptit.com.securitystandard.controller;


import dungnt_ptit.com.securitystandard.pojo.entity.User;
import dungnt_ptit.com.securitystandard.repository.UserRepository;
import dungnt_ptit.com.securitystandard.ulti.common.mapper.mapStruct.UserMapStruct;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@RequestMapping("/user")
@Controller
@Slf4j
public class UserController {
    @Resource(name = "EntityManager") private EntityManager entityManager;
    @Autowired private UserRepository userRepository;
    @Autowired private UserMapStruct userMapStruct;

    private Session session; // Nói chung hibernate Session không bằng Hibernate JPA(EntityManage)

    @Autowired
    public UserController(EntityManager entityManager){
        this.entityManager = entityManager; // cái này ko phải Shared nhé
        this.session = entityManager.unwrap(Session.class); // Shared chỉ dùng để select dữ liệu;
    }

    @GetMapping("/getInfor")
    public @ResponseBody ResponseEntity getInforByHibernateSession(@RequestParam("name") String userName){
        User user = null;
        List<User> userList = null;
        // Way 1 use Session
        NativeQuery nativeQuery = session.createNativeQuery("SELECT * FROM Users u WHERE u.username = ?1",User.class);
        nativeQuery.setParameter(1,userName);
        userList = nativeQuery.getResultList();
        user =  userList != null ? userList.get(0) : null;
        // Way 2 use EntityManager
//            Query query  = entityManager.createNativeQuery("SELECT * FROM Users u WHERE u.username = ?1",User.class);
//            query.setParameter(1,userName);
////        User user = (User) query.getSingleResult(); // ko đc
//            userList = query.getResultList();
//            user =  userList != null ? userList.get(0) : null;
//            tx.commit();
//        }catch (Exception ex){
//            tx.rollback();
//        }
        return ResponseEntity.ok(userMapStruct.toDto(user));
    }
}
