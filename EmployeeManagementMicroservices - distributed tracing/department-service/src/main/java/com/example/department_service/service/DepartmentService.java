package com.example.department_service.service;

import com.example.department_service.dto.DepartmentDto;
import com.example.department_service.dto.pagination.PaginatedResponseDepartmentDTO;
import com.example.department_service.entity.Department;
import jakarta.validation.constraints.Max;

import java.util.List;

public interface DepartmentService {
    public DepartmentDto saveDepartment(DepartmentDto departmentDto);
    public PaginatedResponseDepartmentDTO getAllDepartments(int page, int size);
    public DepartmentDto getDepartment(String departmentId);
}
