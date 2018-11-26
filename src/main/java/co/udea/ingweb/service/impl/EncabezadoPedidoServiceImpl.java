package co.udea.ingweb.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import co.udea.ingweb.exception.DataNotFoundException;
import co.udea.ingweb.model.Cliente;
import co.udea.ingweb.model.EncabezadoPedido;
import co.udea.ingweb.model.Producto;
import co.udea.ingweb.repository.ClienteRepositoy;
import co.udea.ingweb.repository.EncabezadoPedidoRepositoy;
import co.udea.ingweb.service.ClienteService;
import co.udea.ingweb.service.EncabezadoPedidoService;
import co.udea.ingweb.service.ProductoService;
import co.udea.ingweb.util.Messages;


@Service
public class EncabezadoPedidoServiceImpl implements EncabezadoPedidoService {
	
	private final Logger log = LoggerFactory.getLogger(EncabezadoPedidoServiceImpl.class);
	
	private Messages messages;	
	private EncabezadoPedidoRepositoy encabezadoRepository;

	public EncabezadoPedidoServiceImpl(EncabezadoPedidoRepositoy encabezadoRepository, Messages messages) {
		this.encabezadoRepository = encabezadoRepository;
		this.messages = messages;
	}

	@Override
	public List<EncabezadoPedido> getEncabezadosPedidos() {
		log.debug("Inicio getEncabezadoPedido");
		List<EncabezadoPedido> encabezado = encabezadoRepository.findAll();
		log.debug("Fin getEncabezadoPedido");
		return encabezado;
	}
	
	@Override
	public EncabezadoPedido getEncabezadoPedido(int id) {
		log.debug("Inicio getCliente: id = {}", id);
		Optional<EncabezadoPedido> encabezadoPedido = encabezadoRepository.findById(id);
		if(!encabezadoPedido.isPresent()){
			throw new DataNotFoundException(messages.get("exception.data_not_found.demo"));
		}
		log.debug("Fin getCliente: cliente = {}", encabezadoPedido.get());
		return encabezadoPedido.get();
	}
	
	
	@Override
	public EncabezadoPedido updateEncabezadoPedido(EncabezadoPedido encabezadoPedido) {
		return encabezadoRepository.save(encabezadoPedido);
	}
	
	@Override
	public EncabezadoPedido addEncabezadoPedido(EncabezadoPedido encabezadoPedido) {
		List<EncabezadoPedido> pedidos= encabezadoRepository.findAll();
		encabezadoPedido.setId(pedidos.size());
		return encabezadoRepository.save(encabezadoPedido);
	}
	
	@Override
	public void deleteEncabezadoPedidoId(int id) {
		log.debug("Inicio deleteClienteId: id = {}", id);
		Optional<EncabezadoPedido> encabezadoPedido = encabezadoRepository.findById(id);
		if(!encabezadoPedido.isPresent()){
			throw new DataNotFoundException(messages.get("exception.data_not_found.demo"));
		}
		log.debug("Fin deleteEncabezadoPedidoId: EncabezadoPedido = {}", encabezadoPedido.get());
		encabezadoRepository.delete(encabezadoPedido.get());		
	}


}
