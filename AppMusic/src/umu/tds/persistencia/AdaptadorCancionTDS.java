package umu.tds.persistencia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import beans.Entidad;
import beans.Propiedad;
import umu.tds.dominio.Cancion;
import umu.tds.dominio.EstiloMusical;
import umu.tds.dominio.Interprete;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public class AdaptadorCancionTDS implements IAdaptadorCancionDAO {

	private static ServicioPersistencia servPersistencia;
	private static AdaptadorCancionTDS unicaInstancia = null;

	public static AdaptadorCancionTDS getUnicaInstancia() { // patron singleton
		if (unicaInstancia == null) {
			return new AdaptadorCancionTDS();
		} else
			return unicaInstancia;
	}

	private AdaptadorCancionTDS() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}

	@Override
	public void registrarCancion(Cancion cancion) {
		Entidad eCancion = null;
		boolean existe = true;
		try {
			eCancion = servPersistencia.recuperarEntidad(cancion.getIdentificador());
		} catch (NullPointerException e) {
			existe = false;
		}
		if (existe)
			return;

		eCancion = new Entidad();
		eCancion.setNombre("cancion");
		eCancion.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(new Propiedad("titulo", cancion.getTitulo()),
				new Propiedad("rutaFichero", cancion.getRutaFichero()),
				new Propiedad("estilo", cancion.getEstilo().getNombre()),
				new Propiedad("interprete", cancion.getInterprete().getNombre()),
				new Propiedad("numReproducciones", String.valueOf(cancion.getNumReproducciones())))));

		eCancion = servPersistencia.registrarEntidad(eCancion);

		cancion.setIdentificador(eCancion.getId());
	}

	@Override
	public void borrarCancion(Cancion cancion) {
		Entidad eCancion = servPersistencia.recuperarEntidad(cancion.getIdentificador());
		servPersistencia.borrarEntidad(eCancion);

	}
	@Override
	public Cancion recuperarCancion(int codigo) {
		Entidad eCancion;
		String titulo;
		String estilo;
		String interprete;
		String rutaFichero;
		int numReproducciones;

		eCancion = servPersistencia.recuperarEntidad(codigo);
		titulo = servPersistencia.recuperarPropiedadEntidad(eCancion, "titulo");
		rutaFichero = servPersistencia.recuperarPropiedadEntidad(eCancion, "rutaFichero");
		estilo = servPersistencia.recuperarPropiedadEntidad(eCancion, "estilo");
		interprete = servPersistencia.recuperarPropiedadEntidad(eCancion, "interprete");	
		numReproducciones = Integer.parseInt(servPersistencia.recuperarPropiedadEntidad(eCancion, "numReproducciones"));

		Cancion cancion = new Cancion(titulo, rutaFichero,new EstiloMusical(estilo),new Interprete(interprete) ,numReproducciones);
		cancion.setIdentificador(codigo);
		return cancion;
	}

	@Override
	public List<Cancion> recuperarTodasCanciones() {
		List<Cancion> canciones = new LinkedList<Cancion>();
		List<Entidad> entidades = servPersistencia.recuperarEntidades("cancion");
		
		for (Entidad eCancion : entidades) {
			canciones.add(recuperarCancion(eCancion.getId()));
		}
		return canciones;
	}
}
