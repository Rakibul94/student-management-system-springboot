package com.studentsystem.rakibul.Service;

import com.studentsystem.rakibul.Model.Department;
import com.studentsystem.rakibul.Repository.DepartmentRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department getDepartmentById(Long id) {
        if (id == null) {
            return null;
        }
        return departmentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department findByName(String name) {
        return departmentRepository.findByNameIgnoreCase(name).orElse(null);
    }

    @Override
    public Department save(Department department) {
        return departmentRepository.save(department);
    }

    public void removeSessionMessage() {
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if (attributes == null) {
            return; // no request context
        }

        HttpSession session = attributes.getRequest().getSession(false);
        if (session == null) {
            return; // no session exists
        }

        session.removeAttribute("message");


    }
}