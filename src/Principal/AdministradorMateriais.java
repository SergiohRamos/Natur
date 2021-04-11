package Principal;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Beans.MaterialBean;
import Dao.AdministradorDao;
import Dao.MaterialDao;
import Model.MateriaisTableModel;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

public class AdministradorMateriais extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNomeMaterial;
	private JTable TabelaMateriais;
	private MaterialBean MaterialBean = new MaterialBean();
	private MateriaisTableModel MaterialModel = new MateriaisTableModel();
	private AdministradorDao AdmDao = new AdministradorDao();
	private MaterialDao MaterialDao = new MaterialDao();
	private JButton btnAdicionarMaterial;
	private JButton btnModificarDados;
	private JButton btnBuscar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdministradorMateriais frame = new AdministradorMateriais();
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
	/**
	 * 
	 */
	public AdministradorMateriais(String Atualizar) {
		this();
		this.MaterialModel.read("");
	}

	public AdministradorMateriais() {
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 608, 520);
		this.setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtNomeMaterial = new JTextField();
		txtNomeMaterial.setBounds(172, 122, 222, 33);
		txtNomeMaterial.setBorder(BorderFactory.createEmptyBorder());
		txtNomeMaterial.setOpaque(false);
		contentPane.add(txtNomeMaterial);
		txtNomeMaterial.setColumns(10);

		btnBuscar = new JButton("");
		btnBuscar.setIcon(new ImageIcon(AdministradorMateriais.class.getResource("/Botoes/BuscarMaterial.png")));
		btnBuscar.setBounds(424, 122, 101, 35);
		btnBuscar.setContentAreaFilled(false);
		contentPane.add(btnBuscar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(8, 166, 574, 214);
		contentPane.add(scrollPane);

		TabelaMateriais = new JTable();
		TabelaMateriais.setModel(MaterialModel);
		TabelaMateriais.getColumnModel().getColumn(0).setResizable(false);
		TabelaMateriais.getColumnModel().getColumn(0).setPreferredWidth(45);
		TabelaMateriais.getColumnModel().getColumn(1).setResizable(false);
		TabelaMateriais.getColumnModel().getColumn(1).setPreferredWidth(74);
		scrollPane.setViewportView(TabelaMateriais);

		btnAdicionarMaterial = new JButton("");
		btnAdicionarMaterial
				.setIcon(new ImageIcon(AdministradorMateriais.class.getResource("/Botoes/botaoAdicionarMaterial.png")));
		btnAdicionarMaterial.setContentAreaFilled(false);
		btnAdicionarMaterial.setBounds(28, 391, 90, 33);
		contentPane.add(btnAdicionarMaterial);

		btnModificarDados = new JButton("");
		btnModificarDados
				.setIcon(new ImageIcon(AdministradorMateriais.class.getResource("/Botoes/botaoAlterarMaterial.png")));
		btnModificarDados.setContentAreaFilled(false);
		btnModificarDados.setBounds(128, 391, 90, 33);
		contentPane.add(btnModificarDados);

		JButton btnRemoverMaterial = new JButton("");
		btnRemoverMaterial
				.setIcon(new ImageIcon(AdministradorMateriais.class.getResource("/Botoes/botaoRemoverMaterial.png")));
		btnRemoverMaterial.setContentAreaFilled(false);
		btnRemoverMaterial.setBounds(243, 391, 90, 33);
		contentPane.add(btnRemoverMaterial);

		JButton btnVoltar = new JButton("");
		btnVoltar.setIcon(new ImageIcon(AdministradorMateriais.class.getResource("/Botoes/btnVoltar.png")));
		btnVoltar.setContentAreaFilled(false);
		btnVoltar.setBounds(465, 431, 90, 33);
		contentPane.add(btnVoltar);

		JLabel lblTela = new JLabel("");
		lblTela.setIcon(new ImageIcon(AdministradorMateriais.class.getResource("/Imagens/AdmMateriais.png")));
		lblTela.setBounds(0, 0, 592, 481);
		contentPane.add(lblTela);

		// -----------------------------------------------------------------------------

		btnBuscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BuscarMaterial();
			}
		});
		btnModificarDados.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AlterarMaterial();
			}
		});
		btnAdicionarMaterial.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AdicionarMaterial();
			}
		});
		btnRemoverMaterial.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ExcluirMaterial();
			}
		});
		btnVoltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Voltar();

			}
		});
	}

	private void BuscarMaterial() {
		String DescricaoMaterial = txtNomeMaterial.getText();
		MaterialModel.read(DescricaoMaterial);
	}

	private void AdicionarMaterial() {
		AdicionarMateriais Add = new AdicionarMateriais("Inserir");
		Add.setVisible(true);
	}

	private void AlterarMaterial() {
		int LinhaSelecionada = TabelaMateriais.getSelectedRow();
		if (LinhaSelecionada == -1) {
			JOptionPane.showMessageDialog(null, "Selecione um item para alterar!", "Atenção!",
					JOptionPane.WARNING_MESSAGE);
		} else {
			MaterialBean.setId_material(MaterialModel.RetornaIDMaterial(TabelaMateriais.getSelectedRow()));
			MaterialBean.setMaterial((String) TabelaMateriais.getValueAt(TabelaMateriais.getSelectedRow(), 0));
			MaterialBean.setPreco((double) TabelaMateriais.getValueAt(TabelaMateriais.getSelectedRow(), 1));
			AdicionarMateriais Add = new AdicionarMateriais("Alterar", MaterialBean);
			Add.setVisible(true);

			this.MaterialModel.read("");

		}
	}

	private void ExcluirMaterial() {
		int LinhaSelecionada = TabelaMateriais.getSelectedRow();
		if (LinhaSelecionada == -1) {
			JOptionPane.showMessageDialog(null, "Selecione algum material para remover!", "Aviso!",
					JOptionPane.WARNING_MESSAGE);
		} else {
			Object[] botoes = { "Sim", "Não" };
			int resposta = JOptionPane.showOptionDialog(null, "Deseja remover esse item? ", "Confirmação",
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, botoes, botoes[0]);
			if (resposta == 0) {
				int ID_Material = MaterialModel.RetornaIDMaterial(LinhaSelecionada);
				if (AdmDao.VerificarSituacaoMaterial(ID_Material) || AdmDao.VerificarPedido(ID_Material)) {
					JOptionPane.showMessageDialog(null, "Material esta sendo utilizado em alguma transação!", "Erro!",
							JOptionPane.WARNING_MESSAGE);
				} else {
					int Estoque_Atual = AdmDao.VerificarEstoque(ID_Material);
					MaterialBean MaterialBean = new MaterialBean();
					MaterialBean.setId_material(ID_Material);
					if (Estoque_Atual > 0) {
						int confirmacao = JOptionPane.showOptionDialog(null,
								"Atenção, há " + Estoque_Atual
										+ " fardos no estoque. Deseja remover o material mesmo assim?",
								"Confirmação", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, botoes,
								botoes[0]);

						if (confirmacao == 0) {
							System.out.println(MaterialBean.getId_material());
							MaterialDao.ExcluirMaterial(MaterialBean);
							JOptionPane.showMessageDialog(null, "Material removido com sucesso!", "Alerta!",
									JOptionPane.INFORMATION_MESSAGE);
						}
					} else {
						MaterialDao.ExcluirMaterial(MaterialBean);
						JOptionPane.showMessageDialog(null, "Material removido com sucesso!", "Alerta!",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
				MaterialModel.read("");
			}
		}
	}

	private void Voltar() {
		this.dispose();
	}
}
