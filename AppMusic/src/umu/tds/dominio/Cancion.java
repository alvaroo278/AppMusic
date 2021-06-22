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

	public void addReproduccion() {
		this.numReproducciones++;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((estilo == null) ? 0 : estilo.hashCode());
		result = prime * result + identificador;
		result = prime * result + ((interprete == null) ? 0 : interprete.hashCode());
		result = prime * result + ((numReproducciones == null) ? 0 : numReproducciones.hashCode());
		result = prime * result + ((rutaFichero == null) ? 0 : rutaFichero.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cancion other = (Cancion) obj;
		if (estilo == null) {
			if (other.estilo != null)
				return false;
		} else if (!estilo.equals(other.estilo))
			return false;
		if (identificador != other.identificador)
			return false;
		if (interprete == null) {
			if (other.interprete != null)
				return false;
		} else if (!interprete.equals(other.interprete))
			return false;
		if (numReproducciones == null) {
			if (other.numReproducciones != null)
				return false;
		} else if (!numReproducciones.equals(other.numReproducciones))
			return false;
		if (rutaFichero == null) {
			if (other.rutaFichero != null)
				return false;
		} else if (!rutaFichero.equals(other.rutaFichero))
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		return true;
	}
	
	

	
}
