package persistencia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import beans.Entidad;
import beans.Propiedad;
import dominio.Cancion;
import dominio.ListaCanciones;
import tds.driver.ServicioPersistencia;
import tds.driver.FactoriaServicioPersistencia;

public class AdaptadorListaCancionesTDS implements IAdaptadorListaCancionesDAO {

	private static ServicioPersistencia servPersistencia;
	private static AdaptadorListaCancionesTDS unicaInstancia;

	public static AdaptadorListaCancionesTDS getUnicaInstancia() {
		if (unicaInstancia == null)
			return new AdaptadorListaCancionesTDS();
		return unicaInstancia;
	}

	private AdaptadorListaCancionesTDS() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}

	@Override
	public void registrarListaCanciones(ListaCanciones playlists) {
		// TODO Auto-generated method stub
		Entidad eListaCanciones;
		boolean existe = true;
		try {
			eListaCanciones = servPersistencia.recuperarEntidad(playlists.getCodigo());
		} catch (NullPointerException e) {
			existe = false;
		}
		if (existe)
			return;

		AdaptadorCancionTDS adaptadorCancion = AdaptadorCancionTDS.getUnicaInstancia();
		for (Cancion c : playlists.getCanciones()) {
			adaptadorCancion.registrarCancion(c);
		}

		eListaCanciones = new Entidad();
		eListaCanciones.setNombre("ListaCanciones");
		eListaCanciones
				.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(new Propiedad("nombre", playlists.getNombre()),
						new Propiedad("canciones", obtenerCodigosCanciones(playlists.getCanciones())))));
		eListaCanciones = servPersistencia.registrarEntidad(eListaCanciones);
		playlists.setCodigo(eListaCanciones.getId());
	}

	@Override
	public void borrarListaCanciones(ListaCanciones playlists) {
		Entidad eListaCanciones = servPersistencia.recuperarEntidad(playlists.getCodigo());
		servPersistencia.borrarEntidad(eListaCanciones);

	}

	@Override
	public void modificarListaCanciones(ListaCanciones playlists) {
		Entidad eListaCanciones = servPersistencia.recuperarEntidad(playlists.getCodigo());

		servPersistencia.eliminarPropiedadEntidad(eListaCanciones, "nombre");
		servPersistencia.anadirPropiedadEntidad(eListaCanciones, "nombre", playlists.getNombre());
		String canciones = obtenerCodigosCanciones(playlists.getCanciones());
		servPersistencia.eliminarPropiedadEntidad(eListaCanciones, "canciones");
		servPersistencia.anadirPropiedadEntidad(eListaCanciones, "canciones", canciones);

	}

	@Override
	public ListaCanciones recuperarListaCanciones(int codigo) {
		Entidad eListaCanciones;
		List<Cancion> canciones = new LinkedList<Cancion>();
		String nombre;

		// recuperar entidad
		eListaCanciones = servPersistencia.recuperarEntidad(codigo);

		// recuperar propiedades que no son objetos
		nombre = servPersistencia.recuperarPropiedadEntidad(eListaCanciones, "nombre");

		ListaCanciones playlist = new ListaCanciones(nombre);
		playlist.setCodigo(codigo);

		canciones = obtenerCancionesDesdeCodigos(
				servPersistencia.recuperarPropiedadEntidad(eListaCanciones, "canciones"));

		for (Cancion c : canciones)
			playlist.addCancion(c);

		return playlist;
	}

	@Override
	public List<ListaCanciones> recuperarTodasLasListas() {
		List<Entidad> ePlaylists = servPersistencia.recuperarEntidades("ListaCanciones");
		List<ListaCanciones> listaCanciones = new LinkedList<ListaCanciones>();

		for (Entidad eListaCancion : ePlaylists) {
			listaCanciones.add(recuperarListaCanciones(eListaCancion.getId()));
		}
		return listaCanciones;
	}

	private List<Cancion> obtenerCancionesDesdeCodigos(String canciones) {
		List<Cancion> listaCanciones = new LinkedList<Cancion>();
		StringTokenizer str = new StringTokenizer(canciones, " ");
		AdaptadorCancionTDS adaptadorC = AdaptadorCancionTDS.getUnicaInstancia();
		while (str.hasMoreTokens()) {
			listaCanciones.add(adaptadorC.recuperarCancion(Integer.valueOf((String) str.nextElement())));
		}
		return listaCanciones;
	}

	// Auxiliar
	private String obtenerCodigosCanciones(List<Cancion> canciones) {
		String str = "";
		for (Cancion c : canciones) {
			str += c.getIdentificador() + " ";
		}
		return str.trim();
	}

}
