package eu.getsoftware.cleanarchitecture.application.domain.model.user;

import java.time.LocalDateTime;

/**
 * Interface for all boundary user-dto
 */
public interface IUserDTO
{
    String getName();
    String getPassword();
    LocalDateTime getCreationTime();

//    IUserDTO withPassword(String Password);
}
