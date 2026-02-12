package com.studentmanagementsystem.servicefacade;

import com.studentmanagementsystem.mapper.DepartmentMapper;
import com.studentmanagementsystem.model.Department;
import com.studentmanagementsystem.service.DepartmentService;
import org.springframework.stereotype.Service;
import com.studentmanagementsystem.data.DepartmentData;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceFacadeImpl implements DepartmentServiceFacade {

    private final DepartmentService departmentService;
    private final DepartmentMapper departmentMapper;

    public DepartmentServiceFacadeImpl(DepartmentService departmentService,
                                       DepartmentMapper departmentMapper) {
        this.departmentService = departmentService;
        this.departmentMapper = departmentMapper;
    }




    @Override
    public List<DepartmentData> getAllDepartments() {
        return departmentService.getAllDepartments()
                .stream()
                .map(departmentMapper::toData)
                .collect(Collectors.toCollection(ArrayList::new));
    }

//    @Override
//    public List<DepartmentData> getAllDepartments() {
//        List<Department> departments = departmentService.getAllDepartments();
//        List<DepartmentData> departmentDataList = new ArrayList<>();
//
//        for (Department department : departments) {
//            departmentDataList.add(departmentMapper.toData(department));
//        }
//
//        return departmentDataList;
//    }


    @Override
    public DepartmentData getDepartmentById(Long id) {
        Department department = departmentService.getDepartmentById(id);
        return departmentMapper.toData(department);
    }

    @Override
    public DepartmentData createDepartment(String name) {

        Department department = new Department();
        department.setName(name);

        return departmentMapper.toData(departmentService.saveDepartment(department));
    }

    @Override
    public DepartmentData updateDepartment(DepartmentData departmentData) {
        Department existingDepartment =
                departmentService.getDepartmentById(departmentData.getId());

        departmentMapper.updateEntity(existingDepartment,departmentData);
        return departmentMapper.toData(departmentService.saveDepartment(existingDepartment));
    }

    @Override
    public void deleteDepartmentById(Long id) {
        departmentService.deleteDepartmentById(id);
    }


}