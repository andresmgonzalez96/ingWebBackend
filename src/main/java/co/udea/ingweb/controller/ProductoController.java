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
import co.udea.ingweb.model.Producto;
import co.udea.ingweb.service.ProductoService;
import co.udea.ingweb.util.Messages;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/producto")
public class ProductoController {
	
	private static Logger log = LoggerFactory.getLogger(ProductoController.class);
	
	private ProductoService productoService;
	
	@Autowired
    private Messages messages;	
	
	public ProductoController(ProductoService proyectoService) {
		this.productoService = proyectoService;
	}
	
	@GetMapping(value = "consultar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Consultar producto por id", response = Page.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Producto encontrado", response = Producto.class),
            @ApiResponse(code = 400, message = "La petición es invalida"),
            @ApiResponse(code = 404, message = "Recurso no encontrado"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
	public ResponseEntity<Producto> getProyecto( @PathVariable("id") String id){
		 log.debug("REST request getProducto id : {}", id);
		return ResponseEntity.ok(productoService.getProducto(id));
	}
	
	@GetMapping(value = "listar", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Buscar todos", response = Page.class)
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Los productos fueron buscados", response = Page.class),
            @ApiResponse(code = 400, message = "La petición es invalida"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
	public ResponseEntity<List<Producto>> getHeros(){
		log.debug("REST request listar todos los productos");
		return ResponseEntity.ok(productoService.getProductos());		
	}
	
	@DeleteMapping(value = "borrar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Borrar producto por id", response = Page.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Producto Borrado", response = Producto.class),
            @ApiResponse(code = 400, message = "La petición es invalida"),
            @ApiResponse(code = 404, message = "Recurso no encontrado"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
	public void deleteProyectoId( @PathVariable("id") String id){
		 log.debug("REST request deleteProducto id : {}", id);
		 productoService.deleteProductoId(id);
	}
	
	@PutMapping(value = "actualizar/", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Actualizar Producto", response = Page.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Producto actualizado", response = Producto.class),
            @ApiResponse(code = 400, message = "La petición es invalida"),
            @ApiResponse(code = 404, message = "Recurso no encontrado"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
	public void updateHero(@RequestBody Producto producto ){
		 log.debug("REST request updateProyecto id : {}", producto.getCodigo());
		productoService.updateProducto(producto);
	}
	
	@RequestMapping(value="crear", method=RequestMethod.POST)
	public Producto addHero(@RequestBody Producto producto) throws DataNotFoundException{
		log.debug("Entro a crear");
		if(producto == null){
			throw new DataNotFoundException(messages.get("exception.data_not_found.proyecto"));
		}
		return productoService.addProducto(producto);		
		
	}

}
