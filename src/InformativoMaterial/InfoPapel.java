package InformativoMaterial;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.JTextPane;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import java.awt.Color;

public class InfoPapel extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			InfoPapel dialog = new InfoPapel();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public InfoPapel() {
		setModal(true);
		setResizable(false);
		setType(Type.UTILITY);
		setTitle("Informa\u00E7\u00F5es Sobre o Papel");
		setBounds(100, 100, 842, 483);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		table = new JTable();
		table.setEnabled(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"Material Reciclavel", "Material Não Reciclavel"},
				{"Jornais", "Papéis Engordurados"},
				{"Revistas", "Fitas"},
				{"Envelopes", "Etiquetas Adesivas"},
				{"Cadernos", "Papéis Metalizados"},
				{"Impressos", "Papéis Plastificados"},
				{"Rascunhos", "Papéis Parafinados"},
				{"Papel De Fax", "Fotografias"},
				{"Fotocópias", },
				{"Listas Telefônicas", },
				{"Cartazes", },
				{"Aparas De Papel", },
				{"Caixas De Papelão", },
			},
			new String[] {
				"Material Reciclavel", "Material Não Reciclavel"
				
			}
		));
		table.setBounds(465, 140, 269, 163);
		contentPanel.add(table);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 245, 366, 169);
		contentPanel.add(scrollPane);
		
		JTextPane txtpnOProcessoDe = new JTextPane();
		txtpnOProcessoDe.setBackground(new Color(255, 255, 255));
		scrollPane.setViewportView(txtpnOProcessoDe);
		txtpnOProcessoDe.setEditable(false);
		txtpnOProcessoDe.setText("O processo de reciclagem do papel come\u00E7a pela sele\u00E7\u00E3o do material, com a separa\u00E7\u00E3o do material que ser\u00E1 utilizado para ser colocado em contentores que ir\u00E3o gerar fibras novas para produ\u00E7\u00E3o de papel a partir das fibras velhas. Normalmente as fibras recicladas s\u00E3o misturadas a pastas de refinador com fibras virgens. Essa nova mistura de pasta de celulose \u00E9 submetida a um processo de refinamento e depura\u00E7\u00E3o onde o novo papel ir\u00E1 ganhar mais resist\u00EAncia e qualidade.\r\nNeste processo s\u00E3o retirados excessos de materiais contaminantes para o papel reciclado. Ao final a pasta \u00E9 levada para a secagem e formata\u00E7\u00E3o. Ap\u00F3s esse processo a reciclagem do papel se conclui e o novo material poder\u00E1 novamente ser introduzido ao mercado consumidor.");
		
		JEditorPane dtrpnAjudaAConter = new JEditorPane();
		dtrpnAjudaAConter.setEditable(false);
		dtrpnAjudaAConter.setText("Ajuda a conter o desmatamento e evita o corte de 30 \u00E1rvores       adultas. \r\nA produ\u00E7\u00E3o de papel   reciclado economiza at\u00E9 80% de energia   comparado \u00E0 produ\u00E7\u00E3o do papel virgem. \r\nO papel  feito com material reciclado reduz em 74% os poluentes liberados no ar e em 35% os  despejados na \u00E1gua.");
		dtrpnAjudaAConter.setBounds(25, 100, 364, 101);
		contentPanel.add(dtrpnAjudaAConter);
		
		JLabel lblBeneficiosDeReciclar = new JLabel("Beneficios De Reciclar ");
		lblBeneficiosDeReciclar.setBounds(133, 75, 137, 14);
		contentPanel.add(lblBeneficiosDeReciclar);
		
		JLabel lblProcessoDeReciclagem = new JLabel("Processo De Reciclagem");
		lblProcessoDeReciclagem.setBounds(133, 222, 148, 14);
		contentPanel.add(lblProcessoDeReciclagem);
		
		JLabel lblOQueEu = new JLabel("O Que Eu Posso Reciclar?");
		lblOQueEu.setBounds(527, 115, 148, 14);
		contentPanel.add(lblOQueEu);
	}
}
