package org.osv.sse.server.resources;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.osv.sse.server.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(SearchResource.class)
public class SearchResourceTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private SearchService service;

    @Test
    public void givenFindResultsWhenFindThenReturnJsonArrayOfResults()
            throws Exception {

        BDDMockito.given(service.find(Arrays.asList("value1", "value2")))
                  .willReturn(Arrays.asList("result 1", "result 2"));

        mvc.perform(get("/search?token=value1&token=value2")
                .contentType(MediaType.APPLICATION_JSON))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$", hasSize(2)))
           .andExpect(jsonPath("$[0]", is("result 1")))
           .andExpect(jsonPath("$[1]", is("result 2")));
    }
}