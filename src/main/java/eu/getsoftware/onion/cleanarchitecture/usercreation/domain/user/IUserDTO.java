package eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user;

import java.time.LocalDateTime;

public interface IUserDTO
{
    String getName();
    String getPassword();
    LocalDateTime getCreationTime();
}
