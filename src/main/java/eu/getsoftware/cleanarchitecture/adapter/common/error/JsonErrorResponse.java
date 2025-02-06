package eu.getsoftware.cleanarchitecture.adapter.common.error;

import lombok.Data;

@Data
public class JsonErrorResponse {
	
	private String error;
	private String message;

	public JsonErrorResponse(String error, String message)
	{
		this.error = error;
		this.message = message;
	}

	public String toJsonString() {
		return toString();
	}
}