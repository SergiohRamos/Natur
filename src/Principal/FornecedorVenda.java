package Principal;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Beans.FornecedorBean;
import Beans.FuncionarioBean;
import Beans.MaterialBean;
import Beans.PreTriagemBean;
import Beans.PreTriagemTableBean;
import Conexao.Conexao;
import Dao.AdministradorDao;
import Dao.EstoqueDao;
import Dao.OrdemPagamentoDao;
import Dao.PreTriagemDao;
import Model.MateriaisTableModel;
import Model.VendaTableModel;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JList;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.border.BevelBorder;

public class FornecedorVenda extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtNomeFornecedor;
	private JTextField txtNomeMaterial;
	private JTextField txtPesoMaterial;
	private JTextField txtVlrKilo;
	private JTable tablePesquisa;
	private JTable tableItensVenda;
	private JList<String> ListaFornecedores;
	private JScrollPane scrllFornecedores;
	private MaterialBean materialBean = new MaterialBean();
	private VendaTableModel ItemTableModel = new VendaTableModel();
	private MateriaisTableModel MaterialModel = new MateriaisTableModel();
	private ArrayList<PreTriagemBean> Dados = new ArrayList<>();
	private String cpf_fornecedor = "";
	private double SomaDoValorTotal = 0;
	private ArrayList<Integer> ID_PreTriagem = new ArrayList<>();
	private JButton btnCancelar = new JButton("");
	private JTextField txtNomeFuncionario;

	private FuncionarioBean Funcionario = new FuncionarioBean();
	private FornecedorBean Fornecedor = new FornecedorBean();
	private DefaultListModel<String> ListModel = new DefaultListModel<String>();
	private List<String> NomesFornecedores = new ArrayList<String>();
	private AdministradorDao AdmDao = new AdministradorDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FornecedorVenda frame = new FornecedorVenda();
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
	public FornecedorVenda(FuncionarioBean FuncionarioBean) {
		this();
		this.Funcionario = FuncionarioBean;
		this.txtNomeFuncionario.setText(FuncionarioBean.getNome_Funcionario());
		this.MaterialModel.read("");
	}

	public FornecedorVenda() {
		getContentPane().setBackground(Color.WHITE);
		setBounds(0, 0, 1012, 707);
		getContentPane().setLayout(null);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 1012, 705);
		getContentPane().add(panel);
		panel.setLayout(null);

		scrllFornecedores = new JScrollPane();
		scrllFornecedores.setBounds(422, 119, 178, 196);
		scrllFornecedores.setVisible(false);
		panel.add(scrllFornecedores);

		ListaFornecedores = new JList<String>();
		ListaFornecedores.setModel(ListModel);
		scrllFornecedores.setViewportView(ListaFornecedores);
		ListaFornecedores.setVisible(false);

		txtNomeFornecedor = new JTextField();
		txtNomeFornecedor.setBounds(426, 90, 162, 29);
		panel.add(txtNomeFornecedor);
		txtNomeFornecedor.setColumns(10);
		txtNomeFornecedor.setOpaque(false);
		txtNomeFornecedor.setBorder(BorderFactory.createEmptyBorder());

		txtNomeMaterial = new JTextField();
		txtNomeMaterial.setBounds(426, 250, 162, 29);
		panel.add(txtNomeMaterial);
		txtNomeMaterial.setColumns(10);
		txtNomeMaterial.setOpaque(false);
		txtNomeMaterial.setBorder(BorderFactory.createEmptyBorder());

		JButton btnBuscar = new JButton("");
		btnBuscar.setIcon(new ImageIcon(FornecedorVenda.class.getResource("/Botoes/BuscarMaterial.png")));
		btnBuscar.setBounds(622, 250, 100, 34);
		panel.add(btnBuscar);
		btnBuscar.setContentAreaFilled(false);

		txtPesoMaterial = new JTextField();
		txtPesoMaterial.setBounds(426, 148, 70, 29);
		panel.add(txtPesoMaterial);
		txtPesoMaterial.setColumns(10);
		txtPesoMaterial.setOpaque(false);
		txtPesoMaterial.setBorder(BorderFactory.createEmptyBorder());

		txtVlrKilo = new JTextField();
		txtVlrKilo.setEnabled(false);
		txtVlrKilo.setEditable(false);
		txtVlrKilo.setVisible(false);
		txtVlrKilo.setBounds(62, 297, 86, 29);
		panel.add(txtVlrKilo);
		txtVlrKilo.setColumns(10);
		txtVlrKilo.setOpaque(false);
		txtVlrKilo.setBorder(BorderFactory.createEmptyBorder());

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 390, 332, 214);
		panel.add(scrollPane);

		tablePesquisa = new JTable();
		tablePesquisa.setModel(MaterialModel);
		tablePesquisa.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.setViewportView(tablePesquisa);
		tablePesquisa.setBackground(Color.LIGHT_GRAY);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(454, 390, 533, 216);
		panel.add(scrollPane_1);

		tableItensVenda = new JTable();
		tableItensVenda.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane_1.setViewportView(tableItensVenda);
		tableItensVenda.setBackground(Color.GRAY);
		tableItensVenda.setModel(ItemTableModel);
		tableItensVenda.getColumnModel().getColumn(0).setPreferredWidth(0);
		tableItensVenda.getColumnModel().getColumn(1).setPreferredWidth(0);
		tableItensVenda.getColumnModel().getColumn(2).setPreferredWidth(0);
		tableItensVenda.getColumnModel().getColumn(3).setPreferredWidth(0);

		JButton btnFinalizar = new JButton("");
		btnFinalizar.setIcon(new ImageIcon(FornecedorVenda.class.getResource("/Botoes/FinalizarTranzacao.png")));
		btnFinalizar.setBounds(399, 619, 162, 52);
		btnFinalizar.setContentAreaFilled(false);
		panel.add(btnFinalizar);
		btnCancelar.setIcon(new ImageIcon(FornecedorVenda.class.getResource("/Botoes/Cancelar.png")));
		btnCancelar.setBounds(571, 619, 136, 51);
		panel.add(btnCancelar);
		btnCancelar.setContentAreaFilled(false);

		JButton btnAdd = new JButton("");
		btnAdd.setIcon(new ImageIcon(FornecedorVenda.class.getResource("/Botoes/Add.png")));
		btnAdd.setBounds(366, 391, 65, 29);
		panel.add(btnAdd);
		btnAdd.setContentAreaFilled(false);

		JButton btnExcluirItem = new JButton("");
		btnExcluirItem.setBounds(791, 350, 33, 29);
		btnExcluirItem.setBorderPainted(false);

		panel.add(btnExcluirItem);
		btnExcluirItem.setContentAreaFilled(false);

		txtNomeFuncionario = new JTextField();
		txtNomeFuncionario.setEditable(false);
		txtNomeFuncionario.setEnabled(false);
		txtNomeFuncionario.setVisible(false);
		txtNomeFuncionario.setBounds(127, 70, 173, 20);
		panel.add(txtNomeFuncionario);
		txtNomeFuncionario.setColumns(10);

		JLabel lblImagemFundo = new JLabel("");
		lblImagemFundo.setBounds(0, 0, 1012, 707);
		lblImagemFundo.setIcon(new ImageIcon(FornecedorVenda.class.getResource("/Imagens/TelaVenda.png")));
		panel.add(lblImagemFundo);

		// -------------------------------------------------------------------------------------------------

		btnExcluirItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ExcluirItemCarrinho();
			}
		});
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AdicionarAoCarrinho();
			}
		});
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CancelarTransação();
			}
		});
		btnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TerminarTransação();
			}
		});
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MaterialModel.read(txtNomeMaterial.getText());
			}
		});
		txtNomeFornecedor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				PesquisarFornecedor();
			}
		});
		ListaFornecedores.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				String Valor = ListaFornecedores.getSelectedValue();
				txtNomeFornecedor.setText(Valor);
				System.out.println(Valor);
				AdmDao.BuscarDadosFornecedor(Valor, Fornecedor);
				txtNomeFornecedor.setText(Fornecedor.getNome_fornecedor());
				LimparLista();

				ListaFornecedores.setVisible(false);
				scrllFornecedores.setVisible(false);
			}
		});

	}

	private void BuscarIDPreTriagem(int Linhas) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = Conexao.getConection();
			String sql = "SELECT pt.id_pre_triagem FROM pre_triagem as pt ORDER BY pt.id_pre_triagem DESC LIMIT ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Linhas);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ID_PreTriagem.add(rs.getInt("pt.id_pre_triagem"));
			}
			Conexao.FecharConexao(conn, pstmt, rs);
		} catch (Exception e) {
			System.out.println("Erro ao selecionar ID_PreTriagem! \n Erro: " + e.getMessage());
		}
	}

	private void TerminarTransação() {
		Object[] botoes = { "Sim", "Não" };
		int resposta = JOptionPane.showOptionDialog(null, "Deseja concluir esta operação? ", "Confirmação",
				JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, botoes, botoes[0]);

		if (resposta == 0) {
			if (Dados.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Selecione pelo menos um item para concluir a transação", "Aviso!",
						JOptionPane.WARNING_MESSAGE);
			} else {
				int Carrinho = tableItensVenda.getRowCount();
				if (Carrinho > 0) {
					PreTriagemDao ptd = new PreTriagemDao();
					EstoqueDao estoque = new EstoqueDao();
					PreTriagemTableBean pttb = new PreTriagemTableBean();
					OrdemPagamentoDao opd = new OrdemPagamentoDao();

					String Data = PegaData();
					for (int i = 0; i < Dados.size(); i++) {
						System.out.println(Dados.get(i).getDados());
						int ID_PreTriagem = ptd.InserirDadosPreTriagem(Dados.get(i));

						double Valor = Dados.get(i).getPeso();
						if ((Valor % 250) == 0) {
							int NovoEstoque = (int) (Valor / 250);
							estoque.AtualizarEstoque(Dados.get(i).getId_material(), NovoEstoque);
							estoque.ZerarTriagem(Dados.get(i).getId_material());
						} else {
							double VerificaTriagem = estoque.ConsultarPesoFardo(Dados.get(i).getId_material());
							System.out.println("---------------------------"+VerificaTriagem);
							if(VerificaTriagem == 250) {
								estoque.AdicionarEstoqueUnitario(Dados.get(i).getId_material());
								estoque.ZerarTriagem(Dados.get(i).getId_material());
							}
						}
						ptd.InserirTableFornPreTriagem(Fornecedor.getId_fornecedor(), ID_PreTriagem, Data);
					}
					pttb.setId_Fornecedor(Fornecedor.getId_fornecedor());
					pttb.setValor_total(SomaDoValorTotal);
					opd.Inserir(pttb);

					JOptionPane.showMessageDialog(null, "Finalizado com sucesso!");
					LimparTabelaItensVenda();
					txtNomeFornecedor.setEnabled(true);
					txtNomeFornecedor.setEditable(true);
					txtNomeFornecedor.setText("");
					txtNomeFornecedor.setFocusable(true);
					txtPesoMaterial.setText("");
				} else {
					JOptionPane.showMessageDialog(null, "Carrinho vazio!", "Aviso!", JOptionPane.WARNING_MESSAGE);
				}
			}
		} else if (resposta == 1) {
			JOptionPane.showMessageDialog(null, "Interrompido");
		}
	}

	private void CancelarTransação() {
		dispose();
		JanelaPrincipal janelaPrincipal = new JanelaPrincipal("Funcionario", Funcionario.getCpf_Funcionario());
		janelaPrincipal.setVisible(true);
	}

	private void AdicionarAoCarrinho() {
		if (txtNomeFornecedor.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informar o nome do fornecedor!", "Aviso!",
					JOptionPane.WARNING_MESSAGE);
		} else {
			int indice = tablePesquisa.getSelectedRow();
			if (indice == -1) {
				JOptionPane.showMessageDialog(null, "Selecione um item para adicionar ao carrinho.","Atenção!", JOptionPane.WARNING_MESSAGE);
			} else {
				if (txtPesoMaterial.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Por favor, informe o peso", "Atenção!",
							JOptionPane.WARNING_MESSAGE);
					txtVlrKilo.setText("");
				} else {
					boolean Numero;
					try {
						Double.parseDouble(txtPesoMaterial.getText());
						Numero = true;
					} catch (Exception e) {
						Numero = false;
					}
					if (Numero) {
						PreTriagemTableBean pttb = new PreTriagemTableBean();
						PreTriagemBean ptb = new PreTriagemBean();
						
						txtNomeFornecedor.setEditable(false);
						double Preco_KG = (double) tablePesquisa.getValueAt(tablePesquisa.getSelectedRow(), 1);
						double PesoMaterial = Double.parseDouble(txtPesoMaterial.getText());
						double ValorTotal = (Preco_KG * PesoMaterial);
						
						int NumLinhas = tableItensVenda.getRowCount();
						int Cod_material = MaterialModel.RetornaIDMaterial(tablePesquisa.getSelectedRow());
						System.out.println("Id material : " + Cod_material);

						pttb.setId_Fornecedor(Fornecedor.getId_fornecedor());
						System.out.println("\n Id do fornecedor: " + Fornecedor.getId_fornecedor());
						pttb.setNome_Fornecedor(Fornecedor.getNome_fornecedor());
						pttb.setId_material(Cod_material);
						pttb.setMaterial((String) tablePesquisa.getValueAt(tablePesquisa.getSelectedRow(), 0));
						pttb.setPesoMaterial(Double.parseDouble(txtPesoMaterial.getText()));
						pttb.setPreco_Kg(Preco_KG);
						pttb.setValor_total(ValorTotal);

						ptb.setId_funcionario(Funcionario.getID_funcionario());
						ptb.setPeso(Double.parseDouble(txtPesoMaterial.getText()));
						ptb.setId_material(Cod_material);

						Dados.add(ptb);
						System.out.println(Dados);

						ItemTableModel.addRow(pttb);

						SomaDoValorTotal = (double) tableItensVenda.getValueAt(0, 3);
						for (int i = 1; i <= NumLinhas; i++) {
							SomaDoValorTotal += (double) tableItensVenda.getValueAt(i, 3);
						}
						txtVlrKilo.setText(Double.toString(Preco_KG));
						txtNomeFornecedor.setEnabled(false);
					} else {
						JOptionPane.showMessageDialog(null, "Valor digitado no campo peso é inválido!", "Erro!",
								JOptionPane.ERROR_MESSAGE);
					}

				}
			}
		}
	}

	private void ExcluirItemCarrinho() {
		if (tableItensVenda.getSelectedRow() != -1) {
			int posicao = tableItensVenda.getSelectedRow();
			Dados.remove(posicao);

			double Valor = (double) tableItensVenda.getValueAt(tableItensVenda.getSelectedRow(), 3);
			SomaDoValorTotal = SomaDoValorTotal - Valor;

			VendaTableModel ItemTableModel = (VendaTableModel) tableItensVenda.getModel();
			ItemTableModel.removeRow(tableItensVenda.getSelectedRow());
			if (tableItensVenda.getRowCount() == 0) {
				txtNomeFornecedor.setEnabled(true);
				txtNomeFornecedor.setEditable(true);
			} else {
				txtNomeFornecedor.setEnabled(false);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Selecione um item para excluir", "Aviso!",
					JOptionPane.WARNING_MESSAGE);
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

	private void PesquisarFornecedor() {
		String Nome = txtNomeFornecedor.getText();
		if (NomesFornecedores.size() > 0) {
			NomesFornecedores.clear();
			ListModel.clear();
		}
		if (!(Nome.isEmpty())) {
			if (AdmDao.BuscarFornecedores(Nome, NomesFornecedores)) {
				DefaultListModel<String> Modelo = new DefaultListModel<String>();
				for (int i = 0; i < NomesFornecedores.size(); i++) {
					Modelo.addElement(NomesFornecedores.get(i));
				}
				ListaFornecedores.setModel(Modelo);

				ListaFornecedores.setVisible(true);
				scrllFornecedores.setVisible(true);
			} else {
				ListaFornecedores.setVisible(false);
				scrllFornecedores.setVisible(false);
			}
		} else {
			ListaFornecedores.setVisible(false);
			scrllFornecedores.setVisible(false);
		}
	}

	private void LimparLista() {
		DefaultListModel<String> ListModel1 = (DefaultListModel<String>) ListaFornecedores.getModel();
		ListModel1.clear();
	}

	private void LimparTabelaItensVenda() {
		ItemTableModel.clear();
		System.out.println("Antes: " + Dados);
		Dados.clear();
		txtNomeFornecedor.setEnabled(true);
		System.out.println("Depois: " + Dados);
	}

}
