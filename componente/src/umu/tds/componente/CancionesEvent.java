package umu.tds.componente;


import java.util.EventObject;

public class CancionesEvent extends EventObject{

		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		protected Canciones cancionesCargadasPost;
		
		 public CancionesEvent(Object fuente,Canciones nuevo) {
			 super(fuente);
			 this.cancionesCargadasPost = nuevo;
		 
		 }
		 
		 public Canciones getCancionesCargadasPost() {
			 return cancionesCargadasPost;
		 }
}
