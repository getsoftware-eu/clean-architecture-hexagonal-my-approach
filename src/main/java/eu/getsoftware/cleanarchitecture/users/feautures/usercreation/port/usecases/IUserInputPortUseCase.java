package eu.getsoftware.cleanarchitecture.users.feautures.usercreation.port.usecases;

import eu.getsoftware.cleanarchitecture.users.feautures.usercreation.port.dto.RequestUserPortDTO;
import eu.getsoftware.cleanarchitecture.users.feautures.usercreation.port.dto.ResponseUserPortDTO;

/**
 * DTO creation for Grenzen
 * The boundaries are contracts defining how components can interact. 
 * The input boundary exposes our use case to outer layers:
 */
public interface IUserInputPortUseCase
{
    ResponseUserPortDTO create(RequestUserPortDTO requestModel);
	
	ResponseUserPortDTO findById(RequestUserPortDTO requestModel, long userId);
}
