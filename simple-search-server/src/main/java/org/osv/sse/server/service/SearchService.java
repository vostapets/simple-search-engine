package org.osv.sse.server.service;

import java.util.List;

public interface SearchService {

    List<String> find(List<String> tokens);
}
