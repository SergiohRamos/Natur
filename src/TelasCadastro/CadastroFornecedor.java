package TelasCadastro;

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

import Beans.FornecedorBeans;
import Dao.CadastrarDao;
import Email.Email;
import TelaLogin.LoginFornecedor;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CadastroFornecedor extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNomeFornecedor;
	private JFormattedTextField txtCEP;
	private JPasswordField txtSenha;
	private JPasswordField txtConfirmaSenha;
	private JTextField txtEmail;
	private JFormattedTextField txtCPF;
	private JFormattedTextField txtTelefone;
	private MaskFormatter MaskCPF;
	private MaskFormatter MaskCEP;
	private MaskFormatter MaskTelefone;
	private JTextField txtCidade;
	private JComboBox<Object> cmbEstado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroFornecedor frame = new CadastroFornecedor();
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
	public CadastroFornecedor() {
		setFont(null);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		setTitle("Cadastro Fornecedor \r\n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1008, 705);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		try {
			MaskCPF = new MaskFormatter("###.###.###-##");
			MaskCEP = new MaskFormatter("#####-###");
			MaskTelefone = new MaskFormatter("(##)#####-####");
		} catch (Exception e) {
			e.printStackTrace();
		}
		contentPane.setLayout(null);

		txtNomeFornecedor = new JTextField();
		txtNomeFornecedor.setBounds(296, 113, 230, 32);
		contentPane.add(txtNomeFornecedor);
		txtNomeFornecedor.setColumns(10);
		txtNomeFornecedor.setOpaque(false);
		txtNomeFornecedor.setBorder(BorderFactory.createEmptyBorder());

		txtCPF = new JFormattedTextField(MaskCPF);
		txtCPF.setBounds(296, 185, 147, 32);
		contentPane.add(txtCPF);
		txtCPF.setColumns(10);
		txtCPF.setOpaque(false);
		txtCPF.setBorder(BorderFactory.createEmptyBorder());

		txtCEP = new JFormattedTextField(MaskCEP);
		txtCEP.setBounds(296, 353, 153, 32);
		contentPane.add(txtCEP);
		txtCEP.setColumns(10);
		txtCEP.setOpaque(false);
		txtCEP.setBorder(BorderFactory.createEmptyBorder());

		txtTelefone = new JFormattedTextField(MaskTelefone);
		txtTelefone.setBounds(550, 435, 230, 32);
		contentPane.add(txtTelefone);
		txtTelefone.setColumns(10);
		txtTelefone.setOpaque(false);
		txtTelefone.setBorder(BorderFactory.createEmptyBorder());

		JButton btnCadastrar = new JButton("");
		btnCadastrar.setIcon(new ImageIcon(CadastroFornecedor.class.getResource("/Botoes/btnCadastrar.png")));
		btnCadastrar.setBackground(Color.WHITE);
		btnCadastrar.setBounds(400, 578, 147, 45);
		contentPane.add(btnCadastrar);

		JButton btnVoltar = new JButton("");
		btnVoltar.setIcon(new ImageIcon(CadastroFornecedor.class.getResource("/Botoes/btnVoltar2.png")));
		btnVoltar.setBounds(557, 578, 131, 45);
		contentPane.add(btnVoltar);

		txtSenha = new JPasswordField();
		txtSenha.setBounds(296, 521, 230, 32);
		txtSenha.setOpaque(false);
		txtSenha.setBorder(BorderFactory.createEmptyBorder());
		contentPane.add(txtSenha);

		txtConfirmaSenha = new JPasswordField();
		txtConfirmaSenha.setBounds(550, 521, 230, 32);
		txtConfirmaSenha.setOpaque(false);
		txtConfirmaSenha.setBorder(BorderFactory.createEmptyBorder());
		contentPane.add(txtConfirmaSenha);

		txtCidade = new JTextField();
		txtCidade.setBounds(472, 270, 190, 32);
		txtCidade.setOpaque(false);
		txtCidade.setBorder(BorderFactory.createEmptyBorder());
		contentPane.add(txtCidade);
		txtCidade.setColumns(10);

		txtEmail = new JTextField();
		txtEmail.setBounds(296, 435, 230, 32);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		txtEmail.setOpaque(false);
		txtEmail.setBorder(BorderFactory.createEmptyBorder());

		cmbEstado = new JComboBox<Object>(new Object[] { "Acre (AC)", "Alagoas (AL)", "Amap� (AP)", "Amazonas (AM)",
				"Bahia (BA)", "Cear� (CE)", "Distrito Federal (DF)", "Espirito Santo (ES)", "Goi�s (GO)",
				"Maranh�o (MA)", "Mato Grosso (MT)", "Mato Grosso do Sul (MS)", "Minas Gerais (MG)", "Par� (PA)",
				"Para�ba (PB)", "Paran� (PR)", "Pernambuco (PE)", "Piau� (PI)", "Rio de Janeiro (RJ)",
				"Rio Grande do Norte (RN)", "Rio Grande do Sul (RS)", "Rond�nia (RO)", "Roraima (RR)",
				"Santa Catarina (SC)", "S�o Paulo (SP)", "Sergipe (SE)", "Tocantins (TO)" });
		cmbEstado.setBounds(288, 268, 163, 37);
		cmbEstado.setOpaque(false);
		cmbEstado.setBorder(BorderFactory.createEmptyBorder());
		contentPane.add(cmbEstado);

		JLabel lblTela = new JLabel("");
		lblTela.setIcon(
				new ImageIcon(CadastroFornecedor.class.getResource("/Imagens/CadastroFornecedor.png")));
		lblTela.setBounds(0, 0, 1008, 705);
		contentPane.add(lblTela);

		// -------------------------------------------------------------------------------
		btnCadastrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnCadastrar.setBackground(new Color(210, 105, 30));
				btnCadastrar.setForeground(new Color(205, 133, 63));

			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnCadastrar.setBackground(new Color(210, 105, 30));
				btnCadastrar.setForeground(Color.WHITE);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				btnCadastrar.setBackground(Color.BLACK);
				btnCadastrar.setForeground(Color.BLACK);
			}
		});
		btnVoltar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnVoltar.setBackground(new Color(210, 105, 30));
				btnVoltar.setForeground(new Color(205, 133, 63));
			}

			public void mouseExited(MouseEvent e) {
				btnVoltar.setBackground(new Color(210, 105, 30));
				btnVoltar.setForeground(Color.WHITE);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				btnVoltar.setBackground(Color.BLACK);
				btnVoltar.setForeground(Color.BLACK);
			}
		});
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastrasFornecedor();
			}
		});
		btnVoltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LimparCampos();
			}
		});

	}

	public void CadastrasFornecedor() {
		FornecedorBeans fornecedorBean = new FornecedorBeans();

		String Senha = String.valueOf(txtSenha.getPassword());
		String ConfirmarSenha = String.valueOf(txtConfirmaSenha.getPassword());
		boolean ValidaEmail = Email.validarEmail(txtEmail.getText());

		if (!(txtNomeFornecedor.getText().isEmpty() || txtCPF.getText().isEmpty() || txtEmail.getText().isEmpty()
				|| txtCidade.getText().isEmpty() || txtCEP.getText().isEmpty())) {
			if (ValidaEmail) {
				fornecedorBean.setNome(txtNomeFornecedor.getText());
				fornecedorBean.setCPF(txtCPF.getText());
				fornecedorBean.setUF((String) cmbEstado.getSelectedItem());
				fornecedorBean.setCidade(txtCidade.getText());
				fornecedorBean.setCep(txtCEP.getText());
				fornecedorBean.setEmail(txtEmail.getText());
				fornecedorBean.setTelefone(txtTelefone.getText());
				fornecedorBean.setSenha(Senha);
				if (!(Senha.equals(ConfirmarSenha))) {
					JOptionPane.showMessageDialog(null, "Senha inv�lida!", "Aten��o", JOptionPane.ERROR_MESSAGE);
					txtSenha.setText(null);
					txtConfirmaSenha.setText(null);

				} else if (Senha.equals(ConfirmarSenha)) {
					CadastrarDao cd = new CadastrarDao();
					if (cd.checkCPF(fornecedorBean)) {
						cd.cadastrar(fornecedorBean);
						JOptionPane.showMessageDialog(null, "Cadastro Efetuado Com Sucesso! ", "Aten��o",
								JOptionPane.PLAIN_MESSAGE);
						dispose();
						LoginFornecedor login = new LoginFornecedor();
						login.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "CPF ja cadastrado.", "Aten��o",
								JOptionPane.WARNING_MESSAGE);
					}
				}

			} else {
				JOptionPane.showMessageDialog(null, "E-mail inv�lido! Por favor, preencha com um email v�lido.",
						"Erro!", JOptionPane.WARNING_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Por favor, preencha os campos obritat�rios!");
		}
	}

	public void LimparCampos() {
		txtNomeFornecedor.setText("");
		txtCEP.setText("");
		txtCPF.setText("");
		txtCidade.setText("");
		txtTelefone.setText("");
		txtConfirmaSenha.setText("");
		txtEmail.setText("");
		txtSenha.setText("");
		dispose();
		OpcaoCadastro Opcao = new OpcaoCadastro();
		Opcao.setVisible(true);
	}
}
