package co.udea.ingweb.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.udea.ingweb.model.Cliente;
import co.udea.ingweb.model.Usuario;

public interface UsuarioRepositoy extends JpaRepository<Usuario, String>{

	public Optional<Usuario> findById(String id); 
	
	
}
