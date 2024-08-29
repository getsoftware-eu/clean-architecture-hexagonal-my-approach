package eu.getsoftware.cleanarchitecture.users.feautures.usercreation.port.dto;

/**
 * RESPONSE Representation:
 * every response has generated login of requester (session)??? and creationTime
 * @param login
 * @param creationTimeStr
 */
public record ResponseUserPortDTO(
      String login,
      String creationTimeStr
){
    public ResponseUserPortDTO withCreationTime(String newCreationTimeStr)
    {
        return new ResponseUserPortDTO(login, newCreationTimeStr);
    }
}