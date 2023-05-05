package dungnt.ptit.spring_mogodb.api.controller;

import dungnt.ptit.spring_mogodb.api.request.EmployeeSearchRequest;
import dungnt.ptit.spring_mogodb.document.Employee;
import dungnt.ptit.spring_mogodb.repository.EmployeeRepository;
import dungnt.ptit.spring_mogodb.repository.custom.EmployeeRepositoryCustom;
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
    @Autowired
    private EmployeeRepositoryCustom employeeRepositoryCustom;

    @PostMapping("/save")
    public ResponseEntity saveOrUpdate(@RequestBody Employee employee){
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeRepository.save(employee));
    }

    @GetMapping("")
    public ResponseEntity getAll(){
        return ResponseEntity.ok(employeeRepository.findAll());
    }

//    @PostMapping("/get-all-by-condition")
//    public ResponseEntity getAllByCondition(@RequestBody EmployeeSearchRequest request){
//        return ResponseEntity.ok(employeeRepository.findAll());
//    }

    @GetMapping("/find-by-fullname")
    public ResponseEntity findByFullName(@RequestParam(name="fullname") String fullname){
        return ResponseEntity.ok(employeeRepository.findCustomByFullName(fullname));
    }

    @GetMapping("/get-maxId")
    public ResponseEntity getMaxId(){
        return ResponseEntity.ok(employeeRepositoryCustom.getMaxEmpId());
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody Employee employee){
        return ResponseEntity.ok(employeeRepositoryCustom.updateEmployee(employee.getEmpNo(),employee.getFullName(),employee.getHireDate()));
    }

}
