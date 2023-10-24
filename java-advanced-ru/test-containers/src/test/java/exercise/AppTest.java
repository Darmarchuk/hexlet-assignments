package exercise;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import org.springframework.http.MediaType;

import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.junit.jupiter.Container;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.testcontainers.containers.PostgreSQLContainer;

@SpringBootTest
@AutoConfigureMockMvc

@Testcontainers
@Transactional

public class AppTest {

    @Autowired
    private MockMvc mockMvc;

    @Container
    private static PostgreSQLContainer database=new PostgreSQLContainer<>("postgres")
            .withDatabaseName("postres")
            .withUsername("sa")
            .withPassword("sa")
            .withInitScript("init.sql");

     @DynamicPropertySource
     public static void setProperties(DynamicPropertyRegistry registry){
         registry.add("spring.datasource.url", database::getJdbcUrl );
         registry.add("spring.datasource.username", database::getUsername );
         registry.add("spring.datasource.password", database::getPassword );
     }

    @Test
    void testGetPersons() throws Exception {
         MockHttpServletResponse responseGet=mockMvc
                 .perform(get("/people")
                 ).andReturn()
                 .getResponse();
         assertThat(responseGet.getStatus()).isEqualTo(200);
        System.out.println( responseGet.getContentAsString());
        JSONAssert.assertEquals( responseGet.getContentAsString(),"[{\"id\":1,\"firstName\":\"John\",\"lastName\":\"Smith\"},{\"id\":2,\"firstName\":\"Jack\",\"lastName\":\"Doe\"},{\"id\":3,\"firstName\":\"Jassica\",\"lastName\":\"Simpson\"},{\"id\":4,\"firstName\":\"Robert\",\"lastName\":\"Lock\"}]", JSONCompareMode.LENIENT);
    }


    @Test
    void testGetPersonById() throws Exception {
        MockHttpServletResponse responseGet=mockMvc
                .perform(get("/people/{id}",1)
                ).andReturn()
                .getResponse();
        assertThat(responseGet.getStatus()).isEqualTo(200);
        System.out.println( responseGet.getContentAsString());
        JSONAssert.assertEquals( responseGet.getContentAsString(),"{\"id\":1,\"firstName\":\"John\",\"lastName\":\"Smith\"}", JSONCompareMode.LENIENT);
    }


    @Test
    void testUpdatePerson() throws Exception{
         MockHttpServletResponse responseUpdate=mockMvc
                 .perform(patch("/people/{id}",1)
                         .contentType(MediaType.APPLICATION_JSON)
                         .content("{\"firstName\": \"UnJackson\", \"lastName\": \"UnBind\"}"))
                 .andReturn()
                 .getResponse();

        assertThat(responseUpdate.getStatus()).isEqualTo(200);

        MockHttpServletResponse responseGet=mockMvc
                .perform(get("/people/{id}",1)
                ).andReturn()
                .getResponse();

        System.out.println( responseGet.getContentAsString());
        JSONAssert.assertEquals( responseGet.getContentAsString(),"{\"id\":1,\"firstName\": \"UnJackson\", \"lastName\": \"UnBind\"}", JSONCompareMode.LENIENT);
    }

    @Test
    void testCreatePerson() throws Exception {
        MockHttpServletResponse responsePost = mockMvc
            .perform(
                post("/people")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"firstName\": \"Jackson\", \"lastName\": \"Bind\"}")
            )
            .andReturn()
            .getResponse();

        assertThat(responsePost.getStatus()).isEqualTo(200);

        MockHttpServletResponse response = mockMvc
            .perform(get("/people"))
            .andReturn()
            .getResponse();

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getContentType()).isEqualTo(MediaType.APPLICATION_JSON.toString());
        assertThat(response.getContentAsString()).contains("Jackson", "Bind");
    }
}
