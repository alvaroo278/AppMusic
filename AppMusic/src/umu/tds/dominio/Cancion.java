package umu.tds.dominio;

public class Cancion {

	private int identificador;
	private String titulo;
	private String rutaFichero;
	private EstiloMusical estilo;
	private Interprete interprete;
	private Integer numReproducciones;

	public Cancion(String titulo, String rutaFichero,EstiloMusical estilo, Interprete interprete, Integer numReproducciones) {
		this.identificador = 0;
		this.titulo = titulo;
		this.rutaFichero = rutaFichero;
		this.estilo = estilo;
		this.interprete = interprete;
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
	public EstiloMusical getEstilo() {
		return estilo;
	}
	public void setEstilo(EstiloMusical estilo) {
		this.estilo = estilo;
	}
	public Interprete getInterprete() {
		return interprete;
	}
	public void setInterprete(Interprete interprete) {
		this.interprete = interprete;
	}
	

	
}
