package eu.getsoftware.cleanarchitecture.application.port.in.user.iPortService.dto;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;

/**
 * REQUEST Representation:
 * every requestDTO has the name of requester and its password???  requester is active-session-user
 * @param name
 * @param password
 */
public record RequestUserPortDTO(
    @NotNull
	String name,

	@Size(min = 8, message = "{validation.name.size.too_short}")
	@Size(max = 200, message = "{validation.name.size.too_long}")	
	String password
) {}
