package exercise;

import exercise.model.Author;
import exercise.repository.AuthorRepository;
import exercise.specification.AuthorSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;

import org.openapitools.jackson.nullable.JsonNullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import exercise.repository.PostRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import exercise.dto.PostDTO;
import exercise.dto.CommentDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class PostsTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private AuthorRepository authorRepository;


    @Test
    public void testFindAuthorByLastName() {
        List<Author> authors = authorRepository.findByLastName("Armarchuk");
        System.out.println(Arrays.toString(authors.toArray()));

    }

    @Test
    public void testFindAuthorByFirstName() {
        List<Author> authors = authorRepository.findByFirstName("Dmitry");
        System.out.println(Arrays.toString(authors.toArray()));

    }

    @Test
    public void testFindByEmailCustom() {
       List<Author> authors= authorRepository.emailFinder("test@mail.ru");
       System.out.println(Arrays.toString(authors.toArray()));
    }

    @Test
    public void specificationTest(){
        AuthorSpecification as=new AuthorSpecification();
        Specification<Author> spec=  as.getAuthorByCriteriaFirstName("Dmitry");
        List<Author> authors=  authorRepository.findAll(spec);
        System.out.println(Arrays.toString(authors.toArray()));

    }


    @Test
    public void testIndex() throws Exception {

        var result = mockMvc.perform(get("/posts"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThatJson(body).isArray().hasSize(6);
    }

    @Test
    public void testShow() throws Exception {
        var testPostDTO = new PostDTO();


        testPostDTO.setId(Long.valueOf(3));
        testPostDTO.setTitle(JsonNullable.of("Post Title 3"));
        testPostDTO.setBody(JsonNullable.of("Post Body 3"));
        var testCommentDTO = new CommentDTO();
        testCommentDTO.setId(Long.valueOf(6));
        testCommentDTO.setBody("Comment Body 6");
        var comments = new ArrayList<CommentDTO>();
        comments.add(testCommentDTO);
        testPostDTO.setComments(comments);
        var result = mockMvc.perform(get("/posts/{id}", testPostDTO.getId()))
                .andExpect(status().isOk())
                .andReturn();
        var body = result.getResponse().getContentAsString();
        PostDTO post = om.readValue(body, new TypeReference<PostDTO>() {
        });
        assertThat(post).usingRecursiveComparison().isEqualTo(testPostDTO);
    }

    @Test
    public void testShowNegative() throws Exception {
        var result = mockMvc.perform(get("/posts/{id}", 100))
                .andExpect(status().isNotFound())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThat(body).contains("Post with id 100 not found");
    }

}
