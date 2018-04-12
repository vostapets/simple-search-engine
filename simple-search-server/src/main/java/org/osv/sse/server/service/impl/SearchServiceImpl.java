package org.osv.sse.server.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.osv.sse.server.exception.SearchRuntimeException;
import org.osv.sse.server.repository.DocumentRepository;
import org.osv.sse.server.service.SearchService;
import org.springframework.stereotype.Service;

@Service
public class SearchServiceImpl implements SearchService {

    private final DocumentRepository documentRepository;

    public SearchServiceImpl(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @Override
    public List<String> find(List<String> tokens) {
        if (tokens == null || tokens.isEmpty()) {
            throw new SearchRuntimeException("Error empty tokens.");
        }
        return documentRepository.getAll()
                                 .stream()
                                 .filter(document -> tokens.stream()
                                                           .allMatch(document::contains))
                                 .collect(Collectors.toList());
    }
}
