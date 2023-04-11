//package eu.getsoftware.onion.cleanarchitecture.usercreation.infastructure.controller;
//
//import static org.hamcrest.Matchers.*;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//import java.util.Set;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Nested;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//
//import eu.getsoftware.onion.cleanarchitecture.usercreation.infrastructure.controller.UserRegisterController;
//import eu.getsoftware.onion.cleanarchitecture.usercreation.main.OnionMainApplication;
//
//@WebMvcTest(UserRegisterController.class)
//@ContextConfiguration(classes = OnionMainApplication.class)
//@AutoConfigureMockMvc
//class ApiDtoEntityControllerIntegrationTest
//{
//  @Autowired private MockMvc mvc;
//
////  @MockBean private ApiDomainService service;
//
//  @Nested
//  class GetAll {
//
//    private ResultActions resultActions;
//
//    @BeforeEach
//    void setup() throws Exception {
//      ApiEntity apiEntity0 = new ApiEntity("Contacts API", "https://example.com/contacts");
//      ApiEntity apiEntity1 = new ApiEntity("Publishing API", "https://example.com/publishing");
//      when(service.findAll()).thenReturn(Set.of(apiEntity0, apiEntity1));
//
//      resultActions = mvc.perform(get("/apis"));
//    }
//
//    @Test
//    void returns200() throws Exception {
//      resultActions.andExpect(status().isOk());
//    }
//
//    @Test
//    void returnsJson() throws Exception {
//      resultActions.andExpect(header().string("content-type", "application/json"));
//    }
//
//    @Test
//    void containsItems() throws Exception {
//      resultActions.andExpect(jsonPath("$.apis").isArray());
//    }
//
//    @Test
//    void containsApiData() throws Exception {
//      resultActions
//          .andExpect(
//              jsonPath("$.apis[*].name")
//                  .value(containsInAnyOrder("Contacts API", "Publishing API")))
//          .andExpect(
//              jsonPath("$.apis[*].url")
//                  .value(
//                      containsInAnyOrder(
//                          "https://example.com/contacts", "https://example.com/publishing")));
//    }
//  }
//}
