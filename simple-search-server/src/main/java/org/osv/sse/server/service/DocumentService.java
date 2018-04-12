package org.osv.sse.server.service;

import java.util.UUID;

public interface DocumentService {

    String get(UUID id);

    UUID save(String document);
}
