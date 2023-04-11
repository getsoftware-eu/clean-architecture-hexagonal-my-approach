package eu.getsoftware.onion.cleanarchitecture.usercreation.infrastructure;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.UserOutputApplicationPresenter;
import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model.UserResponseApplicationModelDTO;

public class UserResponseFormatter implements UserOutputApplicationPresenter
{
    @Override
    public UserResponseApplicationModelDTO prepareSuccessView(UserResponseApplicationModelDTO response) {
        LocalDateTime responseTime = LocalDateTime.parse(response.creationTime());
        String formattedResponseTime = responseTime.format(DateTimeFormatter.ofPattern("hh:mm:ss"));
        
        return response.withCreationTime(formattedResponseTime);
    }

    @Override
    public UserResponseApplicationModelDTO prepareFailView(String error) {
        throw new ResponseStatusException(HttpStatus.CONFLICT, error);
    }
}
