package dungnt.ptit.spring_mogodb.controller;

import dungnt.ptit.spring_mogodb.document.Employee;
import dungnt.ptit.spring_mogodb.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping("/save")
    public ResponseEntity saveOrUpdate(@RequestBody Employee employee){
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeRepository.save(employee));
    }

    @GetMapping("")
    public ResponseEntity getAll(){
        return ResponseEntity.ok(employeeRepository.)
    }
}
