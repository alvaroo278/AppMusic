package catalogos;

import java.util.List;

import javax.swing.ListSelectionModel;

import dominio.Usuario;
import servidor.FactoriaServicioPersistenciaRMIImpl;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public class CatalogoUsuarios {
	
	private List<Usuario> users;
	
	FactoriaServicioPersistencia dao = FactoriaServicioPersistencia.getInstance();
	
}
