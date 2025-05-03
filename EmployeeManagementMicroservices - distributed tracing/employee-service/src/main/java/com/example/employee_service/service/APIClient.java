package com.example.employee_service.service;

import com.example.department_service.dto.DepartmentDto;
import com.example.department_service.util.response.StandardResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "http://localhost:8083", value = "DEPARTMENT-SERVICE")
public interface APIClient {
    @GetMapping(path = "get-department", params = "id")
    ResponseEntity<StandardResponse> getDepartment(@RequestParam(value = "id") String id);
}
