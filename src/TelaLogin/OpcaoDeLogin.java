package TelaLogin;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Principal.JanelaPrincipal;

public class OpcaoDeLogin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton btnAdministrador = new JButton();
	JButton btnFornecedor = new JButton();
	JButton btnCliente = new JButton();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OpcaoDeLogin frame = new OpcaoDeLogin();
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
	public OpcaoDeLogin() {
		setBounds(100, 100, 896, 554);
		getContentPane().setLayout(null);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 890, 525);
		getContentPane().add(panel);
		panel.setLayout(null);
		btnAdministrador.setIcon(new ImageIcon(OpcaoDeLogin.class.getResource("/Botoes/BotaoLoginProprietario.png")));

		btnAdministrador.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LoginAdministrador loginAdm = new LoginAdministrador();
				loginAdm.setVisible(true);
				dispose();
			}
		});
		btnAdministrador.setContentAreaFilled(false);
		btnAdministrador.setBounds(675, 60, 200, 225);
		panel.add(btnAdministrador);
		btnFornecedor.setIcon(new ImageIcon(OpcaoDeLogin.class.getResource("/Botoes/BotaoLoginFornecedorr.png")));

		btnFornecedor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LoginFornecedor loginFornecedor = new LoginFornecedor();
				loginFornecedor.setVisible(true);
				dispose();
			}
		});
		btnFornecedor.setContentAreaFilled(false);
		btnFornecedor.setBounds(10, 60, 200, 225);
		panel.add(btnFornecedor);
		btnCliente.setIcon(new ImageIcon(OpcaoDeLogin.class.getResource("/Botoes/BotaoLoginCliente.png")));

		btnCliente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LoginCliente loginCliente = new LoginCliente();
				loginCliente.setVisible(true);
				dispose();
			}
		});
		btnCliente.setContentAreaFilled(false);
		btnCliente.setBounds(230, 60, 200, 225);
		panel.add(btnCliente);

		JButton btnVoltar = new JButton("");
		btnVoltar.setIcon(new ImageIcon(OpcaoDeLogin.class.getResource("/Botoes/VoltarOpcaoLogin.png")));
		btnVoltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JanelaPrincipal janelaPrincipal = new JanelaPrincipal();
				janelaPrincipal.setVisible(true);
				dispose();
			}
		});
		btnVoltar.setContentAreaFilled(false);
		btnVoltar.setBounds(329, 380, 176, 81);
		panel.add(btnVoltar);

		JButton btnFuncionario = new JButton("");
		btnFuncionario.setIcon(new ImageIcon(OpcaoDeLogin.class.getResource("/Botoes/BotaoLoginFuncionario.png")));
		btnFuncionario.setBounds(451, 60, 200, 225);
		btnFuncionario.setContentAreaFilled(false);
		panel.add(btnFuncionario);

		JLabel lblImgFundo = new JLabel("");
		lblImgFundo.setIcon(new ImageIcon(OpcaoDeLogin.class.getResource("/Imagens/LogarComo.png")));
		lblImgFundo.setBounds(0, 0, 896, 523);
		panel.add(lblImgFundo);

		// -------------------------------------------------------------------------------
		btnFuncionario.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				LoginFuncionario();
			}
		});

	}

	private void LoginFuncionario() {
		LoginFuncionario Login = new LoginFuncionario();
		Login.setVisible(true);
		this.dispose();
	}
}
