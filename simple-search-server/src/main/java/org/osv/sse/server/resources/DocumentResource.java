package org.osv.sse.server.resources;

import java.util.UUID;

import org.osv.sse.server.service.DocumentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/document")
public class DocumentResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(DocumentResource.class);
    private final DocumentService documentService;

    @Autowired
    public DocumentResource(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping("/{id}")
    public String get(@PathVariable(name = "id") UUID id) {
        LOGGER.info("Get document by id {}.", id);
        return documentService.get(id);
    }

    @PostMapping
    public ResponseEntity<UUID> create(UriComponentsBuilder b, @RequestBody String document) {
        LOGGER.info("Create document {}.", document);
        UUID id = documentService.save(document);
        UriComponents uriComponents = b.path("/document/{id}").buildAndExpand(id);
        return ResponseEntity.created(uriComponents.toUri()).build();
    }
}
