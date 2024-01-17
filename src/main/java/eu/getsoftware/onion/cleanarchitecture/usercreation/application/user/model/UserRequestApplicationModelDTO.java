package eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model;

/**
 * REQUEST Representation:
 * every requestDTO has the name of requester and its password???  requester is active-session-user
 * @param name
 * @param password
 */
public record UserRequestApplicationModelDTO(
    String name,
	String password
) {}
