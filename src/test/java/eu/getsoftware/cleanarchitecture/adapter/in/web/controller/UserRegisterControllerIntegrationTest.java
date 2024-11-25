package eu.getsoftware.cleanarchitecture.adapter.in.web.controller;

import kotlin.jvm.JvmStatic;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Testcontainers
@TestPropertySource(properties = "spring.liquibase.change-log=classpath:db/changelog/db.changelog-test-data.xml")
public class UserRegisterControllerIntegrationTest {

    @Container
    private static final PostgreSQLContainer<?> POSTGRES_CONTAINER = new PostgreSQLContainer<>("postgres:15.4")
            .withDatabaseName("testdb")
            .withUsername("testuser")
            .withPassword("testpass");

    @DynamicPropertySource
    static void setProperties(org.springframework.test.context.DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", POSTGRES_CONTAINER::getJdbcUrl);
        registry.add("spring.datasource.username", POSTGRES_CONTAINER::getUsername);
        registry.add("spring.datasource.password", POSTGRES_CONTAINER::getPassword);
    }

    @Autowired
    private MockMvc mockMvc;

    private String domainEntityId = "550e8400-e29b-41d4-a716-446655440022";

    @Test
    void testCreateUser() throws Exception {
        mockMvc.perform(put("/api/v1/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                          "name": "Jane Doe",
                          "email": "jane.doe@example.com",
                          "password": "password123"
                        }
                        """))
                .andExpect(status().isOk());
    }

    @Test
    void testFindById() throws Exception {
        // Prepopulate database or use separate test to create the user
        mockMvc.perform(get("/api/v1/user/"+domainEntityId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateAddress() throws Exception {
        mockMvc.perform(patch("/api/v1/user/"+domainEntityId+"/address")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                          "city": "dummy",
                          "street": "5th Avenue"
                        }
                        """))
                .andExpect(status().isOk());
    }
}
