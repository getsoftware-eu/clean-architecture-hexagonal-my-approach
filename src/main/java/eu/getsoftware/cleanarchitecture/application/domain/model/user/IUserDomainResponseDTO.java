package eu.getsoftware.cleanarchitecture.application.domain.model.user;

/**
 * Interface for all boundary user-dto
 * 
 * EU: no getMethods, because RECORD implements it!!!
 */
public interface IUserDomainResponseDTO
{
    String name();
    boolean isPasswordValid();
    // String password(); //not for DTO result

}
