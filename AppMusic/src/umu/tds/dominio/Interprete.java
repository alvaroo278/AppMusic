package umu.tds.dominio;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import umu.tds.dominio.Cancion;

public class Interprete {
	private String nombre;
	private List<Cancion> canciones;

	public Interprete(String nombre) {
		this.nombre = nombre;
		this.canciones = new LinkedList<Cancion>();
	}

	public List<Cancion> getCanciones() {
		return Collections.unmodifiableList(canciones);
	}

	public void setCanciones(List<Cancion> canciones) {
		this.canciones = canciones;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
