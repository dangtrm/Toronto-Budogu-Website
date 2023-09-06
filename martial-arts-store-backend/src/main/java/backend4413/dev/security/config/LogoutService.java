package backend4413.dev.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import backend4413.dev.security.token.Token;
import backend4413.dev.security.token.TokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class LogoutService implements LogoutHandler {
	
	@Autowired
	private TokenRepository tokenRepository;

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		final String jwtHeader = request.getHeader("Authorization");
		final String jwt;
		if (jwtHeader == null || !jwtHeader.startsWith("Bearer ")) {
			return;
		}
		jwt = jwtHeader.substring(7);
		Token token = tokenRepository.findByToken(jwt).orElse(null);
		if(token != null) {
			token.setExpired(true);
			token.setRevoked(true);
			tokenRepository.save(token);
		}
	}

}
