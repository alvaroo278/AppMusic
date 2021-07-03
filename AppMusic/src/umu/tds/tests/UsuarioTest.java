package umu.tds.tests;

import umu.tds.dominio.Cancion;
import umu.tds.dominio.EstiloMusical;
import umu.tds.dominio.Interprete;
import umu.tds.dominio.ListaCanciones;
import umu.tds.dominio.Usuario;
import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

public class UsuarioTest {
	
	ListaCanciones lc2;
	ListaCanciones lc1;
	Cancion c1;
	Cancion c2;
	Usuario u1;
	@Before
	public void setUp() {
		lc1 = new ListaCanciones("lista");
		c1 = new Cancion("hola", "C:jesuc/Desktop/hola.mp3", new EstiloMusical("test"), new Interprete("Jesus"), 0);
		c2 = new Cancion("adios", "C:jesuc/Desktop/hola.mp3", new EstiloMusical("test"), new Interprete("Pepe"), 0);
		lc1.addCancion(c1);
		lc1.addCancion(c2);
		lc2 = new ListaCanciones("lista2");
		
		u1 = new Usuario("Jesus", "Molina Piernas", "jesus@gmail.com", "molina", "pepepepe", LocalDate.of(2000, 12, 17));
		u1.addLista(lc1);
		u1.addLista(lc2);
		
	}
	
	@Test
	public void pruebaUsuario() {
		Usuario u2 = new Usuario("Pepe", "Molina Piernas", "jesus@gmail.com", "molina", "pepepepe", LocalDate.of(2000, 12, 17));
		u2.setNombre("Jesus");
		assertEquals(u1.getNombre(),u2.getNombre());
	}
	
	
	@Test
	public void pruebaMismaLista() {
		assertEquals(lc1, u1.getPlayList("lista"));
	}
	
	
	@Test
	public void pruebaDescuentoAlIncicio() {
		assertNull(u1.getDescuento());
	}
	
	@Test
	public void pruebaCanciones() {
		lc2.addCancion(c1);
		lc2.addCancion(c2);
		Object[] canciones1 = new Object[10];
		Object[] canciones2 = new Object[10];
		int i = 0;
		for (Cancion c : lc1.getCanciones()) {
			canciones1[i] = c;
			i++;
		}
		i = 0;
		for (Cancion c : lc2.getCanciones()) {
			canciones2[i] = c;
			i++;
		}
		
		assertArrayEquals(canciones1, canciones2);
	}
	
}
