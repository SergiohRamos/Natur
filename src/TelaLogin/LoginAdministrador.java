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
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import Beans.AdministradorLoginBean;
import Dao.AdministradorDao;
import Principal.PrincipalCentroColeta;

public class LoginAdministrador extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JTextField txtCpf;
	private JPasswordField txtSenha;
	private MaskFormatter MaskCpf;
	private JButton btnLogin;
	private JLabel lblImagemFundo;

	private AdministradorLoginBean alb = new AdministradorLoginBean();
	private AdministradorDao AdministradorDao = new AdministradorDao();
	private JButton btnVoltar = new JButton("");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginAdministrador frame = new LoginAdministrador();
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
	public LoginAdministrador() {
		setBounds(100, 100, 378, 356);
		getContentPane().setLayout(null);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		panel = new JPanel();
		panel.setBounds(0, 0, 374, 328);
		getContentPane().add(panel);
		panel.setLayout(null);

		try {
			MaskCpf = new MaskFormatter("###.###.###-##");
		} catch (Exception e) {
			System.out.println("Falha na criação da mascara! \n Erro: " + e.getMessage());
			e.printStackTrace();
		}

		txtCpf = new JFormattedTextField(MaskCpf);
		txtCpf.setBounds(39, 135, 277, 20);
		txtCpf.setOpaque(false);
		txtCpf.setBorder(BorderFactory.createEmptyBorder());
		panel.add(txtCpf);
		txtCpf.setColumns(10);

		txtSenha = new JPasswordField();
		txtSenha.setBounds(39, 195, 299, 20);
		txtSenha.setOpaque(false);
		txtSenha.setBorder(BorderFactory.createEmptyBorder());
		panel.add(txtSenha);
		txtSenha.setColumns(10);

		btnLogin = new JButton("");
		btnLogin.setIcon(new ImageIcon(LoginAdministrador.class.getResource("/Botoes/btnLogin.png")));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				login();
			}
		});
		btnLogin.setContentAreaFilled(false);
		btnLogin.setBounds(132, 226, 90, 36);
		panel.add(btnLogin);
		btnVoltar.setIcon(new ImageIcon(LoginAdministrador.class.getResource("/Botoes/btnVoltar.png")));

		btnVoltar.setBounds(274, 281, 90, 36);
		btnVoltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Voltar();
			}
		});
		btnVoltar.setContentAreaFilled(false);
		panel.add(btnVoltar);

		lblImagemFundo = new JLabel("");
		lblImagemFundo.setIcon(new ImageIcon(LoginAdministrador.class.getResource("/Imagens/LoginProprietario.png")));
		lblImagemFundo.setBounds(0, 0, 374, 328);
		panel.add(lblImagemFundo);
	}

	public void login() {

		String senha = String.valueOf(txtSenha.getPassword());

		if (!(txtCpf.getText().isEmpty() || senha.isEmpty())) {
			alb.setUsuario_adm(txtCpf.getText());
			alb.setSenha_adm(senha);

			if (AdministradorDao.checkLogin(alb)) {
				PrincipalCentroDeColeta();
				this.dispose();
			} else {
				JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos!", "Atenção!",
						JOptionPane.WARNING_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Preencha os campos para efetuar o login!", "Atenção!", JOptionPane.WARNING_MESSAGE);
		}
	}

	private void Voltar() {
		OpcaoDeLogin opcaoLogin = new OpcaoDeLogin();
		opcaoLogin.setVisible(true);
		this.dispose();
	}

	private void PrincipalCentroDeColeta() {
		PrincipalCentroColeta Principal = new PrincipalCentroColeta();
		Principal.setVisible(true);

	}

}
