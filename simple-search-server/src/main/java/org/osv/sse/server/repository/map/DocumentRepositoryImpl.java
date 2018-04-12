package org.osv.sse.server.repository.map;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.osv.sse.server.repository.DocumentRepository;
import org.springframework.stereotype.Repository;

@Repository
public class DocumentRepositoryImpl implements DocumentRepository {

    private ConcurrentHashMap<UUID, String> storage = new ConcurrentHashMap<>();

    @Override
    public UUID save(String documnet) {
        UUID key = UUID.randomUUID();
        storage.put(key, documnet);
        return key;
    }

    @Override
    public String get(UUID key) {
        return storage.get(key);
    }

    @Override
    public List<String> getAll() {
        return new ArrayList<>(storage.values());
    }
}
