package Principal;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.MaterialEstoqueTableModel;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

public class AdmVerificarEstoque extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable ConsultarEstoque;
	private JTextField txtMaterial;
	private MaterialEstoqueTableModel MaterialEstoque = new MaterialEstoqueTableModel();
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdmVerificarEstoque frame = new AdmVerificarEstoque();
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
	public AdmVerificarEstoque() {
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 625, 475);
		this.setLocationRelativeTo(null);
		
		BuscarEstoque();
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 160, 589, 198);
		contentPane.add(scrollPane);
		
		ConsultarEstoque = new JTable();
		ConsultarEstoque.setModel(MaterialEstoque);
		scrollPane.setViewportView(ConsultarEstoque);
		
		txtMaterial = new JTextField();
		txtMaterial.setBounds(201, 126, 133, 23);
		txtMaterial.setOpaque(false);
		txtMaterial.setBorder(BorderFactory.createEmptyBorder());
		contentPane.add(txtMaterial);
		txtMaterial.setColumns(10);
		
		JButton btnBuscar = new JButton("");
		btnBuscar.setIcon(new ImageIcon(AdmVerificarEstoque.class.getResource("/Botoes/BuscarMaterial.png")));
		btnBuscar.setBounds(344, 115, 100, 34);
		btnBuscar.setContentAreaFilled(false);
		contentPane.add(btnBuscar);
		
		JButton btnVoltar = new JButton("");
		btnVoltar.setIcon(new ImageIcon(AdmVerificarEstoque.class.getResource("/Botoes/btnVoltar.png")));
		btnVoltar.setContentAreaFilled(false);
		btnVoltar.setBounds(498, 389, 89, 36);
		contentPane.add(btnVoltar);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(AdmVerificarEstoque.class.getResource("/Imagens/AdmEstoque.png")));
		label.setBounds(0, 0, 609, 436);
		contentPane.add(label);
		
		btnBuscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				BuscarEstoqueBtn();
			}
		});
		btnVoltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FecharJanela();
			}
		});
	}
	private void BuscarEstoque() {
		MaterialEstoque.readEstoq("");
	}
	
	private void BuscarEstoqueBtn(){
		MaterialEstoque.readEstoq(txtMaterial.getText());
	}
	
	private void FecharJanela() {
		this.dispose();
	}
}
