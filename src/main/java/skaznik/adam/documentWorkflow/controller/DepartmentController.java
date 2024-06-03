package skaznik.adam.documentWorkflow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import skaznik.adam.documentWorkflow.model.Department;
import skaznik.adam.documentWorkflow.service.DepartmentService;

@Controller
@RequestMapping("/departments")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public String viewDepartmentsPage(Model model) {
        model.addAttribute("departments", departmentService.findAll());
        return "departments";
    }

    @GetMapping("/new")
    public String showNewDepartmentForm(Model model) {
        model.addAttribute("department", new Department());
        return "new_department";
    }

    @PostMapping("/save")
    public String saveDepartment(@ModelAttribute("department") Department department) {
        departmentService.save(department);
        return "redirect:/departments";
    }

    @GetMapping("/edit/{id}")
    public String showEditDepartmentForm(@PathVariable Long id, Model model) {
        Department department = departmentService.findById(id);
        model.addAttribute("department", department);
        return "edit_department";
    }

    @PostMapping("/update/{id}")
    public String updateDepartment(@PathVariable Long id, @ModelAttribute("department") Department department) {
        Department existingDepartment = departmentService.findById(id);
        existingDepartment.setName(department.getName());
        existingDepartment.setCaseTag(department.getCaseTag());
        departmentService.save(existingDepartment);
        return "redirect:/departments";
    }

}
