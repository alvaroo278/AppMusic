package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window.Type;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.intellijthemes.FlatAllIJThemes;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatArcDarkContrastIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatAtomOneDarkIJTheme;
import com.seaglasslookandfeel.SeaGlassLookAndFeel;
import com.seaglasslookandfeel.ui.SeaGlassButtonUI;

import manejador.AppMusic;

import javax.print.attribute.standard.PrinterMakeAndModel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import java.awt.Dimension;

public class Login {

	private JFrame frmLogin;
	private JTextField textUser;
	private JPasswordField textPassword;
	private JLabel lblPasswordError;
	private JLabel lblUsuarioError;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		try {
			UIManager.setLookAndFeel(new SeaGlassLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		frmLogin = new JFrame();
		frmLogin.setType(Type.UTILITY);
		frmLogin.setTitle("LOGIN");
		frmLogin.getContentPane().setBackground(new Color(248, 248, 255));
		frmLogin.setBackground(Color.WHITE);
		frmLogin.setBounds(100, 100, 469, 335);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		lblPasswordError = new JLabel("La contraseña es obligatoria", SwingConstants.CENTER);
		fixedSize(lblPasswordError, 224, 15);
		lblPasswordError.setForeground(Color.RED);
		
		lblUsuarioError = new JLabel("El usuario es obligatorio", SwingConstants.CENTER);
		fixedSize(lblUsuarioError, 224, 15);
		lblUsuarioError.setForeground(Color.RED);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(248, 248, 255));
		frmLogin.getContentPane().add(panel, BorderLayout.NORTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 48, 0, 112, 47, 22, 40, 0, 0 };
		gbl_panel.rowHeights = new int[] { 126, 30, 30, 0, 20, 34, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);
		
		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		flowLayout.setHgap(10);
		panel_2.setBackground(new Color(248, 248, 255));
		panel_2.setForeground(new Color(248, 248, 255));
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.gridwidth = 3;
		gbc_panel_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_2.gridx = 3;
		gbc_panel_2.gridy = 0;
		panel.add(panel_2, gbc_panel_2);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/imagenes/ondas-sonoras (1).png")));
		panel_2.add(lblNewLabel);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setForeground(new Color(248, 248, 255));
		lblNewLabel_3.setBackground(new Color(248, 248, 255));
		lblNewLabel_3.setIcon(new ImageIcon(Login.class.getResource("/imagenes/boton-de-play.png")));
		panel_2.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(Login.class.getResource("/imagenes/ondas-sonoras (1).png")));
		panel_2.add(lblNewLabel_4);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Login.class.getResource("/imagenes/usuario (1).png")));
		lblNewLabel_1.setForeground(new Color(255, 248, 220));
		lblNewLabel_1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 3;
		gbc_lblNewLabel_1.gridy = 1;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);

		textUser = new JTextField();
		textUser.setName("");
		textUser.setColumns(15);
		
		GridBagConstraints gbc_textUser = new GridBagConstraints();
		gbc_textUser.gridwidth = 2;
		gbc_textUser.fill = GridBagConstraints.HORIZONTAL;
		gbc_textUser.insets = new Insets(0, 0, 5, 5);
		gbc_textUser.gridx = 4;
		gbc_textUser.gridy = 1;
		panel.add(textUser, gbc_textUser);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Login.class.getResource("/imagenes/llave.png")));
		lblNewLabel_2.setForeground(new Color(255, 248, 220));
		lblNewLabel_2.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 17));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.gridx = 3;
		gbc_lblNewLabel_2.gridy = 3;
		panel.add(lblNewLabel_2, gbc_lblNewLabel_2);

		textPassword = new JPasswordField();
		textPassword.setColumns(15);
		GridBagConstraints gbc_textPassword = new GridBagConstraints();
		gbc_textPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_textPassword.insets = new Insets(0, 0, 5, 5);
		gbc_textPassword.gridx = 4;
		gbc_textPassword.gridy = 3;
		panel.add(textPassword, gbc_textPassword);

		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkFields()) {	
					if(AppMusic.getUnicaInstancia().login(textUser.getText(), textPassword.getPassword().toString())) {
						frmLogin.setVisible(false);
						Principal home = new Principal();
						home.getFrame().setVisible(true);
					}else {
						JOptionPane.showMessageDialog(frmLogin, "El usuario o la contraseña no es correcto.\n",
							"Login", JOptionPane.ERROR_MESSAGE);
						frmLogin.setTitle("Login Gestor Eventos");
					}
				}
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.WEST;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 5;
		gbc_btnNewButton.gridy = 3;
		panel.add(btnNewButton, gbc_btnNewButton);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(248, 248, 255));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridwidth = 4;
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 3;
		gbc_panel_1.gridy = 4;
		panel.add(panel_1, gbc_panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblNewLabel_5 = new JLabel("\u00BFNo tienes cuenta?");
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		panel_1.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Reg\u00EDstrate.");
		lblNewLabel_6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel_6.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent m) {
				frmLogin.setVisible(false);
				Register registro = new Register();
				registro.getFrame().setVisible(true);
			}
		});
		lblNewLabel_6.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		panel_1.add(lblNewLabel_6);
		frmLogin.setLocationRelativeTo(null);
	}

	public JFrame getFrame() {
		return frmLogin;
	}
	private boolean checkFields() {
		boolean salida = true;
		if (textUser.getText().trim().isEmpty()) {
			textUser.setForeground(Color.RED);
			textUser.setBorder(BorderFactory.createLineBorder(Color.RED));
			salida = false;
		}
		String password = new String(textPassword.getPassword());
		if (password.isEmpty()) {
			textPassword.setForeground(Color.RED);
			textPassword.setBorder(BorderFactory.createLineBorder(Color.RED));
			salida = false;
		} 
	
		/* Comprobar que no exista otro usuario con igual login 
		if (!lblUsuarioError.getText().isEmpty() && Controlador.getUnicaInstancia().esUsuarioRegistrado(txtUsuario.getText())) {
			lblUsuarioError.setText("Ya existe ese usuario");
			lblUsuarioError.setVisible(true);
			lblUsuario.setForeground(Color.RED);
			txtUsuario.setBorder(BorderFactory.createLineBorder(Color.RED));
			salida = false;
		}*/


		frmLogin.revalidate();
		frmLogin.pack();
		
		return salida;
	}
	
	private void fixedSize(JComponent o, int x, int y) {
		Dimension d = new Dimension(x, y);
		o.setMinimumSize(d);
		o.setMaximumSize(d);
		o.setPreferredSize(d);
	}

}
