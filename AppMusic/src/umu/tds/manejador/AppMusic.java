package umu.tds.manejador;



import umu.tds.persistencia.AdaptadorCancionTDS;
import umu.tds.persistencia.AdaptadorListaCancionesTDS;
import umu.tds.persistencia.AdaptadorUsuarioTDS;
import umu.tds.persistencia.DAOException;
import umu.tds.persistencia.FactoriaDAO;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.time.LocalDate;

import java.util.Comparator;
import java.util.EventObject;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import umu.tds.componente.*;
import umu.tds.descuentos.Descuento;
import umu.tds.descuentos.DescuentoJoven;
import umu.tds.descuentos.DescuentoJubilados;
import umu.tds.descuentos.DescuentoTemporal;

import javax.swing.table.DefaultTableModel;



import umu.tds.dominio.*;
import umu.tds.dominio.Cancion;

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
			this.usuario.comprobarDescuento();
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

	public DefaultTableModel getCancionesFromPlaylist(String name) {
		ListaCanciones lc = usuario.getPlayList(name);
		DefaultTableModel model = new DefaultTableModel(new Object[] {"Título","Intérprete"}, 0);
		for (Cancion c : lc.getCanciones()) {
			Vector<String> row = new Vector<String>();
			row.addElement(c.getTitulo());
			row.addElement(c.getInterprete().getNombre());
			model.addRow(row);
		}
		return model;

	}
	
	
	
	public void aplicarDescuento() {
		Descuento d;
		LocalDate fechaJubilado = LocalDate.now().minusYears(65);
		LocalDate fechaJoven = LocalDate.now().minusYears(25);
		if(usuario.getFechaNacimiento().isBefore(fechaJubilado)) {
			d = new DescuentoJubilados();
			d.aplicarDescuento(usuario);
		}else if(usuario.getFechaNacimiento().isAfter(fechaJoven)){
			d = new DescuentoJoven();
			d.aplicarDescuento(usuario);
		}else {
			d = new DescuentoTemporal();
			d.aplicarDescuento(usuario);
		}
	}
	

	public Set<String> getSetCancionesNamesFromPlaylist(String name) {
		ListaCanciones lc = usuario.getPlayList(name);
		return lc.getCancionesName();
	}
	
	public String[] getSetCancionesURLsFromPlaylist(String name){
		ListaCanciones lc = usuario.getPlayList(name);
		String[] songs = new String[lc.getCanciones().size()];
		int i = 0;
		for (Cancion c : lc.getCanciones()) {
			songs[i] = c.getRutaFichero();
			i++;
		}
		
		return songs;
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
			umu.tds.dominio.Cancion cancion = new umu.tds.dominio.Cancion(s.substring(idx+2, idx2), f.toString() + '\\'+ s.substring(0,idx) + '-' + s.substring(idx+1),
					new EstiloMusical(filename), new Interprete(s.substring(0,idx)), 0);
			adaptadorC.registrarCancion(cancion);
			catalogoCanciones.addCancion(cancion);
		}
		
		
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

	public DefaultTableModel getCancionesCargadas() {
		List<Cancion> canciones = catalogoCanciones.getCanciones();
		DefaultTableModel model = new DefaultTableModel(new Object[] {"Título","Intérprete"} , 0);
		for (Cancion c : canciones) {
			Vector<String> row = new Vector<String>();
			row.addElement(c.getTitulo());
			row.addElement(c.getInterprete().getNombre());
			model.addRow(row);
		}

		return model;
	}

	public DefaultTableModel buscarCanciones(String titulo, String interprete, String genero) {
		List<Cancion> canciones = catalogoCanciones.getCanciones();
		DefaultTableModel model = new DefaultTableModel(new Object[] {"Título","Intérprete"} , 0);
		if (titulo.equals("Titulo"))
			titulo = "";
		if (interprete.equals("Interprete"))
			interprete = "";
		if (genero.equals("Genero"))
			genero = "";
		for (Cancion c : canciones) {
			Vector<String> row = new Vector<String>();
			if (c.getTitulo().toLowerCase().contains(titulo.toLowerCase())
					&& c.getInterprete().getNombre().toLowerCase().contains(interprete.toLowerCase())
					&& c.getEstilo().getNombre().toLowerCase().contains(genero.toLowerCase())) {
					row.addElement(c.getTitulo());
					row.addElement(c.getInterprete().getNombre());
					model.addRow(row);
			}
		}
	
		return model;
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
		int n = 0;
		if(lista.isEmpty())return new DefaultTableModel();
		if(lista.size()<10) {
			 n = lista.size();
		}else {
			n = 10;
		}
		for(int i = 0; i<n;i++) {
			Vector<String> row = new Vector<String>();
			row.addElement(lista.get(i).getTitulo());
			row.addElement(lista.get(i).getInterprete().getNombre());
			row.addElement(String.valueOf(lista.get(i).getNumReproducciones()));
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

	public void logout() {
		this.usuario = null;
		
	}

	public boolean esUsuarioPremium() {
		return usuario.isPremium();
	}

	
	public String getRutaCancion(String name) {
		return getCancion(name).getRutaFichero();
	}

	public void premium() {
		this.usuario.setPremium(true);
		adaptadorU.modificarUsuario(usuario);
	}
	
	
	public void generarPdf() throws FileNotFoundException, DocumentException {
		if(usuario.isPremium()) {
			FileOutputStream f = new FileOutputStream("Resumen.pdf");
			Document d  = new Document();
			PdfWriter.getInstance(d, f);
			d.open();
			
			d.add(new Paragraph("Resumen de " + usuario.getLogin()+ "\n\n",FontFactory.getFont("arial",22,Font.BOLD,BaseColor.BLUE)));
			
			Font fo = new Font();
			fo.setStyle(Font.BOLD);
			fo.setFamily("Times New Roman");
			for (ListaCanciones lc : usuario.getPlaylists()) {
				d.add(new Paragraph("Playlist ---> " + lc.getNombre().toUpperCase() + "\n\n"));
				PdfPTable tabla= new PdfPTable(3);
				tabla.addCell(new Phrase(0, "Titulo", fo));
				tabla.addCell(new Phrase(0, "Interprete", fo));
				tabla.addCell(new Phrase(0, "Estilo", fo));
				for (Cancion c : lc.getCanciones()) {
					tabla.addCell(c.getTitulo());
					tabla.addCell(c.getInterprete().getNombre());
					tabla.addCell(c.getEstilo().getNombre());
				}
				d.add(tabla);
				d.add(new Paragraph("\n\n"));
			}
			d.close();
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
	
}
	
	
	

