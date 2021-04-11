package Dao;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Beans.FornecedorBean;
import Conexao.Conexao;

public class PerfilFornecedorDao {

	public void PerfilFornecedor(String cpf, FornecedorBean FornecedorBean) {

		if (cpf != null) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = "";

			try {
				conn = Conexao.getConection();

				sql = "SELECT id_fornecedor,nome_fornecedor, cpf_fornecedor, uf_fornecedor, cidade_fornecedor, cep_fornecedor, tele_fornecedor, email_fornecedor  ";
				sql += "FROM fornecedor WHERE cpf_fornecedor = ? ";

				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, cpf);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					FornecedorBean.setId_fornecedor(rs.getInt("id_fornecedor"));
					FornecedorBean.setNome_fornecedor(rs.getString("nome_fornecedor"));
					FornecedorBean.setCpf_fornecedor(rs.getString("cpf_fornecedor"));
					FornecedorBean.setUf_fornecedor(rs.getString("uf_fornecedor"));
					FornecedorBean.setCidade_fornecedor(rs.getString("cidade_fornecedor"));
					FornecedorBean.setCep_fornecedor(rs.getString("cep_fornecedor"));
					FornecedorBean.setTele_fornecedor(rs.getString("tele_fornecedor"));
					FornecedorBean.setEmail_fornecedor(rs.getString("email_fornecedor"));
				}
				Conexao.FecharConexao(conn, pstmt, rs);

			} catch (Exception ex) {
				System.out.println("Erro no perfil do fornecedor. \n Erro: " + ex.getMessage());
			}
		} else {
			System.out.println("Valor 'CPF' enviado por parâmetro é nulo!");
		}
	}

	public void AtualizarDadosFornecedor(FornecedorBean FornecedorBean) {

		PreparedStatement pstmt = null;
		String sql = "";
		if (FornecedorBean != null) {
			Connection conn = null;
			try {
				conn = Conexao.getConection();

				sql = "UPDATE fornecedor SET nome_fornecedor=?, cpf_fornecedor=?,"
						+ " uf_fornecedor=?,cidade_fornecedor = ? ,cep_fornecedor=?, tele_fornecedor=?, email_fornecedor=? WHERE id_fornecedor = ?";

				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, FornecedorBean.getNome_fornecedor());
				pstmt.setString(2, FornecedorBean.getCpf_fornecedor());
				pstmt.setString(3, FornecedorBean.getUf_fornecedor());
				pstmt.setString(4, FornecedorBean.getCidade_fornecedor());
				pstmt.setString(5, FornecedorBean.getCep_fornecedor());
				pstmt.setString(6, FornecedorBean.getTele_fornecedor());
				pstmt.setString(7, FornecedorBean.getEmail_fornecedor());
				pstmt.setInt(8, FornecedorBean.getId_fornecedor());
				pstmt.executeUpdate();

				Conexao.FecharConexao(conn);
			} catch (Exception ex) {
				System.out.println("Falha na atualição do perfil do fornecedor. \n Erro: " + ex.getMessage());
			}
		} else {
			System.out.println("Perfil enviado por parâmetro está vazio!");
		}
	}

	public boolean checkEstadoFornecedor(String cpf) {
		boolean checkLogado = false;

		if (cpf != null) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = "";

			try {
				conn = Conexao.getConection();

				sql = "SELECT id_fornecedor, nome_fornecedor, cpf_fornecedor, dt_nasc_fornecedor, end_fornecedor, cep_fornecedor, tele_fornecedor, sex_fornecedor, email_fornecedor  ";
				sql += "FROM fornecedor WHERE cpf_fornecedor = ? ";

				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, cpf);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					checkLogado = true;
				}

				Conexao.FecharConexao(conn, pstmt, rs);

			} catch (Exception ex) {
				System.out.println("Erro no perfil do fornecedor. \n Erro: " + ex.getMessage());
			}
		} else {
			JOptionPane.showMessageDialog(null, "Login não efetuado!");
		}
		return checkLogado;
	}

	public boolean VerificaEmail(String Email) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean checkEmail = false;
		try {
			conn = Conexao.getConection();
			String sql = "SELECT * FROM fornecedor WHERE email_fornecedor = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, Email);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				checkEmail = true;
			}
			Conexao.FecharConexao(conn, pstmt, rs);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro na verificação do email! \n Erro: " + e.getMessage());
		}
		return checkEmail;
	}

	public void AlterarSenha(String cpf, String senha) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = Conexao.getConection();
			String sql = "UPDATE fornecedor SET senha_fornecedor = ? WHERE cpf_fornecedor = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, senha);
			pstmt.setString(2, cpf);
			pstmt.executeUpdate();
			Conexao.FecharConexao(conn, pstmt);
		} catch (Exception e) {
			System.out.println("Erro ao alterar senha! \n Erro: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public boolean ConferirCPF_Email(String CPF, String Email) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean checkEmail = false;
		try {
			conn = Conexao.getConection();
			String sql = "SELECT * FROM fornecedor WHERE email_fornecedor = ? AND cpf_fornecedor = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, Email);
			pstmt.setString(2, CPF);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				checkEmail = true;
			}
			Conexao.FecharConexao(conn, pstmt, rs);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro na verificação do email! \n Erro: " + e.getMessage());
		}
		return checkEmail;
	}

	public void InserirImagem(FornecedorBean FornecedorBean) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = Conexao.getConection();
			String sql = "INSERT INTO imagem_fornecedor VALUES (?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, FornecedorBean.getId_fornecedor());
			pstmt.setBytes(2, FornecedorBean.getImagemPerfil());
			pstmt.execute();
			Conexao.FecharConexao(conn, pstmt);
		} catch (Exception e) {
			System.out.println("Erro ao inserir imagem de perfil do fornecedor! \n Erro: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void AtualizarImgPerfil(FornecedorBean FornecedorBean) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = Conexao.getConection();
			String sql = "UPDATE imagem_fornecedor SET img_perfil = ? WHERE id_fornecedor = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setBytes(1, FornecedorBean.getImagemPerfil());
			pstmt.setInt(2, FornecedorBean.getId_fornecedor());
			pstmt.executeUpdate();

			Conexao.FecharConexao(conn, pstmt);
		} catch (Exception e) {
			System.out.println("Erro ao atualizar imagem de perfil do fornecedor! \n Erro: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public boolean PesquisarImagemPerfil(FornecedorBean FornecedorBean) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean verificaImg = false;
		try {
			conn = Conexao.getConection();
			String sql = "SELECT img_perfil FROM imagem_fornecedor WHERE id_fornecedor = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, FornecedorBean.getId_fornecedor());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				FornecedorBean.setImagemPerfil(rs.getBytes("img_perfil"));
				verificaImg = true;
			}
			pstmt.executeUpdate();
			Conexao.FecharConexao(conn, pstmt, rs);
		} catch (Exception e) {
			System.out.println("Falha ao pesquisar imagem existente do fornecedor! \n Erro: " + e.getMessage());
			e.printStackTrace();
		}
		return verificaImg;
	}

	public void inserirImagemPerfil(File file, FornecedorBean FornecedorBean) {
		if (file != null) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = "INSERT INTO imagem_fornecedor VALUES (?,?)";
			try {
				conn = Conexao.getConection();
				pstmt = conn.prepareStatement(sql);
				InputStream in = new FileInputStream(file);
				byte[] bytes = new byte[(int) file.length()];
				int offSet = 0;
				int numRead = 0;
				while (offSet < bytes.length && (numRead = in.read(bytes, offSet, bytes.length - offSet)) >= 0) {
					offSet += numRead;
				}
				pstmt.setInt(1, FornecedorBean.getId_fornecedor());
				pstmt.setBytes(2, bytes);
				pstmt.execute();

			} catch (SQLException ex) {
				ex.printStackTrace();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public Image carregarImagem(FornecedorBean FornecedorBean) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		String sql = "SELECT img_perfil FROM imagem_fornecedor WHERE id_fornecedor = ?";
		try {
			conn = Conexao.getConection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, FornecedorBean.getId_fornecedor());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				InputStream input = rs.getBinaryStream("img_perfil");
				if (input != null) {
					byte[] rb = new byte[1024];
					int ch = 0;
					while ((ch = input.read(rb)) != -1) {
						output.write(rb, 0, ch);
					}
					byte[] b = output.toByteArray();

					Image ExibirImagem = null;
					ExibirImagem = Toolkit.getDefaultToolkit().createImage(b);

					return ExibirImagem;
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	
	
}
