package umu.tds.persistencia;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import beans.Entidad;
import beans.Propiedad;
import umu.tds.dominio.Cancion;
import umu.tds.dominio.ListaCanciones;
import umu.tds.dominio.Usuario;

import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public class AdaptadorUsuarioTDS implements IAdaptadorUsuarioDAO {

	static private final String SEPARADOR = "-";

	private static ServicioPersistencia servPersistencia;
	private static AdaptadorUsuarioTDS unicaInstancia = null;

	public static AdaptadorUsuarioTDS getUnicaInstancia() {
		if (unicaInstancia == null)
			return new AdaptadorUsuarioTDS();
		return unicaInstancia;
	}

	private AdaptadorUsuarioTDS() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}

	@Override
	public void registrarUsuario(Usuario usuario) {
		Entidad eUsuario;

		eUsuario = servPersistencia.recuperarEntidad(usuario.getId());
		if (eUsuario != null) {
			return;
		}

		AdaptadorListaCancionesTDS adaptadorLista = AdaptadorListaCancionesTDS.getUnicaInstancia();
		for (ListaCanciones lc : usuario.getPlaylists()) {
			adaptadorLista.registrarListaCanciones(lc);
		}

		eUsuario = new Entidad();
		eUsuario.setNombre("usuario");
		eUsuario.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(new Propiedad("nombre", usuario.getNombre()),
				new Propiedad("apellidos", usuario.getApellidos()), new Propiedad("email", usuario.getEmail()),
				new Propiedad("login", usuario.getLogin()), new Propiedad("password", usuario.getPassword()),
				new Propiedad("fechaN", usuario.getFechaNacimiento().toString()),
				new Propiedad("ListaCanciones", obtenerCodigosPlaylists(usuario.getPlaylists())))));

		eUsuario = servPersistencia.registrarEntidad(eUsuario);
		usuario.setId(eUsuario.getId());
	}

	@Override
	public void borrarUsuario(Usuario usuario) {
		Entidad eUsuario = servPersistencia.recuperarEntidad(usuario.getId());
		servPersistencia.borrarEntidad(eUsuario);

	}

	public void comprobarUsuario(Usuario usuario) {
		Entidad eUsuario = servPersistencia.recuperarEntidad(usuario.getId());
		System.out.println(eUsuario.getId() + " " + eUsuario.getNombre());
		for (Propiedad p : eUsuario.getPropiedades()) {
			System.out.println(p.getValor());
		}
	}

	public void borrarListasUsuario(Usuario usuario) {
		Entidad eUsuario = servPersistencia.recuperarEntidad(usuario.getId());
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "ListaCanciones");
	}

	@Override
	public void modificarUsuario(Usuario usuario) {
		Entidad eUsuario = servPersistencia.recuperarEntidad(usuario.getId());
		
		
		
		for (Propiedad prop : eUsuario.getPropiedades()) {
			if (prop.getNombre().equals("nombre")) {
				prop.setValor(usuario.getNombre());
			}else if (prop.getNombre().equals("apellidos")) {
				prop.setValor(usuario.getApellidos());
			}else if (prop.getNombre().equals("email")) {
				prop.setValor(usuario.getEmail());
			}else if (prop.getNombre().equals("login")) {
				prop.setValor(usuario.getLogin());
			}else if (prop.getNombre().equals("password")) {
				prop.setValor(usuario.getPassword());
			}else if (prop.getNombre().equals("fechaN")) {
				prop.setValor(usuario.getFechaNacimiento().toString());
			}else if (prop.getNombre().equals("ListaCanciones")) {
				String canciones = obtenerCodigosPlaylists(usuario.getPlaylists());
				prop.setValor(canciones);
			}
			servPersistencia.modificarPropiedad(prop);
		}

	}

	@Override
	public List<Usuario> recuperarTodosUsuarios() {
		List<Entidad> eUsuarios = servPersistencia.recuperarEntidades("usuario");

		List<Usuario> users = new LinkedList<Usuario>();

		for (Entidad eUsuario : eUsuarios) {
			users.add(recuperarUsuario(eUsuario.getId()));
		}
		return users;
	}

	@Override
	public Usuario recuperarUsuario(int id) {
		if (PoolDAO.getUnicaInstancia().contiene(id)) {
			return (Usuario) PoolDAO.getUnicaInstancia().getObjeto(id);
		}

		Entidad eUsuario;
		List<ListaCanciones> playlists = new LinkedList<ListaCanciones>();
		String nombre;
		String apellidos;
		String email;
		String login;
		String fechaN;
		String password;

		eUsuario = servPersistencia.recuperarEntidad(id);

		nombre = servPersistencia.recuperarPropiedadEntidad(eUsuario, "nombre");

		apellidos = servPersistencia.recuperarPropiedadEntidad(eUsuario, "apellidos");
		email = servPersistencia.recuperarPropiedadEntidad(eUsuario, "email");
		login = servPersistencia.recuperarPropiedadEntidad(eUsuario, "login");

		password = servPersistencia.recuperarPropiedadEntidad(eUsuario, "password");
		fechaN = servPersistencia.recuperarPropiedadEntidad(eUsuario, "fechaN");

		Usuario user = new Usuario(nombre, apellidos, email, login, password, stringToLocalDate(fechaN));
		user.setId(id);

		PoolDAO.getUnicaInstancia().addObjeto(id, user);

		playlists = obtenerPlaylistsDesdeCodigos(
				servPersistencia.recuperarPropiedadEntidad(eUsuario, "ListaCanciones"));
		for (ListaCanciones lc : playlists) {
			user.addLista(lc);
		}

		return user;

	}

	// Auxiliar
	private String obtenerCodigosPlaylists(Set<ListaCanciones> playlists) {
		String aux = "";
		for (ListaCanciones lc : playlists) {
			aux += lc.getCodigo() + " ";
		}
		return aux.trim();
	}

	private LocalDate stringToLocalDate(String fecha) {
		String[] lines = fecha.split(String.valueOf(SEPARADOR));
		return LocalDate.of(Integer.parseInt(lines[0]), Integer.parseInt(lines[1]), Integer.parseInt(lines[2]));

	}

	private List<ListaCanciones> obtenerPlaylistsDesdeCodigos(String playlists) {

		List<ListaCanciones> listaCanciones = new LinkedList<ListaCanciones>();
		StringTokenizer strTok = new StringTokenizer(playlists, " ");

		while (strTok.hasMoreTokens()) {
			ListaCanciones lc = AdaptadorListaCancionesTDS.getUnicaInstancia()
					.recuperarListaCanciones(Integer.valueOf((String) strTok.nextElement()));
			listaCanciones.add(lc);
		}
		return listaCanciones;
	}

	public void borrarTodos() {
		List<Usuario> users = recuperarTodosUsuarios();
		for (Usuario usuario : users) {
			borrarUsuario(usuario);
		}
	}

}
