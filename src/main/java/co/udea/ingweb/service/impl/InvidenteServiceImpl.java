package co.udea.ingweb.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import co.udea.ingweb.exception.DataNotFoundException;
import co.udea.ingweb.model.Incidentes;
import co.udea.ingweb.repository.IncidentesRepository;
import co.udea.ingweb.service.IncidentesService;
import co.udea.ingweb.util.Messages;


@Service
public class InvidenteServiceImpl implements IncidentesService {
	
	private final Logger log = LoggerFactory.getLogger(InvidenteServiceImpl.class);
	
	private Messages messages;	
	private IncidentesRepository incidenteRepository;

	public InvidenteServiceImpl(IncidentesRepository encabezadoRepository, Messages messages) {
		this.incidenteRepository = encabezadoRepository;
		this.messages = messages;
	}

	@Override
	public List<Incidentes> getIncidentes() {
		log.debug("Inicio getIncidente");
		List<Incidentes> encabezado = incidenteRepository.findAll();
		log.debug("Fin getIncidente");
		return encabezado;
	}
	
	@Override
	public Incidentes getIncidente(int id) {
		log.debug("Inicio getCliente: id = {}", id);
		Optional<Incidentes> Incidente = incidenteRepository.findById(id);
		if(!Incidente.isPresent()){
			throw new DataNotFoundException(messages.get("exception.data_not_found.demo"));
		}
		log.debug("Fin getCliente: cliente = {}", Incidente.get());
		return Incidente.get();
	}
	
	
	@Override
	public Incidentes updateIncidente(Incidentes Incidente) {
		return incidenteRepository.save(Incidente);
	}
	
	@Override
	public Incidentes addIncidente(Incidentes Incidente) {
		//List<Incidentes> pedidos= incidenteRepository.findAll();
		//Incidente.setId(pedidos.size());
		return incidenteRepository.save(Incidente);
	}
	
	@Override
	public void deleteIncidenteId(int id) {
		log.debug("Inicio deleteClienteId: id = {}", id);
		Optional<Incidentes> Incidente = incidenteRepository.findById(id);
		if(!Incidente.isPresent()){
			throw new DataNotFoundException(messages.get("exception.data_not_found.demo"));
		}
		log.debug("Fin deleteIncidenteId: Incidente = {}", Incidente.get());
		incidenteRepository.delete(Incidente.get());		
	}


}
