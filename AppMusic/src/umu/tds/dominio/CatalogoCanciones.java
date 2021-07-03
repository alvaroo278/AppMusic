package umu.tds.dominio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import umu.tds.persistencia.DAOException;
import umu.tds.persistencia.FactoriaDAO;
import umu.tds.persistencia.IAdaptadorCancionDAO;

public class CatalogoCanciones {

	private Map<String, Cancion> canciones;
	private static CatalogoCanciones unicaInstancia;

	private FactoriaDAO dao;
	private IAdaptadorCancionDAO adaptadorCancion;

	private CatalogoCanciones() {
		try {
			dao = FactoriaDAO.getInstancia(FactoriaDAO.DAO_TDS);
			adaptadorCancion = dao.getCancionDAO();
			canciones = new HashMap<String, Cancion>();
			this.cargarCatalogo();
		} catch (DAOException eDAO) {
			eDAO.printStackTrace();
		}
	}

	
	public static CatalogoCanciones getUnicaInstancia() {
		if(unicaInstancia == null) return new CatalogoCanciones();
		return unicaInstancia;
	}

	public List<Cancion> getCanciones() {
		ArrayList<Cancion> lista = new ArrayList<Cancion>();
		for (Cancion c : canciones.values()) 
			lista.add(c);	
		return lista;
	}

	public Cancion getCancion(int codigo) {
		for (Cancion c : canciones.values()) {
			if (c.getIdentificador() == codigo)
				return c;
		}
		return null;
	}

	public Cancion getCancion(String titulo) {
		return canciones.get(titulo);
	}

	public void addCancion(Cancion can) {
		canciones.put(can.getTitulo(), can);
	
	}
	
	public Set<Cancion> getCanciones(Interprete i){
		Set<Cancion> lista = new HashSet<Cancion>();
		for (Cancion cancion : canciones.values()) {
			if(cancion.getInterprete().equals(i)) {
				lista.add(cancion);
			}
		}
		return lista;
	}

	public void removeCancion(Cancion can) {
		canciones.remove(can.getTitulo());
	}

	private void cargarCatalogo() throws DAOException {
		List<Cancion> cancionesBD = adaptadorCancion.recuperarTodasCanciones();
		for (Cancion pro : cancionesBD)
			canciones.put(pro.getTitulo(), pro);
	}
}
