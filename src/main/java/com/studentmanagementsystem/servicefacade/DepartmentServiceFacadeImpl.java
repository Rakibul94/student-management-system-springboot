package com.studentmanagementsystem.servicefacade;

import com.studentmanagementsystem.model.Department;
import com.studentmanagementsystem.service.DepartmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.studentmanagementsystem.data.DepartmentDTO;

import java.util.List;

@Service
public class DepartmentServiceFacadeImpl implements DepartmentServiceFacade {

    private final DepartmentService departmentService;

    public DepartmentServiceFacadeImpl(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

//    public List<Department> getAllDepartments() {
//        return departmentService.getAllDepartments();
//    }
//
//    public Department findOrCreateByName(String name) {
//
//        if (name == null || name.isBlank()) {
//            return null;
//        }
//
//        Department department = departmentService.findByName(name);
//
//        if (department == null) {
//            department = new Department();
//            department.setName(name);
//            department = departmentService.save(department);
//        }
//
//        return department;
//    }
//
//    public Department getDepartmentById(Long id) {
//        return departmentService.getDepartmentById(id);
//    }
//
//    @Transactional
//    public Department updateDepartment(Department department , Long departmentId) {
//
//
//
//        // fetch existing student
//        Department existingDepartment = departmentService.getDepartmentById(departmentId);
//        if (existingDepartment == null) {
//            return null;
//        }
//
//
//        // update fields
//        existingDepartment.setName(department.getName());
//
//        return departmentService.updateDepartment(existingDepartment);
//    }
//
//
//    @Transactional
//    public boolean deleteDepartment(Long departmentId) {
//        if (departmentId == null) {
//            return false;
//        }
//
//        Department department = departmentService.getDepartmentById(departmentId);
//        if (department == null) {
//            return false;
//        }
//
//        departmentService.deleteDepartment(departmentId);
//        return true;
//    }

    @Override
    public List<DepartmentDTO> getAllDepartments() {
        return departmentService.getAllDepartments()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public DepartmentDTO getDepartmentById(Long id) {
        Department d = departmentService.getDepartmentById(id);
        return d == null ? null : toDTO(d);
    }

    @Override
    public DepartmentDTO createDepartment(String name) {
        if (name == null || name.isBlank()) return null;

        Department department = new Department();
        department.setName(name);

        return toDTO(departmentService.save(department));
    }

    @Override
    public DepartmentDTO updateDepartment(DepartmentDTO dto) {
        Department existing =
                departmentService.getDepartmentById(dto.getId());

        if (existing == null) return null;

        existing.setName(dto.getName());
        return toDTO(departmentService.updateDepartment(existing));
    }

    @Override
    public boolean deleteDepartment(Long id) {
        return departmentService.deleteDepartment(id);
    }

    private DepartmentDTO toDTO(Department d) {
        DepartmentDTO dto = new DepartmentDTO();
        dto.setId(d.getId());
        dto.setName(d.getName());
        return dto;
    }


}