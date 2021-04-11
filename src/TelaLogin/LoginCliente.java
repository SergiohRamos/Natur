package TelaLogin;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.text.MaskFormatter;

import Beans.ClienteLoginBean;
import Dao.ClienteDao;
import Principal.JanelaPrincipal;
import TelasCadastro.CadastroCliente;

public class LoginCliente extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFormattedTextField txtCNPJ;
	private JPasswordField txtSenhaCliente;
	private JButton btnVoltar = new JButton("");
	private MaskFormatter CNPJMask;
	private JButton btnLogin = new JButton("");
	private JButton btnCadastrar = new JButton("");
	private ClienteLoginBean CliLogBean = new ClienteLoginBean();
	private final JButton btnRecuperacaoSenha = new JButton("");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginCliente frame = new LoginCliente();
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
	public LoginCliente() {
		setBounds(100, 100, 430, 418);
		getContentPane().setLayout(null);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 425, 401);
		getContentPane().add(panel);
		panel.setLayout(null);

		try {
			CNPJMask = new MaskFormatter("##.###.###/####-##");
		} catch (Exception e) {
			System.out.println("Erro ao criar a mascara! \n Erro: " + e.getMessage());
		}

		txtCNPJ = new JFormattedTextField(CNPJMask);
		txtCNPJ.setBounds(45, 157, 342, 26);
		txtCNPJ.setOpaque(false);
		txtCNPJ.setBorder(BorderFactory.createEmptyBorder());
		panel.add(txtCNPJ);
		txtCNPJ.setColumns(10);

		txtSenhaCliente = new JPasswordField();
		txtSenhaCliente.setBounds(45, 229, 306, 26);
		txtSenhaCliente.setOpaque(false);
		txtSenhaCliente.setBorder(BorderFactory.createEmptyBorder());
		panel.add(txtSenhaCliente);
		txtSenhaCliente.setColumns(10);
		btnLogin.setIcon(new ImageIcon(LoginCliente.class.getResource("/Botoes/btnLogin.png")));

		btnLogin.setBounds(109, 271, 90, 37);
		btnLogin.setContentAreaFilled(false);
		panel.add(btnLogin);
		btnVoltar.setIcon(new ImageIcon(LoginCliente.class.getResource("/Botoes/btnVoltar.png")));

		btnVoltar.setBounds(325, 342, 90, 37);
		btnVoltar.setContentAreaFilled(false);
		panel.add(btnVoltar);
		btnCadastrar.setIcon(new ImageIcon(LoginCliente.class.getResource("/Botoes/btnCadastre-se.png")));

		btnCadastrar.setBounds(209, 271, 101, 37);
		btnCadastrar.setContentAreaFilled(false);

		panel.add(btnCadastrar);
		btnRecuperacaoSenha.setIcon(new ImageIcon(LoginCliente.class.getResource("/Botoes/btnEsqueceuSenha.png")));
		btnRecuperacaoSenha.setBounds(119, 319, 162, 37);
		btnRecuperacaoSenha.setContentAreaFilled(false);
		btnRecuperacaoSenha.setVisible(false);
		btnRecuperacaoSenha.setEnabled(false);
		panel.add(btnRecuperacaoSenha);

		JLabel lblImgFundo = new JLabel("");
		lblImgFundo.setIcon(new ImageIcon(LoginCliente.class.getResource("/Imagens/LoginCliente.png")));
		lblImgFundo.setBounds(0, 0, 425, 390);
		panel.add(lblImgFundo);

		btnCadastrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Cadastro();
			}
		});
		btnVoltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Voltar();
			}
		});
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Login();
			}
		});
	}

	private void Login() {
		String password = String.valueOf(txtSenhaCliente.getPassword());
		CliLogBean.setCnpj_cliente(txtCNPJ.getText());
		CliLogBean.setSenha_cliente(password);

		if (!(txtCNPJ.getText().isEmpty() || password.isEmpty())) {
			ClienteDao CliDao = new ClienteDao();
			if (CliDao.checkLoginCliente(CliLogBean)) {
				this.dispose();
				JanelaPrincipal janelaPrincipal = new JanelaPrincipal("Cliente", CliLogBean.getCnpj_cliente());
				janelaPrincipal.setVisible(true);

			} else {
				JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos", "Atenção",
						JOptionPane.WARNING_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Preencha os campos para efetuar o login!", "Aviso!",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	private void Cadastro() {
		CadastroCliente cadastroCliente = new CadastroCliente();
		cadastroCliente.setVisible(true);
		this.dispose();
	}

	private void Voltar() {
		OpcaoDeLogin opcaoLogin = new OpcaoDeLogin();
		opcaoLogin.setVisible(true);
		this.dispose();
	}
}
