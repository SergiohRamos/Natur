package Principal;

import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Beans.ClienteBean;
import Beans.FornecedorBean;
import Beans.FuncionarioBean;
import Dao.ClienteDao;
import Dao.FuncionarioDao;
import Dao.PerfilFornecedorDao;
import InformativoMaterial.Informacoes;
import TelaLogin.OpcaoDeLogin;
import TelasCadastro.OpcaoCadastro;
import TelasPerfil.PerfilCliente;
import TelasPerfil.PerfilFornecedor;

public class JanelaPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private FornecedorBean FornecedorBean = new FornecedorBean();
	private ClienteBean ClienteBean = new ClienteBean();
	private OpcaoDeLogin OpcaoLogin = new OpcaoDeLogin();

	private PerfilFornecedorDao PerfilFornecedorDao = new PerfilFornecedorDao();
	private ClienteDao ClienteDao = new ClienteDao();
	private FuncionarioDao FuncionarioDao = new FuncionarioDao();

	private JButton btnLogin;
	private JButton btnPerfil;
	private JButton btnVenda;

	private JButton btnDeslogar = new JButton("");
	private String Entidade = "";
	private String CPF = "";
	private String CNPJ = "";
	private JButton btnHistorico;

	private FuncionarioBean FuncionarioBean = new FuncionarioBean();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JanelaPrincipal frame = new JanelaPrincipal();
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
	// Construtor para o fornecedor
	public JanelaPrincipal(String Entidade, String CPF_CNPJ) {
		this();
		if (Entidade.equals("Fornecedor") || Entidade.equals("Funcionario")) {
			this.CPF = CPF_CNPJ;
			if (Entidade.equals("Fornecedor")) {
				this.btnHistorico.setVisible(true);
				this.btnHistorico.setEnabled(true);
				this.btnHistorico.setBounds(314,256,176,178);
				this.btnLogin.setEnabled(false);
				this.btnLogin.setVisible(false);
				this.btnVenda.setVisible(false);
				this.btnVenda.setEnabled(false);
				this.btnPerfil.setBounds(542, 256, 190, 178);
			} else {
				this.btnHistorico.setVisible(false);
				this.btnHistorico.setEnabled(false);
				this.btnVenda.setIcon(new ImageIcon(JanelaPrincipal.class.getResource("/Botoes/BotaoVenda.png")));
				this.btnVenda.setVisible(true);
				this.btnVenda.setEnabled(true);
				this.btnVenda.setBounds(314, 256, 190, 178);	
				this.btnPerfil.setBounds(542, 256, 190, 178);
			}
		} else if (Entidade.equals("Cliente")) {
			this.CNPJ = CPF_CNPJ;
			this.btnHistorico.setVisible(true);
			this.btnHistorico.setEnabled(true);
			this.btnHistorico.setBounds(179,256,176,178);
			this.btnPerfil.setBounds(407, 256, 176,178);
			this.btnVenda.setIcon(new ImageIcon(JanelaPrincipal.class.getResource("/Botoes/BotaoCompra.png")));
			this.btnVenda.setVisible(true);
			this.btnVenda.setEnabled(true);
			this.btnVenda.setBounds(635, 256, 176, 178);
		}
		this.btnPerfil.setIcon(new ImageIcon(JanelaPrincipal.class.getResource("/Botoes/BotaoPerfil.png")));
		this.Entidade = Entidade;
		this.btnDeslogar.setVisible(true);
		this.btnDeslogar.setEnabled(true);
		this.btnLogin.setVisible(false);
		this.btnLogin.setEnabled(false);
	}

	public JanelaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1012, 705);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.text);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnDeslogar.setIcon(new ImageIcon(JanelaPrincipal.class.getResource("/Botoes/BotaoLogOff.png")));
		btnDeslogar.setBounds(459, 502, 124, 110);
		btnDeslogar.setVisible(false);
		btnDeslogar.setEnabled(false);
		btnDeslogar.setContentAreaFilled(false);
		contentPane.add(btnDeslogar);

		btnLogin = new JButton("");
		btnLogin.setIcon(new ImageIcon(JanelaPrincipal.class.getResource("/Botoes/botaoLogin.png")));
		btnLogin.setContentAreaFilled(false);
		btnLogin.setBounds(314, 256, 190, 178);
		contentPane.add(btnLogin);

		btnPerfil = new JButton("");
		btnPerfil.setIcon(new ImageIcon(JanelaPrincipal.class.getResource("/Botoes/BotaoCadastrar.png")));
		btnPerfil.setContentAreaFilled(false);
		btnPerfil.setBounds(542, 256, 190, 178);
		contentPane.add(btnPerfil);

		btnVenda = new JButton("");
		btnVenda.setIcon(new ImageIcon(JanelaPrincipal.class.getResource("/Botoes/BotaoInformativo.png")));
		btnVenda.setContentAreaFilled(false);
		btnVenda.setVisible(false);
		btnVenda.setBounds(542, 256, 190, 178);
		contentPane.add(btnVenda);

		btnHistorico = new JButton("");
		btnHistorico.setIcon(new ImageIcon(JanelaPrincipal.class.getResource("/Botoes/BotaoHistorico.png")));
		btnHistorico.setBounds(314, 256, 176, 178);
		btnHistorico.setVisible(false);
		btnHistorico.setEnabled(false);
		contentPane.add(btnHistorico);

		JLabel ImagemTelaPrincipal = new JLabel("");
		ImagemTelaPrincipal.setIcon(new ImageIcon(JanelaPrincipal.class.getResource("/Imagens/TelaPrincipal.png")));
		ImagemTelaPrincipal.setBounds(0, 0, 1006, 676);
		contentPane.add(ImagemTelaPrincipal);

		// ---------------------------------------------------------------------------

		btnDeslogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Deslogar();
			}
		});
		btnHistorico.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Entidade.equals("Fornecedor")) {
					HistoricoFornecedor();
				} else if (Entidade.equals("Cliente")) {
					HistoricoCliente();
				}
			}
		});
		btnVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!Entidade.isEmpty()) {
					if (Entidade.equals("Funcionario")) {
						FornecedorVendaMateriais();
					} else if (Entidade.contentEquals("Cliente")) {
						ClienteCompraMaterial();
					}
				} 
			}
		});
		btnPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!Entidade.isEmpty()) {
					if (Entidade.equals("Fornecedor")) {
						AbrirPerfilFornecedor();
					} else if (Entidade.equals("Cliente")) {
						AbrirPerfilCliente();
					} else {
						AbrirPerfilFuncionario();
					}
				} else {
					AbrirOpcaoCadastro();
				}
			}
		});
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				OpcaoLogin.setVisible(true);
			}
		});
	}

	private void AbrirOpcaoCadastro() {
		this.dispose();
		OpcaoCadastro opcao = new OpcaoCadastro();
		opcao.setVisible(true);
	}

	private void AbrirInformativo() {
		this.dispose();
		Informacoes informativo = new Informacoes();
		informativo.setVisible(true);
	}

	private void AbrirPerfilFornecedor() {
		PerfilFornecedorDao.PerfilFornecedor(CPF, FornecedorBean);
		PerfilFornecedor PerfilFornecedor = new PerfilFornecedor("Fornecedor", FornecedorBean);
		PerfilFornecedor.setVisible(true);
		this.dispose();
	}

	private void AbrirPerfilFuncionario() {
		FuncionarioDao.BuscarDadosFuncionario(CPF, FuncionarioBean);
		PerfilFornecedor PerfilFornecedor = new PerfilFornecedor("Funcionario", FuncionarioBean);
		PerfilFornecedor.setVisible(true);
		this.dispose();
	}

	private void AbrirPerfilCliente() {
		ClienteDao.BuscasDadosCliente(CNPJ, ClienteBean);
		PerfilCliente PerfilCliente = new PerfilCliente(ClienteBean);
		PerfilCliente.setVisible(true);
		this.dispose();
	}

	private void FornecedorVendaMateriais() {
		FuncionarioDao.BuscarDadosFuncionario(CPF, FuncionarioBean);
		FornecedorVenda FornecedorVenda = new FornecedorVenda(FuncionarioBean);
		FornecedorVenda.setVisible(true);
		this.dispose();
	}

	private void HistoricoFornecedor() {
		PerfilFornecedorDao.PerfilFornecedor(CPF, FornecedorBean);
		HistoricoFornecedor Historico = new HistoricoFornecedor(FornecedorBean);
		Historico.setVisible(true);
	}

	private void HistoricoCliente() {
		ClienteDao.BuscasDadosCliente(CNPJ, ClienteBean);
		HistoricoCliente Historico = new HistoricoCliente(ClienteBean);
		Historico.setVisible(true);
	}

	private void Deslogar() {
		this.btnDeslogar.setEnabled(false);
		this.btnDeslogar.setVisible(false);
		this.btnLogin.setVisible(true);
		this.btnLogin.setEnabled(true);
		this.OpcaoLogin.setVisible(true);
		this.btnPerfil.setIcon(new ImageIcon(JanelaPrincipal.class.getResource("/Botoes/BotaoCadastrar.png")));

		this.dispose();
	}

	private void ClienteCompraMaterial() {
		ClienteDao.BuscasDadosCliente(CNPJ, ClienteBean);
		PedidoCliente pedido = new PedidoCliente(ClienteBean);
		pedido.setVisible(true);
		this.dispose();
	}
}
