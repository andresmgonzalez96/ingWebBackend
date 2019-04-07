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
import co.udea.ingweb.model.Proveedor;
import co.udea.ingweb.service.ProveedorService;
import co.udea.ingweb.util.Messages;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/proveedor")
public class ProveedorController {
	
	private static Logger log = LoggerFactory.getLogger(ProveedorController.class);
	
	private ProveedorService proveedorService;
	
	@Autowired
    private Messages messages;	
	
	public ProveedorController(ProveedorService proveedorService) {
		this.proveedorService = proveedorService;
	}
	
	@GetMapping(value = "consultar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Consultar Proveedor por id", response = Page.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Proveedor encontrado", response = Proveedor.class),
            @ApiResponse(code = 400, message = "La petición es invalida"),
            @ApiResponse(code = 404, message = "Recurso no encontrado"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
	public ResponseEntity<Proveedor> getProveedor( @PathVariable("id") String id){
		 log.debug("REST request getProveedor id : {}", id);
		return ResponseEntity.ok(proveedorService.getProveedor(id));
	}
	
	@GetMapping(value = "listar", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Buscar todos", response = Page.class)
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Los Proveedors fueron buscados", response = Page.class),
            @ApiResponse(code = 400, message = "La petición es invalida"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
	public ResponseEntity<List<Proveedor>> getProveedores(){
		log.debug("REST request listar todos los Proveedors");
		return ResponseEntity.ok(proveedorService.getProveedores());		
	}
	
	@DeleteMapping(value = "borrar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Borrar Proveedor por id", response = Page.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Proveedor Borrado", response = Proveedor.class),
            @ApiResponse(code = 400, message = "La petición es invalida"),
            @ApiResponse(code = 404, message = "Recurso no encontrado"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
	public void deleteProveedorId( @PathVariable("id") String id){
		 log.debug("REST request deleteProveedor id : {}", id);
		 proveedorService.deleteProveedorId(id);
	}
	
	@PutMapping(value = "actualizar/", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Actualizar Proveedor", response = Page.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Proveedor actualizado", response = Proveedor.class),
            @ApiResponse(code = 400, message = "La petición es invalida"),
            @ApiResponse(code = 404, message = "Recurso no encontrado"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
	public void updateProveedor(@RequestBody Proveedor Proveedor ){
		 log.debug("REST request updateProveedor id : {}", Proveedor.getId());
		proveedorService.updateProveedor(Proveedor);
	}
	
	@RequestMapping(value="crear", method=RequestMethod.POST)
	public Proveedor addProveedor(@RequestBody Proveedor Proveedor) throws DataNotFoundException{
		log.debug("Entro a crear");
		if(Proveedor == null){
			throw new DataNotFoundException(messages.get("exception.data_not_found.Proveedor"));
		}
		return proveedorService.addProveedor(Proveedor);		
		
	}

}
