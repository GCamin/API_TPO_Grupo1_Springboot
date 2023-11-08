
package com.group1.dev.app.auth;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group1.dev.app.dto.UserDTO;
import com.group1.dev.app.model.entity.EntityUser;
import com.group1.dev.app.services.IUserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/auth")
public class AuthController {

	private final int EXPIRATION_TIME_IN_MIN = 60;
	
	@Autowired
	private IUserService usuarioService;

	@Autowired
	private SecretKey secretKey; // Inyecta la clave secreta

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody EntityUser credentials) {
	
		if (usuarioService.findUser(credentials.getUsername(), credentials.getPassword()) != null) {
			// Crear el token JWT
			String token = Jwts.builder().setSubject(credentials.getUsername()).setIssuedAt(new Date())
					.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME_IN_MIN * 60 * 1000))
					.signWith(secretKey, SignatureAlgorithm.HS256).compact();

			return new ResponseEntity<String>(token, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Credenciales inválidas.", HttpStatus.ACCEPTED);
		}
	}


}


// package com.group1.dev.app.auth;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// @CrossOrigin(origins = "http://localhost:3000")
// @RestController
// @RequestMapping("/auth")
// public class AuthController {

// 	@Autowired
// 	private AuthService authService;

// 	@PostMapping("/login")
// 	public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {

// 		return ResponseEntity.ok(authService.login(request));

// 	}

// 	@PostMapping("/register")
// 	public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
// 		authService.register(request);
// 		return ResponseEntity.ok("Usuario Creado");
// 	}

// }
