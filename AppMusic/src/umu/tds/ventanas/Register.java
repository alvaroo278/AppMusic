package umu.tds.ventanas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window.Type;

import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Cursor;


import com.seaglasslookandfeel.SeaGlassLookAndFeel;
import com.toedter.calendar.JDateChooser;

import umu.tds.manejador.*;
import java.awt.event.ActionListener;
import java.sql.Date;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;

public class Register {

	private JFrame frmRegistro;
	private JTextField textName;
	private JTextField textApellido;
	private JTextField textUser;

	private JTextField textCorreo;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JDateChooser dateFechaN;
	private JLabel lblNewLabel_7;
	private LocalDate fecha;
	private JPasswordField textPassword;
	static private final String SEPARADOR = "/";
	private JPasswordField textConfirmar;

	private JLabel lblName;
	private JLabel lblApellidos;
	private JLabel lblUser;
	private JLabel lblContrasena;
	private JLabel lblConfirmar;
	private JLabel lblCorreo;
	private JLabel lblfechaN;
	private JLabel lblUsuarioError;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register window = new Register();
					window.frmRegistro.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Register() {

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

		// Iniciar label

		lblUsuarioError = new JLabel("El ususario ya existe");
		lblUsuarioError.setVisible(false);

		frmRegistro = new JFrame();
		frmRegistro.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		frmRegistro.setType(Type.UTILITY);
		frmRegistro.setForeground(new Color(248, 248, 255));
		frmRegistro.setBackground(new Color(248, 248, 255));
		frmRegistro.setTitle("REGISTRO");
		frmRegistro.getContentPane().setBackground(new Color(248, 248, 255));
		frmRegistro.setResizable(false);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 20, 0, 230, 0, 0, 20, 0 };
		gridBagLayout.rowHeights = new int[] { 32, 19, 0, 0, 0, 0, 0, 30, 33, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		frmRegistro.getContentPane().setLayout(gridBagLayout);

		lblName = new JLabel("Nombre");
		lblName.setForeground(Color.BLACK);
		lblName.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.EAST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 1;
		gbc_lblName.gridy = 2;
		frmRegistro.getContentPane().add(lblName, gbc_lblName);

		textName = new JTextField();
		GridBagConstraints gbc_textName = new GridBagConstraints();
		gbc_textName.anchor = GridBagConstraints.WEST;
		gbc_textName.insets = new Insets(0, 0, 5, 5);
		gbc_textName.gridx = 2;
		gbc_textName.gridy = 2;
		frmRegistro.getContentPane().add(textName, gbc_textName);
		textName.setColumns(25);

		lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.setIcon(new ImageIcon(Register.class.getResource("/umu/tds/imagenes/sellado.png")));
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.gridheight = 3;
		gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_7.gridx = 4;
		gbc_lblNewLabel_7.gridy = 1;
		frmRegistro.getContentPane().add(lblNewLabel_7, gbc_lblNewLabel_7);

		lblApellidos = new JLabel("Apellidos");
		lblApellidos.setForeground(Color.BLACK);
		lblApellidos.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
		GridBagConstraints gbc_lblApellidos = new GridBagConstraints();
		gbc_lblApellidos.anchor = GridBagConstraints.EAST;
		gbc_lblApellidos.insets = new Insets(0, 0, 5, 5);
		gbc_lblApellidos.gridx = 1;
		gbc_lblApellidos.gridy = 3;
		frmRegistro.getContentPane().add(lblApellidos, gbc_lblApellidos);

		textApellido = new JTextField();
		GridBagConstraints gbc_textApellido = new GridBagConstraints();
		gbc_textApellido.anchor = GridBagConstraints.WEST;
		gbc_textApellido.insets = new Insets(0, 0, 5, 5);
		gbc_textApellido.gridx = 2;
		gbc_textApellido.gridy = 3;
		frmRegistro.getContentPane().add(textApellido, gbc_textApellido);
		textApellido.setColumns(25);

		lblUser = new JLabel("Usuario");
		lblUser.setForeground(Color.BLACK);
		lblUser.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
		GridBagConstraints gbc_lblUser = new GridBagConstraints();
		gbc_lblUser.insets = new Insets(0, 0, 5, 5);
		gbc_lblUser.anchor = GridBagConstraints.EAST;
		gbc_lblUser.gridx = 1;
		gbc_lblUser.gridy = 4;
		frmRegistro.getContentPane().add(lblUser, gbc_lblUser);

		textUser = new JTextField();
		GridBagConstraints gbc_textUser = new GridBagConstraints();
		gbc_textUser.fill = GridBagConstraints.HORIZONTAL;
		gbc_textUser.insets = new Insets(0, 0, 5, 5);
		gbc_textUser.gridx = 2;
		gbc_textUser.gridy = 4;
		frmRegistro.getContentPane().add(textUser, gbc_textUser);
		textUser.setColumns(15);

		lblContrasena = new JLabel("Contrase\u00F1a");
		lblContrasena.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
		lblContrasena.setForeground(Color.BLACK);
		GridBagConstraints gbc_lblContrasena = new GridBagConstraints();
		gbc_lblContrasena.anchor = GridBagConstraints.EAST;
		gbc_lblContrasena.insets = new Insets(0, 0, 5, 5);
		gbc_lblContrasena.gridx = 1;
		gbc_lblContrasena.gridy = 5;
		frmRegistro.getContentPane().add(lblContrasena, gbc_lblContrasena);

		textPassword = new JPasswordField();
		GridBagConstraints gbc_textPassword = new GridBagConstraints();
		gbc_textPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_textPassword.insets = new Insets(0, 0, 5, 5);
		gbc_textPassword.gridx = 2;
		gbc_textPassword.gridy = 5;
		frmRegistro.getContentPane().add(textPassword, gbc_textPassword);

		lblConfirmar = new JLabel("Confirmar");
		lblConfirmar.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
		lblConfirmar.setForeground(Color.BLACK);
		GridBagConstraints gbc_lblConfirmar = new GridBagConstraints();
		gbc_lblConfirmar.insets = new Insets(0, 0, 5, 5);
		gbc_lblConfirmar.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblConfirmar.gridx = 3;
		gbc_lblConfirmar.gridy = 5;
		frmRegistro.getContentPane().add(lblConfirmar, gbc_lblConfirmar);

		textConfirmar = new JPasswordField();
		GridBagConstraints gbc_textConfirmar = new GridBagConstraints();
		gbc_textConfirmar.fill = GridBagConstraints.HORIZONTAL;
		gbc_textConfirmar.insets = new Insets(0, 0, 5, 5);
		gbc_textConfirmar.gridx = 4;
		gbc_textConfirmar.gridy = 5;
		frmRegistro.getContentPane().add(textConfirmar, gbc_textConfirmar);

		lblCorreo = new JLabel("E-Mail");
		lblCorreo.setForeground(Color.BLACK);
		lblCorreo.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
		GridBagConstraints gbc_lblCorreo = new GridBagConstraints();
		gbc_lblCorreo.anchor = GridBagConstraints.EAST;
		gbc_lblCorreo.insets = new Insets(0, 0, 5, 5);
		gbc_lblCorreo.gridx = 1;
		gbc_lblCorreo.gridy = 6;
		frmRegistro.getContentPane().add(lblCorreo, gbc_lblCorreo);

		textCorreo = new JTextField();
		GridBagConstraints gbc_textCorreo = new GridBagConstraints();
		gbc_textCorreo.anchor = GridBagConstraints.WEST;
		gbc_textCorreo.insets = new Insets(0, 0, 5, 5);
		gbc_textCorreo.gridx = 2;
		gbc_textCorreo.gridy = 6;
		frmRegistro.getContentPane().add(textCorreo, gbc_textCorreo);
		textCorreo.setColumns(25);

		lblfechaN = new JLabel("F.Nacimiento");
		lblfechaN.setForeground(Color.BLACK);
		lblfechaN.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
		GridBagConstraints gbc_lblfechaN = new GridBagConstraints();
		gbc_lblfechaN.anchor = GridBagConstraints.EAST;
		gbc_lblfechaN.insets = new Insets(0, 0, 5, 5);
		gbc_lblfechaN.gridx = 1;
		gbc_lblfechaN.gridy = 7;
		frmRegistro.getContentPane().add(lblfechaN, gbc_lblfechaN);

		dateFechaN = new JDateChooser();
		dateFechaN.setDateFormatString("dd/MM/yyyy");
		dateFechaN.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		GridBagConstraints gbc_dateFechaN = new GridBagConstraints();
		gbc_dateFechaN.anchor = GridBagConstraints.WEST;
		gbc_dateFechaN.insets = new Insets(0, 0, 5, 5);
		gbc_dateFechaN.fill = GridBagConstraints.VERTICAL;
		gbc_dateFechaN.gridx = 2;
		gbc_dateFechaN.gridy = 7;
		frmRegistro.getContentPane().add(dateFechaN, gbc_dateFechaN);

		btnNewButton_1 = new JButton("Volver");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmRegistro.setVisible(false);
				Login login = new Login();
				login.getFrame().setVisible(true);
			}
		});
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.anchor = GridBagConstraints.WEST;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 2;
		gbc_btnNewButton_1.gridy = 9;
		frmRegistro.getContentPane().add(btnNewButton_1, gbc_btnNewButton_1);

		btnNewButton = new JButton("Confirmar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (checkFields()) {
					getFecha();
					AppMusic.getUnicaInstancia().registroUsuario(textName.getText(), textApellido.getText(),
							textCorreo.getText(), textUser.getText(), new String(textPassword.getPassword()), fecha);
					JOptionPane.showMessageDialog(frmRegistro, "Asistente registrado correctamente.", "Registro",
							JOptionPane.INFORMATION_MESSAGE);
					frmRegistro.setVisible(false);
					Login login = new Login();
					login.getFrame().setVisible(true);
					frmRegistro.dispose();
				} else {
					JOptionPane.showMessageDialog(frmRegistro, "No se ha podido llevar a cabo el registro.\n",
							"Registro", JOptionPane.ERROR_MESSAGE);
					frmRegistro.setTitle("Login Gestor Eventos");
				}
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton.gridx = 4;
		gbc_btnNewButton.gridy = 9;
		frmRegistro.getContentPane().add(btnNewButton, gbc_btnNewButton);
		frmRegistro.setBounds(100, 100, 748, 371);
		frmRegistro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRegistro.setLocationRelativeTo(null);
	}

	public JFrame getFrame() {
		return frmRegistro;
	}

	private void getFecha() {
		SimpleDateFormat formato = new SimpleDateFormat(dateFechaN.getDateFormatString());
		String[] lines = formato.format(dateFechaN.getDate()).split(String.valueOf(SEPARADOR));
		fecha = LocalDate.of(Integer.parseInt(lines[2]), Integer.parseInt(lines[1]), Integer.parseInt(lines[0]));
	}

	private boolean checkFields() {
		boolean salida = true;
		if (textName.getText().trim().isEmpty()) {
			lblName.setForeground(Color.RED);
			textName.setBorder(BorderFactory.createLineBorder(Color.RED));
			salida = false;
		}
		if (textApellido.getText().trim().isEmpty()) {
			lblApellidos.setForeground(Color.RED);
			textApellido.setBorder(BorderFactory.createLineBorder(Color.RED));
			salida = false;
		}
		if (textCorreo.getText().trim().isEmpty()) {
			lblCorreo.setForeground(Color.RED);
			textCorreo.setBorder(BorderFactory.createLineBorder(Color.RED));
			salida = false;
		}
		if (textUser.getText().trim().isEmpty()) {
			lblUsuarioError.setVisible(true);
			lblUsuarioError.setBounds(150, 150, 10, 10);
			lblUser.setForeground(Color.RED);
			textUser.setBorder(BorderFactory.createLineBorder(Color.RED));
			salida = false;
		} else {
			if (AppMusic.getUnicaInstancia().esUsuarioRegistrado(textUser.getText())) {
				lblUsuarioError.setVisible(true);
				lblUsuarioError.setBounds(100, 100, 10, 10);
				lblUser.setForeground(Color.RED);
				textUser.setBorder(BorderFactory.createLineBorder(Color.RED));
				salida = false;
			}
		}
		String password = new String(textPassword.getPassword());
		String password2 = new String(textConfirmar.getPassword());
		if (password.isEmpty()) {
			lblContrasena.setForeground(Color.RED);
			textPassword.setBorder(BorderFactory.createLineBorder(Color.RED));
			salida = false;
		}
		if (password2.isEmpty()) {
			lblConfirmar.setForeground(Color.RED);
			textConfirmar.setBorder(BorderFactory.createLineBorder(Color.RED));
			salida = false;
		}
		if (!password.equals(password2)) {

			lblContrasena.setForeground(Color.RED);
			lblConfirmar.setForeground(Color.RED);
			textPassword.setBorder(BorderFactory.createLineBorder(Color.RED));
			textConfirmar.setBorder(BorderFactory.createLineBorder(Color.RED));
			salida = false;
		}
		// Comprobar que no exista otro usuario con igual login

		if (dateFechaN.getDate() == null || dateFechaN.getDate().after(Date.valueOf(LocalDate.now()))) {
			lblfechaN.setForeground(Color.RED);
			dateFechaN.setBorder(BorderFactory.createLineBorder(Color.RED));
			salida = false;
		}

		frmRegistro.revalidate();
		frmRegistro.pack();

		return salida;
	}

}
