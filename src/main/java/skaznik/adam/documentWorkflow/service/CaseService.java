package skaznik.adam.documentWorkflow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import skaznik.adam.documentWorkflow.model.Case;
import skaznik.adam.documentWorkflow.repository.CaseRepository;

import java.util.List;

@Service
public class CaseService {
    @Autowired
    private CaseRepository caseRepository;

    public List<Case> findAll() {
        return caseRepository.findAll();
    }

//    public List<Case> findAllByArchived(boolean archived) {
//        return caseRepository.findByArchived(archived);
//    }

    public Case save(Case caseEntity) {
        return caseRepository.save(caseEntity);
    }

    public Case findById(Long id) {
        return caseRepository.findById(id).orElse(null);
    }
}
