package eu.getsoftware.cleanarchitecture.application.domain.model.user;

/**
 * Interface for all boundary user-dto
 * 
 * EU: no getMethods, because RECORD implements it!!!
 */
public interface IUserDomainRequestDTO
{
    String name();
    String password(); //not for DTO response

}
