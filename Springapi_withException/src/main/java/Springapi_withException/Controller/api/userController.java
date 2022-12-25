package Springapi_withException.Controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import Springapi_withException.Controller.response.responseDto;
import Springapi_withException.Service.userService;
import Springapi_withException.Service.impl.userImpl;
import Springapi_withException.dto.user;

@Controller
@RequestMapping("user")
@CrossOrigin(origins={"*"})
public class userController {
	
	@Autowired userService ui;
	
	@GetMapping(value="/getAllUsers", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<responseDto<user>> showUserAll(Pageable pageable){
		Page<user> page = ui.findAll(pageable);
		responseDto<user> res = new responseDto<>(pageable, page, "OK");
		return ResponseEntity.status(HttpStatus.OK).body(res);
	}
	
	@GetMapping(value="/getUserById/{id}")
	public ResponseEntity<user>getUserByID(@PathVariable(name="id") int id){
		user u = ui.findById(id);
		
		return ResponseEntity.status(HttpStatus.OK).body(u);
	}
}
