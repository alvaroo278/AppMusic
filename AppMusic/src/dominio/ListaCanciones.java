package dominio;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ListaCanciones {
	private String nombre;
	private List<Cancion> canciones;
	private int codigo;
	
	public ListaCanciones(String nombre) {
		this.nombre = nombre;
		canciones = new LinkedList<Cancion>();
	}

	public void addCancion(Cancion c) {
		canciones.add(c);
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

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	
}
