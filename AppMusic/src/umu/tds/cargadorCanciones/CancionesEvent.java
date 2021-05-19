package umu.tds.cargadorCanciones;

import java.io.Serializable;
import java.util.EventObject;

import umu.tds.componente.Canciones;

public class CancionesEvent extends EventObject{

		protected Canciones cancionesCargadasPost;
		
		 public CancionesEvent(Object fuente,Canciones nuevo) {
		 super(fuente);
		 this.cancionesCargadasPost = nuevo;
		 
		 }
		 
		 public Canciones getCancionesCargadasPost() {
			 return cancionesCargadasPost;
		 }
}
