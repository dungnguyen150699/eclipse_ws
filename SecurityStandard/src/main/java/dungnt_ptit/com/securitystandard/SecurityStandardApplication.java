package dungnt_ptit.com.securitystandard;

import dungnt_ptit.com.securitystandard.pojo.entity.*;
import dungnt_ptit.com.securitystandard.repository.OrderRepository;
import dungnt_ptit.com.securitystandard.repository.UserRepository;
import dungnt_ptit.com.securitystandard.ulti.constant.enums.ProcessOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@SpringBootApplication(exclude = {HibernateJpaAutoConfiguration.class })
@EnableScheduling
public class SecurityStandardApplication implements CommandLineRunner {
    @Autowired private UserRepository userRepository;
    @Autowired private PasswordEncoder cryptPasswordEncoder;
    @Resource(name = "EntityManager")   private EntityManager entityManager;
    @Autowired private OrderRepository orderRepository;
    public static void main(String[] args) {
        SpringApplication.run(SecurityStandardApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        OrderRepository orderRepository = new JpaRepositoryFactory(entityManager).getRepository(OrderRepository.class);
        List<Order> orders = orderRepository.findAll();
        saveNewOrder();
        saveNewUser();
    }

    public void saveNewUser(){
        User user = new User()
                .setUsername("dung-15-06-99")
                .setPassword(cryptPasswordEncoder.encode("1"))
                .setRoles(new HashSet<>(Arrays.asList(new Role().setName("ADMIN"))) );
        EntityTransaction tx = null;
        try{
            tx = entityManager.getTransaction();
            tx.begin();
            entityManager.persist(user);
//        userRepository.save(user);
            tx.commit();
        }catch (Exception ex){
            ex.printStackTrace();
            tx.rollback();
        }
    }

    public void saveNewOrder(){
        EntityTransaction tx = null;
        Order order = null;
        List<OrderDetail> orderDetailList;
        try{
            tx = entityManager.getTransaction();
            tx.begin();
            order = new Order();
            order.setDateOrder(new Date());
            order.setProcessOrder(ProcessOrder.JUST_ORDER);
//            orderRepository.save(order); // Giống nhau cả
            entityManager.persist(order);
            for(int i=0 ; i<2 ; i++){
                Product product = new Product();
                OrderDetail orderDetail = new OrderDetail();
                product.setName("p_"+i);
                product.setCount(Long.valueOf(i));
                entityManager.persist(product);
                orderDetail.setOrder(order);
                orderDetail.setProduct(product);
                orderDetail.setCount(Long.valueOf(i));
                orderDetail.setPrice(Double.valueOf(10+i));
                entityManager.persist(orderDetail);
            }
            tx.commit();
        }catch (Exception ex){
            ex.printStackTrace();
            tx.rollback();
        }
    }

}
