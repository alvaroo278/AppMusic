package umu.tds.dominio;

public class Cancion {

	private int identificador;
	private String titulo;
	private String rutaFichero;
	private Integer numReproducciones;

	public Cancion(String titulo, String rutaFichero, Integer numReproducciones) {
		this.identificador = 0;
		this.titulo = titulo;
		this.rutaFichero = rutaFichero;
		this.numReproducciones = numReproducciones;
	}
	public int getIdentificador() {
		return identificador;
	}

	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getRutaFichero() {
		return rutaFichero;
	}

	public void setRutaFichero(String rutaFichero) {
		this.rutaFichero = rutaFichero;
	}

	public int getNumReproducciones() {
		return numReproducciones;
	}

	public void setNumReproducciones(Integer numReproducciones) {
		this.numReproducciones = numReproducciones;
	}
}
