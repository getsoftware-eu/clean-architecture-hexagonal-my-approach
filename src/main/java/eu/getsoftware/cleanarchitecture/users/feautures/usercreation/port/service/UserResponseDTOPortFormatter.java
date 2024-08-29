package eu.getsoftware.cleanarchitecture.users.feautures.usercreation.port.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import eu.getsoftware.cleanarchitecture.users.feautures.usercreation.port.dto.IUserResponseDTOPortPresenter;
import eu.getsoftware.cleanarchitecture.users.feautures.usercreation.port.dto.ResponseUserPortDTO;

/**
 * Eugen: //TODO: We assign HttpStatus to every error.
 */
@Component
public class UserResponseDTOPortFormatter implements IUserResponseDTOPortPresenter
{
    @Override
    public ResponseUserPortDTO prepareSuccessView(ResponseUserPortDTO response) {
        LocalDateTime responseTime = LocalDateTime.parse(response.creationTimeStr());
        String formattedResponseTime = responseTime.format(DateTimeFormatter.ofPattern("hh:mm:ss"));
        
        return response.withCreationTime(formattedResponseTime);
    }

    
    @Override
    public ResponseUserPortDTO prepareFailView(String error) {
        //TODO usage of all possible exceptions with different returning HttpCodes to client!
        throw new ResponseStatusException(HttpStatus.CONFLICT, error);
    }
}