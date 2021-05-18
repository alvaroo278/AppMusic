package persistencia;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.h2.engine.Procedure;

import beans.Entidad;
import beans.Propiedad;
import dominio.ListaCanciones;
import dominio.Usuario;

import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public class AdaptadorUsuarioTDS  implements IAdaptadorUsuarioDAO{

	private static ServicioPersistencia servPersistencia;
	private static AdaptadorUsuarioTDS unicaInstancia = null;
	
	public static AdaptadorUsuarioTDS getUnicaInstancia(){
		if(unicaInstancia == null) return new AdaptadorUsuarioTDS();
		return unicaInstancia;
	}
	
	private AdaptadorUsuarioTDS() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}
	
	@Override
	public void registrarUsuario(Usuario usuario) {
		Entidad eUsuario;
		boolean existe = true;
		
		try {
			eUsuario = servPersistencia.recuperarEntidad(usuario.getId());
		}catch (NullPointerException e) {
			existe = false;
		}
		if(existe) return;
		
		AdaptadorListaCancionesTDS adaptadorLista = AdaptadorListaCancionesTDS.getUnicaInstancia();
		for (ListaCanciones lc : usuario.getPlaylists()) {
			adaptadorLista.registrarListaCanciones(lc);
		}
		
		eUsuario = new Entidad();
		eUsuario.setNombre("usuario");
		eUsuario.setPropiedades(new ArrayList<Propiedad>(
				Arrays.asList(new Propiedad("nombre",usuario.getNombre()), new Propiedad("apellidos",usuario.getApellidos()),
						new Propiedad("email",usuario.getEmail()), new Propiedad("login",usuario.getLogin()),
						new Propiedad("password",usuario.getPassword()), new Propiedad("fechaN",usuario.getFechaNacimiento().toString()),
						new Propiedad("playlists", obtenerCodigosPlaylists(usuario.getPlaylists()))))
				);
	}


	@Override
	public void borrarUsuario(Usuario usuario) {
		Entidad eUsuario = servPersistencia.recuperarEntidad(usuario.getId());
		servPersistencia.borrarEntidad(eUsuario);
		
	}

	@Override
	public void modificarUsuario(Usuario usuario) {
		Entidad eUsuario = servPersistencia.recuperarEntidad(usuario.getId());
		
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "nombre");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "nombre", usuario.getNombre());
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "apellidos");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "apellidos", usuario.getApellidos());
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "email");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "email", usuario.getEmail());
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "login");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "login", usuario.getLogin());
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "password");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "password", usuario.getPassword());
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "fechaN");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "fechaN", usuario.getFechaNacimiento().toString());
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "nombre");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "nombre", usuario.getNombre());
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "playlists");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "playlists", obtenerCodigosPlaylists(usuario.getPlaylists()));
		
	}



	@Override
	public List<Usuario> recuperarTodosUsuarios() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario recuperarUsuario(int id) {
		
	}
	//Auxiliar
	private String obtenerCodigosPlaylists(List<ListaCanciones> playlists) {
		String aux = "";
		for (ListaCanciones lc : playlists) {
			aux += lc.getCodigo() + " ";
		}
		return aux.trim();
	}

	
}
