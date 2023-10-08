package exercise.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import exercise.model.Task;
import exercise.repository.TaskRepository;
import net.datafaker.Faker;
import org.instancio.Instancio;
import org.instancio.Select;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Map;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// END
@SpringBootTest
@AutoConfigureMockMvc
public class TaskControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    TaskRepository taskRepository;
    @Autowired
    Faker faker;

    @Autowired
    ObjectMapper om;


    @BeforeEach
    public void initRepository() {
        Task task = Instancio.of(Task.class)
                .set(Select.field(Task::getId), 1)
                .supply(Select.field(Task::getTitle), () -> {
                    return "Marketing Administrator";
                })
                .supply(Select.field(Task::getDescription), () -> {
                    return "test";
                })
                .create();
        Task task2 = Instancio.of(Task.class)
                .set(Select.field(Task::getId), 2)
                .supply(Select.field(Task::getTitle), () -> {
                    return "Human Manufacturing Designer";
                })
                .supply(Select.field(Task::getDescription), () -> {
                    return "test";
                })
                .create();
//        taskRepository.deleteById(task.getId());
//        taskRepository.deleteById(task2.getId());
        taskRepository.save(task);
        taskRepository.save(task2);
        System.out.println("init task run");
    }


    @Test
    @Order(1)
    public void testGetTasks() throws Exception {
        MvcResult result = mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andReturn();
        String response = result.getResponse().getContentAsString();
        assertThatJson(response).whenIgnoringPaths("[*].updatedAt", "[*].createdAt")
                .isEqualTo("[{\"id\":1,\"title\":\"Marketing Administrator\",\"description\":\"test\",\"updatedAt\":\"2023-10-08T10:42:36.459+00:00\",\"createdAt\":\"2023-10-08T10:42:36.459+00:00\"}," +
                        "{\"id\":2,\"title\":\"Human Manufacturing Designer\",\"description\":\"test\",\"updatedAt\":\"2023-10-08T10:42:36.556+00:00\",\"createdAt\":\"2023-10-08T10:42:36.556+00:00\"}]\n"
                );
        System.out.println(response);

    }

    @Test
    @Order(2)
    public void testGetTaskById() throws Exception {
        MvcResult result = mockMvc.perform(get("/tasks/1"))
                .andExpect(status().isOk())
                .andReturn();
        String response = result.getResponse().getContentAsString();
        assertThatJson(response).whenIgnoringPaths("updatedAt", "createdAt")
                .isEqualTo("{\"id\":1,\"title\":\"Marketing Administrator\",\"description\":\"test\"," +
                        "\"updatedAt\":\"2023-10-08T10:42:36.459+00:00\",\"createdAt\":\"2023-10-08T10:42:36.459+00:00\"},"
                );
        assertThatJson(response).and(a -> a.node("title").isEqualTo("Marketing Administrator"));


        System.out.println(response);

    }

    //

    @Test
    @Order(3)
    public void testPostTask() throws Exception {
        var request = post("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(Map.of("title", "test title", "description", "testDesc")));
        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andReturn();
        Task task = om.readValue(result.getResponse().getContentAsString(), Task.class);
        Task savedTask = taskRepository.findById(task.getId()).orElse(new Task());

        assertThat(savedTask.getTitle()).isEqualTo("test title");

        ;
    }

    @Test
    @Order(4)
    public void testPutTask() throws Exception {
        var request = put("/tasks/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(Map.of("title", "test title upd", "description", "testDesc")));
        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();
        Task task = om.readValue(result.getResponse().getContentAsString(), Task.class);
        Task savedTask = taskRepository.findById(task.getId()).orElse(new Task());

        assertThat(savedTask.getTitle()).isEqualTo("test title upd");

    }

}

