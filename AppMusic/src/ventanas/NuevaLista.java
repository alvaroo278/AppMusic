package ventanas;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.border.BevelBorder;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.JLabel;

public class NuevaLista extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JScrollPane scrollPane;
	private JTable table;
	private Label columna;
	private JTable table_1;
	private JButton btnNewButton_2;
	private JTextField playlistTittle;
	private JButton btnNewButton_3;
	private JButton btnNewButton_4;
	private JButton btnNewButton;
	private JTable table_2;
	private JScrollPane scrollPane_1;
	private JButton btnNewButton_1;
	private JButton btnNewButton_5;
	private JLabel playlistLabel;
	/**
	 * Create the panel.
	 */
	public NuevaLista() {
		

		/**
		 * Create the panel.
		 */
			GridBagLayout gridBagLayout = new GridBagLayout();
			gridBagLayout.columnWidths = new int[]{107, 0, 0, 139, 10, 33, 33, 0, 0, 25, 0, 0, 20, 0};
			gridBagLayout.rowHeights = new int[]{25, 0, 20, 0, 20, 0, 0, 0, 0, 20, 0};
			gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
			gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
			setLayout(gridBagLayout);
			
			playlistTittle = new JTextField();
			GridBagConstraints gbc_playlistTittle = new GridBagConstraints();
			gbc_playlistTittle.gridwidth = 2;
			gbc_playlistTittle.insets = new Insets(0, 0, 5, 5);
			gbc_playlistTittle.fill = GridBagConstraints.HORIZONTAL;
			gbc_playlistTittle.gridx = 1;
			gbc_playlistTittle.gridy = 1;
			add(playlistTittle, gbc_playlistTittle);
			playlistTittle.setColumns(10);
			
			btnNewButton_2 = new JButton("New button");
			GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
			gbc_btnNewButton_2.anchor = GridBagConstraints.WEST;
			gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 5);
			gbc_btnNewButton_2.gridx = 3;
			gbc_btnNewButton_2.gridy = 1;
			add(btnNewButton_2, gbc_btnNewButton_2);
			
			textField = new JTextField();
			GridBagConstraints gbc_textField = new GridBagConstraints();
			gbc_textField.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField.gridwidth = 2;
			gbc_textField.insets = new Insets(0, 0, 5, 5);
			gbc_textField.gridx = 0;
			gbc_textField.gridy = 3;
			add(textField, gbc_textField);
			textField.setColumns(10);
			
			textField_1 = new JTextField();
			GridBagConstraints gbc_textField_1 = new GridBagConstraints();
			gbc_textField_1.insets = new Insets(0, 0, 5, 5);
			gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_1.gridx = 3;
			gbc_textField_1.gridy = 3;
			add(textField_1, gbc_textField_1);
			textField_1.setColumns(10);
			
			textField_2 = new JTextField();
			GridBagConstraints gbc_textField_2 = new GridBagConstraints();
			gbc_textField_2.insets = new Insets(0, 0, 5, 5);
			gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_2.gridx = 5;
			gbc_textField_2.gridy = 3;
			add(textField_2, gbc_textField_2);
			textField_2.setColumns(10);
			
			btnNewButton = new JButton("New button");
			GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
			gbc_btnNewButton.anchor = GridBagConstraints.WEST;
			gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
			gbc_btnNewButton.gridx = 7;
			gbc_btnNewButton.gridy = 3;
			add(btnNewButton, gbc_btnNewButton);
			
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
			
			
			
			table_1 = new JTable();
			table_1.setVerifyInputWhenFocusTarget(false);
			table_1.setUpdateSelectionOnSort(false);
			table_1.setRowSelectionAllowed(false);
			table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table_1.setModel(new DefaultTableModel(
				new Object[][] {
					{"Regg", "dsfsf"},
					{"dsfsf", "dsfsf"},
					{"dsfdsf", "sdasdasdadasfasdasdasdasd"},
					{"sdfsf", "sdfdsf"},
				},
				new String[] {
					"Título", "Intérprete"
				}
			));
			scrollPane.setViewportView(table_1);
			
			btnNewButton_3 = new JButton("New button");
			GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
			gbc_btnNewButton_3.anchor = GridBagConstraints.SOUTH;
			gbc_btnNewButton_3.insets = new Insets(0, 0, 5, 5);
			gbc_btnNewButton_3.gridx = 4;
			gbc_btnNewButton_3.gridy = 6;
			add(btnNewButton_3, gbc_btnNewButton_3);
			
			scrollPane_1 = new JScrollPane();
			GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
			gbc_scrollPane_1.gridheight = 2;
			gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
			gbc_scrollPane_1.gridwidth = 7;
			gbc_scrollPane_1.insets = new Insets(0, 0, 5, 5);
			gbc_scrollPane_1.gridx = 5;
			gbc_scrollPane_1.gridy = 6;
			add(scrollPane_1, gbc_scrollPane_1);
			
			table_2 = new JTable();
			table_2.setModel(new DefaultTableModel(
				new Object[][] {
					{"Reggasdadsasdasdsasdasd", "dsfsf"},
					{"dsfsf", "dsfsf"},
					{"dsfdsf", "sdf"},
					{"sdfsf", "sdfdsf"},
					{"sadfsdf","asdasd"},
					{"asdasdasd","asdasdasd"},
					{"sadfsdf","asdasd"},
					{"asdasdasd","asdasdasd"}
				},
				new String[] {
					"New column", "New column"
				}
			));
			scrollPane_1.setViewportView(table_2);
			
			btnNewButton_4 = new JButton("New button");
			GridBagConstraints gbc_btnNewButton_4 = new GridBagConstraints();
			scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			gbc_btnNewButton_4.anchor = GridBagConstraints.NORTH;
			gbc_btnNewButton_4.insets = new Insets(0, 0, 5, 5);
			gbc_btnNewButton_4.gridx = 4;
			gbc_btnNewButton_4.gridy = 7;
			add(btnNewButton_4, gbc_btnNewButton_4);
			
			btnNewButton_1 = new JButton("New button");
			GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
			gbc_btnNewButton_1.anchor = GridBagConstraints.EAST;
			gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
			gbc_btnNewButton_1.gridx = 3;
			gbc_btnNewButton_1.gridy = 8;
			add(btnNewButton_1, gbc_btnNewButton_1);
			
			btnNewButton_5 = new JButton("New button");
			GridBagConstraints gbc_btnNewButton_5 = new GridBagConstraints();
			gbc_btnNewButton_5.gridwidth = 2;
			gbc_btnNewButton_5.anchor = GridBagConstraints.WEST;
			gbc_btnNewButton_5.insets = new Insets(0, 0, 5, 5);
			gbc_btnNewButton_5.gridx = 5;
			gbc_btnNewButton_5.gridy = 8;
			add(btnNewButton_5, gbc_btnNewButton_5);
			
	}

}
