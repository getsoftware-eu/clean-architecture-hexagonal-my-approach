package eu.getsoftware.cleanarchitecture.application.domain.model.user;

/**
 * Interface for all boundary user-dto
 * 
 * EU: no getMethods, because RECORD implements it!!!
 */
public interface IUserDomainResponseDTO
{
    EntityIdentifier domainEntityId();
    String name();
    boolean isPasswordValid();
    String creationTime();

    IUserDomainResponseDTO withCreationTime(String formattedResponseTime);
    // String password(); //not for DTO result

}
