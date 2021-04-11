package Principal;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Beans.ClienteBean;
import Beans.FornecedorBean;
import Beans.FuncionarioBean;
import Conexao.Conexao;
import Dao.AdministradorDao;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.ImageIcon;

public class AdmConsultarFornecedores extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtPesquisa;
	private JList<String> list;
	private DefaultListModel<String> ListModel = new DefaultListModel<String>();
	private AdministradorDao AdmDao = new AdministradorDao();
	private List<String> ListaDeNomes = new ArrayList<String>();
	private JScrollPane scrollPane;
	private JTextField txtID;
	private JTextField txtNome;
	private JTextField txtCPF;
	private JTextField txtTelefone;
	private JTextField txtEmail;
	private JTextField txtUf;
	private JTextField txtCidade;
	private JTextField txtCep;

	private FornecedorBean Fornecedor = new FornecedorBean();
	private ClienteBean Cliente = new ClienteBean();
	private FuncionarioBean Funcionario = new FuncionarioBean();

	private JButton btnVoltar;
	private JButton btnBuscar;

	private String Entidade;
	private JLabel lblTela;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdmConsultarFornecedores frame = new AdmConsultarFornecedores();
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

	public AdmConsultarFornecedores(String Entidade) {
		this();
		if (Entidade.equals("Cliente")) {
			lblTela.setIcon(new ImageIcon(AdmConsultarFornecedores.class.getResource("/Imagens/AdmConsultaCliente.png")));
		} else if (Entidade.equals("Fornecedor")) {
			lblTela.setIcon(new ImageIcon(AdmConsultarFornecedores.class.getResource("/Imagens/AdmConsultaFornecedor.png")));
		}else {
			lblTela.setIcon(new ImageIcon(AdmConsultarFornecedores.class.getResource("/Imagens/AdmConsultaFuncionario.png")));
		}
		this.Entidade = Entidade;

	}

	public AdmConsultarFornecedores() {
		setModal(true);
		setForeground(Color.WHITE);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 521, 512);
		this.setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtPesquisa = new JTextField();
		txtPesquisa.setBounds(21, 125, 140, 31);
		txtPesquisa.setOpaque(false);
		txtPesquisa.setBorder(BorderFactory.createEmptyBorder());
		contentPane.add(txtPesquisa);
		txtPesquisa.setColumns(10);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 160, 140, 130);
		contentPane.add(scrollPane);
		scrollPane.setVisible(false);

		list = new JList<String>();
		scrollPane.setViewportView(list);
		list.setModel(ListModel);
		list.setVisible(false);

		btnBuscar = new JButton("");
		btnBuscar.setIcon(new ImageIcon(AdmConsultarFornecedores.class.getResource("/Botoes/BuscarMaterial.png")));
		btnBuscar.setBounds(171, 114, 101, 36);
		btnBuscar.setContentAreaFilled(false);
		btnBuscar.setVisible(false);
		btnBuscar.setEnabled(false);
		contentPane.add(btnBuscar);

		txtID = new JTextField();
		txtID.setEditable(false);
		txtID.setBounds(21, 217, 39, 31);
		txtID.setOpaque(false);
		txtID.setBorder(BorderFactory.createEmptyBorder());
		contentPane.add(txtID);
		txtID.setColumns(10);

		txtNome = new JTextField();
		txtNome.setEditable(false);
		txtNome.setBounds(82, 228, 202, 20);
		txtNome.setOpaque(false);
		txtNome.setBorder(BorderFactory.createEmptyBorder());
		contentPane.add(txtNome);
		txtNome.setColumns(10);

		txtCPF = new JTextField();
		txtCPF.setEditable(false);
		txtCPF.setBounds(319, 228, 126, 20);
		txtCPF.setOpaque(false);
		txtCPF.setBorder(BorderFactory.createEmptyBorder());
		contentPane.add(txtCPF);
		txtCPF.setColumns(10);

		txtTelefone = new JTextField();
		txtTelefone.setEditable(false);
		txtTelefone.setBounds(21, 285, 109, 20);
		txtTelefone.setOpaque(false);
		txtTelefone.setBorder(BorderFactory.createEmptyBorder());
		contentPane.add(txtTelefone);
		txtTelefone.setColumns(10);

		txtEmail = new JTextField();
		txtEmail.setEditable(false);
		txtEmail.setBounds(165, 280, 280, 31);
		txtEmail.setOpaque(false);
		txtEmail.setBorder(BorderFactory.createEmptyBorder());
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);

		txtUf = new JTextField();
		txtUf.setEditable(false);
		txtUf.setBounds(24, 368, 128, 20);
		txtUf.setOpaque(false);
		txtUf.setBorder(BorderFactory.createEmptyBorder());
		contentPane.add(txtUf);
		txtUf.setColumns(10);

		txtCidade = new JTextField();
		txtCidade.setEditable(false);
		txtCidade.setBounds(188, 367, 116, 23);
		txtCidade.setOpaque(false);
		txtCidade.setBorder(BorderFactory.createEmptyBorder());
		contentPane.add(txtCidade);
		txtCidade.setColumns(10);

		txtCep = new JTextField();
		txtCep.setEditable(false);
		txtCep.setBounds(329, 368, 116, 20);
		txtCep.setOpaque(false);
		txtCep.setBorder(BorderFactory.createEmptyBorder());
		contentPane.add(txtCep);
		txtCep.setColumns(10);

		btnVoltar = new JButton("");
		btnVoltar.setIcon(new ImageIcon(AdmConsultarFornecedores.class.getResource("/Botoes/btnVoltar.png")));
		btnVoltar.setContentAreaFilled(false);
		btnVoltar.setBounds(406, 426, 89, 36);
		contentPane.add(btnVoltar);
		
		lblTela = new JLabel("");
		lblTela.setIcon(new ImageIcon(AdmConsultarFornecedores.class.getResource("/Imagens/AdmConsultaCliente.png")));
		lblTela.setBounds(0, 0, 506, 474);
		contentPane.add(lblTela);

		// ----------------------------------------------------------------------------------------------//
		btnBuscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String Nome = txtPesquisa.getText();
				if (ListaDeNomes.size() > 0) {
					ListaDeNomes.clear();
					ListModel.clear();
				}
				BuscarNomes(Nome);
				DefaultListModel<String> Modelo = new DefaultListModel<String>();
				for (int i = 0; i < ListaDeNomes.size(); i++) {
					Modelo.addElement(ListaDeNomes.get(i));

				}
				list.setModel(Modelo);

				list.setVisible(true);
				scrollPane.setVisible(true);
			}
		});
		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				String Valor = list.getSelectedValue();
				txtPesquisa.setText("");
				if (Entidade.equals("Fornecedor")) {
					AdmDao.BuscarDadosFornecedor(Valor, Fornecedor);
				} else if (Entidade.equals("Cliente")){
					AdmDao.BuscarDadosClientes(Valor, Cliente);
				}else if(Entidade.equals("Funcionario")) {
					AdmDao.BuscarDadosFuncionarios(Valor, Funcionario);
				}
				PreencherCampos();
				LimparLista();

				list.setVisible(false);
				scrollPane.setVisible(false);
			}
		});
		txtPesquisa.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				String Nome = txtPesquisa.getText();
				if (ListaDeNomes.size() > 0) {
					ListaDeNomes.clear();
					ListModel.clear();
				}
				if (!(Nome.isEmpty())) {
					if (Entidade.equals("Fornecedor")) {
						if (AdmDao.BuscarFornecedores(Nome, ListaDeNomes)) {

							DefaultListModel<String> Modelo = new DefaultListModel<String>();
							for (int i = 0; i < ListaDeNomes.size(); i++) {
								Modelo.addElement(ListaDeNomes.get(i));
							}
							list.setModel(Modelo);

							list.setVisible(true);
							scrollPane.setVisible(true);
						} else {
							list.setVisible(false);
							scrollPane.setVisible(false);
						}
					} else if (Entidade.equals("Cliente")) {
						if (AdmDao.BuscarClientes(Nome, ListaDeNomes)) {
							DefaultListModel<String> Modelo = new DefaultListModel<String>();
							for (int i = 0; i < ListaDeNomes.size(); i++) {
								Modelo.addElement(ListaDeNomes.get(i));
							}
							list.setModel(Modelo);

							list.setVisible(true);
							scrollPane.setVisible(true);
						} else {
							list.setVisible(false);
							scrollPane.setVisible(false);
						}
					} else if (Entidade.equals("Funcionario")) {
						if (AdmDao.BuscarFuncionarios(Nome, ListaDeNomes)) {
							DefaultListModel<String> Modelo = new DefaultListModel<String>();
							for (int i = 0; i < ListaDeNomes.size(); i++) {
								Modelo.addElement(ListaDeNomes.get(i));
							}
							list.setModel(Modelo);

							list.setVisible(true);
							scrollPane.setVisible(true);
						} else {
							list.setVisible(false);
							scrollPane.setVisible(false);
						}

					}
				} else {
					list.setVisible(false);
					scrollPane.setVisible(false);
				}
			}

		});
		btnVoltar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Voltar();
			}

		});

	}

	private boolean BuscarNomes(String Nome) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT nome_fornecedor FROM fornecedor WHERE nome_fornecedor LIKE ?";
		boolean Verificar = false;
		try {
			conn = Conexao.getConection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + Nome + "%");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ListaDeNomes.add(rs.getString("nome_fornecedor"));
				Verificar = true;
			}
			Conexao.FecharConexao(conn, pstmt, rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Verificar;
	}

	private void BuscarDados(String Nome) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM fornecedor WHERE nome_fornecedor LIKE ?";
		try {
			conn = Conexao.getConection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + Nome + "%");
			rs = pstmt.executeQuery();

			if (rs.next()) {
				Fornecedor.setId_fornecedor(rs.getInt("id_fornecedor"));
				Fornecedor.setNome_fornecedor(rs.getString("nome_fornecedor"));
				Fornecedor.setCpf_fornecedor(rs.getString("cpf_fornecedor"));
				Fornecedor.setCidade_fornecedor(rs.getString("cidade_fornecedor"));
				Fornecedor.setUf_fornecedor(rs.getString("uf_fornecedor"));
				Fornecedor.setCep_fornecedor(rs.getString("cep_fornecedor"));
				Fornecedor.setTele_fornecedor(rs.getString("tele_fornecedor"));
				Fornecedor.setEmail_fornecedor(rs.getString("email_fornecedor"));
			}
			Conexao.FecharConexao(conn, pstmt, rs);
		} catch (Exception e) {
			System.out.println("Erro ao consultar fornecedor! \n Erro: " + e.getMessage());
			e.printStackTrace();
		}

	}

	private void PreencherCampos() {
		if (Entidade.equals("Fornecedor")) {
			if (Fornecedor != null) {
				txtID.setText(Integer.toString(Fornecedor.getId_fornecedor()));
				txtNome.setText(Fornecedor.getNome_fornecedor());
				txtCPF.setText(Fornecedor.getCpf_fornecedor());
				txtTelefone.setText(Fornecedor.getTele_fornecedor());
				txtEmail.setText(Fornecedor.getEmail_fornecedor());
				txtUf.setText(Fornecedor.getUf_fornecedor());
				txtCidade.setText(Fornecedor.getCidade_fornecedor());
				txtCep.setText(Fornecedor.getCep_fornecedor());
			} else {
				System.out.println("Fornecedor vazio!");
			}
		} else if (Entidade.equals("Cliente")) {
			if (Cliente != null) {
				txtID.setText(Integer.toString(Cliente.getId_Cliente()));
				txtNome.setText(Cliente.getNome_Cliente());
				txtCPF.setText(Cliente.getCnpj_Cliente());
				txtTelefone.setText(Cliente.getTelefone_Cliente());
				txtEmail.setText(Cliente.getEmail_Cliente());
				txtUf.setText(Cliente.getUf_Cliente());
				txtCidade.setText(Cliente.getCidade_Cliente());
				txtCep.setText(Cliente.getCep_Cliente());
			} else {
				System.out.println("Cliente está vazio!");
			}
		}else if(Entidade.equals("Funcionario")) {
			if(Funcionario != null) {
				txtID.setText(Integer.toString(Funcionario.getID_funcionario()));
				txtNome.setText(Funcionario.getNome_Funcionario());
				txtCPF.setText(Funcionario.getCpf_Funcionario());
				txtTelefone.setText(Funcionario.getTelefone_Funcionario());
				txtEmail.setText(Funcionario.getEmail_Funcionario());
				txtUf.setText(Funcionario.getUf_Funcionario());
				txtCidade.setText(Funcionario.getCidade_Funcionario());
				txtCep.setText(Funcionario.getCep_Funcionario());
			}else {
				System.out.println("Funcionario vazio!");
			}
		}
	}

	private void LimparLista() {
		DefaultListModel<String> ListModel1 = (DefaultListModel<String>) list.getModel();
		ListModel1.clear();
	}

	private void Voltar() {
		this.dispose();
	}
}
