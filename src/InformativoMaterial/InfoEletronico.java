package InformativoMaterial;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

public class InfoEletronico extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			InfoEletronico dialog = new InfoEletronico();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public InfoEletronico() {
		setTitle("Informa\u00E7\u00F5es Sobre Eletr\u00F4nicos");
		setType(Type.UTILITY);
		setBounds(100, 100, 842, 482);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 442, 826, 1);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		JEditorPane dtrpnApsEsteDescarte = new JEditorPane();
		dtrpnApsEsteDescarte.setEditable(false);
		dtrpnApsEsteDescarte.setText("Ap\u00F3s este descarte, os equipamentos s\u00E3o encaminhados para empresas brasileiras que se especializaram em desmontar estes equipamentos e reciclar os materiais que fazem parte do Res\u00EDduo Eletr\u00F4nico. Os eletr\u00F4nicos contem partes e pe\u00E7as feitas de pl\u00E1stico, vidro e metal, al\u00E9m de outras subst\u00E2ncias.");
		dtrpnApsEsteDescarte.setBounds(20, 290, 367, 116);
		getContentPane().add(dtrpnApsEsteDescarte);
		
		JLabel lblComoFeita = new JLabel("COMO \u00C9 FEITA A RECICLAGEM");
		lblComoFeita.setBounds(118, 265, 189, 14);
		getContentPane().add(lblComoFeita);
		
		JEditorPane dtrpnAlmDeGarantir = new JEditorPane();
		dtrpnAlmDeGarantir.setEditable(false);
		dtrpnAlmDeGarantir.setText("Al\u00E9m de garantir uma quantidade menor de lixo para o meio ambiente absorver, a reciclagem desse tipo de res\u00EDduo gera emprego e renda para muitas pessoas em muitas comunidades.");
		dtrpnAlmDeGarantir.setBounds(427, 117, 367, 116);
		getContentPane().add(dtrpnAlmDeGarantir);
		
		JLabel lblBeneficiosDeReciclar = new JLabel("BENEFICIOS DE RECICLAR ");
		lblBeneficiosDeReciclar.setBounds(533, 92, 168, 14);
		getContentPane().add(lblBeneficiosDeReciclar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 116, 367, 123);
		getContentPane().add(scrollPane);
		
		JEditorPane dtrpnParaNoProvocar = new JEditorPane();
		dtrpnParaNoProvocar.setEditable(false);
		scrollPane.setViewportView(dtrpnParaNoProvocar);
		dtrpnParaNoProvocar.setText("Para n\u00E3o provocar a contamina\u00E7\u00E3o e polui\u00E7\u00E3o do meio ambiente, o correto \u00E9 fazer o descarte de lixo eletr\u00F4nico em locais apropriados como, por exemplo, empresas e cooperativas que atuam na \u00E1rea de reciclagem.\r\nCelulares e suas baterias podem ser entregues nas empresas de telefonia celular. Elas encaminham estes res\u00EDduos de forma a n\u00E3o provocar danos ao meio ambiente.\r\nOutra op\u00E7\u00E3o \u00E9 doar equipamentos em boas condi\u00E7\u00F5es, mas que n\u00E3o est\u00E3o mais em uso, para entidades sociais que atuam na \u00E1rea de inclus\u00E3o digital. Al\u00E9m de n\u00E3o contaminar o meio ambiente, o ato ajudar\u00E1 pessoas que precisam.\r\n");
		
		JLabel lblDescarteCorreto = new JLabel("DESCARTE CORRETO E REUTILIZA\u00C7\u00C3O");
		lblDescarteCorreto.setBounds(92, 93, 219, 14);
		getContentPane().add(lblDescarteCorreto);
		
		JEditorPane dtrpnCercaDe = new JEditorPane();
		dtrpnCercaDe.setEditable(false);
		dtrpnCercaDe.setText("Cerca de 40 milh\u00F5es de toneladas de lixo eletr\u00F4nico s\u00E3o gerados por ano no mundo.\r\nEntre os pa\u00EDses emergentes, o Brasil \u00E9 o pa\u00EDs que mais gera lixo eletr\u00F4nico.\r\nA cada ano, o Brasil descarta: cerca de 97 mil toneladas m\u00E9tricas de computadores; 2,2 mil toneladas de celulares; 17,2 mil toneladas de impressoras.\r\n");
		dtrpnCercaDe.setBounds(427, 290, 367, 116);
		getContentPane().add(dtrpnCercaDe);
		
		JLabel lblMaisInformaes = new JLabel("MAIS INFORMA\u00C7\u00D5ES");
		lblMaisInformaes.setBounds(547, 265, 135, 14);
		getContentPane().add(lblMaisInformaes);
	}
}
