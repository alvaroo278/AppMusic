package umu.tds.dominio;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.jgoodies.looks.plastic.PlasticButtonUI;

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
	private Set<ListaCanciones> playlists;
	
	public Usuario(String nombre, String apellidos, String email, String usuario, String password,
			LocalDate fechaNacimiento) {
		this.id = 0;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.usuario = usuario;
		this.password = password;
		this.fechaNacimiento = fechaNacimiento;
		playlists = new HashSet<ListaCanciones>();
	}

	public void addLista(ListaCanciones lc) {
		playlists.add(lc);
	}
	
	public void removeLista(ListaCanciones lc) {
		playlists.remove(lc);
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

	public ListaCanciones getPlayList(String name) {
		for (ListaCanciones listaCanciones : playlists) {
			if(listaCanciones.getNombre().equals(name)) return listaCanciones;
		}
		return null;
	}
	
	public Set<ListaCanciones> getPlaylists() {
		return playlists;
	}

	public Set<String> getPlaylistByName(){
		Set<String> names  = new HashSet<String>();
		for (ListaCanciones listaCanciones : playlists) {
			names.add(listaCanciones.getNombre());
		}
		return Collections.unmodifiableSet(names);
	}
	
	public String[] getPlaylistsToString() {
		String[] lines = new String[playlists.size()];
		int cont = 0;
		for (ListaCanciones listaCanciones : playlists) {
			lines[cont] = listaCanciones.getNombre();
			cont++;
		}
		return lines;
	}
	
	public void setPlaylists(Set<ListaCanciones> playlists) {
		this.playlists = playlists;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apellidos == null) ? 0 : apellidos.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((fechaNacimiento == null) ? 0 : fechaNacimiento.hashCode());
		result = prime * result + id;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((playlists == null) ? 0 : playlists.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
		Usuario other = (Usuario) obj;
		if (apellidos == null) {
			if (other.apellidos != null)
				return false;
		} else if (!apellidos.equals(other.apellidos))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (fechaNacimiento == null) {
			if (other.fechaNacimiento != null)
				return false;
		} else if (!fechaNacimiento.equals(other.fechaNacimiento))
			return false;
		if (id != other.id)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (playlists == null) {
			if (other.playlists != null)
				return false;
		} else if (!playlists.equals(other.playlists))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}

	
	
}
