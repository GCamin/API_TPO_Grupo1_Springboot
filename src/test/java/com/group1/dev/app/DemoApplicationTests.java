package com.group1.dev.app;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.group1.dev.app.model.dao.EdificioRepository;
import com.group1.dev.app.model.dao.ImagenRepository;
import com.group1.dev.app.model.dao.PersonaRepository;
import com.group1.dev.app.model.dao.ReclamoRepository;
import com.group1.dev.app.model.dao.UnidadRepository;
import com.group1.dev.app.model.dao.UsuarioRepository;
import com.group1.dev.app.model.entity.EstadoUnidad;
import com.group1.dev.app.model.entity.Persona;
import com.group1.dev.app.model.entity.TipoPersona;
import com.group1.dev.app.model.entity.Unidad;

@DataJpaTest
class DemoApplicationTests {
	
	@Autowired
	private PersonaRepository personaRepo;
	
	@Autowired
	private UnidadRepository unidadRepo;
	
	//private EdificioRepository edificioRepo;
	//private ReclamoRepository reclamoRepo;
	//private ImagenRepository imagenRepo;
	//private UsuarioRepository usuarioRepo;


	@Test
	public void should_find_no_personas_if_repository_is_empty() {
		assertThat(personaRepo.findAll()).isEmpty();
	}

	@Test
	public void should_store_a_Unidad() {
				
		Unidad unidad = new Unidad();
		unidad.setNro(5);unidad.setPiso(1);unidad.setEstado(EstadoUnidad.HabitadaPorDuenio);
		unidadRepo.save(unidad);
				
		assertThat(unidadRepo.findById(1).get()).hasFieldOrPropertyWithValue("piso", 1);
	}

	/*
	@Test
	public void should_find_all_Edificios() {
	}

	@Test
	public void should_find_Reclamo_by_id() {
	}

	@Test
	public void should_delete_tutorial_by_id() {
	}
*/
}
