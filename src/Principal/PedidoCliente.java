package Principal;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Beans.ClienteBean;
import Beans.ClienteCompraTableBean;
import Beans.ItemBean;
import Beans.MaterialBean;
import Dao.ClienteDao;
import Dao.MaterialDao;
import Model.ClienteCompraTableModel;
import Model.MaterialEstoqueTableModel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

public class PedidoCliente extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNomeEmpresa;
	private JTextField txtNomeMaterial;
	private JTextField txtQuantidade;
	private JTable TableMateriais;

	private MaterialEstoqueTableModel MaterialModel = new MaterialEstoqueTableModel();

	private JScrollPane scrollPane;
	private JTable TabelaItensSelecionados;
	private JScrollPane scrollPane_1;
	private ClienteBean ClienteBean = new ClienteBean();
	private ClienteDao clienteDao = new ClienteDao();
	private ArrayList<ClienteCompraTableBean> Dados = new ArrayList<ClienteCompraTableBean>();
	private ClienteCompraTableModel ClienteCompraTable = new ClienteCompraTableModel();
	private MaterialDao MaterialDao = new MaterialDao();
	private ArrayList<ItemBean> Itens = new ArrayList<ItemBean>();
	private ArrayList<Integer> Pedido = new ArrayList<Integer>();

	private double Custo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PedidoCliente frame = new PedidoCliente();
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
	public PedidoCliente(ClienteBean ClienteBean) {
		this();
		this.ClienteBean = ClienteBean;
		this.txtNomeEmpresa.setText(ClienteBean.getNome_Cliente());
		this.MaterialModel.readEstoq("");
	}

	public PedidoCliente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1012, 705);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtNomeEmpresa = new JTextField();
		txtNomeEmpresa.setBounds(426, 93, 261, 25);
		txtNomeEmpresa.setOpaque(false);
		txtNomeEmpresa.setEditable(false);
		txtNomeEmpresa.setBorder(BorderFactory.createEmptyBorder());
		contentPane.add(txtNomeEmpresa);
		txtNomeEmpresa.setColumns(10);

		txtNomeMaterial = new JTextField();
		txtNomeMaterial.setBounds(426, 255, 161, 25);
		txtNomeMaterial.setOpaque(false);
		txtNomeMaterial.setBorder(BorderFactory.createEmptyBorder());
		contentPane.add(txtNomeMaterial);
		txtNomeMaterial.setColumns(10);

		txtQuantidade = new JTextField();
		txtQuantidade.setBounds(426, 149, 65, 32);
		txtQuantidade.setOpaque(false);
		txtQuantidade.setBorder(BorderFactory.createEmptyBorder());
		contentPane.add(txtQuantidade);
		txtQuantidade.setColumns(10);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 386, 308, 216);
		contentPane.add(scrollPane);

		TableMateriais = new JTable();
		scrollPane.setViewportView(TableMateriais);
		TableMateriais.setModel(MaterialModel);

		JButton btnBuscar = new JButton("");
		btnBuscar.setIcon(new ImageIcon(PedidoCliente.class.getResource("/Botoes/BuscarMaterial.png")));
		btnBuscar.setContentAreaFilled(false);
		btnBuscar.setBounds(609, 255, 100, 34);
		contentPane.add(btnBuscar);

		JButton btnCriarPedido = new JButton("");
		btnCriarPedido.setIcon(new ImageIcon(PedidoCliente.class.getResource("/Botoes/FinalizarPedido.png")));
		btnCriarPedido.setContentAreaFilled(false);
		btnCriarPedido.setBounds(335, 626, 161, 40);
		contentPane.add(btnCriarPedido);

		JButton btnVoltar = new JButton("");
		btnVoltar.setIcon(new ImageIcon(PedidoCliente.class.getResource("/Botoes/btnVoltar2.png")));
		btnVoltar.setBounds(506, 626, 126, 40);
		btnVoltar.setContentAreaFilled(false);
		contentPane.add(btnVoltar);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(492, 386, 496, 216);
		contentPane.add(scrollPane_1);

		TabelaItensSelecionados = new JTable();
		TabelaItensSelecionados.setModel(ClienteCompraTable);
		scrollPane_1.setViewportView(TabelaItensSelecionados);
		TabelaItensSelecionados.getColumnModel().getColumn(3).setPreferredWidth(5);
		TabelaItensSelecionados.getColumnModel().getColumn(2).setPreferredWidth(5);

		JButton btnAddAoCarrinho = new JButton("");
		btnAddAoCarrinho.setIcon(new ImageIcon(PedidoCliente.class.getResource("/Botoes/Add.png")));
		btnAddAoCarrinho.setContentAreaFilled(false);
		btnAddAoCarrinho.setBounds(358, 384, 65, 29);
		contentPane.add(btnAddAoCarrinho);

		JButton btnRemover = new JButton("");
		btnRemover.setBounds(790, 346, 44, 32);
		btnRemover.setBorder(BorderFactory.createEmptyBorder());
		btnRemover.setContentAreaFilled(false);
		contentPane.add(btnRemover);
		
		JLabel lblTela = new JLabel("");
		lblTela.setIcon(new ImageIcon(PedidoCliente.class.getResource("/Imagens/TelaVendaMaterial.png")));
		lblTela.setBounds(0, 0, 1012, 705);
		contentPane.add(lblTela);

		// -----------------------------------------------------------------------------------

		btnAddAoCarrinho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddCarrinho();
			}
		});

		btnBuscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MaterialModel.readEstoq(txtNomeMaterial.getText());
			}
		});
		btnVoltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JanelaPrincipal Principal = new JanelaPrincipal("Cliente", ClienteBean.getCnpj_Cliente());
				Principal.setVisible(true);
				dispose();
			}
		});
		btnCriarPedido.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FinalizarCompra();
			}
		});
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removerItemSelecionado();
			}
		});
	}

	public void AddCarrinho() {
		int indice = TableMateriais.getSelectedRow();

		if (indice == -1) {
			JOptionPane.showMessageDialog(null, "Selecione algum material para adicionar ao carrinho!", "Atenção!",
					JOptionPane.WARNING_MESSAGE);
		} else {
			if (txtQuantidade.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Informe a quantidade desejada!", "Atenção!",
						JOptionPane.WARNING_MESSAGE);
			} else {
				// Verifica se o arrombado realmente digitou um numero
				boolean Numero;
				try {
					Integer.parseInt(txtQuantidade.getText());
					Numero = true;
				} catch (Exception e) {
					Numero = false;
				}
				if (Numero) {
					MaterialBean MaterialBean = new MaterialBean();
					ItemBean item = new ItemBean();
					ClienteCompraTableBean CompraClienteTable = new ClienteCompraTableBean();

					String Descricao = (String) TableMateriais.getValueAt(TableMateriais.getSelectedRow(), 0);

					MaterialBean.setMaterial(Descricao);
					MaterialBean = MaterialDao.BuscaID_Preco(MaterialBean);

					int ID_Material = MaterialBean.getId_material();
					System.out.println("ID do material --- " + ID_Material);
					double Preco_Material = MaterialBean.getPreco();

					int Estoque = (int) TableMateriais.getValueAt(TableMateriais.getSelectedRow(), 1);
					String Nome_Empresa = txtNomeEmpresa.getText();
					String Quantidade_Pedido = txtQuantidade.getText();
					double Peso_Kg = Double.parseDouble(Quantidade_Pedido) * 250;
					double Custo = (Peso_Kg * Preco_Material);
					double Despesas = (Custo * 0.1);
					/* double Imposto = (Custo + 0.15); */
					double Custo_Final = (Custo + Despesas);
					this.Custo = Custo_Final;

					CompraClienteTable.setNome_Empresa(Nome_Empresa);
					CompraClienteTable.setMaterial(Descricao);
					CompraClienteTable.setQuantidade_Fardo(Integer.parseInt(Quantidade_Pedido));
					CompraClienteTable.setPeso_Kg(Peso_Kg);
					CompraClienteTable.setCusto(Custo_Final);

					item.setId_Material(ID_Material);
					item.setQuantidade_Fardos(Integer.parseInt(Quantidade_Pedido));
					item.setPeso_Kg(Peso_Kg);

					ClienteCompraTable.addRow(CompraClienteTable);

					Dados.add(CompraClienteTable);
					Itens.add(item);
					System.out.println(Itens);
					System.out.println(Dados);
				} else {
					JOptionPane.showMessageDialog(null, "Valor digitado no campo quantidade é inválido!", "Atenção!",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		}
	}

	private void removerItemSelecionado() {
		if (TabelaItensSelecionados.getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(null, "Selecione um item para remover do carrinho!", "Atenção !",
					JOptionPane.WARNING_MESSAGE);
		} else {
			int LinhaSelecionada = TabelaItensSelecionados.getSelectedRow();
			Itens.remove(LinhaSelecionada);
			System.out.println(Itens);

			ClienteCompraTableModel CompraModel = (ClienteCompraTableModel) TabelaItensSelecionados.getModel();
			CompraModel.removeRow(TabelaItensSelecionados.getSelectedRow());
		}
	}

	private void FinalizarCompra() {

		Object[] botoes = { "Sim", "Não" };
		int resposta = JOptionPane.showOptionDialog(null, "Deseja concluir esta operação? ", "Confirmação",
				JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, botoes, botoes[0]);

		if (resposta == 0) {
			int ItensNoCarrinho = TabelaItensSelecionados.getRowCount();
			if (ItensNoCarrinho == 0) {
				JOptionPane.showMessageDialog(null, "Selecione algum material para efetuar a compra!", "Atenção!",
						JOptionPane.WARNING_MESSAGE);
			} else {
				for (int i = 0; i < ItensNoCarrinho; i++) {
					int Id_Item = clienteDao.InserirItem(Itens.get(i));
					int Id_Pedido = clienteDao.RealizarPedido(ClienteBean, PegaData());
					clienteDao.InserirItem_Pedido(Id_Pedido, Id_Item);

					clienteDao.InserirContasAPagar(Id_Item, ClienteBean.getId_Cliente(), Dados.get(i).getCusto());
					System.out.println("ID Pedido: " + Id_Pedido);
					System.out.println("ID Item: " + Id_Item);
				}
				JOptionPane.showMessageDialog(null, "Compra finalizada com sucesso!", "Transação",
						JOptionPane.INFORMATION_MESSAGE);
				LimparTable();
			}
		}
	}

	private String PegaData() {
		Date CapturaData = new Date();
		String Data = DateFormat.getDateInstance(DateFormat.MEDIUM).format(CapturaData);
		Calendar c = Calendar.getInstance();
		String Hora = Integer.toString(c.get(Calendar.HOUR_OF_DAY));
		String Minutos = Integer.toString(c.get(Calendar.MINUTE));

		String Dado = (Data + " - " + Hora + ":" + Minutos);
		System.out.println(Dado);

		return Dado;
	}
	
	private void LimparTable() {
		Dados.clear();
		ClienteCompraTable.clear();
	}
}
