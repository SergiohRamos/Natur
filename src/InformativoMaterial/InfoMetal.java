package InformativoMaterial;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class InfoMetal extends JDialog {

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
			InfoMetal dialog = new InfoMetal();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public InfoMetal() {
		setTitle("Informa\u00E7\u00F5es Sobre Metais\r\n");
		setType(Type.UTILITY);
		setBounds(100, 100, 842, 482);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JEditorPane dtrpnOProcessoDe = new JEditorPane();
			dtrpnOProcessoDe.setEditable(false);
			dtrpnOProcessoDe.setText("O processo de reciclagem do metal depende primeiramente das fases de coleta e separa\u00E7\u00E3o dos materiais. Os res\u00EDduos met\u00E1licos coletados s\u00E3o separados nos centros de triagem entre ferrosos e n\u00E3o ferrosos e tamb\u00E9m por tipo e caracter\u00EDsticas. \r\nComo os metais ferrosos possuem propriedades magn\u00E9ticas, sua separa\u00E7\u00E3o dos demais res\u00EDduos pode ser feita por meio do uso de eletro\u00EDm\u00E3s. Os res\u00EDduos j\u00E1 separados s\u00E3o prensados, classificados e encaminhados para as esta\u00E7\u00F5es de reciclagem espec\u00EDficas. Nessas esta\u00E7\u00F5es os res\u00EDduos s\u00E3o livrados das impurezas, triturados, derretidos e transformados em novos produtos.");
			dtrpnOProcessoDe.setBounds(44, 252, 370, 146);
			contentPanel.add(dtrpnOProcessoDe);
		}
		{
			JLabel lblProcessoDeReciclagem = new JLabel("PROCESSO DE RECICLAGEM");
			lblProcessoDeReciclagem.setBounds(149, 219, 198, 14);
			contentPanel.add(lblProcessoDeReciclagem);
		}
		{
			JEditorPane dtrpnAGrandeVantagem = new JEditorPane();
			dtrpnAGrandeVantagem.setEditable(false);
			dtrpnAGrandeVantagem.setText("A grande vantagem do reaproveitamento de metais \u00E9 que eles s\u00E3o 100% recicl\u00E1veis, e esse processo pode ser feito in\u00FAmeras vezes, sem que o material perca a maioria das suas propriedades. \r\nAo contr\u00E1rio do papel e de outros materiais, o metal pode passar infinitas vezes pela reciclagem sem perder suas propriedades. \r\nNo caso do alum\u00EDnio, esse processo ainda pode poupar a extra\u00E7\u00E3o da bauxita. Por lata reciclada, \u00E9 produzida energia suficiente para deixar acesa uma l\u00E2mpada de 100 watts por 20 horas.");
			dtrpnAGrandeVantagem.setBounds(44, 90, 370, 118);
			contentPanel.add(dtrpnAGrandeVantagem);
		}
		{
			JLabel lblBeneficiosDeReciclar = new JLabel("BENEFICIOS DE RECICLAR");
			lblBeneficiosDeReciclar.setBounds(140, 65, 221, 14);
			contentPanel.add(lblBeneficiosDeReciclar);
		}
		{
			table = new JTable();
			table.setEnabled(false);
			table.setModel(new DefaultTableModel(
				new Object[][] {
					{"Material Reciclavel", "Material Não Reciclavel"},
					{"Tampinha de Garrafa", "Latas De Tinta"},
					{"Latinhas ", "Verniz"},
					{"Enlatados", "solventes Químicos"},
					{"Talheres De Metais", "Inseticidas"},
					{"Tampas De Panelas", "Aerossóis"},
					{"Panelas Sem Cabos", "Esponjas De Aço"},
					{"Pregos", "Clipes"},
					{"Embalagens Descartáveis", "Tachinhas"},
					{"Papel Alumínio", "Grampos"},
				},
				new String[] {
					"Material Reciclavel", "Material Não Reciclavel"
				}
			));
			table.setBounds(503, 136, 276, 160);
			contentPanel.add(table);
		}
		{
			JLabel lblOQuePosso = new JLabel("O QUE POSSO RECICLAR?");
			lblOQuePosso.setBounds(558, 111, 188, 14);
			contentPanel.add(lblOQuePosso);
		}
	}

}
