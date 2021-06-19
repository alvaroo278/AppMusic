package umu.tds.ventanas;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JTable;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import umu.tds.manejador.AppMusic;

public class MisListas extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel listaActual;
	private String[][] canciones;
	/**
	 * Create the panel.
	 */
	public MisListas() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{20, 0, 20, 0};
		gridBagLayout.rowHeights = new int[]{30, 0, 100, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 1;
		add(scrollPane, gbc_scrollPane);
		listaActual = new DefaultTableModel(canciones,
				new String[] {
					"T�tulo", "Int�rprete"
				}
			) {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;
				boolean[] columnEditables = new boolean[] {
					false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		table = new JTable();
		table.setModel(listaActual);
		
		scrollPane.setViewportView(table);

	}
	
	public void mostrarLista(String name) {
		canciones = AppMusic.getUnicaInstancia().getCancionesFromPlaylist(name);
		listaActual.setDataVector(canciones, new String[] {
				"T�tulo", "Int�rprete"
			});
		table.setModel(listaActual);
		
	}
}
