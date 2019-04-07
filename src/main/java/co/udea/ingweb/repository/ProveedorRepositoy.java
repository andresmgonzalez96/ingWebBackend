package co.udea.ingweb.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.udea.ingweb.model.Proveedor;

public interface ProveedorRepositoy extends JpaRepository<Proveedor, String>{

	public Optional<Proveedor> findById(String id); 
	
	
}
