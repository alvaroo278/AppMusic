package persistencia;

public abstract class FactoriaDAO {
	private static FactoriaDAO unicaInstancia;

	public static final String DAO_TDS = "persistencia.TDSFactoriaDAO";
<<<<<<< HEAD
=======


		public static FactoriaDAO getInstancia(String tipo) throws DAOException{
			if (unicaInstancia == null)
				try { unicaInstancia=(FactoriaDAO) Class.forName(tipo).newInstance();
				} catch (Exception e) {	
					throw new DAOException(e.getMessage());
				} 
			return unicaInstancia;
		}

>>>>>>> refs/remotes/origin/main


	
<<<<<<< HEAD

	public static FactoriaDAO getInstancia(String tipo) throws DAOException{
		if (unicaInstancia == null)
			try { unicaInstancia=(FactoriaDAO) Class.forName(tipo).newInstance();
			} catch (Exception e) {	
				throw new DAOException(e.getMessage());
			} 
		return unicaInstancia;
	}


	public static FactoriaDAO getInstancia() throws DAOException{
			if (unicaInstancia == null) return getInstancia (FactoriaDAO.DAO_TDS);
					else return unicaInstancia;
		}



=======
	/** 
	 * Crea un tipo de factoria DAO.
	 * Solo existe el tipo TDSFactoriaDAO
	 */
	

>>>>>>> refs/remotes/origin/main
	/* Constructor */
	protected FactoriaDAO (){}
		
		
	// Metodos factoria que devuelven adaptadores que implementen estos interfaces
	public abstract IAdaptadorCancionDAO getCancionDAO();
	public abstract IAdaptadorListaCancionesDAO getListaCancionesDAO();
	public abstract IAdaptadorUsuarioDAO getUsuarioDAO();

<<<<<<< HEAD

=======
>>>>>>> refs/remotes/origin/main
}
