package myoauth2server.repository.impl;

import myoauth2server.pojo.entity.User;
import myoauth2server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
	private EntityManager entityManager;

    @Override
    public boolean authenUser(String email, String password) {
        String hql = "FROM User AS u WHERE u.email = :email AND u.password = :password AND u.active = 1";
		List<User> lstUsers = (List<User>) entityManager.createQuery(hql).setParameter("email", email).setParameter("password", password).getResultList();
		return lstUsers != null && lstUsers.size() > 0;
    }

    @Override
    public User getUserByEmail(String email) {
        String hql = "FROM User AS u WHERE u.email = :email AND u.active = 1";
		List<User> lstResult = entityManager.createQuery(hql).setParameter("email", email).getResultList();
		if (lstResult != null && lstResult.size() > 0) {
			return lstResult.get(0);
		}
        return null;
    }

}
