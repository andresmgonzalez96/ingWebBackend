package co.udea.ingweb.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import co.udea.ingweb.exception.DataNotFoundException;
import co.udea.ingweb.model.Proveedor;
import co.udea.ingweb.repository.ProveedorRepositoy;
import co.udea.ingweb.service.ProveedorService;
import co.udea.ingweb.util.Messages;


@Service
public class ProveedorServiceImpl implements ProveedorService {
	
	private final Logger log = LoggerFactory.getLogger(ProveedorServiceImpl.class);
	
	private Messages messages;	
	private ProveedorRepositoy proveedorRepository;

	public ProveedorServiceImpl(ProveedorRepositoy proveedorRepository, Messages messages) {
		this.proveedorRepository = proveedorRepository;
		this.messages = messages;
	}

	@Override
	public List<Proveedor> getProveedores() {
		List<Proveedor> proveedores = proveedorRepository.findAll();
		return proveedores;
	}
	
	@Override
	public Proveedor getProveedor(String id) {
		Optional<Proveedor> proveedor = proveedorRepository.findById(id);
		if(!proveedor.isPresent()){
			throw new DataNotFoundException(messages.get("exception.data_not_found.demo"));
		}
		return proveedor.get();
	}
	
	
	@Override
	public Proveedor updateProveedor(Proveedor proveedor) {
		return proveedorRepository.save(proveedor);
	}
	
	@Override
	public Proveedor addProveedor(Proveedor proveedor) {
		return proveedorRepository.save(proveedor);
	}
	
	@Override
	public void deleteProveedorId(String id) {
		Optional<Proveedor> proveedor = proveedorRepository.findById(id);
		if(!proveedor.isPresent()){
			throw new DataNotFoundException(messages.get("exception.data_not_found.demo"));
		}
		proveedorRepository.delete(proveedor.get());		
	}


}
