package co.udea.ingweb.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.udea.ingweb.exception.DataNotFoundException;
import co.udea.ingweb.model.Incidentes;
import co.udea.ingweb.service.IncidentesService;
import co.udea.ingweb.util.Messages;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/incidente")
public class IncidenteController {
	
	private static Logger log = LoggerFactory.getLogger(IncidenteController.class);
	
	private IncidentesService incidenteService;
	
	@Autowired
    private Messages messages;	
	
	public IncidenteController(IncidentesService incidente) {
		this.incidenteService = incidente;
	}
	
	@GetMapping(value = "consultar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Consultar Incidente por id", response = Page.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Incidente encontrado", response = Incidentes.class),
            @ApiResponse(code = 400, message = "La petición es invalida"),
            @ApiResponse(code = 404, message = "Recurso no encontrado"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
	public ResponseEntity<Incidentes> getIncidente( @PathVariable("id") int id){
		 log.debug("REST request getIncidente id : {}", id);
		return ResponseEntity.ok(incidenteService.getIncidente(id));
	}
	
	@GetMapping(value = "listar", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Buscar todos", response = Page.class)
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Los Incidente fueron buscados", response = Page.class),
            @ApiResponse(code = 400, message = "La petición es invalida"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
	public ResponseEntity<List<Incidentes>> getEncabezadosPedidos(){
		log.debug("REST request listar todos los Incidentes");
		return ResponseEntity.ok(incidenteService.getIncidentes());		
	}
	
	@DeleteMapping(value = "borrar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Borrar Incidente por id", response = Page.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Incidente Borrado", response = Incidentes.class),
            @ApiResponse(code = 400, message = "La petición es invalida"),
            @ApiResponse(code = 404, message = "Recurso no encontrado"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
	public void deleteIncidenteId( @PathVariable("id") int id){
		 incidenteService.deleteIncidenteId(id);
	}
	
	@PutMapping(value = "actualizar", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Actualizar Incidente", response = Page.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Incidente actualizado", response = Incidentes.class),
            @ApiResponse(code = 400, message = "La petición es invalida"),
            @ApiResponse(code = 404, message = "Recurso no encontrado"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
	public void updateIncidente(@RequestBody Incidentes incidente ){
		 log.debug("REST request updateIncidente id : {}", incidente.getId());
		incidenteService.updateIncidente(incidente);
	}
	
	@RequestMapping(value="crear", method=RequestMethod.POST)
	public Incidentes addIncidente(@RequestBody Incidentes incidente) throws DataNotFoundException{
		log.debug("Entro a crear");
		if(incidente == null){
			throw new DataNotFoundException(messages.get("exception.data_not_found.pedido"));
		}
		return incidenteService.addIncidente(incidente);		
		
	}

}
