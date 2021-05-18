package persistencia;

public class FactoriaDAO {
	private static FactoriaDAO unicaInstancia;



	public static FactoriaDAO getInstancia() throws DAOException{
			if (unicaInstancia == null) unicaInstancia = new FactoriaDAO();
			return unicaInstancia;
		}

	/* Constructor */
	protected FactoriaDAO (){}
		
		
	// Metodos factoria que devuelven adaptadores que implementen estos interfaces
	public abstract IAdaptadorVentaDAO getVentaDAO();
	public abstract IAdaptadorLineaVentaDAO getLineaVentaDAO();
	public abstract IAdaptadorProductoDAO getProductoDAO();
	public abstract IAdaptadorClienteDAO getClienteDAO();
}
