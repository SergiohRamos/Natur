package TelaLogin;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import Beans.FuncionarioBean;
import Dao.FuncionarioDao;
import Principal.JanelaPrincipal;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

public class LoginFuncionario extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JFormattedTextField txtCpf;
	private JPasswordField txtSenha;
	private MaskFormatter MaskCpf;

	private FuncionarioDao funcionarioDao = new FuncionarioDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFuncionario frame = new LoginFuncionario();
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
	public LoginFuncionario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 378, 358);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		try {
			MaskCpf = new MaskFormatter("###.###.###-##");
		} catch (Exception e) {
			System.out.println("Problemas ao criar a mascara! \n Erro: " + e.getMessage());
		}
		txtCpf = new JFormattedTextField(MaskCpf);
		txtCpf.setBounds(38, 134, 270, 23);
		txtCpf.setOpaque(false);
		txtCpf.setBorder(BorderFactory.createEmptyBorder());
		contentPane.add(txtCpf);
		txtCpf.setColumns(10);

		txtSenha = new JPasswordField();
		txtSenha.setBounds(38, 195, 270, 23);
		txtSenha.setOpaque(false);
		txtSenha.setBorder(BorderFactory.createEmptyBorder());
		contentPane.add(txtSenha);
		txtSenha.setColumns(10);

		JButton btnLogin = new JButton("");
		btnLogin.setIcon(new ImageIcon(LoginFuncionario.class.getResource("/Botoes/btnLogin.png")));
		btnLogin.setBounds(144, 241, 89, 36);
		btnLogin.setContentAreaFilled(false);
		contentPane.add(btnLogin);

		JButton btnVoltar = new JButton("");
		btnVoltar.setIcon(new ImageIcon(LoginFuncionario.class.getResource("/Botoes/btnVoltar.png")));
		btnVoltar.setContentAreaFilled(false);
		btnVoltar.setBounds(273, 282, 89, 36);
		contentPane.add(btnVoltar);
		
		JLabel lblTela = new JLabel("");
		lblTela.setIcon(new ImageIcon(LoginFuncionario.class.getResource("/Imagens/LoginFuncionario.png")));
		lblTela.setBounds(0, 0, 372, 329);
		contentPane.add(lblTela);

		// ------------------------------------------------------------------------------
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login();
			}
		});
		btnVoltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Voltar();
			}
		});
	}

	private void Login() {
		String Senha = String.valueOf(txtSenha.getPassword());

		if (txtCpf.getText().isEmpty() || Senha.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha os campos para efetuar o login!", "Aviso!", JOptionPane.WARNING_MESSAGE);
		} else {
			FuncionarioBean fb = new FuncionarioBean();
			fb.setCpf_Funcionario(txtCpf.getText());
			fb.setSenha_Funcionario(Senha);
			if (funcionarioDao.VerificarLogin(fb)) {
				JanelaPrincipal Principal = new JanelaPrincipal("Funcionario", txtCpf.getText());
				Principal.setVisible(true);
				this.dispose();
			} else {
				JOptionPane.showMessageDialog(null, "Cpf ou senha inválidos!", "Aviso!", JOptionPane.WARNING_MESSAGE);
			}
		}
	}

	private void Voltar() {
		OpcaoDeLogin Login = new OpcaoDeLogin();
		Login.setVisible(true);
		this.dispose();
	}
}
