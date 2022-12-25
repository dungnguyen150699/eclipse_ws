package com.example.demo;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.Entity.RoleEntity;
import com.example.demo.Entity.UserEntity;
import com.example.demo.Service.UserService;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//import com.example.demo.Config.ConfigSercurity;
//import com.example.demo.Config.SwaggerConfig;

@SpringBootApplication
@EnableSwagger2
//@Import({SwaggerConfig.class,ConfigSercurity.class})
public class DemoshopRestApIvsReactApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoshopRestApIvsReactApplication.class, args);
	}
	@Autowired
	private UserService us;
	
	@Autowired
	private PasswordEncoder pe;
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		UserDetails userDetails = null;
		try{
			userDetails = us.loadUserByUsername("admin");
		}catch (Exception ex){
			ex.printStackTrace();
		}
		if(userDetails == null) {
			RoleEntity r = new RoleEntity();
			r.setName("ADMIN");
			Set <RoleEntity> listr = new HashSet<RoleEntity>();
			listr.add(r);
			UserEntity u = new UserEntity();
			u.setUsername("admin");
			u.setPassword(pe.encode("1"));
			u.setRoles(listr);
			u.setActive(true);
			us.save(u);
		}
		
	}

}
