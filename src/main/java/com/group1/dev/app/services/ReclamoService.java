package com.group1.dev.app.services;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.group1.dev.app.model.dao.ReclamoRepository;
import com.group1.dev.app.model.entity.Edificio;
import com.group1.dev.app.model.entity.EstadoReclamo;
import com.group1.dev.app.model.entity.Persona;
import com.group1.dev.app.model.entity.Reclamo;
import com.group1.dev.app.model.entity.ReclamoDTO;
import com.group1.dev.app.model.entity.TipoReclamo;

@Service
public class ReclamoService implements IReclamoService {

	@Autowired
	private PersonaService personaService;

	@Autowired
	private EdificioService edificioService;

	@Autowired
	private ReclamoRepository reclamoRepo;
	
	
public ReclamoDTO reclamoToDto(Reclamo reclamo) {
		
		
		ReclamoDTO reclamoDTO = new ReclamoDTO();
		
		if (reclamo.getActualizacion() != null) { 
		reclamoDTO.setActualizacion(reclamo.getActualizacion());
		}
		
		if(reclamo.getDescripcion() != null) {
		reclamoDTO.setDescripcion(reclamo.getDescripcion());}
		
	
		if(reclamo.getEdificio().getId() !=0) {
			reclamoDTO.setId_edificio(reclamo.getEdificio().getId());
		}
		
		
		if(reclamo.getPersona().getId() !=0) {
			reclamoDTO.setId_persona(reclamo.getPersona().getId());
		}
		
		if (reclamo.getEstadoReclamo() != null) {
		
		String estado = reclamo.getEstadoReclamo().toString();
		reclamoDTO.setEstadoReclamo(estado);
		}
		
		if (reclamo.getTipoReclamo() != null) {
		String tipo = reclamo.getTipoReclamo().toString();
		reclamoDTO.setTipoReclamo(tipo);}
		
		if (reclamo.getTitulo() != null) {
		reclamoDTO.setTitulo(reclamo.getTitulo());
		}		
		return reclamoDTO;
	}
		

	
public Reclamo DTOtoReclamo(ReclamoDTO reclamoDTO) {
		
		Reclamo reclamo = new Reclamo();
		
		if (reclamoDTO.getActualizacion() != null) { 
		reclamo.setActualizacion(reclamoDTO.getActualizacion());
		}
		
		if(reclamoDTO.getDescripcion() != null) {
		reclamo.setDescripcion(reclamoDTO.getDescripcion());}
		
		
		if(reclamoDTO.getId_edificio() !=0) {
		Optional<Edificio> optionalEdificio = Optional.of(new Edificio());
		EdificioService edificioService = new EdificioService();
		optionalEdificio = edificioService.findById(reclamoDTO.getId_edificio());
		Edificio edificio = new Edificio();
    	if (optionalEdificio.isPresent()) {
    	    
    	    edificio = optionalEdificio.get();
    	}
    	
		
		reclamo.setEdificio(edificio);}
		
		if (reclamoDTO.getId_persona() != 0) {
		Optional<Persona> optionalPersona = Optional.of(new Persona());
		PersonaService personaService = new PersonaService();
		optionalPersona = personaService.findById(reclamoDTO.getId_persona());
		Persona persona = new Persona();
    	if (optionalPersona.isPresent()) {
    	    
    	    persona = optionalPersona.get();
    	}
		
		reclamo.setPersona(persona);
		}
		
		if (reclamoDTO.getEstadoReclamo() != null) {
		
		EstadoReclamo estado = EstadoReclamo.valueOf(reclamoDTO.getEstadoReclamo());
		reclamo.setEstadoReclamo(estado);
		}
		
		if (reclamoDTO.getTipoReclamo() != null) {
		TipoReclamo tipo = TipoReclamo.valueOf(reclamoDTO.getTipoReclamo());
		reclamo.setTipoReclamo(tipo);}
		
		if (reclamoDTO.getTitulo() != null) {
		reclamo.setTitulo(reclamoDTO.getTitulo());
		}
		
		
		
		
		return reclamo;
		
	}
	

	@Override
	public List<ReclamoDTO> findAll() {
		
		List<Reclamo> allReclamos = reclamoRepo.findAll();
		List<ReclamoDTO> allReclamosDTO = new ArrayList<ReclamoDTO>();
		for (Reclamo reclamo : allReclamos) {
			
			ReclamoDTO reclamoDTO = reclamoToDto(reclamo);
			
			allReclamosDTO.add(reclamoDTO);
			
			
		} 
		
		return allReclamosDTO;
	}

	@Override
	public ReclamoDTO findById(Integer id) {

		Optional<Reclamo> optionalReclamo = Optional.of(new Reclamo());
		optionalReclamo = reclamoRepo.findById(id);
		Reclamo reclamo = new Reclamo();

		if (optionalReclamo.isPresent()) {

			reclamo = optionalReclamo.get();
		}
		
		ReclamoDTO reclamoDTO = reclamoToDto(reclamo);

		return reclamoDTO;
	}

	@Override
	public void save(ReclamoDTO reclamoDTO) {
		Reclamo reclamo = DTOtoReclamo(reclamoDTO);
		
		reclamoRepo.save(reclamo);

	}

	@Override
	public void deleteById(Integer id) {
		reclamoRepo.deleteById(id);

	}

	public void update(Integer id, ReclamoDTO reclamoNew) {
		
		
		reclamoNew.setId_reclamo(id);
		
		Reclamo reclamo = DTOtoReclamo(reclamoNew);

		reclamoRepo.save(reclamo);
	}

	public List<ReclamoDTO> filter(Integer userId, Integer buildingId, String state, String type) {

		Reclamo reclamo = new Reclamo();

		if (userId != null) {
			reclamo.setPersona(personaService.findById(userId).get());
		}
		if (buildingId != null) {
			reclamo.setEdificio(edificioService.findById(buildingId).get());
		}
		if (state != null) {
			reclamo.setEstadoReclamo(EstadoReclamo.valueOf(state));
		} else {
			reclamo.setEstadoReclamo(null);
		}
		if (type != null) {
			TipoReclamo tipo = TipoReclamo.valueOf(type);

			reclamo.setTipoReclamo(tipo);
		}
		Example<Reclamo> example = Example.of(reclamo);
		System.out.println(example);
		List<Reclamo> allReclamos = (List<Reclamo>) reclamoRepo.findAll(example);
		List<ReclamoDTO> allReclamosDTO = new ArrayList<ReclamoDTO>();
		for (Reclamo reclamoeg : allReclamos) {
			
			ReclamoDTO reclamoDTO = reclamoToDto(reclamoeg);
			
			allReclamosDTO.add(reclamoDTO);
			
			
		} 
		
		return allReclamosDTO;

	}

}
