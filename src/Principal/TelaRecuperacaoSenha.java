package Principal;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import Dao.PerfilFornecedorDao;
import Email.Email;
import TelaLogin.LoginFornecedor;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

public class TelaRecuperacaoSenha extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JPanel panel = new JPanel();
	private JTextField txtCampo;
	private JFormattedTextField txtCpf;
	private JPasswordField txtSenha;
	private JPasswordField txtConfirmaSenha;
	JLabel lblCampo1 = new JLabel();
	JLabel lblCampo2 = new JLabel();
	JLabel lblCampo3 = new JLabel();
	MaskFormatter MaskCpf = new MaskFormatter();
	private JButton btnEnviar = new JButton();
	public JButton btnVoltar = new JButton("");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaRecuperacaoSenha frame = new TelaRecuperacaoSenha();
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
	/**
	 * 
	 */
	public TelaRecuperacaoSenha() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 515, 373);
		this.setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel.setBounds(0, 0, 500, 334);
		contentPane.add(panel);
		panel.setLayout(null);
		lblCampo1.setBounds(191, 94, 113, 14);
		panel.add(lblCampo1);

		txtCampo = new JTextField();
		txtCampo.setBounds(56, 144, 391, 26);
		txtCampo.setOpaque(false);
		txtCampo.setBorder(BorderFactory.createEmptyBorder());
		panel.add(txtCampo);
		txtCampo.setColumns(10);
		btnEnviar.setIcon(new ImageIcon(TelaRecuperacaoSenha.class.getResource("/Botoes/EnviarSolicitacao.png")));
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RecuperarConta();
			}
		});
		btnEnviar.setContentAreaFilled(false);
		btnEnviar.setBounds(99, 205, 148, 36);
		panel.add(btnEnviar);
		btnVoltar.setIcon(new ImageIcon(TelaRecuperacaoSenha.class.getResource("/Botoes/btnVoltar.png")));

		btnVoltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				LoginFornecedor loginFornecedor = new LoginFornecedor();
				loginFornecedor.setVisible(true);
			}
		});
		btnVoltar.setContentAreaFilled(false);
		btnVoltar.setBounds(279, 205, 92, 34);
		panel.add(btnVoltar);

		JLabel lblImagemFundo = new JLabel("");
		lblImagemFundo.setIcon(
				new ImageIcon(TelaRecuperacaoSenha.class.getResource("/Imagens/RecuperarContaFornecedor.png")));
		lblImagemFundo.setBounds(0, 0, 500, 336);
		panel.add(lblImagemFundo);

		try {
			MaskCpf = new MaskFormatter("###.###.###-##");
		} catch (Exception e) {
			System.out.println("Erro na criação da MaskCpf. \n " + e.getMessage());
			e.printStackTrace();
		}
	}

	private void RecuperarConta() {
		int chave = (int) (Math.random() * 100000000); // Variavel que gera o
														// codigo
		PerfilFornecedorDao FornecedorDao = new PerfilFornecedorDao();
		String email = txtCampo.getText();
		boolean status = Email.validarEmail(email); // Metodo que valida e-mail
		if (status) { // Se o e-mail for valido ele faz isso
			if (FornecedorDao.VerificaEmail(txtCampo.getText())) { // Verifica
																	// se o
																	// e-mail
																	// esta
																	// cadastrado
				if (Email.RecuperarConta(chave, email)) { // Envia o e-mail
					JOptionPane.showMessageDialog(null, "Mensagem enviada com sucesso!");
					lblCampo1.setText("Codigo de verificação: "); // Altera o
																	// nome dos
																	// lbls
					btnEnviar.setText("Recuperar Conta");
					for (ActionListener al : btnEnviar.getActionListeners()) { // Remove
																				// a
																				// ação
																				// do
																				// botão
						btnEnviar.removeActionListener(al);
					}
					txtCampo.setText(null); // Apaga o e-mail
					btnVoltar.setText("Cancelar");
					btnEnviar.addActionListener(new ActionListener() { // Add
																		// uma
																		// nova
																		// ação
						@Override
						public void actionPerformed(ActionEvent e) {
							if (txtCampo.getText().equals(Integer.toString(chave))) { // Verifica
																						// se
																						// as
																						// chaves
																						// coincidem
								JOptionPane.showMessageDialog(null, "Chave correta!");
								txtCampo.setText(null);
								txtCampo.setVisible(false);

								lblCampo1.setText("Informe seu CPF:"); // Cria
																		// novos
																		// campos
																		// onde
																		// serão
																		// informados
								lblCampo1.setBounds(10, 42, 101, 14); // Cpf,
																		// Nova
																		// senha
																		// e
																		// confirmar
																		// senha
								txtCpf = new JFormattedTextField(MaskCpf);
								txtCpf.setBounds(141, 39, 234, 20);
								panel.add(txtCpf);
								panel.add(txtCampo);

								lblCampo2.setText("Nova senha: ");
								lblCampo2.setBounds(10, 73, 101, 14);
								txtSenha = new JPasswordField();
								txtSenha.setBounds(141, 70, 234, 20);
								txtSenha.setColumns(10);
								panel.add(lblCampo2);
								panel.add(txtSenha);

								lblCampo3.setText("Confirmar senha:");
								lblCampo3.setBounds(10, 104, 101, 14);
								txtConfirmaSenha = new JPasswordField();
								txtConfirmaSenha.setBounds(141, 101, 234, 20);
								txtConfirmaSenha.setColumns(10);
								panel.add(lblCampo3);
								panel.add(txtConfirmaSenha);

								btnEnviar.setText("Alterar senha"); // Muda o
																	// nome do
																	// botão e
																	// remove
																	// sua ação
																	// (dnv =v)
								btnEnviar.setBounds(67, 201, 128, 23);
								btnVoltar.setBounds(227, 201, 89, 23);
								for (ActionListener al : btnEnviar.getActionListeners()) {
									btnEnviar.removeActionListener(al);
								}
								btnEnviar.addActionListener(new ActionListener() { // Add
																					// a
																					// nova
																					// e
																					// ultima
																					// açao
									@Override
									public void actionPerformed(ActionEvent e) {
										String cpf = txtCpf.getText();
										String senha = String.valueOf(txtSenha.getPassword());
										String confirmaSenha = String.valueOf(txtConfirmaSenha.getPassword());

										if (senha.equals(confirmaSenha)) { // Se
																			// as
																			// senhas
																			// forem
																			// iguais
																			// efetua
																			// a
																			// alteração
											if (FornecedorDao.ConferirCPF_Email(cpf, email)) {
												FornecedorDao.AlterarSenha(cpf, senha);
												JOptionPane.showMessageDialog(null, "Senha alterada com sucesso!");
											} else {
												JOptionPane.showMessageDialog(null,
														"CPF não cadastrado com esse endereço de e-mail!", "Erro ",
														JOptionPane.ERROR_MESSAGE);
											}

										} else {
											JOptionPane.showMessageDialog(null, "Senhas não combinam!", "Atenção",
													JOptionPane.WARNING_MESSAGE);
										}
									}
								});

							} else {
								JOptionPane.showMessageDialog(null, "Chave incorreta!", "Erro!",
										JOptionPane.ERROR_MESSAGE);
							}
						}
					});
				} else {
					JOptionPane.showMessageDialog(null, "Falha ao enviar codigo de verificação!", "Aviso!",
							JOptionPane.WARNING_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Este endereço de email não esta cadastrado!", "Aviso!",
						JOptionPane.WARNING_MESSAGE);

			}
		} else {
			JOptionPane.showMessageDialog(null, "Email inválido", "Erro!", JOptionPane.ERROR_MESSAGE);
		}

	}
}
