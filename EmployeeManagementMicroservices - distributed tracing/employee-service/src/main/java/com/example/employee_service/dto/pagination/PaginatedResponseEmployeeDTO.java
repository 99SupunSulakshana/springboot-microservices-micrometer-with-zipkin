package com.example.employee_service.dto.pagination;

import com.example.employee_service.dto.EmployeeDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaginatedResponseEmployeeDTO {
    List<EmployeeDTO> list;
    private long dataCount;
}
