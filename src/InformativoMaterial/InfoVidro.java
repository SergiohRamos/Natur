package InformativoMaterial;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class InfoVidro extends JDialog {

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
			InfoVidro dialog = new InfoVidro();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public InfoVidro() {
		setTitle("Informa\u00E7\u00F5es Sobre Vidro");
		setType(Type.UTILITY);
		setBounds(100, 100, 842, 482);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(25, 242, 402, 166);
			contentPanel.add(scrollPane);
			{
				JEditorPane dtrpnOVidro = new JEditorPane();
				dtrpnOVidro.setEditable(false);
				scrollPane.setViewportView(dtrpnOVidro);
				dtrpnOVidro.setText("O vidro \u00E9 separado de acordo com seu tipo, espessura e classifica\u00E7\u00E3o.\r\nO passo seguinte \u00E9 a lavagem, na qual s\u00E3o retiradas todas as impurezas e restos que normalmente ficam na superf\u00EDcie do material. Na fase de tritura\u00E7\u00E3o, todo o material \u00E9 reduzido a micropeda\u00E7os que s\u00E3o submetidos a temperaturas alt\u00EDssimas, acima dos 1300\u00BAC. Como resultado, o material se funde e fica pronto para a etapa final: a modelagem.\r\nO vidro derretido/fundido \u00E9 male\u00E1vel, podendo assumir formatos que variam de acordo com o molde escolhido. \u00C9 nesta etapa que o material ganha a forma de novo produto, podendo assumir a forma de uma garrafa, pote ou enfeite de vidro, que ser\u00E1 inserido novamente na cadeia de   consumo.");
			}
		}
		{
			JLabel lblProcessoDeReciclagem = new JLabel("PROCESSO DE RECICLAGEM");
			lblProcessoDeReciclagem.setBounds(118, 217, 198, 14);
			contentPanel.add(lblProcessoDeReciclagem);
		}
		
		JEditorPane dtrpnEmbalagensDeVidro = new JEditorPane();
		dtrpnEmbalagensDeVidro.setEditable(false);
		dtrpnEmbalagensDeVidro.setText("Embalagens de vidro podem ser totalmente reaproveitadas no ciclo produtivo da reciclagem de vidro, sem nenhuma perda de material. \r\nA reutiliza\u00E7\u00E3o do vidro para a produ\u00E7\u00E3o de novas embalagens consome menor quantidade de energia e emite res\u00EDduos menos particulados de CO2, contribuindo para a preserva\u00E7\u00E3o do meio ambiente. ");
		dtrpnEmbalagensDeVidro.setBounds(25, 69, 402, 124);
		contentPanel.add(dtrpnEmbalagensDeVidro);
		
		JLabel lblBeneficiosDeReciclar = new JLabel("BENEFICIOS DE RECICLAR");
		lblBeneficiosDeReciclar.setBounds(115, 46, 217, 14);
		contentPanel.add(lblBeneficiosDeReciclar);
		
		table = new JTable();
		table.setEnabled(false);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"Material Reciclavel", "Material Não Reciclavel"},
				{"Garrafas", "Espelhos"},
				{"Potes De Conserva", "Vidros Temperados"},
				{"Frascos Em Geral", "Refratários "},
				{"Copos", "Louças De Porcelana"},
				{"Vidros De Janelas", "Cristais"},
				{"", "Lâmpadas"},
				{"", "Vidros Especiais"},
				{"", "Ampolas De Remédios"},
				{"", ""},
				{"", ""},
			},
			new String[] {
				"New column", "New column"
			}
		));
		table.setBounds(499, 140, 273, 145);
		contentPanel.add(table);
		
		JLabel lblOQuePosso = new JLabel("O QUE POSSO RECICLAR?");
		lblOQuePosso.setBounds(553, 115, 148, 14);
		contentPanel.add(lblOQuePosso);
	}
}
