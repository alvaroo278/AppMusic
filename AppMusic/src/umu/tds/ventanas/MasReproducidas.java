package umu.tds.ventanas;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JTable;
import java.awt.GridBagConstraints;
import javax.swing.JScrollPane;
import java.awt.Insets;



import umu.tds.manejador.AppMusic;

public class MasReproducidas extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public MasReproducidas() {
		
		
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
		
		table = new JTable();	
		scrollPane.setViewportView(table);
		
		mostrarLista();
	}
	


	public void mostrarLista() {
		table.setModel(AppMusic.getUnicaInstancia().getCancionesMasReproducidas());
	}

	public String getSelectedSong() {
		if(table.getSelectedRow() == -1) return "";
		return (String) table.getModel().getValueAt(table.getSelectedRow(), 0);
	}
	public String[] getTitles() {
		
		int n = table.getModel().getRowCount();
		String[] titles = new String[n];
		for(int i = 0; i<n ; i++) {
			titles[i] = AppMusic.getUnicaInstancia().getCancion((String) table.getModel().getValueAt(i, 0)).getTitulo();
		}
		return titles;
	}
	
}
