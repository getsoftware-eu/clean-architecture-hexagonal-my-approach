package eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user;

import java.time.LocalDateTime;

/**
 * Interface for all boundary user-dto
 */
public interface IUserDTO
{
    String getName();
    String getPassword();
    LocalDateTime getCreationTime();
}
