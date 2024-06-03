package skaznik.adam.documentWorkflow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import skaznik.adam.documentWorkflow.model.Attachment;
import skaznik.adam.documentWorkflow.model.Case;
import skaznik.adam.documentWorkflow.repository.AttachmentRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class AttachmentService {
    private static final String UPLOAD_DIR = "upload";

    @Autowired
    private AttachmentRepository attachmentRepository;

    public Attachment saveAttachment(MultipartFile file, Case caseEntity) throws IOException {
        Path uploadPath = Paths.get(UPLOAD_DIR);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String filename = file.getOriginalFilename();
        Path filePath = uploadPath.resolve(filename);
        Files.copy(file.getInputStream(), filePath);

        Attachment attachment = new Attachment();
        attachment.setFilename(filename);
        attachment.setACase(caseEntity);

        return attachmentRepository.save(attachment);
    }
}
