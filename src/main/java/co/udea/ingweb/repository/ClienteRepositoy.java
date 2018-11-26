package co.udea.ingweb.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.udea.ingweb.model.Cliente;
import co.udea.ingweb.model.Producto;

public interface ClienteRepositoy extends JpaRepository<Cliente, String>{

	public Optional<Cliente> findById(String id); 
	
	
}
