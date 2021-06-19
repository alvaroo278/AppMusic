package umu.tds.ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;

import java.awt.Container;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import umu.tds.cargadorCanciones.Cargador;
import umu.tds.manejador.*;
import umu.tds.test.TestPlay;

import com.seaglasslookandfeel.SeaGlassLookAndFeel;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.EventObject;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;

import pulsador.IEncendidoListener;
import pulsador.Luz;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class Principal {

	private JFrame frmPrincipal;
	private static JFrame frame;
	private JPanel expWindow;

	private JPanel nuevaListaWindow;

	private JPanel rcWindow;

	private JPanel listWindow;
	private JScrollPane scrollPane;
	private JPanel panelCentral;
	private JPanel panelInferior;
	protected File fich;
	private AbstractListModel<String> misListas;
	
	private MediaPlayer mediaPlayer;
	private String tempPath;
	private String binPath;


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
			UIManager.setLookAndFeel(new SeaGlassLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		frmPrincipal = new JFrame();
		frmPrincipal.setBounds(100, 100, 864, 571);
		frmPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container contentPane = frmPrincipal.getContentPane();

		frame = frmPrincipal;

		JPanel panelSuperior = new JPanel();
		contentPane.add(panelSuperior, BorderLayout.NORTH);
		panelSuperior.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 10));

		Luz luz = new Luz();
		panelSuperior.add(luz);

		luz.addEncendidoListener(new IEncendidoListener() {
			@Override
			public void enteradoCambioEncendido(EventObject arg0) {
				JFileChooser chooser = new JFileChooser();
				int seleccion = chooser.showOpenDialog(panelCentral);
				if (seleccion == JFileChooser.APPROVE_OPTION) {
					// Cargar canciones
					fich = chooser.getSelectedFile();
					Cargador carg = new Cargador();
					carg.addCancionListener(AppMusic.getUnicaInstancia());
					carg.notificarCambio(carg.getEvento(), fich.toString());
				}
			}
		});
		
		 JLabel lblNewLabel = new JLabel("Hola " + AppMusic.getUnicaInstancia().getUsuario().getNombre());
		 panelSuperior.add(lblNewLabel);

		JButton btnNewButton_1 = new JButton("Mejora tu cuenta");
		panelSuperior.add(btnNewButton_1);

		JButton btnNewButton = new JButton("Logout");
		panelSuperior.add(btnNewButton);

		JPanel panelIzq = new JPanel();
		contentPane.add(panelIzq, BorderLayout.WEST);

		// panel_1.setPreferredSize(new Dimension(600,300));

		frmPrincipal.setLocationRelativeTo(null);

		GridBagLayout gbl_panelIzq = new GridBagLayout();

		gbl_panelIzq.columnWidths = new int[] { 20, 10, 30 };
		gbl_panelIzq.rowHeights = new int[] { 15, 25, 20, 0, 20, 0, 20, 0, 0, 20, 0, 0 };
		gbl_panelIzq.columnWeights = new double[] { 0.0, 1.0, 0.0 };
		gbl_panelIzq.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };

		gbl_panelIzq.columnWidths = new int[] { 15, 157, 15, 0 };
		gbl_panelIzq.rowHeights = new int[] { 30, 25, 15, 0, 15, 0, 15, 0, 0, 0 };
		gbl_panelIzq.columnWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_panelIzq.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };

		panelIzq.setLayout(gbl_panelIzq);

		panelCentral = new Reciente();
		contentPane.add(panelCentral, BorderLayout.CENTER);

		panelInferior = new JPanel();
		frmPrincipal.getContentPane().add(panelInferior, BorderLayout.SOUTH);
		GridBagLayout gbl_panelInferior = new GridBagLayout();
		gbl_panelInferior.columnWidths = new int[] { 350, 0, 10, 0, 10, 0, 0 };
		gbl_panelInferior.rowHeights = new int[] { 5, 0, 10, 0, 10, 0 };
		gbl_panelInferior.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panelInferior.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panelInferior.setLayout(gbl_panelInferior);

		JButton explorarButton = new JButton("Explorar");
		explorarButton.setIcon(new ImageIcon(Principal.class.getResource("/umu/tds/imagenes/lupa.png")));
		explorarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				expWindow = (JPanel) new Explorar();
				if (panelCentral != null)
					contentPane.remove(panelCentral);
				panelCentral = expWindow;
				contentPane.add(expWindow, BorderLayout.CENTER);
				contentPane.revalidate();
				contentPane.repaint();
				validate();
			}
		});

		GridBagConstraints gbc_explorarButton = new GridBagConstraints();
		gbc_explorarButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_explorarButton.insets = new Insets(0, 0, 5, 5);
		gbc_explorarButton.anchor = GridBagConstraints.NORTH;
		gbc_explorarButton.gridx = 1;
		gbc_explorarButton.gridy = 1;
		panelIzq.add(explorarButton, gbc_explorarButton);

		JButton nuevaListaButton = new JButton("Nueva lista");
		nuevaListaButton.setIcon(new ImageIcon(Principal.class.getResource("/umu/tds/imagenes/mas.png")));
		nuevaListaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nuevaListaWindow = (JPanel) new NuevaLista();
				if (panelCentral != null)
					contentPane.remove(panelCentral);
				panelCentral = nuevaListaWindow;
				contentPane.add(nuevaListaWindow, BorderLayout.CENTER);
				contentPane.revalidate();
				contentPane.repaint();
				validate();
			}
		});
		GridBagConstraints gbc_nuevaListaButton = new GridBagConstraints();
		gbc_nuevaListaButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_nuevaListaButton.insets = new Insets(0, 0, 5, 5);
		gbc_nuevaListaButton.gridx = 1;
		gbc_nuevaListaButton.gridy = 3;
		panelIzq.add(nuevaListaButton, gbc_nuevaListaButton);

		JButton recienteButton = new JButton("Reciente");
		recienteButton.setIcon(new ImageIcon(Principal.class.getResource("/umu/tds/imagenes/reloj-de-pared.png")));
		recienteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rcWindow = (JPanel) new Reciente();
				if (panelCentral != null)
					contentPane.remove(panelCentral);
				panelCentral = rcWindow;
				contentPane.add(rcWindow, BorderLayout.CENTER);
				contentPane.revalidate();
				contentPane.repaint();
				validate();

			}
		});
		GridBagConstraints gbc_recienteButton = new GridBagConstraints();
		gbc_recienteButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_recienteButton.insets = new Insets(0, 0, 5, 5);
		gbc_recienteButton.gridx = 1;
		gbc_recienteButton.gridy = 5;
		panelIzq.add(recienteButton, gbc_recienteButton);

		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 2;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 8;
		panelIzq.add(scrollPane, gbc_scrollPane);

		JList<String> listaPlaylist = new JList<String>();
		listaPlaylist.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				String lista = listaPlaylist.getSelectedValue();
				((MisListas) listWindow).mostrarLista(lista);
			}
		});
		listaPlaylist.setFixedCellWidth(100);

		listaPlaylist.setVisibleRowCount(5);
		listaPlaylist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		misListas = new AbstractListModel<String>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			String[] values = AppMusic.getUnicaInstancia().getPlaylistsToString();

			public int getSize() {
				return values.length;
			}

			public String getElementAt(int index) {
				return values[index];
			}
		};

		listaPlaylist.setModel(misListas);
		scrollPane.setViewportView(listaPlaylist);

		scrollPane.setVisible(false);

		JButton misListasButton = new JButton("Mis listas");
		misListasButton.setIcon(new ImageIcon(Principal.class.getResource("/umu/tds/imagenes/favorito.png")));
		misListasButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listWindow = new MisListas();
				if (panelCentral != null)
					contentPane.remove(panelCentral);
				panelCentral = (JPanel) listWindow;
				contentPane.add(listWindow, BorderLayout.CENTER);
				contentPane.revalidate();
				contentPane.repaint();
				validate();
				panelInferior.setVisible(true);
				misListas = new AbstractListModel<String>() {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;
					String[] values = AppMusic.getUnicaInstancia().getPlaylistsToString();

					public String getElementAt(int index) {
						return values[index];
					}

					public int getSize() {
						return values.length;
					}
				};
				listaPlaylist.setModel(misListas);

			}
		});

		GridBagConstraints gbc_misListasButton = new GridBagConstraints();
		gbc_misListasButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_misListasButton.insets = new Insets(0, 0, 5, 5);
		gbc_misListasButton.gridx = 1;
		gbc_misListasButton.gridy = 7;
		panelIzq.add(misListasButton, gbc_misListasButton);

		JButton playButton = new JButton("");
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String song = (String) ((Explorar) panelCentral).getSelectedSong();
				System.out.println(AppMusic.getUnicaInstancia().getCancion(song).getRutaFichero());
				playCancion(AppMusic.getUnicaInstancia().getCancion(song).getRutaFichero());
	
			}
		});
		playButton.setIcon(new ImageIcon(Principal.class.getResource("/umu/tds/imagenes/play.png")));
		GridBagConstraints gbc_playButton = new GridBagConstraints();
		gbc_playButton.insets = new Insets(0, 0, 5, 5);
		gbc_playButton.gridx = 3;
		gbc_playButton.gridy = 1;
		panelInferior.add(playButton, gbc_playButton);

		gbl_panelInferior.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panelInferior.setLayout(gbl_panelInferior);

		JButton pauseButton = new JButton("");
		pauseButton.setIcon(new ImageIcon(Principal.class.getResource("/umu/tds/imagenes/pause.png")));
		pauseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		JButton lastButton = new JButton("");
		lastButton.setIcon(new ImageIcon(Principal.class.getResource("/umu/tds/imagenes/left-chevron (1).png")));

		lastButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		GridBagConstraints gbc_lastButton = new GridBagConstraints();
		gbc_lastButton.insets = new Insets(0, 0, 5, 5);
		gbc_lastButton.gridx = 1;
		gbc_lastButton.gridy = 3;
		panelInferior.add(lastButton, gbc_lastButton);
		GridBagConstraints gbc_pauseButton = new GridBagConstraints();
		gbc_pauseButton.insets = new Insets(0, 0, 5, 5);
		gbc_pauseButton.gridx = 3;
		gbc_pauseButton.gridy = 3;
		panelInferior.add(pauseButton, gbc_pauseButton);

		JButton nextButton = new JButton("");
		nextButton.setIcon(new ImageIcon(Principal.class.getResource("/umu/tds/imagenes/right-chevron (1).png")));
		GridBagConstraints gbc_nextButton = new GridBagConstraints();
		gbc_nextButton.insets = new Insets(0, 0, 5, 0);
		gbc_nextButton.gridx = 5;
		gbc_nextButton.gridy = 3;
		panelInferior.add(nextButton, gbc_nextButton);

		mediaPlayer = null;
		binPath = TestPlay.class.getClassLoader().getResource(".").getPath();
		binPath = binPath.replaceFirst("/", "");
		// quitar "/" añadida al inicio del path en plataforma Windows
		tempPath = binPath.replace("/bin", "/temp");
		
	}

	
	public void playCancion(String url) {
		URL uri = null;
		try {
			com.sun.javafx.application.PlatformImpl.startup(() -> {
			});

			uri = new URL(url);

			System.setProperty("java.io.tmpdir", tempPath);
			Path mp3 = Files.createTempFile("now-playing", ".mp3");

			System.out.println(mp3.getFileName());
			try (InputStream stream = uri.openStream()) {
				Files.copy(stream, mp3, StandardCopyOption.REPLACE_EXISTING);
			}
			System.out.println("finished-copy: " + mp3.getFileName());

			Media media = new Media(mp3.toFile().toURI().toString());
			mediaPlayer = new MediaPlayer(media);
			
			mediaPlayer.play();
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public void validate() {
		if (panelCentral instanceof NuevaLista) {
			frmPrincipal.setBounds(100, 100, 1050, 650);
			panelInferior.setVisible(false);
			scrollPane.setVisible(false);
		} else if (panelCentral instanceof MisListas) {
			scrollPane.setVisible(true);
			frmPrincipal.setBounds(100, 100, 864, 571);
		} else {
			frmPrincipal.setBounds(100, 100, 864, 571);
			scrollPane.setVisible(false);
			panelInferior.setVisible(true);
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

	public static JFrame getFrame() {
		return frame;
	}

}
