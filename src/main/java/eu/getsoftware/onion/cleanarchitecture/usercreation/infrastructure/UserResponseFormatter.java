package eu.getsoftware.onion.cleanarchitecture.usercreation.infrastructure;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.IUserOutputApplicationPresenter;
import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model.UserResponseApplicationModelDTO;

/**
 * Eugen: //TODO: We assign HttpStatus to every error.
 */
public class UserResponseFormatter implements IUserOutputApplicationPresenter
{
    @Override
    public UserResponseApplicationModelDTO prepareSuccessView(UserResponseApplicationModelDTO response) {
        LocalDateTime responseTime = LocalDateTime.parse(response.creationTime());
        String formattedResponseTime = responseTime.format(DateTimeFormatter.ofPattern("hh:mm:ss"));
        
        return response.withCreationTime(formattedResponseTime);
    }

    
    @Override
    public UserResponseApplicationModelDTO prepareFailView(String error) {
        //TODO usage of all possible exceptions with different returning HttpCodes to client!
        throw new ResponseStatusException(HttpStatus.CONFLICT, error);
    }
}
