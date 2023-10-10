package com.group1.dev.app.model.entity;





public class ReclamoDTO {
	
	private Integer id_reclamo;
	private String titulo;
	private int id_persona = 0;
	private String descripcion;
	private String estadoReclamo;
	private String tipoReclamo;
	private String actualizacion;
	private int id_edificio = 0;
	
	
	public ReclamoDTO() {
		super();
	}

	public ReclamoDTO(int id_reclamo, String titulo, int id_persona, String descripcion, String estadoReclamo,
			String tipoReclamo, String actualizacion, int id_edificio) {
		super();
		this.id_reclamo = id_reclamo;
		this.titulo = titulo;
		this.id_persona = id_persona;
		this.descripcion = descripcion;
		this.estadoReclamo = estadoReclamo;
		this.tipoReclamo = tipoReclamo;
		this.actualizacion = actualizacion;
		this.id_edificio = id_edificio;
	}
	
	

	public int getId_reclamo() {
		return id_reclamo;
	}

	public void setId_reclamo(int id_reclamo) {
		this.id_reclamo = id_reclamo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getId_persona() {
		return id_persona;
	}

	public void setId_persona(int id_persona) {
		this.id_persona = id_persona;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstadoReclamo() {
		return estadoReclamo;
	}

	public void setEstadoReclamo(String estadoReclamo) {
		this.estadoReclamo = estadoReclamo;
	}

	public String getTipoReclamo() {
		return tipoReclamo;
	}

	public void setTipoReclamo(String tipoReclamo) {
		this.tipoReclamo = tipoReclamo;
	}

	public String getActualizacion() {
		return actualizacion;
	}

	public void setActualizacion(String actualizacion) {
		this.actualizacion = actualizacion;
	}

	public int getId_edificio() {
		return id_edificio;
	}

	public void setId_edificio(int id_edificio) {
		this.id_edificio = id_edificio;
	}
	
}
