package InformativoMaterial;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.ScrollPaneConstants;

public class InfoPlastico extends JDialog {

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
			InfoPlastico dialog = new InfoPlastico();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public InfoPlastico() {
		setResizable(false);
		setType(Type.UTILITY);
		setTitle("Informa\u00E7\u00F5es Sobre  Pl\u00E1stico\r\n");
		setBounds(100, 100, 844, 482);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		table = new JTable();
		table.setEnabled(false);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"Material Reciclavel", "Material Não Reciclavel"},
				{"Potes", "Fraldas Descartáveis"},
				{"Embalagens", "Embalagens Metalizadas"},
				{"Copos", "Adesivos"},
				{"Garrafas", "Cabos de Panelas"},
				{"Frascos De Produtos De Limpeza", "Espuma"},
				{"Frascos De Produtos De Higiene Pessoal", "Esponja De Cozinha"},
				{"Sacos e Sacolas", "Tomadas"},
				{"Utensílios Plásticos", "Plásticos Termofixos"},
				{"Brinquedos De Plásticos", "Acrílico"},
				{"Isopor", ""},
			},
			new String[] {
				"New column", "New column"
			}
		));
		table.setBounds(515, 158, 288, 176);
		contentPanel.add(table);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 118, 449, 107);
		contentPanel.add(scrollPane_1);
		
		JEditorPane dtrpnAsMaioresVantagens = new JEditorPane();
		dtrpnAsMaioresVantagens.setEditable(false);
		scrollPane_1.setViewportView(dtrpnAsMaioresVantagens);
		dtrpnAsMaioresVantagens.setText("As maiores vantagens da reciclagem de embalagens de pl\u00E1stico s\u00E3o a diminui\u00E7\u00E3o de res\u00EDduos urbanos e a economia de recursos naturais. \r\nA n\u00EDvel energ\u00E9tico este processo \u00E9 ben\u00E9fico, dado que economiza petr\u00F3leo (1 tonelada de pl\u00E1stico permite economizar 130 Kg de petr\u00F3leo).\r\nEvita o abate de \u00E1rvores (1 tonelada de embalagens cartonadas permite evitar o abate de 20 \u00E1rvores).");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 261, 449, 181);
		contentPanel.add(scrollPane);
		
		JEditorPane dtrpnOPrimeiroPasso = new JEditorPane();
		scrollPane.setViewportView(dtrpnOPrimeiroPasso);
		dtrpnOPrimeiroPasso.setEditable(false);
		dtrpnOPrimeiroPasso.setToolTipText("");
		dtrpnOPrimeiroPasso.setText("O primeiro passo a ser seguido \u00E9 realizar a separa\u00E7\u00E3o dos materiais provenien tes de diversas fontes de acordo com a simbologia dos materiais pl\u00E1sticos e do tipo de material do qual esse res\u00EDduo \u00E9 feito. As cooperativas de catadores   normalmente possuem seu pr\u00F3prio processo de triagem, que \u00E9 feito separando os materiais por tipo e posteriormente por cor, pois valoriza o mesmo no momento da venda.\r\nOs materiais previamente separados s\u00E3o ent\u00E3o submetidos ao processo de   moagem mec\u00E2nica, que \u00E9 feito comumente em moinhos de facas, fragmentan  do os res\u00EDduos em part\u00EDculas de menor dimens\u00E3o, que s\u00E3o chamadas de flakes.\r\nOs flakes passam por um processo de lavagem que \u00E9 feito utilizando \u00E1gua. Neste momento, a separa\u00E7\u00E3o dos materiais \u00E9 feita atrav\u00E9s da diferen\u00E7a de densidade\r\nAp\u00F3s serem lavados e separados por densidade, os flakes s\u00E3o ent\u00E3o submetidos ao processo de secagem, que \u00E9 feito com aux\u00EDlios de secadores que utilizam a circula\u00E7\u00E3o de ar quente para efetuar a secagem de forma homog\u00EAnea. \r\nOs flakes s\u00E3o inseridos no funil de alimenta\u00E7\u00E3o de uma m\u00E1quina chamada extrusora, onde passam por um cilindro de metal que possui resist\u00EAncias em seu exterior, que promovem o aquecimento deste cilindro, previamente programado de acordo com o tipo de material que vai ser processado. No interior deste cilindro h\u00E1 uma rosca transportadora, que faz movimentos rotacionais transportando o material ao longo do cilindro e promovendo al\u00E9m do transporte, a homogeneiza\u00E7\u00E3o e consequentemente a plastifica\u00E7\u00E3o do material . Em seguida esse material \u00E9 for\u00E7ado a passar por orif\u00EDcios que d\u00E3o forma ao material, ou seja, ele \u00E9 extrudado atrav\u00E9s de uma matriz, formando os filamentos, que s\u00E3o resfriados em uma banheira contendo \u00E1gua circulante, em seguida passam por um secador para retirada da umidade e s\u00E3o ent\u00E3o granulados e embalados.\r\n");
		
		JLabel lblProcessoDeReciclagem = new JLabel("PROCESSO DE RECICLAGEM");
		lblProcessoDeReciclagem.setBounds(136, 236, 163, 14);
		contentPanel.add(lblProcessoDeReciclagem);
		
		JLabel lblBeneficiosDeReciclar = new JLabel("BENEFICIOS DE RECICLAR");
		lblBeneficiosDeReciclar.setBounds(136, 90, 163, 14);
		contentPanel.add(lblBeneficiosDeReciclar);
		
		JLabel lblOQueEu = new JLabel("O QUE EU POSSO RECICLAR?");
		lblOQueEu.setBounds(548, 133, 183, 14);
		contentPanel.add(lblOQueEu);
	}
}
