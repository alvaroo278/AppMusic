package umu.tds.ventanas;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JScrollPane;


import umu.tds.manejador.AppMusic;

public class MisListas extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private int contAux = 0;
	private int sizeUrls = 0;
	private boolean vuelta = false;

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
		
		table = new JTable();
		
		
		scrollPane.setViewportView(table);

	}
	
	public void mostrarLista(String name) {
		table.setModel(AppMusic.getUnicaInstancia().getCancionesFromPlaylist(name));
		sizeUrls = AppMusic.getUnicaInstancia().getCancionesFromPlaylist(name).getRowCount();
	}
	
	public String getSelectedSong() {
		if(table.getSelectedRow() == -1) return "";
		return (String) table.getModel().getValueAt(table.getSelectedRow(), 0);
	}
	
	public String getNextSong() {

		if(table.getSelectedRow() == -1) return "";
		if(!vuelta){
			if(contAux + table.getSelectedRow() < table.getRowCount()-1) {
				contAux++;
				return (String) table.getModel().getValueAt(contAux+ table.getSelectedRow(), 0);
			}
			else {
				contAux = 0;
				vuelta = true;
				return (String) table.getModel().getValueAt(contAux, 0);
			}
	
		}
		if(contAux < table.getRowCount()-1) {
			contAux++;
		}else {
			contAux= 0;
		}
		return (String) table.getModel().getValueAt(contAux, 0);
	}
	
	public String getLastSong() {
		
		if(table.getSelectedRow() == -1) return "";
		if(!vuelta){
			if(contAux - table.getSelectedRow() > table.getRowCount()+1) {
				contAux--;
				return (String) table.getModel().getValueAt(contAux+ table.getSelectedRow(), 0);
			}
			else {
				contAux = table.getRowCount();
				vuelta = true;
				return (String) table.getModel().getValueAt(contAux, 0);
			}
	
		}
		if(contAux > table.getRowCount()+1) {
			contAux--;
		}else {
			contAux= table.getRowCount();
		}
		return (String) table.getModel().getValueAt(contAux, 0);
	}
	
	
	
	public String[] getTitles() {
		String[] titles = new String[sizeUrls];
		for(int i = 0; i<sizeUrls ; i++) {
			titles[i] = AppMusic.getUnicaInstancia().getCancion((String) table.getModel().getValueAt(i, 0)).getTitulo();
		}
		return titles;
	}
	
	
	public TableModel getModel() {
		return table.getModel();
	}
}
