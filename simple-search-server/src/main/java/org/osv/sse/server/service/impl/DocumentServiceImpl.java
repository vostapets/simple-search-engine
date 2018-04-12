package org.osv.sse.server.service.impl;

import java.util.UUID;

import org.osv.sse.server.repository.DocumentRepository;
import org.osv.sse.server.service.DocumentService;
import org.springframework.stereotype.Service;

@Service
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;

    public DocumentServiceImpl(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @Override
    public String get(UUID id) {
        return documentRepository.get(id);
    }

    @Override
    public UUID save(String document) {
        return documentRepository.save(document);
    }
}
