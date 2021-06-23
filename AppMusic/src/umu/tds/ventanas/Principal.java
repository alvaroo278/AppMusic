package umu.tds.ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;

import javax.swing.JFileChooser;

import java.awt.Container;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import umu.tds.cargadorCanciones.Cargador;
import umu.tds.cargadorCanciones.Reproductor;
import umu.tds.dominio.Cancion;
import umu.tds.manejador.*;

import com.seaglasslookandfeel.SeaGlassLookAndFeel;

import javafx.beans.value.ObservableValue;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.util.Duration;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.io.File;

import java.util.EventObject;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;

import pulsador.IEncendidoListener;
import pulsador.Luz;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.Color;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.Cursor;
import javax.swing.JRadioButton;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class Principal {

	private JFrame frmPrincipal;
	private static JFrame frame;
	private JPanel expWindow;

	private JPanel nuevaListaWindow;

	private JPanel rcWindow;
	private JPanel masEscuchadasWindow;
	private JPanel listWindow;
	private JScrollPane scrollPane;
	private JPanel panelCentral;
	private JPanel panelInferior;
	protected File fich;
	private AbstractListModel<String> misListas;
	private Reproductor reproductor;
	private String[] titles = new String[100];
	private String song;
	private int songActual;
	private JSlider sliderSong;
	JSlider slider;
	private Duration duracion = Duration.ZERO;
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
		frmPrincipal.setBounds(100, 100, 860, 565);
		frmPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container contentPane = frmPrincipal.getContentPane();

		frame = frmPrincipal;

		JPanel panelSuperior = new JPanel();
		contentPane.add(panelSuperior, BorderLayout.NORTH);
		panelSuperior.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 10));

		JButton masEscuchadasButton = new JButton("");
		masEscuchadasButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				masEscuchadasWindow = (JPanel) new MasReproducidas();
				if (panelCentral != null)
					contentPane.remove(panelCentral);
				panelCentral = masEscuchadasWindow;
				contentPane.add(masEscuchadasWindow, BorderLayout.CENTER);
				contentPane.revalidate();
				contentPane.repaint();
				validate();
			}
		});
		masEscuchadasButton.setIcon(new ImageIcon(Principal.class.getResource("/umu/tds/imagenes/beneficios.png")));
		panelSuperior.add(masEscuchadasButton);

		Luz luz = new Luz();
		luz.setColor(Color.WHITE);
		panelSuperior.add(luz);

		luz.addEncendidoListener(new IEncendidoListener() {
			@Override
			public void enteradoCambioEncendido(EventObject arg0) {
				Object[] options = { "Locales", "Desde la nube" };
				JFileChooser chooser = new JFileChooser();
				int n = JOptionPane.showOptionDialog(Principal.getFrame(),
						"�Desea a�adir canciones locales o de la nube?", "Cargar canciones", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, options, null);
				if (n == 0) {
					int seleccion = chooser.showOpenDialog(panelCentral);
					if (seleccion == JFileChooser.APPROVE_OPTION) {
						fich = chooser.getCurrentDirectory();
						AppMusic.getUnicaInstancia().cargarCancionesLocales(fich);

					}
				} else if (n == 1) {
					int seleccion = chooser.showOpenDialog(panelCentral);
					if (seleccion == JFileChooser.APPROVE_OPTION) {
						// Cargar canciones
						fich = chooser.getSelectedFile();
						Cargador carg = new Cargador();
						carg.addCancionListener(AppMusic.getUnicaInstancia());
						carg.notificarCambio(carg.getEvento(), fich.toString());
					}
				}
			}
		});

		inicializarReproductor();

		JLabel lblNewLabel = new JLabel("Hola " + AppMusic.getUnicaInstancia().getUsuario().getNombre());
		panelSuperior.add(lblNewLabel);

		JButton btnNewButton_1 = new JButton("Mejora tu cuenta");
		panelSuperior.add(btnNewButton_1);

		JButton btnNewButton = new JButton("Logout");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AppMusic.getUnicaInstancia().logout();
				Login lg = new Login();
				contentPane.removeAll();
				frmPrincipal.setVisible(false);
				frmPrincipal = null;
				reproductor.getMediaPlayer().stop();
				lg.getFrame().setVisible(true);
			}
		});
		panelSuperior.add(btnNewButton);

		JPanel panelIzq = new JPanel();
		contentPane.add(panelIzq, BorderLayout.WEST);

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
		contentPane.add(panelInferior, BorderLayout.SOUTH);
		GridBagLayout gbl_panelInferior = new GridBagLayout();
		gbl_panelInferior.columnWidths = new int[] { 285, 0, 10, 0, 10, 0, 0, 0, 50, 100, 0, 0, 20, 0 };
		gbl_panelInferior.rowHeights = new int[] { 0, 0, 10, 0, 10, 25, 10, 0 };
		gbl_panelInferior.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0,
				0.0, Double.MIN_VALUE };
		gbl_panelInferior.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
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
				nuevaListaWindow = new NuevaLista();
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
				try {
					((MisListas) listWindow).mostrarLista(lista);
				} catch (NullPointerException e) {
					return;
				}

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

		gbl_panelInferior.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panelInferior.setLayout(gbl_panelInferior);

		JButton playButton = new JButton("");
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				song = "";
				if (panelCentral instanceof Reciente) {
					song = (String) ((((Reciente) panelCentral).getSelectedSong()));
					titles = ((Reciente) panelCentral).getTitles();
				} else if (panelCentral instanceof Explorar) {
					song = (String) ((((Explorar) panelCentral).getSelectedSong()));
					titles = ((Explorar) panelCentral).getTitles();
				} else if (panelCentral instanceof NuevaLista) {
					song = (String) ((((NuevaLista) panelCentral).getSelectedSong()));
					titles = ((NuevaLista) panelCentral).getTitles();
				} else if (panelCentral instanceof MisListas) {
					song = (String) ((((MisListas) panelCentral).getSelectedSong()));
					titles = ((MisListas) panelCentral).getTitles();
				} else if (panelCentral instanceof MasReproducidas) {
					song = (String) ((((MasReproducidas) panelCentral).getSelectedSong()));
					titles = ((MasReproducidas) panelCentral).getTitles();
				}
				if (song.equals(""))
					return;
				songActual = getIdx(song);
				reproducir(song);
				
			}
		});
		playButton.setIcon(new ImageIcon(Principal.class.getResource("/umu/tds/imagenes/play.png")));
		GridBagConstraints gbc_playButton = new GridBagConstraints();
		gbc_playButton.insets = new Insets(0, 0, 5, 5);
		gbc_playButton.gridx = 3;
		gbc_playButton.gridy = 1;
		panelInferior.add(playButton, gbc_playButton);

		JButton pauseButton = new JButton("");
		pauseButton.setIcon(new ImageIcon(Principal.class.getResource("/umu/tds/imagenes/pause.png")));
		pauseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reproductor.pauseCancion();
				double d = sliderSong.getValue();
				duracion = new Duration(d*1000);
			}
		});
		
		JButton lastButton = new JButton("");
		lastButton.setIcon(new ImageIcon(Principal.class.getResource("/umu/tds/imagenes/left-chevron (1).png")));

		lastButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reproductor.stopCancion();
				duracion = Duration.ZERO;
				LastSong();
				
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
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reproductor.stopCancion();
				duracion = Duration.ZERO;
				nextSong();
				
			}
		});
		nextButton.setIcon(new ImageIcon(Principal.class.getResource("/umu/tds/imagenes/right-chevron (1).png")));
		GridBagConstraints gbc_nextButton = new GridBagConstraints();
		gbc_nextButton.insets = new Insets(0, 0, 5, 5);
		gbc_nextButton.gridx = 5;
		gbc_nextButton.gridy = 3;
		panelInferior.add(nextButton, gbc_nextButton);

		slider = new JSlider();
		slider.setPreferredSize(new Dimension(100, 26));
		slider.setValueIsAdjusting(true);
		slider.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		slider.setSnapToTicks(true);
		slider.setRequestFocusEnabled(false);
		slider.setValue(100);
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				double volume = (double) slider.getValue() / 100;
				if (reproductor.getMediaPlayer() != null)
					reproductor.getMediaPlayer().setVolume(volume);
			}
		});
		GridBagConstraints gbc_slider = new GridBagConstraints();
		gbc_slider.insets = new Insets(0, 0, 5, 5);
		gbc_slider.gridx = 9;
		gbc_slider.gridy = 3;
		panelInferior.add(slider, gbc_slider);

		 sliderSong = new JSlider();
		/* sliderSong.addChangeListener(new ChangeListener() {
		 	public void stateChanged(ChangeEvent arg0) {
		 		actualizarDuracion(sliderSong.getValue(), sliderSong.getMaximum());
		 		actualizarCancion();
		 	}
		 });*/
		
		sliderSong.setMaximum(200);
		sliderSong.setValue(0);
		
		sliderSong.setPreferredSize(new Dimension(180, 20));
		GridBagConstraints gbc_slider_1 = new GridBagConstraints();
		gbc_slider_1.gridwidth = 5;
		gbc_slider_1.insets = new Insets(0, 0, 5, 5);
		gbc_slider_1.gridx = 1;
		gbc_slider_1.gridy = 5;
		panelInferior.add(sliderSong, gbc_slider_1);

		JRadioButton rdbtnNewRadioButton = new JRadioButton("");
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reproduccionAutomatica();
			}
		});
		rdbtnNewRadioButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		GridBagConstraints gbc_rdbtnNewRadioButton = new GridBagConstraints();
		gbc_rdbtnNewRadioButton.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnNewRadioButton.gridx = 6;
		gbc_rdbtnNewRadioButton.gridy = 5;
		panelInferior.add(rdbtnNewRadioButton, gbc_rdbtnNewRadioButton);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Principal.class.getResource("/umu/tds/imagenes/cargando.png")));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 7;
		gbc_lblNewLabel_1.gridy = 5;
		panelInferior.add(lblNewLabel_1, gbc_lblNewLabel_1);
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

	public void inicializarReproductor() {
		reproductor = new Reproductor();
	}

	private void reproducir(String song) {
		AppMusic.getUnicaInstancia().anadirRepro(song);
		AppMusic.getUnicaInstancia().anadirReciente(song);
		reproductor.playCancion(AppMusic.getUnicaInstancia().getCancion(song).getRutaFichero(),duracion,slider.getValue());	
		MediaPlayer m = reproductor.getMediaPlayer();
	
		m.currentTimeProperty().addListener(new javafx.beans.value.ChangeListener<Duration>() {	
			@Override
			public void changed(ObservableValue<? extends Duration> arg0, Duration arg1, Duration arg2) {
				// TODO Auto-generated method stub
				double cont = arg2.toSeconds();
				double total = m.getTotalDuration().toSeconds() + duracion.toSeconds();
				actualizarDuracion(cont,total+duracion.toSeconds());
			}

			
		});
	}
	

	private int getIdx(String song) {
		int idx = 0;
		for (String string : titles) {
			if (string.equals(song)) {
				return idx;
			}
			idx++;
		}
		return idx;
	}

	private void nextSong() {
		if (songActual < titles.length - 1) {
			songActual = getIdx(song);
			songActual++;
		} else {
			songActual = 0;
		}
		song = titles[songActual];
		reproducir(song);

	}

	private void LastSong() {
		if (songActual > 0) {
			songActual = getIdx(song);
			songActual--;
		} else {
			songActual = titles.length-1;
		}
		song = titles[songActual];
		reproducir(song);

	}
	private void actualizarDuracion(double seconds, double total) {
		if(reproductor.getMediaPlayer() != null) {
			sliderSong.setValue((int) (seconds));
			sliderSong.setMaximum((int) total);
			duracion = new Duration(seconds*1000);
		}

	}	
	
	
	private void reproduccionAutomatica() {
		try {
			reproductor.getMediaPlayer().setAutoPlay(true);
		}catch (NullPointerException e) {
			return;
		}
	}
	
	private void actualizarCancion() {
		reproductor.actualizarCancion(song, duracion, slider.getValue());
	}

}
