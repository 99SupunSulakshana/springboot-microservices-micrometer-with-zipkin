package com.example.department_service.service.impl;

import com.example.department_service.dto.DepartmentDto;
import com.example.department_service.dto.pagination.PaginatedResponseDepartmentDTO;
import com.example.department_service.dto.queryinterfaces.DepartmentDetailsInterface;
import com.example.department_service.entity.Department;
import com.example.department_service.repository.DepartmentRepo;
import com.example.department_service.service.DepartmentService;
import com.example.department_service.util.DepartmentMapper;
import com.example.department_service.util.exceptions.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepo departmentRepo;

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        Department department = new Department(
                departmentDto.getId(),
                departmentDto.getDepartmentName(),
                departmentDto.getDepartmentDescription(),
                departmentDto.getDepartmentCode()
        );

        Department savedDepartment = departmentRepo.save(department);
        return new DepartmentDto(
            savedDepartment.getId(),
            savedDepartment.getDepartmentName(),
            savedDepartment.getDepartmentDescription(),
            savedDepartment.getDepartmentCode()
        );
    }

    @Override
    public PaginatedResponseDepartmentDTO getAllDepartments(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Department> departmentPageDetails = departmentRepo.findAll(pageable);
        List<DepartmentDto> listDTO = departmentMapper.listDTOToPage(departmentPageDetails);
        if(departmentPageDetails.getSize() < 1){
            throw new NotFoundException("No Data");
        }
        return new PaginatedResponseDepartmentDTO(
            listDTO, listDTO.size()
        );
    }

    @Override
    public DepartmentDto getDepartment(String departmentId) {
        if(departmentRepo.existsById(departmentId)){
            Department department = departmentRepo.getReferenceById(departmentId);
            return modelMapper.map(department, DepartmentDto.class);
        }else{
            throw new RuntimeException("No found department!");
        }
    }
}
