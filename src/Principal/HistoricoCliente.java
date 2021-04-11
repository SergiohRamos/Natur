package Principal;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Beans.ClienteBean;
import Model.HistoricoClienteTableModel;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Color;

public class HistoricoCliente extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tblPedidosCliente;
	private JRadioButton rdbtnTodos;
	private JRadioButton rdbtnAprovados;
	private JRadioButton rdbtnCancelados;
	
	private final ButtonGroup buttonGroup = new ButtonGroup();

	private HistoricoClienteTableModel HistoricoModel = new HistoricoClienteTableModel();
	private ClienteBean ClienteBean = new ClienteBean();
	private JRadioButton rdbtnEspera;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HistoricoCliente frame = new HistoricoCliente();
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
	public HistoricoCliente(ClienteBean ClienteBean) {
		this();
		this.ClienteBean = ClienteBean;
		this.HistoricoModel.PreencherTabela(ClienteBean.getId_Cliente(), "Todos");
	}

	public HistoricoCliente() {
		setModal(true);
		setBounds(100, 100, 682, 578);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(8, 104, 659, 344);
		contentPane.add(scrollPane);

		tblPedidosCliente = new JTable();
		tblPedidosCliente.setModel(HistoricoModel);
		scrollPane.setViewportView(tblPedidosCliente);

		JButton btnVoltar = new JButton("");
		btnVoltar.setIcon(new ImageIcon(HistoricoCliente.class.getResource("/Botoes/btnVoltar.png")));
		btnVoltar.setBounds(549, 490, 90, 36);
		contentPane.add(btnVoltar);

		rdbtnTodos = new JRadioButton("Todos");
		rdbtnTodos.setBackground(Color.WHITE);
		rdbtnTodos.setSelected(true);
		buttonGroup.add(rdbtnTodos);
		rdbtnTodos.setBounds(183, 490, 74, 23);
		contentPane.add(rdbtnTodos);

		rdbtnAprovados = new JRadioButton("Aprovados");
		rdbtnAprovados.setBackground(Color.WHITE);
		buttonGroup.add(rdbtnAprovados);
		rdbtnAprovados.setBounds(259, 490, 89, 23);
		contentPane.add(rdbtnAprovados);

		rdbtnCancelados = new JRadioButton("Cancelados");
		rdbtnCancelados.setBackground(Color.WHITE);
		buttonGroup.add(rdbtnCancelados);
		rdbtnCancelados.setBounds(350, 490, 89, 23);
		contentPane.add(rdbtnCancelados);

		JLabel lblStatusDoPedido = new JLabel("Status do pedido:");
		lblStatusDoPedido.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblStatusDoPedido.setBounds(29, 491, 138, 19);
		contentPane.add(lblStatusDoPedido);
		
		rdbtnEspera = new JRadioButton("Espera");
		rdbtnEspera.setBackground(Color.WHITE);
		buttonGroup.add(rdbtnEspera);
		rdbtnEspera.setBounds(441, 490, 74, 23);
		contentPane.add(rdbtnEspera);
		
		JLabel lblTela = new JLabel("");
		lblTela.setIcon(new ImageIcon(HistoricoCliente.class.getResource("/Imagens/HistoricoCliente.png")));
		lblTela.setBounds(0, 0, 677, 549);
		contentPane.add(lblTela);

		// ---------------------------------------------------------------------------------------------------//
		btnVoltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Voltar();
			}
		});
		rdbtnTodos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				PreencherTable();
			}
		});
		rdbtnEspera.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PreencherTable();
			}
		});
		rdbtnAprovados.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PreencherTable();
			}
		});
		rdbtnCancelados.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PreencherTable();
			}
		});
		
	}
	private void Voltar() {
		this.dispose();
	}

	private void PreencherTable() {
		HistoricoClienteTableModel HistoricoModell = new HistoricoClienteTableModel();
		String Status = "";
		if(rdbtnTodos.isSelected()) {
			Status = "Todos";
		}
		else if(rdbtnAprovados.isSelected()) {
			Status = "Aprovado";
		}
		else if(rdbtnCancelados.isSelected()) {
			Status = "Cancelado";
		}
		else {
			Status = "Espera";
		}
		HistoricoModell.PreencherTabela(ClienteBean.getId_Cliente(), Status);
		tblPedidosCliente.setModel(HistoricoModell);
	}
}
