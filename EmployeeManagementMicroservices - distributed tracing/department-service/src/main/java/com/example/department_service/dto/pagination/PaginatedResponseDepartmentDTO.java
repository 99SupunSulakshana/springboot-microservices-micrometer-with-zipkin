package com.example.department_service.dto.pagination;

import com.example.department_service.dto.DepartmentDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaginatedResponseDepartmentDTO {
    List<DepartmentDto> list;
    private long dataCount;
}
