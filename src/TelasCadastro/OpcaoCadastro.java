package TelasCadastro;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Principal.JanelaPrincipal;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class OpcaoCadastro extends JFrame {

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
					OpcaoCadastro frame = new OpcaoCadastro();
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
	public OpcaoCadastro() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 613, 576);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnCadastroFornecedor = new JButton("");
		btnCadastroFornecedor.setIcon(new ImageIcon(OpcaoCadastro.class.getResource("/Botoes/BotaoLoginFornecedorr.png")));
		btnCadastroFornecedor.setBounds(58, 58, 202, 224);
		btnCadastroFornecedor.setContentAreaFilled(false);
		contentPane.add(btnCadastroFornecedor);
		
		JButton btnCadastroCliente = new JButton("");
		btnCadastroCliente.setIcon(new ImageIcon(OpcaoCadastro.class.getResource("/Botoes/BotaoLoginCliente.png")));
		btnCadastroCliente.setBounds(331, 58, 202, 226);
		btnCadastroCliente.setContentAreaFilled(false);
		contentPane.add(btnCadastroCliente);
		
		JButton btnVoltar = new JButton("");
		btnVoltar.setIcon(new ImageIcon(OpcaoCadastro.class.getResource("/Botoes/VoltarOpcaoLogin.png")));
		btnVoltar.setBounds(212, 406, 179, 78);
		btnVoltar.setContentAreaFilled(false);
		contentPane.add(btnVoltar);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(OpcaoCadastro.class.getResource("/Imagens/LogarComo.png")));
		label.setBounds(0, 0, 612, 547);
		contentPane.add(label);
		
		btnCadastroFornecedor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CadastrarFornecedor();
			}
		});
		btnCadastroCliente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CadastrarCliente();
			}
		});
		btnVoltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VoltarTelaInicial();
			}
		});
		
	}
	public void CadastrarFornecedor(){
		this.dispose();
		CadastroFornecedor cadastrar = new CadastroFornecedor();
		cadastrar.setVisible(true);
	}
	public void CadastrarCliente(){
		this.dispose();
		CadastroCliente cadastrar = new CadastroCliente();
		cadastrar.setVisible(true);
	}
	public void VoltarTelaInicial(){
		dispose();
		JanelaPrincipal principal = new JanelaPrincipal();
		principal.setVisible(true);
	}
}
