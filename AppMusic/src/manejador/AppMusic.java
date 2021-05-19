package manejador;

import dominio.CatalogoCanciones;
import dominio.CatalogoUsuarios;
import dominio.Usuario;
import persistencia.DAOException;
import persistencia.FactoriaDAO;
import persistencia.IAdaptadorCancionDAO;
import persistencia.IAdaptadorListaCancionesDAO;
import persistencia.IAdaptadorUsuarioDAO;

import java.time.LocalDate;
import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public class AppMusic {
	private static AppMusic unicaInstancia = null;
	private Usuario usuario;
	private IAdaptadorListaCancionesDAO adaptadorLC;
	private IAdaptadorUsuarioDAO adaptadorUsuario;
	private IAdaptadorCancionDAO adaptadorCancion;
	
	private CatalogoCanciones ctCanciones;
	private CatalogoUsuarios ctUsuarios;


	public static AppMusic getUnicaInstancia() {
		if (unicaInstancia == null)
			unicaInstancia = new AppMusic();
		return unicaInstancia;
	}

	
	private AppMusic() {
		inicializarAdaptadores();
		inicializarCatalogos();
	}

	public boolean login(String usuario, String contraseña) {
		// Comprobar los datos del usuario con la base de datos

		factoria.getInstance().getServicioPersistencia();

		//factoria.getInstance().getServicioPersistencia();

		return true;

	}

	public void registroUsuario(String nombre, String apellidos, String email, String usuario, String password,
			LocalDate fecha) {
		Usuario user = new Usuario(nombre, apellidos, email, usuario, password, fecha);
		adaptadorUsuario.registrarUsuario(user);
		ctUsuarios.addUsuario(user);

	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuariol(Usuario usuario) {
		this.usuario = usuario;
	}


	
	private void inicializarCatalogos() {
		ctCanciones = CatalogoCanciones.getUnicaInstancia();
		ctUsuarios = CatalogoUsuarios.getUnicaInstancia();
	}
	
	private void inicializarAdaptadores() {
		FactoriaDAO factoria = null;
		try {
			factoria = FactoriaDAO.getInstancia(FactoriaDAO.DAO_TDS);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		adaptadorUsuario = factoria.getUsuarioDAO();
		adaptadorCancion= factoria.getCancionDAO();
		adaptadorLC = factoria.getListaCancionesDAO();
	}

	public boolean esUsuarioRegistrado(String usu) {
		if(ctUsuarios.getUsuario(usu) != null ) {
			return usu.equals(ctUsuarios.getUsuario(usu).getLogin());
		}
		return false;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AppMusic other = (AppMusic) obj;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}
	
	
	
}
