package co.udea.ingweb.service;

import java.util.List;

import co.udea.ingweb.model.Proveedor;

public interface ProveedorService {
	
	public List<Proveedor> getProveedores();
	
	public Proveedor getProveedor(String id) ;
	public Proveedor addProveedor(Proveedor proveedor) ;
	public Proveedor updateProveedor(Proveedor proveedor) ;
	public void deleteProveedorId(String id) ;
	

}
