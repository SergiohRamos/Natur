package Principal;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Beans.FornecedorBean;
import Model.HistoricoTableModel;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.ImageIcon;

public class HistoricoFornecedor extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tblHistorico;
	private JScrollPane scrollPane;
	private JButton btnVoltar;
	
	private FornecedorBean FornecedorBean;
	private HistoricoTableModel HistoricoFornecedor = new HistoricoTableModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HistoricoFornecedor frame = new HistoricoFornecedor();
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
	public HistoricoFornecedor(FornecedorBean FornecedorBean){
		this();
		this.FornecedorBean = FornecedorBean;
		this.HistoricoFornecedor.readHistorico(this.FornecedorBean.getId_fornecedor());
	}
	
	public HistoricoFornecedor() {
		setModal(true);
		setBounds(100, 100, 545, 496);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(7, 89, 525, 293);
		contentPane.add(scrollPane);
		
		tblHistorico = new JTable();
		tblHistorico.setModel(HistoricoFornecedor);
		scrollPane.setViewportView(tblHistorico);
		
		btnVoltar = new JButton("");
		btnVoltar.setIcon(new ImageIcon(HistoricoFornecedor.class.getResource("/Botoes/btnVoltar.png")));
		btnVoltar.setBounds(224, 404, 92, 36);
		contentPane.add(btnVoltar);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(HistoricoFornecedor.class.getResource("/Imagens/HistoricoFornecedor.png")));
		label.setBounds(0, 0, 539, 467);
		contentPane.add(label);
		
		btnVoltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Voltar();
			}
		});
	}
	private void Voltar(){
		this.dispose();
	}	
}
