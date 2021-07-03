package umu.tds.dominio;



import java.util.Set;

import umu.tds.dominio.Cancion;

public class Interprete {
	private String nombre;

	public Interprete(String nombre) {
		this.nombre = nombre;
	}

	public Set<Cancion> getCanciones() {
		return CatalogoCanciones.getUnicaInstancia().getCanciones(this);
	}



	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
