package eu.getsoftware.onion.cleanarchitecture.usercreation.infrastructure.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model.UserDsRequestApplicationModelDTO;
import eu.getsoftware.onion.cleanarchitecture.usercreation.infrastructure.error.UserNotFoundException;
import eu.getsoftware.onion.cleanarchitecture.usercreation.infrastructure.model.UserDataMapperEntity;
import eu.getsoftware.onion.cleanarchitecture.usercreation.infrastructure.repository.JpaUserRepository;
import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.IUserRegisterApplicationDsGatewayService;
import lombok.RequiredArgsConstructor;

/**
 * No own methods, just implement interface contract
 * 
 * no creation hier, controller direct to application layer (registerGateway)
 */
@Service
@RequiredArgsConstructor
class JpaUserRegisterApplicationService implements IUserRegisterApplicationDsGatewayService
{
    private final JpaUserRepository repository;

    @Override
    public boolean existsByName(String name) {
        return repository.existsById(name);
    }    
    
    @Override
    public UserDsRequestApplicationModelDTO getById(long id) {
        Optional<UserDataMapperEntity> optionalUser = repository.findById(String.valueOf(id));
        
        if(optionalUser.isPresent())
        {
            UserDataMapperEntity user = optionalUser.get();
            return new UserDsRequestApplicationModelDTO(user.getName(), user.getPassword(), user.getCreationTime());
        }
        else throw new UserNotFoundException(id);
    }
    
    @Override
    public void save(UserDsRequestApplicationModelDTO requestModel) {
        UserDataMapperEntity accountDataMapper = new UserDataMapperEntity(requestModel.name(), requestModel.password(), requestModel.creationTime());
        repository.save(accountDataMapper);
    }
}
