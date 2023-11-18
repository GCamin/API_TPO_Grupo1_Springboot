package com.group1.dev.app.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.group1.dev.app.dto.UserDTO;
import com.group1.dev.app.mappers.UserMapper;
import com.group1.dev.app.model.entity.EntityUser;
import com.group1.dev.app.services.IUserService;

@CrossOrigin(origins = "http://localhost:3000" )
@RestController
@RequestMapping("/users")
public class UsuarioController {

	@Autowired
	private IUserService usuarioService;

	@Autowired
	private UserMapper userMapper;
	

	@GetMapping(value = "/all")
	public List<UserDTO> findAll() {
		return usuarioService
				.findAll()
				.stream()
				.map(userMapper)
				.collect(Collectors.toList());
	}

	@GetMapping(value = "/find")
	public ResponseEntity<?> findUser(@RequestParam("user") String username) {

		Optional<UserDTO> usuario = usuarioService.findByUsername(username).map(userMapper);

		if (!usuario.isPresent()) {
			String mensaje = "Usuario no encontrado: " + username;
			return new ResponseEntity<String>(mensaje, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<UserDTO>(usuario.get(), HttpStatus.OK);

	}
	
	@PostMapping("/add")
    public ResponseEntity<?> addUsuario(@RequestBody 	EntityUser user) {
		Optional<EntityUser> userExists = usuarioService.findByUsername(user.getUsername());
		
		if (userExists.isPresent()) {
			
			String mensaje = "Nombre de usuario existente";
			return new ResponseEntity<String>(mensaje, HttpStatus.BAD_REQUEST);
			
		}
		
		
		
        usuarioService.save(user);
        return new ResponseEntity<EntityUser>(user, HttpStatus.CREATED);
    }

	@PutMapping(value = "/update/{username}")
	public ResponseEntity<?> updateUsuario(@PathVariable String username, @RequestBody UserDTO updatedUserDTO) {
		Optional<EntityUser> userExists = usuarioService.findByUsername(username);


		if (userExists.isPresent()) {
			EntityUser updatedUser = userExists.get();
			updatedUser.setDni(updatedUserDTO.dni());
			updatedUser.setEdad(updatedUserDTO.edad());
			updatedUser.setEmail(updatedUserDTO.email());
			updatedUser.setNombre(updatedUserDTO.nombre());
			updatedUser.setTipoPersona(updatedUserDTO.tipoPersona());

			usuarioService.save(updatedUser);

			return ResponseEntity.ok("Usuario Creado");
		} else {
			String mensaje = "Usuario no encontrado: " + username;
			return new ResponseEntity<String>(mensaje, HttpStatus.NOT_FOUND);
		}

	}

	@DeleteMapping(value = "/delete")
	public ResponseEntity<String> deleteUsuario(@RequestParam("username") String username) {

		Optional<EntityUser> user = usuarioService.findByUsername(username);
		if (!user.isPresent()) {
			String mensaje = "Usuario no encontrado: " + username;
			return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
		}
		usuarioService.deleteById(user.get().getId());
		String mensaje = "Usuario eliminado con exito";
		return new ResponseEntity<>(mensaje, HttpStatus.OK);
	}
}
