package dominio;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public class Usuario {
	private int id;
	private String nombre;
	private String apellidos;
	private String email;
	private String usuario;
	private String password;
	private LocalDate fechaNacimiento;
	private List<ListaCanciones> playlists;
	
	
	public Usuario(String nombre, String apellidos, String email, String usuario, String password,
			LocalDate fechaNacimiento) {
		this.id = 0;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.usuario = usuario;
		this.password = password;
		this.fechaNacimiento = fechaNacimiento;
		playlists = new LinkedList<ListaCanciones>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return usuario;
	}

	public void setLogin(String login) {
		this.usuario = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public List<ListaCanciones> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(List<ListaCanciones> playlists) {
		this.playlists = playlists;
	}
	
	
}
