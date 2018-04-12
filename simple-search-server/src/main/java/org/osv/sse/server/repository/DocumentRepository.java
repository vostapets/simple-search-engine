package org.osv.sse.server.repository;

import java.util.List;
import java.util.UUID;

public interface DocumentRepository {

    UUID save(String documnet);

    String get(UUID key);

    List<String> getAll();
}
