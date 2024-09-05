package eu.getsoftware.cleanarchitecture.application.port.user.in.iservice;

import eu.getsoftware.cleanarchitecture.application.port.user.in.dto.RequestUserPortDTO;
import eu.getsoftware.cleanarchitecture.application.port.user.out.dto.ResponseUserPortDTO;

/**
 * DTO creation for Grenzen
 * The boundaries are contracts defining how components can interact. 
 * The input boundary exposes our use case to outer layers:
 */
public interface IUserInputPortService
{
    ResponseUserPortDTO create(RequestUserPortDTO requestModel);
	
	ResponseUserPortDTO findById(RequestUserPortDTO requestModel, long userId);
}
