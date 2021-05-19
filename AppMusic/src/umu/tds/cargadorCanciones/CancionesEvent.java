package umu.tds.cargadorCanciones;

import java.io.Serializable;
import java.util.EventObject;

import umu.tds.componente.Canciones;

public class CancionesEvent extends EventObject{

		protected Canciones cancionesCargadasAnt,cancionesCargadasPost;
		
		 public CancionesEvent(Object fuente,Canciones anterior,Canciones nuevo) {
		 super(fuente);
		 this.cancionesCargadasAnt = anterior;
		 this.cancionesCargadasPost = nuevo;
		 
		 }
		 
		 public Canciones getCancionesCargadasAnt() {
			 return cancionesCargadasAnt;
		 }
		 public Canciones getCancionesCargadasPost() {
			 return cancionesCargadasPost;
		 }
}
