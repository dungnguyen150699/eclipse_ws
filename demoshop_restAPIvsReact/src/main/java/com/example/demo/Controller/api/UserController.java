package com.example.demo.Controller.api;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.example.demo.Service.UserService;
import com.example.demo.Service.Impl.UserDetailImpl;
import com.example.demo.error.enums.ResponseCodeEnum;

import lombok.RequiredArgsConstructor;

import com.example.demo.Config.Jwt.JwtService;
import com.example.demo.Controller.response.ResponseBodyDto;
import com.example.demo.Controller.response.UserTokenDto;
import com.example.demo.Entity.OrderEntity;
import com.example.demo.Entity.RoleEntity;
import com.example.demo.Entity.UserEntity;

@Controller
@RequestMapping("/user")
@CrossOrigin(origins= {"*"})
@RequiredArgsConstructor
public class UserController {

	@Autowired
	private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder pe;
    @Autowired
    private JwtService js;

    @PostMapping(value="/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserEntity> register(@RequestBody UserEntity request){
    	RoleEntity role = new RoleEntity();
    	role.setName("USER");
    	
    	Set<RoleEntity> roles = new HashSet<RoleEntity>();
    	roles.add(role);
    	request.setRoles(roles);
    	System.out.println("contious");
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(request));
    }
    
    @PostMapping(value="/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserTokenDto> login(@RequestBody UserEntity request){
    	try {
	    	Authentication authenticate = authenticationManager
	    	          .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
	    	
	    	 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

	         Set<String> roles = authenticate.getAuthorities().stream()
	                 .map(r -> r.getAuthority()).collect(Collectors.toSet());
	         System.out.println("roles" + roles.toString());
	
	    	UserDetailImpl user = (UserDetailImpl) authenticate.getPrincipal();
	    	UserTokenDto udto = UserTokenDto.builder()
	    			.token(js.generateTokenLogin(user.getUsername()))
	    			.user(user).build();
	    	return ResponseEntity.status(HttpStatus.OK).body(udto);    		
    	}
    	catch (UsernameNotFoundException e) {
			// TODO: handle exception
    		System.out.println("xxxxxxxxxxxxxxxx");
    		HttpHeaders headers = new HttpHeaders();
    		headers.setContentType(MediaType.APPLICATION_JSON);
    		ResponseBodyDto<Object> dtoResult = new ResponseBodyDto<>();
    		dtoResult.setCode(ResponseCodeEnum.R_401);
    		dtoResult.setMessage(e.getMessage());
    		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}
    }
    
    @GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserEntity> getUserById(@PathVariable(name="id") int id){
    	return ResponseEntity.status(HttpStatus.OK).body(userService.findUserByID(id));    		
    }
    //produces = MediaType.APPLICATION_JSON_VALUE Set truong content_Type ow header la json
    @GetMapping(value="/order/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<OrderEntity>> getOrderUserById(@PathVariable(name="id") int id,Pageable pageable){
    	return ResponseEntity.status(HttpStatus.OK).body(userService.findOrderByUserId(id, pageable));    		
    }
    
    @DeleteMapping(value="/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteUser(@PathVariable(name="id") int id){
    	userService.deleteById(id);
    	return ResponseEntity.status(HttpStatus.OK).body("Deleted");    		
    }
}