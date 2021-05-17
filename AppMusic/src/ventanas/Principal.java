package ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.Container;

import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
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
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.ImageIcon;

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
		
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{15, 97, 15, 0};
		gbl_panel_1.rowHeights = new int[]{30, 25, 15, 0, 15, 0, 15, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.CENTER);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		

		
		JButton btnNewButton_2 = new JButton("Explorar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				expWindow = (JPanel) new Explorar();
				if(oldPanel != null) contentPane.remove(oldPanel);
				oldPanel = expWindow;
				contentPane.add(expWindow,BorderLayout.CENTER);
				contentPane.revalidate();
				contentPane.repaint();
				frmPrincipal.setBounds(100, 100, 864, 571);
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
				frmPrincipal.setBounds(100, 100, 1050, 650);
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
		gbc_btnNewButton_5.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_5.gridx = 1;
		gbc_btnNewButton_5.gridy = 7;
		panel_1.add(btnNewButton_5, gbc_btnNewButton_5);
		
		JPanel panel_3 = new JPanel();
		frmPrincipal.getContentPane().add(panel_3, BorderLayout.SOUTH);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{350, 0, 10, 0, 10, 0, 0};
		gbl_panel_3.rowHeights = new int[]{5, 0, 10, 0, 10, 0};
		gbl_panel_3.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);
		
		JButton btnNewButton_6 = new JButton("");
		btnNewButton_6.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/play.png")));
		GridBagConstraints gbc_btnNewButton_6 = new GridBagConstraints();
		gbc_btnNewButton_6.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_6.gridx = 3;
		gbc_btnNewButton_6.gridy = 1;
		panel_3.add(btnNewButton_6, gbc_btnNewButton_6);
		

		gbl_panel_3.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);
		
		JButton btnNewButton_7 = new JButton("");
		btnNewButton_7.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/pause.png")));
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnNewButton_9 = new JButton("");
		btnNewButton_9.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/left-chevron (1).png")));
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_btnNewButton_9 = new GridBagConstraints();
		gbc_btnNewButton_9.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_9.gridx = 1;
		gbc_btnNewButton_9.gridy = 3;
		panel_3.add(btnNewButton_9, gbc_btnNewButton_9);
		GridBagConstraints gbc_btnNewButton_7 = new GridBagConstraints();
		gbc_btnNewButton_7.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_7.gridx = 3;
		gbc_btnNewButton_7.gridy = 3;
		panel_3.add(btnNewButton_7, gbc_btnNewButton_7);
		
		JButton btnNewButton_8 = new JButton("");
		btnNewButton_8.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/right-chevron (1).png")));
		GridBagConstraints gbc_btnNewButton_8 = new GridBagConstraints();
		gbc_btnNewButton_8.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_8.gridx = 5;
		gbc_btnNewButton_8.gridy = 3;
		panel_3.add(btnNewButton_8, gbc_btnNewButton_8);
		
		
	}
	
	public JFrame getFrame() {
		return frmPrincipal;
	}
	
}
