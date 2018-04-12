package org.osv.sse.server.service.impl;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.osv.sse.server.repository.DocumentRepository;
import org.osv.sse.server.service.DocumentService;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DocumentServiceImplTest {

    @Mock
    private DocumentRepository repository;
    private DocumentService documentService;

    @Before
    public void setUp() {
        documentService = new DocumentServiceImpl(repository);
    }

    @Test
    public void getTest() {
        UUID id = UUID.randomUUID();
        when(documentService.get(id)).thenReturn("get document");
        String document = documentService.get(id);
        assertEquals("get document", document);
    }

    @Test
    public void saveTest() {
        UUID expected = UUID.randomUUID();
        when(documentService.save("save document"))
                .thenReturn(expected);
        UUID actual = documentService.save("save document");
        assertEquals(expected, actual);
    }
}