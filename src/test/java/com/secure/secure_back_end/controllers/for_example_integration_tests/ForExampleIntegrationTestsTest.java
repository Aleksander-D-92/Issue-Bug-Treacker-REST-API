package com.secure.secure_back_end.controllers.for_example_integration_tests;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
public class ForExampleIntegrationTestsTest
{
    private static final String HELLO_URL = "/integration-test-example/hello/{userId}";
    private static final String JSON_URL = "/integration-test-example/json";
    private static final String privateResource_URL = "/integration-test-example/users-only";
    private JSONObject jsonObject;
    private ObjectMapper mapper;
    String jsonString;
    private MockMvc mockMvc;
    @InjectMocks
    private ForExampleIntegrationTests forExampleIntegrationTests; //@Autowire controller as a dependency

    @Before
    public void setUp() throws Exception
    {
        mapper = new ObjectMapper();
        this.jsonObject = new JSONObject("aaaaa", "aaaaa");
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        jsonString = mapper.writeValueAsString(jsonObject);
        mockMvc = MockMvcBuilders.standaloneSetup(forExampleIntegrationTests).build();

    }

    //GET method with request params
    @Test
    public void getHello() throws Exception
    {
        mockMvc
                .perform(get(HELLO_URL, "2"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("hello 2"));
    }

    //GET method that returns Java class as JSON
    @Test
    public void returnJSON() throws Exception
    {
        mockMvc
                .perform(get(JSON_URL).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", Matchers.is("SomeTitle")))
                .andExpect(jsonPath("$.value", Matchers.is("SomeValue")))
                .andExpect(jsonPath("$.*", Matchers.hasSize(2))); //$.* means how many JSON keys does it have
    }

    //POST method that validates
    @Test
    public void postJSON() throws Exception
    {

        mockMvc.perform(post("/integration-test-example/json")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(jsonObject))).andExpect(status().isOk());

    }
    //post for private resource
    @Test
    public void privateResource() throws Exception
    {
        mockMvc.perform(get(privateResource_URL))
                .andExpect(status().isOk())
                .andExpect(content().string("Private resource"));
    }
}
