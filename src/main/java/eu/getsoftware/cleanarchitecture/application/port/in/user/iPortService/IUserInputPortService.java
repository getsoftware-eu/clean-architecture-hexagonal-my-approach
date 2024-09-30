package eu.getsoftware.cleanarchitecture.application.port.in.user.iPortService;

import eu.getsoftware.cleanarchitecture.application.port.in.user.iPortService.dto.RequestUserPortDTO;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iPortService.dto.ResponseUserPortDTO;

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
