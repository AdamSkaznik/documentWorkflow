package skaznik.adam.documentWorkflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import skaznik.adam.documentWorkflow.model.Attachment;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
}
