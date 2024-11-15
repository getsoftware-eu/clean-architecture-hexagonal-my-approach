package eu.getsoftware.cleanarchitecture.adapter.out;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import eu.getsoftware.cleanarchitecture.application.port.in.user.iportservice.dto.UserClientDTO;
import eu.getsoftware.cleanarchitecture.application.port.out.user.IUserResponseDTOPortPresenter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

/**
 * Eugen: //TODO: We assign HttpStatus to every error.
 */
@Component
public class UserResponseDTOPortFormatter implements IUserResponseDTOPortPresenter<UserClientDTO>
{
    @Override
    public UserClientDTO prepareSuccessView(UserClientDTO response) {
        LocalDateTime responseTime = LocalDateTime.parse(response.creationTime());
        String formattedResponseTime = responseTime.format(DateTimeFormatter.ofPattern("hh:mm:ss"));
        
        return response.withCreationTime(formattedResponseTime);
    }

    
    @Override
    public UserClientDTO prepareFailView(String error) {
        //TODO usage of all possible exceptions with different returning HttpCodes to client!
        throw new ResponseStatusException(HttpStatus.CONFLICT, error);
    }
}
