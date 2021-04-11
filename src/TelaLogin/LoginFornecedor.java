package TelaLogin;

import java.awt.Color;
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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import Beans.FornecedorBean;
import Dao.LoginDao;
import Principal.JanelaPrincipal;
import Principal.TelaRecuperacaoSenha;
import TelasCadastro.CadastroFornecedor;

public class LoginFornecedor extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtCpf_fornecedor;
	private JPasswordField passSenha;
	private MaskFormatter MaskCpf;
	private FornecedorBean FornecedorBean = new FornecedorBean();
	private JButton btnVoltar = new JButton("");
	private JButton btnCadastro = new JButton("");
	private JButton btnLogin = new JButton("");
	private JLabel CPF = new JLabel("");
	private JButton btnRecuperarSenha = new JButton("");
	private JLabel lblImagem = new JLabel("");

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFornecedor frame = new LoginFornecedor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LoginFornecedor() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 430, 428);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		try {
			MaskCpf = new MaskFormatter("###.###.###-##");
		} catch (Exception e) {
			System.out.println("Falha na criação da mascara! \n Erro: " + e.getMessage());
			e.printStackTrace();
		}

		txtCpf_fornecedor = new JFormattedTextField(MaskCpf);
		txtCpf_fornecedor.setBounds(42, 124, 299, 34);
		contentPane.add(txtCpf_fornecedor);
		txtCpf_fornecedor.setColumns(10);
		txtCpf_fornecedor.setOpaque(false);
		txtCpf_fornecedor.setBorder(BorderFactory.createEmptyBorder());

		passSenha = new JPasswordField();
		passSenha.setBounds(42, 200, 309, 34);
		contentPane.add(passSenha);
		passSenha.setOpaque(false);
		passSenha.setBorder(BorderFactory.createEmptyBorder());
		btnLogin.setIcon(new ImageIcon(LoginFornecedor.class.getResource("/Botoes/btnLogin.png")));

		btnLogin.setBounds(111, 257, 92, 35);
		btnLogin.setForeground(Color.WHITE);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Login();
			}
		});
		btnLogin.setContentAreaFilled(false);
		contentPane.add(btnLogin);
		btnVoltar.setIcon(new ImageIcon(LoginFornecedor.class.getResource("/Botoes/btnVoltar.png")));

		btnVoltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Voltar();
			}
		});
		btnVoltar.setBounds(322, 353, 92, 35);
		contentPane.add(btnVoltar);
		btnVoltar.setContentAreaFilled(false);

		CPF = new JLabel("");
		CPF.setBackground(Color.PINK);
		CPF.setBounds(22, 339, 67, 28);
		contentPane.add(CPF);

		btnRecuperarSenha = new JButton("");
		btnRecuperarSenha.setVisible(false);
		btnRecuperarSenha.setEnabled(false);
		btnRecuperarSenha.setIcon(new ImageIcon(LoginFornecedor.class.getResource("/Botoes/btnEsqueceuSenha.png")));
		btnRecuperarSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RecuperarSenha();
			}
		});
		btnRecuperarSenha.setBounds(42, 329, 161, 38);
		btnRecuperarSenha.setContentAreaFilled(false);
		contentPane.add(btnRecuperarSenha);
		btnCadastro.setIcon(new ImageIcon(LoginFornecedor.class.getResource("/Botoes/btnCadastre-se.png")));

		btnCadastro.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Cadastrar();
			}
		});
		btnCadastro.setBounds(224, 257, 100, 35);
		btnCadastro.setContentAreaFilled(false);
		contentPane.add(btnCadastro);

		lblImagem = new JLabel("");
		lblImagem.setBounds(0, 0, 425, 399);
		lblImagem.setIcon(new ImageIcon(LoginFornecedor.class.getResource("/Imagens/LoginFornecedor.png")));
		contentPane.add(lblImagem);
	}

	private void Login() {
		String strSenha = String.valueOf(passSenha.getPassword());
		FornecedorBean.setCpf_fornecedor(txtCpf_fornecedor.getText());
		FornecedorBean.setSenha_fornecedor(strSenha);

		if (!(txtCpf_fornecedor.getText().isEmpty() || strSenha.isEmpty())) {
			LoginDao Dao = new LoginDao();
			if (Dao.checklogin(FornecedorBean)) {
				JanelaPrincipal janelaPrincipal = new JanelaPrincipal("Fornecedor", FornecedorBean.getCpf_fornecedor());
				janelaPrincipal.setVisible(true);
				dispose();
			} else {
				JOptionPane.showMessageDialog(null, "Usuário ou Senha incorretos", "Atenção",
						JOptionPane.WARNING_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Preencha os campos para efetuar o login!", "Atenção", JOptionPane.WARNING_MESSAGE);
		}
	}

	private void Voltar() {
		OpcaoDeLogin opcaoLogin = new OpcaoDeLogin();
		opcaoLogin.setVisible(true);
		this.dispose();
	}

	private void RecuperarSenha() {
		TelaRecuperacaoSenha RecuperarSenha = new TelaRecuperacaoSenha();
		RecuperarSenha.setVisible(true);
		dispose();
	}

	private void Cadastrar() {
		CadastroFornecedor cadastroFornecedor = new CadastroFornecedor();
		cadastroFornecedor.setVisible(true);
		this.dispose();
	}
}
