package eu.getsoftware.cleanarchitecture.adapter.out;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import eu.getsoftware.cleanarchitecture.application.domain.model.domain.BusinessException;
import eu.getsoftware.cleanarchitecture.application.domain.model.user.IUserDomainResponseDTO;
import eu.getsoftware.cleanarchitecture.application.port.out.user.IUserResponseDTOPortPresenter;
import eu.getsoftware.cleanarchitecture.common.error.JsonErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

/**
 * Eugen: //TODO: We assign HttpStatus to every error.
 */
@Component
public class UserResponseDTOPortFormatter implements IUserResponseDTOPortPresenter
{
    @Override
    public IUserDomainResponseDTO prepareSuccessView(IUserDomainResponseDTO response) {
        LocalDateTime responseTime = LocalDateTime.parse(response.creationTime());
        String formattedResponseTime = responseTime.format(DateTimeFormatter.ofPattern("hh:mm:ss"));
        
        return response.withCreationTime(formattedResponseTime);
    }

    
    @Override
    public <T> T prepareFailView(String error) {
        //TODO usage of all possible exceptions with different returning HttpCodes to client!
        throw new ResponseStatusException(HttpStatus.CONFLICT, error);
    }   
    
    @Override
    public <T> T prepareFailView(Exception exception) {
        if (exception instanceof IllegalArgumentException || exception instanceof BusinessException) {
            throw new RuntimeException((new JsonErrorResponse("ERROR", exception.getMessage()).toJsonString()));
        } else {
            throw new RuntimeException((new JsonErrorResponse("SERVER_ERROR", "Unexpected failure").toJsonString()));
        }
    }
}
