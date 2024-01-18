package eu.getsoftware.onion.cleanarchitecture.usercreation.infrastructure.service;

import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.IUserRegisterApplicationDsGatewayService;
import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model.UserDsRequestApplicationModelDTO;
import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model.UserMapper;
import eu.getsoftware.onion.cleanarchitecture.usercreation.infrastructure.error.UserNotFoundException;
import eu.getsoftware.onion.cleanarchitecture.usercreation.infrastructure.model.UserDataMapperEntity;
import eu.getsoftware.onion.cleanarchitecture.usercreation.infrastructure.repository.JpaUserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * No own methods, just implement interface contract
 * 
 * no creation hier, controller direct to application layer (registerGateway)
 */
@Service
class JpaUserRegisterApplicationService /*extends IRegisterService<UserDataMapperEntity, UserDsRequestApplicationModelDTO>*/ implements IUserRegisterApplicationDsGatewayService
{
    private final UserMapper userMapper;
    private final JpaUserRepository repository;

    JpaUserRegisterApplicationService(UserMapper userMapper, JpaUserRepository repository){
//        super(userMapper, repository);
        this.userMapper = userMapper;
        this.repository = repository;
    }
    
    @Override
    public boolean existsByName(String name) {
        return repository.findByName(name).isPresent();
    }    
    
    @Override
    public UserDsRequestApplicationModelDTO getById(long id) {
        Optional<UserDataMapperEntity> optionalUser = repository.findById(id);
        
        if(optionalUser.isPresent())
        {
            UserDataMapperEntity user = optionalUser.get();
            UserDsRequestApplicationModelDTO dto = userMapper.toDsRequestDTO(user);
//            return new UserDsRequestApplicationModelDTO(user.getName(), user.getPassword(), user.getCreationTime());
            return dto;
        }
        else throw new UserNotFoundException(id);
    }
    
    @Override
    public void save(UserDsRequestApplicationModelDTO requestModel) {

        UserDataMapperEntity accountDataMapper = new UserDataMapperEntity();
        userMapper.updateAllFromDto(requestModel, accountDataMapper);
//        UserDataMapperEntity accountDataMapper = new UserDataMapperEntity(requestModel.name(), requestModel.password(), requestModel.creationTime());

        repository.save(accountDataMapper);
    }

//    @Override
//    public Class<UserDataMapperEntity> getAssetClass() {
//        return UserDataMapperEntity.class;
//    }
}
