package com.ttc.bookmeetingroom.controller.api;


import com.ttc.bookmeetingroom.common.enums.ResponseCodeEnum;
import com.ttc.bookmeetingroom.controller.request.DepartmentCreateRequest;
import com.ttc.bookmeetingroom.controller.request.DepartmentUpdateRequest;
import com.ttc.bookmeetingroom.controller.response.ResponseBodyDto;
import com.ttc.bookmeetingroom.dto.DepartmentDto;
import com.ttc.bookmeetingroom.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("department")
@CrossOrigin(origins= {"*"})
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping(value = "/get-by-conditions" ,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseBodyDto<DepartmentDto>> getAllDepartment(
            @RequestParam(name = "id", required = false) Integer id,
            @RequestParam(name = "name", required = false) String name,
            Pageable pageable) {

        Page<DepartmentDto> page = departmentService.getAllByCondition(id, name , pageable);

        ResponseBodyDto<DepartmentDto> res = new ResponseBodyDto<>(pageable, page, ResponseCodeEnum.R_200, "OK");
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseBodyDto<DepartmentDto>> createDepartment(
            @RequestBody DepartmentCreateRequest request) {
        DepartmentDto departmentDto = departmentService.create(request);
        ResponseBodyDto<DepartmentDto> res = new ResponseBodyDto<>(departmentDto,
                ResponseCodeEnum.R_201, "CREATED");
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseBodyDto<DepartmentDto>> updateDepartment(
            @PathVariable(name = "id") Integer id,
            @RequestBody DepartmentUpdateRequest request) {
        DepartmentDto departmentDto = departmentService.update(request, id);
        ResponseBodyDto<DepartmentDto> res = new ResponseBodyDto<>(departmentDto, ResponseCodeEnum.R_200, "OK");
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseBodyDto<DepartmentDto>> deleteDepartment(
            @PathVariable(name = "id") Integer id) {
        departmentService.delete(id);
        ResponseBodyDto<DepartmentDto> res = new ResponseBodyDto<>(ResponseCodeEnum.R_200, "OK");
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }


}
