package eu.getsoftware.cleanarchitecture.adapter.in.web.controller;

import eu.getsoftware.cleanarchitecture.application.domain.model.user.UserDomainId;
import eu.getsoftware.cleanarchitecture.application.port.in.user.dto.UserClientDTO;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iusecase.UserCrudUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserCrudController.class)
public class UserRegisterControllerMVCTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserCrudUseCase userUseCase;

    private String domainEntityId = "550e8400-e29b-41d4-a716-446655440022";

    @Test
    public void testCreateUser() throws Exception {
        String requestBody = """
            {
              "name": "Jane Doe",
              "email": "jane.doe@example.com",
              "password": "password123"
            }
        """;

        mockMvc.perform(put("/api/v1/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk());
    }

    @Test
    public void testFindById() throws Exception {
        UserClientDTO mockResponse = new UserClientDTO(
                UserDomainId.from(domainEntityId),
                "Jane Doe",
                "jane.doe@example.com"
        );

        when(userUseCase.findExistingUserByDomainId(any())).thenReturn(mockResponse);

        mockMvc.perform(get("/api/v1/user/" + domainEntityId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Jane Doe"))
                .andExpect(jsonPath("$.email").value("jane.doe@example.com"));
    }

    @Test
    public void testUpdateAddress() throws Exception {
        String requestAddressBody = """
            {
              "city": "New York",
              "street": "5th Avenue"
            }
        """;

        UserClientDTO mockResponse = new UserClientDTO(
                UserDomainId.from(domainEntityId),
                "Jane Doe",
                "jane.doe@example.com");

        when(userUseCase.updateUserAddress(any(), any())).thenReturn(mockResponse);

        mockMvc.perform(patch("/api/v1/user/" + domainEntityId + "/address")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestAddressBody))
                .andExpect(status().isOk());
    }
}
