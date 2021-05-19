package umu.tds.cargadorCanciones;

import java.io.Serializable;
import java.util.Vector;

import umu.tds.componente.Canciones;



public class Cargador{
	
	private Canciones archivoCanciones;
	private Vector<CancionesListener> cancionesListeners = new Vector<CancionesListener>();
	
	public Cargador(Canciones archivoCanciones) {
		this.archivoCanciones = archivoCanciones;
	}
	
	 public synchronized void addCancionListener(CancionesListener listener){
		 cancionesListeners.addElement(listener);
		 }
		 public synchronized void removeSueldoListener(CancionesListener listener){
		 cancionesListeners.removeElement(listener);
		 } 
	
	private void setArchivoCanciones(Canciones nuevoArchivoCanciones) {
		Canciones anterior = archivoCanciones;
		archivoCanciones = nuevoArchivoCanciones;
		if(!anterior.equals(nuevoArchivoCanciones)) {
			CancionesEvent e = new CancionesEvent(this, anterior,nuevoArchivoCanciones);
		}
	}
		 
	private void notificarCambio(CancionesEvent event) {
		Vector<CancionesListener> lista;
		synchronized(this) {
			lista = (Vector<CancionesListener>)cancionesListeners.clone();
		}
		for(CancionesListener ie: lista) {
			ie.nuevasCanciones(event);
		}
	}
}
