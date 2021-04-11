package TelasPerfil;

import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Beans.ClienteBean;
import Dao.ClienteDao;
import Principal.JanelaPrincipal;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

public class PerfilCliente extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtId;
	private JTextField txtNomeCliente;
	private JTextField txtCNPJ;
	private JTextField txtCepCliente;
	private JTextField txtTelefoneCliente;
	private JTextField txtEmailCliente;
	private JTextField txtUf;
	private JTextField txtCidade;

	private ClienteDao clienteDao = new ClienteDao();
	private ClienteBean ClienteBean = new ClienteBean();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PerfilCliente frame = new PerfilCliente();
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
	// Construtor q preenche os dados do cliente
	public PerfilCliente(ClienteBean ClienteBean) {
		this();
		this.ClienteBean = ClienteBean;
		this.txtId.setText(Integer.toString(ClienteBean.getId_Cliente()));
		this.txtNomeCliente.setText(ClienteBean.getNome_Cliente());
		this.txtCNPJ.setText(ClienteBean.getCnpj_Cliente());
		this.txtCepCliente.setText(ClienteBean.getCep_Cliente());
		this.txtTelefoneCliente.setText(ClienteBean.getTelefone_Cliente());
		this.txtEmailCliente.setText(ClienteBean.getEmail_Cliente());
		this.txtUf.setText(ClienteBean.getUf_Cliente());
		this.txtCidade.setText(ClienteBean.getCidade_Cliente());
	}

	public PerfilCliente() {
		setBounds(100, 100, 1012, 705);
		getContentPane().setLayout(null);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1006, 676);
		getContentPane().add(panel);
		panel.setLayout(null);

		txtId = new JTextField();
		txtId.setBounds(251, 191, 86, 20);
		txtId.setVisible(false);
		txtId.setEnabled(false);
		panel.add(txtId);
		txtId.setColumns(10);

		txtNomeCliente = new JTextField();
		txtNomeCliente.setBounds(351, 188, 297, 27);
		txtNomeCliente.setColumns(10);
		txtNomeCliente.setOpaque(false);
		txtNomeCliente.setBorder(BorderFactory.createEmptyBorder());
		panel.add(txtNomeCliente);

		txtCNPJ = new JTextField();
		txtCNPJ.setBounds(351, 263, 297, 27);
		txtCNPJ.setOpaque(false);
		txtCNPJ.setBorder(BorderFactory.createEmptyBorder());
		txtCNPJ.setColumns(10);
		panel.add(txtCNPJ);

		txtTelefoneCliente = new JTextField();
		txtTelefoneCliente.setBounds(509, 427, 139, 27);
		txtTelefoneCliente.setOpaque(false);
		txtTelefoneCliente.setBorder(BorderFactory.createEmptyBorder());
		txtTelefoneCliente.setColumns(10);
		panel.add(txtTelefoneCliente);

		txtCepCliente = new JTextField();
		txtCepCliente.setBounds(351, 427, 122, 27);
		txtCepCliente.setOpaque(false);
		txtCepCliente.setBorder(BorderFactory.createEmptyBorder());
		txtCepCliente.setColumns(10);
		panel.add(txtCepCliente);

		txtEmailCliente = new JTextField();
		txtEmailCliente.setBounds(351, 499, 297, 27);
		txtEmailCliente.setOpaque(false);
		txtEmailCliente.setBorder(BorderFactory.createEmptyBorder());
		txtEmailCliente.setColumns(10);
		panel.add(txtEmailCliente);

		JButton btnAlterarDados = new JButton("");
		btnAlterarDados.setIcon(new ImageIcon(PerfilCliente.class.getResource("/Botoes/btnAlterarDados.png")));
		btnAlterarDados.setBounds(426, 548, 164, 41);
		btnAlterarDados.setContentAreaFilled(false);
		panel.add(btnAlterarDados);

		JButton btnVoltar = new JButton("");
		btnVoltar.setIcon(new ImageIcon(PerfilCliente.class.getResource("/Botoes/btnVoltar.png")));
		btnVoltar.setBounds(887, 627, 91, 38);
		btnVoltar.setContentAreaFilled(false);
		panel.add(btnVoltar);

		JButton btnImagemPerfil = new JButton("");
		btnImagemPerfil.setEnabled(false);
		btnImagemPerfil.setVisible(false);
		btnImagemPerfil.setBounds(911, 182, 182, 157);
		btnImagemPerfil.setContentAreaFilled(false);
		panel.add(btnImagemPerfil);

		txtUf = new JTextField();
		txtUf.setBounds(351, 346, 113, 27);
		txtUf.setOpaque(false);
		txtUf.setBorder(BorderFactory.createEmptyBorder());
		panel.add(txtUf);
		txtUf.setColumns(10);

		txtCidade = new JTextField();
		txtCidade.setBounds(509, 346, 139, 27);
		txtCidade.setOpaque(false);
		txtCidade.setBorder(BorderFactory.createEmptyBorder());
		panel.add(txtCidade);
		txtCidade.setColumns(10);

		JLabel lblImagemPerfil = new JLabel("");
		lblImagemPerfil.setBounds(911, 182, 182, 157);
		panel.add(lblImagemPerfil);

		JLabel lblImagem = new JLabel("");
		lblImagem.setIcon(new ImageIcon(PerfilCliente.class.getResource("/Imagens/PerfilCliente.jpg")));
		lblImagem.setBounds(0, 0, 1006, 673);
		panel.add(lblImagem);

		// -------------------------------------------------------------------------------------------------------//

		btnAlterarDados.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AlterarDados();
			}
		});
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Voltar();
			}
		});
	}

	private void AlterarDados() {
		ClienteBean.setNome_Cliente(txtNomeCliente.getText());
		ClienteBean.setCnpj_Cliente(txtCNPJ.getText());
		ClienteBean.setUf_Cliente(txtUf.getText());
		ClienteBean.setCidade_Cliente(txtCidade.getText());
		ClienteBean.setCep_Cliente(txtCepCliente.getText());
		ClienteBean.setEmail_Cliente(txtEmailCliente.getText());
		ClienteBean.setTelefone_Cliente(txtTelefoneCliente.getText());

		Object[] botoes = { "Sim", "Não" };
		int resposta = JOptionPane.showOptionDialog(null, "Concluir alteração? ", "Confirmação",
				JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, botoes, botoes[0]);
		if (resposta == 0) {
			clienteDao.AlterarDadosCliente(ClienteBean);
			JOptionPane.showMessageDialog(null, "Alterado com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private void Voltar() {
		JanelaPrincipal janelaPrincipal = new JanelaPrincipal("Cliente", txtCNPJ.getText());
		janelaPrincipal.setVisible(true);
		this.dispose();
	}
}
