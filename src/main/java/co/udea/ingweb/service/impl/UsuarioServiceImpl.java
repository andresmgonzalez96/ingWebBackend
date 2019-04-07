package co.udea.ingweb.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import co.udea.ingweb.exception.DataNotFoundException;
import co.udea.ingweb.model.Proveedor;
import co.udea.ingweb.model.Usuario;
import co.udea.ingweb.repository.ProveedorRepositoy;
import co.udea.ingweb.repository.UsuarioRepositoy;
import co.udea.ingweb.service.ProveedorService;
import co.udea.ingweb.service.UsuarioService;
import co.udea.ingweb.util.Messages;


@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	private final Logger log = LoggerFactory.getLogger(UsuarioServiceImpl.class);
	
	private Messages messages;	
	private UsuarioRepositoy usuarioRepository;

	public UsuarioServiceImpl(UsuarioRepositoy usuarioRepository, Messages messages) {
		this.usuarioRepository = usuarioRepository;
		this.messages = messages;
	}

	@Override
	public List<Usuario> getUsuarios() {
		log.debug("Inicio getUsuarios");
		List<Usuario> usuarios = usuarioRepository.findAll();
		log.debug("Fin getUsuarios");
		return usuarios;
	}
	
	@Override
	public Usuario getUsuario(String id) {
		log.debug("Inicio getUsuario: id = {}", id);
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		if(!usuario.isPresent()){
			throw new DataNotFoundException(messages.get("exception.data_not_found.demo"));
		}
		log.debug("Fin getUsuario: usuario = {}", usuario.get());
		return usuario.get();
	}
	
	@Override
	public Usuario doLogin(String user, String pass) {
		List<Usuario> usuario = usuarioRepository.findAll();
		for (Usuario usuario2 : usuario) {
			if (user.equals(usuario2.getUser())) {
				if (pass.equals(usuario2.getPassword())) {
					return usuario2;
				}
			}
			
		} 
		throw new DataNotFoundException(messages.get("Las credenciales son invalidas"));
	}
	
	
	@Override
	public Usuario updateUsuario(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	@Override
	public Usuario addUsuario(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	@Override
	public void deleteUsuarioId(String id) {
		log.debug("Inicio deleteUsuarioId: id = {}", id);
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		if(!usuario.isPresent()){
			throw new DataNotFoundException(messages.get("exception.data_not_found.demo"));
		}
		log.debug("Fin deleteUsuarioId: usuario = {}", usuario.get());
		usuarioRepository.delete(usuario.get());		
	}


}
