package eu.getsoftware.onion.cleanarchitecture.usercreation.infrastructure;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.UserOutputApplicationPresenter;
import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model.UserResponseApplicationModel;

public class UserResponseFormatter implements UserOutputApplicationPresenter
{
    @Override
    public UserResponseApplicationModel prepareSuccessView(UserResponseApplicationModel response) {
        LocalDateTime responseTime = LocalDateTime.parse(response.getCreationTime());
        String formattedResponseTime = responseTime.format(DateTimeFormatter.ofPattern("hh:mm:ss"));
        
        return response.withCreationTime(formattedResponseTime);
    }

    @Override
    public UserResponseApplicationModel prepareFailView(String error) {
        throw new ResponseStatusException(HttpStatus.CONFLICT, error);
    }
}
