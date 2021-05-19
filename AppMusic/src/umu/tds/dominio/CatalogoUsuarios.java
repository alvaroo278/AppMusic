package umu.tds.dominio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import umu.tds.dominio.Usuario;

import umu.tds.persistencia.DAOException;
import umu.tds.persistencia.FactoriaDAO;
import umu.tds.persistencia.IAdaptadorUsuarioDAO;

public class CatalogoUsuarios {

	private Map<String, Usuario> usuarios;

	private static CatalogoUsuarios unicaInstancia;

	private FactoriaDAO dao;
	private IAdaptadorUsuarioDAO adaptadorUsuario;

	private CatalogoUsuarios() {
		try {
			dao = FactoriaDAO.getInstancia(FactoriaDAO.DAO_TDS);
			adaptadorUsuario = dao.getUsuarioDAO();
			//adaptadorUsuario.borrarTodos();
			usuarios = new HashMap<String, Usuario>();
			this.cargarCatalogo();

		} catch (DAOException eDAO) {
			eDAO.printStackTrace();
		}
	}

	public static CatalogoUsuarios getUnicaInstancia() {

		if(unicaInstancia == null) return new CatalogoUsuarios();

		return unicaInstancia;
		
	}
	
	
	/* devuelve todos los clientes */
	public List<Usuario> getUsuarios() {
		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		for (Usuario c : usuarios.values())
			lista.add(c);
		return lista;
	}

	public Usuario getUsuario(int codigo) {
		for (Usuario c : usuarios.values()) {
			if (c.getId() == codigo)
				return c;
		}
		return null;
	}

	public Usuario getUsuario(String nombre) {
		return usuarios.get(nombre);
	}

	public void addUsuario(Usuario usu) {
		usuarios.put(usu.getLogin(), usu);
	}

	public void removeUsuario(Usuario usu) {
		usuarios.remove(usu.getLogin());
	}

	/* Recupera todos los clientes para trabajar con ellos en memoria */
	private void cargarCatalogo() throws DAOException {
		List<Usuario> usuariosBD = adaptadorUsuario.recuperarTodosUsuarios();
		for (Usuario usu : usuariosBD) {
			System.out.println(usu.getLogin());
			usuarios.put(usu.getLogin(), usu);
		}
	}

	
}