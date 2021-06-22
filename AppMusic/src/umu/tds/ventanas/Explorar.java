package umu.tds.ventanas;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.GridBagLayout;

import javax.swing.JTextField;

import umu.tds.manejador.AppMusic;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JButton;

import javax.swing.ScrollPaneConstants;

import javax.swing.JTable;

import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Cursor;

public class Explorar extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField tituloText;
	private JTextField interpreteText;
	private JTextField generoText;
	private JScrollPane scrollPane;
	private JTable table_1;


	/**
	 * Create the panel.
	 */
	public Explorar() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 20, 27, 107, 139, 10, 33, 20, 20, 0 };
		gridBagLayout.rowHeights = new int[] { 25, 0, 20, 0, 20, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 1.0, 1.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

	
		tituloText = new JTextField();
		tituloText.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		tituloText.setText("Titulo");
		tituloText.setToolTipText("Escriba el titulo de la cancion que desee escuchar\r\n");
		GridBagConstraints gbc_tituloText = new GridBagConstraints();
		gbc_tituloText.gridwidth = 2;
		gbc_tituloText.fill = GridBagConstraints.HORIZONTAL;
		gbc_tituloText.insets = new Insets(0, 0, 5, 5);
		gbc_tituloText.gridx = 1;
		gbc_tituloText.gridy = 1;
		add(tituloText, gbc_tituloText);
		tituloText.setColumns(10);

		interpreteText = new JTextField();
		interpreteText.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		interpreteText.setText("Interprete");
		interpreteText.setToolTipText("Escriba el nombre del interprete que desee escuchar");
		GridBagConstraints gbc_interpreteText = new GridBagConstraints();
		gbc_interpreteText.insets = new Insets(0, 0, 5, 5);
		gbc_interpreteText.fill = GridBagConstraints.HORIZONTAL;
		gbc_interpreteText.gridx = 3;
		gbc_interpreteText.gridy = 1;
		add(interpreteText, gbc_interpreteText);
		interpreteText.setColumns(10);

		generoText = new JTextField();
		generoText.setText("Genero");
		generoText.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		generoText.setToolTipText("Escriba el genero que desee escuchar");
		GridBagConstraints gbc_generoText = new GridBagConstraints();
		gbc_generoText.insets = new Insets(0, 0, 5, 5);
		gbc_generoText.fill = GridBagConstraints.HORIZONTAL;
		gbc_generoText.gridx = 5;
		gbc_generoText.gridy = 1;
		add(generoText, gbc_generoText);
		generoText.setColumns(10);

		JButton btnNewButton = new JButton("Buscar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table_1.setModel(AppMusic.getUnicaInstancia().buscarCanciones(tituloText.getText(), interpreteText.getText(), generoText.getText()));
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 2;
		gbc_btnNewButton.gridy = 3;
		add(btnNewButton, gbc_btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table_1.setModel(AppMusic.getUnicaInstancia().getCancionesCargadas());
				tituloText.setText("Titulo");
				interpreteText.setText("Interprete");
				generoText.setText("Genero");
			}
		});
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.anchor = GridBagConstraints.WEST;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 3;
		gbc_btnNewButton_1.gridy = 3;
		add(btnNewButton_1, gbc_btnNewButton_1);

		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 2;
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 2;
		gbc_scrollPane.gridy = 5;
		add(scrollPane, gbc_scrollPane);


		
		table_1 = new JTable();

		table_1.setVerifyInputWhenFocusTarget(false);
		table_1.setUpdateSelectionOnSort(false);
		table_1.setRowSelectionAllowed(false);

		table_1.setIgnoreRepaint(false);
		table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
		mostrarTabla();
		scrollPane.setViewportView(table_1);

	}
	
	public String getSelectedSong() {
		if(table_1.getSelectedRow() == -1) return "";
		return (String) table_1.getModel().getValueAt(table_1.getSelectedRow(), 0);

	}
	
	private void mostrarTabla() {
		table_1.setModel(AppMusic.getUnicaInstancia().getCancionesCargadas());
	}
	
	public String[] getTitles() {
		int n = AppMusic.getUnicaInstancia().getCancionesCargadasSize();
		String[] titles = new String[n];
		for(int i = 0; i<n ; i++) {
			titles[i] = AppMusic.getUnicaInstancia().getCancion((String) table_1.getModel().getValueAt(i, 0)).getTitulo();
		}
		return titles;
	}

}
