package co.udea.ingweb.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.udea.ingweb.model.Incidentes;

public interface IncidentesRepository extends JpaRepository<Incidentes, String>{

	public Optional<Incidentes> findById(int id); 
	
	
}
