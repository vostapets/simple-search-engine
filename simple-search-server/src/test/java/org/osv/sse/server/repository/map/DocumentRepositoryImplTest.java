package org.osv.sse.server.repository.map;

import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.osv.sse.server.repository.DocumentRepository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DocumentRepositoryImplTest {

    private DocumentRepository repository = new DocumentRepositoryImpl();

    @Test
    public void saveAndGet() {
        UUID key = repository.save("document");
        String actual = repository.get(key);
        assertEquals("document", actual);
    }

    @Test
    public void getAll() {
        repository.save("document 1");
        repository.save("document 2");
        repository.save("document 3");
        List<String> actual = repository.getAll();
        assertEquals(3, actual.size());
        assertTrue(actual.contains("document 1"));
        assertTrue(actual.contains("document 2"));
        assertTrue(actual.contains("document 3"));
    }
}