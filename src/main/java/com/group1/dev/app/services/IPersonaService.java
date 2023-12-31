package com.group1.dev.app.services;

import java.util.List;
import java.util.Optional;

import com.group1.dev.app.model.entity.Persona;
import com.group1.dev.app.model.entity.PersonaDTO;

public interface IPersonaService {

	public List<Persona> findAll();

	public Optional<Persona> findById(int id);

	public void save(Persona persona);

	public void deleteById(int id);
	
	public Persona findPersonaDni(int dni);
	
	public List<Persona> findPersonaApellido(String apellido);
	
	public PersonaDTO personaToDTO(Persona persona);
	
	public Persona dtoToPersona(PersonaDTO personaDTO);

}
