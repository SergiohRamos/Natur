package TelasPerfil;

import java.awt.EventQueue;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import Beans.FornecedorBean;
import Beans.FuncionarioBean;
import Dao.FuncionarioDao;
import Dao.PerfilFornecedorDao;
import Principal.JanelaPrincipal;
import ResizeImage.RedimencionarImagemPerfil;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.AlphaComposite;
import java.awt.Color;

public class PerfilFornecedor extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtNome;
	private JTextField txtCPF;
	private JTextField txtUf;
	private JTextField txtCep;
	private JTextField txtEmail;
	private JTextField txtTelefone;
	private FornecedorBean FornecedorBean = new FornecedorBean();
	private FuncionarioBean FuncionarioBean = new FuncionarioBean();
	private FuncionarioDao FuncionarioDao = new FuncionarioDao();
	
	private PerfilFornecedorDao pfd = new PerfilFornecedorDao();
	private JLabel lblImagemPerfil = new JLabel("");
	private JTextField txtPath;
	private JButton btnVoltar;
	private File arquivo;
	private byte[] imgBytes;
	private JTextField txtCidade;

	private String Entidade;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PerfilFornecedor frame = new PerfilFornecedor();
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
	// Construtor que seta os dados em seus respectivos campos
	public PerfilFornecedor(String Entidade, Object Bean) {
		this();
		this.Entidade = Entidade;
		if (Entidade.equals("Fornecedor")) {
			this.FornecedorBean = (FornecedorBean) Bean;
			this.txtNome.setText(FornecedorBean.getNome_fornecedor());
			this.txtCPF.setText(FornecedorBean.getCpf_fornecedor());
			this.txtUf.setText(FornecedorBean.getUf_fornecedor());
			this.txtCidade.setText(FornecedorBean.getCidade_fornecedor());
			this.txtCep.setText(FornecedorBean.getCep_fornecedor());
			this.txtEmail.setText(FornecedorBean.getEmail_fornecedor());
			this.txtTelefone.setText(FornecedorBean.getTele_fornecedor());
		} else {
			this.FuncionarioBean = (FuncionarioBean) Bean;
			this.txtNome.setText(FuncionarioBean.getNome_Funcionario());
			this.txtCPF.setText(FuncionarioBean.getCpf_Funcionario());
			this.txtUf.setText(FuncionarioBean.getUf_Funcionario());
			this.txtCidade.setText(FuncionarioBean.getCidade_Funcionario());
			this.txtCep.setText(FuncionarioBean.getCep_Funcionario());
			this.txtEmail.setText(FuncionarioBean.getEmail_Funcionario());
			this.txtTelefone.setText(FuncionarioBean.getTelefone_Funcionario());
		}
	}

	public PerfilFornecedor() {
		setBounds(100, 100, 1012, 705);
		getContentPane().setLayout(null);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1012, 705);
		getContentPane().add(panel);
		panel.setLayout(null);

		txtNome = new JTextField();
		txtNome.setBounds(354, 200, 290, 30);
		panel.add(txtNome);
		txtNome.setColumns(10);
		txtNome.setOpaque(false);
		txtNome.setBorder(BorderFactory.createEmptyBorder());

		txtCPF = new JTextField();
		txtCPF.setBounds(354, 278, 290, 27);
		panel.add(txtCPF);
		txtCPF.setColumns(10);
		txtCPF.setOpaque(false);
		txtCPF.setBorder(BorderFactory.createEmptyBorder());

		txtUf = new JTextField();
		txtUf.setBounds(354, 360, 110, 30);
		panel.add(txtUf);
		txtUf.setColumns(10);
		txtUf.setOpaque(false);
		txtUf.setBorder(BorderFactory.createEmptyBorder());

		txtCidade = new JTextField();
		txtCidade.setBounds(504, 362, 145, 26);
		panel.add(txtCidade);
		txtCidade.setOpaque(false);
		txtCidade.setBorder(BorderFactory.createEmptyBorder());
		txtCidade.setColumns(10);

		txtCep = new JTextField();
		txtCep.setBounds(354, 440, 115, 30);
		panel.add(txtCep);
		txtCep.setColumns(10);
		txtCep.setOpaque(false);
		txtCep.setBorder(BorderFactory.createEmptyBorder());

		txtEmail = new JTextField();
		txtEmail.setBounds(354, 512, 290, 30);
		panel.add(txtEmail);
		txtEmail.setColumns(10);
		txtEmail.setOpaque(false);
		txtEmail.setBorder(BorderFactory.createEmptyBorder());

		txtTelefone = new JTextField();
		txtTelefone.setBounds(504, 440, 145, 30);
		panel.add(txtTelefone);
		txtTelefone.setColumns(10);
		txtTelefone.setOpaque(false);
		txtTelefone.setBorder(BorderFactory.createEmptyBorder());

		JButton btnAlterarDados = new JButton("");
		btnAlterarDados.setIcon(new ImageIcon(PerfilFornecedor.class.getResource("/Botoes/btnAlterarDados.png")));
		btnAlterarDados.setBounds(417, 567, 163, 39);
		panel.add(btnAlterarDados);
		btnAlterarDados.setContentAreaFilled(false);

		JButton btnCarregarImagem = new JButton("");
		btnCarregarImagem.setEnabled(false);
		btnCarregarImagem.setVisible(false);
		btnCarregarImagem.setContentAreaFilled(false);
		btnCarregarImagem.setBounds(967, 180, 223, 206);
		panel.add(btnCarregarImagem);

		txtPath = new JTextField();
		txtPath.setEditable(false);
		txtPath.setEnabled(false);
		txtPath.setVisible(false);
		txtPath.setBounds(977, 407, 213, 20);
		panel.add(txtPath);
		txtPath.setColumns(10);

		btnVoltar = new JButton("");
		btnVoltar.setIcon(new ImageIcon(PerfilFornecedor.class.getResource("/Botoes/btnVoltar.png")));
		btnVoltar.setContentAreaFilled(false);
		btnVoltar.setBounds(837, 607, 91, 39);
		panel.add(btnVoltar);

		lblImagemPerfil.setBackground(Color.GRAY);
		lblImagemPerfil.setBounds(967, 180, 223, 206);
		panel.add(lblImagemPerfil);

		JLabel lblImagem = new JLabel("");
		lblImagem.setIcon(new ImageIcon(PerfilFornecedor.class.getResource("/Imagens/PerfilFornecedor.jpg")));
		lblImagem.setBounds(0, 0, 1304, 705);
		panel.add(lblImagem);

		// --------------------------------------------------------------------------------------------------------------------------------//

		btnCarregarImagem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BuscarImg();
				try {
					getBytes(arquivo);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		btnVoltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Voltar();
			}
		});
		btnAlterarDados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Entidade.equals("Fornecedor")) {

					pfd.PerfilFornecedor(txtCPF.getText(), FornecedorBean);

					FornecedorBean.setNome_fornecedor(FornecedorBean.getNome_fornecedor());
					FornecedorBean.setCep_fornecedor(txtCep.getText());
					FornecedorBean.setUf_fornecedor(txtUf.getText());
					FornecedorBean.setCidade_fornecedor(txtCidade.getText());
					FornecedorBean.setCpf_fornecedor(txtCPF.getText());
					FornecedorBean.setTele_fornecedor(txtTelefone.getText());
					FornecedorBean.setEmail_fornecedor(txtEmail.getText());
					FornecedorBean.setImagemPerfil(getImagemBytes());

					pfd.AtualizarDadosFornecedor(FornecedorBean);
				} else{
					FuncionarioBean.setNome_Funcionario(txtNome.getText());
					FuncionarioBean.setCep_Funcionario(txtCep.getText());
					FuncionarioBean.setUf_Funcionario(txtUf.getText());
					FuncionarioBean.setCidade_Funcionario(txtCidade.getText());
					FuncionarioBean.setCpf_Funcionario(txtCPF.getText());
					FuncionarioBean.setTelefone_Funcionario(txtTelefone.getText());
					FuncionarioBean.setEmail_Funcionario(txtEmail.getText());
					
					FuncionarioDao.AlterarDadosFuncionario(FuncionarioBean);
				}

				JOptionPane.showMessageDialog(null, "Alterado com sucesso!", "Confirmação",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});

	}

	@SuppressWarnings("static-access")
	public void BuscarImg() {
		JFileChooser filechooser = new JFileChooser();
		filechooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		FileNameExtensionFilter filtro = new FileNameExtensionFilter("Abrir Imagem", "jpg", "png");
		filechooser.setFileFilter(filtro);
		filechooser.addChoosableFileFilter(filtro);
		filechooser.setAcceptAllFileFilterUsed(false);
		filechooser.setDialogType(JFileChooser.OPEN_DIALOG);
		filechooser.setCurrentDirectory(new File("C:/"));

		int i = filechooser.showSaveDialog(null);

		if (i == 1) {
			JOptionPane.showMessageDialog(null, "Interrompido");
		} else {
			if (lblImagemPerfil.getIcon() != null) {
				lblImagemPerfil.setIcon(null);
				arquivo = null;
			}
			arquivo = filechooser.getSelectedFile();
			txtPath.setText(arquivo.getAbsolutePath());

			String InputImagePath = arquivo.getPath();
			RedimencionarImagemPerfil Resize = new RedimencionarImagemPerfil();

			try {
				int scaledWidth = lblImagemPerfil.getWidth();
				int scaledHeight = lblImagemPerfil.getHeight();
				Resize.resize(InputImagePath, InputImagePath, scaledWidth, scaledHeight);
			} catch (IOException ex) {
				System.out.println("Erro ao redimencionar a imagem! \n Erro: " + ex.getMessage());
				ex.printStackTrace();
			}
			lblImagemPerfil.setIcon(new ImageIcon(InputImagePath));
		}
	}

	private byte[] getBytes(File file) throws FileNotFoundException, IOException {
		byte[] buffer = new byte[1024];
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int read;
		FileInputStream f = new FileInputStream(file);
		while ((read = f.read(buffer)) != -1) {
			out.write(buffer, 0, read);
		}
		f.close();
		out.close();
		return out.toByteArray();
	}

	public void toFile(byte[] byteImg, File arquivoImg) {
		try {
			FileOutputStream out = new FileOutputStream(arquivoImg);
			out.write(byteImg);
			out.close();
		} catch (Exception e) {
			System.out.println("Falha ao carregar a imagem! \n Erro: " + e.getMessage());
			e.printStackTrace();
		}
	}

	private File selecionarImagem() {
		JFileChooser filechooser = new JFileChooser();
		FileNameExtensionFilter filtro = new FileNameExtensionFilter("Imagens em JPG e PNG", "jpg", "png");
		filechooser.addChoosableFileFilter(filtro);
		filechooser.setAcceptAllFileFilterUsed(false);
		filechooser.setDialogType(JFileChooser.OPEN_DIALOG);
		filechooser.showOpenDialog(this);

		return filechooser.getSelectedFile();
	}

	private byte[] getImagemBytes() {
		boolean isPng = false;

		if (arquivo != null) {
			isPng = arquivo.getName().endsWith("png");
			try {
				BufferedImage image = ImageIO.read(arquivo);
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				int type = BufferedImage.TYPE_INT_RGB;

				if (isPng) {
					type = BufferedImage.BITMASK;
				}
				BufferedImage novaImagem = new BufferedImage(lblImagemPerfil.getWidth() - 5,
						lblImagemPerfil.getHeight() - 10, type);
				Graphics2D g2d = novaImagem.createGraphics();
				g2d.setComposite(AlphaComposite.Src);
				g2d.drawImage(image, 0, 0, lblImagemPerfil.getWidth() - 5, lblImagemPerfil.getHeight() - 10, null);

				if (isPng) {
					ImageIO.write(novaImagem, "png", out);
				} else {
					ImageIO.write(novaImagem, "jpg", out);
				}
				out.flush();
				byte[] byteArray = out.toByteArray();
				out.close();
				return byteArray;
			} catch (IOException e) {
				System.out.println("Erro ao converter imagem para bytes! \n Erro: " + e.getMessage());
				e.printStackTrace();
			}
		}
		return null;
	}

	private void Voltar() {
		JanelaPrincipal janelaPrincipal = new JanelaPrincipal(Entidade, txtCPF.getText());
		janelaPrincipal.setVisible(true);
		dispose();
	}

}
