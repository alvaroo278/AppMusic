package ventanas;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JScrollPane;
import java.awt.GridBagConstraints;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Insets;

public class Reciente extends JPanel {
	private JTable table;

	/**
	 * Create the panel.
	 */
	public Reciente() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{20, 0, 20, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 100, 0};
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
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"dsvdsv", "vdsv"},
				{"sdv", "sdvdsv"},
				{"sdvsd", "sdvs"},
				{"vdsv", "sdvds"},
			},
			new String[] {
				"Titulo", "Interprete"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(table);

	}

}
