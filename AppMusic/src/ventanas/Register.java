package ventanas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window.Type;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Dimension;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.seaglasslookandfeel.SeaGlassLookAndFeel;
import com.toedter.calendar.JDateChooser;


import manejador.*;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import java.awt.Component;

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
	static protected final String SEPARADOR = "/";
	private JPasswordField textConfirmar;
		
	private JLabel name;
	private JLabel apellidos;
	private JLabel user;
	private JLabel contrasena;
	private JLabel confirmar;
	private JLabel correo;
	private JLabel fechaN;
	
	
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
		
		//Iniciar label
		
		frmRegistro = new JFrame();
		frmRegistro.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		frmRegistro.setType(Type.UTILITY);
		frmRegistro.setForeground(new Color(248, 248, 255));
		frmRegistro.setBackground(new Color(248, 248, 255));
		frmRegistro.setTitle("REGISTRO");		
		frmRegistro.getContentPane().setBackground(new Color(248, 248, 255));
		frmRegistro.setResizable(false);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{20, 0, 230, 0, 0, 20, 0};
		gridBagLayout.rowHeights = new int[]{32, 19, 0, 0, 0, 0, 0, 30, 33, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frmRegistro.getContentPane().setLayout(gridBagLayout);
		
		
		name = new JLabel("Nombre");
		name.setForeground(Color.BLACK);
		name.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
		GridBagConstraints gbc_name = new GridBagConstraints();
		gbc_name.anchor = GridBagConstraints.EAST;
		gbc_name.insets = new Insets(0, 0, 5, 5);
		gbc_name.gridx = 1;
		gbc_name.gridy = 2;
		frmRegistro.getContentPane().add(name, gbc_name);
		
		textName = new JTextField();
		GridBagConstraints gbc_textName = new GridBagConstraints();
		gbc_textName.anchor = GridBagConstraints.WEST;
		gbc_textName.insets = new Insets(0, 0, 5, 5);
		gbc_textName.gridx = 2;
		gbc_textName.gridy = 2;
		frmRegistro.getContentPane().add(textName, gbc_textName);
		textName.setColumns(25);
		
		lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.setIcon(new ImageIcon(Register.class.getResource("/imagenes/sellado.png")));
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.gridheight = 3;
		gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_7.gridx = 4;
		gbc_lblNewLabel_7.gridy = 1;
		frmRegistro.getContentPane().add(lblNewLabel_7, gbc_lblNewLabel_7);
		
		 apellidos = new JLabel("Apellidos");
		apellidos.setForeground(Color.BLACK);
		apellidos.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
		GridBagConstraints gbc_apellidos = new GridBagConstraints();
		gbc_apellidos.anchor = GridBagConstraints.EAST;
		gbc_apellidos.insets = new Insets(0, 0, 5, 5);
		gbc_apellidos.gridx = 1;
		gbc_apellidos.gridy = 3;
		frmRegistro.getContentPane().add(apellidos, gbc_apellidos);
		
		textApellido = new JTextField();
		GridBagConstraints gbc_textApellido = new GridBagConstraints();
		gbc_textApellido.anchor = GridBagConstraints.WEST;
		gbc_textApellido.insets = new Insets(0, 0, 5, 5);
		gbc_textApellido.gridx = 2;
		gbc_textApellido.gridy = 3;
		frmRegistro.getContentPane().add(textApellido, gbc_textApellido);
		textApellido.setColumns(25);
		
		 user = new JLabel("Usuario");
		user.setForeground(Color.BLACK);
		user.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
		GridBagConstraints gbc_user = new GridBagConstraints();
		gbc_user.insets = new Insets(0, 0, 5, 5);
		gbc_user.anchor = GridBagConstraints.EAST;
		gbc_user.gridx = 1;
		gbc_user.gridy = 4;
		frmRegistro.getContentPane().add(user, gbc_user);
		
		textUser = new JTextField();
		GridBagConstraints gbc_textUser = new GridBagConstraints();
		gbc_textUser.fill = GridBagConstraints.HORIZONTAL;
		gbc_textUser.insets = new Insets(0, 0, 5, 5);
		gbc_textUser.gridx = 2;
		gbc_textUser.gridy = 4;
		frmRegistro.getContentPane().add(textUser, gbc_textUser);
		textUser.setColumns(15);
		
		contrasena = new JLabel("Contrase\u00F1a");
		contrasena.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
		contrasena.setForeground(Color.BLACK);
		GridBagConstraints gbc_contrasena = new GridBagConstraints();
		gbc_contrasena.anchor = GridBagConstraints.EAST;
		gbc_contrasena.insets = new Insets(0, 0, 5, 5);
		gbc_contrasena.gridx = 1;
		gbc_contrasena.gridy = 5;
		frmRegistro.getContentPane().add(contrasena, gbc_contrasena);
		
		textPassword = new JPasswordField();
		GridBagConstraints gbc_textPassword = new GridBagConstraints();
		gbc_textPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_textPassword.insets = new Insets(0, 0, 5, 5);
		gbc_textPassword.gridx = 2;
		gbc_textPassword.gridy = 5;
		frmRegistro.getContentPane().add(textPassword, gbc_textPassword);
		
		confirmar = new JLabel("Confirmar");
		confirmar.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
		confirmar.setForeground(Color.BLACK);
		GridBagConstraints gbc_confirmar = new GridBagConstraints();
		gbc_confirmar.insets = new Insets(0, 0, 5, 5);
		gbc_confirmar.anchor = GridBagConstraints.NORTHEAST;
		gbc_confirmar.gridx = 3;
		gbc_confirmar.gridy = 5;
		frmRegistro.getContentPane().add(confirmar, gbc_confirmar);
		
		textConfirmar = new JPasswordField();
		GridBagConstraints gbc_textConfirmar = new GridBagConstraints();
		gbc_textConfirmar.fill = GridBagConstraints.HORIZONTAL;
		gbc_textConfirmar.insets = new Insets(0, 0, 5, 5);
		gbc_textConfirmar.gridx = 4;
		gbc_textConfirmar.gridy = 5;
		frmRegistro.getContentPane().add(textConfirmar, gbc_textConfirmar);
		
		correo = new JLabel("E-Mail");
		correo.setForeground(Color.BLACK);
		correo.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
		GridBagConstraints gbc_correo = new GridBagConstraints();
		gbc_correo.anchor = GridBagConstraints.EAST;
		gbc_correo.insets = new Insets(0, 0, 5, 5);
		gbc_correo.gridx = 1;
		gbc_correo.gridy = 6;
		frmRegistro.getContentPane().add(correo, gbc_correo);
		
		textCorreo = new JTextField();
		GridBagConstraints gbc_textCorreo = new GridBagConstraints();
		gbc_textCorreo.anchor = GridBagConstraints.WEST;
		gbc_textCorreo.insets = new Insets(0, 0, 5, 5);
		gbc_textCorreo.gridx = 2;
		gbc_textCorreo.gridy = 6;
		frmRegistro.getContentPane().add(textCorreo, gbc_textCorreo);
		textCorreo.setColumns(25);
		
		fechaN = new JLabel("F.Nacimiento");
		fechaN.setForeground(Color.BLACK);
		fechaN.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
		GridBagConstraints gbc_fechaN = new GridBagConstraints();
		gbc_fechaN.anchor = GridBagConstraints.EAST;
		gbc_fechaN.insets = new Insets(0, 0, 5, 5);
		gbc_fechaN.gridx = 1;
		gbc_fechaN.gridy = 7;
		frmRegistro.getContentPane().add(fechaN, gbc_fechaN);
		
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
				if(checkFields()) {
					getFecha();
					if(AppMusic.getInstancia().registroUsuario(textUser.getText(), textPassword.getPassword().toString(), textName.getText(), textApellido.getText(), textCorreo.getText(), fecha)) {
						JOptionPane.showMessageDialog(frmRegistro, "Asistente registrado correctamente.", "Registro",JOptionPane.INFORMATION_MESSAGE);
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
			name.setForeground(Color.RED);
			textName.setBorder(BorderFactory.createLineBorder(Color.RED));
			salida = false;
		}
		if (textApellido.getText().trim().isEmpty()) {
			apellidos.setForeground(Color.RED);
			textApellido.setBorder(BorderFactory.createLineBorder(Color.RED));
			salida = false;
		}
		if (textCorreo.getText().trim().isEmpty()) {
			correo.setForeground(Color.RED);
			textCorreo.setBorder(BorderFactory.createLineBorder(Color.RED));
			salida = false;
		}
		if (textUser.getText().trim().isEmpty()) {
			user.setForeground(Color.RED);
			textUser.setBorder(BorderFactory.createLineBorder(Color.RED));
			salida = false;
		}
		String password = new String(textPassword.getPassword());
		String password2 = new String(textConfirmar.getPassword());
		if (password.isEmpty()) {
			contrasena.setForeground(Color.RED);
			textPassword.setBorder(BorderFactory.createLineBorder(Color.RED));
			salida = false;
		} 
		if (password2.isEmpty()) {
			confirmar.setForeground(Color.RED);
			textConfirmar.setBorder(BorderFactory.createLineBorder(Color.RED));
			salida = false;
		} 
		if (!password.equals(password2)) {

			contrasena.setForeground(Color.RED);
			confirmar.setForeground(Color.RED);
			textPassword.setBorder(BorderFactory.createLineBorder(Color.RED));
			textConfirmar.setBorder(BorderFactory.createLineBorder(Color.RED));
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
		if (dateFechaN.getDate() == null) {
			fechaN.setForeground(Color.RED);
			dateFechaN.setBorder(BorderFactory.createLineBorder(Color.RED));
			salida = false;
		}

		frmRegistro.revalidate();
		frmRegistro.pack();
		
		return salida;
	}
	
	private void fixedSize(JComponent o, int x, int y) {
		Dimension d = new Dimension(x, y);
		o.setMinimumSize(d);
		o.setMaximumSize(d);
		o.setPreferredSize(d);
	}
}
