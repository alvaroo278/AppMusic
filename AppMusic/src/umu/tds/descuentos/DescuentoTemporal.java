package umu.tds.descuentos;

import java.time.LocalDate;

import umu.tds.dominio.Usuario;

public class DescuentoTemporal implements Descuento {
	LocalDate fechaLimite;
	@Override
	public void aplicarDescuento(Usuario usu) {
		usu.setDescuento("Temporal");
		fechaLimite = LocalDate.now().plusMonths(2);
		usu.setFechaLimiteDescuento(fechaLimite);
		}
	
}
