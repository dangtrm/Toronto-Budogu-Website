package backend4413.dev.security.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import backend4413.dev.dto.UserDTO;
import backend4413.dev.model.Role;
import backend4413.dev.model.User;
import backend4413.dev.repository.UserRepository;
import backend4413.dev.security.auth.AuthResponse;
import backend4413.dev.security.token.Token;
import backend4413.dev.security.token.TokenRepository;
import backend4413.dev.security.token.TokenType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class AuthService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TokenRepository tokenRepository;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private AuthenticationManager authenticationManager;

	public AuthResponse register(UserDTO request) {
		User user = new User(request.getName(), request.getUsername(), request.getEmail(),
				passwordEncoder.encode(request.getPassword()), Role.USER);

		User test = userRepository.findByUsername(request.getUsername()).orElse(null);
		if (test != null) {
			return new AuthResponse("User already available", null);
		}

		String jwtToken = jwtService.generateToken(user);
		String refreshToken = jwtService.generateRefreshToken(user);
		saveUserAndToken(user, jwtToken);
		return new AuthResponse(jwtToken, refreshToken, user.getId());
	}

	public AuthResponse authenticate(UserDTO request) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

		User user = userRepository.findByUsername(request.getUsername()).orElseThrow();
		String jwtToken = jwtService.generateToken(user);
		String refreshToken = jwtService.generateRefreshToken(user);
		revokeAllUserTokens(user);
		saveUserAndToken(user, jwtToken);
		return new AuthResponse(jwtToken, refreshToken, user.getId());
	}

	private void saveUserAndToken(User user, String jwtToken) {
		Token token = new Token(jwtToken, TokenType.BEARER, user.getId(), false, false);
		List<String> userTokens = user.getTokens() != null ? user.getTokens() : new ArrayList<>();
		userTokens.add(jwtToken);
		user.setTokens(userTokens);
		tokenRepository.save(token);
		userRepository.save(user);
	}

	private void revokeAllUserTokens(User user) {
		List<Token> validUserTokens = tokenRepository.findAllValidTokensByUserId(user.getId());
		if (validUserTokens.isEmpty()) {
			return;
		}
		validUserTokens.forEach(t -> {
			t.setExpired(true);
			t.setRevoked(true);
		});
		tokenRepository.saveAll(validUserTokens);
	}

	public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
		final String jwtHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		final String refreshToken;
		final String username;
		if (jwtHeader == null || !jwtHeader.startsWith("Bearer ")) {
			return;
		}
		refreshToken = jwtHeader.substring(7);
		username = jwtService.extractUsername(refreshToken);
		if (username != null) {
			User user = userRepository.findByUsername(username).orElseThrow();
//			boolean isTokenValid = tokenRepository.findByToken(refreshToken).map(t -> !t.isExpired() && !t.isRevoked())
//					.orElse(false);
			if (jwtService.isTokenValid(refreshToken, user)) {
				String accessToken = jwtService.generateToken(user);
				revokeAllUserTokens(user);
				saveUserAndToken(user, accessToken);
				AuthResponse authResponse = new AuthResponse(accessToken, refreshToken, user.getId());
				new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
			}
		}
	}

	public AuthResponse validateToken(String token) {
		Optional<Token> currToken = tokenRepository.findByToken(token);
		boolean isTokenValid = currToken.map(t -> !t.isExpired() && !t.isRevoked()).orElse(false);
		if (!jwtService.isTokenExpired(token) && isTokenValid) {
			return new AuthResponse("Success", null);
		}
		return new AuthResponse("Failed", null);
	}

}
