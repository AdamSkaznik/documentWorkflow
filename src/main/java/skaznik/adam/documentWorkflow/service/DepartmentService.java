package skaznik.adam.documentWorkflow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import skaznik.adam.documentWorkflow.model.Department;
import skaznik.adam.documentWorkflow.repository.DepartmentRepository;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    public Department save(Department department) {
        return departmentRepository.save(department);
    }

    public Department findById(Long id) {
        return departmentRepository.findById(id).orElse(null);
    }
}
