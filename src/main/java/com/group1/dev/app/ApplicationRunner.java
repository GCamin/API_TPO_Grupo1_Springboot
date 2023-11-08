package com.group1.dev.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// import com.group1.dev.app.auth.AuthService;
// import com.group1.dev.app.auth.RegisterRequest;
import com.group1.dev.app.model.dao.UserRepository;
import com.group1.dev.app.model.entity.EntityUser;
import com.group1.dev.app.model.entity.TipoPersona;
import com.group1.dev.app.services.UserService;

public class ApplicationRunner implements CommandLineRunner {

	@Autowired
	private UserService authService;
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		EntityUser user = new EntityUser("admin", "admin", 0, 0, "admin", "admin", TipoPersona.Administrador);
		authService.save(user);	
	}

}
