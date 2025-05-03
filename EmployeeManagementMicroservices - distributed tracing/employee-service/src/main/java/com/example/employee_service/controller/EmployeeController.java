package com.example.employee_service.controller;


import com.example.employee_service.dto.ApiResponseDto;
import com.example.employee_service.dto.EmployeeDTO;
import com.example.employee_service.dto.pagination.PaginatedResponseEmployeeDTO;
import com.example.employee_service.service.EmployeeService;
import com.example.employee_service.util.response.StandardResponse;
import io.micrometer.tracing.annotation.NewSpan;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Max;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/employee")
@CrossOrigin
@Tag(name = "Employee API", description = "Operations related to employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Operation(summary = "save employee")
//    @NewSpan("save-employee")
    @PostMapping("save")
    public ResponseEntity<StandardResponse> saveEmployee(@RequestBody EmployeeDTO employeeDTO){
        EmployeeDTO employeeDTO1 = employeeService.saveEmployee(employeeDTO);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(
                        200,
                        "Employee Added Successfully!",
                        employeeDTO1
                ),
                HttpStatus.CREATED
        );
    }

    @Operation(summary = "get employee by id")
//    @NewSpan("get-employee-id")
    @GetMapping(path = "/get-employee-id", params = {"id"})
    private ResponseEntity<StandardResponse> getEmployeeDetails(@RequestParam(value = "id") String employeeId){
        ApiResponseDto apiResponseDto = employeeService.getEmployee(employeeId);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(
                        200,
                        "Successfully!",
                        apiResponseDto
                ),
                HttpStatus.CREATED
        );
    }

    @Operation(summary = "get all employee")
//    @NewSpan("get-all-employee")
    @GetMapping(path = "/get-all-employee", params = {"page","size"})
    private ResponseEntity<StandardResponse> getAllEmployee(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") @Max(50) int size
    ){
        PaginatedResponseEmployeeDTO paginatedResponseEmployeeDTO = employeeService.getAllEmployee(page,size);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(
                        200,
                        "Successfully!",
                        paginatedResponseEmployeeDTO
                ),
                HttpStatus.CREATED
        );
    }
}
