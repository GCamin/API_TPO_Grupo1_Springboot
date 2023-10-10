package com.group1.dev.app.services;

import java.util.List;

import com.group1.dev.app.model.entity.ReclamoDTO;

public interface IReclamoService {

	public List<ReclamoDTO> findAll();

	public ReclamoDTO findById(Integer id);

	public void save(ReclamoDTO reclamoDTO);

	public void deleteById(Integer id);

	public void update(Integer id,ReclamoDTO reclamoNew);

	public List<ReclamoDTO> filter(Integer userId, Integer buildingId, String state, String type);

}
