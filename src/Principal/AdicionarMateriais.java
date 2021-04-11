package Principal;

import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Beans.MaterialBean;
import Dao.AdministradorDao;
import Dao.MaterialDao;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

public class AdicionarMateriais extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtMaterial;
	private JTextField txtPreco;
	private JButton btnAdicionar = new JButton();
	private JButton btnCancelar = new JButton("Cancelar");

	private AdministradorDao AdmDao = new AdministradorDao();
	private MaterialDao MaterialDao = new MaterialDao();

	private JTextField txtIDMaterial;
	private MaterialBean MaterialBean = new MaterialBean();
	private String Acao = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdicionarMateriais frame = new AdicionarMateriais();
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
	// Contrutor que manipula a ação dos botões
	public AdicionarMateriais(String Acao, MaterialBean MaterialBean) {
		this();
		this.Acao = "Alterar";
		this.btnAdicionar.setIcon(new ImageIcon(AdicionarMateriais.class.getResource("/Botoes/btnAlterar.png")));
		this.MaterialBean = MaterialBean;
		this.txtMaterial.setText(MaterialBean.getMaterial());
		this.txtPreco.setText(Double.toString(MaterialBean.getPreco()));
	}

	public AdicionarMateriais(String Acao) {
		this();
		this.btnAdicionar.setIcon(new ImageIcon(AdicionarMateriais.class.getResource("/Botoes/btnAdicionar.png")));
		this.Acao = "Adicionar";

	}

	public AdicionarMateriais() {
		setModal(true);
		setBounds(100, 100, 450, 300);
		this.setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtMaterial = new JTextField();
		txtMaterial.setBounds(124, 21, 168, 20);
		txtMaterial.setOpaque(false);
		txtMaterial.setBorder(BorderFactory.createEmptyBorder());
		contentPane.add(txtMaterial);
		txtMaterial.setColumns(10);

		txtPreco = new JTextField();
		txtPreco.setBounds(124, 74, 71, 20);
		txtPreco.setOpaque(false);
		txtPreco.setBorder(BorderFactory.createEmptyBorder());
		contentPane.add(txtPreco);
		txtPreco.setColumns(10);
		btnAdicionar.setIcon(new ImageIcon(AdicionarMateriais.class.getResource("/Botoes/btnAdicionar.png")));

		btnAdicionar.setBounds(144, 117, 88, 23);
		btnAdicionar.setContentAreaFilled(false);
		contentPane.add(btnAdicionar);
		btnCancelar.setIcon(new ImageIcon(AdicionarMateriais.class.getResource("/Botoes/btnVoltar.png")));

		btnCancelar.setText("");
		btnCancelar.setContentAreaFilled(false);
		btnCancelar.setBounds(335, 227, 89, 23);
		contentPane.add(btnCancelar);

		txtIDMaterial = new JTextField();
		txtIDMaterial.setVisible(false);
		txtIDMaterial.setEnabled(false);
		txtIDMaterial.setEditable(false);
		txtIDMaterial.setOpaque(false);
		txtIDMaterial.setBorder(BorderFactory.createEmptyBorder());
		txtIDMaterial.setBounds(36, 21, 48, 20);
		contentPane.add(txtIDMaterial);
		txtIDMaterial.setColumns(10);
		
		JLabel lblTela = new JLabel("");
		lblTela.setIcon(new ImageIcon(AdicionarMateriais.class.getResource("/Imagens/Adm  add Material.png")));
		lblTela.setBounds(0, 0, 434, 261);
		contentPane.add(lblTela);

		btnAdicionar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Acao.equals("Alterar")) {
					AlterarDados();
				} else if (Acao.equals("Adicionar")) {
					btnAdd();
				}
			}
		});
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CancelarOperacao();
			}
		});
	}

	private void btnAdd() {
		if (!(txtMaterial.getText().isEmpty() || txtPreco.getText().isEmpty())) {
			Object[] botoes = { "Sim", "Não" };
			int resposta = JOptionPane.showOptionDialog(null, "Deseja concluir esta operação? ", "Confirmação",
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, botoes, botoes[0]);
			if (resposta == 0) {
				boolean Numero;
				try {
					Integer.parseInt(txtPreco.getText());
					Numero = true;
				} catch (Exception e) {
					Numero = false;
				}

				if (Numero) {
					if (MaterialDao.VerificarExistenciaMaterial(txtMaterial.getText())) {
						JOptionPane.showMessageDialog(null, "Material já está cadastrado!", "Atenção!",
								JOptionPane.WARNING_MESSAGE);
					} else {
						String Material = txtMaterial.getText();
						double PrecoKG = Double.parseDouble(txtPreco.getText());
						int ChaveGerada = MaterialDao.InserirNovoMaterial(Material, PrecoKG);
						System.out.println("Id do novo material ------- " + ChaveGerada);

						AdministradorMateriais AdmMateriais = new AdministradorMateriais("AttTable");
						AdmMateriais.setVisible(true);
						this.dispose();
					}

				} else {
					JOptionPane.showMessageDialog(null, "Valor inserido no campo preço inválido!", "Erro!",
							JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Interrompido!", "Atenção", JOptionPane.PLAIN_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Preencha os campos para adicionar um novo material!", "Aviso!",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	private void CancelarOperacao() {
		this.dispose();
	}

	private void AlterarDados() {
		Object[] botoes = { "Sim", "Não" };
		int resposta = JOptionPane.showOptionDialog(null, "Deseja concluir essa alteração? ", "Confirmação",
				JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, botoes, botoes[0]);
		if (resposta == 0) {

			System.out.println(MaterialBean.getId_material());
			MaterialBean.setMaterial(txtMaterial.getText());
			MaterialBean.setPreco(Double.parseDouble(txtPreco.getText()));

			MaterialDao.AtualizarMaterial(MaterialBean);
			JOptionPane.showMessageDialog(null, "Alterado com sucesso!","Confirmação", JOptionPane.INFORMATION_MESSAGE);

			this.dispose();
		}
	}
}
