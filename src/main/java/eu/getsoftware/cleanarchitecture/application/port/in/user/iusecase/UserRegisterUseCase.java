package eu.getsoftware.cleanarchitecture.application.port.in.user.iusecase;

import eu.getsoftware.cleanarchitecture.application.port.in.user.dto.UserClientDTO;
import eu.getsoftware.cleanarchitecture.application.port.in.user.dto.UserRegisterRequestUseCaseDTO;

/**
 * eu: in.port only DTO, no inner entity
 * Commands идут через UseCase. UseCase - содержит бизнес-логику и взаимодействует с репозиториями.
 * 
 * DTO creation for Grenzen
 * The boundaries are contracts defining how components can interact. 
 * The input boundary exposes our use case to outer layers:
 */
public interface UserRegisterUseCase
{
    UserClientDTO execute(UserRegisterRequestUseCaseDTO requestModel);
}
