package eu.getsoftware.cleanarchitecture.application.domain.usecase.user;

import eu.getsoftware.cleanarchitecture.application.port.in.user.iUseCase.IUserRegisterUseCase;
import eu.getsoftware.cleanarchitecture.adapter.out.persistence.outPortServiceImpl.RegisterUserPortServiceImpl;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iUseCase.dto.RequestUserUseCaseDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

//TODO: is it application layer??? usecase???

//TODO port это infrastruktur layer (technical impl) or just boundary обслуживание *(aka dto service?)

/**
 * No own methods, just implement interface contract
 * 
 * no creation hier, controller direct to application layer (registerGateway)
 * 
 * eu: now is anemic struktur, no extra interaktor logik here!
 * 
 * Eu ask: how can I protect this UseCaseImpl from other Layers (and force interface access), if it is a public bean ??????
 * - maybe I have to wear this implementation manual in @Configuration (and not declare @Service for s auto-bean-package-scan ???)
 */
@Service
@AllArgsConstructor
/*public restrict??*/ class UserRegisterUseCaseImpl /*extends IRegisterService<UserDataMapperEntity, UserDsRequestApplicationModelDTO>*/ implements IUserRegisterUseCase
{
    /**
     * we insert low-level-Service to useCase ?
     */
    private final RegisterUserPortServiceImpl registerUserPortServiceImpl;
    
    boolean someAbstratMethod(String identifier){

        if(identifier==null)
            abstractAction1();
        if(identifier.length()>10)
            abstractAction2();
        
        return true;
    };

    private void abstractAction2() {
    }

    private void abstractAction1() {
        
    }
    
    @Override
    public boolean existsByName(String name) {
        return registerUserPortServiceImpl.existsByName(name);
    }    
    
    @Override
    public RequestUserUseCaseDTO getById(long id) {
        return registerUserPortServiceImpl.getDTOById(id);
    }
    
    @Override
    public void save(RequestUserUseCaseDTO requestModel) {
        registerUserPortServiceImpl.persistFromDTO(requestModel);
    }

//    @Override
//    public Class<UserDataMapperEntity> getAssetClass() {
//        return UserDataMapperEntity.class;
//    }
}
