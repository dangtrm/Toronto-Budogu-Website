package backend4413.dev.security.auth;

public class AuthResponse {

	private String accessToken;
	private String refreshToken;
	private String userId;

	public AuthResponse() {
	}

	public AuthResponse(String accessToken) {
		this.accessToken = accessToken;
	}
	
	public AuthResponse(String accessToken, String refreshToken) {
		this(accessToken);
		this.refreshToken = refreshToken;
	}
	
	public AuthResponse(String accessToken, String refreshToken, String userId) {
		this(accessToken, refreshToken);
		this.userId = userId;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
