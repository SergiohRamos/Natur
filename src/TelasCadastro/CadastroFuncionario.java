package TelasCadastro;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import Beans.FuncionarioBean;
import Dao.FuncionarioDao;
import Email.Email;
import Principal.PrincipalCentroColeta;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

public class CadastroFuncionario extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNomeFuncionario;
	private JFormattedTextField txtCpfFuncionario;
	private JTextField txtCidade;
	private JFormattedTextField txtCep;
	private JPasswordField txtSenhaFuncionario;
	private JPasswordField txtConfirmarSenha;
	private JTextField txtEmail;
	private JFormattedTextField txtTelefone;
	private MaskFormatter MaskCpf;
	private MaskFormatter MaskCep;
	private MaskFormatter MaskTelefone;
	private JComboBox<Object> cmbEstado;

	private FuncionarioDao FuncionarioDao = new FuncionarioDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroFuncionario frame = new CadastroFuncionario();
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
	public CadastroFuncionario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1012, 705);
		this.setResizable(false);
		this.setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtNomeFuncionario = new JTextField();
		txtNomeFuncionario.setOpaque(false);
		txtNomeFuncionario.setBorder(BorderFactory.createEmptyBorder());
		txtNomeFuncionario.setBounds(287, 101, 242, 30);
		contentPane.add(txtNomeFuncionario);
		txtNomeFuncionario.setColumns(10);

		try {
			MaskCpf = new MaskFormatter("###.###.###-##");
			MaskCep = new MaskFormatter("#####-###");
			MaskTelefone = new MaskFormatter("(##)#####-####");
		} catch (Exception e) {
			System.out.println("Falha ao criar a mascara! \n Erro: " + e.getMessage());
			e.printStackTrace();
		}

		txtCpfFuncionario = new JFormattedTextField(MaskCpf);
		txtCpfFuncionario.setBounds(287, 173, 160, 30);
		txtCpfFuncionario.setOpaque(false);
		txtCpfFuncionario.setBorder(BorderFactory.createEmptyBorder());
		contentPane.add(txtCpfFuncionario);
		txtCpfFuncionario.setColumns(10);

		txtCidade = new JTextField();
		txtCidade.setBounds(470, 256, 195, 34);
		txtCidade.setOpaque(false);
		txtCidade.setBorder(BorderFactory.createEmptyBorder());
		contentPane.add(txtCidade);
		txtCidade.setColumns(10);

		txtCep = new JFormattedTextField(MaskCep);
		txtCep.setOpaque(false);
		txtCep.setBorder(BorderFactory.createEmptyBorder());
		txtCep.setBounds(287, 340, 160, 30);
		contentPane.add(txtCep);
		txtCep.setColumns(10);

		txtSenhaFuncionario = new JPasswordField();
		txtSenhaFuncionario.setOpaque(false);
		txtSenhaFuncionario.setBorder(BorderFactory.createEmptyBorder());
		txtSenhaFuncionario.setBounds(287, 507, 242, 30);
		contentPane.add(txtSenhaFuncionario);
		txtSenhaFuncionario.setColumns(10);

		txtConfirmarSenha = new JPasswordField();
		txtConfirmarSenha.setOpaque(false);
		txtConfirmarSenha.setBorder(BorderFactory.createEmptyBorder());
		txtConfirmarSenha.setBounds(551, 507, 234, 30);
		contentPane.add(txtConfirmarSenha);
		txtConfirmarSenha.setColumns(10);

		JButton btnCadastrar = new JButton("");
		btnCadastrar.setIcon(new ImageIcon(CadastroFuncionario.class.getResource("/Botoes/btnCadastrar.png")));
		btnCadastrar.setBounds(374, 583, 152, 48);
		contentPane.add(btnCadastrar);

		JButton btnVoltar = new JButton("");
		btnVoltar.setIcon(new ImageIcon(CadastroFuncionario.class.getResource("/Botoes/btnVoltar2.png")));
		btnVoltar.setBounds(551, 583, 131, 48);
		contentPane.add(btnVoltar);

		txtEmail = new JTextField();
		txtEmail.setOpaque(false);
		txtEmail.setBorder(BorderFactory.createEmptyBorder());
		txtEmail.setBounds(287, 423, 242, 30);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);

		txtTelefone = new JFormattedTextField(MaskTelefone);
		txtTelefone.setOpaque(false);
		txtTelefone.setBorder(BorderFactory.createEmptyBorder());
		txtTelefone.setBounds(551, 423, 234, 30);
		contentPane.add(txtTelefone);
		txtTelefone.setColumns(10);

		cmbEstado = new JComboBox<Object>(new Object[] { "Acre (AC)", "Alagoas (AL)", "Amapá (AP)", "Amazonas (AM)",
				"Bahia (BA)", "Ceará (CE)", "Distrito Federal (DF)", "Espirito Santo (ES)", "Goiás (GO)",
				"Maranhão (MA)", "Mato Grosso (MT)", "Mato Grosso do Sul (MS)", "Minas Gerais (MG)", "Pará (PA)",
				"Paraíba (PB)", "Paraná (PR)", "Pernambuco (PE)", "Piauí (PI)", "Rio de Janeiro (RJ)",
				"Rio Grande do Norte (RN)", "Rio Grande do Sul (RS)", "Rondônia (RO)", "Roraima (RR)",
				"Santa Catarina (SC)", "São Paulo (SP)", "Sergipe (SE)", "Tocantins (TO)" });
		
		cmbEstado.setBounds(287, 255, 168, 36);
		cmbEstado.setOpaque(false);
		cmbEstado.setBorder(BorderFactory.createEmptyBorder());
		contentPane.add(cmbEstado);
		
		JLabel lblTela = new JLabel("");
		lblTela.setIcon(new ImageIcon(CadastroFuncionario.class.getResource("/Imagens/CadastroFornecedor.png")));
		lblTela.setBounds(0, 0, 1012, 676);
		contentPane.add(lblTela);

		btnCadastrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CadastrarFuncionario();
			}
		});
		btnVoltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Voltar();
			}
		});
	}

	private void CadastrarFuncionario() {
		FuncionarioBean Funcionario = new FuncionarioBean();
		String Senha = String.valueOf(txtSenhaFuncionario.getPassword());
		String ConfirmarSenha = String.valueOf(txtConfirmarSenha.getPassword());
		boolean ValidarEmail = Email.validarEmail(txtEmail.getText());

		if (!(txtNomeFuncionario.getText().isEmpty() || txtCpfFuncionario.getText().isEmpty()
				|| txtCep.getText().isEmpty() || txtCidade.getText().isEmpty() || txtEmail.getText().isEmpty()
				|| Senha.isEmpty() || ConfirmarSenha.isEmpty())) {
			Funcionario.setNome_Funcionario(txtNomeFuncionario.getText());
			Funcionario.setCpf_Funcionario(txtCpfFuncionario.getText());
			Funcionario.setUf_Funcionario(cmbEstado.getName());
			Funcionario.setCidade_Funcionario(txtCidade.getText());
			Funcionario.setCep_Funcionario(txtCep.getText());
			Funcionario.setEmail_Funcionario(txtEmail.getText());
			Funcionario.setTelefone_Funcionario(txtTelefone.getText());
			Funcionario.setSenha_Funcionario(Senha);

			if (Senha.equals(ConfirmarSenha)) {
				if (ValidarEmail) {
					if (FuncionarioDao.VerificaCadastroExistente(Funcionario)) {
						JOptionPane.showMessageDialog(null, "Esse cpf ja está cadastrado!", "Atenção!",
								JOptionPane.ERROR_MESSAGE);
					} else {
						FuncionarioDao.CadastrarFuncionario(Funcionario);
						JOptionPane.showMessageDialog(null, "Funcionário cadastrado com sucesso!", "Aviso!",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "E-mail inválido!", "Atenção!", JOptionPane.ERROR_MESSAGE);
				}

			} else {
				JOptionPane.showMessageDialog(null, "Senhas não coincidem!", "Erro!", JOptionPane.ERROR_MESSAGE);
				txtSenhaFuncionario.setText("");
				txtConfirmarSenha.setText("");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios!", "Aviso!",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	private void Voltar() {
		PrincipalCentroColeta Principal = new PrincipalCentroColeta();
		Principal.setVisible(true);
		this.dispose();
	}

}
