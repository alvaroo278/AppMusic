package umu.tds.cargadorCanciones;

import java.util.Vector;

import umu.tds.componente.Canciones;
import umu.tds.componente.MapperCancionesXMLtoJava;

public class Cargador {

	private Canciones archivoCanciones = null;
	private Vector<CancionesListener> cancionesListeners = new Vector<CancionesListener>();
	private CancionesEvent e;

	public synchronized void addCancionListener(CancionesListener listener) {
		cancionesListeners.addElement(listener);
	}

	public synchronized void removeCancionListener(CancionesListener listener) {
		cancionesListeners.removeElement(listener);
	}

	public void setArchivoCanciones(String nuevoArchivoCanciones) {
		Canciones anterior = archivoCanciones;
		archivoCanciones = MapperCancionesXMLtoJava.cargarCanciones(nuevoArchivoCanciones);

		try {
			if (!anterior.equals(archivoCanciones));
		} catch (NullPointerException excp) {
			System.out.println("primera");
		}
		e = new CancionesEvent(this, archivoCanciones);
	}

	public CancionesEvent getEvento() {
		return e;
	}

	public void notificarCambio(CancionesEvent event, String fich) {
		Vector<CancionesListener> lista;
		synchronized (this) {
			Vector<CancionesListener> clone = (Vector<CancionesListener>) cancionesListeners.clone();
			lista = clone;
		}
		for (CancionesListener ie : lista) {
			ie.nuevasCanciones(event, fich);
		}
	}
	
}
