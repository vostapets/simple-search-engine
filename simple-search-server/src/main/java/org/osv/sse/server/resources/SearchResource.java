package org.osv.sse.server.resources;

import java.util.List;

import org.osv.sse.server.service.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
public class SearchResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(SearchResource.class);

    private final SearchService searchService;

    @Autowired
    public SearchResource(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping
    public List<String> find(@RequestParam(name = "token") List<String> tokens) {
        LOGGER.info("Search by tokens {}.", tokens);
        return searchService.find(tokens);
    }
}
