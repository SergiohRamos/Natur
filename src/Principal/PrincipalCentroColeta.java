package Principal;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import TelasCadastro.CadastroFuncionario;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class PrincipalCentroColeta extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrincipalCentroColeta frame = new PrincipalCentroColeta();
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
	public PrincipalCentroColeta() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1012, 684);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnConsultar = new JMenu("Consultar");
		menuBar.add(mnConsultar);
		
		JMenu mnDados = new JMenu("Dados");
		mnConsultar.add(mnDados);
		
		JMenuItem mntmClientes = new JMenuItem("Clientes");
		mnDados.add(mntmClientes);
		
		JMenuItem mntmFuncionarios = new JMenuItem("Funcionarios");
		mnDados.add(mntmFuncionarios);
		
		JMenuItem mntmFornecedores = new JMenuItem("Fornecedores");
		mnDados.add(mntmFornecedores);
		
		JMenuItem mntmPedidos = new JMenuItem("Pedidos");
		mnConsultar.add(mntmPedidos);
		
		JMenuItem mntmEstoque = new JMenuItem("Estoque");
		mnConsultar.add(mntmEstoque);
		
		JMenu mnCadastrar = new JMenu("Cadastrar");
		menuBar.add(mnCadastrar);
		
		JMenuItem mntmFuncionrio = new JMenuItem("Funcion\u00E1rio");
		mnCadastrar.add(mntmFuncionrio);
		
		JMenuItem mntmNovoMaterial = new JMenuItem("Novo material");
		mnCadastrar.add(mntmNovoMaterial);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTela = new JLabel("");
		lblTela.setIcon(new ImageIcon(PrincipalCentroColeta.class.getResource("/Imagens/PrincipalAdm.png")));
		lblTela.setBounds(0, 0, 1012, 684);
		contentPane.add(lblTela);

		// ActionListners----------------------------------------------------
		
		mntmFornecedores.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PesquisarFornecedor();
			}
		});
		mntmClientes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PesquisarCliente();	
			}
		});
		mntmFuncionarios.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ConsultarFuncionario();
			}
		});
		mntmPedidos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PedidosPendentes();
			}
		});
		mntmFuncionrio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastrarFuncionario();
			}
		});
		mntmNovoMaterial.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TelaMaterial();
			}
		});
		mntmEstoque.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ConsultarEstoque();
			}
		});

	}

	private void TelaMaterial() {
		AdministradorMateriais Material = new AdministradorMateriais("Att");
		Material.setVisible(true);
	}

	private void ConsultarEstoque() {
		AdmVerificarEstoque Estoque = new AdmVerificarEstoque();
		Estoque.setVisible(true);
	}

	private void PesquisarFornecedor() {
		AdmConsultarFornecedores ConsultarFornecedor = new AdmConsultarFornecedores("Fornecedor");
		ConsultarFornecedor.setVisible(true);
	}

	private void PedidosPendentes() {
		AdmPedidos Pedidos = new AdmPedidos();
		Pedidos.setVisible(true);
	}
	
	private void PesquisarCliente() {
		AdmConsultarFornecedores ConsultarCliente = new AdmConsultarFornecedores("Cliente");
		ConsultarCliente.setVisible(true);
	}
	
	private void ConsultarFuncionario() {
		AdmConsultarFornecedores ConsultarFuncionario = new AdmConsultarFornecedores("Funcionario");
		ConsultarFuncionario.setVisible(true);
	}
	
	private void CadastrarFuncionario() {
		CadastroFuncionario Funcionario = new CadastroFuncionario();
		Funcionario.setVisible(true);
		this.dispose();
	}
}
