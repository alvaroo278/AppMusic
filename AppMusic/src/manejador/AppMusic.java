package manejador;

import dominio.Usuario;
import java.time.LocalDate;
import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public class AppMusic {
	private static AppMusic unicaInstancia = null;
	private Usuario usuario;
	private FactoriaServicioPersistencia factoria;

	private AppMusic() {
		this.usuario = null;
	}

	public boolean login(String usuario, String contraseña) {
		// Comprobar los datos del usuario con la base de datos
		factoria.getInstance().getServicioPersistencia();
		return true;

	}

	public boolean registroUsuario(String usuario, String clave, String nombre, String apellidos, String email,
			LocalDate fecha) {
		Usuario user = new Usuario(usuario, clave, nombre, apellidos, email, fecha);
		return true;

	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuariol(Usuario usuario) {
		this.usuario = usuario;
	}

	public static AppMusic getInstancia() {
		if (unicaInstancia == null)
			unicaInstancia = new AppMusic();
		return unicaInstancia;
	}

}
