package co.udea.ingweb.service;

import java.util.List;

import co.udea.ingweb.model.Cliente;

public interface ClienteService {
	
	public List<Cliente> getClientes();
	
	public Cliente getCliente(String id) ;
	public Cliente addCliente(Cliente cliente) ;
	public Cliente updateCliente(Cliente cliente) ;
	public void deleteClienteId(String id) ;
	

}
