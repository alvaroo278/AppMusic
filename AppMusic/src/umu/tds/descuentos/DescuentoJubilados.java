package umu.tds.descuentos;

import umu.tds.dominio.Usuario;

public class DescuentoJubilados implements Descuento{

	@Override
	public void aplicarDescuento(Usuario usu) {
		usu.setDescuento("Jubilados");
		
	}
	
}
