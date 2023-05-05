package com.ttc.bookmeetingroom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//import com.ttc.bookmeetingroom.model.User;
//import com.ttc.bookmeetingroom.repository.UserJpa;


@SpringBootApplication
public class BookMeetingRoomApplication extends SpringBootServletInitializer implements CommandLineRunner{

    public static void main(String[] args) {
        SpringApplication.run(BookMeetingRoomApplication.class, args);
    }
<<<<<<< HEAD

=======
    
>>>>>>> 319df242ec7cf37fd2afd6607cad485ed4292185
//    @Autowired
//    private BCryptPasswordEncoder bp;
//    @Autowired
//    private UserJpa ur;

	@Override
	public void run(String... args) throws Exception {
//		// TODO Auto-generated method stub
//		User u = new User();
//		u.setActive(true);
//		u.setRole("ADMIN");
//		u.setPassword(bp.encode("1"));
//		u.setEmail("admin");
//		ur.save(u);
		/*
		Cái này nó đang load userId 2 lần
		- 1 là Encode(clientId) --> Vì client mình cũng mã hóa nữa lên nó pass lần 1
		- 2 là UserName ở requestParam --> Tiếp tục lần thứ 2
		$2a$10$WGtTpwfyUTSBtIes6cut6.C.FOC2QdSm2LKo8I.GUzKi4fZx1iCiy
		*/

	}

}
