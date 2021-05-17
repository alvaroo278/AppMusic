package ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.Container;

import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.junit.experimental.theories.Theories;

import manejador.*;

import com.seaglasslookandfeel.SeaGlassLookAndFeel;
import com.seaglasslookandfeel.painter.ContentPanePainter;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import java.awt.List;

public class Principal {

	private JFrame frmPrincipal;
	private JPanel expWindow;

	private JPanel nuevaListaWindow;

	private JPanel rcWindow;

	private JPanel oldPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					window.frmPrincipal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Principal() {
		initialize();
	}
	
	/**
	 * Initialize the contents of the frmPrincipal.
	 */
	private void initialize() {
		try {
			UIManager.setLookAndFeel(new SeaGlassLookAndFeel() );
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		oldPanel = new JPanel();
		
		frmPrincipal = new JFrame();
		frmPrincipal.setBounds(100, 100, 864, 571);
		frmPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container contentPane = frmPrincipal.getContentPane();
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 10));
		
		JLabel lblNewLabel = new JLabel("Hola " + AppMusic.getInstancia().getUsuario());
		panel.add(lblNewLabel);
		
		JButton btnNewButton_1 = new JButton("Mejora tu cuenta");
		panel.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("Logout");
		panel.add(btnNewButton);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.WEST);
		
		
	//	panel_1.setPreferredSize(new Dimension(600,300));
		
		
		frmPrincipal.setLocationRelativeTo(null);
		
	
		
		
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] {20, 10, 30};
		gbl_panel_1.rowHeights = new int[]{15, 25, 20, 0, 20, 0, 20, 0, 0, 20, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 1.0, 0.0};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.CENTER);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		

		
		JPanel panel_3 = new JPanel();
		frmPrincipal.getContentPane().add(panel_3, BorderLayout.SOUTH);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{350, 0, 10, 0, 10, 0, 0};
		gbl_panel_3.rowHeights = new int[]{5, 0, 10, 0, 10, 0};
		gbl_panel_3.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);
		
		
		JButton btnNewButton_2 = new JButton("Explorar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				expWindow = (JPanel) new Explorar();
				if(oldPanel != null) contentPane.remove(oldPanel);
				oldPanel = expWindow;
				contentPane.add(expWindow,BorderLayout.CENTER);
				contentPane.revalidate();
				contentPane.repaint();
				validate();
				panel_3.setVisible(true);
			}
		});
		
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_2.anchor = GridBagConstraints.NORTH;
		gbc_btnNewButton_2.gridx = 1;
		gbc_btnNewButton_2.gridy = 1;
		panel_1.add(btnNewButton_2, gbc_btnNewButton_2);
		

		JButton btnNewButton_3 = new JButton("Nueva lista");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nuevaListaWindow = (JPanel) new NuevaLista();
				if(oldPanel != null) 	contentPane.remove(oldPanel);
				oldPanel = nuevaListaWindow;
				contentPane.add(nuevaListaWindow,BorderLayout.CENTER);
				contentPane.revalidate();
				contentPane.repaint();
				validate();
				panel_3.setVisible(false);
			}
		});
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_3.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_3.gridx = 1;
		gbc_btnNewButton_3.gridy = 3;
		panel_1.add(btnNewButton_3, gbc_btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Reciente");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rcWindow = (JPanel) new Reciente();
				if(oldPanel != null) contentPane.remove(oldPanel);
				oldPanel = rcWindow;
				contentPane.add(rcWindow,BorderLayout.CENTER);
				contentPane.revalidate();
				contentPane.repaint();
				validate();
				panel_3.setVisible(true);
			}
		});
		GridBagConstraints gbc_btnNewButton_4 = new GridBagConstraints();
		gbc_btnNewButton_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_4.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_4.gridx = 1;
		gbc_btnNewButton_4.gridy = 5;
		panel_1.add(btnNewButton_4, gbc_btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Mis listas");
		
		
		
		GridBagConstraints gbc_btnNewButton_5 = new GridBagConstraints();
		gbc_btnNewButton_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_5.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_5.gridx = 1;
		gbc_btnNewButton_5.gridy = 7;
		panel_1.add(btnNewButton_5, gbc_btnNewButton_5);
		
		JScrollPane scrollPane = new JScrollPane();
		//scrollPane.setMaximumSize(new Dimension(20, 32767));
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 2;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 8;
		panel_1.add(scrollPane, gbc_scrollPane);
		
		

		
		JList<String> listaPlaylist = new JList<String>();
		listaPlaylist.setFixedCellWidth(100);

		
		listaPlaylist.setVisibleRowCount(5);
		listaPlaylist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaPlaylist.setModel(new AbstractListModel<String>() {
			String[] values = new String[] {"sfwedf", "asdasdaSDASDasdasdasdasdsASDasAS", "asda", "sdasdasdasd", "asdasd", "asdaaaaaa", "asda111111111", "asd", "asd", "a", "sda", "sd", "asd", "a", "sd", "asd", "a", "sd", "a", "sdasdaaaaaaaaa"};
			public int getSize() {
				return values.length;
			}
			public String getElementAt(int index) {
				return values[index];
			}
		});
		scrollPane.setViewportView(listaPlaylist);
	
		scrollPane.setVisible(false);
		
		
	
		
		
		JButton playButton = new JButton("");
		playButton.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/play.png")));
		GridBagConstraints gbc_playButton = new GridBagConstraints();
		gbc_playButton.insets = new Insets(0, 0, 5, 5);
		gbc_playButton.gridx = 3;
		gbc_playButton.gridy = 1;
		panel_3.add(playButton, gbc_playButton);
		

		gbl_panel_3.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);
		
		JButton pauseButton = new JButton("");
		pauseButton.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/pause.png")));
		pauseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton lastButton = new JButton("");
		lastButton.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/left-chevron (1).png")));
		
		lastButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		
		GridBagConstraints gbc_lastButton = new GridBagConstraints();
		gbc_lastButton.insets = new Insets(0, 0, 5, 5);
		gbc_lastButton.gridx = 1;
		gbc_lastButton.gridy = 3;
		panel_3.add(lastButton, gbc_lastButton);
		GridBagConstraints gbc_pauseButton = new GridBagConstraints();
		gbc_pauseButton.insets = new Insets(0, 0, 5, 5);
		gbc_pauseButton.gridx = 3;
		gbc_pauseButton.gridy = 3;
		panel_3.add(pauseButton, gbc_pauseButton);
		
		JButton nextButton = new JButton("");
		nextButton.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/right-chevron (1).png")));
		GridBagConstraints gbc_nextButton = new GridBagConstraints();
		gbc_nextButton.insets = new Insets(0, 0, 5, 0);
		gbc_nextButton.gridx = 5;
		gbc_nextButton.gridy = 3;
		panel_3.add(nextButton, gbc_nextButton);
		
		
		
	}
	
	public void validate() {
		if(oldPanel instanceof NuevaLista) {
			frmPrincipal.setBounds(100, 100, 1050, 650);
		}else {
			frmPrincipal.setBounds(100, 100, 864, 571);
		}
		frmPrincipal.setLocationRelativeTo(null);
	}
	
	public JFrame getFrmPrincipal() {
		return frmPrincipal;
	}

	public void setFrmPrincipal(JFrame frmPrincipal) {
		this.frmPrincipal = frmPrincipal;
	}

	public JPanel getExpWindow() {
		return expWindow;
	}

	public void setExpWindow(JPanel expWindow) {
		this.expWindow = expWindow;
	}

	public JPanel getNuevaListaWindow() {
		return nuevaListaWindow;
	}

	public void setNuevaListaWindow(JPanel nuevaListaWindow) {
		this.nuevaListaWindow = nuevaListaWindow;
	}

	public JPanel getRcWindow() {
		return rcWindow;
	}

	public void setRcWindow(JPanel rcWindow) {
		this.rcWindow = rcWindow;
	}

	public JPanel getOldPanel() {
		return oldPanel;
	}

	public void setOldPanel(JPanel oldPanel) {
		this.oldPanel = oldPanel;
	}


	
	public JFrame getFrame() {
		return frmPrincipal;
	}
	
}
