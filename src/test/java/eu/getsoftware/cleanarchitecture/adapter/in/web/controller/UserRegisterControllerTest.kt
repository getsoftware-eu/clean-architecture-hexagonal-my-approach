//package eu.getsoftware.cleanarchitecture.adapter.`in`.web.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper
//import eu.getsoftware.cleanarchitecture.application.port.`in`.user.iportservice.dto.UserRequestUseCaseDTO
//import eu.getsoftware.cleanarchitecture.application.port.`in`.user.iportservice.dto.UserResponseClientDTO
//import eu.getsoftware.cleanarchitecture.application.port.`in`.user.iusecase.IUserExternalClientUseCase
//import eu.getsoftware.cleanarchitecture.MainApplication
//import org.junit.jupiter.api.Assertions
//import org.junit.jupiter.api.Test
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.context.SpringBootTest
//
////@WebMvcTest(controllers = UserRegisterController.class)
//@SpringBootTest(classes = [MainApplication::class])
//class UserRegisterControllerTest(@Autowired val userInputUseCase: IUserExternalClientUseCase) {
//    
////    @MockkBean
////    lateinit var persistService: DomainEntityGatewayServiceAbstr<UserMappedDBEntity>
//    val mapper = /*jackson*/ObjectMapper();
//    
//    val sampleRequestDTO = UserRequestUseCaseDTO(1, "name", "user", "password", "-")
//
////    @Test
////    fun givenExistingBankAccount_whenGetRequest_thenReturnsBankAccountJsonWithStatus200() {
////        every { hotelService.getHotelById(HotelDomainEntityId("1")) } returns sampleHotelDTO;
////
////        mockMvc.perform(get("/api/v1/hotels?id=1"))
////            .andExpect(status().isOk)
////            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
////            .andExpect(jsonPath("$.bankCode").value("ING"));
////    }
////
////    @Test
////    fun givenBankAccountDoesntExist_whenGetRequest_thenReturnsStatus400() {
////        every { hotelService.getHotelById(HotelDomainEntityId("2")) } returns null;
////
////        mockMvc.perform(get("/api/v1/hotels?id=2"))
////            .andExpect(status().isBadRequest());
////    }
//
//    @Test fun doTest() {
//        val responseDTO: UserResponseClientDTO = userInputUseCase.registerNewUser(sampleRequestDTO)
//
//        Assertions.assertNotEquals(responseDTO, null)
//    }
//    
////    @Test
////    fun whenPostRequestWithBankAccountJson_thenReturnsStatus200() {
//////        every { hotelService.addHotel(sampleRequestDTO) } returns sampleRequestDTO;
////
////        mockMvc.perform(post("/api/v1/register/user").content(mapper.writeValueAsString(sampleRequestDTO)).contentType(MediaType.APPLICATION_JSON))
////            .andExpect(status().isOk)
////            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
////            .andExpect(jsonPath("$.bankCode").value("ING"));
////    }
//}