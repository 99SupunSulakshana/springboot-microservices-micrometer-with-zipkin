package com.example.employee_service.util;

import com.example.employee_service.dto.EmployeeDTO;
import com.example.employee_service.entity.Employee;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    List<EmployeeDTO> entityListToDTOList(List<Employee> departments);
    List<EmployeeDTO> listDTOToPage(Page<Employee> page);
}
