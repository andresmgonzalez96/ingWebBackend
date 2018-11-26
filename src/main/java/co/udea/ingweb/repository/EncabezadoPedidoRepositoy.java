package co.udea.ingweb.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.udea.ingweb.model.Cliente;
import co.udea.ingweb.model.EncabezadoPedido;
import co.udea.ingweb.model.Producto;

public interface EncabezadoPedidoRepositoy extends JpaRepository<EncabezadoPedido, String>{

	public Optional<EncabezadoPedido> findById(int id); 
	
	
}
