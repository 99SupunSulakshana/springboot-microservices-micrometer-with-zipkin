package com.example.employee_service.dto;

import com.example.department_service.dto.DepartmentDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponseDto {
    private EmployeeDTO employeeDto;
    private DepartmentDto departmentDto;
}
