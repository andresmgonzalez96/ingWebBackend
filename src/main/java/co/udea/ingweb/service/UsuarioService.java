package co.udea.ingweb.service;

import java.util.List;

import co.udea.ingweb.model.Usuario;

public interface UsuarioService {

	public List<Usuario> getUsuarios();

	public Usuario getUsuario(String id);

	public Usuario addUsuario(Usuario usuario);

	public Usuario updateUsuario(Usuario usuario);

	public void deleteUsuarioId(String id);

	public Usuario doLogin(String id, String pass);

}
