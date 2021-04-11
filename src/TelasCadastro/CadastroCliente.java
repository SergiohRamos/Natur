package TelasCadastro;

import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import Beans.ClienteBean;
import Dao.ClienteDao;
import Email.Email;
import TelaLogin.LoginCliente;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;

public class CadastroCliente extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtNomeCliente;
	private JFormattedTextField txtCNPJ;
	private JTextField txtCidade;
	private JFormattedTextField txtCEPCliente;
	private JTextField txtEmail;
	private JFormattedTextField txtTelefoneCliente;
	private ClienteBean ClienteBean = new ClienteBean();
	private ClienteDao ClienteDao = new ClienteDao();
	private JPasswordField txtSenha;
	private JPasswordField txtConfirmarSenha;
	private JButton btnVoltar = new JButton("");
	private JComboBox<Object> cmbEstado;
	private MaskFormatter CNPJMask;
	private MaskFormatter CEPMask;
	private MaskFormatter TelefoneMask;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroCliente frame = new CadastroCliente();
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
	public CadastroCliente() {
		setBounds(100, 100, 1012, 705);
		getContentPane().setLayout(null);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1304, 741);
		getContentPane().add(panel);
		panel.setLayout(null);

		try {
			CNPJMask = new MaskFormatter("##.###.###/####-##");
			CEPMask = new MaskFormatter("#####-###");
			TelefoneMask = new MaskFormatter("(##)#####-####");
		} catch (Exception e) {
			System.out.println("Erro ao criar a mascara! \n Erro: " + e.getMessage());
		}
		txtNomeCliente = new JTextField();
		txtNomeCliente.setBounds(289, 112, 242, 29);
		txtNomeCliente.setOpaque(false);
		txtNomeCliente.setBorder(BorderFactory.createEmptyBorder());
		panel.add(txtNomeCliente);
		txtNomeCliente.setColumns(10);

		txtCNPJ = new JFormattedTextField(CNPJMask);
		txtCNPJ.setBounds(289, 187, 159, 29);
		txtCNPJ.setOpaque(false);
		txtCNPJ.setBorder(BorderFactory.createEmptyBorder());
		panel.add(txtCNPJ);
		txtCNPJ.setColumns(10);

		txtCidade = new JTextField();
		txtCidade.setBounds(469, 274, 187, 29);
		txtCidade.setOpaque(false);
		txtCidade.setBorder(BorderFactory.createEmptyBorder());
		panel.add(txtCidade);
		txtCidade.setColumns(10);

		txtCEPCliente = new JFormattedTextField(CEPMask);
		txtCEPCliente.setBounds(289, 354, 159, 29);
		txtCEPCliente.setOpaque(false);
		txtCEPCliente.setBorder(BorderFactory.createEmptyBorder());
		panel.add(txtCEPCliente);
		txtCEPCliente.setColumns(10);

		txtEmail = new JTextField();
		txtEmail.setBounds(289, 437, 242, 29);
		txtEmail.setOpaque(false);
		txtEmail.setBorder(BorderFactory.createEmptyBorder());
		panel.add(txtEmail);
		txtEmail.setColumns(10);

		txtTelefoneCliente = new JFormattedTextField(TelefoneMask);
		txtTelefoneCliente.setBounds(548, 437, 236, 29);
		txtTelefoneCliente.setOpaque(false);
		txtTelefoneCliente.setBorder(BorderFactory.createEmptyBorder());
		panel.add(txtTelefoneCliente);
		txtTelefoneCliente.setColumns(10);

		txtSenha = new JPasswordField();
		txtSenha.setBounds(289, 518, 242, 34);
		txtSenha.setOpaque(false);
		txtSenha.setBorder(BorderFactory.createEmptyBorder());
		panel.add(txtSenha);
		txtSenha.setColumns(10);

		txtConfirmarSenha = new JPasswordField();
		txtConfirmarSenha.setBounds(548, 521, 236, 29);
		txtConfirmarSenha.setOpaque(false);
		txtConfirmarSenha.setBorder(BorderFactory.createEmptyBorder());
		panel.add(txtConfirmarSenha);
		txtConfirmarSenha.setColumns(10);

		JButton btnCadastrar = new JButton("");
		btnCadastrar.setIcon(new ImageIcon(CadastroCliente.class.getResource("/Botoes/btnCadastrar.png")));
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastrarCliente();
			}
		});
		btnCadastrar.setContentAreaFilled(false);
		btnCadastrar.setBounds(328, 601, 149, 47);
		panel.add(btnCadastrar);
		btnVoltar.setIcon(new ImageIcon(CadastroCliente.class.getResource("/Botoes/btnVoltar2.png")));

		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RetornarOpcaoCadastro();
			}
		});
		btnVoltar.setContentAreaFilled(false);
		btnVoltar.setBounds(505, 601, 133, 48);
		panel.add(btnVoltar);

		cmbEstado = new JComboBox<Object>(new Object[] { "Acre (AC)", "Alagoas (AL)", "Amapá (AP)", "Amazonas (AM)",
				"Bahia (BA)", "Ceará (CE)", "Distrito Federal (DF)", "Espirito Santo (ES)", "Goiás (GO)",
				"Maranhão (MA)", "Mato Grosso (MT)", "Mato Grosso do Sul (MS)", "Minas Gerais (MG)", "Pará (PA)",
				"Paraíba (PB)", "Paraná (PR)", "Pernambuco (PE)", "Piauí (PI)", "Rio de Janeiro (RJ)",
				"Rio Grande do Norte (RN)", "Rio Grande do Sul (RS)", "Rondônia (RO)", "Roraima (RR)",
				"Santa Catarina (SC)", "São Paulo (SP)", "Sergipe (SE)", "Tocantins (TO)" });
		
		cmbEstado.setBounds(289, 271, 159, 34);
		cmbEstado.setBorder(BorderFactory.createEmptyBorder());
		panel.add(cmbEstado);

		JLabel lblImagemTela = new JLabel("");
		lblImagemTela.setIcon(new ImageIcon(CadastroCliente.class.getResource("/Imagens/CadastroCliente.png")));
		lblImagemTela.setBounds(0, 0, 1012, 705);
		panel.add(lblImagemTela);
	}

	private void CadastrarCliente() {

		String Senha = String.valueOf(txtSenha.getPassword());
		String ConfirmarSenha = String.valueOf(txtConfirmarSenha.getPassword());

		ClienteBean.setNome_Cliente(txtNomeCliente.getText());
		ClienteBean.setCnpj_Cliente(txtCNPJ.getText());
		ClienteBean.setSenha_Cliente(Senha);
		ClienteBean.setUf_Cliente((String) cmbEstado.getSelectedItem());
		ClienteBean.setCidade_Cliente(txtCidade.getText());
		ClienteBean.setCep_Cliente(txtCEPCliente.getText());
		ClienteBean.setEmail_Cliente(txtEmail.getText());
		ClienteBean.setTelefone_Cliente(txtTelefoneCliente.getText());

		if (txtNomeCliente.getText().isEmpty() || txtCNPJ.getText().isEmpty() || txtCidade.getText().isEmpty()
				|| txtEmail.getText().isEmpty() || Senha.isEmpty() || ConfirmarSenha.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Por favor, preencha os campos obrigatórios!", "Aviso!",
					JOptionPane.WARNING_MESSAGE);
		} else {
			if (!(Senha.equals(ConfirmarSenha))) {
				JOptionPane.showMessageDialog(null, "Senha inválida!", "Atenção", JOptionPane.ERROR_MESSAGE);
				txtSenha.setText("");
				txtConfirmarSenha.setText("");
			} else {
				boolean ValidarEmail = Email.validarEmail(txtEmail.getText());
				if(ValidarEmail){
					if (ClienteDao.checkCNPJ(ClienteBean)) {
						ClienteDao.CadastrarCliente(ClienteBean);
						JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!", "Aviso", JOptionPane.PLAIN_MESSAGE);
	
						LoginCliente Login = new LoginCliente();
						Login.setVisible(true);
						this.dispose();
						
					} else {
						JOptionPane.showMessageDialog(null, "CNPJ já cadastrado!", "Atenção", JOptionPane.ERROR_MESSAGE);
					}
				}else{
					JOptionPane.showMessageDialog(null, "E-mail inválido!", "Atenção!", JOptionPane.WARNING_MESSAGE);
				}	
			}
		}
	}
	private void RetornarOpcaoCadastro() {
		this.dispose();
		OpcaoCadastro opcao = new OpcaoCadastro();
		opcao.setVisible(true);
	}
}
