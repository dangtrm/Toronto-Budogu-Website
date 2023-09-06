package backend4413.dev.security.auth;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend4413.dev.dto.TokenDTO;
import backend4413.dev.dto.UserDTO;
import backend4413.dev.security.service.AuthService;
import backend4413.dev.security.token.Token;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	@PostMapping("/register")
	public ResponseEntity<AuthResponse> register(@RequestBody UserDTO user) {
		AuthResponse res = authService.register(user);
		if (res.getRefreshToken() == null) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(res);
	}

	@PostMapping("/authenticate")
	public ResponseEntity<AuthResponse> authenticate(@RequestBody UserDTO user) {
		return ResponseEntity.ok(authService.authenticate(user));
	}

	@PostMapping("/validateToken")
	public ResponseEntity<AuthResponse> validateToken(@RequestBody TokenDTO token) {
		AuthResponse res = authService.validateToken(token.getToken());
		if (res.getAccessToken().equals("Success")) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.badRequest().build();
	}

	@PostMapping("refresh-token")
	public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
		authService.refreshToken(request, response);
	}
}
