package umu.tds.manejador;

import umu.tds.cargadorCanciones.CancionesListener;
import umu.tds.cargadorCanciones.Cargador;

import umu.tds.persistencia.AdaptadorCancionTDS;
import umu.tds.persistencia.AdaptadorListaCancionesTDS;
import umu.tds.persistencia.AdaptadorUsuarioTDS;
import umu.tds.persistencia.DAOException;
import umu.tds.persistencia.FactoriaDAO;

import java.awt.print.Printable;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.EventObject;

import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import umu.tds.dominio.*;

public class AppMusic implements CancionesListener {
	private static AppMusic unicaInstancia = null;
	private Usuario usuario = null;
	private FactoriaDAO factoria;
	private CatalogoUsuarios catalogoUsuarios;
	private CatalogoCanciones catalogoCanciones;
	private AdaptadorListaCancionesTDS adaptadorLC;
	private AdaptadorUsuarioTDS adaptadorU;
	private AdaptadorCancionTDS adaptadorC;
	
	private void inicializarAdaptadores() {
		factoria = null;
		try {
			factoria = FactoriaDAO.getInstancia(FactoriaDAO.DAO_TDS);
		} catch (DAOException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		adaptadorC = (AdaptadorCancionTDS) factoria.getCancionDAO();
		adaptadorLC = (AdaptadorListaCancionesTDS) factoria.getListaCancionesDAO();
		adaptadorU = (AdaptadorUsuarioTDS) factoria.getUsuarioDAO();
	}

	private void inicializarCatalogos() {
		catalogoCanciones = CatalogoCanciones.getUnicaInstancia();
		catalogoUsuarios = CatalogoUsuarios.getUnicaInstancia();
	}

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
		Usuario usu = CatalogoUsuarios.getUnicaInstancia().getUsuario(usuario);
		if (usu != null && usu.getPassword().equals(contraseña)) {
			this.usuario = usu;

			return true;
		}
		return false;
	}

