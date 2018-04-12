package org.osv.sse.server.service.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.osv.sse.server.exception.SearchRuntimeException;
import org.osv.sse.server.repository.DocumentRepository;
import org.osv.sse.server.service.SearchService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SearchServiceImplTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Mock
    private DocumentRepository documentRepository;
    private SearchService searchService;

    @Before
    public void setUp() throws Exception {
        searchService = new SearchServiceImpl(documentRepository);
        when(documentRepository.getAll())
                .thenReturn(Arrays.asList("aaa bbb ccc", "aaa ccc", "bbb ccc"));
    }

    @Test
    public void findByOneTokenTest() {
        List<String> actual = searchService.find(Collections.singletonList("aaa"));
        assertEquals(2, actual.size());
        assertTrue(actual.contains("aaa bbb ccc"));
        assertTrue(actual.contains("aaa ccc"));
    }

    @Test
    public void findByManyOneTokenTest() {
        List<String> actual = searchService.find(Arrays.asList("bbb", "aaa"));
        assertEquals(1, actual.size());
        assertTrue(actual.contains("aaa bbb ccc"));
    }

    @Test
    public void findByManyTwoTokenTest() {
        List<String> actual = searchService.find(Arrays.asList("ccc", "aaa"));
        assertEquals(2, actual.size());
        assertTrue(actual.contains("aaa bbb ccc"));
        assertTrue(actual.contains("aaa ccc"));
    }

    @Test
    public void findWithEmptyTokenTest() {
        exception.expect(SearchRuntimeException.class);
        exception.expectMessage("Error empty tokens");
        searchService.find(Collections.emptyList());
    }
}