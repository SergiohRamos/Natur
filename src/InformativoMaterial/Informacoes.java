package InformativoMaterial;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import InformativoMaterial.InfoVidro;
import Principal.JanelaPrincipal;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class Informacoes extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Informacoes frame = new Informacoes();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Informacoes() {
		setTitle("Informa\u00E7\u00F5es");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		this.setLocationRelativeTo(null);
		this.setExtendedState(MAXIMIZED_BOTH);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnPapel = new JButton("");
		btnPapel.setContentAreaFilled(false);
		btnPapel.setBounds(296, 309, 157, 118);
		contentPane.add(btnPapel);

		JButton btnPlastico = new JButton("");
		btnPlastico.setContentAreaFilled(false);
		btnPlastico.setBounds(669, 309, 159, 118);
		contentPane.add(btnPlastico);

		JButton btnVidro = new JButton("");
		btnVidro.setContentAreaFilled(false);
		btnVidro.setBounds(481, 309, 159, 118);
		contentPane.add(btnVidro);

		JButton btnMetal = new JButton("");
		btnMetal.setContentAreaFilled(false);
		btnMetal.setBounds(849, 309, 159, 118);
		contentPane.add(btnMetal);

		JButton btnVoltar = new JButton("");
		btnVoltar.setContentAreaFilled(false);
		btnVoltar.setBounds(552, 486, 171, 51);
		contentPane.add(btnVoltar);

		JButton btnEletronico = new JButton("Eletr\u00F4nico\r\n");
		btnEletronico.setVisible(false);
		btnEletronico.setEnabled(false);
		btnEletronico.setBounds(465, 164, 107, 89);
		contentPane.add(btnEletronico);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Informacoes.class.getResource("/Imagens/InformativoMateriais.png")));
		label.setBounds(0, 0, 1304, 741);
		contentPane.add(label);

		btnEletronico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				InfoEletronico infoeletronico = new InfoEletronico();
				infoeletronico.setVisible(true);
			}
		});
		btnVoltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RetornarJanelaPrincipal();
			}
		});
		btnPapel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				InfoPapel infopapel = new InfoPapel();
				infopapel.setVisible(true);
			}
		});
		btnPlastico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InfoPlastico infoplastico = new InfoPlastico();
				infoplastico.setVisible(true);
			}
		});
		btnMetal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InfoMetal infometal = new InfoMetal();
				infometal.setVisible(true);
			}
		});
		btnVidro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InfoVidro infovidro = new InfoVidro();
				infovidro.setVisible(true);
			}
		});
	}
	public void RetornarJanelaPrincipal() {
		this.dispose();
		JanelaPrincipal principal = new JanelaPrincipal();
		principal.setVisible(true);
	}
}
