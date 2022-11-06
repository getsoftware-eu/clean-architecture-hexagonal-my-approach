package eu.getsoftware.onion.cleanarchitecture.usercreation.infrastructure.service;

import org.springframework.stereotype.Service;

import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model.UserDsRequestModel;
import eu.getsoftware.onion.cleanarchitecture.usercreation.infrastructure.model.UserDataMapper;
import eu.getsoftware.onion.cleanarchitecture.usercreation.infrastructure.repository.JpaUserRepository;
import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.UserRegisterDsGateway;

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
