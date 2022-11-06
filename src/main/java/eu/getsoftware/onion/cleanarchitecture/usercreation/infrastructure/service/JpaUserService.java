package eu.getsoftware.onion.cleanarchitecture.usercreation.infrastructure.service;

import com.baeldung.pattern.cleanarchitecture.usercreation.infrastructure.UserDataMapper;
import com.baeldung.pattern.cleanarchitecture.usercreation.infrastructure.repository.JpaUserRepository;
import com.baeldung.pattern.cleanarchitecture.usercreation.service.user.UserDsRequestModel;
import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.UserRegisterDsGateway;
import eu.getsoftware.onion.cleanarchitecture.usercreation.infrastructure.UserDataMapper;
import eu.getsoftware.onion.cleanarchitecture.usercreation.infrastructure.repository.JpaUserRepository;

class JpaUserService implements UserRegisterDsGateway
{

    final JpaUserRepository repository;

    JpaUserService(JpaUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean existsByName(String name) {
        return repository.existsById(name);
    }

    @Override
    public void save(UserDsRequestModel requestModel) {
        UserDataMapper accountDataMapper = new UserDataMapper(requestModel.getName(), requestModel.getPassword(), requestModel.getCreationTime());
        repository.save(accountDataMapper);
    }
}
