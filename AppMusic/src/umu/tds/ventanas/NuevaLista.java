package umu.tds.ventanas;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import umu.tds.manejador.AppMusic;

import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Component;

import javax.swing.border.TitledBorder;
import javax.swing.border.BevelBorder;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.zip.DeflaterInputStream;
import java.awt.event.ActionEvent;

public class NuevaLista extends JPanel {
	private JTextField txtTitulo;
	private JTextField txtInterprete;
	private JTextField txtGenero;
	private JScrollPane scrollPane;
	private JTable table;
	private Label columna;
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
	private DefaultTableModel modeloCancionesCargadas;

	private String[][] cancionesCargadas;
	private String[][] nuevaLista;
	private JButton eliminarButton;

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
		

		
		cancionesCargadas = AppMusic.getUnicaInstancia().getCancionesCargadas();
		anadirButton = new JButton("Crear");
		anadirButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Comprueba mis listas
				// Pregunta al usuario si se quiere crear y se ponen visibles los demas
				// componentes
				if(checkfield()) {
					if(AppMusic.getUnicaInstancia().getPlaylistByName().contains(playlistTittle.getText())) {
						int n = JOptionPane.showConfirmDialog(Principal.getFrame(), "�Desea modificar la lista ya existente?", "Lista existente",JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE);
						if(n == 0) {
							//modificar
							eliminarButton.setVisible(true);
						}else {
							
						}
					}else {
					int n = JOptionPane.showConfirmDialog(Principal.getFrame(), "�Deseas crear una nueva lista?", "Crear lista",JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE);
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
		GridBagConstraints gbc_eliminarButton = new GridBagConstraints();
		gbc_eliminarButton.insets = new Insets(0, 0, 5, 5);
		gbc_eliminarButton.gridx = 4;
		gbc_eliminarButton.gridy = 1;
		add(eliminarButton, gbc_eliminarButton);

		txtTitulo = new JTextField();
		txtTitulo.setText("Titulo");
		GridBagConstraints gbc_txtTitulo = new GridBagConstraints();
		gbc_txtTitulo.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTitulo.gridwidth = 2;
		gbc_txtTitulo.insets = new Insets(0, 0, 5, 5);
		gbc_txtTitulo.gridx = 0;
		gbc_txtTitulo.gridy = 3;
		add(txtTitulo, gbc_txtTitulo);
		txtTitulo.setColumns(10);

		txtInterprete = new JTextField();
		txtInterprete.setText("Interprete\r\n");
		GridBagConstraints gbc_txtInterprete = new GridBagConstraints();
		gbc_txtInterprete.insets = new Insets(0, 0, 5, 5);
		gbc_txtInterprete.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtInterprete.gridx = 3;
		gbc_txtInterprete.gridy = 3;
		add(txtInterprete, gbc_txtInterprete);
		txtInterprete.setColumns(10);

		txtGenero = new JTextField();
		txtGenero.setText("Genero");
		GridBagConstraints gbc_txtGenero = new GridBagConstraints();
		gbc_txtGenero.insets = new Insets(0, 0, 5, 5);
		gbc_txtGenero.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtGenero.gridx = 5;
		gbc_txtGenero.gridy = 3;
		add(txtGenero, gbc_txtGenero);
		txtGenero.setColumns(10);

		buscarButton = new JButton("Buscar");
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

		modeloNuevaLista = new DefaultTableModel(nuevaLista, new String[] { "T�tulo", "Int�rprete" }) {
			boolean[] columnEditables = new boolean[] { false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};

		modeloCancionesCargadas = new DefaultTableModel(cancionesCargadas, new String[] { "T�tulo", "Int�rprete" }) {
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
		todasCanciones.setModel(modeloCancionesCargadas);

		scrollPane.setViewportView(todasCanciones);

		rigthArrowButton = new JButton("");
		rigthArrowButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				int idx = todasCanciones.getSelectedRow();
				String[] row = new String[2];
				if (idx != -1 && titles.add(cancionesCargadas[idx][0])) {
					row[0] = (String) modeloCancionesCargadas.getValueAt(idx, 0);
					row[1] = (String) modeloCancionesCargadas.getValueAt(idx, 1);
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
				txtGenero.setText("Genero");
				txtInterprete.setText("Interprete");
				txtTitulo.setText("Titulo");
				playlistTittle.setText("");
				cancionesCargadas = null;
				setAllVisibleFalse();
				// poner todo visible false
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
}
