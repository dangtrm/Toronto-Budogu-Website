package backend4413.dev.security.token;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(value = "Token")
public class Token {

	@Id
	private String id;
	private String token;
	private TokenType tokenType;
	private String userId;
	private boolean expired;
	private boolean revoked;

	public Token() {
	}

	public Token(String token, TokenType tokenType, String userId, boolean expired, boolean revoked) {
		super();
		this.token = token;
		this.tokenType = tokenType;
		this.userId = userId;
		this.expired = expired;
		this.revoked = revoked;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public TokenType getTokenType() {
		return tokenType;
	}

	public void setTokenType(TokenType tokenType) {
		this.tokenType = tokenType;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public boolean isExpired() {
		return expired;
	}

	public void setExpired(boolean expired) {
		this.expired = expired;
	}

	public boolean isRevoked() {
		return revoked;
	}

	public void setRevoked(boolean revoked) {
		this.revoked = revoked;
	}

}
