package com.example.employee_service.service.impl;

import com.example.department_service.dto.DepartmentDto;
import com.example.department_service.util.response.StandardResponse;
import com.example.employee_service.dto.ApiResponseDto;
import com.example.employee_service.dto.EmployeeDTO;
import com.example.employee_service.dto.pagination.PaginatedResponseEmployeeDTO;
import com.example.employee_service.entity.Employee;
import com.example.employee_service.repository.EmployeeRepository;
import com.example.employee_service.service.APIClient;
import com.example.employee_service.service.EmployeeService;
import com.example.employee_service.util.EmployeeMapper;
import com.example.employee_service.util.exceptions.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient webClient;

    @Autowired
    private APIClient apiClient;

    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        Employee employee = modelMapper.map(employeeDTO, Employee.class);
        Employee employeeSave = employeeRepository.save(employee);
        EmployeeDTO employeeDTO1 = modelMapper.map(employeeSave, EmployeeDTO.class);
        return employeeDTO1;
    }

    @Override
    public PaginatedResponseEmployeeDTO getAllEmployee(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page pageResponse = employeeRepository.findAll(pageable);
        List listDTO = employeeMapper.listDTOToPage(pageResponse);
        if(pageResponse.getSize() < 1){
            throw new NotFoundException("No Data");
        }
        return new PaginatedResponseEmployeeDTO(
                listDTO, listDTO.size()
        );
    }

    @Override
    public ApiResponseDto getEmployee(String employeeId) {
        if(employeeRepository.existsById(employeeId)){
            Employee employee = employeeRepository.getReferenceById(employeeId);

            // Rest Template Usage......

//            UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("http://localhost:8083/get-department")
//                    .queryParam("id", employee.getDepartmentCode());
//            ResponseEntity<StandardResponse> responseEntity =  restTemplate.getForEntity(builder.toUriString(), StandardResponse.class);
//            StandardResponse standardResponse = responseEntity.getBody();
//            DepartmentDto departmentDto = null;
//            if (standardResponse != null && standardResponse.getData() != null) {
//                Object data = standardResponse.getData();
//                departmentDto = modelMapper.map(data, DepartmentDto.class);
//            }

            // Web client .................

//            StandardResponse standardResponse = webClient.get()
//                    .uri(uriBuilder -> uriBuilder
//                            .scheme("http")
//                            .host("localhost")
//                            .port(8083)
//                            .path("/get-department")
//                            .queryParam("id", employee.getDepartmentCode())
//                            .build())
//                    .retrieve()
//                    .bodyToMono(StandardResponse.class)
//                    .block();
//            DepartmentDto departmentDto = null;
//            if (standardResponse != null && standardResponse.getData() != null) {
//                Object data = standardResponse.getData();
//                departmentDto = modelMapper.map(data, DepartmentDto.class);
//            }

            // API Client............................................

            ResponseEntity<StandardResponse> responseEntity = apiClient.getDepartment(employee.getDepartmentCode());
            StandardResponse standardResponse = responseEntity.getBody();
            DepartmentDto departmentDto = null;
            if (standardResponse != null && standardResponse.getData() != null) {
                Object data = standardResponse.getData();
                departmentDto = modelMapper.map(data, DepartmentDto.class);
            }

            EmployeeDTO employeeDTO = modelMapper.map(employee, EmployeeDTO.class);
            ApiResponseDto apiResponseDto = new ApiResponseDto();
            apiResponseDto.setEmployeeDto(employeeDTO);
            apiResponseDto.setDepartmentDto(departmentDto);
            return apiResponseDto;
        }else{
            throw new RuntimeException("No found department!");
        }
    }
}
