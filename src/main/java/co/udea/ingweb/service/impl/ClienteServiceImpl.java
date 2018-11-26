package co.udea.ingweb.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import co.udea.ingweb.exception.DataNotFoundException;
import co.udea.ingweb.model.Cliente;
import co.udea.ingweb.model.Producto;
import co.udea.ingweb.repository.ClienteRepositoy;
import co.udea.ingweb.service.ClienteService;
import co.udea.ingweb.service.ProductoService;
import co.udea.ingweb.util.Messages;


@Service
public class ClienteServiceImpl implements ClienteService {
	
	private final Logger log = LoggerFactory.getLogger(ClienteServiceImpl.class);
	
	private Messages messages;	
	private ClienteRepositoy clienteRepository;

	public ClienteServiceImpl(ClienteRepositoy clienteRepository, Messages messages) {
		this.clienteRepository = clienteRepository;
		this.messages = messages;
	}

	@Override
	public List<Cliente> getClientes() {
		log.debug("Inicio getClientes");
		List<Cliente> clientes = clienteRepository.findAll();
		log.debug("Fin getClientes");
		return clientes;
	}
	
	@Override
	public Cliente getCliente(String id) {
		log.debug("Inicio getCliente: id = {}", id);
		Optional<Cliente> cliente = clienteRepository.findById(id);
		if(!cliente.isPresent()){
			throw new DataNotFoundException(messages.get("exception.data_not_found.demo"));
		}
		log.debug("Fin getCliente: cliente = {}", cliente.get());
		return cliente.get();
	}
	
	
	@Override
	public Cliente updateCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	@Override
	public Cliente addCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	@Override
	public void deleteClienteId(String id) {
		log.debug("Inicio deleteClienteId: id = {}", id);
		Optional<Cliente> cliente = clienteRepository.findById(id);
		if(!cliente.isPresent()){
			throw new DataNotFoundException(messages.get("exception.data_not_found.demo"));
		}
		log.debug("Fin deleteClienteId: cliente = {}", cliente.get());
		clienteRepository.delete(cliente.get());		
	}


}
