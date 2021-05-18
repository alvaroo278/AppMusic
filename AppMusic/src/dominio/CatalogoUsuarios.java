package dominio;

import java.io.IOError;
import java.io.IOException;
import java.util.List;

import javax.swing.ListSelectionModel;

import beans.Entidad;
import persistencia.DAOException;
import persistencia.FactoriaDAO;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public class CatalogoUsuarios {
	
	private static CatalogoUsuarios unicaInstancia = new CatalogoUsuarios();
	private Map<String,Usuario> usuarios;
	private FactoriaDAO dao;
	private IAdaptadorClienteDAO adaptadorCliente;

	private CatalogoUsuarios() {
		try {
			dao = FactoriaDAO.getInstancia();
		}catch (DAOException e) {
			e.printStackTrace();
		}
		

	}
	
}
