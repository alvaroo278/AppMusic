package umu.tds.ventanas;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;


import javax.swing.JButton;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import umu.tds.manejador.AppMusic;


import java.awt.Color;
import java.awt.Component;


import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.ImageIcon;
import java.awt.event.ActionListener;

import java.util.HashSet;

import java.util.Set;

import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class NuevaLista extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField tituloText;
	private JTextField interpreteText;
	private JScrollPane scrollPane;
	private JTable todasCanciones;
	private JButton anadirButton;
	private JTextField playlistTittle;
	private JButton rigthArrowButton;
	private JButton leftArrowButton;
	private JButton buscarButton;
	private JTable misCanciones;
	private JScrollPane scrollPane_1;
	private JButton aceptarButton;
	private JButton cancelarButton;
	private JLabel playlistLabel;
	Set<String> titles = new HashSet<String>();
	private DefaultTableModel modeloNuevaLista;

	private String[][] nuevaLista;
	private JButton eliminarButton;
	private JComboBox<String> comboBox;

	/**
	 * Create the panel.
	 */
	public NuevaLista() {

		/**
		 * Create the panel.
		 */
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 107, 0, 0, 139, 10, 33, 33, 0, 0, 25, 0, 0, 20, 0 };
		gridBagLayout.rowHeights = new int[] { 25, 0, 20, 0, 20, 0, 0, 0, 0, 20, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 1.0, 1.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0, 0.0, 0.0,
				Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		playlistTittle = new JTextField();
		playlistTittle.setToolTipText("Introduzca el nombre de la playlist que quiere crear");
		GridBagConstraints gbc_playlistTittle = new GridBagConstraints();
		gbc_playlistTittle.gridwidth = 2;
		gbc_playlistTittle.insets = new Insets(0, 0, 5, 5);
		gbc_playlistTittle.fill = GridBagConstraints.HORIZONTAL;
		gbc_playlistTittle.gridx = 1;
		gbc_playlistTittle.gridy = 1;
		add(playlistTittle, gbc_playlistTittle);
		playlistTittle.setColumns(10);
		

		
		
		anadirButton = new JButton("Crear");
		anadirButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Comprueba mis listas
				// Pregunta al usuario si se quiere crear y se ponen visibles los demas
				// componentes
				if(checkfield()) {
					//UserContainsPlaylist
					if(AppMusic.getUnicaInstancia().userContainsPlaylist(playlistTittle.getText())) {
						int n = JOptionPane.showConfirmDialog(Principal.getFrame(), "¿Desea modificar la lista ya existente?", "Lista existente",JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE);
						if(n == 0) {
							//modificar
							setAllVisibleTrue();
							playlistTittle.setVisible(false);
							anadirButton.setVisible(false);
							eliminarButton.setVisible(true);
							titles = AppMusic.getUnicaInstancia().getSetCancionesNamesFromPlaylist(playlistTittle.getText());		
							misCanciones.setModel(AppMusic.getUnicaInstancia().getCancionesFromPlaylist(playlistTittle.getText()));
						}else {
							
						}
					}else {
					int n = JOptionPane.showConfirmDialog(Principal.getFrame(), "¿Deseas crear una nueva lista?", "Crear lista",JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE);
					if(n == 0) {
						setAllVisibleTrue();
						playlistTittle.setVisible(false);
						anadirButton.setVisible(false);
						
					}else {
						
					}
				}
			}
			}

		
		});
		GridBagConstraints gbc_anadirButton = new GridBagConstraints();
		gbc_anadirButton.anchor = GridBagConstraints.WEST;
		gbc_anadirButton.insets = new Insets(0, 0, 5, 5);
		gbc_anadirButton.gridx = 3;
		gbc_anadirButton.gridy = 1;
		add(anadirButton, gbc_anadirButton);
		
		eliminarButton = new JButton("Eliminar");
		eliminarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				titles.clear();
				modeloNuevaLista.setRowCount(0);	
				setAllVisibleFalse();
				playlistTittle.setVisible(true);
				anadirButton.setVisible(true);
				AppMusic.getUnicaInstancia().borrarPlaylist(playlistTittle.getText());
				JOptionPane.showMessageDialog(Principal.getFrame(), "Lista eliminada con éxito.\n",
						"Nueva Lista", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		GridBagConstraints gbc_eliminarButton = new GridBagConstraints();
		gbc_eliminarButton.insets = new Insets(0, 0, 5, 5);
		gbc_eliminarButton.gridx = 4;
		gbc_eliminarButton.gridy = 1;
		add(eliminarButton, gbc_eliminarButton);

		tituloText = new JTextField();
		tituloText.setText("Titulo");
		GridBagConstraints gbc_tituloText = new GridBagConstraints();
		gbc_tituloText.fill = GridBagConstraints.HORIZONTAL;
		gbc_tituloText.gridwidth = 2;
		gbc_tituloText.insets = new Insets(0, 0, 5, 5);
		gbc_tituloText.gridx = 0;
		gbc_tituloText.gridy = 3;
		add(tituloText, gbc_tituloText);
		tituloText.setColumns(10);

		interpreteText = new JTextField();
		interpreteText.setText("Interprete\r\n");
		GridBagConstraints gbc_interpreteText = new GridBagConstraints();
		gbc_interpreteText.insets = new Insets(0, 0, 5, 5);
		gbc_interpreteText.fill = GridBagConstraints.HORIZONTAL;
		gbc_interpreteText.gridx = 3;
		gbc_interpreteText.gridy = 3;
		add(interpreteText, gbc_interpreteText);
		interpreteText.setColumns(10);

		
		
		comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Genero", "URBANO", "POP", "JAZZ", "CLASICA", "FLAMENCO", "ROCK"}));
		comboBox.setSelectedIndex(0);
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 5;
		gbc_comboBox.gridy = 3;
		add(comboBox, gbc_comboBox);
		
		buscarButton = new JButton("Buscar");
		buscarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				todasCanciones.setModel(AppMusic.getUnicaInstancia().buscarCanciones(tituloText.getText(), interpreteText.getText(), (String) comboBox.getSelectedItem()));
			}
		});
		GridBagConstraints gbc_buscarButton = new GridBagConstraints();
		gbc_buscarButton.anchor = GridBagConstraints.WEST;
		gbc_buscarButton.insets = new Insets(0, 0, 5, 5);
		gbc_buscarButton.gridx = 7;
		gbc_buscarButton.gridy = 3;
		add(buscarButton, gbc_buscarButton);

		playlistLabel = new JLabel("Playlist");
		playlistLabel.setForeground(new Color(0, 191, 255));
		playlistLabel.setBackground(new Color(135, 206, 235));
		GridBagConstraints gbc_playlistLabel = new GridBagConstraints();
		gbc_playlistLabel.anchor = GridBagConstraints.WEST;
		gbc_playlistLabel.insets = new Insets(0, 0, 5, 5);
		gbc_playlistLabel.gridx = 5;
		gbc_playlistLabel.gridy = 5;
		add(playlistLabel, gbc_playlistLabel);

		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 2;
		gbc_scrollPane.gridwidth = 4;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 6;
		add(scrollPane, gbc_scrollPane);

		modeloNuevaLista = new DefaultTableModel(nuevaLista, new String[] { "Título", "Intérprete" }) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] { false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};


		todasCanciones = new JTable();
		todasCanciones.setIgnoreRepaint(true);
		todasCanciones.setVerifyInputWhenFocusTarget(false);
		todasCanciones.setUpdateSelectionOnSort(false);
		todasCanciones.setRowSelectionAllowed(false);
		todasCanciones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		mostrarTabla();

		scrollPane.setViewportView(todasCanciones);

		rigthArrowButton = new JButton("");
		rigthArrowButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				int idx = todasCanciones.getSelectedRow();
				String[] row = new String[2];
				if (idx != -1 && titles.add((String) todasCanciones.getModel().getValueAt(idx, 0))) {
					row[0] = (String) todasCanciones.getModel().getValueAt(idx, 0);
					row[1] = (String) todasCanciones.getModel().getValueAt(idx, 1);
					modeloNuevaLista.addRow(row);
				}
			}
		});
		rigthArrowButton.setIcon(new ImageIcon(NuevaLista.class.getResource("/umu/tds/imagenes/right-arrow.png")));
		GridBagConstraints gbc_rigthArrowButton = new GridBagConstraints();
		gbc_rigthArrowButton.anchor = GridBagConstraints.SOUTH;
		gbc_rigthArrowButton.insets = new Insets(0, 0, 5, 5);
		gbc_rigthArrowButton.gridx = 4;
		gbc_rigthArrowButton.gridy = 6;
		add(rigthArrowButton, gbc_rigthArrowButton);

		scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.gridheight = 2;
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridwidth = 7;
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_1.gridx = 5;
		gbc_scrollPane_1.gridy = 6;
		add(scrollPane_1, gbc_scrollPane_1);

		misCanciones = new JTable();

		misCanciones.setRowSelectionAllowed(false);
		misCanciones.setVerifyInputWhenFocusTarget(false);
		misCanciones.setUpdateSelectionOnSort(false);

		misCanciones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		misCanciones.setIgnoreRepaint(true);
		misCanciones.setModel(modeloNuevaLista);
		scrollPane_1.setViewportView(misCanciones);

		leftArrowButton = new JButton("");
		leftArrowButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int idx = misCanciones.getSelectedRow();
				if (idx != -1 && titles.remove(modeloNuevaLista.getValueAt(idx, 0))) {
					modeloNuevaLista.removeRow(idx);
				}
			}
		});
		leftArrowButton.setIcon(new ImageIcon(NuevaLista.class.getResource("/umu/tds/imagenes/left-arrow.png")));
		GridBagConstraints gbc_leftArrowButton = new GridBagConstraints();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		gbc_leftArrowButton.anchor = GridBagConstraints.NORTH;
		gbc_leftArrowButton.insets = new Insets(0, 0, 5, 5);
		gbc_leftArrowButton.gridx = 4;
		gbc_leftArrowButton.gridy = 7;
		add(leftArrowButton, gbc_leftArrowButton);

		aceptarButton = new JButton("Aceptar");
		aceptarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AppMusic.getUnicaInstancia().anadirPlaylist(titles, playlistTittle.getText());	
				titles.clear();
				modeloNuevaLista.setRowCount(0);	
				setAllVisibleFalse();
				playlistTittle.setVisible(true);
				anadirButton.setVisible(true);
				JOptionPane.showMessageDialog(Principal.getFrame(), "Lista creada con éxito.\n",
						"Nueva Lista", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		GridBagConstraints gbc_aceptarButton = new GridBagConstraints();
		gbc_aceptarButton.anchor = GridBagConstraints.EAST;
		gbc_aceptarButton.insets = new Insets(0, 0, 5, 5);
		gbc_aceptarButton.gridx = 3;
		gbc_aceptarButton.gridy = 8;
		add(aceptarButton, gbc_aceptarButton);

		cancelarButton = new JButton("Cancelar");
		cancelarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				titles.clear();
				comboBox.setSelectedIndex(0);
				interpreteText.setText("Interprete");
				tituloText.setText("Titulo");
				playlistTittle.setText("");
				setAllVisibleFalse();
				playlistTittle.setVisible(true);
				anadirButton.setVisible(true);
				modeloNuevaLista.setRowCount(0);
				titles.clear();				
			}
		});
		GridBagConstraints gbc_cancelarButton = new GridBagConstraints();
		gbc_cancelarButton.gridwidth = 2;
		gbc_cancelarButton.anchor = GridBagConstraints.WEST;
		gbc_cancelarButton.insets = new Insets(0, 0, 5, 5);
		gbc_cancelarButton.gridx = 5;
		gbc_cancelarButton.gridy = 8;
		add(cancelarButton, gbc_cancelarButton);
		setAllVisibleFalse();
	}

	private void setAllVisibleFalse() {
		for (Component c : this.getComponents()) {
			if (!c.equals(anadirButton) && !c.equals(playlistTittle)) {
				c.setVisible(false);
			}
		}
	}
	
	private void setAllVisibleTrue() {
		for (Component c : this.getComponents()) {
			if (!c.equals(eliminarButton)) {
				c.setVisible(true);
			}
		}
	}
	
	private boolean checkfield() {
		if(playlistTittle.getText().isEmpty()) {
			JOptionPane.showMessageDialog(Principal.getFrame(), "Escriba un nombre para la playlist", "WARNING", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}
	
	
	public String getSelectedSong() {
		return (String) todasCanciones.getModel().getValueAt(todasCanciones.getSelectedRow(),0);
	}
	
	
	private void mostrarTabla() {
		todasCanciones.setModel(AppMusic.getUnicaInstancia().getCancionesCargadas());
	}
	
	public String[] getTitles() {
		int n = AppMusic.getUnicaInstancia().getCancionesCargadasSize();
		String[] titles = new String[n];
		for(int i = 0; i<n ; i++) {
			titles[i] = AppMusic.getUnicaInstancia().getCancion((String) todasCanciones.getModel().getValueAt(i, 0)).getTitulo();
		}
		return titles;
	}
	
}
