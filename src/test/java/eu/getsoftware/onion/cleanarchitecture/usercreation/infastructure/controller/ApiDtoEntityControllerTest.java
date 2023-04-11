//package eu.getsoftware.onion.cleanarchitecture.usercreation.infastructure.controller;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.Mockito.when;
//
//import java.util.Set;
//
//import org.junit.jupiter.api.Nested;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import eu.getsoftware.onion.cleanarchitecture.usercreation.infrastructure.controller.UserRegisterController;
//
//@ExtendWith(MockitoExtension.class)
//class ApiDtoEntityControllerTest
//{
//  @Mock private ApiDomainService service;
//
//  @InjectMocks private UserRegisterController controller;
//
//  @Nested
//  class GetAll {
//    /*
//    This is a scaffolding test that can be removed when we have really implemented it
//     */
//    @Test
//    void itReturnsContainer() {
//      ApiResponseContainer actual = controller.getAll();
//
//      assertThat(actual).isNotNull();
//    }
//
//    @Test
//    void itReturnsInContainer() {
//      ApiEntity apiEntity =
//          new ApiEntity("The name", "https://example.foo/bar");
//      when(service.findAll()).thenReturn(Set.of(apiEntity));
//      ApiDto expected = new ApiDto("The name", "https://example.foo/bar");
//
//      ApiResponseContainer actual = controller.getAll();
//
//      assertThat(actual.getApis()).usingRecursiveFieldByFieldElementComparator().contains(expected);
//    }
//  }
//}
