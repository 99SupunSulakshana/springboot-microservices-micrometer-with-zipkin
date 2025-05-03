package com.example.employee_service.service;

import com.example.employee_service.dto.ApiResponseDto;
import com.example.employee_service.dto.EmployeeDTO;
import com.example.employee_service.dto.pagination.PaginatedResponseEmployeeDTO;
import com.example.employee_service.entity.Employee;
import com.example.employee_service.util.response.StandardResponse;

public interface EmployeeService {
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO);
    public PaginatedResponseEmployeeDTO getAllEmployee(int page, int size);
    public ApiResponseDto getEmployee(String employeeId);
}
