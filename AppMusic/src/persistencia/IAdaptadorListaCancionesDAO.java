package persistencia;

import java.util.List;

import dominio.ListaCanciones;


public interface IAdaptadorListaCancionesDAO {

	public void registrarListaCanciones(ListaCanciones playlists);
	public void borrarListaCanciones(ListaCanciones playlists);
	public void modificarListaCanciones(ListaCanciones playlists);
	public ListaCanciones recuperarListaCanciones(int codigo);
	public List<ListaCanciones> recuperarTodasLasListas();
}
