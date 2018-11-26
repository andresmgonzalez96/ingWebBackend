package co.udea.ingweb.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.udea.ingweb.model.Cliente;
import co.udea.ingweb.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, String>{

	public Optional<Producto> findByCodigo(String id); 
	
	
}
