package skaznik.adam.documentWorkflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import skaznik.adam.documentWorkflow.model.Case;

@Repository
public interface CaseRepository extends JpaRepository<Case, Long> {
}
