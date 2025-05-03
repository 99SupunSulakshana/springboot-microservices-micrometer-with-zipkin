package com.example.department_service.controller;

import com.example.department_service.dto.DepartmentDto;
import com.example.department_service.dto.pagination.PaginatedResponseDepartmentDTO;
import com.example.department_service.service.DepartmentService;
import com.example.department_service.util.response.StandardResponse;
import io.micrometer.tracing.annotation.NewSpan;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Max;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(name = "api/v1/departments")
@Tag(name = "Department API", description = "Operations related to department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @Operation(summary = "save department")
 //   @NewSpan("save-department")
    @PostMapping("save")
    public ResponseEntity<DepartmentDto> saveDepartment(@RequestBody DepartmentDto departmentDto){
        DepartmentDto departmentDto1 =departmentService.saveDepartment(departmentDto);
        return new ResponseEntity<>(
                departmentDto1,
                HttpStatus.CREATED
        );
    }

    @Operation(summary = "get all department")
 //   @NewSpan("get-all-departments")
    @GetMapping(params = {"page", "size"}, path = "/get-all-departments")
    public ResponseEntity<StandardResponse> getAllDepartments(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") @Max(50) int size
    ){
        PaginatedResponseDepartmentDTO paginatedResponseDepartmentDTO = departmentService.getAllDepartments(page,size);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(
                    200,
                "Successfully!",
                        paginatedResponseDepartmentDTO
                ),
                HttpStatus.OK
        );
    }

    @Operation(summary = "get department by id")
//    @NewSpan("get-departments-by-id")
    @GetMapping(path = "get-department", params = "id")
    public ResponseEntity<StandardResponse> getDepartment(@RequestParam(value = "id") String id){
        DepartmentDto departmentDto = departmentService.getDepartment(id);
        return new ResponseEntity<>(
                new StandardResponse(
                        200,
                        "Successfully!",
                        departmentDto
                ),
                HttpStatus.OK
        );
    }

}
