package umu.tds.persistencia;

import java.util.List;

import umu.tds.dominio.Usuario;

public interface IAdaptadorUsuarioDAO {

	public void registrarUsuario(Usuario cliente);

	public void borrarUsuario(Usuario cliente);

	public void modificarUsuario(Usuario cliente);

	public Usuario recuperarUsuario(int id);

	public List<Usuario> recuperarTodosUsuarios();
	
	public void borrarTodos();
}
