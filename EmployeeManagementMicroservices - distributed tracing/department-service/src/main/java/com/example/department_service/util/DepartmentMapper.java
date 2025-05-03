package com.example.department_service.util;

import com.example.department_service.dto.DepartmentDto;
import com.example.department_service.entity.Department;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    List<DepartmentDto> entityListToDTOList(List<Department> departments);
    List<DepartmentDto> listDTOToPage(Page<Department> page);
}
