package co.udea.ingweb.service;

import java.util.List;

import co.udea.ingweb.model.Proveedor;
import co.udea.ingweb.model.Incidentes;

public interface IncidentesService {
	
	public List<Incidentes> getIncidentes();
	
	public Incidentes getIncidente(int id) ;
	public Incidentes addIncidente(Incidentes incidente) ;
	public Incidentes updateIncidente(Incidentes incidente) ;
	public void deleteIncidenteId(int id) ;
	

}
