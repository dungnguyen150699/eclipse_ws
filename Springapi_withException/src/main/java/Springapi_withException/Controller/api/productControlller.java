package Springapi_withException.Controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Springapi_withException.Service.productService;
import Springapi_withException.dto.productDto;

@RestController
@RequestMapping("product")
@CrossOrigin(origins={"*"})
public class productControlller {
	
	@Autowired
	private productService ps;
	@GetMapping("/getAllProduct")
	public ResponseEntity<List<productDto>> getAllProduct(){
		return ResponseEntity.status(HttpStatus.OK).body(ps.findAll());
	}
	
//	@PostMapping("/create")
//	public ResponseEntity<?> createProduct(){
//		return null;
//	}
//	
//	@PutMapping("/update/{id}")
//	public ResponseEntity<?> updateProduct(@PathVariable (name="id") int id,@RequestBody productDto ){
//		return null;
//	}
//	
//	@DeleteMapping("/delete/{id}")
//	public ResponseEntity<?> deleteProduct(@PathVariable (name="id") int id){
//		return null;
//	}
}
