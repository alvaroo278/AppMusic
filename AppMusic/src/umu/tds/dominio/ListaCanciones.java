package umu.tds.dominio;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class ListaCanciones {
	private String nombre;
	private Set<Cancion> canciones;
	private int codigo;

	public ListaCanciones(String nombre) {
		this.nombre = nombre;
		canciones = new HashSet<Cancion>();
	}

	public void addCancion(Cancion c) {
		canciones.add(c);
	}

	public Set<Cancion> getCanciones() {
		return Collections.unmodifiableSet(canciones);
	}
	
	public Set<String> getCancionesName(){
		Set<String> nombres = new HashSet<String>();
		for (Cancion cancion : canciones) {
			nombres.add(cancion.getTitulo());
		}
		return nombres;
	}
	public void clearCanciones() {
		this.canciones.clear();
	}
	
	public void setCanciones(Set<Cancion> canciones) {
		this.canciones = canciones;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	
	
}
