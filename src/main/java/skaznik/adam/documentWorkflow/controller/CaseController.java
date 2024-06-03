package skaznik.adam.documentWorkflow.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import skaznik.adam.documentWorkflow.model.Case;
import skaznik.adam.documentWorkflow.model.Department;
import skaznik.adam.documentWorkflow.service.AttachmentService;
import skaznik.adam.documentWorkflow.service.CaseService;
import skaznik.adam.documentWorkflow.service.DepartmentService;

import java.io.IOException;
import java.util.List;

@Controller
public class CaseController {
    @Autowired
    private CaseService caseService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private AttachmentService attachmentService;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("cases", caseService.findAll());
        return "index";
    }

//    @GetMapping("/archived")
//    public String viewArchivedCases(Model model) {
//        model.addAttribute("cases", caseService.findAllByArchived(true));
//        return "archived_cases";
//    }

    @GetMapping("/new")
    public String showNewCaseForm(Model model) {
        List<Department> departments = departmentService.findAll();
        model.addAttribute("case", new Case());
        model.addAttribute("departments", departments);
        return "new_case";
    }

    @PostMapping("/save")
    public String saveCase(@ModelAttribute("case") Case caseEntity,
                           @RequestParam("files") MultipartFile[] files) {
        Case savedCase = caseService.save(caseEntity);
        try {
            for (MultipartFile file : files) {
                attachmentService.saveAttachment(file, savedCase);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showEditCaseForm(@PathVariable Long id, Model model) {
        Case caseEntity = caseService.findById(id);
        List<Department> departments = departmentService.findAll();
        model.addAttribute("case", caseEntity);
        model.addAttribute("departments", departments);
        return "edit_case";
    }

    @PostMapping("/update/{id}")
    public String updateCase(@PathVariable Long id,
                             @ModelAttribute("case") Case caseEntity,
                             @RequestParam("files") MultipartFile[] files) {
        Case existingCase = caseService.findById(id);
        existingCase.setType(caseEntity.getType());
        existingCase.setDescription(caseEntity.getDescription());
        existingCase.setDepartment(caseEntity.getDepartment());
        existingCase.setArchived(caseEntity.isArchived());

        try {
            for (MultipartFile file : files) {
                attachmentService.saveAttachment(file, existingCase);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        caseService.save(existingCase);
        return "redirect:/";
    }
}
