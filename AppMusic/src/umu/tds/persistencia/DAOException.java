package umu.tds.persistencia;

@SuppressWarnings("serial")
public class DAOException extends Exception {
	public DAOException(String warning) {
		super(warning);
	}
}
