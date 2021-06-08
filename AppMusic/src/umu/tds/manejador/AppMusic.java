package umu.tds.manejador;



import umu.tds.cargadorCanciones.CancionesListener;
import umu.tds.cargadorCanciones.Cargador;
import umu.tds.componente.*;
import umu.tds.componente.MapperCancionesXMLtoJava;
import umu.tds.persistencia.AdaptadorCancionTDS;
import umu.tds.persistencia.AdaptadorListaCancionesTDS;
import umu.tds.persistencia.AdaptadorUsuarioTDS;
import umu.tds.persistencia.DAOException;
import umu.tds.persistencia.FactoriaDAO;
import umu.tds.persistencia.IAdaptadorCancionDAO;
import umu.tds.persistencia.IAdaptadorListaCancionesDAO;
import umu.tds.persistencia.IAdaptadorUsuarioDAO;

import java.time.LocalDate;
import java.util.EventObject;
import java.util.List;
import java.util.Set;

import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.xml.crypto.dsig.CanonicalizationMethod;

import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.dominio.*;
import umu.tds.dominio.Cancion;
public class AppMusic implements CancionesListener{
	private static AppMusic unicaInstancia = null;
	private Usuario usuario;
	private FactoriaDAO factoria;

	public static AppMusic getUnicaInstancia() {
		if (unicaInstancia == null)
			unicaInstancia = new AppMusic();
		return unicaInstancia;
	}

	
	private AppMusic() {
		usuario = null;
		try {
			factoria = FactoriaDAO.getInstancia();
		}catch (DAOException e) {
			e.printStackTrace();
		}
	}

	public boolean login(String usuario, String contraseņa) {
		// Comprobar los datos del usuario con la base de datos
		Usuario usu = CatalogoUsuarios.getUnicaInstancia().getUsuario(usuario);
		if(usu != null && usu.getPassword().equals(contraseņa)) {
			this.usuario = usu;
			
			return true;
		}
			return false;
	}

	public void registroUsuario(String nombre, String apellidos, String email, String usuario, String password,
			LocalDate fecha) {
		Usuario user = new Usuario(nombre, apellidos, email, usuario, password, fecha);
		AdaptadorUsuarioTDS.getUnicaInstancia().registrarUsuario(user);
		CatalogoUsuarios.getUnicaInstancia().addUsuario(user);

	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuariol(Usuario usuario) {
		this.usuario = usuario;
	}


	public void anadirPlaylist(List<String> lista, String name) {
		ListaCanciones lc = new ListaCanciones(name);
		for (String can : lista) {
			Cancion c = CatalogoCanciones.getUnicaInstancia().getCancion(can);
			lc.addCancion(c);
		}
		usuario.addLista(lc);
		AdaptadorListaCancionesTDS.getUnicaInstancia().registrarListaCanciones(lc);
		AdaptadorUsuarioTDS.getUnicaInstancia().modificarUsuario(usuario);
	}

	public String[][] getCancionesFromPlaylist(String name){
		ListaCanciones lc = usuario.getPlayList(name);
		String[][] canciones;
		try {
			canciones = new String[lc.getCanciones().size()][2];
		}catch (NullPointerException e) {
			return null;
		}

		int cont = 0;
		for (Cancion c : lc.getCanciones()) {
			canciones[cont][0] = c.getTitulo();
			canciones[cont][1] = c.getInterprete().getNombre();
			cont++;
		}
		return canciones;
	}
	
	public boolean esUsuarioRegistrado(String usu) {
		if(CatalogoUsuarios.getUnicaInstancia().getUsuario(usu) != null ) {
			return usu.equals(CatalogoUsuarios.getUnicaInstancia().getUsuario(usu).getLogin());
		}
		return false;
	}

	public void cargarCanciones(String fich) {
		Cargador carg = new Cargador();
		carg.setArchivoCanciones(fich);
		
		for (umu.tds.componente.Cancion c  : carg.getEvento().getCancionesCargadasPost().getCancion()) {
			umu.tds.dominio.Cancion cancion = new umu.tds.dominio.Cancion(c.getTitulo(), c.getURL(),new EstiloMusical(c.getEstilo()),
					new Interprete(c.getInterprete()), 0) ;
			System.out.println("TITULO ES " + cancion.getTitulo());
			AdaptadorCancionTDS.getUnicaInstancia().registrarCancion(cancion);
			CatalogoCanciones.getUnicaInstancia().addCancion(cancion);
		}
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


	@Override
	public void nuevasCanciones(EventObject e,String fich) {
		cargarCanciones(fich);
	}

	public Set<String> getPlaylistByName(){
		return usuario.getPlaylistByName();
	}
	
	public String[] getPlaylistsToString(){
		return usuario.getPlaylistsToString();
	}

	public int getCancionesCargadasSize() {
		return CatalogoCanciones.getUnicaInstancia().getCanciones().size();
	}
	
	public String[][] getCancionesCargadas() {
		List<Cancion> canciones = CatalogoCanciones.getUnicaInstancia().getCanciones();
		String[][] matriz = new String[canciones.size()][2];
		int c1 = 0,c2 = 0;
		for (Cancion c : canciones) {
			matriz[c1][c2] = c.getTitulo();
			matriz[c1][c2+1] = c.getInterprete().getNombre();
			c1++;
			c2 = 0;
		}
		
		return matriz;
	}
	
	public String[][] buscarCanciones(String titulo, String interprete, String genero){
		List<Cancion> canciones = CatalogoCanciones.getUnicaInstancia().getCanciones();
		String[][] filtradas = new String[canciones.size()][2];
		int cont= 0;
		if(titulo.equals("Titulo")) titulo = "";
		if(interprete.equals("Interprete")) interprete = "";
		if(genero.equals("Genero")) genero = "";
		for (Cancion cancion : canciones) {
			if(cancion.getTitulo().toLowerCase().contains(titulo.toLowerCase()) && cancion.getInterprete().getNombre().toLowerCase().contains(interprete.toLowerCase()) 
					&& cancion.getEstilo().getNombre().toLowerCase().contains(genero.toLowerCase()) ) {
				filtradas[cont][0] = cancion.getTitulo();
				filtradas[cont][1] = cancion.getInterprete().getNombre();
				cont++;
				System.out.println(cancion.getTitulo());
			}
		}
		return filtradas;
	}
	
	
	
}