	public void registroUsuario(String nombre, String apellidos, String email, String usuario, String password,
			LocalDate fecha) {
		Usuario user = new Usuario(nombre, apellidos, email, usuario, password, fecha);
		adaptadorU.registrarUsuario(user);
		catalogoUsuarios.addUsuario(user);

	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuariol(Usuario usuario) {
		this.usuario = usuario;
	}

	public void anadirPlaylist(Set<String> lista, String name) {
		ListaCanciones lc = new ListaCanciones(name);
		if (usuario.getPlayList(name) == null) {
			for (String can : lista) {
				Cancion c = catalogoCanciones.getCancion(can);
				lc.addCancion(c);
			}
			adaptadorLC.registrarListaCanciones(lc);
			usuario.addLista(lc);
			adaptadorU.modificarUsuario(usuario);

		} else {
			lc = usuario.getPlayList(name);
			lc.clearCanciones();
			for (String can : lista) {
				Cancion c = catalogoCanciones.getCancion(can);
				lc.addCancion(c);
			}
			usuario.modifySet(lc);
			adaptadorLC.modificarListaCanciones(lc);
			adaptadorU.modificarUsuario(usuario);
		}

	}

	public String[][] getCancionesFromPlaylist(String name) {
		ListaCanciones lc = usuario.getPlayList(name);
		String[][] canciones;
		try {
			canciones = new String[lc.getCanciones().size()][2];
		} catch (NullPointerException e) {
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

	public Set<String> getSetCancionesFromPlaylist(String name) {
		ListaCanciones lc = usuario.getPlayList(name);
		return lc.getCancionesName();
	}

	public boolean esUsuarioRegistrado(String usu) {
		if (catalogoUsuarios.getUsuario(usu) != null) {
			return usu.equals(catalogoUsuarios.getUsuario(usu).getLogin());
		}
		return false;
	}

	public void cargarCanciones(String fich) {
		Cargador carg = new Cargador();
		carg.setArchivoCanciones(fich);

		for (umu.tds.componente.Cancion c : carg.getEvento().getCancionesCargadasPost().getCancion()) {
			umu.tds.dominio.Cancion cancion = new umu.tds.dominio.Cancion(c.getTitulo(), c.getURL(),
					new EstiloMusical(c.getEstilo()), new Interprete(c.getInterprete()), 0);
			adaptadorC.registrarCancion(cancion);
			catalogoCanciones.addCancion(cancion);
		}
	}
	
	
	
	public void cargarCancionesLocales(File f) {
	
		String filename = new File(f.getPath()).getName();
		for (String  s : f.list()) {
			int idx = s.indexOf('-');
			int idx2 = s.indexOf('.');
			umu.tds.dominio.Cancion cancion = new umu.tds.dominio.Cancion(s.substring(idx+1, idx2), f.toString() + '\\'+ s.substring(0,idx) + '-' + s.substring(idx+1),
					new EstiloMusical(filename), new Interprete(s.substring(0,idx)), 0);
			adaptadorC.registrarCancion(cancion);
			catalogoCanciones.addCancion(cancion);
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
	public void nuevasCanciones(EventObject e, String fich) {
		cargarCanciones(fich);
	}

	public Set<String> getPlaylistByName() {
		return usuario.getPlaylistByName();
	}

	public String[] getPlaylistsToString() {
		return usuario.getPlaylistsToString();
	}

	public int getCancionesCargadasSize() {
		return catalogoCanciones.getCanciones().size();
	}

	public String[][] getCancionesCargadas() {
		List<Cancion> canciones = catalogoCanciones.getCanciones();
		String[][] matriz = new String[canciones.size()][2];
		int c1 = 0, c2 = 0;
		for (Cancion c : canciones) {
			matriz[c1][c2] = c.getTitulo();
			matriz[c1][c2 + 1] = c.getInterprete().getNombre();
			c1++;
			c2 = 0;
		}

		return matriz;
	}

	public String[][] buscarCanciones(String titulo, String interprete, String genero) {
		List<Cancion> canciones = catalogoCanciones.getCanciones();
		String[][] filtradas = new String[canciones.size()][2];
		int cont = 0;
		if (titulo.equals("Titulo"))
			titulo = "";
		if (interprete.equals("Interprete"))
			interprete = "";
		if (genero.equals("Genero"))
			genero = "";
		for (Cancion cancion : canciones) {
			if (cancion.getTitulo().toLowerCase().contains(titulo.toLowerCase())
					&& cancion.getInterprete().getNombre().toLowerCase().contains(interprete.toLowerCase())
					&& cancion.getEstilo().getNombre().toLowerCase().contains(genero.toLowerCase())) {
				filtradas[cont][0] = cancion.getTitulo();
				filtradas[cont][1] = cancion.getInterprete().getNombre();
				cont++;
				System.out.println(cancion.getTitulo());
			}
		}
		return filtradas;
	}
	
	public boolean userContainsPlaylist(String name) {
		return getPlaylistByName().contains(name);
	}
	
	public void borrarPlaylist(String name) {
		ListaCanciones lc = usuario.getPlayList(name);
		usuario.removeLista(lc);
		adaptadorLC.borrarListaCanciones(lc);
		adaptadorU.modificarUsuario(usuario);
		
	}
	
	public Cancion getCancion(String name) {
		return catalogoCanciones.getCancion(name);
	}

	public void anadirRepro(String song) {
		Cancion c = getCancion(song);
		c.addReproduccion();
		adaptadorC.modificarCancion(c);
	
	}
	
	public void anadirReciente(String name) {
		Cancion c = getCancion(name);
		if(usuario.anadirRecientes(c)) adaptadorU.modificarUsuario(usuario);
	}

	public DefaultTableModel getCancionesMasReproducidas() {
		List<Cancion> lista = catalogoCanciones.getCanciones();
		String[] columnNames = {"Título", "Intérprete", "NumReproducciones"};
		DefaultTableModel model = new DefaultTableModel(columnNames,0);
		lista.sort(Comparator.comparing(Cancion::getNumReproducciones).reversed());
		for (Cancion c : lista) {
			Vector<String> row = new Vector<String>();
			row.addElement(c.getTitulo());
			row.addElement(c.getInterprete().getNombre());
			row.addElement(String.valueOf(c.getNumReproducciones()));
			model.addRow(row);
		}
		return model;
	}

	
	
	public DefaultTableModel getCancionesRecientes() {
		String[] columnNames = {"Título", "Intérprete"};
		DefaultTableModel model = new DefaultTableModel(columnNames,0);
		for (Cancion c : usuario.getRecientes()) {
			Vector<String> row = new Vector<String>();
			row.addElement(c.getTitulo());
			row.addElement(c.getInterprete().getNombre());
			model.addRow(row);
		}
		return model;
	}

}
