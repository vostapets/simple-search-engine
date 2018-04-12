package org.osv.sse.server.resources;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.osv.sse.server.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(DocumentResource.class)
public class DocumentResourceTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private DocumentService service;

    @Test
    public void givenDocumentWhenGetByIdThenReturnDocument()
            throws Exception {
        UUID uuid = UUID.randomUUID();
        BDDMockito.given(service.get(uuid))
                  .willReturn("get document");

        mvc.perform(get("/document/{id}", uuid)
                .contentType(MediaType.APPLICATION_JSON))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$", is("get document")));
    }

    @Test
    public void givenDocumentWhenSaveThenReturnLocation()
            throws Exception {
        UUID uuid = UUID.randomUUID();
        BDDMockito.given(service.save("post document"))
                  .willReturn(uuid);

        mvc.perform(post("/document")
                .contentType(MediaType.APPLICATION_JSON)
                .content("post document"))
           .andExpect(status().isCreated())
           .andExpect(header().string("Location", containsString("/document/" + uuid)));
    }
}