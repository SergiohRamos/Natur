package Principal;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;

import Beans.AdmPedidosBean;
import Beans.ClienteBean;
import Dao.AdministradorDao;
import Model.AdmPedidosTableModel;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import java.awt.Color;

public class AdmPedidos extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tblPedidos;
	private AdmPedidosTableModel PedidoModel = new AdmPedidosTableModel();
	private ArrayList<AdmPedidosBean> Dados = new ArrayList<AdmPedidosBean>();
	private AdministradorDao AdmDao = new AdministradorDao();
	private JRadioButton rdbtnEspera;
	private JRadioButton rdbtnAprovado;
	private JRadioButton rdbtnCancelado;
	private JRadioButton rdbtnTodos;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdmPedidos frame = new AdmPedidos();
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
	public AdmPedidos() {
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 777, 532);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 90, 741, 326);
		contentPane.add(scrollPane);

		tblPedidos = new JTable();
		tblPedidos.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tblPedidos.setModel(PedidoModel);
		tblPedidos.getColumnModel().getColumn(0).setPreferredWidth(0);
		tblPedidos.getColumnModel().getColumn(3).setPreferredWidth(0);
		tblPedidos.getColumnModel().getColumn(4).setPreferredWidth(5);
		scrollPane.setViewportView(tblPedidos);
		Dados = (ArrayList<AdmPedidosBean>) this.PedidoModel.PreencherTabela("Todos");

		JButton btnAprovar = new JButton("");
		btnAprovar.setIcon(new ImageIcon(AdmPedidos.class.getResource("/Botoes/btnAprovar.png")));
		btnAprovar.setToolTipText("\r\n");
		btnAprovar.setBounds(20, 427, 89, 36);
		btnAprovar.setContentAreaFilled(false);
		btnAprovar.setEnabled(false);
		contentPane.add(btnAprovar);

		JButton btnReprovar = new JButton("");
		btnReprovar.setIcon(new ImageIcon(AdmPedidos.class.getResource("/Botoes/btnCancelar.png")));
		btnReprovar.setBounds(119, 427, 89, 36);
		btnReprovar.setContentAreaFilled(false);
		btnReprovar.setEnabled(false);
		contentPane.add(btnReprovar);

		JButton btnVoltar = new JButton("");
		btnVoltar.setIcon(new ImageIcon(AdmPedidos.class.getResource("/Botoes/btnVoltar.png")));
		btnVoltar.setBounds(651, 448, 89, 36);
		btnVoltar.setContentAreaFilled(false);
		contentPane.add(btnVoltar);

		JLabel lblBuscarPedidosEm = new JLabel("Status do pedido:");
		lblBuscarPedidosEm.setBounds(329, 427, 127, 14);
		contentPane.add(lblBuscarPedidosEm);

		rdbtnEspera = new JRadioButton("Espera");
		rdbtnEspera.setBackground(Color.WHITE);
		buttonGroup.add(rdbtnEspera);
		rdbtnEspera.setBounds(240, 448, 67, 23);
		contentPane.add(rdbtnEspera);

		rdbtnAprovado = new JRadioButton("Aprovado");
		rdbtnAprovado.setBackground(Color.WHITE);
		buttonGroup.add(rdbtnAprovado);
		rdbtnAprovado.setBounds(317, 448, 79, 23);
		contentPane.add(rdbtnAprovado);

		rdbtnCancelado = new JRadioButton("Cancelado");
		rdbtnCancelado.setBackground(Color.WHITE);
		buttonGroup.add(rdbtnCancelado);
		rdbtnCancelado.setBounds(398, 448, 89, 23);
		contentPane.add(rdbtnCancelado);

		rdbtnTodos = new JRadioButton("Todos");
		rdbtnTodos.setBackground(Color.WHITE);
		rdbtnTodos.setBounds(489, 448, 67, 23);
		rdbtnTodos.setSelected(true);
		buttonGroup.add(rdbtnTodos);
		contentPane.add(rdbtnTodos);
		
		JLabel lblTela = new JLabel("");
		lblTela.setIcon(new ImageIcon(AdmPedidos.class.getResource("/Imagens/AdmPedido.png")));
		lblTela.setBounds(0, 0, 761, 493);
		contentPane.add(lblTela);

		// --------------------------------------------------------------------

		btnVoltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Voltar();
			}
		});
		btnAprovar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Aprovar();
			}
		});
		btnReprovar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Cancelar();
			}
		});
		rdbtnAprovado.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AtualizarTabela();
				btnAprovar.setEnabled(false);
				btnReprovar.setEnabled(false);
			}
		});
		rdbtnCancelado.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AtualizarTabela();
				btnAprovar.setEnabled(true);
				btnReprovar.setEnabled(false);
			}
		});
		rdbtnEspera.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AtualizarTabela();
				btnAprovar.setEnabled(true);
				btnReprovar.setEnabled(true);
			}
		});
		rdbtnTodos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AtualizarTabela();
				btnAprovar.setEnabled(false);
				btnReprovar.setEnabled(false);
			}
		});
		// -------------------------------------------------------------------------
	}

	private void Aprovar() {
		int LinhaSelecionada = tblPedidos.getSelectedRow();

		if (LinhaSelecionada == -1) {
			JOptionPane.showMessageDialog(null, "Selecione algum item para efetuar a operação!", "Atenção!",
					JOptionPane.WARNING_MESSAGE);
		} else {
			Object[] botoes = { "Sim", "Não" };
			int resposta = JOptionPane.showOptionDialog(null, "Deseja aprovar esse pedido? ", "Confirmação",
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, botoes, botoes[0]);

			if (resposta == 0) {
				int Indice = tblPedidos.getSelectedRow();
				int Id_Material = Dados.get(Indice).getID_Material();
				int Id_Estoque = AdmDao.BuscarIDEstoque(Id_Material);

				AdmPedidosBean Pedido = new AdmPedidosBean();
				Pedido.setID_Item(Dados.get(Indice).getID_Item());
				Pedido.setID_Pedido(Dados.get(Indice).getID_Pedido());

				int Fardos_Requisitados = (int) tblPedidos.getValueAt(tblPedidos.getSelectedRow(), 3);
				int Estoque = AdmDao.VerificarEstoque(Id_Material);
				int NovoEstoque = 0;

				if (Estoque >= Fardos_Requisitados) {
					NovoEstoque = Estoque - Fardos_Requisitados;

					AdmDao.DescontarDoEstoque(NovoEstoque, Id_Material);
					AdmDao.InserirExpedicao(Pedido, Id_Estoque);
					AdmDao.AtualizarStatusPedido(Pedido, "Aprovado");
					JOptionPane.showMessageDialog(null, "Pedido aprovado com sucesso!", "Confirmação!",
							JOptionPane.INFORMATION_MESSAGE);
					RemoverLinha(Indice);
					
				} else {
					JOptionPane.showMessageDialog(null, "Estoque insuficiente!", "Erro!", JOptionPane.ERROR_MESSAGE);
				}
			} 
		}
	}

	private void Cancelar() {
		int LinhaSelecionada = tblPedidos.getSelectedRow();

		if (LinhaSelecionada == -1) {
			JOptionPane.showMessageDialog(null, "Selecione algum item para efetuar a operação!", "Erro!",
					JOptionPane.ERROR_MESSAGE);
		} else {
			Object[] botoes = { "Sim", "Não" };
			int resposta = JOptionPane.showOptionDialog(null, "Deseja concluir esta operação? ", "Confirmação",
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, botoes, botoes[0]);

			if (resposta == 0) {
				int Indice = tblPedidos.getSelectedRow();

				AdmPedidosBean Pedido = new AdmPedidosBean();
				Pedido.setID_Item(Dados.get(Indice).getID_Item());
				Pedido.setID_Pedido(Dados.get(Indice).getID_Pedido());

				AdmDao.AtualizarStatusPedido(Pedido, "Cancelado");
				JOptionPane.showMessageDialog(null, "Pedido Cancelado!", "Confirmação", JOptionPane.WARNING_MESSAGE);

				RemoverLinha(Indice);
			}
		}
	}

	private void Voltar() {
		this.dispose();
	}

	private void AtualizarTabela() {
		AdmPedidosTableModel TabelaAtualizada = new AdmPedidosTableModel();
		Dados.clear();
		String Status = "";
		if (rdbtnEspera.isSelected()) {
			Status = "Espera";
		} else if (rdbtnCancelado.isSelected()) {
			Status = "Cancelado";
		} else if (rdbtnAprovado.isSelected()) {
			Status = "Aprovado";
		} else if (rdbtnTodos.isSelected()) {
			Status = "Todos";
		}
		Dados = (ArrayList<AdmPedidosBean>) TabelaAtualizada.PreencherTabela(Status);
		tblPedidos.setModel(TabelaAtualizada);
	}

	private void RemoverLinha(int Indice) {
		AdmPedidosTableModel TabelaAtualizada = (AdmPedidosTableModel) tblPedidos.getModel();
		TabelaAtualizada.removeRow(Indice);
	}
}
