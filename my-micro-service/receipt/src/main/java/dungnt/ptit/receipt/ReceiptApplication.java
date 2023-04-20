package dungnt.ptit.receipt;

import dungnt.ptit.receipt.pojo.Product;
import dungnt.ptit.receipt.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import javax.annotation.Resource;
import javax.annotation.Resources;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@SpringBootApplication
@EnableFeignClients
public class ReceiptApplication implements CommandLineRunner {
    @Autowired
    private ProductRepository productRepository;
    @Resource(name="masterEntityManager")
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager ;
    public static void main(String[] args) {
        SpringApplication.run(ReceiptApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if(productRepository != null){
            List<Product> datas = productRepository.findAll();
            System.out.println(datas.toString());
        }
        System.out.println(entityManagerFactory.toString());
        if(entityManagerFactory != null){
            entityManager = entityManagerFactory.createEntityManager();
            System.out.println(entityManager.toString());
        }
    }
}
